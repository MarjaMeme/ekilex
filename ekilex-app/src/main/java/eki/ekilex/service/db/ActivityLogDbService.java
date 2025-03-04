package eki.ekilex.service.db;

import static eki.ekilex.data.db.Tables.ACTIVITY_LOG;
import static eki.ekilex.data.db.Tables.DEFINITION;
import static eki.ekilex.data.db.Tables.DEFINITION_FREEFORM;
import static eki.ekilex.data.db.Tables.DEFINITION_SOURCE_LINK;
import static eki.ekilex.data.db.Tables.FREEFORM;
import static eki.ekilex.data.db.Tables.FREEFORM_SOURCE_LINK;
import static eki.ekilex.data.db.Tables.LEXEME;
import static eki.ekilex.data.db.Tables.LEXEME_ACTIVITY_LOG;
import static eki.ekilex.data.db.Tables.LEXEME_FREEFORM;
import static eki.ekilex.data.db.Tables.LEXEME_SOURCE_LINK;
import static eki.ekilex.data.db.Tables.LEX_RELATION;
import static eki.ekilex.data.db.Tables.MEANING;
import static eki.ekilex.data.db.Tables.MEANING_ACTIVITY_LOG;
import static eki.ekilex.data.db.Tables.MEANING_DOMAIN;
import static eki.ekilex.data.db.Tables.MEANING_FREEFORM;
import static eki.ekilex.data.db.Tables.MEANING_LAST_ACTIVITY_LOG;
import static eki.ekilex.data.db.Tables.MEANING_RELATION;
import static eki.ekilex.data.db.Tables.PARADIGM;
import static eki.ekilex.data.db.Tables.SOURCE;
import static eki.ekilex.data.db.Tables.SOURCE_ACTIVITY_LOG;
import static eki.ekilex.data.db.Tables.SOURCE_FREEFORM;
import static eki.ekilex.data.db.Tables.WORD;
import static eki.ekilex.data.db.Tables.WORD_ACTIVITY_LOG;
import static eki.ekilex.data.db.Tables.WORD_ETYMOLOGY;
import static eki.ekilex.data.db.Tables.WORD_FREEFORM;
import static eki.ekilex.data.db.Tables.WORD_LAST_ACTIVITY_LOG;
import static eki.ekilex.data.db.Tables.WORD_RELATION;
import static eki.ekilex.data.db.Tables.WORD_WORD_TYPE;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.JSONB;
import org.jooq.Record2;
import org.jooq.Table;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import eki.common.constant.ActivityEntity;
import eki.common.constant.ActivityOwner;
import eki.common.constant.GlobalConstant;
import eki.common.constant.LastActivityType;
import eki.ekilex.data.TypeActivityLogDiff;
import eki.ekilex.data.WordLexemeMeaningIds;
import eki.ekilex.data.db.tables.ActivityLog;
import eki.ekilex.data.db.tables.Definition;
import eki.ekilex.data.db.tables.DefinitionFreeform;
import eki.ekilex.data.db.tables.Freeform;
import eki.ekilex.data.db.tables.Lexeme;
import eki.ekilex.data.db.tables.LexemeFreeform;
import eki.ekilex.data.db.tables.Meaning;
import eki.ekilex.data.db.tables.MeaningActivityLog;
import eki.ekilex.data.db.tables.MeaningFreeform;
import eki.ekilex.data.db.tables.MeaningLastActivityLog;
import eki.ekilex.data.db.tables.SourceFreeform;
import eki.ekilex.data.db.tables.Word;
import eki.ekilex.data.db.tables.WordActivityLog;
import eki.ekilex.data.db.tables.WordFreeform;
import eki.ekilex.data.db.tables.WordLastActivityLog;
import eki.ekilex.data.db.udt.records.TypeActivityLogDiffRecord;

@Component
public class ActivityLogDbService implements GlobalConstant {

	@Autowired
	private DSLContext create;

	public List<eki.ekilex.data.ActivityLog> getWordActivityLog(Long wordId) {

		ActivityLog al = ACTIVITY_LOG.as("al");
		WordActivityLog wal = WORD_ACTIVITY_LOG.as("wal");
		Field<String> wvf = getWordValueField(al);

		return create.select(
				al.ID,
				al.EVENT_BY,
				al.EVENT_ON,
				al.FUNCT_NAME,
				al.OWNER_ID,
				al.OWNER_NAME,
				al.ENTITY_ID,
				al.ENTITY_NAME,
				al.PREV_DIFFS,
				al.CURR_DIFFS,
				wvf.as("word_value"))
				.from(al, wal)
				.where(
						wal.WORD_ID.eq(wordId)
								.and(wal.ACTIVITY_LOG_ID.eq(al.ID)))
				.orderBy(al.EVENT_ON.desc())
				.fetchInto(eki.ekilex.data.ActivityLog.class);
	}

	public List<eki.ekilex.data.ActivityLog> getMeaningActivityLog(Long meaningId) {

		ActivityLog al = ACTIVITY_LOG.as("al");
		MeaningActivityLog mal = MEANING_ACTIVITY_LOG.as("mal");
		Field<String> wvf = getWordValueField(al);

		return create.select(
				al.ID,
				al.EVENT_BY,
				al.EVENT_ON,
				al.FUNCT_NAME,
				al.OWNER_ID,
				al.OWNER_NAME,
				al.ENTITY_ID,
				al.ENTITY_NAME,
				al.PREV_DIFFS,
				al.CURR_DIFFS,
				wvf.as("word_value"))
				.from(al, mal)
				.where(
						mal.MEANING_ID.eq(meaningId)
								.and(mal.ACTIVITY_LOG_ID.eq(al.ID)))
				.orderBy(al.EVENT_ON.desc())
				.fetchInto(eki.ekilex.data.ActivityLog.class);
	}

	private Field<String> getWordValueField(ActivityLog al) {
		Word w = WORD.as("w");
		Lexeme l = LEXEME.as("l");
		Field<String> wvf = DSL.field(DSL
				.select(w.VALUE)
				.from(w, l)
				.where(
						l.ID.eq(al.OWNER_ID)
								.and(al.OWNER_NAME.eq(ActivityOwner.LEXEME.name()))
								.and(w.ID.eq(l.WORD_ID)))
				.unionAll(DSL
						.select(w.VALUE)
						.from(w)
						.where(
								w.ID.eq(al.OWNER_ID)
										.and(al.OWNER_NAME.eq(ActivityOwner.WORD.name())))));
		return wvf;
	}

	public List<eki.ekilex.data.ActivityLog> getSourceActivityLog(Long sourceId) {

		return create.select(
				ACTIVITY_LOG.ID,
				ACTIVITY_LOG.EVENT_BY,
				ACTIVITY_LOG.EVENT_ON,
				ACTIVITY_LOG.FUNCT_NAME,
				ACTIVITY_LOG.OWNER_ID,
				ACTIVITY_LOG.OWNER_NAME,
				ACTIVITY_LOG.ENTITY_ID,
				ACTIVITY_LOG.ENTITY_NAME,
				ACTIVITY_LOG.PREV_DIFFS,
				ACTIVITY_LOG.CURR_DIFFS)
				.from(ACTIVITY_LOG, SOURCE_ACTIVITY_LOG)
				.where(
						SOURCE_ACTIVITY_LOG.SOURCE_ID.eq(sourceId)
								.and(SOURCE_ACTIVITY_LOG.ACTIVITY_LOG_ID.eq(ACTIVITY_LOG.ID)))
				.orderBy(ACTIVITY_LOG.EVENT_ON.desc())
				.fetchInto(eki.ekilex.data.ActivityLog.class);
	}

	public Timestamp getActivityLogEventOn(Long activityLogId) {

		return create
				.select(ACTIVITY_LOG.EVENT_ON)
				.from(ACTIVITY_LOG)
				.where(ACTIVITY_LOG.ID.eq(activityLogId))
				.fetchOneInto(Timestamp.class);
	}

	public Long create(eki.ekilex.data.ActivityLog activityLog) {

		return create.insertInto(
				ACTIVITY_LOG,
				ACTIVITY_LOG.EVENT_BY,
				ACTIVITY_LOG.FUNCT_NAME,
				ACTIVITY_LOG.OWNER_ID,
				ACTIVITY_LOG.OWNER_NAME,
				ACTIVITY_LOG.ENTITY_ID,
				ACTIVITY_LOG.ENTITY_NAME,
				ACTIVITY_LOG.PREV_DATA,
				ACTIVITY_LOG.CURR_DATA,
				ACTIVITY_LOG.PREV_DIFFS,
				ACTIVITY_LOG.CURR_DIFFS)
				.values(
						activityLog.getEventBy(),
						activityLog.getFunctName(),
						activityLog.getOwnerId(),
						activityLog.getOwnerName().name(),
						activityLog.getEntityId(),
						activityLog.getEntityName().name(),
						JSONB.valueOf(activityLog.getPrevData()),
						JSONB.valueOf(activityLog.getCurrData()),
						convert(activityLog.getPrevDiffs()),
						convert(activityLog.getCurrDiffs()))
				.returning(ACTIVITY_LOG.ID)
				.fetchOne()
				.getId();
	}

	public void createLexemesActivityLogs(Long activityLogId, Long... lexemeIds) {

		for (Long lexemeId : lexemeIds) {
			create
					.insertInto(LEXEME_ACTIVITY_LOG, LEXEME_ACTIVITY_LOG.LEXEME_ID, LEXEME_ACTIVITY_LOG.ACTIVITY_LOG_ID)
					.select(DSL
							.select(DSL.val(lexemeId), DSL.val(activityLogId))
							.whereExists(DSL.select(LEXEME.ID).from(LEXEME).where(LEXEME.ID.eq(lexemeId))))
					.execute();
		}
	}

	public void createWordsActivityLogs(Long activityLogId, Long... wordIds) {

		for (Long wordId : wordIds) {
			create
					.insertInto(WORD_ACTIVITY_LOG, WORD_ACTIVITY_LOG.WORD_ID, WORD_ACTIVITY_LOG.ACTIVITY_LOG_ID)
					.select(DSL
							.select(DSL.val(wordId), DSL.val(activityLogId))
							.whereExists(DSL.select(WORD.ID).from(WORD).where(WORD.ID.eq(wordId))))
					.execute();
		}
	}

	public void createMeaningsActivityLogs(Long activityLogId, Long... meaningIds) {

		for (Long meaningId : meaningIds) {
			create
					.insertInto(MEANING_ACTIVITY_LOG, MEANING_ACTIVITY_LOG.MEANING_ID, MEANING_ACTIVITY_LOG.ACTIVITY_LOG_ID)
					.select(DSL
							.select(DSL.val(meaningId), DSL.val(activityLogId))
							.whereExists(DSL.select(MEANING.ID).from(MEANING).where(MEANING.ID.eq(meaningId))))
					.execute();
		}
	}

	public Long createOrUpdateWordLastActivityLog(Long wordId) {

		WordActivityLog wal = WORD_ACTIVITY_LOG.as("wal");
		WordLastActivityLog wlal = WORD_LAST_ACTIVITY_LOG.as("wlal");
		ActivityLog al = ACTIVITY_LOG.as("al");

		Long activityLogId = create
				.select(al.ID)
				.from(wal, al)
				.where(
						wal.WORD_ID.eq(wordId)
						.and(wal.ACTIVITY_LOG_ID.eq(al.ID))
						.and(al.OWNER_NAME.in(ActivityOwner.WORD.name(), ActivityOwner.LEXEME.name())))
				.orderBy(al.EVENT_ON.desc())
				.limit(1)
				.fetchOptionalInto(Long.class).orElse(null);

		if (activityLogId == null) {
			return null;
		}

		create
				.update(wlal)
				.set(wlal.ACTIVITY_LOG_ID, activityLogId)
				.where(wlal.WORD_ID.eq(wordId))
				.execute();

		create
				.insertInto(wlal, wlal.WORD_ID, wlal.ACTIVITY_LOG_ID)
				.select(DSL
						.select(DSL.val(wordId), DSL.val(activityLogId))
						.whereNotExists(DSL
								.select(wlal.ID)
								.from(wlal)
								.where(wlal.WORD_ID.eq(wordId))))
				.execute();

		return activityLogId;
	}

	public void createOrUpdateMeaningLastActivityLog(Long meaningId, LastActivityType lastActivityType) {

		MeaningActivityLog mal = MEANING_ACTIVITY_LOG.as("mal");
		MeaningLastActivityLog mlal = MEANING_LAST_ACTIVITY_LOG.as("mlal");
		ActivityLog al = ACTIVITY_LOG.as("al");
		Long activityLogId = null;

		if (LastActivityType.EDIT.equals(lastActivityType)) {

			final String[] conceptActivityWordFunctNamesOfInterest = new String[] {"createWord", "updateWordValue", "deleteWord"};
			final String conceptActivityWordFunctNamesCrit = "(" + StringUtils.join(conceptActivityWordFunctNamesOfInterest, '|') + ")";

			Table<Record2<Long, Timestamp>> lmlwal = DSL
					.select(al.ID, al.EVENT_ON)
					.from(mal, al)
					.where(
							mal.MEANING_ID.eq(meaningId)
							.and(mal.ACTIVITY_LOG_ID.eq(al.ID))
							.and(al.OWNER_NAME.in(ActivityOwner.MEANING.name(), ActivityOwner.LEXEME.name()))
							.and(al.FUNCT_NAME.ne(FUNCT_NAME_APPROVE_MEANING)))
					.orderBy(al.EVENT_ON.desc())
					.limit(1)
					.unionAll(DSL
							.select(al.ID, al.EVENT_ON)
							.from(mal, al)
							.where(
									mal.MEANING_ID.eq(meaningId)
									.and(mal.ACTIVITY_LOG_ID.eq(al.ID))
									.and(al.OWNER_NAME.eq(ActivityOwner.WORD.name()))
									.and(al.ENTITY_NAME.eq(ActivityEntity.WORD.name()))
									.and(al.FUNCT_NAME.similarTo(conceptActivityWordFunctNamesCrit)))
							.orderBy(al.EVENT_ON.desc())
							.limit(1))
					.asTable("lmlwal");

			activityLogId = create
					.select(lmlwal.field("id", Long.class))
					.from(lmlwal)
					.orderBy(lmlwal.field("event_on").desc())
					.limit(1)
					.fetchOptionalInto(Long.class).orElse(null);

			// fallback compensation
			if (activityLogId == null) {
				activityLogId = create
						.select(al.ID)
						.from(mal, al)
						.where(
								mal.MEANING_ID.eq(meaningId)
								.and(mal.ACTIVITY_LOG_ID.eq(al.ID)))
						.orderBy(al.EVENT_ON.desc())
						.limit(1)
						.fetchOptionalInto(Long.class).orElse(null);
			}

		} else if (LastActivityType.APPROVE.equals(lastActivityType)) {

			activityLogId = create
					.select(al.ID)
					.from(mal, al)
					.where(
							mal.MEANING_ID.eq(meaningId)
							.and(mal.ACTIVITY_LOG_ID.eq(al.ID))
							.and(al.FUNCT_NAME.eq(FUNCT_NAME_APPROVE_MEANING)))
					.orderBy(al.EVENT_ON.desc())
					.limit(1)
					.fetchOptionalInto(Long.class).orElse(null);
		}

		if (activityLogId == null) {
			return;
		}

		create
				.update(mlal)
				.set(mlal.ACTIVITY_LOG_ID, activityLogId)
				.where(mlal.MEANING_ID.eq(meaningId).and(mlal.TYPE.eq(lastActivityType.name())))
				.execute();

		create
				.insertInto(mlal, mlal.MEANING_ID, mlal.ACTIVITY_LOG_ID, mlal.TYPE)
				.select(DSL
						.select(DSL.val(meaningId), DSL.val(activityLogId), DSL.val(lastActivityType.name()))
						.whereNotExists(DSL
								.select(mlal.ID)
								.from(mlal)
								.where(mlal.MEANING_ID.eq(meaningId).and(mlal.TYPE.eq(lastActivityType.name())))))
				.execute();
	}

	public void createSourceActivityLog(Long activityLogId, Long sourceId) {

		create
				.insertInto(SOURCE_ACTIVITY_LOG, SOURCE_ACTIVITY_LOG.SOURCE_ID, SOURCE_ACTIVITY_LOG.ACTIVITY_LOG_ID)
				.select(DSL
						.select(DSL.val(sourceId), DSL.val(activityLogId))
						.whereExists(DSL.select(SOURCE.ID).from(SOURCE).where(SOURCE.ID.eq(sourceId))))
				.execute();
	}

	public void updateWordManualEventOn(Long wordId, Timestamp eventOn) {

		create
				.update(WORD)
				.set(WORD.MANUAL_EVENT_ON, eventOn)
				.where(WORD.ID.eq(wordId))
				.execute();
	}

	public void updateMeaningManualEventOn(Long meaningId, Timestamp eventOn) {

		create
				.update(MEANING)
				.set(MEANING.MANUAL_EVENT_ON, eventOn)
				.where(MEANING.ID.eq(meaningId))
				.execute();
	}

	private TypeActivityLogDiffRecord[] convert(List<TypeActivityLogDiff> logDiffs) {
		if (CollectionUtils.isEmpty(logDiffs)) {
			return new TypeActivityLogDiffRecord[0];
		}
		int logSize = logDiffs.size();
		TypeActivityLogDiffRecord[] diffRecords = new TypeActivityLogDiffRecord[logSize];
		for (int recordIndex = 0; recordIndex < logSize; recordIndex++) {
			TypeActivityLogDiff diffObj = logDiffs.get(recordIndex);
			diffRecords[recordIndex] = new TypeActivityLogDiffRecord(diffObj.getOp(), diffObj.getPath(), diffObj.getValue());
		}
		return diffRecords;
	}

	public WordLexemeMeaningIds getLexemeMeaningIds(Long wordId) {

		Word w = WORD.as("w");
		Condition entityIdCond = w.ID.eq(wordId);
		return getWordLexemeMeaningIds(entityIdCond, w.ID);
	}

	public WordLexemeMeaningIds getWordMeaningIds(Long lexemeId) {

		Lexeme l = LEXEME.as("l");
		Condition entityIdCond = l.ID.eq(lexemeId);
		return getWordLexemeMeaningIds(entityIdCond, l.ID);
	}

	public WordLexemeMeaningIds getLexemeWordIds(Long meaningId) {

		Meaning m = MEANING.as("m");
		Condition entityIdCond = m.ID.eq(meaningId);
		return getWordLexemeMeaningIds(entityIdCond, m.ID);
	}

	private WordLexemeMeaningIds getWordLexemeMeaningIds(Condition entityIdCond, Field<Long> groupByField) {

		Word w = WORD.as("w");
		Lexeme l = LEXEME.as("l");
		Meaning m = MEANING.as("m");

		return create
				.select(
						DSL.arrayAggDistinct(l.ID).as("lexeme_ids"),
						DSL.arrayAggDistinct(w.ID).as("word_ids"),
						DSL.arrayAggDistinct(m.ID).as("meaning_ids"))
				.from(w, l, m)
				.where(
						entityIdCond
								.and(l.WORD_ID.eq(w.ID))
								.and(l.MEANING_ID.eq(m.ID)))
				.groupBy(groupByField)
				.fetchOptionalInto(WordLexemeMeaningIds.class)
				.orElse(new WordLexemeMeaningIds());
	}

	public Map<String, Object> getFirstDepthFreeformOwnerDataMap(Long freeformId) {

		LexemeFreeform lff = LEXEME_FREEFORM.as("lff");
		WordFreeform wff = WORD_FREEFORM.as("wff");
		MeaningFreeform mff = MEANING_FREEFORM.as("mff");
		SourceFreeform sff = SOURCE_FREEFORM.as("sff");
		DefinitionFreeform dff = DEFINITION_FREEFORM.as("dff");
		Definition d = DEFINITION.as("d");
		Freeform ff = FREEFORM.as("ff");

		return create.select(
				ff.TYPE,
				lff.LEXEME_ID,
				wff.WORD_ID,
				mff.MEANING_ID,
				d.MEANING_ID.as("d_meaning_id"),
				sff.SOURCE_ID)
				.from(
						ff
								.leftOuterJoin(lff).on(lff.FREEFORM_ID.eq(ff.ID))
								.leftOuterJoin(wff).on(wff.FREEFORM_ID.eq(ff.ID))
								.leftOuterJoin(mff).on(mff.FREEFORM_ID.eq(ff.ID))
								.leftOuterJoin(sff).on(sff.FREEFORM_ID.eq(ff.ID))
								.leftOuterJoin(dff).on(dff.FREEFORM_ID.eq(ff.ID))
								.leftOuterJoin(d).on(d.ID.eq(dff.DEFINITION_ID)))
				.where(ff.ID.eq(freeformId))
				.fetchOptionalMap()
				.orElse(null);
	}

	public Map<String, Object> getSecondDepthFreeformOwnerDataMap(Long freeformId) {

		LexemeFreeform lff = LEXEME_FREEFORM.as("lff");
		WordFreeform wff = WORD_FREEFORM.as("wff");
		MeaningFreeform mff = MEANING_FREEFORM.as("mff");
		SourceFreeform sff = SOURCE_FREEFORM.as("sff");
		DefinitionFreeform dff = DEFINITION_FREEFORM.as("dff");
		Definition d = DEFINITION.as("d");
		Freeform ff = FREEFORM.as("ff");

		return create.select(
				ff.TYPE,
				lff.LEXEME_ID,
				wff.WORD_ID,
				mff.MEANING_ID,
				d.MEANING_ID.as("d_meaning_id"),
				sff.SOURCE_ID)
				.from(
						ff
								.leftOuterJoin(lff).on(lff.FREEFORM_ID.eq(ff.PARENT_ID))
								.leftOuterJoin(wff).on(wff.FREEFORM_ID.eq(ff.PARENT_ID))
								.leftOuterJoin(mff).on(mff.FREEFORM_ID.eq(ff.PARENT_ID))
								.leftOuterJoin(sff).on(sff.FREEFORM_ID.eq(ff.PARENT_ID))
								.leftOuterJoin(dff).on(dff.FREEFORM_ID.eq(ff.PARENT_ID))
								.leftOuterJoin(d).on(d.ID.eq(dff.DEFINITION_ID)))
				.where(ff.ID.eq(freeformId))
				.fetchOptionalMap()
				.orElse(null);
	}

	public Long getWordRelationOwnerId(Long wordRelationId) {
		return create.select(WORD_RELATION.WORD1_ID).from(WORD_RELATION).where(WORD_RELATION.ID.eq(wordRelationId)).fetchOptionalInto(Long.class).orElse(null);
	}

	public Long getWordTypeOwnerId(Long wordTypeId) {
		return create.select(WORD_WORD_TYPE.WORD_ID).from(WORD_WORD_TYPE).where(WORD_WORD_TYPE.ID.eq(wordTypeId)).fetchOptionalInto(Long.class).orElse(null);
	}

	public Long getWordEtymologyOwnerId(Long entityId) {
		return create.select(WORD_ETYMOLOGY.WORD_ID).from(WORD_ETYMOLOGY).where(WORD_ETYMOLOGY.ID.eq(entityId)).fetchOptionalInto(Long.class).orElse(null);
	}

	public Long getMeaningDomainOwnerId(Long meaningDomainId) {
		return create.select(MEANING_DOMAIN.MEANING_ID).from(MEANING_DOMAIN).where(MEANING_DOMAIN.ID.eq(meaningDomainId)).fetchOptionalInto(Long.class).orElse(null);
	}

	public Long getMeaningDefinitionOwnerId(Long definitionId) {
		return create.select(DEFINITION.MEANING_ID).from(DEFINITION).where(DEFINITION.ID.eq(definitionId)).fetchOptionalInto(Long.class).orElse(null);
	}

	public Long getLexemeRelationOwnerId(Long lexemeRelationId) {
		return create.select(LEX_RELATION.LEXEME1_ID).from(LEX_RELATION).where(LEX_RELATION.ID.eq(lexemeRelationId)).fetchOptionalInto(Long.class).orElse(null);
	}

	public Long getMeaningRelationOwnerId(Long meaningRelationId) {
		return create.select(MEANING_RELATION.MEANING1_ID).from(MEANING_RELATION).where(MEANING_RELATION.ID.eq(meaningRelationId)).fetchOptionalInto(Long.class).orElse(null);
	}

	public Long getFreeformSourceLinkFreeformId(Long sourceLinkId) {
		return create.select(FREEFORM_SOURCE_LINK.FREEFORM_ID).from(FREEFORM_SOURCE_LINK).where(FREEFORM_SOURCE_LINK.ID.eq(sourceLinkId)).fetchOptionalInto(Long.class).orElse(null);
	}

	public Long getLexemeSourceLinkOwnerId(Long sourceLinkId) {
		return create.select(LEXEME_SOURCE_LINK.LEXEME_ID).from(LEXEME_SOURCE_LINK).where(LEXEME_SOURCE_LINK.ID.eq(sourceLinkId)).fetchOptionalInto(Long.class).orElse(null);
	}

	public Long getDefinitionSourceLinkOwnerId(Long sourceLinkId) {
		return create
				.select(DEFINITION.MEANING_ID)
				.from(DEFINITION, DEFINITION_SOURCE_LINK)
				.where(DEFINITION_SOURCE_LINK.ID.eq(sourceLinkId).and(DEFINITION_SOURCE_LINK.DEFINITION_ID.eq(DEFINITION.ID)))
				.fetchOptionalInto(Long.class).orElse(null);
	}

	public Long getParadigmOwnerId(Long paradigmId) {
		return create.select(PARADIGM.WORD_ID).from(PARADIGM).where(PARADIGM.ID.eq(paradigmId)).fetchOptionalInto(Long.class).orElse(null);
	}

	public Timestamp getMeaningLastActivityLog(Long meaningId, LastActivityType lastActivityType) {
		return create
				.select(ACTIVITY_LOG.EVENT_ON)
				.from(MEANING_LAST_ACTIVITY_LOG, ACTIVITY_LOG)
				.where(
						MEANING_LAST_ACTIVITY_LOG.MEANING_ID.eq(meaningId)
						.and(MEANING_LAST_ACTIVITY_LOG.ACTIVITY_LOG_ID.eq(ACTIVITY_LOG.ID))
						.and(MEANING_LAST_ACTIVITY_LOG.TYPE.eq(lastActivityType.name())))
				.fetchOptionalInto(Timestamp.class)
				.orElse(null);
	}

	public void deleteMeaningLastActivityLog(Long meaningId, LastActivityType lastActivityType) {
		create
			.deleteFrom(MEANING_LAST_ACTIVITY_LOG)
			.where(
					MEANING_LAST_ACTIVITY_LOG.MEANING_ID.eq(meaningId)
					.and(MEANING_LAST_ACTIVITY_LOG.TYPE.eq(lastActivityType.name())))
			.execute();
	}

	public void moveMeaningLastActivityLog(Long targetMeaningId, Long sourceMeaningId, LastActivityType lastActivityType) {
		create
			.update(MEANING_LAST_ACTIVITY_LOG)
			.set(MEANING_LAST_ACTIVITY_LOG.MEANING_ID, targetMeaningId)
			.where(
					MEANING_LAST_ACTIVITY_LOG.MEANING_ID.eq(sourceMeaningId)
					.and(MEANING_LAST_ACTIVITY_LOG.TYPE.eq(lastActivityType.name())))
			.execute();
	}
}
