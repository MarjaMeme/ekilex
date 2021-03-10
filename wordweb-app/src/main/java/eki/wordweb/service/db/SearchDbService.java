package eki.wordweb.service.db;

import static eki.wordweb.data.db.Tables.MVIEW_WW_COLLOCATION;
import static eki.wordweb.data.db.Tables.MVIEW_WW_DEFINITION_SOURCE_LINK;
import static eki.wordweb.data.db.Tables.MVIEW_WW_FORM;
import static eki.wordweb.data.db.Tables.MVIEW_WW_LEXEME;
import static eki.wordweb.data.db.Tables.MVIEW_WW_LEXEME_FREEFORM_SOURCE_LINK;
import static eki.wordweb.data.db.Tables.MVIEW_WW_LEXEME_RELATION;
import static eki.wordweb.data.db.Tables.MVIEW_WW_LEXEME_SOURCE_LINK;
import static eki.wordweb.data.db.Tables.MVIEW_WW_MEANING;
import static eki.wordweb.data.db.Tables.MVIEW_WW_MEANING_FREEFORM_SOURCE_LINK;
import static eki.wordweb.data.db.Tables.MVIEW_WW_MEANING_RELATION;
import static eki.wordweb.data.db.Tables.MVIEW_WW_WORD;
import static eki.wordweb.data.db.Tables.MVIEW_WW_WORD_ETYMOLOGY;
import static eki.wordweb.data.db.Tables.MVIEW_WW_WORD_ETYM_SOURCE_LINK;
import static eki.wordweb.data.db.Tables.MVIEW_WW_WORD_RELATION;
import static eki.wordweb.data.db.Tables.MVIEW_WW_WORD_SEARCH;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record3;
import org.jooq.Record5;
import org.jooq.Table;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import eki.common.constant.Complexity;
import eki.common.constant.DatasetType;
import eki.common.constant.FormMode;
import eki.common.constant.GlobalConstant;
import eki.wordweb.constant.SystemConstant;
import eki.wordweb.data.CollocationTuple;
import eki.wordweb.data.SearchContext;
import eki.wordweb.data.Form;
import eki.wordweb.data.Lexeme;
import eki.wordweb.data.LexemeMeaningTuple;
import eki.wordweb.data.Word;
import eki.wordweb.data.WordEtymTuple;
import eki.wordweb.data.WordForm;
import eki.wordweb.data.WordRelationsTuple;
import eki.wordweb.data.WordSearchElement;
import eki.wordweb.data.db.Routines;
import eki.wordweb.data.db.tables.MviewWwCollocation;
import eki.wordweb.data.db.tables.MviewWwDefinitionSourceLink;
import eki.wordweb.data.db.tables.MviewWwForm;
import eki.wordweb.data.db.tables.MviewWwLexeme;
import eki.wordweb.data.db.tables.MviewWwLexemeFreeformSourceLink;
import eki.wordweb.data.db.tables.MviewWwLexemeRelation;
import eki.wordweb.data.db.tables.MviewWwLexemeSourceLink;
import eki.wordweb.data.db.tables.MviewWwMeaning;
import eki.wordweb.data.db.tables.MviewWwMeaningFreeformSourceLink;
import eki.wordweb.data.db.tables.MviewWwMeaningRelation;
import eki.wordweb.data.db.tables.MviewWwWord;
import eki.wordweb.data.db.tables.MviewWwWordEtymSourceLink;
import eki.wordweb.data.db.tables.MviewWwWordEtymology;
import eki.wordweb.data.db.tables.MviewWwWordRelation;
import eki.wordweb.data.db.tables.MviewWwWordSearch;
import eki.wordweb.data.db.tables.records.MviewWwWordRecord;
import eki.wordweb.data.db.udt.records.TypeLangComplexityRecord;
import eki.wordweb.service.util.JooqBugCompensator;

@Component
public class SearchDbService implements GlobalConstant, SystemConstant {

	@Autowired
	private DSLContext create;

	@Autowired
	private JooqBugCompensator jooqBugCompensator;

	@SuppressWarnings("unchecked")
	public Map<String, List<WordSearchElement>> getWordsByInfixLev(String wordInfix, SearchContext searchContext, int maxWordCount) {

		String wordInfixLower = StringUtils.lowerCase(wordInfix);
		String wordInfixCrit = '%' + wordInfixLower + '%';
		String wordInfixCritUnaccent = '%' + StringUtils.stripAccents(wordInfixLower) + '%';

		MviewWwWordSearch w = MVIEW_WW_WORD_SEARCH.as("w");
		MviewWwWordSearch aw = MVIEW_WW_WORD_SEARCH.as("aw");
		MviewWwWordSearch f = MVIEW_WW_WORD_SEARCH.as("f");
		Field<String> wgf = DSL.field(DSL.val(WORD_SEARCH_GROUP_WORD));

		Table<Record5<String, String, String, Long, TypeLangComplexityRecord[]>> ws = DSL
				.select(
						wgf.as("sgroup"),
						w.WORD,
						w.CRIT,
						w.LANG_ORDER_BY,
						w.LANG_COMPLEXITIES)
				.from(w)
				.where(
						w.SGROUP.eq(WORD_SEARCH_GROUP_WORD)
								.and(w.UNACRIT.like(wordInfixCritUnaccent)))
				.unionAll(DSL
						.select(
								wgf.as("sgroup"),
								aw.WORD,
								aw.CRIT,
								aw.LANG_ORDER_BY,
								aw.LANG_COMPLEXITIES)
						.from(aw)
						.where(
								aw.SGROUP.eq(WORD_SEARCH_GROUP_AS_WORD)
										.and(aw.UNACRIT.like(wordInfixCritUnaccent))))
				.asTable("ws");

		Field<Integer> wlf = DSL.field(Routines.levenshtein1(ws.field("word", String.class), DSL.inline(wordInfixLower)));

		Condition wsWhere = ws.field("crit").like(wordInfixCrit);
		wsWhere = applyLangCompDatasetFilter(ws, searchContext, wsWhere);

		Condition fWhere = f.SGROUP.eq(WORD_SEARCH_GROUP_FORM).and(f.CRIT.eq(wordInfixLower));
		fWhere = applyLangCompDatasetFilter(f, searchContext, fWhere);

		Table<Record3<String, String, Integer>> wfs = DSL
				.select(
						ws.field("sgroup", String.class),
						ws.field("word", String.class),
						wlf.as("lev"))
				.from(ws)
				.where(wsWhere)
				.orderBy(
						ws.field("lang_order_by"),
						DSL.field("lev"))
				.limit(maxWordCount)
				.unionAll(DSL
						.select(
								f.SGROUP,
								f.WORD,
								DSL.field(DSL.val(0)).as("lev"))
						.from(f)
						.where(fWhere)
						.orderBy(f.WORD)
						.limit(maxWordCount))
				.asTable("wfs");

		return (Map<String, List<WordSearchElement>>) create
				.select(
						wfs.field("sgroup", String.class),
						wfs.field("word", String.class))
				.from(wfs)
				.fetchGroups("sgroup", WordSearchElement.class);
	}

	public List<Word> getWords(String searchWord, SearchContext searchContext) {

		MviewWwWord w = MVIEW_WW_WORD.as("w");
		MviewWwForm f = MVIEW_WW_FORM.as("f");

		String searchWordLower = StringUtils.lowerCase(searchWord);
		boolean fiCollationExists = searchContext.isFiCollationExists();

		if (StringUtils.equals(searchWordLower, ILLEGAL_FORM_VALUE)) {
			return Collections.emptyList();
		}

		Table<MviewWwWordRecord> ww = DSL
				.selectFrom(w)
				.where(DSL.or(DSL.lower(w.WORD).eq(searchWordLower), DSL.lower(w.AS_WORD).eq(searchWordLower)) 
						.andNotExists(DSL
								.select(f.FORM_ID)
								.from(f)
								.where(
										f.WORD_ID.eq(w.WORD_ID)
										.and(f.MODE.eq(FormMode.WORD.name()))
										.and(f.MORPH_CODE.ne(UNKNOWN_FORM_CODE)))))
				.unionAll(DSL
						.selectFrom(w)
						.whereExists(DSL
								.select(f.WORD_ID)
								.from(f)
								.where(f.WORD_ID.eq(w.WORD_ID)
										.and(DSL.lower(f.VALUE).eq(searchWordLower))
										.and(f.MORPH_CODE.ne(UNKNOWN_FORM_CODE))
										.and(f.MORPH_EXISTS.isTrue())
										.and(f.IS_QUESTIONABLE.isFalse()))))
				.asTable("w");

		Condition where = applyLangCompDatasetFilter(w, searchContext, DSL.noCondition());

		Field<String> wvobf;
		if (fiCollationExists) {
			wvobf = ww.field("word", String.class).collate("fi_FI");
		} else {
			wvobf = ww.field("word", String.class);
		}
		return create
				.selectFrom(ww)
				.where(where)
				.orderBy(ww.field("min_ds_order_by"), ww.field("lang_order_by"), wvobf, ww.field("word_type_order_by"), ww.field("homonym_nr"))
				.fetch(record -> {
					Word pojo = record.into(Word.class);
					jooqBugCompensator.trimWordTypeData(pojo.getMeaningWords());
					jooqBugCompensator.trimDefinitions(pojo.getDefinitions());
					return pojo;
				});
	}


	public List<Lexeme> getLexemes(Long wordId, SearchContext searchContext) {

		DatasetType datasetType = searchContext.getDatasetType();
		List<String> datasetCodes = searchContext.getDatasetCodes();
		Complexity lexComplexity = searchContext.getLexComplexity();
		List<String> complexityNames = composeFilteringComplexityNames(lexComplexity);

		MviewWwLexeme l = MVIEW_WW_LEXEME.as("l");
		MviewWwLexemeRelation lr = MVIEW_WW_LEXEME_RELATION.as("lr");

		Condition where = l.WORD_ID.eq(wordId).and(l.COMPLEXITY.in(complexityNames));
		if (datasetType != null) {
			where = where.and(l.DATASET_TYPE.eq(datasetType.name()));
		}
		if (CollectionUtils.isNotEmpty(datasetCodes)) {
			if (datasetCodes.size() == 1) {
				String datasetCode = datasetCodes.get(0);
				where = where.and(l.DATASET_CODE.eq(datasetCode));
			} else {
				where = where.and(l.DATASET_CODE.in(datasetCodes));
			}
		}
		where = applyLangCompDatasetFilter(l, searchContext, where);

		return getLexemes(l, lr, where);
	}

	private List<Lexeme> getLexemes(MviewWwLexeme l, MviewWwLexemeRelation lr, Condition where) {

		MviewWwLexemeSourceLink lsl = MVIEW_WW_LEXEME_SOURCE_LINK.as("lsl");
		MviewWwLexemeFreeformSourceLink ffsl = MVIEW_WW_LEXEME_FREEFORM_SOURCE_LINK.as("ffsl");

		return create
				.select(
						l.WORD_ID,
						l.LEXEME_ID,
						l.MEANING_ID,
						l.DATASET_CODE,
						l.DATASET_TYPE,
						l.DATASET_NAME,
						l.VALUE_STATE_CODE,
						l.LEVEL1,
						l.LEVEL2,
						l.COMPLEXITY,
						l.WEIGHT,
						l.DATASET_ORDER_BY,
						l.LEXEME_ORDER_BY,
						l.VALUE_STATE_ORDER_BY,
						l.REGISTER_CODES,
						l.POS_CODES,
						l.DERIV_CODES,
						l.MEANING_WORDS,
						l.ADVICE_NOTES,
						l.NOTES.as("lexeme_notes"),
						l.GRAMMARS,
						l.GOVERNMENTS,
						l.USAGES,
						l.OD_LEXEME_RECOMMENDATIONS,
						lsl.SOURCE_LINKS.as("lexeme_source_links"),
						ffsl.SOURCE_LINKS.as("lexeme_freeform_source_links"),
						lr.RELATED_LEXEMES)
				.from(l
						.leftOuterJoin(lsl).on(lsl.LEXEME_ID.eq(l.LEXEME_ID))
						.leftOuterJoin(ffsl).on(ffsl.LEXEME_ID.eq(l.LEXEME_ID))
						.leftOuterJoin(lr).on(lr.LEXEME_ID.eq(l.LEXEME_ID)))
				.where(where)
				.fetch(record -> {
					Lexeme pojo = record.into(Lexeme.class);
					jooqBugCompensator.trimWordTypeData(pojo.getMeaningWords());
					jooqBugCompensator.trimFreeforms(pojo.getLexemeNotes());
					jooqBugCompensator.trimFreeforms(pojo.getGrammars());
					jooqBugCompensator.trimFreeforms(pojo.getGovernments());
					jooqBugCompensator.trimUsages(pojo.getUsages());
					jooqBugCompensator.trimWordTypeData(pojo.getRelatedLexemes());
					return pojo;
				});
	}

	private List<String> composeFilteringComplexityNames(Complexity lexComplexity) {
		return Arrays.asList(Complexity.ANY.name(), lexComplexity.name());
	}

	private Condition applyLangCompDatasetFilter(Table<?> lcTable, SearchContext searchContext, Condition where) {

		List<String> destinLangs = searchContext.getDestinLangs();
		List<String> datasetCodes = searchContext.getDatasetCodes();
		Complexity lexComplexity = searchContext.getLexComplexity();
		List<String> complexityNames = composeFilteringComplexityNames(lexComplexity);

		Table<?> lc = DSL.unnest(lcTable.field("lang_complexities")).as("lc", "lang", "dataset_code", "lex_complexity", "data_complexity");
		Condition lcWhere = lc.field("lex_complexity", String.class).in(complexityNames)
				.and(lc.field("data_complexity", String.class).in(complexityNames));
		if (CollectionUtils.isNotEmpty(destinLangs)) {
			if (destinLangs.size() == 1) {
				String destinLang = destinLangs.get(0);
				lcWhere = lcWhere.and(lc.field("lang", String.class).eq(destinLang));
			} else {
				lcWhere = lcWhere.and(lc.field("lang", String.class).in(destinLangs));
			}
		}
		if (CollectionUtils.isNotEmpty(datasetCodes)) {
			if (datasetCodes.size() == 1) {
				String datasetCode = datasetCodes.get(0);
				lcWhere = lcWhere.and(lc.field("dataset_code", String.class).eq(datasetCode));
			} else {
				lcWhere = lcWhere.and(lc.field("dataset_code", String.class).in(datasetCodes));
			}
		}
		where = where.andExists(DSL.selectFrom(lc).where(lcWhere));
		return where;
	}

	public Word getWord(Long wordId) {

		MviewWwWord w = MVIEW_WW_WORD.as("w");
		MviewWwWordEtymSourceLink wesl = MVIEW_WW_WORD_ETYM_SOURCE_LINK.as("wesl");

		return create
				.select(
						w.WORD_ID,
						w.WORD,
						w.WORD_PRESE,
						w.AS_WORD,
						w.HOMONYM_NR,
						w.LANG,
						w.WORD_TYPE_CODES,
						w.DISPLAY_MORPH_CODE,
						w.ASPECT_CODE,
						w.VOCAL_FORM,
						w.LAST_ACTIVITY_EVENT_ON,
						w.MEANING_WORDS,
						w.DEFINITIONS,
						wesl.SOURCE_LINKS.as("word_etym_source_links"),
						w.OD_WORD_RECOMMENDATIONS,
						w.FORMS_EXIST)
				.from(w.leftOuterJoin(wesl).on(wesl.WORD_ID.eq(wordId)))
				.where(w.WORD_ID.eq(wordId))
				.fetchOne(record -> {
					Word pojo = record.into(Word.class);
					jooqBugCompensator.trimWordTypeData(pojo.getMeaningWords());
					jooqBugCompensator.trimDefinitions(pojo.getDefinitions());
					return pojo;
				});
	}

	public List<Form> getParadigmForms(Long paradigmId, Integer maxDisplayLevel, boolean excludeQuestionable) {

		MviewWwForm f = MVIEW_WW_FORM.as("f");

		Condition where = f.PARADIGM_ID.eq(paradigmId).and(f.MODE.in(FormMode.WORD.name(), FormMode.FORM.name()));
		if (maxDisplayLevel != null) {
			where = where.and(f.DISPLAY_LEVEL.le(maxDisplayLevel));
		}
		if (excludeQuestionable) {
			where = where.and(f.IS_QUESTIONABLE.isFalse());
		}

		return create
				.selectFrom(f)
				.where(where)
				.orderBy(f.ORDER_BY, f.FORM_ID)
				.fetchInto(Form.class);
	}

	public List<Form> getWordForms(Long wordId, SearchContext searchContext) {

		Integer maxDisplayLevel = searchContext.getMaxDisplayLevel();
		boolean excludeQuestionable = searchContext.isExcludeQuestionable();

		MviewWwForm f = MVIEW_WW_FORM.as("f");

		Condition where = f.WORD_ID.eq(wordId).and(f.MODE.in(FormMode.WORD.name(), FormMode.FORM.name()));
		if (maxDisplayLevel != null) {
			where = where.and(f.DISPLAY_LEVEL.le(maxDisplayLevel));
		}
		if (excludeQuestionable) {
			where = where.and(f.IS_QUESTIONABLE.isFalse());
		}
		return create
				.selectFrom(f)
				.where(where)
				.orderBy(f.PARADIGM_ID, f.ORDER_BY, f.FORM_ID)
				.fetchInto(Form.class);
	}

	@Cacheable(value = CACHE_KEY_NULL_WORD, key = "{#wordId, #tokens}")
	public List<WordForm> getWordFormCandidates(Long wordId, List<String> tokens) {

		MviewWwForm f = MVIEW_WW_FORM.as("f");

		return create
				.select(
						f.WORD,
						f.VALUE.as("form"))
				.from(f)
				.where(
						f.WORD_ID.eq(wordId)
								.and(f.VALUE.in(tokens)))
				.fetchInto(WordForm.class);
	}

	public List<LexemeMeaningTuple> getLexemeMeaningTuples(Long wordId) {

		MviewWwLexeme l = MVIEW_WW_LEXEME.as("l");
		MviewWwMeaning m = MVIEW_WW_MEANING.as("m");
		MviewWwMeaningRelation mr = MVIEW_WW_MEANING_RELATION.as("mr");
		MviewWwMeaningFreeformSourceLink ffsl = MVIEW_WW_MEANING_FREEFORM_SOURCE_LINK.as("ffsl");
		MviewWwDefinitionSourceLink dsl = MVIEW_WW_DEFINITION_SOURCE_LINK.as("dsl");

		Condition where = l.WORD_ID.eq(wordId);

		return create
				.select(
						l.LEXEME_ID,
						m.MEANING_ID,
						m.LAST_ACTIVITY_EVENT_ON.as("meaning_last_activity_event_on"),
						m.DOMAIN_CODES,
						m.IMAGE_FILES,
						m.MEDIA_FILES,
						m.SYSTEMATIC_POLYSEMY_PATTERNS,
						m.SEMANTIC_TYPES,
						m.LEARNER_COMMENTS,
						m.NOTES,
						m.DEFINITIONS,
						mr.RELATED_MEANINGS,
						ffsl.SOURCE_LINKS.as("freeform_source_links"),
						dsl.SOURCE_LINKS.as("definition_source_links"))
				.from(
						l.innerJoin(m).on(m.MEANING_ID.eq(l.MEANING_ID))
								.leftOuterJoin(mr).on(mr.MEANING_ID.eq(m.MEANING_ID))
								.leftOuterJoin(ffsl).on(ffsl.MEANING_ID.eq(m.MEANING_ID))
								.leftOuterJoin(dsl).on(dsl.MEANING_ID.eq(m.MEANING_ID)))
				.where(where)
				.orderBy(m.MEANING_ID, l.LEXEME_ID)
				.fetch(record -> {
					LexemeMeaningTuple pojo = record.into(LexemeMeaningTuple.class);
					jooqBugCompensator.trimDefinitions(pojo.getDefinitions());
					jooqBugCompensator.trimFreeforms(pojo.getNotes());
					jooqBugCompensator.trimWordTypeData(pojo.getRelatedMeanings());
					return pojo;
				});
	}

	public WordRelationsTuple getWordRelationsTuple(Long wordId) {

		MviewWwWordRelation wr = MVIEW_WW_WORD_RELATION.as("wr");

		return create
				.select(
						wr.WORD_ID,
						wr.RELATED_WORDS,
						wr.WORD_GROUP_MEMBERS)
				.from(wr)
				.where(wr.WORD_ID.eq(wordId))
				.fetchOptional(record -> {
					WordRelationsTuple pojo = record.into(WordRelationsTuple.class);
					jooqBugCompensator.trimWordTypeData(pojo.getRelatedWords());
					jooqBugCompensator.trimWordTypeData(pojo.getWordGroupMembers());
					return pojo;
				})
				.orElse(null);
	}

	public List<WordEtymTuple> getWordEtymologyTuples(Long wordId) {

		MviewWwWordEtymology we = MVIEW_WW_WORD_ETYMOLOGY.as("we");

		return create
				.select(
						we.WORD_ID,
						we.WORD_ETYM_ID,
						we.WORD_ETYM_WORD_ID,
						we.WORD_ETYM_WORD,
						we.WORD_ETYM_WORD_LANG,
						we.WORD_ETYM_WORD_MEANING_WORDS,
						we.ETYMOLOGY_TYPE_CODE,
						we.ETYMOLOGY_YEAR,
						we.WORD_ETYM_COMMENT,
						we.WORD_ETYM_IS_QUESTIONABLE,
						we.WORD_ETYM_RELATIONS)
				.from(we)
				.where(we.WORD_ID.eq(wordId))
				.orderBy(we.WORD_ETYM_ORDER_BY)
				.fetchInto(WordEtymTuple.class);
	}

	public List<CollocationTuple> getCollocations(Long wordId) {

		MviewWwCollocation c = MVIEW_WW_COLLOCATION.as("c");

		Condition where = c.WORD_ID.eq(wordId);

		return create
				.select(
						c.LEXEME_ID,
						c.WORD_ID,
						c.POS_GROUP_ID,
						c.POS_GROUP_CODE,
						c.REL_GROUP_ID,
						c.REL_GROUP_NAME,
						c.COLLOC_ID,
						c.COLLOC_VALUE,
						c.COLLOC_DEFINITION,
						c.COLLOC_USAGES,
						c.COLLOC_MEMBERS,
						c.COMPLEXITY)
				.from(c)
				.where(where)
				.orderBy(
						c.POS_GROUP_ORDER_BY,
						c.REL_GROUP_ORDER_BY,
						c.COLLOC_GROUP_ORDER,
						c.COLLOC_ID)
				.fetch(record -> {
					CollocationTuple pojo = record.into(CollocationTuple.class);
					jooqBugCompensator.trimCollocMembers(pojo.getCollocMembers());
					return pojo;
				});
	}

}
