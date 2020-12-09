package eki.ekilex.service.db;

import static eki.ekilex.data.db.Tables.DEFINITION;
import static eki.ekilex.data.db.Tables.LEXEME;
import static eki.ekilex.data.db.Tables.LEXEME_DERIV;
import static eki.ekilex.data.db.Tables.LEXEME_LIFECYCLE_LOG;
import static eki.ekilex.data.db.Tables.LEXEME_POS;
import static eki.ekilex.data.db.Tables.LEXEME_REGION;
import static eki.ekilex.data.db.Tables.LEXEME_REGISTER;
import static eki.ekilex.data.db.Tables.LIFECYCLE_LOG;
import static eki.ekilex.data.db.Tables.MEANING_DOMAIN;
import static eki.ekilex.data.db.Tables.MEANING_FREEFORM;
import static eki.ekilex.data.db.Tables.MEANING_LIFECYCLE_LOG;
import static eki.ekilex.data.db.Tables.MEANING_SEMANTIC_TYPE;
import static eki.ekilex.data.db.Tables.SOURCE_LIFECYCLE_LOG;
import static eki.ekilex.data.db.Tables.WORD_LIFECYCLE_LOG;
import static eki.ekilex.data.db.Tables.WORD_WORD_TYPE;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eki.common.constant.FreeformType;
import eki.common.constant.GlobalConstant;
import eki.common.constant.LifecycleEntity;
import eki.common.constant.LifecycleEventType;
import eki.common.constant.LifecycleProperty;
import eki.ekilex.data.Government;
import eki.ekilex.data.ListData;
import eki.ekilex.data.LogData;
import eki.ekilex.data.db.tables.records.DefinitionRecord;
import eki.ekilex.service.db.util.LifecycleLogDbServiceHelper;

@Deprecated
@Component
public class LifecycleLogDbService implements GlobalConstant {

	@Autowired
	private DSLContext create;

	@Autowired
	private CommonDataDbService commonDataDbService;

	@Autowired
	private LifecycleLogDbServiceHelper helper;

	public void createLog(LogData logData) {

		LifecycleEventType eventType = logData.getEventType();
		LifecycleEntity entity = logData.getEntityName();
		Long entityId = logData.getEntityId();
		LifecycleProperty property = logData.getProperty();

		if (LifecycleEntity.LEXEME.equals(entity)) {
			createLexemeLog(logData);
		} else if (LifecycleEntity.DEFINITION.equals(entity)) {
			createDefinitionLog(logData);
		} else if (LifecycleEntity.WORD.equals(entity)) {
			createWordLog(logData);
		} else if (LifecycleEntity.MEANING.equals(entity)) {
			createMeaningLog(logData);
		} else if (LifecycleEntity.SOURCE.equals(entity)) {
			createSourceLog(logData);
		} else if (LifecycleEntity.USAGE.equals(entity)) {
			createUsageLog(logData);
		} else if (LifecycleEntity.USAGE_TRANSLATION.equals(entity)) {
			if (LifecycleProperty.VALUE.equals(property)) {
				Map<String, Object> entityData = helper.getSecondDepthFreeformData(create, entityId, FreeformType.USAGE_TRANSLATION);
				String recent = (String) entityData.get("value_prese");
				boolean isRecentValueValid = validateAndSetRecentValue(logData, recent);
				if (!isRecentValueValid) {
					return;
				}
				Long lexemeId = (Long) entityData.get("lexeme_id");
				Long lifecycleLogId = createLifecycleLog(logData);
				createLexemeLifecycleLog(lexemeId, lifecycleLogId);
			}
		} else if (LifecycleEntity.USAGE_DEFINITION.equals(entity)) {
			if (LifecycleProperty.VALUE.equals(property)) {
				Map<String, Object> entityData = helper.getSecondDepthFreeformData(create, entityId, FreeformType.USAGE_DEFINITION);
				String recent = (String) entityData.get("value_prese");
				boolean isRecentValueValid = validateAndSetRecentValue(logData, recent);
				if (!isRecentValueValid) {
					return;
				}
				Long lexemeId = (Long) entityData.get("lexeme_id");
				Long lifecycleLogId = createLifecycleLog(logData);
				createLexemeLifecycleLog(lexemeId, lifecycleLogId);
			}
		} else if (LifecycleEntity.GOVERNMENT.equals(entity)) {
			if (LifecycleProperty.VALUE.equals(property)) {
				Map<String, Object> entityData = helper.getFirstDepthFreeformData(create, entityId, FreeformType.GOVERNMENT);
				String recent = (String) entityData.get("value_prese");
				boolean isRecentValueValid = validateAndSetRecentValue(logData, recent);
				if (!isRecentValueValid) {
					return;
				}
				Long lexemeId = (Long) entityData.get("lexeme_id");
				Long lifecycleLogId = createLifecycleLog(logData);
				createLexemeLifecycleLog(lexemeId, lifecycleLogId);
			}
		} else if (LifecycleEntity.GRAMMAR.equals(entity)) {
			if (LifecycleProperty.VALUE.equals(property)) {
				Map<String, Object> entityData = helper.getFirstDepthFreeformData(create, entityId, FreeformType.GRAMMAR);
				String recent = (String) entityData.get("value_prese");
				boolean isRecentValueValid = validateAndSetRecentValue(logData, recent);
				if (!isRecentValueValid) {
					return;
				}
				Long lexemeId = (Long) entityData.get("lexeme_id");
				Long lifecycleLogId = createLifecycleLog(logData);
				createLexemeLifecycleLog(lexemeId, lifecycleLogId);
			}
		} else if (LifecycleEntity.WORD_RELATION.equals(entity)) {
			if (LifecycleProperty.VALUE.equals(property)) {
				Map<String, Object> entityData = helper.getWordRelationData(create, entityId);
				Map<String, Object> relatedWordData = helper.getWordData(create, (Long) entityData.get("word2_id"));
				String logString = entityData.get("word_rel_type_code") + " -> " + relatedWordData.get("value");
				Long lifecycleLogId;
				if (LifecycleEventType.DELETE == eventType) {
					logData.setRecent(logString);
					lifecycleLogId = createLifecycleLog(logData);
				} else {
					logData.setEntry(logString);
					lifecycleLogId = createLifecycleLog(logData);
				}
				Long wordId = (Long) entityData.get("word1_id");
				createWordLifecycleLog(wordId, lifecycleLogId);
			} else if (LifecycleProperty.STATUS.equals(property)) {
				Map<String, Object> entityData = helper.getWordRelationData(create, entityId);
				String recent = (String) entityData.get("relation_status");
				boolean isRecentValueValid = validateAndSetRecentValue(logData, recent);
				if (!isRecentValueValid) {
					return;
				}
				Long wordId = (Long) entityData.get("word1_id");
				Long lifecycleLogId = createLifecycleLog(logData);
				createWordLifecycleLog(wordId, lifecycleLogId);
			}
		} else if (LifecycleEntity.WORD_RELATION_GROUP_MEMBER.equals(entity)) {
			Map<String, Object> memberData = helper.getWordRelationGroupMember(create, entityId);
			Long lifecycleLogId = createLifecycleLog(logData);
			Long wordId = (Long) memberData.get("word_id");
			createWordLifecycleLog(wordId, lifecycleLogId);
		} else if (LifecycleEntity.LEXEME_RELATION.equals(entity)) {
			Map<String, Object> entityData = helper.getLexemeRelationData(create, entityId);
			Map<String, Object> relatedWordData = helper.getWordData(create, (Long) entityData.get("word2_id"));
			String logString = entityData.get("lex_rel_type_code") + " -> " + relatedWordData.get("value");
			Long lifecycleLogId;
			if (LifecycleEventType.DELETE == eventType) {
				logData.setRecent(logString);
				lifecycleLogId = createLifecycleLog(logData);
			} else {
				logData.setEntry(logString);
				lifecycleLogId = createLifecycleLog(logData);
			}
			Long lexemeId = (Long) entityData.get("lexeme1_id");
			createLexemeLifecycleLog(lexemeId, lifecycleLogId);
		} else if (LifecycleEntity.MEANING_RELATION.equals(entity)) {
			Map<String, Object> entityData = helper.getMeaningRelationData(create, entityId);
			List<String> meaningWords = helper.getMeaningWords(create, (Long) entityData.get("meaning2_id"));
			String logString = entityData.get("meaning_rel_type_code") + " -> " + String.join(",", meaningWords);
			Long lifecycleLogId;
			if (LifecycleEventType.DELETE == eventType) {
				logData.setRecent(logString);
				lifecycleLogId = createLifecycleLog(logData);
			} else {
				logData.setEntry(logString);
				lifecycleLogId = createLifecycleLog(logData);
			}
			Long meaningId = (Long) entityData.get("meaning1_id");
			createMeaningLifecycleLog(meaningId, lifecycleLogId);
		} else if (LifecycleEntity.LEARNER_COMMENT.equals(entity)) {
			if (LifecycleProperty.VALUE.equals(property)) {
				Map<String, Object> entityData = helper.getMeaningFreeformData(create, entityId, FreeformType.LEARNER_COMMENT);
				String recent = (String) entityData.get("value_prese");
				boolean isRecentValueValid = validateAndSetRecentValue(logData, recent);
				if (!isRecentValueValid) {
					return;
				}
				Long meaningId = (Long) entityData.get("meaning_id");
				Long lifecycleLogId = createLifecycleLog(logData);
				createMeaningLifecycleLog(meaningId, lifecycleLogId);
			}
		}
	}

	private void createWordLog(LogData logData) {

		LifecycleEventType eventType = logData.getEventType();
		Long entityId = logData.getEntityId();
		LifecycleProperty property = logData.getProperty();

		if (LifecycleProperty.VALUE.equals(property)) {
			if (LifecycleEventType.UPDATE == eventType || LifecycleEventType.DELETE == eventType) {
				Map<String, Object> entityData = helper.getWordData(create, entityId);
				String recent = entityData.get("value_prese").toString();
				boolean isRecentValueValid = validateAndSetRecentValue(logData, recent);
				if (!isRecentValueValid) {
					return;
				}
			}
			Long lifecycleLogId = createLifecycleLog(logData);
			createWordLifecycleLog(entityId, lifecycleLogId);
		} else if (LifecycleProperty.WORD_TYPE.equals(property)) {
			Long wordId = create
					.select(WORD_WORD_TYPE.WORD_ID)
					.from(WORD_WORD_TYPE)
					.where(WORD_WORD_TYPE.ID.eq(entityId))
					.fetchSingleInto(Long.class);
			logData.setEntityId(wordId);
			Long lifecycleLogId = createLifecycleLog(logData);
			createWordLifecycleLog(wordId, lifecycleLogId);
		} else if (LifecycleProperty.GENDER.equals(property)) {
			Map<String, Object> entityData = helper.getWordData(create, entityId);
			String recent = (String) entityData.get("gender_code");
			logData.setRecent(recent);
			Long lifecycleLogId = createLifecycleLog(logData);
			createWordLifecycleLog(entityId, lifecycleLogId);
		} else if (LifecycleProperty.ASPECT.equals(property)) {
			Map<String, Object> entityData = helper.getWordData(create, entityId);
			String recent = (String) entityData.get("aspect_code");
			logData.setRecent(recent);
			Long lifecycleLogId = createLifecycleLog(logData);
			createWordLifecycleLog(entityId, lifecycleLogId);
		} else if (LifecycleProperty.DISPLAY_MORPH_CODE.equals(property)) {
			Map<String, Object> entityData = helper.getWordData(create, entityId);
			String recent = (String) entityData.get("display_morph_code");
			logData.setRecent(recent);
			Long lifecycleLogId = createLifecycleLog(logData);
			createWordLifecycleLog(entityId, lifecycleLogId);
		}else if (LifecycleProperty.VOCAL_FORM.equals(property)) {
			Map<String, Object> entityData = helper.getWordData(create, entityId);
			String recent = (String) entityData.get("vocal_form");
			logData.setRecent(recent);
			Long lifecycleLogId = createLifecycleLog(logData);
			createWordLifecycleLog(entityId, lifecycleLogId);
		} else if (LifecycleProperty.MORPH_CODE.equals(property)) {
			Map<String, Object> entityData = helper.getWordData(create, entityId);
			String recent = (String) entityData.get("morph_code");
			boolean isRecentValueValid = validateAndSetRecentValue(logData, recent);
			if (!isRecentValueValid) {
				return;
			}
			Long lifecycleLogId = createLifecycleLog(logData);
			createWordLifecycleLog(entityId, lifecycleLogId);
		} else if (LifecycleProperty.LANG.equals(property)) {
			Map<String, Object> entityData = helper.getWordData(create, entityId);
			String recent = (String) entityData.get("lang");
			logData.setRecent(recent);
			Long lifecycleLogId = createLifecycleLog(logData);
			createWordLifecycleLog(entityId, lifecycleLogId);
		} else if (LifecycleProperty.OD_RECOMMENDATION.equals(property)) {
			Map<String, Object> entityData = helper.getWordFreeformData(create, entityId, FreeformType.OD_WORD_RECOMMENDATION);
			String recent = (String) entityData.get("value_prese");
			boolean isRecentValueValid = validateAndSetRecentValue(logData, recent);
			if (!isRecentValueValid) {
				return;
			}
			Long wordId = (Long) entityData.get("word_id");
			logData.setEntityId(wordId);
			Long lifecycleLogId = createLifecycleLog(logData);
			createWordLifecycleLog(wordId, lifecycleLogId);
		} else if (LifecycleProperty.NOTE.equals(property)) {
			Map<String, Object> entityData = helper.getWordFreeformData(create, entityId, FreeformType.NOTE);
			String recent = (String) entityData.get("value_prese");
			boolean isRecentValueValid = validateAndSetRecentValue(logData, recent);
			if (!isRecentValueValid) {
				return;
			}
			Long wordId = (Long) entityData.get("word_id");
			logData.setEntityId(wordId);
			Long lifecycleLogId = createLifecycleLog(logData);
			createWordLifecycleLog(wordId, lifecycleLogId);
		}
	}

	private void createLexemeLog(LogData logData) {

		LifecycleEventType eventType = logData.getEventType();
		Long entityId = logData.getEntityId();
		LifecycleProperty property = logData.getProperty();

		if (LifecycleProperty.COMPLEXITY.equals(property)) {
			Map<String, Object> entityData = helper.getLexemeData(create, entityId);
			String recent = (String) entityData.get("complexity");
			logData.setRecent(recent);
			Long lifecycleLogId = createLifecycleLog(logData);
			createLexemeLifecycleLog(entityId, lifecycleLogId);
		} else if (LifecycleProperty.POS.equals(property)) {
			Long lexemeId = create
					.select(LEXEME_POS.LEXEME_ID)
					.from(LEXEME_POS)
					.where(LEXEME_POS.ID.eq(entityId))
					.fetchSingleInto(Long.class);
			logData.setEntityId(lexemeId);
			Long lifecycleLogId = createLifecycleLog(logData);
			createLexemeLifecycleLog(lexemeId, lifecycleLogId);
		} else if (LifecycleProperty.DERIV.equals(property)) {
			Long lexemeId = create
					.select(LEXEME_DERIV.LEXEME_ID)
					.from(LEXEME_DERIV)
					.where(LEXEME_DERIV.ID.eq(entityId))
					.fetchSingleInto(Long.class);
			logData.setEntityId(lexemeId);
			Long lifecycleLogId = createLifecycleLog(logData);
			createLexemeLifecycleLog(lexemeId, lifecycleLogId);
		} else if (LifecycleProperty.REGISTER.equals(property)) {
			Long lexemeId = create
					.select(LEXEME_REGISTER.LEXEME_ID)
					.from(LEXEME_REGISTER)
					.where(LEXEME_REGISTER.ID.eq(entityId))
					.fetchSingleInto(Long.class);
			logData.setEntityId(lexemeId);
			Long lifecycleLogId = createLifecycleLog(logData);
			createLexemeLifecycleLog(lexemeId, lifecycleLogId);
		} else if (LifecycleProperty.REGION.equals(property)) {
			Long lexemeId = create
					.select(LEXEME_REGION.LEXEME_ID)
					.from(LEXEME_REGION)
					.where(LEXEME_REGION.ID.eq(entityId))
					.fetchSingleInto(Long.class);
			logData.setEntityId(lexemeId);
			Long lifecycleLogId = createLifecycleLog(logData);
			createLexemeLifecycleLog(lexemeId, lifecycleLogId);
		} else if (LifecycleProperty.LEVEL.equals(property)) {
			Map<String, Object> entityData = helper.getLexemeData(create, entityId);
			String recent = StringUtils.joinWith(".", entityData.get("level1"), entityData.get("level2"));
			logData.setRecent(recent);
			if (!logData.isValueChanged()) {
				if (logData.isUpdateEvent()) {
					return;
				}
			}
			Long lifecycleLogId = createLifecycleLog(logData);
			createLexemeLifecycleLog(entityId, lifecycleLogId);
		} else if (LifecycleProperty.DATASET.equals(property)) {
			Long lifecycleLogId = createLifecycleLog(logData);
			createLexemeLifecycleLog(entityId, lifecycleLogId);
		} else if (LifecycleProperty.VALUE.equals(property)) {
			Long lifecycleLogId;
			if (LifecycleEventType.DELETE == eventType) {
				Map<String, Object> entityData = helper.getLexemeData(create, entityId);
				Map<String, Object> usageData = helper.getLexemeUsageData(create, entityId);
				String logString = lexemeLogString(entityData) + (Objects.equals("null", usageData.get("value_text")) ? "" : " " + usageData.get("value_text"));
				logData.setRecent(logString);
				lifecycleLogId = createLifecycleLog(logData);
			} else {
				lifecycleLogId = createLifecycleLog(logData);
			}
			createLexemeLifecycleLog(entityId, lifecycleLogId);
		} else if (LifecycleProperty.VALUE_STATE.equals(property)) {
			Map<String, Object> entityData = helper.getLexemeData(create, entityId);
			String recent = (String) entityData.get("value_state_code");
			logData.setRecent(recent);
			Long lifecycleLogId = createLifecycleLog(logData);
			createLexemeLifecycleLog(entityId, lifecycleLogId);
		} else if (LifecycleProperty.OD_RECOMMENDATION.equals(property)) {
			Map<String, Object> entityData = helper.getFirstDepthFreeformData(create, entityId, FreeformType.OD_LEXEME_RECOMMENDATION);
			String recent = (String) entityData.get("value_prese");
			boolean isRecentValueValid = validateAndSetRecentValue(logData, recent);
			if (!isRecentValueValid) {
				return;
			}
			Long lexemeId = (Long) entityData.get("lexeme_id");
			logData.setEntityId(lexemeId);
			Long lifecycleLogId = createLifecycleLog(logData);
			createLexemeLifecycleLog(lexemeId, lifecycleLogId);
		} else if (LifecycleProperty.SOURCE_LINK.equals(property)) {
			Map<String, Object> entityData = helper.getLexemeSourceLinkData(create, entityId);
			String recent = (String) entityData.get("value");
			boolean isRecentValueValid = validateAndSetRecentValue(logData, recent);
			if (!isRecentValueValid) {
				return;
			}
			Long lexemeId = (Long) entityData.get("lexeme_id");
			logData.setEntityId(lexemeId);
			Long lifecycleLogId = createLifecycleLog(logData);
			createLexemeLifecycleLog(lexemeId, lifecycleLogId);
		} else if (LifecycleProperty.FREEFORM_SOURCE_LINK.equals(property)) {
			Map<String, Object> entityData = helper.getLexemeFreeformSourceLinkData(create, entityId);
			String recent = (String) entityData.get("value");
			boolean isRecentValueValid = validateAndSetRecentValue(logData, recent);
			if (!isRecentValueValid) {
				return;
			}
			Long lexemeId = (Long) entityData.get("lexeme_id");
			logData.setEntityId(lexemeId);
			Long lifecycleLogId = createLifecycleLog(logData);
			createLexemeLifecycleLog(lexemeId, lifecycleLogId);
		} else if (LifecycleProperty.NOTE.equals(property)) {
			Map<String, Object> entityData = helper.getFirstDepthFreeformData(create, entityId, FreeformType.NOTE);
			String recent = (String) entityData.get("value_prese");
			boolean isRecentValueValid = validateAndSetRecentValue(logData, recent);
			if (!isRecentValueValid) {
				return;
			}
			Long lexemeId = (Long) entityData.get("lexeme_id");
			logData.setEntityId(lexemeId);
			Long lifecycleLogId = createLifecycleLog(logData);
			createLexemeLifecycleLog(lexemeId, lifecycleLogId);
		} else if (LifecycleProperty.MEANING_WORD.equals(property)) {
			if (LifecycleEventType.CREATE.equals(eventType)) {
				Long meaningId = create
						.select(LEXEME.MEANING_ID)
						.from(LEXEME)
						.where(LEXEME.ID.eq(entityId))
						.fetchSingleInto(Long.class);
				Long lifecycleLogId = createLifecycleLog(logData);
				createMeaningLifecycleLog(meaningId, lifecycleLogId);
			} else if (LifecycleEventType.ORDER_BY.equals(eventType)) {
				Long lifecycleLogId = createLifecycleLog(logData);
				createLexemeLifecycleLog(entityId, lifecycleLogId);
			}
		} else if (LifecycleProperty.WEIGHT.equals(property)) {
			Map<String, Object> entityData = helper.getLexemeData(create, entityId);
			BigDecimal recentWeight = (BigDecimal) entityData.get("weight");
			BigDecimal newWeight = new BigDecimal(logData.getEntry());
			if (recentWeight.compareTo(newWeight) == 0) {
				if (logData.isUpdateEvent()) {
					return;
				}
			}
			String recent = String.valueOf(recentWeight);
			logData.setRecent(recent);
			Long lifecycleLogId = createLifecycleLog(logData);
			createLexemeLifecycleLog(entityId, lifecycleLogId);
		} else if (LifecycleProperty.PUBLICITY.equals(property)) {
			Map<String, Object> entityData = helper.getLexemeData(create, entityId);
			String recent = entityData.get("is_public").toString();
			boolean isRecentValueValid = validateAndSetRecentValue(logData, recent);
			if (!isRecentValueValid) {
				return;
			}
			Long lifecycleLogId = createLifecycleLog(logData);
			createLexemeLifecycleLog(entityId, lifecycleLogId);
		} else if (LifecycleProperty.TAG.equals(property)) {
			Long lifecycleLogId = createLifecycleLog(logData);
			createLexemeLifecycleLog(entityId, lifecycleLogId);
		}
	}

	private void createMeaningLog(LogData logData) {

		Long entityId = logData.getEntityId();
		LifecycleProperty property = logData.getProperty();

		if (LifecycleProperty.DOMAIN.equals(property)) {
			Long meaningId = create
					.select(MEANING_DOMAIN.MEANING_ID)
					.from(MEANING_DOMAIN)
					.where(MEANING_DOMAIN.ID.eq(entityId))
					.fetchSingleInto(Long.class);
			logData.setEntityId(meaningId);
			Long lifecycleLogId = createLifecycleLog(logData);
			createMeaningLifecycleLog(meaningId, lifecycleLogId);
		} else if (LifecycleProperty.VALUE.equals(property)) {
			Long lifecycleLogId = createLifecycleLog(logData);
			createMeaningLifecycleLog(entityId, lifecycleLogId);
		}  else if (LifecycleProperty.IMAGE_TITLE.equals(property)) {
			Long meaningId = create
					.select(MEANING_FREEFORM.MEANING_ID)
					.from(MEANING_FREEFORM)
					.where(MEANING_FREEFORM.FREEFORM_ID.eq(entityId))
					.fetchSingleInto(Long.class);
			logData.setEntityId(meaningId);
			Long lifecycleLogId = createLifecycleLog(logData);
			createMeaningLifecycleLog(meaningId, lifecycleLogId);
		}  else if (LifecycleProperty.IMAGE.equals(property)) {
			Long meaningId = create
					.select(MEANING_FREEFORM.MEANING_ID)
					.from(MEANING_FREEFORM)
					.where(MEANING_FREEFORM.FREEFORM_ID.eq(entityId))
					.fetchSingleInto(Long.class);
			logData.setEntityId(meaningId);
			Long lifecycleLogId = createLifecycleLog(logData);
			createMeaningLifecycleLog(meaningId, lifecycleLogId);
		} else if (LifecycleProperty.SEMANTIC_TYPE.equals(property)) {
			Long meaningId = create
					.select(MEANING_SEMANTIC_TYPE.MEANING_ID)
					.from(MEANING_SEMANTIC_TYPE)
					.where(MEANING_SEMANTIC_TYPE.ID.eq(entityId))
					.fetchSingleInto(Long.class);
			logData.setEntityId(meaningId);
			Long lifecycleLogId = createLifecycleLog(logData);
			createMeaningLifecycleLog(meaningId, lifecycleLogId);
		} else if (LifecycleProperty.FREEFORM_SOURCE_LINK.equals(property)) {
			Map<String, Object> entityData = helper.getMeaningFreeformSourceLinkData(create, entityId);
			String recent = (String) entityData.get("value");
			boolean isRecentValueValid = validateAndSetRecentValue(logData, recent);
			if (!isRecentValueValid) {
				return;
			}
			Long meaningId = (Long) entityData.get("meaning_id");
			logData.setEntityId(meaningId);
			Long lifecycleLogId = createLifecycleLog(logData);
			createMeaningLifecycleLog(meaningId, lifecycleLogId);
		} else if (LifecycleProperty.NOTE.equals(property)) {
			Map<String, Object> entityData = helper.getMeaningFreeformData(create, entityId, FreeformType.NOTE);
			String recent = (String) entityData.get("value_prese");
			boolean isRecentValueValid = validateAndSetRecentValue(logData, recent);
			if (!isRecentValueValid) {
				return;
			}
			Long meaningId = (Long) entityData.get("meaning_id");
			logData.setEntityId(meaningId);
			Long lifecycleLogId = createLifecycleLog(logData);
			createMeaningLifecycleLog(meaningId, lifecycleLogId);
		}
	}

	private void createDefinitionLog(LogData logData) {

		Long entityId = logData.getEntityId();
		LifecycleProperty property = logData.getProperty();

		if (LifecycleProperty.VALUE.equals(property)) {
			Map<String, Object> entityData = helper.getDefinitionData(create, entityId);
			String recent = (String) entityData.get("value_prese");
			boolean isRecentValueValid = validateAndSetRecentValue(logData, recent);
			if (!isRecentValueValid) {
				return;
			}
			Long meaningId = (Long) entityData.get("meaning_id");
			Long lifecycleLogId = createLifecycleLog(logData);
			createMeaningLifecycleLog(meaningId, lifecycleLogId);
		} else if (LifecycleProperty.SOURCE_LINK.equals(property)) {
			Map<String, Object> entityData = helper.getDefinitionSourceLinkData(create, entityId);
			String recent = (String) entityData.get("value");
			boolean isRecentValueValid = validateAndSetRecentValue(logData, recent);
			if (!isRecentValueValid) {
				return;
			}
			Long meaningId = (Long) entityData.get("meaning_id");
			Long definitionId = (Long) entityData.get("definition_id");
			logData.setEntityId(definitionId);
			Long lifecycleLogId = createLifecycleLog(logData);
			createMeaningLifecycleLog(meaningId, lifecycleLogId);
		} else if (LifecycleProperty.FREEFORM_SOURCE_LINK.equals(property)) {
			Map<String, Object> entityData = helper.getDefinitionFreeformSourceLinkData(create, entityId);
			String recent = (String) entityData.get("value");
			boolean isRecentValueValid = validateAndSetRecentValue(logData, recent);
			if (!isRecentValueValid) {
				return;
			}
			Long meaningId = (Long) entityData.get("meaning_id");
			Long definitionId = (Long) entityData.get("definition_id");
			logData.setEntityId(definitionId);
			Long lifecycleLogId = createLifecycleLog(logData);
			createMeaningLifecycleLog(meaningId, lifecycleLogId);
		} else if (LifecycleProperty.NOTE.equals(property)) {
			Map<String, Object> entityData = helper.getDefinitionFreeformData(create, entityId, FreeformType.NOTE);
			String recent = (String) entityData.get("value_prese");
			boolean isRecentValueValid = validateAndSetRecentValue(logData, recent);
			if (!isRecentValueValid) {
				return;
			}
			Long meaningId = (Long) entityData.get("meaning_id");
			Long definitionId = (Long) entityData.get("definition_id");
			logData.setEntityId(definitionId);
			Long lifecycleLogId = createLifecycleLog(logData);
			createMeaningLifecycleLog(meaningId, lifecycleLogId);
		}
	}

	private void createUsageLog(LogData logData) {

		Long entityId = logData.getEntityId();
		LifecycleProperty property = logData.getProperty();

		if (LifecycleProperty.VALUE.equals(property)) {
			Map<String, Object> entityData = helper.getFirstDepthFreeformData(create, entityId, FreeformType.USAGE);
			String recent = (String) entityData.get("value_prese");
			boolean isRecentValueValid = validateAndSetRecentValue(logData, recent);
			if (!isRecentValueValid) {
				return;
			}
			Long lexemeId = (Long) entityData.get("lexeme_id");
			Long lifecycleLogId = createLifecycleLog(logData);
			createLexemeLifecycleLog(lexemeId, lifecycleLogId);
		} else if (LifecycleProperty.OD_DEFINITION.equals(property)) {
			Map<String, Object> entityData = helper.getSecondDepthFreeformData(create, entityId, FreeformType.OD_USAGE_DEFINITION);
			String recent = (String) entityData.get("value_prese");
			boolean isRecentValueValid = validateAndSetRecentValue(logData, recent);
			if (!isRecentValueValid) {
				return;
			}
			Long lexemeId = (Long) entityData.get("lexeme_id");
			Long usageId = (Long) entityData.get("parent_id");
			logData.setEntityId(usageId);
			Long lifecycleLogId = createLifecycleLog(logData);
			createLexemeLifecycleLog(lexemeId, lifecycleLogId);
		} else if (LifecycleProperty.OD_ALTERNATIVE.equals(property)) {
			Map<String, Object> entityData = helper.getSecondDepthFreeformData(create, entityId, FreeformType.OD_USAGE_ALTERNATIVE);
			String recent = (String) entityData.get("value_prese");
			boolean isRecentValueValid = validateAndSetRecentValue(logData, recent);
			if (!isRecentValueValid) {
				return;
			}
			Long lexemeId = (Long) entityData.get("lexeme_id");
			Long usageId = (Long) entityData.get("parent_id");
			logData.setEntityId(usageId);
			Long lifecycleLogId = createLifecycleLog(logData);
			createLexemeLifecycleLog(lexemeId, lifecycleLogId);
		} else if (LifecycleProperty.SOURCE_LINK.equals(property)) {
			Map<String, Object> entityData = helper.getUsageSourceLinkData(create, entityId);
			String recent = (String) entityData.get("value");
			boolean isRecentValueValid = validateAndSetRecentValue(logData, recent);
			if (!isRecentValueValid) {
				return;
			}
			Long lexemeId = (Long) entityData.get("lexeme_id");
			Long usageId = (Long) entityData.get("usage_id");
			logData.setEntityId(usageId);
			Long lifecycleLogId = createLifecycleLog(logData);
			createLexemeLifecycleLog(lexemeId, lifecycleLogId);
		}
	}

	private boolean validateAndSetRecentValue(LogData logData, String recent) {

		logData.setRecent(recent);
		if (!logData.isValueChanged()) {
			if (logData.isUpdateEvent()) {
				return false;
			}
			logData.setRecent(null);
		}
		return true;
	}

	private void createSourceLog(LogData logData) {

		LifecycleEventType eventType = logData.getEventType();
		Long entityId = logData.getEntityId();
		LifecycleProperty property = logData.getProperty();

		if (LifecycleProperty.SOURCE_TYPE.equals(property)) {
			if (LifecycleEventType.UPDATE == eventType) {
				Map<String, Object> entityData = helper.getSourceType(create, entityId);
				String recent = (String) entityData.get("type");
				logData.setRecent(recent);
			}
		} else if (!LifecycleProperty.VALUE.equals(property)) {
			if (LifecycleEventType.UPDATE == eventType || LifecycleEventType.DELETE == eventType) {
				FreeformType freeformType = FreeformType.valueOf(property.name());
				Map<String, Object> entityData = helper.getSourceFreeformData(create, entityId, freeformType);
				String recent = (String) entityData.get("value");
				Long sourceId = (Long) entityData.get("source_id");
				logData.setRecent(recent);
				entityId = sourceId;
				logData.setEntityId(entityId);
				if (!logData.isValueChanged()) {
					if (logData.isUpdateEvent()) {
						return;
					}
					logData.setRecent(null);
				}
			}
		}
		Long lifecycleLogId = createLifecycleLog(logData);
		createSourceLifecycleLog(entityId, lifecycleLogId);
	}

	public void createListOrderingLog(LogData logData) {

		LifecycleEventType eventType = logData.getEventType();
		if (!LifecycleEventType.ORDER_BY.equals(eventType)) {
			return;
		}

		LifecycleEntity entity = logData.getEntityName();
		LifecycleProperty property = logData.getProperty();
		ListData listData = logData.getListData();
		Long newOrderby = listData.getOrderby();
		Long entityId = listData.getId();
		logData.setEntityId(entityId);

		if (LifecycleEntity.DEFINITION.equals(entity)) {
			if (LifecycleProperty.ID.equals(property)) {
				Map<String, Object> entityData = helper.getDefinitionData(create, entityId);
				String definitionValue = (String) entityData.get("value");
				Long meaningId = (Long) entityData.get("meaning_id");
				Long prevOrderBy = (Long) entityData.get("order_by");
				if (newOrderby.equals(prevOrderBy)) {
					return;
				}
				String recent = prevOrderBy + ") " + definitionValue;
				String entry = newOrderby + ") " + definitionValue;
				logData.setRecent(recent);
				logData.setEntry(entry);
				Long lifecycleLogId = createLifecycleLog(logData);
				createMeaningLifecycleLog(meaningId, lifecycleLogId);
			} else if (LifecycleProperty.NOTE.equals(property)) {
				Map<String, Object> entityData = helper.getDefinitionFreeformData(create, entityId, FreeformType.NOTE);
				Long prevOrderBy = (Long) entityData.get("order_by");
				if (newOrderby.equals(prevOrderBy)) {
					return;
				}
				Long meaningId = (Long) entityData.get("meaning_id");
				String valueText = (String) entityData.get("value_text");
				String recent = prevOrderBy + ") " + valueText;
				String entry = newOrderby + ") " + valueText;
				logData.setRecent(recent);
				logData.setEntry(entry);
				Long lifecycleLogId = createLifecycleLog(logData);
				createMeaningLifecycleLog(meaningId, lifecycleLogId);
			}
		} else if (LifecycleEntity.LEXEME_RELATION.equals(entity)) {
			if (LifecycleProperty.ID.equals(property)) {
				Map<String, Object> entityData = helper.getLexemeRelationData(create, entityId);
				String relTypeCode = (String) entityData.get("lex_rel_type_code");
				Long lexemeId = (Long) entityData.get("lexeme1_id");
				Long prevOrderBy = (Long) entityData.get("order_by");
				if (newOrderby.equals(prevOrderBy)) {
					return;
				}
				String recent = prevOrderBy + ") " + relTypeCode;
				String entry = newOrderby + ") " + relTypeCode;
				logData.setRecent(recent);
				logData.setEntry(entry);
				Long lifecycleLogId = createLifecycleLog(logData);
				createLexemeLifecycleLog(lexemeId, lifecycleLogId);
			}
		} else if (LifecycleEntity.MEANING_RELATION.equals(entity)) {
			if (LifecycleProperty.ID.equals(property)) {
				Map<String, Object> entityData = helper.getMeaningRelationData(create, entityId);
				String relTypeCode = (String) entityData.get("meaning_rel_type_code");
				Long meaningId = (Long) entityData.get("meaning1_id");
				Long prevOrderBy = (Long) entityData.get("order_by");
				if (newOrderby.equals(prevOrderBy)) {
					return;
				}
				String recent = prevOrderBy + ") " + relTypeCode;
				String entry = newOrderby + ") " + relTypeCode;
				logData.setRecent(recent);
				logData.setEntry(entry);
				Long lifecycleLogId = createLifecycleLog(logData);
				createMeaningLifecycleLog(meaningId, lifecycleLogId);
			}
		} else if (LifecycleEntity.WORD_RELATION.equals(entity)) {
			if (LifecycleProperty.ID.equals(property)) {
				Map<String, Object> entityData = helper.getWordRelationData(create, entityId);
				String relTypeCode = (String) entityData.get("word_rel_type_code");
				Long wordId = (Long) entityData.get("word1_id");
				Long prevOrderBy = (Long) entityData.get("order_by");
				if (newOrderby.equals(prevOrderBy)) {
					return;
				}
				String recent = prevOrderBy + ") " + relTypeCode;
				String entry = newOrderby + ") " + relTypeCode;
				logData.setRecent(recent);
				logData.setEntry(entry);
				Long lifecycleLogId = createLifecycleLog(logData);
				createWordLifecycleLog(wordId, lifecycleLogId);
			}
		} else if (LifecycleEntity.LEXEME.equals(entity)) {
			if (LifecycleProperty.ID.equals(property)) {
				Map<String, Object> entityData = helper.getLexemeData(create, entityId);
				Long prevOrderBy = (Long) entityData.get("order_by");
				if (newOrderby.equals(prevOrderBy)) {
					return;
				}
				String recent = prevOrderBy + ") " + lexemeLogString(entityData);
				String entry = newOrderby + ") " + lexemeLogString(entityData);
				logData.setRecent(recent);
				logData.setEntry(entry);
				Long lifecycleLogId = createLifecycleLog(logData);
				createLexemeLifecycleLog(entityId, lifecycleLogId);
			} else if (LifecycleProperty.NOTE.equals(property)) {
				Map<String, Object> entityData = helper.getLexemeFreeformData(create, entityId);
				Long prevOrderBy = (Long) entityData.get("order_by");
				if (newOrderby.equals(prevOrderBy)) {
					return;
				}
				Long lexemeId = (Long) entityData.get("lexeme_id");
				String valueText = (String) entityData.get("value_text");
				String recent = prevOrderBy + ") " + valueText;
				String entry = newOrderby + ") " + valueText;
				logData.setRecent(recent);
				logData.setEntry(entry);
				Long lifecycleLogId = createLifecycleLog(logData);
				createLexemeLifecycleLog(lexemeId, lifecycleLogId);
			}
		} else if (LifecycleEntity.MEANING.equals(entity)) {
			if (LifecycleProperty.DOMAIN.equals(property)) {
				Map<String, Object> entityData = helper.getMeaningDomainData(create, entityId);
				String domainCode = (String) entityData.get("domain_code");
				Long meaningId = (Long) entityData.get("meaning_id");
				Long prevOrderBy = (Long) entityData.get("order_by");
				if (newOrderby.equals(prevOrderBy)) {
					return;
				}
				String recent = prevOrderBy + ") " + domainCode;
				String entry = newOrderby + ") " + domainCode;
				logData.setRecent(recent);
				logData.setEntry(entry);
				logData.setEntityId(meaningId);
				Long lifecycleLogId = createLifecycleLog(logData);
				createMeaningLifecycleLog(meaningId, lifecycleLogId);
			} else if (LifecycleProperty.NOTE.equals(property)) {
				Map<String, Object> entityData = helper.getMeaningFreeformData(create, entityId, FreeformType.NOTE);
				Long prevOrderBy = (Long) entityData.get("order_by");
				if (newOrderby.equals(prevOrderBy)) {
					return;
				}
				Long meaningId = (Long) entityData.get("meaning_id");
				String valueText = (String) entityData.get("value_text");
				String recent = prevOrderBy + ") " + valueText;
				String entry = newOrderby + ") " + valueText;
				logData.setRecent(recent);
				logData.setEntry(entry);
				Long lifecycleLogId = createLifecycleLog(logData);
				createMeaningLifecycleLog(meaningId, lifecycleLogId);
			}
		} else if (LifecycleEntity.GOVERNMENT.equals(entity)) {
			if (LifecycleProperty.ID.equals(property)) {
				Map<String, Object> entityData = helper.getFirstDepthFreeformData(create, entityId, FreeformType.GOVERNMENT);
				String governmentValue = (String) entityData.get("value_text");
				Long lexemeId = (Long) entityData.get("lexeme_id");
				Long prevOrderBy = (Long) entityData.get("order_by");
				if (newOrderby.equals(prevOrderBy)) {
					return;
				}
				String recent = prevOrderBy + ") " + governmentValue;
				String entry = newOrderby + ") " + governmentValue;
				logData.setRecent(recent);
				logData.setEntry(entry);
				Long lifecycleLogId = createLifecycleLog(logData);
				createLexemeLifecycleLog(lexemeId, lifecycleLogId);
			}
		} else if (LifecycleEntity.USAGE.equals(entity)) {
			if (LifecycleProperty.ID.equals(property)) {
				Map<String, Object> entityData = helper.getFirstDepthFreeformData(create, entityId, FreeformType.USAGE);
				String usageValue = (String) entityData.get("value_text");
				Long lexemeId = (Long) entityData.get("lexeme_id");
				Long prevOrderBy = (Long) entityData.get("order_by");
				if (newOrderby.equals(prevOrderBy)) {
					return;
				}
				String recent = prevOrderBy + ") " + usageValue;
				String entry = newOrderby + ") " + usageValue;
				logData.setRecent(recent);
				logData.setEntry(entry);
				Long lifecycleLogId = createLifecycleLog(logData);
				createLexemeLifecycleLog(lexemeId, lifecycleLogId);
			}
		}
	}

	private String lexemeLogString(Map<String, Object> entityData) {
		return entityData.get("value") + " [" + entityData.get("level1") + "." + entityData.get("level2") + "]";
	}

	private Long createLifecycleLog(LogData logData) {

		Long entityId = logData.getEntityId();
		LifecycleEntity entity = logData.getEntityName();
		String entityName = entity.name();
		LifecycleProperty property = logData.getProperty();
		String entityProp = property.name();
		LifecycleEventType eventType = logData.getEventType();
		String eventTypeName = eventType.name();
		String userName = logData.getUserName();
		String recent = logData.getRecent();
		String entry = logData.getEntry();

		Long lifecycleLogId = create
				.insertInto(
						LIFECYCLE_LOG,
						LIFECYCLE_LOG.ENTITY_ID,
						LIFECYCLE_LOG.ENTITY_NAME,
						LIFECYCLE_LOG.ENTITY_PROP,
						LIFECYCLE_LOG.EVENT_TYPE,
						LIFECYCLE_LOG.EVENT_BY,
						LIFECYCLE_LOG.RECENT,
						LIFECYCLE_LOG.ENTRY)
				.values(
						entityId,
						entityName,
						entityProp,
						eventTypeName,
						userName,
						recent,
						entry)
				.returning(LIFECYCLE_LOG.ID)
				.fetchOne()
				.getId();
		return lifecycleLogId;
	}

	private void createWordLifecycleLog(Long wordId, Long lifecycleLogId) {
		create
				.insertInto(
						WORD_LIFECYCLE_LOG,
						WORD_LIFECYCLE_LOG.WORD_ID,
						WORD_LIFECYCLE_LOG.LIFECYCLE_LOG_ID)
				.values(wordId, lifecycleLogId)
				.execute();
	}

	private void createLexemeLifecycleLog(Long lexemeId, Long lifecycleLogId) {
		create
				.insertInto(
						LEXEME_LIFECYCLE_LOG,
						LEXEME_LIFECYCLE_LOG.LEXEME_ID,
						LEXEME_LIFECYCLE_LOG.LIFECYCLE_LOG_ID)
				.values(lexemeId, lifecycleLogId)
				.execute();
	}

	private void createMeaningLifecycleLog(Long meaningId, Long lifecycleLogId) {
		create
				.insertInto(
						MEANING_LIFECYCLE_LOG,
						MEANING_LIFECYCLE_LOG.MEANING_ID,
						MEANING_LIFECYCLE_LOG.LIFECYCLE_LOG_ID)
				.values(meaningId, lifecycleLogId)
				.execute();
	}

	private void createSourceLifecycleLog(Long sourceId, Long lifecycleLogId) {
		create
				.insertInto(
						SOURCE_LIFECYCLE_LOG,
						SOURCE_LIFECYCLE_LOG.SOURCE_ID,
						SOURCE_LIFECYCLE_LOG.LIFECYCLE_LOG_ID)
				.values(sourceId, lifecycleLogId)
				.execute();
	}

	public String getSimpleLexemeDescription(Long lexemeId) {

		Map<String, Object> lexemeData = helper.getLexemeData(create, lexemeId);
		String level1 = lexemeData.get("level1").toString();
		String level2 = lexemeData.get("level2").toString();
		String levels = String.join(".", level1, level2);
		return lexemeData.get("value") + " [" + levels + "]";
	}

	public String getExtendedLexemeDescription(Long lexemeId) {

		String logString = getSimpleLexemeDescription(lexemeId);
		Map<String, Object> lexemeUsageData = helper.getLexemeUsageData(create, lexemeId);
		String usages = "";
		if (lexemeUsageData.get("value_text") != null) {
			usages = lexemeUsageData.get("value_text").toString();
		}
		List<Government> lexemeGovernments = commonDataDbService.getLexemeGovernments(lexemeId);
		String governments = lexemeGovernments.stream().map(Government::getValue).collect(Collectors.joining(", "));
		logString = String.join(" ", logString, governments, usages);
		return logString;
	}

	public String getCombinedMeaningDefinitions(Long meaningId) {
		Result<DefinitionRecord> definitions = create.selectFrom(DEFINITION).where(DEFINITION.MEANING_ID.eq(meaningId)).orderBy(DEFINITION.ORDER_BY).fetch();
		return definitions.stream().map(DefinitionRecord::getValue).collect(Collectors.joining(" | "));
	}
}
