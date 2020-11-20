package eki.ekilex.runner;

import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import eki.common.constant.Complexity;
import eki.common.constant.GlobalConstant;
import eki.common.constant.LifecycleEntity;
import eki.common.constant.LifecycleEventType;
import eki.common.constant.LifecycleLogOwner;
import eki.common.constant.LifecycleProperty;
import eki.common.constant.ReferenceType;
import eki.common.data.Count;
import eki.common.data.util.AbstractRowMapper;
import eki.common.exception.DataLoadingException;
import eki.ekilex.data.transform.Collocation;
import eki.ekilex.data.transform.Freeform;
import eki.ekilex.data.transform.FreeformSourceLink;
import eki.ekilex.data.transform.Lexeme;
import eki.ekilex.data.transform.LexemeClassifier;
import eki.ekilex.data.transform.LexemeCollocationGroupTuple;
import eki.ekilex.data.transform.LexemeCollocationTuple;
import eki.ekilex.data.transform.LexemeFrequency;
import eki.ekilex.data.transform.LexemeLifecycleLog;
import eki.ekilex.data.transform.LexemeRelation;
import eki.ekilex.data.transform.LexemeSourceLink;
import eki.ekilex.data.transform.LexemeTag;
import eki.ekilex.data.transform.WordMeaningPair;
import eki.ekilex.data.util.CollocationRowMapper;
import eki.ekilex.data.util.FreeformRowMapper;
import eki.ekilex.data.util.FreeformSourceLinkRowMapper;
import eki.ekilex.data.util.LexemeClassifierRowMapper;
import eki.ekilex.data.util.LexemeCollocationGroupTupleRowMapper;
import eki.ekilex.data.util.LexemeCollocationTupleRowMapper;
import eki.ekilex.data.util.LexemeFrequencyRowMapper;
import eki.ekilex.data.util.LexemeLifecycleLogRowMapper;
import eki.ekilex.data.util.LexemeRelationRowMapper;
import eki.ekilex.data.util.LexemeSourceLinkRowMapper;
import eki.ekilex.data.util.LexemeTagRowMapper;
import eki.ekilex.data.util.WordMeaningPairRowMapper;
import eki.ekilex.service.ReportComposer;

@Component
public class LexemeMergerRunner extends AbstractLoaderRunner implements GlobalConstant {

	private static Logger logger = LoggerFactory.getLogger(LexemeMergerRunner.class);

	private static final String REPORT_LEXEME_DATA_CONFLICT = "lexeme_data_conflict";

	private static final String DATASET_CODE_SS1 = "ss1";

	private static final String SQL_SELECT_WORD_LEXEME_MEANING_IDS_PATH = "sql/select_word_lexeme_meaning_ids.sql";

	private static final String SQL_SELECT_LEXEME_FREEFORMS_PATH = "sql/select_lexeme_freeforms.sql";

	private static final String SQL_SELECT_COLLOCATIONS_FOR_DATASETS_PATH = "sql/select_collocations_for_datasets.sql";

	private static final String SQL_SELECT_LEXEME_COLLOC_TUPLES_FOR_DATASETS_PATH = "sql/select_lexeme_colloc_tuples_for_datasets.sql";

	private static final String SQL_SELECT_LEXEME_COLLOC_GROUP_TUPLES_FOR_DATASETS_PATH = "sql/select_lexeme_colloc_group_tuples_for_datasets.sql";

	private static final String SQL_SELECT_LEXEME_LIFECYCLE_LOGS_FOR_DATASETS_PATH = "sql/select_lexeme_lifecycle_logs_for_datasets.sql";

	private static final String SQL_INSERT_DEFINITION_DATASET_WHERE_NOT_EXISTS_PATH = "sql/insert_definition_dataset_where_not_exists.sql";

	private String sqlSelectWordLexemeMeaningIds;

	private String sqlSelectLexemeFreeforms;

	private String sqlSelectCollocationsForDatasets;

	private String sqlSelectLexemeCollocTuplesForDatasets;

	private String sqlSelectLexemeCollocGroupTuplesForDatasets;

	private String sqlSelectLexemeLifecycleLogsForDatasets;

	private String sqlInsertDefinitionDatasetWhereNotExists;

	private String sqlSelectLexeme = "select * from " + LEXEME + " where id = :id";

	private String sqlSelectFreeformChildren = "select ff.*, false as children_exist, false as source_links_exist from " + FREEFORM + " ff where ff.parent_id = :freeformId order by ff.order_by";

	private String sqlSelectLexemeFrequencies = "select lf.* from " + LEXEME_FREQUENCY + " lf where lf.lexeme_id = :lexemeId";

	private String sqlSelectLexemeTags = "select lt.lexeme_id, lt.tag_name from " + LEXEME_TAG + " lt where lt.lexeme_id = :lexemeId";

	private String sqlSelectLexemeRegisters = "select c.lexeme_id, c.register_code as code from " + LEXEME_REGISTER + " c where c.lexeme_id = :lexemeId order by c.order_by";

	private String sqlSelectLexemePoses = "select c.lexeme_id, c.pos_code as code from " + LEXEME_POS + " c where c.lexeme_id = :lexemeId order by c.order_by";

	private String sqlSelectLexemeDerivs = "select c.lexeme_id, c.deriv_code as code from " + LEXEME_DERIV + " c where c.lexeme_id = :lexemeId order by c.id";

	private String sqlSelectLexemeRegions = "select c.lexeme_id, c.region_code as code from " + LEXEME_REGION + " c where c.lexeme_id = :lexemeId order by c.id";

	private String sqlSelectLexemeSourceLinks = "select lsl.* from " + LEXEME_SOURCE_LINK + " lsl where lsl.lexeme_id = :lexemeId order by lsl.order_by";

	private String sqlSelectFreeformSourceLinks = "select ffsl.* from " + FREEFORM_SOURCE_LINK + " ffsl where ffsl.freeform_id = :freeformId order by ffsl.order_by";

	private String sqlSelectLexemeRelations =
			"select r.lexeme1_id, r.lexeme2_id, r.lex_rel_type_code, r.order_by from " + LEXEME_RELATION + " r where r.lexeme1_id in (:lexemeIds) order by r.order_by";

	private ReportComposer reportComposer;

	private String lexemeMergeName;

	@Override
	public String getDataset() {
		return lexemeMergeName;
	}

	@Override
	public Complexity getLexemeComplexity() {
		return null;
	}

	@Override
	public Complexity getDefinitionComplexity() {
		return null;
	}

	@Override
	public Complexity getFreeformComplexity() {
		return null;
	}

	@Override
	public void deleteDatasetData() throws Exception {
		// deleteLexemeMergeData does it
	}

	@Transactional
	public void deleteLexemeMergeData(String lexemeMergeName) throws Exception {
		deleteDatasetData(lexemeMergeName);
	}

	@Override
	public void initialise() throws Exception {

		ClassLoader classLoader = this.getClass().getClassLoader();
		InputStream resourceFileInputStream;

		resourceFileInputStream = classLoader.getResourceAsStream(SQL_SELECT_WORD_LEXEME_MEANING_IDS_PATH);
		sqlSelectWordLexemeMeaningIds = getContent(resourceFileInputStream);

		resourceFileInputStream = classLoader.getResourceAsStream(SQL_SELECT_LEXEME_FREEFORMS_PATH);
		sqlSelectLexemeFreeforms = getContent(resourceFileInputStream);

		resourceFileInputStream = classLoader.getResourceAsStream(SQL_SELECT_COLLOCATIONS_FOR_DATASETS_PATH);
		sqlSelectCollocationsForDatasets = getContent(resourceFileInputStream);

		resourceFileInputStream = classLoader.getResourceAsStream(SQL_SELECT_LEXEME_COLLOC_TUPLES_FOR_DATASETS_PATH);
		sqlSelectLexemeCollocTuplesForDatasets = getContent(resourceFileInputStream);

		resourceFileInputStream = classLoader.getResourceAsStream(SQL_SELECT_LEXEME_COLLOC_GROUP_TUPLES_FOR_DATASETS_PATH);
		sqlSelectLexemeCollocGroupTuplesForDatasets = getContent(resourceFileInputStream);

		resourceFileInputStream = classLoader.getResourceAsStream(SQL_SELECT_LEXEME_LIFECYCLE_LOGS_FOR_DATASETS_PATH);
		sqlSelectLexemeLifecycleLogsForDatasets = getContent(resourceFileInputStream);

		resourceFileInputStream = classLoader.getResourceAsStream(SQL_INSERT_DEFINITION_DATASET_WHERE_NOT_EXISTS_PATH);
		sqlInsertDefinitionDatasetWhereNotExists = getContent(resourceFileInputStream);
	}

	@Transactional
	public void execute(String lexemeMergeName, List<String> lexemeMergeDatasets, boolean doReports) throws Exception {
		this.lexemeMergeName = lexemeMergeName;
		this.doReports = doReports;

		if (doReports) {
			reportComposer = new ReportComposer(getDataset() + " loader", REPORT_LEXEME_DATA_CONFLICT);
		}

		start();

		logger.debug("Merging {} lexemes into {}", lexemeMergeDatasets, lexemeMergeName);

		Map<String, Count> countsMap = createCountsMap();

		Count wordMeaningPairCount = countsMap.get("wordMeaningPairCount");
		Count summableLexemeCount = countsMap.get("summableLexemeCount");
		Count sumLexemeCount = countsMap.get("sumLexemeCount");
		Count summableLexemeRelationCount = countsMap.get("summableLexemeRelationCount");

		createDatasetIfNotExists(lexemeMergeName);

		List<WordMeaningPair> wordMeaningPairs = getWordMeaningPairs(lexemeMergeDatasets);
		int wordMeaningPairCountValue = wordMeaningPairs.size();
		wordMeaningPairCount.increment(wordMeaningPairCountValue);

		Map<Long, Long> sumLexemeIdMap = new HashMap<>();
		Map<Long, Integer> nonSsWordLexemeLevelMap = new HashMap<>();

		long wordMeaningPairCounter = 0;
		long progressIndicator = wordMeaningPairCountValue / Math.min(wordMeaningPairCountValue, 100);

		for (WordMeaningPair wordMeaningPair : wordMeaningPairs) {

			List<Long> lexemeIds = wordMeaningPair.getLexemeIds();
			List<LexemeExt> allLexemes = new ArrayList<>();
			for (Long lexemeId : lexemeIds) {
				LexemeExt lexeme = getFullLexeme(lexemeId, countsMap);
				allLexemes.add(lexeme);
			}

			LexemeExt sumLexeme = composeSumLexeme(wordMeaningPair, allLexemes, nonSsWordLexemeLevelMap, countsMap);
			sumLexeme.setDatasetCode(lexemeMergeName);

			Long sumLexemeId = createLexeme(sumLexeme);
			sumLexemeCount.increment();
			for (Long lexemeId : lexemeIds) {
				sumLexemeIdMap.put(lexemeId, sumLexemeId);
				summableLexemeCount.increment();
			}

			String word = wordMeaningPair.getWord();
			createLexemeFreeformsAndSourceLinks(sumLexemeId, allLexemes, countsMap);
			createLexemeFrequencies(sumLexemeId, allLexemes);
			createLexemeRegisters(word, sumLexemeId, allLexemes);
			createLexemePoses(word, sumLexemeId, allLexemes);
			createLexemeDerivs(word, sumLexemeId, allLexemes);
			createLexemeRegions(word, sumLexemeId, allLexemes);
			createLexemeSourceLinks(sumLexemeId, allLexemes, countsMap);
			createLexemeTags(sumLexemeId, allLexemes, countsMap);

			// progress
			wordMeaningPairCounter++;
			if (wordMeaningPairCounter % progressIndicator == 0) {
				long progressPercent = wordMeaningPairCounter / progressIndicator;
				logger.debug("{}% - {} word meaning pairs iterated", progressPercent, wordMeaningPairCounter);
			}
		}

		// post handling lexeme relations
		List<LexemeRelation> lexemeRelations = getLexemeRelations(sumLexemeIdMap.keySet());
		summableLexemeRelationCount.increment(lexemeRelations.size());
		createLexemeRelations(sumLexemeIdMap, lexemeRelations, countsMap);

		// post handling collocations
		List<Collocation> collocations = getCollocations(lexemeMergeDatasets);
		List<LexemeCollocationTuple> lexemeCollocationTuples = getLexemeCollocationTuples(lexemeMergeDatasets);
		List<LexemeCollocationGroupTuple> lexemeCollocationGroupTuples = getLexemeCollocationGroupTuples(lexemeMergeDatasets);
		createLexemeCollocations(sumLexemeIdMap, collocations, lexemeCollocationTuples, lexemeCollocationGroupTuples, countsMap);

		// post handling lexeme lifecycle logs
		List<LexemeLifecycleLog> lexemeLifecycleLogs = getLexemeLifecycleLogs(lexemeMergeDatasets);
		createLexemeLifecycleLogs(sumLexemeIdMap, lexemeLifecycleLogs, countsMap);

		// post handling definition datasets
		appendMergeDatasetToDefinitions(lexemeMergeName, countsMap);

		logCounts(countsMap);

		if (reportComposer != null) {
			reportComposer.end();
		}

		end();
	}

	private Map<String, Count> createCountsMap() {

		Count wordMeaningPairCount = new Count();
		Count tooManyFreqGroupCodeCount = new Count();
		Count tooManyValueStateCodeCount = new Count();
		Count summableLexemeCount = new Count();
		Count sumLexemeCount = new Count();
		Count summableLexemeFreeformCount = new Count();
		Count sumLexemeFreeformCount = new Count();
		Count summableLexemeFreeformSourceLinkCount = new Count();
		Count sumLexemeFreeformSourceLinkCount = new Count();
		Count summableLexemeSourceLinkCount = new Count();
		Count sumLexemeSourceLinkCount = new Count();
		Count summableLexemeRelationCount = new Count();
		Count sumLexemeRelationCount = new Count();
		Count sumCollocationCount = new Count();
		Count sumLexemeCollocCount = new Count();
		Count sumLexemeCollocPosGroupCount = new Count();
		Count sumLexemeCollocRelGroupCount = new Count();
		Count sumLexemeTagCount = new Count();
		Count sumLexemeLifecycleLogCount = new Count();
		Count sumDefinitionCount = new Count();

		Map<String, Count> countsMap = new HashMap<>();
		countsMap.put("wordMeaningPairCount", wordMeaningPairCount);
		countsMap.put("tooManyFreqGroupCodeCount", tooManyFreqGroupCodeCount);
		countsMap.put("tooManyValueStateCodeCount", tooManyValueStateCodeCount);
		countsMap.put("summableLexemeCount", summableLexemeCount);
		countsMap.put("sumLexemeCount", sumLexemeCount);
		countsMap.put("summableLexemeFreeformCount", summableLexemeFreeformCount);
		countsMap.put("sumLexemeFreeformCount", sumLexemeFreeformCount);
		countsMap.put("summableLexemeFreeformSourceLinkCount", summableLexemeFreeformSourceLinkCount);
		countsMap.put("sumLexemeFreeformSourceLinkCount", sumLexemeFreeformSourceLinkCount);
		countsMap.put("summableLexemeSourceLinkCount", summableLexemeSourceLinkCount);
		countsMap.put("sumLexemeSourceLinkCount", sumLexemeSourceLinkCount);
		countsMap.put("summableLexemeRelationCount", summableLexemeRelationCount);
		countsMap.put("sumLexemeRelationCount", sumLexemeRelationCount);
		countsMap.put("sumCollocationCount", sumCollocationCount);
		countsMap.put("sumLexemeCollocCount", sumLexemeCollocCount);
		countsMap.put("sumLexemeCollocPosGroupCount", sumLexemeCollocPosGroupCount);
		countsMap.put("sumLexemeCollocRelGroupCount", sumLexemeCollocRelGroupCount);
		countsMap.put("sumLexemeTagCount", sumLexemeTagCount);
		countsMap.put("sumLexemeLifecycleLogCount", sumLexemeLifecycleLogCount);
		countsMap.put("sumDefinitionCount", sumDefinitionCount);

		return countsMap;
	}

	private void logCounts(Map<String, Count> countsMap) {

		Count wordMeaningPairCount = countsMap.get("wordMeaningPairCount");
		Count tooManyFreqGroupCodeCount = countsMap.get("tooManyFreqGroupCodeCount");
		Count tooManyValueStateCodeCount = countsMap.get("tooManyValueStateCodeCount");
		Count summableLexemeCount = countsMap.get("summableLexemeCount");
		Count sumLexemeCount = countsMap.get("sumLexemeCount");
		Count summableLexemeFreeformCount = countsMap.get("summableLexemeFreeformCount");
		Count sumLexemeFreeformCount = countsMap.get("sumLexemeFreeformCount");
		Count summableLexemeFreeformSourceLinkCount = countsMap.get("summableLexemeFreeformSourceLinkCount");
		Count sumLexemeFreeformSourceLinkCount = countsMap.get("sumLexemeFreeformSourceLinkCount");
		Count summableLexemeSourceLinkCount = countsMap.get("summableLexemeSourceLinkCount");
		Count sumLexemeSourceLinkCount = countsMap.get("sumLexemeSourceLinkCount");
		Count summableLexemeRelationCount = countsMap.get("summableLexemeRelationCount");
		Count sumLexemeRelationCount = countsMap.get("sumLexemeRelationCount");
		Count sumCollocationCount = countsMap.get("sumCollocationCount");
		Count sumLexemeCollocCount = countsMap.get("sumLexemeCollocCount");
		Count sumLexemeCollocPosGroupCount = countsMap.get("sumLexemeCollocPosGroupCount");
		Count sumLexemeCollocRelGroupCount = countsMap.get("sumLexemeCollocRelGroupCount");
		Count sumLexemeTagCount = countsMap.get("sumLexemeTagCount");
		Count sumLexemeLifecycleLogCount = countsMap.get("sumLexemeLifecycleLogCount");
		Count sumDefinitionCount = countsMap.get("sumDefinitionCount");

		logger.info("Collected {} word meaning pairs", wordMeaningPairCount.getValue());
		logger.info("Collected {} lexemes to sum", summableLexemeCount.getValue());
		logger.info("Created {} sum lexemes", sumLexemeCount.getValue());
		logger.info("Found {} competing freq group lexemes", tooManyFreqGroupCodeCount.getValue());
		logger.info("Found {} competing value state lexemes", tooManyValueStateCodeCount.getValue());
		logger.info("Collected {} lexeme freeforms", summableLexemeFreeformCount.getValue());
		logger.info("Created {} lexeme freeforms", sumLexemeFreeformCount.getValue());
		logger.info("Collected {} lexeme freeform source links", summableLexemeFreeformSourceLinkCount.getValue());
		logger.info("Created {} lexeme freeform source links", sumLexemeFreeformSourceLinkCount.getValue());
		logger.info("Collected {} lexeme source links", summableLexemeSourceLinkCount.getValue());
		logger.info("Created {} lexeme source links", sumLexemeSourceLinkCount.getValue());
		logger.info("Found {} lexeme relations", summableLexemeRelationCount.getValue());
		logger.info("Created {} lexeme relations", sumLexemeRelationCount.getValue());
		logger.info("Created {} collocations", sumCollocationCount.getValue());
		logger.info("Created {} lexeme colloc bindings", sumLexemeCollocCount.getValue());
		logger.info("Created {} lexeme colloc pos groups", sumLexemeCollocPosGroupCount.getValue());
		logger.info("Created {} lexeme colloc rel groups", sumLexemeCollocRelGroupCount.getValue());
		logger.info("Created {} lexeme tags", sumLexemeTagCount.getValue());
		logger.info("Created {} lexeme lifecycle logs", sumLexemeLifecycleLogCount.getValue());
		logger.info("Associated {} definitions", sumDefinitionCount.getValue());
	}

	private LexemeExt composeSumLexeme(WordMeaningPair wordMeaningPair, List<LexemeExt> allLexemes,
			Map<Long, Integer> nonSsWordLexemeLevelMap, Map<String, Count> countsMap) throws Exception {

		Count tooManyFreqGroupCodeCount = countsMap.get("tooManyFreqGroupCodeCount");
		Count tooManyValueStateCodeCount = countsMap.get("tooManyValueStateCodeCount");

		String word = wordMeaningPair.getWord();
		Long wordId = wordMeaningPair.getWordId();
		Long meaningId = wordMeaningPair.getMeaningId();
		boolean mainDatasetLexemeExists = wordMeaningPair.isMainDatasetLexemeExists();
		Integer mainDatasetLexemeMaxLevel1 = wordMeaningPair.getMainDatasetLexemeMaxLevel1();

		if (!nonSsWordLexemeLevelMap.containsKey(wordId)) {
			if (mainDatasetLexemeExists) {
				nonSsWordLexemeLevelMap.put(wordId, mainDatasetLexemeMaxLevel1);
			} else {
				nonSsWordLexemeLevelMap.put(wordId, 0);
			}
		}

		// sum levels
		Lexeme ssLexeme = allLexemes.stream().filter(lexeme -> StringUtils.equals(DATASET_CODE_SS1, lexeme.getDatasetCode())).findFirst().orElse(null);

		Integer sumLevel1 = null;
		Integer sumLevel2 = null;

		if (ssLexeme == null) {
			sumLevel1 = nonSsWordLexemeLevelMap.get(wordId);
			sumLevel1 = sumLevel1 + 1;
			sumLevel2 = 0;
			nonSsWordLexemeLevelMap.put(wordId, sumLevel1);
		} else {
			sumLevel1 = ssLexeme.getLevel1();
			sumLevel2 = ssLexeme.getLevel2();
		}

		// sum freq group
		List<String> frequencyGroupCodeCandidates = allLexemes.stream()
				.filter(lexeme -> StringUtils.isNotBlank(lexeme.getFrequencyGroupCode()))
				.map(Lexeme::getFrequencyGroupCode)
				.distinct()
				.collect(Collectors.toList());
		String sumFrequencyGroupCode;
		if (CollectionUtils.isEmpty(frequencyGroupCodeCandidates)) {
			sumFrequencyGroupCode = null;
		} else if (frequencyGroupCodeCandidates.size() > 1) {
			//logger.debug("More than one freq group codes: {}", frequencyGroupCodeCandidates);
			tooManyFreqGroupCodeCount.increment();
			sumFrequencyGroupCode = frequencyGroupCodeCandidates.get(0);
		} else {
			sumFrequencyGroupCode = frequencyGroupCodeCandidates.get(0);
		}

		// sum corpus freq
		Float sumCorpusFrequency = allLexemes.stream()
				.filter(lexeme -> lexeme.getCorpusFrequency() != null)
				.map(Lexeme::getCorpusFrequency)
				.findFirst().orElse(null);

		// sum value state
		List<String> valueStateCodeCandidates = allLexemes.stream()
				.filter(lexeme -> StringUtils.isNotBlank(lexeme.getValueStateCode()))
				.map(Lexeme::getValueStateCode)
				.distinct()
				.collect(Collectors.toList());
		String sumValueStateCode;
		if (CollectionUtils.isEmpty(valueStateCodeCandidates)) {
			sumValueStateCode = null;
		} else if (valueStateCodeCandidates.size() > 1) {
			appendToReport(REPORT_LEXEME_DATA_CONFLICT, word, "ilmikutel kokku rohkem kui üks väärtusolek", valueStateCodeCandidates);
			tooManyValueStateCodeCount.increment();
			sumValueStateCode = valueStateCodeCandidates.get(0);
		} else {
			sumValueStateCode = valueStateCodeCandidates.get(0);
		}

		// sum process state
		List<Boolean> publicityCandidates = allLexemes.stream()
				.filter(lexeme -> lexeme.getIsPublic() != null)
				.map(Lexeme::getIsPublic)
				.distinct()
				.collect(Collectors.toList());
		boolean sumPublicity;
		if (publicityCandidates.size() > 1) {
			appendToReport(REPORT_LEXEME_DATA_CONFLICT, word, "ilmikutel erinevad avalikkused", publicityCandidates);
			sumPublicity = publicityCandidates.get(0);
		} else {
			sumPublicity = publicityCandidates.get(0);
		}

		// sum complexity
		boolean isSimpleComplexity = allLexemes.stream().anyMatch(lexeme -> Objects.equals(Complexity.SIMPLE, lexeme.getComplexity()));
		Complexity complexity;
		if (isSimpleComplexity) {
			complexity = Complexity.SIMPLE;
		} else {
			complexity = Complexity.DETAIL;
		}

		LexemeExt sumLexeme = new LexemeExt();
		sumLexeme.setWordId(wordId);
		sumLexeme.setMeaningId(meaningId);
		sumLexeme.setFrequencyGroupCode(sumFrequencyGroupCode);
		sumLexeme.setCorpusFrequency(sumCorpusFrequency);
		sumLexeme.setLevel1(sumLevel1);
		sumLexeme.setLevel2(sumLevel2);
		sumLexeme.setValueStateCode(sumValueStateCode);
		sumLexeme.setIsPublic(sumPublicity);
		sumLexeme.setComplexity(complexity);

		return sumLexeme;
	}

	private void createDatasetIfNotExists(String datasetCode) throws Exception {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("code", datasetCode);
		Map<String, Object> dataset = basicDbService.select(DATASET, paramMap);
		if (dataset == null) {
			paramMap.put("name", datasetCode);
			paramMap.put("type", DATASET_TYPE_TERM);
			basicDbService.createWithoutId(DATASET, paramMap);
		}
	}

	private List<WordMeaningPair> getWordMeaningPairs(List<String> datasetCodes) throws Exception {

		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("datasetCodes", datasetCodes);
		paramMap.put("mainDatasetCode", DATASET_CODE_SS1);
		List<WordMeaningPair> wordMeaningPairs = basicDbService.getResults(sqlSelectWordLexemeMeaningIds, paramMap, new WordMeaningPairRowMapper());
		return wordMeaningPairs;
	}

	private LexemeExt getFullLexeme(Long lexemeId, Map<String, Count> countsMap) throws Exception {

		Count summableLexemeFreeformCount = countsMap.get("summableLexemeFreeformCount");
		Count summableLexemeFreeformSourceLinkCount = countsMap.get("summableLexemeFreeformSourceLinkCount");
		Count summableLexemeSourceLinkCount = countsMap.get("summableLexemeSourceLinkCount");

		Map<String, Object> paramMap;
		FreeformRowMapper freeformRowMapper = new FreeformRowMapper();
		FreeformSourceLinkRowMapper freeformSourceLinkRowMapper = new FreeformSourceLinkRowMapper();

		paramMap = new HashMap<>();
		paramMap.put("id", lexemeId);
		LexemeExt lexeme = basicDbService.getSingleResult(sqlSelectLexeme, paramMap, new LexemeExtRowMapper());

		paramMap = new HashMap<>();
		paramMap.put("lexemeId", lexemeId);
		List<Freeform> freeforms = basicDbService.getResults(sqlSelectLexemeFreeforms, paramMap, freeformRowMapper);
		lexeme.setFreeforms(freeforms);
		summableLexemeFreeformCount.increment(freeforms.size());

		List<Freeform> children;
		List<FreeformSourceLink> sourceLinks;

		for (Freeform freeform : freeforms) {
			if (freeform.isChildrenExist()) {
				paramMap = new HashMap<>();
				paramMap.put("freeformId", freeform.getFreeformId());
				children = basicDbService.getResults(sqlSelectFreeformChildren, paramMap, freeformRowMapper);
				freeform.setChildren(children);
				summableLexemeFreeformCount.increment(children.size());
			}
			if (freeform.isSourceLinksExist()) {
				paramMap = new HashMap<>();
				paramMap.put("freeformId", freeform.getFreeformId());
				sourceLinks = basicDbService.getResults(sqlSelectFreeformSourceLinks, paramMap, freeformSourceLinkRowMapper);
				freeform.setSourceLinks(sourceLinks);
				summableLexemeFreeformSourceLinkCount.increment(sourceLinks.size());
			}
		}

		paramMap = new HashMap<>();
		paramMap.put("lexemeId", lexemeId);
		List<LexemeSourceLink> lexemeSourceLinks = basicDbService.getResults(sqlSelectLexemeSourceLinks, paramMap, new LexemeSourceLinkRowMapper());
		lexeme.setLexemeSourceLinks(lexemeSourceLinks);
		summableLexemeSourceLinkCount.increment(lexemeSourceLinks.size());

		paramMap = new HashMap<>();
		paramMap.put("lexemeId", lexemeId);
		List<LexemeFrequency> lexemeFrequencies = basicDbService.getResults(sqlSelectLexemeFrequencies, paramMap, new LexemeFrequencyRowMapper());
		lexeme.setLexemeFrequencies(lexemeFrequencies);

		paramMap = new HashMap<>();
		paramMap.put("lexemeId", lexemeId);
		List<LexemeTag> lexemeTags = basicDbService.getResults(sqlSelectLexemeTags, paramMap, new LexemeTagRowMapper());
		lexeme.setLexemeTags(lexemeTags);

		paramMap = new HashMap<>();
		paramMap.put("lexemeId", lexemeId);
		List<LexemeClassifier> lexemeRegisters = basicDbService.getResults(sqlSelectLexemeRegisters, paramMap, new LexemeClassifierRowMapper());
		lexeme.setLexemeRegisters(lexemeRegisters);

		paramMap = new HashMap<>();
		paramMap.put("lexemeId", lexemeId);
		List<LexemeClassifier> lexemePoses = basicDbService.getResults(sqlSelectLexemePoses, paramMap, new LexemeClassifierRowMapper());
		lexeme.setLexemePoses(lexemePoses);

		paramMap = new HashMap<>();
		paramMap.put("lexemeId", lexemeId);
		List<LexemeClassifier> lexemeDerivs = basicDbService.getResults(sqlSelectLexemeDerivs, paramMap, new LexemeClassifierRowMapper());
		lexeme.setLexemeDerivs(lexemeDerivs);

		paramMap = new HashMap<>();
		paramMap.put("lexemeId", lexemeId);
		List<LexemeClassifier> lexemeRegions = basicDbService.getResults(sqlSelectLexemeRegions, paramMap, new LexemeClassifierRowMapper());
		lexeme.setLexemeRegions(lexemeRegions);

		return lexeme;
	}

	private List<LexemeRelation> getLexemeRelations(Set<Long> lexemeIds) throws Exception {

		List<Long> allLexemeIdList = new ArrayList<>(lexemeIds);
		List<LexemeRelation> lexemeRelations = new ArrayList<>();

		Map<String, Object> paramMap;

		int lexemeIdCount = lexemeIds.size();
		final int lexemeSublistLength = 100;
		int lexemeIdSublistFromIndex = 0;
		int lexemeIdSublistToIndex;
		List<Long> lexemeIdSublist;
		List<LexemeRelation> lexemeRelationSublist;

		while (lexemeIdSublistFromIndex < lexemeIdCount) {
			lexemeIdSublistToIndex = lexemeIdSublistFromIndex + lexemeSublistLength;
			lexemeIdSublistToIndex = Math.min(lexemeIdSublistToIndex, lexemeIdCount);
			lexemeIdSublist = allLexemeIdList.subList(lexemeIdSublistFromIndex, lexemeIdSublistToIndex);
			paramMap = new HashMap<>();
			paramMap.put("lexemeIds", lexemeIdSublist);
			lexemeRelationSublist = basicDbService.getResults(sqlSelectLexemeRelations, paramMap, new LexemeRelationRowMapper());
			if (CollectionUtils.isNotEmpty(lexemeRelationSublist)) {
				lexemeRelations.addAll(lexemeRelationSublist);
			}
			lexemeIdSublistFromIndex = lexemeIdSublistToIndex;
		}
		lexemeRelations = lexemeRelations.stream().sorted(Comparator.comparing(LexemeRelation::getOrderBy)).collect(Collectors.toList());
		return lexemeRelations;
	}

	private List<Collocation> getCollocations(List<String> lexemeMergeDatasets) throws Exception {

		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("datasetCodes", lexemeMergeDatasets);
		List<Collocation> collocations = basicDbService.getResults(sqlSelectCollocationsForDatasets, paramMap, new CollocationRowMapper());
		return collocations;
	}

	private List<LexemeCollocationTuple> getLexemeCollocationTuples(List<String> lexemeMergeDatasets) throws Exception {

		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("datasetCodes", lexemeMergeDatasets);
		List<LexemeCollocationTuple> lexemeCollocationTuples = basicDbService.getResults(sqlSelectLexemeCollocTuplesForDatasets, paramMap, new LexemeCollocationTupleRowMapper());
		return lexemeCollocationTuples;
	}

	private List<LexemeCollocationGroupTuple> getLexemeCollocationGroupTuples(List<String> lexemeMergeDatasets) throws Exception {

		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("datasetCodes", lexemeMergeDatasets);
		List<LexemeCollocationGroupTuple> lexemeCollocationGroupTuples = basicDbService.getResults(sqlSelectLexemeCollocGroupTuplesForDatasets, paramMap, new LexemeCollocationGroupTupleRowMapper());
		return lexemeCollocationGroupTuples;
	}

	private List<LexemeLifecycleLog> getLexemeLifecycleLogs(List<String> lexemeMergeDatasets) throws Exception {

		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("datasetCodes", lexemeMergeDatasets);
		List<LexemeLifecycleLog> lexemeLifecycleLogs = basicDbService.getResults(sqlSelectLexemeLifecycleLogsForDatasets, paramMap, new LexemeLifecycleLogRowMapper());
		return lexemeLifecycleLogs;
	}

	private void createLexemeFreeformsAndSourceLinks(Long sumLexemeId, List<LexemeExt> allLexemes, Map<String, Count> countsMap) throws Exception {

		Count sumLexemeFreeformCount = countsMap.get("sumLexemeFreeformCount");
		Count sumLexemeFreeformSourceLinkCount = countsMap.get("sumLexemeFreeformSourceLinkCount");

		List<Freeform> sumFreeforms = new ArrayList<>();
		List<String> allFfVals = new ArrayList<>();
		for (LexemeExt lexeme : allLexemes) {
			List<Freeform> srcFreeforms = lexeme.getFreeforms();
			if (CollectionUtils.isEmpty(srcFreeforms)) {
				continue;
			}
			for (Freeform srcFreeform : srcFreeforms) {
				allFfVals.add(srcFreeform.getValueText() + " (" + srcFreeform.getType() + ")");
			}
			if (CollectionUtils.isEmpty(sumFreeforms)) {
				sumFreeforms.addAll(srcFreeforms);
			} else {
				for (Freeform srcFreeform : srcFreeforms) {
					boolean freeformExists = sumFreeforms.stream()
							.filter(sumFreeform -> Objects.equals(sumFreeform.getType(), srcFreeform.getType()))
							.anyMatch(sumFreeform -> {
								if (srcFreeform.getValueText() != null) {
									if (StringUtils.equals(sumFreeform.getValueText(), srcFreeform.getValueText())) {
										return true;
									}
								} else if (srcFreeform.getValueDate() != null) {
									if (Objects.equals(sumFreeform.getValueDate(), srcFreeform.getValueDate())) {
										return true;
									}									
								}
								return false;
							});
					if (!freeformExists) {
						sumFreeforms.add(srcFreeform);
					}
				}
			}
		}
		sumLexemeFreeformCount.increment(sumFreeforms.size());

		for (Freeform freeform : sumFreeforms) {
			Long freeformId = null;
			if (freeform.getValueText() != null) {
				freeformId = createLexemeFreeform(sumLexemeId, freeform.getType(), freeform.getValueText(), freeform.getValuePrese(), freeform.getLangCode(), freeform.getComplexity());
			} else if (freeform.getValueDate() != null) {
				throw new DataLoadingException("Unexpected lexeme freeform data type: " + freeform.getValueDate());
			} else {
				// other data types?
			}
			if (freeform.isChildrenExist()) {
				List<Freeform> children = freeform.getChildren();
				createNestedFreeforms(freeformId, children);
				sumLexemeFreeformCount.increment(children.size());
			}
			if (freeform.isSourceLinksExist()) {
				List<FreeformSourceLink> sourceLinks = freeform.getSourceLinks();
				sumLexemeFreeformSourceLinkCount.increment(sourceLinks.size());
				for (FreeformSourceLink sourceLink : sourceLinks) {
					createFreeformSourceLink(freeformId, sourceLink.getType(), sourceLink.getSourceId(), sourceLink.getName(), sourceLink.getValue());
				}
			}
		}
	}

	private void createNestedFreeforms(Long parentId, List<Freeform> freeforms) throws Exception {

		for (Freeform freeform : freeforms) {
			if (freeform.getValueText() != null) {
				createFreeformText(parentId, freeform.getType(), freeform.getValueText(), freeform.getValuePrese(), freeform.getLangCode(), freeform.getComplexity(), null);
			} else if (freeform.getValueDate() != null) {
				createFreeformDate(parentId, freeform.getType(), freeform.getValueDate(), freeform.getComplexity());
			}
		}
	}

	private void createLexemeFrequencies(Long sumLexemeId, List<LexemeExt> allLexemes) throws Exception {

		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("lexeme_id", sumLexemeId);
		for (LexemeExt lexeme : allLexemes) {
			List<LexemeFrequency> lexemeFrequencies = lexeme.getLexemeFrequencies();
			if (CollectionUtils.isNotEmpty(lexemeFrequencies)) {
				for (LexemeFrequency lexemeFrequency : lexemeFrequencies) {
					paramMap.put("source_name", lexemeFrequency.getSourceName());
					paramMap.put("created_on", lexemeFrequency.getCreatedOn());
					paramMap.put("rank", lexemeFrequency.getRank());
					paramMap.put("value", lexemeFrequency.getValue());
					basicDbService.createIfNotExists(LEXEME_FREQUENCY, paramMap);
				}
				// first available set is created
				return;
			}
		}
	}

	private void createLexemeRegisters(String word, Long sumLexemeId, List<LexemeExt> allLexemes) throws Exception {

		List<List<String>> allLexemeClassifierCodes = new ArrayList<>();

		for (LexemeExt lexeme : allLexemes) {
			List<LexemeClassifier> lexemeClassifiers = lexeme.getLexemeRegisters();
			if (CollectionUtils.isNotEmpty(lexemeClassifiers)) {
				List<String> lexemeClassifierCodes = lexemeClassifiers.stream().map(LexemeClassifier::getCode).collect(Collectors.toList());
				allLexemeClassifierCodes.add(lexemeClassifierCodes);
				for (LexemeClassifier lexemeClassifier : lexemeClassifiers) {
					createLexemeRegister(sumLexemeId, lexemeClassifier.getCode());
				}
			}
		}

		detectAndLogConflictingData(word, allLexemeClassifierCodes, "register");
	}

	private void createLexemePoses(String word, Long sumLexemeId, List<LexemeExt> allLexemes) throws Exception {

		List<List<String>> allLexemeClassifierCodes = new ArrayList<>();

		for (LexemeExt lexeme : allLexemes) {
			List<LexemeClassifier> lexemeClassifiers = lexeme.getLexemePoses();
			if (CollectionUtils.isNotEmpty(lexemeClassifiers)) {
				List<String> lexemeClassifierCodes = lexemeClassifiers.stream().map(LexemeClassifier::getCode).collect(Collectors.toList());
				allLexemeClassifierCodes.add(lexemeClassifierCodes);
				for (LexemeClassifier lexemeClassifier : lexemeClassifiers) {
					createLexemePos(sumLexemeId, lexemeClassifier.getCode());
				}
			}
		}

		detectAndLogConflictingData(word, allLexemeClassifierCodes, "pos");
	}

	private void createLexemeDerivs(String word, Long sumLexemeId, List<LexemeExt> allLexemes) throws Exception {

		List<List<String>> allLexemeClassifierCodes = new ArrayList<>();

		for (LexemeExt lexeme : allLexemes) {
			List<LexemeClassifier> lexemeClassifiers = lexeme.getLexemeDerivs();
			if (CollectionUtils.isNotEmpty(lexemeClassifiers)) {
				List<String> lexemeClassifierCodes = lexemeClassifiers.stream().map(LexemeClassifier::getCode).collect(Collectors.toList());
				allLexemeClassifierCodes.add(lexemeClassifierCodes);
				for (LexemeClassifier lexemeClassifier : lexemeClassifiers) {
					createLexemeDeriv(sumLexemeId, lexemeClassifier.getCode());
				}
			}
		}

		detectAndLogConflictingData(word, allLexemeClassifierCodes, "pos");
	}

	private void createLexemeRegions(String word, Long sumLexemeId, List<LexemeExt> allLexemes) throws Exception {

		List<List<String>> allLexemeClassifierCodes = new ArrayList<>();

		for (LexemeExt lexeme : allLexemes) {
			List<LexemeClassifier> lexemeClassifiers = lexeme.getLexemeRegions();
			if (CollectionUtils.isNotEmpty(lexemeClassifiers)) {
				List<String> lexemeClassifierCodes = lexemeClassifiers.stream().map(LexemeClassifier::getCode).collect(Collectors.toList());
				allLexemeClassifierCodes.add(lexemeClassifierCodes);
				for (LexemeClassifier lexemeClassifier : lexemeClassifiers) {
					createLexemeRegion(sumLexemeId, lexemeClassifier.getCode());
				}
			}
		}

		detectAndLogConflictingData(word, allLexemeClassifierCodes, "region");
	}

	private void createLexemeTags(Long sumLexemeId, List<LexemeExt> allLexemes, Map<String, Count> countsMap) throws Exception {

		Count sumLexemeTagCount = countsMap.get("sumLexemeTagCount");
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("lexeme_id", sumLexemeId);

		for (LexemeExt lexeme : allLexemes) {
			List<LexemeTag> lexemeTags = lexeme.getLexemeTags();
			if (CollectionUtils.isNotEmpty(lexemeTags)) {
				for (LexemeTag lexemeTag : lexemeTags) {
					paramMap.put("tag_name", lexemeTag.getTagName());
					Long lexemeTagId = basicDbService.createIfNotExists(LEXEME_TAG, paramMap);
					if (lexemeTagId != null) {
						createLifecycleLog(LifecycleLogOwner.LEXEME, sumLexemeId, sumLexemeId, LifecycleEntity.LEXEME, LifecycleProperty.TAG, LifecycleEventType.CREATE, lexemeTag.getTagName());
						sumLexemeTagCount.increment();
					}
				}
			}
		}
	}

	private void detectAndLogConflictingData(String word, List<List<String>> allLexemeClassifierCodes, String dataName) throws Exception {

		boolean disconnectionExists = disconnectionExists(allLexemeClassifierCodes);
		if (disconnectionExists) {
			List<String> sumClassifierCodes = getSumClassifierCodes(allLexemeClassifierCodes);
			appendToReport(REPORT_LEXEME_DATA_CONFLICT, word, "ilmikutel mittekattuvad klassifikaatorid: " + dataName, sumClassifierCodes);
		}
	}

	private boolean disconnectionExists(List<List<String>> allClassifierCodes) {

		if (allClassifierCodes.size() > 1) {
			for (List<String> classifierCodes1 : allClassifierCodes) {
				for (List<String> classifierCodes2 : allClassifierCodes) {
					Collection<String> intersection = CollectionUtils.intersection(classifierCodes1, classifierCodes2);
					if (CollectionUtils.isEmpty(intersection)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private List<String> getSumClassifierCodes(List<List<String>> allClassifierCodes) {

		List<String> sumClassifierCodes = new ArrayList<>();
		for (List<String> classifierCodes : allClassifierCodes) {
			Collection<String> disjunction = CollectionUtils.disjunction(sumClassifierCodes, classifierCodes);
			sumClassifierCodes.addAll(disjunction);
		}
		sumClassifierCodes = sumClassifierCodes.stream().distinct().collect(Collectors.toList());
		return sumClassifierCodes;
	}

	private void createLexemeSourceLinks(Long sumLexemeId, List<LexemeExt> allLexemes, Map<String, Count> countsMap) throws Exception {

		Count sumLexemeSourceLinkCount = countsMap.get("sumLexemeSourceLinkCount");

		for (LexemeExt lexeme : allLexemes) {
			List<LexemeSourceLink> sourceLinks = lexeme.getLexemeSourceLinks();
			if (CollectionUtils.isNotEmpty(sourceLinks)) {
				for (LexemeSourceLink sourceLink : sourceLinks) {
					Long sourceLinkId = createLexemeSourceLinkIfNotExists(sumLexemeId, sourceLink);
					if (sourceLinkId != null) {
						sumLexemeSourceLinkCount.increment();
					}
				}
			}
		}
	}

	private Long createLexemeSourceLinkIfNotExists(Long sumLexemeId, LexemeSourceLink sourceLink) throws Exception {

		Long sourceId = sourceLink.getSourceId();
		ReferenceType type = sourceLink.getType();
		String name = sourceLink.getName();
		String value = sourceLink.getValue();

		Map<String, Object> tableRowParamMap = new HashMap<>();
		tableRowParamMap.put("lexeme_id", sumLexemeId);
		tableRowParamMap.put("source_id", sourceId);
		tableRowParamMap.put("type", type.name());
		if (StringUtils.isNotBlank(name)) {
			tableRowParamMap.put("name", name);
		}
		if (StringUtils.isNotBlank(value)) {
			tableRowParamMap.put("value", value);
		}
		Long sourceLinkId = basicDbService.createIfNotExists(LEXEME_SOURCE_LINK, tableRowParamMap);

		if (sourceLinkId != null) {
			createLifecycleLog(LifecycleLogOwner.LEXEME, sumLexemeId, sourceLinkId, LifecycleEntity.LEXEME, LifecycleProperty.SOURCE_LINK, LifecycleEventType.CREATE, value);
		}

		return sourceLinkId;
	}

	private void createLexemeRelations(Map<Long, Long> sumLexemeIdMap, List<LexemeRelation> lexemeRelations, Map<String, Count> countsMap) throws Exception {

		logger.debug("Creating lexeme relations");

		Count sumLexemeRelationCount = countsMap.get("sumLexemeRelationCount");

		for (LexemeRelation lexemeRelation : lexemeRelations) {
			Long lexeme1Id = lexemeRelation.getLexeme1Id();
			Long lexeme2Id = lexemeRelation.getLexeme2Id();
			String lexemeRelationTypeCode = lexemeRelation.getLexemeRelationTypeCode();
			Long sumLexeme1Id = sumLexemeIdMap.get(lexeme1Id);
			Long sumLexeme2Id = sumLexemeIdMap.get(lexeme2Id);
			Long lexemeRelationId = createLexemeRelation(sumLexeme1Id, sumLexeme2Id, lexemeRelationTypeCode);
			if (lexemeRelationId != null) {
				sumLexemeRelationCount.increment();
			}
		}

		logger.debug("Done with lexeme relations");
	}

	private void createLexemeCollocations(
			Map<Long, Long> sumLexemeIdMap, List<Collocation> collocations, List<LexemeCollocationTuple> lexemeCollocationTuples,
			List<LexemeCollocationGroupTuple> lexemeCollocationGroupTuples, Map<String, Count> countsMap) throws Exception {

		logger.debug("Creating collocations and associated lexeme bindings");

		Count sumCollocationCount = countsMap.get("sumCollocationCount");
		Count sumLexemeCollocCount = countsMap.get("sumLexemeCollocCount");
		Count sumLexemeCollocPosGroupCount = countsMap.get("sumLexemeCollocPosGroupCount");
		Count sumLexemeCollocRelGroupCount = countsMap.get("sumLexemeCollocRelGroupCount");

		Map<Long, Long> collocIdMap = new HashMap<>();

		for (Collocation colloc : collocations) {

			Long sourceCollocId = colloc.getCollocationId();
			String value = colloc.getValue();
			String definition = colloc.getDefinition();
			Float frequency = colloc.getFrequency();
			Float score = colloc.getScore();
			List<String> usages = colloc.getUsages();
			Complexity complexity = colloc.getComplexity();
			Long targetCollocId = createCollocation(value, definition, frequency, score, usages, complexity);
			collocIdMap.put(sourceCollocId, targetCollocId);
			sumCollocationCount.increment();
		}

		Map<Long, Long> lexCollocPosGroupIdMap = new HashMap<>();
		Map<Long, Long> lexCollocRelGroupIdMap = new HashMap<>();

		for (LexemeCollocationGroupTuple tuple : lexemeCollocationGroupTuples) {

			Long sourceLexemeId = tuple.getLexemeId();
			Long targetLexemeId = sumLexemeIdMap.get(sourceLexemeId);
			Long sourcePosGroupId = tuple.getPosGroupId();
			Long sourceRelGroupId = tuple.getRelGroupId();

			Long targetPosGroupId = null;
			Long targetRelGroupId = null;

			if (sourcePosGroupId != null) {
				targetPosGroupId = lexCollocPosGroupIdMap.get(sourcePosGroupId);
				if (targetPosGroupId == null) {
					String posGroupCode = tuple.getPosGroupCode();
					Long posGroupOrderBy = tuple.getPosGroupOrderBy();
					targetPosGroupId = createCollocPosGroup(targetLexemeId, posGroupCode, posGroupOrderBy);
					lexCollocPosGroupIdMap.put(sourcePosGroupId, targetPosGroupId);
					sumLexemeCollocPosGroupCount.increment();
				}
			}

			if (sourceRelGroupId != null) {
				targetRelGroupId = lexCollocRelGroupIdMap.get(sourceRelGroupId);
				if (targetRelGroupId == null) {
					String relGroupName = tuple.getRelGroupName();
					Float relGroupFrequency = tuple.getRelGroupFrequency();
					Float relGroupScore = tuple.getRelGroupScore();
					Long relGroupOrderBy = tuple.getRelGroupOrderBy();
					targetRelGroupId = createCollocRelGroup(targetPosGroupId, relGroupName, relGroupFrequency, relGroupScore, relGroupOrderBy);
					lexCollocRelGroupIdMap.put(sourceRelGroupId, targetRelGroupId);
					sumLexemeCollocRelGroupCount.increment();
				}
			}
		}

		Map<Long, Long> lexCollocIdMap = new HashMap<>();

		for (LexemeCollocationTuple tuple : lexemeCollocationTuples) {

			Long sourceLexemeId = tuple.getLexemeId();
			Long sourceLexCollocId = tuple.getLexCollocId();
			Long sourceRelGroupId = tuple.getRelGroupId();
			Long sourceCollocId = tuple.getCollocationId();
			Long targetLexemeId = sumLexemeIdMap.get(sourceLexemeId);
			Long targetCollocId = collocIdMap.get(sourceCollocId);
			Long targetRelGroupId = lexCollocRelGroupIdMap.get(sourceRelGroupId);
			String memberForm = tuple.getMemberForm();
			String conjunct = tuple.getConjunct();
			Float weight = tuple.getWeight();
			Integer memberOrder = tuple.getMemberOrder();
			Integer groupOrder = tuple.getGroupOrder();
			Long targetLexCollocId = createLexemeCollocation(targetLexemeId, targetRelGroupId, targetCollocId, memberForm, conjunct, weight, memberOrder, groupOrder);
			lexCollocIdMap.put(sourceLexCollocId, targetLexCollocId);
			sumLexemeCollocCount.increment();
		}

		logger.debug("Done with collocations");
	}

	private Long createLexemeCollocation(
			Long lexemeId, Long relGroupId, Long collocationId, String memberForm, String conjunct, Float weight, Integer memberOrder, Integer groupOrder) throws Exception {

		Map<String, Object> tableRowParamMap = new HashMap<>();
		tableRowParamMap.put("collocation_id", collocationId);
		tableRowParamMap.put("lexeme_id", lexemeId);
		if (relGroupId != null) {
			tableRowParamMap.put("rel_group_id", relGroupId);
		}
		tableRowParamMap.put("member_form", memberForm);
		if (StringUtils.isNotBlank(conjunct)) {
			tableRowParamMap.put("conjunct", conjunct);
		}
		tableRowParamMap.put("weight", weight);
		tableRowParamMap.put("member_order", memberOrder);
		if (groupOrder != null) {
			tableRowParamMap.put("group_order", groupOrder);
		}
		Long lexCollocId = basicDbService.create(LEX_COLLOC, tableRowParamMap);
		return lexCollocId;
	}

	private Long createCollocPosGroup(Long lexemeId, String posGroupCode, Long orderBy) throws Exception {

		Map<String, Object> tableRowParamMap = new HashMap<>();
		tableRowParamMap.put("lexeme_id", lexemeId);
		tableRowParamMap.put("pos_group_code", posGroupCode);
		tableRowParamMap.put("order_by", orderBy);
		Long collocPosGroupId = basicDbService.create(LEX_COLLOC_POS_GROUP, tableRowParamMap);
		return collocPosGroupId;
	}

	private Long createCollocRelGroup(Long posGroupId, String name, Float frequency, Float score, Long orderBy) throws Exception {

		Map<String, Object> tableRowParamMap = new HashMap<>();
		tableRowParamMap.put("pos_group_id", posGroupId);
		tableRowParamMap.put("name", name);
		if (frequency != null) {
			tableRowParamMap.put("frequency", frequency);
		}
		if (score != null) {
			tableRowParamMap.put("score", score);
		}
		tableRowParamMap.put("order_by", orderBy);
		Long collocRelGroupId = basicDbService.create(LEX_COLLOC_REL_GROUP, tableRowParamMap);
		return collocRelGroupId;
	}

	private void createLexemeLifecycleLogs(Map<Long, Long> sumLexemeIdMap, List<LexemeLifecycleLog> lexemeLifecycleLogs, Map<String, Count> countsMap) throws Exception {

		logger.debug("Creating lexeme lifecycle logs");

		Count sumLexemeLifecycleLogCount = countsMap.get("sumLexemeLifecycleLogCount");

		for (LexemeLifecycleLog lexemeLifecycleLog : lexemeLifecycleLogs) {
			Long sourceLexemeId = lexemeLifecycleLog.getLexemeId();
			Long targetLexemeId = sumLexemeIdMap.get(sourceLexemeId);
			Long entityId = lexemeLifecycleLog.getEntityId();
			LifecycleEntity entity = lexemeLifecycleLog.getEntity();
			LifecycleProperty property = lexemeLifecycleLog.getProperty();
			LifecycleEventType eventType = lexemeLifecycleLog.getEventType();
			String eventBy = lexemeLifecycleLog.getEventBy();
			Timestamp eventOn = lexemeLifecycleLog.getEventOn();
			String recent = lexemeLifecycleLog.getRecent();
			String entry = lexemeLifecycleLog.getEntry();
			createLifecycleLog(LifecycleLogOwner.LEXEME, targetLexemeId, entityId, entity, property, eventType, eventBy, eventOn, recent, entry);
			sumLexemeLifecycleLogCount.increment();
		}

		logger.debug("Done with lexeme lifecycle logs");
	}

	private void appendMergeDatasetToDefinitions(String lexemeMergeName, Map<String, Count> countsMap) {

		logger.debug("Appending merge dataset to definitions");

		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("datasetCode", lexemeMergeName);
		int definitionCount = basicDbService.executeScript(sqlInsertDefinitionDatasetWhereNotExists, paramMap);
		Count sumDefinitionCount = countsMap.get("sumDefinitionCount");
		sumDefinitionCount.increment(definitionCount);
	}

	private void appendToReport(String reportName, Object ... reportCells) throws Exception {
		if (!doReports) {
			return;
		}
		String logRow = StringUtils.join(reportCells, CSV_SEPARATOR);
		reportComposer.append(reportName, logRow);
	}

	class LexemeExt extends Lexeme {

		private static final long serialVersionUID = 1L;

		private List<Freeform> freeforms;

		private List<LexemeFrequency> lexemeFrequencies;

		private List<LexemeTag> lexemeTags;

		private List<LexemeClassifier> lexemeRegisters;

		private List<LexemeClassifier> lexemePoses;

		private List<LexemeClassifier> lexemeDerivs;

		private List<LexemeClassifier> lexemeRegions;

		private List<LexemeSourceLink> lexemeSourceLinks;

		public List<Freeform> getFreeforms() {
			return freeforms;
		}

		public void setFreeforms(List<Freeform> freeforms) {
			this.freeforms = freeforms;
		}

		public List<LexemeFrequency> getLexemeFrequencies() {
			return lexemeFrequencies;
		}

		public void setLexemeFrequencies(List<LexemeFrequency> lexemeFrequencies) {
			this.lexemeFrequencies = lexemeFrequencies;
		}

		public List<LexemeTag> getLexemeTags() {
			return lexemeTags;
		}

		public void setLexemeTags(List<LexemeTag> lexemeTags) {
			this.lexemeTags = lexemeTags;
		}

		public List<LexemeClassifier> getLexemeRegisters() {
			return lexemeRegisters;
		}

		public void setLexemeRegisters(List<LexemeClassifier> lexemeRegisters) {
			this.lexemeRegisters = lexemeRegisters;
		}

		public List<LexemeClassifier> getLexemePoses() {
			return lexemePoses;
		}

		public void setLexemePoses(List<LexemeClassifier> lexemePoses) {
			this.lexemePoses = lexemePoses;
		}

		public List<LexemeClassifier> getLexemeDerivs() {
			return lexemeDerivs;
		}

		public void setLexemeDerivs(List<LexemeClassifier> lexemeDerivs) {
			this.lexemeDerivs = lexemeDerivs;
		}

		public List<LexemeClassifier> getLexemeRegions() {
			return lexemeRegions;
		}

		public void setLexemeRegions(List<LexemeClassifier> lexemeRegions) {
			this.lexemeRegions = lexemeRegions;
		}

		public List<LexemeSourceLink> getLexemeSourceLinks() {
			return lexemeSourceLinks;
		}

		public void setLexemeSourceLinks(List<LexemeSourceLink> lexemeSourceLinks) {
			this.lexemeSourceLinks = lexemeSourceLinks;
		}

	}

	class LexemeExtRowMapper extends AbstractRowMapper implements RowMapper<LexemeExt> {

		@Override
		public LexemeExt mapRow(ResultSet rs, int rowNum) throws SQLException {

			Long lexemeId = rs.getObject("id", Long.class);
			Long wordId = rs.getObject("word_id", Long.class);
			Long meaningId = rs.getObject("meaning_id", Long.class);
			String datasetCode = rs.getString("dataset_code");
			String frequencyGroupCode = rs.getString("frequency_group_code");
			Float corpusFrequency = getFloat(rs, "corpus_frequency");
			Integer level1 = rs.getObject("level1", Integer.class);
			Integer level2 = rs.getObject("level2", Integer.class);
			String valueStateCode = rs.getString("value_state_code");
			boolean isPublic = rs.getBoolean("is_public");
			String complexityStr = rs.getString("complexity");
			Complexity complexity = null;
			if (StringUtils.isNotBlank(complexityStr)) {
				complexity = Complexity.valueOf(complexityStr);
			}
			Long orderBy = rs.getObject("order_by", Long.class);

			LexemeExt lexeme = new LexemeExt();
			lexeme.setLexemeId(lexemeId);
			lexeme.setWordId(wordId);
			lexeme.setMeaningId(meaningId);
			lexeme.setDatasetCode(datasetCode);
			lexeme.setFrequencyGroupCode(frequencyGroupCode);
			lexeme.setCorpusFrequency(corpusFrequency);
			lexeme.setLevel1(level1);
			lexeme.setLevel2(level2);
			lexeme.setValueStateCode(valueStateCode);
			lexeme.setIsPublic(isPublic);
			lexeme.setComplexity(complexity);
			lexeme.setOrderBy(orderBy);
			return lexeme;
		}

	}
}
