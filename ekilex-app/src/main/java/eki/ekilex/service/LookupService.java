package eki.ekilex.service;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eki.common.constant.FreeformType;
import eki.common.constant.LayerName;
import eki.common.constant.LifecycleEntity;
import eki.common.service.TextDecorationService;
import eki.common.service.util.LexemeLevelPreseUtil;
import eki.ekilex.data.Classifier;
import eki.ekilex.data.ClassifierSelect;
import eki.ekilex.data.DatasetPermission;
import eki.ekilex.data.Definition;
import eki.ekilex.data.DefinitionLangGroup;
import eki.ekilex.data.FreeForm;
import eki.ekilex.data.Government;
import eki.ekilex.data.Lexeme;
import eki.ekilex.data.LexemeLangGroup;
import eki.ekilex.data.LexemeWordTuple;
import eki.ekilex.data.Meaning;
import eki.ekilex.data.MeaningWord;
import eki.ekilex.data.MeaningWordCandidates;
import eki.ekilex.data.MeaningWordLangGroup;
import eki.ekilex.data.OrderedClassifier;
import eki.ekilex.data.SearchDatasetsRestriction;
import eki.ekilex.data.SourceLink;
import eki.ekilex.data.Usage;
import eki.ekilex.data.UsageTranslationDefinitionTuple;
import eki.ekilex.data.Word;
import eki.ekilex.data.WordDescript;
import eki.ekilex.data.WordDetails;
import eki.ekilex.data.WordEtym;
import eki.ekilex.data.WordEtymTuple;
import eki.ekilex.data.WordLexeme;
import eki.ekilex.data.WordStress;
import eki.ekilex.data.WordsResult;
import eki.ekilex.service.db.CommonDataDbService;
import eki.ekilex.service.db.LexSearchDbService;
import eki.ekilex.service.db.LookupDbService;
import eki.ekilex.service.db.PermissionDbService;
import eki.ekilex.service.db.TermSearchDbService;
import eki.ekilex.service.util.PermCalculator;

@Component
public class LookupService extends AbstractWordSearchService {

	@Autowired
	private LookupDbService lookupDbService;

	@Autowired
	private CommonDataDbService commonDataDbService;

	@Autowired
	private PermissionDbService permissionDbService;

	@Autowired
	private LexSearchDbService lexSearchDbService;

	@Autowired
	private TermSearchDbService termSearchDbService;

	@Autowired
	private LexemeLevelPreseUtil lexemeLevelPreseUtil;

	@Autowired
	private TextDecorationService textDecorationService;

	@Autowired
	private PermCalculator permCalculator;

	@Transactional
	public boolean meaningHasWord(Long meaningId, String wordValue, String language) {
		return lookupDbService.meaningHasWord(meaningId, wordValue, language);
	}

	@Transactional
	public boolean isValidWordStressAndMarkup(Long sourceWordId, Long targetWordId) {

		Map<Long, WordStress> wordStressData = lookupDbService.getWordStressData(sourceWordId, targetWordId, DISPLAY_FORM_STRESS_SYMBOL);
		WordStress sourceWordStress = wordStressData.get(sourceWordId);
		WordStress targetWordStress = wordStressData.get(targetWordId);

		if (sourceWordStress.isMorphExists() && targetWordStress.isMorphExists()) {
			String sourceDisplayForm = sourceWordStress.getDisplayForm();
			boolean isSourceDisplayFormStressExists = sourceWordStress.isStressExists();
			String targetDisplayForm = targetWordStress.getDisplayForm();
			boolean isTargetDisplayFormStressExists = targetWordStress.isStressExists();
			boolean isDisplayFormMatch = isDisplayFormMatch(targetDisplayForm, sourceDisplayForm);
			if (!isDisplayFormMatch) {
				if (isSourceDisplayFormStressExists && isTargetDisplayFormStressExists) {
					return false;
				} else if (!isSourceDisplayFormStressExists && !isTargetDisplayFormStressExists) {
					return false;
				}
			}
		}

		String sourceValuePrese = sourceWordStress.getValuePrese();
		String targetValuePrese = targetWordStress.getValuePrese();
		if (StringUtils.equals(sourceValuePrese, targetValuePrese)) {
			return true;
		}

		boolean isSourceWordDecorated = textDecorationService.isDecorated(sourceValuePrese);
		boolean isTargetWordDecorated = textDecorationService.isDecorated(targetValuePrese);
		if (isSourceWordDecorated && isTargetWordDecorated) {
			return false;
		}
		return true;
	}

	private boolean isDisplayFormMatch(String targetDisplayForm, String sourceDisplayForm) {

		String cleanedTargetDisplayForm = targetDisplayForm;
		String cleanedSourceDisplayForm = sourceDisplayForm;

		for (String ignoreSymbol : DISPLAY_FORM_IGNORE_SYMBOLS) {
			cleanedTargetDisplayForm = StringUtils.remove(cleanedTargetDisplayForm, ignoreSymbol);
			cleanedSourceDisplayForm = StringUtils.remove(cleanedSourceDisplayForm, ignoreSymbol);
		}

		return StringUtils.equals(cleanedTargetDisplayForm, cleanedSourceDisplayForm);
	}

	@Transactional
	public MeaningWordCandidates getMeaningWordCandidates(Long sourceMeaningId, String wordValue, String language, Long userId, DatasetPermission userRole, LayerName layerName) {

		boolean meaningHasWord = lookupDbService.meaningHasWord(sourceMeaningId, wordValue, language);
		SearchDatasetsRestriction searchDatasetsRestriction = composeDatasetsRestriction(Collections.emptyList());
		WordsResult words = getWords(wordValue, Collections.emptyList(), userRole, layerName, true, DEFAULT_OFFSET, DEFAULT_MAX_RESULTS_LIMIT);
		List<WordDescript> wordCandidates = new ArrayList<>();
		for (Word word : words.getWords()) {
			List<WordLexeme> lexemes = lexSearchDbService.getWordLexemes(word.getWordId(), searchDatasetsRestriction, classifierLabelLang, classifierLabelTypeDescrip);
			boolean lexemeAlreadyExists = false;
			if (sourceMeaningId != null) {
				lexemeAlreadyExists = lexemes.stream().anyMatch(lexeme -> lexeme.getMeaningId().equals(sourceMeaningId));
			}
			if (lexemeAlreadyExists) {
				continue;
			}
			List<String> allDefinitionValues = new ArrayList<>();
			lexemes.forEach(lexeme -> {
				Long lexemeId = lexeme.getLexemeId();
				Long meaningId = lexeme.getMeaningId();
				String datasetCode = lexeme.getDatasetCode();
				List<MeaningWord> meaningWords = lexSearchDbService.getMeaningWords(lexemeId);
				List<MeaningWordLangGroup> meaningWordLangGroups = conversionUtil.composeMeaningWordLangGroups(meaningWords, lexeme.getWordLang());
				lexeme.setMeaningWordLangGroups(meaningWordLangGroups);
				List<Definition> definitions = commonDataDbService.getMeaningDefinitions(meaningId, datasetCode, CLASSIF_LABEL_LANG_EST, CLASSIF_LABEL_TYPE_DESCRIP);
				permCalculator.filterVisibility(definitions, userId);
				List<String> lexemeDefinitionValues = definitions.stream().map(def -> def.getValue()).collect(Collectors.toList());
				allDefinitionValues.addAll(lexemeDefinitionValues);
			});
			List<String> distinctDefinitionValues = allDefinitionValues.stream().distinct().collect(Collectors.toList());
			WordDescript wordCandidate = new WordDescript();
			wordCandidate.setWord(word);
			wordCandidate.setLexemes(lexemes);
			wordCandidate.setDefinitions(distinctDefinitionValues);
			wordCandidates.add(wordCandidate);
		}
		boolean wordCandidatesExist = CollectionUtils.isNotEmpty(wordCandidates);
		MeaningWordCandidates meaningWordCandidates = new MeaningWordCandidates();
		meaningWordCandidates.setWordValue(wordValue);
		meaningWordCandidates.setMeaningHasWord(meaningHasWord);
		meaningWordCandidates.setWordCandidates(wordCandidates);
		meaningWordCandidates.setWordCandidatesExist(wordCandidatesExist);
		return meaningWordCandidates;
	}

	@Transactional
	public List<WordDetails> getWordDetailsOfJoinCandidates(
			Word targetWord, String roleDatasetCode, List<String> userPrefDatasetCodes,
			List<String> userPermDatasetCodes, Long userId) {

		List<WordDetails> wordDetailsList = new ArrayList<>();
		Long targetWordId = targetWord.getWordId();
		List<Long> sourceWordIds = lookupDbService.getWordIdsOfJoinCandidates(targetWord, userPrefDatasetCodes, userPermDatasetCodes);
		sourceWordIds.sort(Comparator.comparing(sourceWordId -> !isWordJoinGranted(sourceWordId, targetWordId, roleDatasetCode, userPermDatasetCodes)));

		for (Long sourceWordId : sourceWordIds) {
			WordDetails wordDetails = getWordJoinDetails(sourceWordId, userId);
			wordDetailsList.add(wordDetails);
		}
		return wordDetailsList;
	}

	private boolean isWordJoinGranted(Long sourceWordId, Long targetWordId, String roleDatasetCode, List<String> userPermDatasetCodes) {

		if (!permissionDbService.isGrantedForWord(sourceWordId, roleDatasetCode, userPermDatasetCodes)) {
			return false;
		}
		return isValidWordStressAndMarkup(sourceWordId, targetWordId);
	}

	@Transactional
	public WordDetails getWordJoinDetails(Long wordId, Long userId) {

		SearchDatasetsRestriction searchDatasetsRestriction = composeDatasetsRestriction(Collections.emptyList());
		Word word = lexSearchDbService.getWord(wordId);
		List<Classifier> wordTypes = commonDataDbService.getWordTypes(wordId, CLASSIF_LABEL_LANG_EST, CLASSIF_LABEL_TYPE_DESCRIP);
		List<WordLexeme> lexemes = lexSearchDbService.getWordLexemes(wordId, searchDatasetsRestriction, classifierLabelLang, classifierLabelTypeDescrip);
		List<WordEtymTuple> wordEtymTuples = lexSearchDbService.getWordEtymology(wordId);
		List<WordEtym> wordEtymology = conversionUtil.composeWordEtymology(wordEtymTuples);

		lexemes.forEach(lexeme -> populateLexemeWithMinimalData(lexeme, userId));
		lexemeLevelPreseUtil.combineLevels(lexemes);
		String firstDefinitionValue = getFirstDefinitionValue(lexemes);

		WordDetails wordDetails = new WordDetails();
		wordDetails.setWord(word);
		wordDetails.setWordTypes(wordTypes);
		wordDetails.setLexemes(lexemes);
		wordDetails.setWordEtymology(wordEtymology);
		wordDetails.setFirstDefinitionValue(firstDefinitionValue);

		return wordDetails;
	}

	@Transactional
	public List<WordLexeme> getWordLexemesOfJoinCandidates(
			String searchWord, List<String> userPrefDatasetCodes, Integer wordHomonymNumber,
			Long excludedMeaningId, Long userId, DatasetPermission userRole, LayerName layerName) {

		SearchDatasetsRestriction searchDatasetsRestriction = composeDatasetsRestriction(userPrefDatasetCodes);
		List<WordLexeme> lexemes = new ArrayList<>();
		if (isNotBlank(searchWord)) {
			String cleanedUpFilter = searchWord.replace("*", "").replace("?", "").replace("%", "").replace("_", "");
			WordsResult words = getWords(cleanedUpFilter, userPrefDatasetCodes, userRole, layerName, true, DEFAULT_OFFSET, DEFAULT_MAX_RESULTS_LIMIT);
			if (CollectionUtils.isNotEmpty(words.getWords())) {
				Map<String, String> datasetNameMap = commonDataDbService.getDatasetNameMap();
				for (Word word : words.getWords()) {
					if ((wordHomonymNumber != null) && !word.getHomonymNr().equals(wordHomonymNumber)) {
						continue;
					}
					List<WordLexeme> wordLexemes = lexSearchDbService.getWordLexemes(word.getWordId(), searchDatasetsRestriction, classifierLabelLang, classifierLabelTypeDescrip);
					wordLexemes.removeIf(lex -> lex.getMeaningId().equals(excludedMeaningId));
					wordLexemes.forEach(lexeme -> {
						Long lexemeId = lexeme.getLexemeId();
						Long meaningId = lexeme.getMeaningId();
						String datasetCode = lexeme.getDatasetCode();
						String datasetName = datasetNameMap.get(datasetCode);
						List<MeaningWord> meaningWords = lexSearchDbService.getMeaningWords(lexemeId);
						List<MeaningWordLangGroup> meaningWordLangGroups = conversionUtil.composeMeaningWordLangGroups(meaningWords, lexeme.getWordLang());
						List<Definition> definitions = commonDataDbService.getMeaningDefinitions(meaningId, datasetCode, CLASSIF_LABEL_LANG_EST, CLASSIF_LABEL_TYPE_DESCRIP);
						permCalculator.filterVisibility(definitions, userId);
						List<Government> governments = commonDataDbService.getLexemeGovernments(lexemeId);
						List<UsageTranslationDefinitionTuple> usageTranslationDefinitionTuples =
								commonDataDbService.getLexemeUsageTranslationDefinitionTuples(lexemeId, CLASSIF_LABEL_LANG_EST, CLASSIF_LABEL_TYPE_DESCRIP);
						List<Usage> usages = conversionUtil.composeUsages(usageTranslationDefinitionTuples);
						permCalculator.filterVisibility(usages, userId);

						lexeme.setDatasetName(datasetName);
						lexeme.setMeaningWordLangGroups(meaningWordLangGroups);
						lexeme.setGovernments(governments);
						lexeme.setUsages(usages);
						Meaning meaning = new Meaning();
						meaning.setMeaningId(meaningId);
						meaning.setDefinitions(definitions);
						permCalculator.applyCrud(meaning, userRole);
						lexeme.setMeaning(meaning);
					});
					lexemeLevelPreseUtil.combineLevels(wordLexemes);
					lexemes.addAll(wordLexemes);
				}
			}
		}
		lexemes.sort(Comparator.comparing(lexeme -> !lexeme.getMeaning().isAnyGrant()));
		return lexemes;
	}

	@Transactional
	public Meaning getMeaningOfJoinTarget(Long meaningId, List<ClassifierSelect> languagesOrder, Long userId) {

		SearchDatasetsRestriction searchDatasetsRestriction = composeDatasetsRestriction(Collections.emptyList());
		Meaning meaning = termSearchDbService.getMeaning(meaningId, searchDatasetsRestriction);
		composeMeaningSelectData(meaning, languagesOrder, userId);
		return meaning;
	}

	@Transactional
	public List<Meaning> getMeaningsOfJoinCandidates(String searchFilter, List<String> userPrefDatasetCodes, List<ClassifierSelect> languagesOrder,
			Long excludedMeaningId, Long userId, DatasetPermission userRole) {

		if (StringUtils.isBlank(searchFilter)) {
			return Collections.emptyList();
		} else {
			SearchDatasetsRestriction searchDatasetsRestriction = composeDatasetsRestriction(userPrefDatasetCodes);
			List<Meaning> meanings = lookupDbService.getMeanings(searchFilter, searchDatasetsRestriction, excludedMeaningId);
			permCalculator.applyCrud(meanings, userRole);
			meanings.sort(Comparator.comparing(meaning -> !meaning.isAnyGrant()));
			meanings.forEach(meaning -> composeMeaningSelectData(meaning, languagesOrder, userId));
			return meanings;
		}
	}

	@Transactional
	public List<Meaning> getMeaningsOfRelationCandidates(Long excludedMeaningId, String wordValue, List<ClassifierSelect> languagesOrder, Long userId) {

		if (StringUtils.isBlank(wordValue)) {
			return Collections.emptyList();
		} else {
			SearchDatasetsRestriction searchDatasetsRestriction = composeDatasetsRestriction(Collections.emptyList());
			List<Meaning> meanings = lookupDbService.getMeanings(wordValue, searchDatasetsRestriction, excludedMeaningId);
			meanings.forEach(meaning -> composeMeaningSelectData(meaning, languagesOrder, userId));
			return meanings;
		}
	}

	@Transactional
	public Map<String, Integer[]> getMeaningsWordsWithMultipleHomonymNumbers(List<Long> meaningIds) {
		return lookupDbService.getMeaningsWordsWithMultipleHomonymNumbers(meaningIds);
	}

	@Transactional
	public Long getMeaningId(Long lexemeId) {
		return lookupDbService.getMeaningId(lexemeId);
	}

	@Transactional
	public List<Classifier> getOppositeRelations(LifecycleEntity entity, String relationTypeCode) {

		List<Classifier> oppositeRelations = new ArrayList<>();
		if (LifecycleEntity.WORD.equals(entity)) {
			oppositeRelations = lookupDbService.getWordOppositeRelations(relationTypeCode, CLASSIF_LABEL_LANG_EST, CLASSIF_LABEL_TYPE_FULL);
		} else if (LifecycleEntity.LEXEME.equals(entity)) {
			oppositeRelations = lookupDbService.getLexemeOppositeRelations(relationTypeCode, CLASSIF_LABEL_LANG_EST, CLASSIF_LABEL_TYPE_FULL);
		} else if (LifecycleEntity.MEANING.equals(entity)) {
			oppositeRelations = lookupDbService.getMeaningOppositeRelations(relationTypeCode, CLASSIF_LABEL_LANG_EST, CLASSIF_LABEL_TYPE_DESCRIP);
		}
		return oppositeRelations;
	}

	private void composeMeaningSelectData(Meaning meaning, List<ClassifierSelect> languagesOrder, Long userId) {

		final String[] excludeMeaningAttributeTypes = new String[] {FreeformType.LEARNER_COMMENT.name(), FreeformType.NOTE.name()};
		Map<String, String> datasetNameMap = commonDataDbService.getDatasetNameMap();
		Long meaningId = meaning.getMeaningId();

		List<Definition> definitions = commonDataDbService.getMeaningDefinitions(meaningId, CLASSIF_LABEL_LANG_EST, CLASSIF_LABEL_TYPE_DESCRIP);
		permCalculator.filterVisibility(definitions, userId);
		List<DefinitionLangGroup> definitionLangGroups = conversionUtil.composeMeaningDefinitionLangGroups(definitions, languagesOrder);
		List<OrderedClassifier> domains = commonDataDbService.getMeaningDomains(meaningId);
		domains = conversionUtil.removeOrderedClassifierDuplicates(domains);
		List<FreeForm> meaningFreeforms = commonDataDbService.getMeaningFreeforms(meaningId, excludeMeaningAttributeTypes);

		List<Long> lexemeIds = meaning.getLexemeIds();
		List<Lexeme> lexemes = new ArrayList<>();
		for (Long lexemeId : lexemeIds) {
			LexemeWordTuple lexemeWordTuple = termSearchDbService.getLexemeWordTuple(lexemeId, CLASSIF_LABEL_LANG_EST, CLASSIF_LABEL_TYPE_DESCRIP);
			Lexeme lexeme = conversionUtil.composeLexeme(lexemeWordTuple);
			List<Classifier> wordTypes = commonDataDbService.getWordTypes(lexeme.getWordId(), CLASSIF_LABEL_LANG_EST, CLASSIF_LABEL_TYPE_DESCRIP);
			List<UsageTranslationDefinitionTuple> usageTranslationDefinitionTuples =
					commonDataDbService.getLexemeUsageTranslationDefinitionTuples(lexemeId, CLASSIF_LABEL_LANG_EST, CLASSIF_LABEL_TYPE_DESCRIP);
			List<Usage> usages = conversionUtil.composeUsages(usageTranslationDefinitionTuples);
			permCalculator.filterVisibility(usages, userId);
			List<SourceLink> lexemeRefLinks = commonDataDbService.getLexemeSourceLinks(lexemeId);
			String dataset = lexeme.getDatasetCode();
			dataset = datasetNameMap.get(dataset);

			lexeme.setDatasetCode(dataset);
			lexeme.setWordTypes(wordTypes);
			lexeme.setUsages(usages);
			lexeme.setSourceLinks(lexemeRefLinks);
			lexemes.add(lexeme);
		}
		List<LexemeLangGroup> lexemeLangGroups = conversionUtil.composeLexemeLangGroups(lexemes, languagesOrder);

		meaning.setDefinitionLangGroups(definitionLangGroups);
		meaning.setDomains(domains);
		meaning.setFreeforms(meaningFreeforms);
		meaning.setLexemeLangGroups(lexemeLangGroups);
	}

	private void populateLexemeWithMinimalData(WordLexeme lexeme, Long userId) {

		Long lexemeId = lexeme.getLexemeId();
		Long meaningId = lexeme.getMeaningId();
		String datasetCode = lexeme.getDatasetCode();
		List<MeaningWord> meaningWords = lexSearchDbService.getMeaningWords(lexemeId);
		List<MeaningWordLangGroup> meaningWordLangGroups = conversionUtil.composeMeaningWordLangGroups(meaningWords, lexeme.getWordLang());
		List<Definition> definitions = commonDataDbService.getMeaningDefinitions(meaningId, datasetCode, CLASSIF_LABEL_LANG_EST, CLASSIF_LABEL_TYPE_DESCRIP);
		permCalculator.filterVisibility(definitions, userId);

		lexeme.setMeaningWordLangGroups(meaningWordLangGroups);
		Meaning meaning = new Meaning();
		meaning.setDefinitions(definitions);
		lexeme.setMeaning(meaning);
	}

	private String getFirstDefinitionValue(List<WordLexeme> wordLexemes) {

		Optional<WordLexeme> wordLexemeWithDefinition = wordLexemes.stream()
				.filter(lex -> CollectionUtils.isNotEmpty(lex.getMeaning().getDefinitions()) && Objects.nonNull(lex.getMeaning().getDefinitions().get(0)))
				.findFirst();

		if (wordLexemeWithDefinition.isPresent()) {
			String wordFirstDefinitionValue = wordLexemeWithDefinition.get().getMeaning().getDefinitions().get(0).getValue();
			return wordFirstDefinitionValue;
		} else {
			return null;
		}
	}

}
