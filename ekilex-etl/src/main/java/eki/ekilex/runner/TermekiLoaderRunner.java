package eki.ekilex.runner;

import static java.util.Collections.emptyList;
import static java.util.Collections.emptyMap;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

import eki.common.constant.Complexity;
import eki.common.constant.ContentKey;
import eki.common.constant.FreeformType;
import eki.common.constant.LifecycleEntity;
import eki.common.constant.LifecycleEventType;
import eki.common.constant.LifecycleLogOwner;
import eki.common.constant.LifecycleProperty;
import eki.common.constant.ReferenceType;
import eki.common.constant.SourceType;
import eki.common.constant.TextDecoration;
import eki.common.data.Count;
import eki.ekilex.data.transform.Lexeme;
import eki.ekilex.data.transform.Word;
import eki.ekilex.service.TermekiService;

@Component
@ConditionalOnBean(name = "dataSourceTermeki")
public class TermekiLoaderRunner extends AbstractLoaderRunner {

	private static Logger logger = LoggerFactory.getLogger(TermekiLoaderRunner.class);

	private static final String TERMEKI_MARKUP_PATTERN_FOREIGN = "(\\[i\\](.+?)\\[\\/i\\])";

	private static final String TERMEKI_MARKUP_PATTERN_LINK = "(\\[c=(\\d*)\\](.+?)\\[\\/c\\])";

	private final static String LEXEME_RELATION_ABBREVIATION = "lyh";
	private static final String TERMEKI_CLASSIFIER_PRONUNCIATION = "termeki_pronunciation";
	private static final String TERMEKI_CLASSIFIER_WORD_CLASS = "termeki_word_class";

	private static final String RELATION_TYPE_OTHER = "määramata";
	private static final String RELATION_TYPE_ANT = "vastand";
	private static final String RELATION_TYPE_HYPO = "liigimõiste";
	private static final String RELATION_TYPE_HYPERO = "soomõiste";
	private static final String RELATION_TYPE_MERO = "osamõiste";
	private static final String RELATION_TYPE_HOLO = "tervikumõiste";

	private Pattern markupPatternForeign;

	private Pattern markupPatternLink;

	private Map<String, String> posCodes;

	private Map<String, String> lexemeValueStateCodes;

	private Map<String, String> wordTypeCodes;

	private Map<Integer, String> relationTypeCodes;

	private String dataset;

	@Autowired
	private TermekiService termekiService;

	@Override
	String getDataset() {
		return dataset;
	}

	@Override
	public Complexity getLexemeComplexity() {
		return Complexity.DETAIL;
	}

	@Override
	public Complexity getDefinitionComplexity() {
		return Complexity.DETAIL;
	}

	@Override
	public Complexity getFreeformComplexity() {
		return Complexity.DETAIL;
	}

	@Override
	public void deleteDatasetData() throws Exception {
		//deleteDatasetData(String dataset) does it
	}

	@Transactional
	public void deleteTermekiDatasetData(String dataset) throws Exception {
		deleteDatasetData(dataset);
	}

	@Override
	void initialise() throws Exception {
		markupPatternForeign = Pattern.compile(TERMEKI_MARKUP_PATTERN_FOREIGN);
		markupPatternLink = Pattern.compile(TERMEKI_MARKUP_PATTERN_LINK);
		posCodes = loadClassifierMappingsFor(TERMEKI_CLASSIFIER_PRONUNCIATION);
		posCodes.putAll(loadClassifierMappingsFor(TERMEKI_CLASSIFIER_WORD_CLASS));

		wordTypeCodes = new HashMap<>();
		wordTypeCodes.put("abbreviation", "l");

		lexemeValueStateCodes = new HashMap<>();
		lexemeValueStateCodes.put("variant", "variant");
		lexemeValueStateCodes.put("synonym", "mööndav");
		lexemeValueStateCodes.put("preferredTerm-admn-sts", "eelistatud");
		lexemeValueStateCodes.put("deprecatedTerm-admn-sts", "väldi");

		relationTypeCodes = new HashMap<>();
		relationTypeCodes.put(1, RELATION_TYPE_OTHER);
		relationTypeCodes.put(2, RELATION_TYPE_ANT);
		relationTypeCodes.put(4, RELATION_TYPE_HYPO);
		relationTypeCodes.put(5, RELATION_TYPE_HYPERO);
		relationTypeCodes.put(6, RELATION_TYPE_MERO);
		relationTypeCodes.put(7, RELATION_TYPE_HOLO);
	}

	@Transactional
	public void execute(Integer baseId, String dataset) throws Exception {

		if (!hasTermDatabaseAndIsKnownDataset(baseId, dataset)) {
			return;
		}
		this.dataset = dataset;
		start();

		Context context = new Context();
		context.terms = termekiService.getTerms(baseId);
		context.definitions = termekiService.getDefinitions(baseId);
		context.sourceMapping = loadSources(baseId);
		context.comments = termekiService.getComments(baseId);
		context.examples = termekiService.getExamples(baseId);
		context.images = termekiService.getImages(baseId);
		context.abbreviations = loadAbbreviations(dataset);
		context.genuses = loadGenuses(dataset);
		context.families = loadFamilies(dataset);
		context.describers = loadDescribers(dataset);
		context.describingYears = loadDescribingYears(dataset);
		context.geolDomains = loadGeolDomains(dataset);
		context.models = loadModelHtml(dataset);

		Map<Integer, Long> conceptMeaningIdMap = new HashMap<>();
		doImport(context, dataset, conceptMeaningIdMap);
		List<Map<String, Object>> conceptRelations = termekiService.getConceptRelations();
		createMeaningRelations(conceptMeaningIdMap, conceptRelations);
		updateDataset(baseId, dataset);

		end();
	}

	// abbreviations are present only in termbase 1283851 - Eesti E-tervise SA terminibaas (ett)
	private List<Map<String, Object>> loadAbbreviations(String dataset) {
		if ("ett".equals(dataset)) {
			List<Map<String, Object>> abbreviations = termekiService.getTermAttributes(38708);
			return abbreviations;
		} else {
			return emptyList();
		}
	}

	// genuses are present only in termbase 7351963 - Ihtüoloogia (iht)
	private List<Map<String, Object>> loadGenuses(String dataset) {
		if ("iht".equals(dataset)) {
			List<Map<String, Object>> attributes = termekiService.getConceptAttributes(41152);
			return attributes;
		} else {
			return emptyList();
		}
	}

	// families are present only in termbase 7351963 - Ihtüoloogia (iht)
	private List<Map<String, Object>> loadFamilies(String dataset) {
		if ("iht".equals(dataset)) {
			List<Map<String, Object>> attributes = termekiService.getConceptAttributes(41153);
			return attributes;
		} else {
			return emptyList();
		}
	}

	// describers are present only in termbase 7351963 - Ihtüoloogia (iht)
	private List<Map<String, Object>> loadDescribers(String dataset) {
		if ("iht".equals(dataset)) {
			List<Map<String, Object>> attributes = termekiService.getConceptAttributes(44274);
			return attributes;
		} else {
			return emptyList();
		}
	}

	// describing years are present only in termbase 7351963 - Ihtüoloogia (iht)
	private List<Map<String, Object>> loadDescribingYears(String dataset) {
		if ("iht".equals(dataset)) {
			List<Map<String, Object>> attributes = termekiService.getConceptAttributes(44275);
			return attributes;
		} else {
			return emptyList();
		}
	}

	// in case of Geol, domains are stored as concept extended attributes, termbase 4441577 - Geol (get)
	private List<Map<String, Object>> loadGeolDomains(String dataset) {
		if ("get".equals(dataset)) {
			List<Map<String, Object>> attributes = termekiService.getConceptAttributes(44388);
			return attributes;
		} else {
			return emptyList();
		}
	}

	// models html, termbase 3651782 - Arheoloogia terminibaas (arh)
	private List<Map<String, Object>> loadModelHtml(String dataset) {
		if ("arh".equals(dataset)) {
			List<Map<String, Object>> attributes = termekiService.getConceptAttributes(57314);
			return attributes;
		} else {
			return emptyList();
		}
	}

	private Map<Integer, SourceData> loadSources(Integer baseId) throws Exception {
		List<Map<String, Object>> sources = termekiService.getSources(baseId);
		Map<Integer, SourceData> termekiSourceToEkilexSourceMap = new HashMap<>();
		for (Map<String, Object> source : sources) {
			SourceData sourceData = getOrCreateSource(source);
			if (sourceData != null) {
				termekiSourceToEkilexSourceMap.put(sourceData.origId, sourceData);
			}
		}
		return termekiSourceToEkilexSourceMap;
	}

	private SourceData getOrCreateSource(Map<String, Object> origSource) throws Exception {
		String sourceName = (String) origSource.get("source_name");
		if (StringUtils.isBlank(sourceName)) {
			return null;
		}
		Integer origExtSourceId = (Integer) origSource.get("source_id");
		String extSourceId = origExtSourceId.toString();
		String author = (String) origSource.get("author");
		String publisher = (String) origSource.get("publisher");
		String isbn = (String) origSource.get("isbn");
		String www = (String) origSource.get("source_link");
		Date publishDate = (Date) origSource.get("publish_date");
		SourceType sourceType;
		if (StringUtils.isNotBlank(author)) {
			sourceType = SourceType.PERSON;
		} else if (StringUtils.isNotBlank(publisher)) {
			sourceType = SourceType.PERSON;
		} else if (StringUtils.isNotBlank(isbn)) {
			sourceType = SourceType.DOCUMENT;
		} else if (StringUtils.isNotBlank(www)) {
			sourceType = SourceType.DOCUMENT;
		} else {
			sourceType = SourceType.UNKNOWN;
		}
		Long sourceId = getSource(sourceType, extSourceId, sourceName, getDataset());
		if (sourceId == null) {
			sourceId = createSource(sourceType, extSourceId, sourceName);
			if (StringUtils.isNotBlank(author)) {
				createSourceFreeform(sourceId, FreeformType.SOURCE_AUTHOR, author);
			}
			if (StringUtils.isNotBlank(publisher)) {
				createSourceFreeform(sourceId, FreeformType.SOURCE_PUBLISHER, publisher);
			}
			if (StringUtils.isNotBlank(isbn)) {
				createSourceFreeform(sourceId, FreeformType.SOURCE_ISBN, isbn);
			}
			if (StringUtils.isNotBlank(www)) {
				createSourceFreeform(sourceId, FreeformType.SOURCE_WWW, www);
			}
			if (publishDate != null) {
				Timestamp publishedTs = new Timestamp(publishDate.getTime());
				createSourceFreeform(sourceId, FreeformType.SOURCE_PUBLICATION_YEAR, publishedTs);
			}
		}
		return new SourceData(origExtSourceId, sourceId, sourceName);
	}

	private void updateDataset(Integer baseId, String dataset) throws Exception {

		Map<String, Object> termbase = termekiService.getTermbase(baseId);
		Map<String, Object> params = new HashMap<>();
		params.put("code", dataset);
		Map<String, Object> values = new HashMap<>();
		values.put("name", termbase.get("termbase_name"));
		values.put("description", termbase.get("description"));
		values.put("is_public", termbase.get("is_public"));
		basicDbService.update(DATASET, params, values);
	}

	private void doImport(Context context, String dataset, Map<Integer, Long> conceptMeaningIdMap) throws Exception {

		Count wordDuplicateCount = new Count();
		Map<Integer, Long> termWordIdMap = new HashMap<>();
		List<String> existingGenders = getGenders();

		long termCount = context.terms.size();
		long termCounter = 0;
		long progressIndicator = termCount / Math.min(termCount, 100);

		for (Map<String, Object> term : context.terms) {

			String wordValue = (String) term.get("term");
			if (StringUtils.isBlank(wordValue)) {
				continue;
			}
			Integer termId = (Integer) term.get("term_id");
			Integer conceptId = (Integer) term.get("concept_id");
			String language = (String) term.get("lang");
			language = unifyLang(language);
			String gender = (String) term.get("gender");
			String pronunciation = (String) term.get("pronunciation");
			String wordClass = term.get("word_class").toString();
			Integer sourceId = (Integer) term.get("source_id");
			Integer createdById = (Integer) term.get("creater_id");
			Timestamp createdOn = (Timestamp) term.get("create_time");
			Integer modifiedById = (Integer) term.get("changer_id");
			Timestamp modifiedOn = (Timestamp) term.get("change_time");
			Boolean isPublic = (Boolean) term.get("is_public");
			String administrativeStatus = (String) term.get("administrative_status");
			//TODO not relevant any more?
			//Integer termStatus = (Integer) term.get("term_status");
			String termType = (String) term.get("term_type");
			Boolean inDictionary = (Boolean) term.get("in_dictionary");

			boolean isValidTerm = isValidTerm(dataset, inDictionary);
			if (!isValidTerm) {
				continue;
			}

			String wordTypeCode = null;
			String lexemeValueStateCode = null;
			if (StringUtils.isNotBlank(termType)) {
				wordTypeCode = wordTypeCodes.get(termType);
				lexemeValueStateCode = lexemeValueStateCodes.get(termType);
			}

			Long wordId;
			if (termWordIdMap.containsKey(termId)) {
				wordId = termWordIdMap.get(termId);
			} else {
				int homonymNr = getWordMaxHomonymNr(wordValue, language) + 1;
				List<String> wordTypeCodes = null;
				if (StringUtils.isNotBlank(wordTypeCode)) {
					wordTypeCodes = new ArrayList<>();
					wordTypeCodes.add(wordTypeCode);
				}
				Word word = new Word(wordValue, language, homonymNr, null, DEFAULT_WORD_MORPH_CODE, null, null, null, null, wordTypeCodes);
				String genderCode = intoGenderCode(gender);
				if (StringUtils.isNotBlank(genderCode)) {
					if (existingGenders.contains(genderCode)) {
						word.setGenderCode(genderCode);
					} else {
						logger.info("Invalid gender code : {}", genderCode);
					}
				}
				wordId = createOrSelectWord(word, null, wordDuplicateCount);
				termWordIdMap.put(termId, wordId);
			}

			if (createdOn != null && createdById != null) {
				createLifecycleLog(LifecycleLogOwner.WORD, wordId, wordId, LifecycleEntity.WORD, LifecycleProperty.VALUE, LifecycleEventType.CREATE, String.valueOf(createdById),
						createdOn, wordValue);
			}
			if (modifiedOn != null && modifiedById != null) {
				createLifecycleLog(LifecycleLogOwner.WORD, wordId, wordId, LifecycleEntity.WORD, LifecycleProperty.VALUE, LifecycleEventType.UPDATE, String.valueOf(modifiedById),
						modifiedOn, wordValue);
			}

			Long meaningId;
			if (conceptMeaningIdMap.containsKey(conceptId)) {
				meaningId = conceptMeaningIdMap.get(conceptId);
			} else {
				meaningId = createMeaning();
				conceptMeaningIdMap.put(conceptId, meaningId);

				createMeaningFreeform(meaningId, FreeformType.CONCEPT_ID, conceptId.toString());
				addMeaningFreeforms(context, conceptId, meaningId);
				saveImages(context, conceptId, meaningId);
			}

			List<String> domainCodes = getDomainCodes(term, context, dataset);
			for (String domainCode : domainCodes) {
				Map<String, Object> domain = getDomain(domainCode, dataset);
				if (domain == null) {
					logger.info("Invalid domain code : {}", domainCode);
				} else {
					createMeaningDomain(meaningId, dataset, domainCode);
					//updateDomainDatsetsIfNeeded(domain, dataset);
				}
			}

			if (StringUtils.isBlank(lexemeValueStateCode)) {
				lexemeValueStateCode = lexemeValueStateCodes.get(administrativeStatus);
			}

			Lexeme lexeme = new Lexeme();
			lexeme.setWordId(wordId);
			lexeme.setMeaningId(meaningId);
			lexeme.setIsPublic(PUBLICITY_PRIVATE);
			lexeme.setValueStateCode(lexemeValueStateCode);
			Long lexemeId = createLexemeIfNotExists(lexeme);
			if (lexemeId != null) {
				String posCode;
				if (StringUtils.isBlank(pronunciation)) {
					posCode = wordClass;
				} else {
					posCode = pronunciation;
				}
				savePosCode(lexemeId, posCode);
				createLexemeSourceLink(context, sourceId, lexemeId);
				createAbbreviationIfNeeded(context, termId, meaningId, lexemeId, language, dataset, wordDuplicateCount);
				saveUsages(context, lexemeId, termId);
				if (isPublic) {
					updateLexemePublicity(lexemeId, isPublic);
				}
			}

			// progress
			termCounter++;
			if (termCounter % progressIndicator == 0) {
				long progressPercent = termCounter / progressIndicator;
				logger.debug("{}% - {} terms iterated", progressPercent, termCounter);
			}
		}
		logger.info("{} terms imported", termWordIdMap.size());
		logger.info("{} duplicate terms found", wordDuplicateCount.getValue());
		logger.info("{} meanings created", conceptMeaningIdMap.size());

		int definitionsCount = 0;
		for (Map<String, Object> definition : context.definitions) {
			String language = unifyLang((String) definition.get("lang"));
			Integer conceptId = (Integer) definition.get("concept_id");
			if (conceptMeaningIdMap.containsKey(conceptId)) {
				Long meaningId = conceptMeaningIdMap.get(conceptId);
				String definitionValue = (String) definition.get("definition");
				definitionValue = applyPattern(markupPatternForeign, definitionValue, TextDecoration.FOREIGN);
				definitionValue = convertTermekiLinkMarkup(definitionValue, conceptMeaningIdMap);
				Long definitionId = createOrSelectDefinition(meaningId, definitionValue, language);
				definitionsCount++;
				String note = (String) definition.get("description");
				if (isNotBlank(note)) {
					createMeaningFreeform(meaningId, FreeformType.NOTE, note);
				}
				Integer sourceId = (Integer) definition.get("source_id");
				createDefinitionSourceLink(context, definitionId, sourceId);

				Integer createdById = (Integer) definition.get("creater_id");
				Timestamp createdOn = (Timestamp) definition.get("create_time");
				Integer modifiedById = (Integer) definition.get("changer_id");
				Timestamp modifiedOn = (Timestamp) definition.get("change_time");
				if (createdOn != null && createdById != null) {
					createLifecycleLog(LifecycleLogOwner.MEANING, meaningId, definitionId, LifecycleEntity.DEFINITION, LifecycleProperty.VALUE,
							LifecycleEventType.CREATE, String.valueOf(createdById), createdOn, definitionValue);
				}
				if (modifiedOn != null && modifiedById != null) {
					createLifecycleLog(LifecycleLogOwner.MEANING, meaningId, definitionId, LifecycleEntity.DEFINITION, LifecycleProperty.VALUE,
							LifecycleEventType.UPDATE, String.valueOf(modifiedById), modifiedOn, definitionValue);
				}
			}
		}
		logger.info("{} definitions created", definitionsCount);

		for (Map<String, Object> comment : context.comments) {
			Integer conceptId = (Integer) comment.get("concept_id");
			if (conceptMeaningIdMap.containsKey(conceptId)) {
				Long meaningId = conceptMeaningIdMap.get(conceptId);
				String privateNote = (String) comment.get("content");
				if (isNotBlank(privateNote)) {
					createMeaningFreeform(meaningId, FreeformType.NOTE, privateNote, false);
				}
			}
		}
	}

	private boolean isValidTerm(String dataset, Boolean inDictionary) {

		if ("les".equals(dataset) && !inDictionary) {
			return false;
		} else {
			return true;
		}
	}

	private String convertTermekiLinkMarkup(String text, Map<Integer, Long> conceptMeaningIdMap) {

		StringBuffer decorBuf = new StringBuffer();
		Matcher matcher = markupPatternLink.matcher(text);
		int textLength = text.length();
		int textStart = 0;
		int matchStart;
		int matchEnd;
		String cleanTextFragment;
		String conceptIdStr;
		Integer conceptId;
		Long meaningId;
		String linkId;
		String linkValue;
		String decoratedLink;
		while (matcher.find()) {
			matchStart = matcher.start();
			matchEnd = matcher.end();
			cleanTextFragment = StringUtils.substring(text, textStart, matchStart);
			decorBuf.append(cleanTextFragment);
			conceptIdStr = matcher.group(2);
			linkValue = matcher.group(3);
			if (StringUtils.isBlank(conceptIdStr)) {
				//broken link
				decorBuf.append(linkValue);
			} else {
				conceptId = Integer.valueOf(conceptIdStr);
				meaningId = conceptMeaningIdMap.get(conceptId);
				linkId = null;
				if (meaningId != null) {
					linkId = meaningId.toString();
				}
				decoratedLink = composeLinkMarkup(ContentKey.MEANING_LINK, linkId, linkValue);
				decorBuf.append(decoratedLink);
			}
			textStart = matchEnd;
		}
		if (textStart < textLength) {
			cleanTextFragment = StringUtils.substring(text, textStart, textLength);
			decorBuf.append(cleanTextFragment);
		}
		text = decorBuf.toString();
		return text;
	}

	private void saveImages(Context context, Integer conceptId, Long meaningId) throws Exception {

		List<Map<String, Object>> images = context.images.stream().filter(f -> f.get("concept_id").equals(conceptId)).collect(toList());
		if (images.isEmpty()) {
			return;
		}

		for (Map<String, Object> image : images) {
			Long imageFreeformId = createMeaningFreeform(meaningId, FreeformType.IMAGE_FILE, image.get("image_id").toString());

			String heading = (String) image.get("heading");
			if (StringUtils.isNotBlank(heading)) {
				createFreeformTextEkiMarkup(imageFreeformId, FreeformType.IMAGE_TITLE, heading, null, null, null);
			}

			Integer extSourceId = (Integer) image.get("source_id");
			if (context.sourceMapping.containsKey(extSourceId)) {
				SourceData ekilexSource = context.sourceMapping.get(extSourceId);
				createFreeformSourceLink(imageFreeformId, ReferenceType.ANY, ekilexSource.id, null, ekilexSource.name);
			}
		}
	}

	private void saveUsages(Context context, Long lexemeId, Integer termId) throws Exception {

		List<Map<String, Object>> examples = context.examples.stream().filter(f -> f.get("term_id").equals(termId)).collect(toList());
		if (examples.isEmpty())
			return;

		for (Map<String, Object> example : examples) {
			String lang = unifyLang((String) example.get("lang"));
			Long usageId = createLexemeFreeform(lexemeId, FreeformType.USAGE, example.get("example"), lang);
			Integer sourceId = (Integer) example.get("source_id");
			createUsageSourceLink(context, sourceId, usageId);
		}
	}

	private String intoGenderCode(String gender) {

		if (StringUtils.startsWith(gender, "die")) {
			return "f";
		} else if (StringUtils.startsWith(gender, "der")) {
			return "n";
		} else if (StringUtils.startsWith(gender, "das")) {
			return "m";
		} else {
			return gender;
		}
	}

	private List<String> getDomainCodes(Map<String, Object> term, Context context, String dataset) {

		List<String> codes = new ArrayList<>();
		if ("get".equals(dataset)) {
			Integer conceptId = (Integer) term.get("concept_id");
			Optional<Map<String, Object>> domainCode = context.geolDomains.stream().filter(d -> d.get("concept_id").equals(conceptId)).findFirst();
			if (domainCode.isPresent()) {
				String codeValue = cleanUpGeoDomainCodes((String) domainCode.get().get("attribute_value"));
				String[] codeValues = codeValue.split(",");
				for (String code : codeValues) {
					if (isNotBlank(code)) {
						codes.add(code);
					}
				}
			}
		} else {
			String code = (String) term.get("domain_code");
			if (isNotBlank(code)) {
				codes.add(code);
			}
		}
		codes = codes.stream().distinct().collect(Collectors.toList());
		return codes;
	}

	private String cleanUpGeoDomainCodes(String domainCodes) {
		String cleanedUp = domainCodes.toLowerCase()
				.replace("(paleo)", "pecol,")
				.replace("ecology", "ecol")
				.replace("cim", "clim")
				.replace("paleoecol", "pecol")
				.replace("glscio", "glacio")
				.replace("goechem", "geochem")
				.replace("hydrogeology", "hydro")
				.replace(" ", "")
				.replace("gen.gl", "gen. gl")
				.replace("gengl", "gen. gl")
				.replace("q", "Q")
				.replace(";", ",");
		if (cleanedUp.equals("eco")) {
			cleanedUp = "ecol";
		}
		return cleanedUp;
	}

	private void addMeaningFreeforms(Context context, Integer conceptId, Long meaningId) throws Exception {
		createMeaningFreeformOfType(FreeformType.GENUS, context.genuses, conceptId, meaningId);
		createMeaningFreeformOfType(FreeformType.FAMILY, context.families, conceptId, meaningId);
		createMeaningFreeformOfType(FreeformType.DESCRIBER, context.describers, conceptId, meaningId);
		createMeaningFreeformOfType(FreeformType.DESCRIBING_YEAR, context.describingYears, conceptId, meaningId);
		createMeaningFreeformOfType(FreeformType.NOTE, context.models, conceptId, meaningId);
	}

	private void createMeaningFreeformOfType(FreeformType freeformType, List<Map<String, Object>> items, Integer conceptId, Long meaningId) throws Exception {
		if (!items.isEmpty()) {
			Optional<Map<String, Object>> item = items.stream().filter(i -> i.get("concept_id").equals(conceptId)).findFirst();
			if (item.isPresent()) {
				createMeaningFreeform(meaningId, freeformType, item.get().get("attribute_value"));
			}
		}
	}

	private void createAbbreviationIfNeeded(
			Context context,
			Integer termId,
			Long meaningId,
			Long termLexemeId,
			String language,
			String dataset,
			Count wordDuplicateCount) throws Exception {

		if (CollectionUtils.isEmpty(context.abbreviations)) {
			return;
		}

		Optional<Map<String, Object>> abbreviation = context.abbreviations.stream().filter(a -> a.get("term_id").equals(termId)).findFirst();
		if (abbreviation.isPresent()) {
			String abbreviationValue = (String) abbreviation.get().get("attribute_value");
			int homonymNr = getWordMaxHomonymNr(abbreviationValue, language) + 1;
			Word word = new Word(abbreviationValue, language, homonymNr, null, DEFAULT_WORD_MORPH_CODE, null, null, null, null, null);
			Long wordId = createOrSelectWord(word, null, wordDuplicateCount);
			Lexeme lexeme = new Lexeme();
			lexeme.setWordId(wordId);
			lexeme.setMeaningId(meaningId);
			Long abbreviationLexemeId = createLexemeIfNotExists(lexeme);
			createLexemeRelation(abbreviationLexemeId, termLexemeId, LEXEME_RELATION_ABBREVIATION);
		}
	}

	private void createDefinitionSourceLink(Context context, Long definitionId, Integer sourceId) throws Exception {

		if (context.sourceMapping.containsKey(sourceId)) {
			SourceData ekilexSource = context.sourceMapping.get(sourceId);
			//TODO define proper ref type
			createDefinitionSourceLink(definitionId, ReferenceType.ANY, ekilexSource.id, null, ekilexSource.name);
		}
	}

	private void createLexemeSourceLink(Context context, Integer sourceId, Long lexemeId) throws Exception {

		if (context.sourceMapping.containsKey(sourceId)) {
			SourceData ekilexSource = context.sourceMapping.get(sourceId);
			//TODO define proper ref type
			createLexemeSourceLink(lexemeId, ReferenceType.ANY, ekilexSource.id, null, ekilexSource.name);
		}
	}

	private void createUsageSourceLink(Context context, Integer sourceId, Long usageId) throws Exception {

		if (context.sourceMapping.containsKey(sourceId)) {
			SourceData ekilexSource = context.sourceMapping.get(sourceId);
			//TODO define proper ref type
			createFreeformSourceLink(usageId, ReferenceType.ANY, ekilexSource.id, null, ekilexSource.name);
		}
	}

	private void savePosCode(Long lexemeId, String posCode) throws Exception {

		if (posCodes.containsKey(posCode)) {
			String mappedPosCode = posCodes.get(posCode);
			createLexemePos(lexemeId, mappedPosCode);
		}
	}

	private boolean hasTermDatabaseAndIsKnownDataset(Integer baseId, String dataset) throws Exception {
		return termekiService.hasTermDatabase(baseId) && isKnownDataset(dataset);
	}

	private Map<String, Object> getDomain(String code, String origin) throws Exception {

		Map<String, Object> tableRowParamMap = new HashMap<>();
		tableRowParamMap.put("code", code);
		tableRowParamMap.put("origin", origin);
		return basicDbService.select(DOMAIN, tableRowParamMap);
	}

	private List<String> getGenders() throws Exception {
		return basicDbService.selectAll(GENDER, emptyMap())
				.stream().map(rec -> (String) rec.get("code")).collect(toList());
	}

	private boolean isKnownDataset(String dataset) throws Exception {

		Map<String, Object> params = new HashMap<>();
		params.put("code", dataset);
		Map<String, Object> selectedDataset = basicDbService.select(DATASET, params);
		if (selectedDataset == null) {
			logger.info("No dataset with id {} defined in EKILEX", dataset);
		} else {
			logger.info("Dataset {} : {}", dataset, selectedDataset.get("name"));
		}
		return selectedDataset != null;
	}

	private void createMeaningRelations(Map<Integer, Long> conceptMeaningIdMap, List<Map<String, Object>> conceptRelations) throws Exception {

		for (Map<String, Object> conceptRelation : conceptRelations) {
			Integer conceptId1 = (Integer) conceptRelation.get("source_concept_id");
			Integer conceptId2 = (Integer) conceptRelation.get("target_concept_id");
			int relationTypeNum = ((BigInteger) conceptRelation.get("relation_type")).intValue();
			Long meaningId1 = conceptMeaningIdMap.get(conceptId1);
			Long meaningId2 = conceptMeaningIdMap.get(conceptId2);

			if (meaningId1 != null && meaningId2 != null) {
				String relationType = relationTypeCodes.get(relationTypeNum);
				createMeaningRelation(meaningId1, meaningId2, relationType);
			}
		}
	}

	private class SourceData {
		Integer origId;
		Long id;
		String name;

		SourceData(Integer origId, Long id, String name) {
			this.origId = origId;
			this.id = id;
			this.name = name;
		}
	}

	private class Context {
		List<Map<String, Object>> terms;
		List<Map<String, Object>> definitions;
		Map<Integer, SourceData> sourceMapping;
		List<Map<String, Object>> comments;
		List<Map<String, Object>> abbreviations;
		List<Map<String, Object>> genuses;
		List<Map<String, Object>> families;
		List<Map<String, Object>> describers;
		List<Map<String, Object>> describingYears;
		List<Map<String, Object>> geolDomains;
		List<Map<String, Object>> examples;
		List<Map<String, Object>> images;
		List<Map<String, Object>> models;
	}
}
