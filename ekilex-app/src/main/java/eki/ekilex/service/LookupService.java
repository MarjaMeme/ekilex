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

import eki.common.constant.ActivityOwner;
import eki.common.constant.FreeformType;
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
import eki.ekilex.data.OrderedClassifier;
import eki.ekilex.data.SearchDatasetsRestriction;
import eki.ekilex.data.SimpleWord;
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
	public SimpleWord getLexemeSimpleWord(Long lexemeId) {
		return lookupDbService.getLexemeSimpleWord(lexemeId);
	}

	@Transactional
	public String getLexemeDatasetCode(Long lexemeId) {
		return lookupDbService.getLexemeDatasetCode(lexemeId);
	}

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
	public MeaningWordCandidates getMeaningWordCandidates(DatasetPermission userRole, String wordValue, String language, Long sourceMeaningId, List<String> tagNames) {

		boolean meaningHasWord = lookupDbService.meaningHasWord(sourceMeaningId, wordValue, language);
		SearchDatasetsRestriction searchDatasetsRestriction = composeDatasetsRestriction(Collections.emptyList());
		WordsResult words = getWords(wordValue, Collections.emptyList(), userRole, tagNames, true, DEFAULT_OFFSET, DEFAULT_MAX_RESULTS_LIMIT);
		List<WordDescript> wordCandidates = new ArrayList<>();
		for (Word word : words.getWords()) {
			List<WordLexeme> lexemes = lexSearchDbService.getWordLexemes(word.getWordId(), searchDatasetsRestriction, CLASSIF_LABEL_LANG_EST, CLASSIF_LABEL_TYPE_DESCRIP);
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
				List<MeaningWord> meaningWords = commonDataDbService.getMeaningWords(lexemeId);
				lexeme.setMeaningWords(meaningWords);
				List<Definition> definitions = commonDataDbService.getMeaningDefinitions(meaningId, datasetCode, CLASSIF_LABEL_LANG_EST, CLASSIF_LABEL_TYPE_DESCRIP);
				permCalculator.filterVisibility(userRole, definitions);
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
	public List<WordDetails> getWordDetailsOfJoinCandidates(DatasetPermission userRole, Word targetWord, List<String> userPrefDatasetCodes, List<String> userVisibleDatasetCodes) {

		List<WordDetails> wordDetailsList = new ArrayList<>();
		Long userId = userRole.getUserId();
		Long targetWordId = targetWord.getWordId();
		List<Long> sourceWordIds = lookupDbService.getWordIdsOfJoinCandidates(targetWord, userPrefDatasetCodes, userVisibleDatasetCodes);
		sourceWordIds.sort(Comparator.comparing(sourceWordId -> !isWordJoinGranted(userId, userRole, sourceWordId, targetWordId)));

		for (Long sourceWordId : sourceWordIds) {
			WordDetails wordDetails = getWordJoinDetails(userRole, sourceWordId);
			wordDetailsList.add(wordDetails);
		}
		return wordDetailsList;
	}

	private boolean isWordJoinGranted(Long userId, DatasetPermission userRole, Long sourceWordId, Long targetWordId) {

		if (!permissionDbService.isGrantedForWord(userId, userRole, sourceWordId, AUTH_ITEM_DATASET, AUTH_OPS_CRUD)) {
			return false;
		}
		return isValidWordStressAndMarkup(sourceWordId, targetWordId);
	}

	@Transactional
	public WordDetails getWordJoinDetails(DatasetPermission userRole, Long wordId) {

		SearchDatasetsRestriction searchDatasetsRestriction = composeDatasetsRestriction(Collections.emptyList());
		Word word = lexSearchDbService.getWord(wordId);
		List<Classifier> wordTypes = commonDataDbService.getWordTypes(wordId, CLASSIF_LABEL_LANG_EST, CLASSIF_LABEL_TYPE_DESCRIP);
		List<WordLexeme> lexemes = lexSearchDbService.getWordLexemes(wordId, searchDatasetsRestriction, CLASSIF_LABEL_LANG_EST, CLASSIF_LABEL_TYPE_DESCRIP);
		List<WordEtymTuple> wordEtymTuples = lexSearchDbService.getWordEtymology(wordId);
		List<WordEtym> wordEtymology = conversionUtil.composeWordEtymology(wordEtymTuples);

		lexemes.forEach(lexeme -> populateLexemeWithMinimalData(userRole, lexeme));
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
			DatasetPermission userRole, List<String> userPrefDatasetCodes,
			String searchWord, Integer wordHomonymNumber, Long excludedMeaningId, List<String> tagNames) {

		SearchDatasetsRestriction searchDatasetsRestriction = composeDatasetsRestriction(userPrefDatasetCodes);
		List<WordLexeme> lexemes = new ArrayList<>();
		if (isNotBlank(searchWord)) {
			String cleanedUpFilter = searchWord.replace("*", "").replace("?", "").replace("%", "").replace("_", "");
			WordsResult words = getWords(cleanedUpFilter, userPrefDatasetCodes, userRole, tagNames, true, DEFAULT_OFFSET, DEFAULT_MAX_RESULTS_LIMIT);
			if (CollectionUtils.isNotEmpty(words.getWords())) {
				Map<String, String> datasetNameMap = commonDataDbService.getDatasetNameMap();
				for (Word word : words.getWords()) {
					if ((wordHomonymNumber != null) && !word.getHomonymNr().equals(wordHomonymNumber)) {
						continue;
					}
					List<WordLexeme> wordLexemes = lexSearchDbService.getWordLexemes(word.getWordId(), searchDatasetsRestriction, CLASSIF_LABEL_LANG_EST, CLASSIF_LABEL_TYPE_DESCRIP);
					wordLexemes.removeIf(lex -> lex.getMeaningId().equals(excludedMeaningId));
					wordLexemes.forEach(lexeme -> {
						Long lexemeId = lexeme.getLexemeId();
						Long meaningId = lexeme.getMeaningId();
						String datasetCode = lexeme.getDatasetCode();
						String datasetName = datasetNameMap.get(datasetCode);
						List<MeaningWord> meaningWords = commonDataDbService.getMeaningWords(lexemeId);
						List<Definition> definitions = commonDataDbService.getMeaningDefinitions(meaningId, datasetCode, CLASSIF_LABEL_LANG_EST, CLASSIF_LABEL_TYPE_DESCRIP);
						permCalculator.filterVisibility(userRole, definitions);
						List<Government> governments = commonDataDbService.getLexemeGovernments(lexemeId);
						List<UsageTranslationDefinitionTuple> usageTranslationDefinitionTuples =
								commonDataDbService.getLexemeUsageTranslationDefinitionTuples(lexemeId, CLASSIF_LABEL_LANG_EST, CLASSIF_LABEL_TYPE_DESCRIP);
						List<Usage> usages = conversionUtil.composeUsages(usageTranslationDefinitionTuples);
						permCalculator.filterVisibility(userRole, usages);

						lexeme.setDatasetName(datasetName);
						lexeme.setMeaningWords(meaningWords);
						lexeme.setGovernments(governments);
						lexeme.setUsages(usages);
						Meaning meaning = new Meaning();
						meaning.setMeaningId(meaningId);
						meaning.setDefinitions(definitions);
						permCalculator.applyCrud(userRole, meaning);
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
	public Meaning getMeaningOfJoinTarget(DatasetPermission userRole, Long meaningId, List<ClassifierSelect> languagesOrder) {

		SearchDatasetsRestriction searchDatasetsRestriction = composeDatasetsRestriction(Collections.emptyList());
		Meaning meaning = termSearchDbService.getMeaning(meaningId, searchDatasetsRestriction);
		composeMeaningSelectData(userRole, meaning, languagesOrder);
		return meaning;
	}

	@Transactional
	public List<Meaning> getMeaningsOfJoinCandidates(
			DatasetPermission userRole, List<String> userPrefDatasetCodes, String searchFilter, List<ClassifierSelect> languagesOrder, Long excludedMeaningId) {

		if (StringUtils.isBlank(searchFilter)) {
			return Collections.emptyList();
		} else {
			SearchDatasetsRestriction searchDatasetsRestriction = composeDatasetsRestriction(userPrefDatasetCodes);
			List<Meaning> meanings = lookupDbService.getMeanings(searchFilter, searchDatasetsRestriction, excludedMeaningId);
			permCalculator.applyCrud(userRole, meanings);
			meanings.sort(Comparator.comparing(meaning -> !meaning.isAnyGrant()));
			meanings.forEach(meaning -> composeMeaningSelectData(userRole, meaning, languagesOrder));
			return meanings;
		}
	}

	@Transactional
	public List<Meaning> getMeaningsOfRelationCandidates(DatasetPermission userRole, String wordValue, Long excludedMeaningId, List<ClassifierSelect> languagesOrder) {

		if (StringUtils.isBlank(wordValue)) {
			return Collections.emptyList();
		} else {
			SearchDatasetsRestriction searchDatasetsRestriction = composeDatasetsRestriction(Collections.emptyList());
			List<Meaning> meanings = lookupDbService.getMeanings(wordValue, searchDatasetsRestriction, excludedMeaningId);
			meanings.forEach(meaning -> composeMeaningSelectData(userRole, meaning, languagesOrder));
			return meanings;
		}
	}

	@Transactional
	public Map<String, Integer[]> getMeaningsWordsWithMultipleHomonymNumbers(List<Long> meaningIds) {
		return lookupDbService.getMeaningsWordsWithMultipleHomonymNumbers(meaningIds);
	}

	@Transactional
	public Long getMeaningId(Long lexemeId) {
		return lookupDbService.getLexemeMeaningId(lexemeId);
	}

	@Transactional
	public List<Classifier> getOppositeRelations(ActivityOwner owner, String relationTypeCode) {

		List<Classifier> oppositeRelations = new ArrayList<>();
		if (ActivityOwner.WORD.equals(owner)) {
			oppositeRelations = lookupDbService.getWordOppositeRelations(relationTypeCode, CLASSIF_LABEL_LANG_EST, CLASSIF_LABEL_TYPE_DESCRIP);
		} else if (ActivityOwner.LEXEME.equals(owner)) {
			oppositeRelations = lookupDbService.getLexemeOppositeRelations(relationTypeCode, CLASSIF_LABEL_LANG_EST, CLASSIF_LABEL_TYPE_DESCRIP);
		} else if (ActivityOwner.MEANING.equals(owner)) {
			oppositeRelations = lookupDbService.getMeaningOppositeRelations(relationTypeCode, CLASSIF_LABEL_LANG_EST, CLASSIF_LABEL_TYPE_DESCRIP);
		}
		return oppositeRelations;
	}

	private void composeMeaningSelectData(DatasetPermission userRole, Meaning meaning, List<ClassifierSelect> languagesOrder) {

		final String[] excludeMeaningAttributeTypes = new String[] {FreeformType.LEARNER_COMMENT.name(), FreeformType.NOTE.name()};
		Map<String, String> datasetNameMap = commonDataDbService.getDatasetNameMap();
		Long meaningId = meaning.getMeaningId();

		List<Definition> definitions = commonDataDbService.getMeaningDefinitions(meaningId, CLASSIF_LABEL_LANG_EST, CLASSIF_LABEL_TYPE_DESCRIP);
		permCalculator.filterVisibility(userRole, definitions);
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
			permCalculator.filterVisibility(userRole, usages);
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

	private void populateLexemeWithMinimalData(DatasetPermission userRole, WordLexeme lexeme) {

		Long lexemeId = lexeme.getLexemeId();
		Long meaningId = lexeme.getMeaningId();
		String datasetCode = lexeme.getDatasetCode();
		List<MeaningWord> meaningWords = commonDataDbService.getMeaningWords(lexemeId);
		List<Definition> definitions = commonDataDbService.getMeaningDefinitions(meaningId, datasetCode, CLASSIF_LABEL_LANG_EST, CLASSIF_LABEL_TYPE_DESCRIP);
		permCalculator.filterVisibility(userRole, definitions);

		lexeme.setMeaningWords(meaningWords);
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
