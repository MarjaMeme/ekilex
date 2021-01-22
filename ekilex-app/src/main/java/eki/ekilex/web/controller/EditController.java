package eki.ekilex.web.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import eki.common.constant.ContentKey;
import eki.common.constant.LifecycleEntity;
import eki.common.constant.LifecycleProperty;
import eki.common.constant.ReferenceType;
import eki.ekilex.constant.WebConstant;
import eki.ekilex.data.Classifier;
import eki.ekilex.data.ClassifierSelect;
import eki.ekilex.data.ConfirmationRequest;
import eki.ekilex.data.CreateItemRequest;
import eki.ekilex.data.DatasetPermission;
import eki.ekilex.data.ListData;
import eki.ekilex.data.SourceProperty;
import eki.ekilex.data.UpdateItemRequest;
import eki.ekilex.data.UpdateListRequest;
import eki.ekilex.service.ComplexOpService;
import eki.ekilex.service.CudService;
import eki.ekilex.service.LookupService;
import eki.ekilex.service.SourceLinkService;
import eki.ekilex.service.SourceService;
import eki.ekilex.service.SynSearchService;
import eki.ekilex.service.util.ConversionUtil;
import eki.ekilex.web.bean.SessionBean;

@ConditionalOnWebApplication
@Controller
@SessionAttributes(WebConstant.SESSION_BEAN)
public class EditController extends AbstractMutableDataPageController {

	private static final Logger logger = LoggerFactory.getLogger(EditController.class);

	@Autowired
	private CudService cudService;

	@Autowired
	private ConversionUtil conversionUtil;

	@Autowired
	private SourceService sourceService;

	@Autowired
	private SourceLinkService sourceLinkService;

	@Autowired
	private LookupService lookupService;

	@Autowired
	private ComplexOpService complexOpService;

	@Autowired
	private SynSearchService synSearchService;

	@ResponseBody
	@PostMapping(CREATE_ITEM_URI)
	public String createItem(@RequestBody CreateItemRequest itemData, @ModelAttribute(name = SESSION_BEAN) SessionBean sessionBean) throws Exception {

		logger.debug("Add new item : {}", itemData);

		String itemValue = itemData.getValue();
		itemValue = valueUtil.trimAndCleanAndRemoveHtmlAndLimit(itemValue);
		String sourceLinkValue;
		String datasetCode;

		switch (itemData.getOpCode()) {
		case "definition":
			datasetCode = getDatasetCodeFromRole();
			cudService.createDefinition(itemData.getId(), itemValue, itemData.getLanguage(), datasetCode, itemData.getComplexity(), itemData.getItemType(), itemData.isPublic());
			break;
		case "definition_note":
			cudService.createDefinitionNote(itemData.getId(), itemValue, itemData.getLanguage(), itemData.isPublic());
			break;
		case "usage":
			cudService.createUsage(itemData.getId(), itemValue, itemData.getLanguage(), itemData.getComplexity(), itemData.isPublic());
			break;
		case "usage_translation":
			cudService.createUsageTranslation(itemData.getId(), itemValue, itemData.getLanguage());
			break;
		case "usage_definition":
			cudService.createUsageDefinition(itemData.getId(), itemValue, itemData.getLanguage());
			break;
		case "lexeme_pos":
			cudService.createLexemePos(itemData.getId(), itemValue);
			break;
		case "lexeme_tag":
			cudService.createLexemeTag(itemData.getId(), itemValue);
			break;
		case "meaning_domain":
			Classifier meaningDomain = conversionUtil.classifierFromIdString(itemValue);
			cudService.createMeaningDomain(itemData.getId2(), meaningDomain);
			break;
		case "government":
			cudService.createLexemeGovernment(itemData.getId(), itemValue, itemData.getComplexity());
			break;
		case ContentKey.DEFINITION_SOURCE_LINK: {
			sourceLinkValue = getSourcePropertyValue(itemData.getId3());
			sourceLinkService.createDefinitionSourceLink(itemData.getId(), itemData.getId2(), ReferenceType.ANY, sourceLinkValue, itemValue);
			break;
		}
		case ContentKey.LEXEME_SOURCE_LINK: {
			sourceLinkValue = getSourcePropertyValue(itemData.getId3());
			sourceLinkService.createLexemeSourceLink(itemData.getId(), itemData.getId2(), ReferenceType.ANY, sourceLinkValue, itemValue);
			break;
		}
		case "usage_author":
			sourceLinkValue = getSourceNameValue(itemData.getId2());
			ReferenceType refType = ReferenceType.valueOf(itemData.getItemType());
			sourceLinkService.createFreeformSourceLink(
					itemData.getId(), itemData.getId2(), refType, sourceLinkValue, null, LifecycleEntity.USAGE, LifecycleProperty.SOURCE_LINK);
			break;
		case "usage_source_link":
			sourceLinkValue = getSourcePropertyValue(itemData.getId3());
			sourceLinkService.createFreeformSourceLink(
					itemData.getId(), itemData.getId2(), ReferenceType.ANY, sourceLinkValue, itemValue, LifecycleEntity.USAGE, LifecycleProperty.SOURCE_LINK);
			break;
		case "lexeme_ff_source_link":
			sourceLinkValue = getSourcePropertyValue(itemData.getId3());
			sourceLinkService.createFreeformSourceLink(itemData.getId(), itemData.getId2(), ReferenceType.ANY, sourceLinkValue, itemValue, LifecycleEntity.LEXEME);
			break;
		case "meaning_ff_source_link":
			sourceLinkValue = getSourcePropertyValue(itemData.getId3());
			sourceLinkService.createFreeformSourceLink(itemData.getId(), itemData.getId2(), ReferenceType.ANY, sourceLinkValue, itemValue, LifecycleEntity.MEANING);
			break;
		case "definition_ff_source_link":
			sourceLinkValue = getSourcePropertyValue(itemData.getId3());
			sourceLinkService.createFreeformSourceLink(itemData.getId(), itemData.getId2(), ReferenceType.ANY, sourceLinkValue, itemValue, LifecycleEntity.DEFINITION);
			break;
		case "lexeme_deriv":
			cudService.createLexemeDeriv(itemData.getId(), itemValue);
			break;
		case "lexeme_register":
			cudService.createLexemeRegister(itemData.getId(), itemValue);
			break;
		case "lexeme_region":
			cudService.createLexemeRegion(itemData.getId(), itemValue);
			break;
		case "word_gender":
			cudService.updateWordGender(itemData.getId3(), itemValue);
			break;
		case "word_type":
			cudService.createWordType(itemData.getId3(), itemValue);
			break;
		case "word_aspect":
			cudService.updateWordAspect(itemData.getId3(), itemValue);
			break;
		case "word_display_morph":
			cudService.updateWordDisplayMorph(itemData.getId3(), itemValue);
			break;
		case "word_vocal_form":
			cudService.updateWordVocalForm(itemData.getId3(), itemValue);
			break;
		case "word_lang":
			cudService.updateWordLang(itemData.getId3(), itemValue);
			break;
		case "lexeme_grammar":
			cudService.createLexemeGrammar(itemData.getId(), itemValue, itemData.getComplexity());
			break;
		case "lexeme_value_state":
			cudService.updateLexemeValueState(itemData.getId(), itemValue);
			break;
		case "learner_comment":
			cudService.createMeaningLearnerComment(itemData.getId(), itemValue, itemData.getLanguage());
			break;
		case "lexeme_note":
			cudService.createLexemeNote(itemData.getId(), itemValue, itemData.getLanguage(), itemData.getComplexity(), itemData.isPublic());
			break;
		case "meaning_note":
			cudService.createMeaningNote(itemData.getId(), itemValue, itemData.getLanguage(), itemData.getComplexity(), itemData.isPublic());
			sessionBean.setRecentNoteLanguage(itemData.getLanguage());
			break;
		case "word_note":
			cudService.createWordNote(itemData.getId(), itemValue);
			break;
		case "image_title":
			cudService.createImageTitle(itemData.getId(), itemValue);
			break;
		case "meaning_video":
			cudService.createMeaningVideo(itemData.getId(), itemValue);
			break;
		case "create_syn_word":
			datasetCode = getDatasetCodeFromRole();
			cudService.createWordAndSynRelation(itemData.getId(), itemValue, datasetCode, itemData.getLanguage(), itemData.getValue2());
			break;
		case "meaning_semantic_type":
			cudService.createMeaningSemanticType(itemData.getId2(), itemValue);
			break;
		case "od_word_recommendation":
			cudService.createOdWordRecommendation(itemData.getId(), itemValue);
			break;
		case "od_lexeme_recommendation":
			cudService.createOdLexemeRecommendation(itemData.getId(), itemValue);
			break;
		case "od_usage_definition":
			cudService.createOdUsageDefinition(itemData.getId(), itemValue);
			break;
		case "od_usage_alternative":
			cudService.createOdUsageAlternative(itemData.getId(), itemValue);
			break;
		}
		return RESPONSE_OK_VER2;
	}

	private String getSourcePropertyValue(Long sourcePropertyId) {
		SourceProperty sourceProperty = sourceService.getSourceProperty(sourcePropertyId);
		return sourceProperty.getValueText();
	}

	private String getSourceNameValue(Long sourceId) {
		return sourceService.getSourceNameValue(sourceId);
	}

	@ResponseBody
	@PostMapping(UPDATE_ITEM_URI)
	public String updateItem(@RequestBody UpdateItemRequest itemData, @ModelAttribute(name = SESSION_BEAN) SessionBean sessionBean) throws Exception {

		logger.debug("Update item : {}", itemData);

		String itemValue = itemData.getValue();
		itemValue = valueUtil.trimAndCleanAndRemoveHtmlAndLimit(itemValue);

		switch (itemData.getOpCode()) {
		case "user_lang_selection":
			List<ClassifierSelect> languagesOrder = sessionBean.getLanguagesOrder();
			ClassifierSelect langSelect = languagesOrder.stream().filter(classif -> StringUtils.equals(classif.getCode(), itemData.getCode())).findFirst().get();
			langSelect.setSelected(!langSelect.isSelected());
			break;
		case "usage":
			cudService.updateUsageValue(itemData.getId(), itemValue, itemData.getComplexity(), itemData.isPublic());
			break;
		case "usage_translation":
			cudService.updateUsageTranslationValue(itemData.getId(), itemValue);
			break;
		case "usage_definition":
			cudService.updateUsageDefinitionValue(itemData.getId(), itemValue);
			break;
		case "definition":
			cudService.updateDefinition(itemData.getId(), itemValue, itemData.getLanguage(), itemData.getComplexity(), itemData.getCode(), itemData.isPublic());
			break;
		case "definition_note":
			cudService.updateDefinitionNote(itemData.getId(), itemValue, itemData.getLanguage(), itemData.isPublic());
			break;
		case "lexeme_complexity":
			cudService.updateLexemeComplexity(itemData.getId(), itemValue);
			break;
		case "lexeme_pos":
			cudService.updateLexemePos(itemData.getId(), itemData.getCurrentValue(), itemValue);
			break;
		case "meaning_domain":
			Classifier currentMeaningDomain = conversionUtil.classifierFromIdString(itemData.getCurrentValue());
			Classifier newMeaningDomain = conversionUtil.classifierFromIdString(itemData.getValue());
			cudService.updateMeaningDomain(itemData.getId(), currentMeaningDomain, newMeaningDomain);
			break;
		case "government":
			cudService.updateLexemeGovernment(itemData.getId(), itemValue, itemData.getComplexity());
			break;
		case "lexeme_deriv":
			cudService.updateLexemeDeriv(itemData.getId(), itemData.getCurrentValue(), itemValue);
			break;
		case "lexeme_register":
			cudService.updateLexemeRegister(itemData.getId(), itemData.getCurrentValue(), itemValue);
			break;
		case "lexeme_region":
			cudService.updateLexemeRegion(itemData.getId(), itemData.getCurrentValue(), itemValue);
			break;
		case "lexeme_weight":
			cudService.updateLexemeWeight(itemData.getId(), itemValue);
			break;
		case "meaning_relation_weight":
			cudService.updateMeaningRelationWeight(itemData.getId(), itemValue);
			break;
		case "word_gender":
			cudService.updateWordGender(itemData.getId(), itemValue);
			break;
		case "word_type":
			cudService.updateWordType(itemData.getId(), itemData.getCurrentValue(), itemValue);
			break;
		case "lexeme_grammar":
			cudService.updateLexemeGrammar(itemData.getId(), itemValue, itemData.getComplexity());
			break;
		case "word_aspect":
			cudService.updateWordAspect(itemData.getId(), itemValue);
			break;
		case "word_display_morph":
			cudService.updateWordDisplayMorph(itemData.getId(), itemValue);
			break;
		case "word_vocal_form":
			cudService.updateWordVocalForm(itemData.getId(), itemValue);
			break;
		case "word_lang":
			cudService.updateWordLang(itemData.getId(), itemValue);
			break;
		case "lexeme_publicity":
			cudService.updateLexemePublicity(itemData.getId(), itemData.isPublic());
			break;
		case "lexeme_value_state":
			cudService.updateLexemeValueState(itemData.getId(), itemValue);
			break;
		case "learner_comment":
			cudService.updateMeaningLearnerComment(itemData.getId(), itemValue);
			break;
		case "lexeme_note":
			cudService.updateLexemeNote(itemData.getId(), itemValue, itemData.getLanguage(), itemData.getComplexity(), itemData.isPublic());
			break;
		case "meaning_note":
			cudService.updateMeaningNote(itemData.getId(), itemValue, itemData.getLanguage(), itemData.getComplexity(), itemData.isPublic());
			break;
		case "word_note":
			cudService.updateWordNote(itemData.getId(), itemValue);
			break;
		case "image_title":
			cudService.updateImageTitle(itemData.getId(), itemValue);
			break;
		case "meaning_video":
			cudService.updateMeaningVideo(itemData.getId(), itemValue);
			break;
		case "meaning_semantic_type":
			cudService.updateMeaningSemanticType(itemData.getId(), itemData.getCurrentValue(), itemValue);
			break;
		case "od_word_recommendation":
			cudService.updateOdWordRecommendation(itemData.getId(), itemValue);
			break;
		case "od_lexeme_recommendation":
			cudService.updateOdLexemeRecommendation(itemData.getId(), itemValue);
			break;
		case "od_usage_definition":
			cudService.updateOdUsageDefinition(itemData.getId(), itemValue);
			break;
		case "od_usage_alternative":
			cudService.updateOdUsageAlternative(itemData.getId(), itemValue);
			break;
		}
		return RESPONSE_OK_VER2;
	}

	@ResponseBody
	@PostMapping(UPDATE_ORDERING_URI)
	public String updateOrdering(@RequestBody UpdateListRequest listData, @ModelAttribute(name = SESSION_BEAN) SessionBean sessionBean) throws Exception {

		logger.debug("Update ordering {}", listData);

		List<ListData> items = listData.getItems();
		switch (listData.getOpCode()) {
		case "definition":
			cudService.updateDefinitionOrdering(items);
			break;
		case "lexeme_relation":
			cudService.updateLexemeRelationOrdering(items);
			break;
		case "meaning_relation":
			cudService.updateMeaningRelationOrdering(items);
			break;
		case "word_relation":
			cudService.updateWordRelationOrdering(items);
			break;
		case "word_etymology":
			cudService.updateWordEtymologyOrdering(items);
			break;
		case "lexeme":
			cudService.updateLexemeOrdering(items);
			break;
		case "meaning_domain":
			cudService.updateMeaningDomainOrdering(items);
			break;
		case "government":
			cudService.updateGovernmentOrdering(items);
			break;
		case "usage":
			cudService.updateUsageOrdering(items);
			break;
		case "lexeme_note":
			cudService.updateLexemeNoteOrdering(items);
			break;
		case "meaning_note":
			cudService.updateMeaningNoteOrdering(items);
			break;
		case "definition_note":
			cudService.updateDefinitionNoteOrdering(items);
			break;
		case "lexeme_meaning_word":
			Long lexemeId = listData.getAdditionalInfo();
			cudService.updateLexemeMeaningWordOrdering(items, lexemeId);
			break;
		}
		return RESPONSE_OK_VER2;
	}

	@ResponseBody
	@PostMapping(UPDATE_LEVELS_URI)
	public String updateLexemeLevels(@RequestParam("id") Long lexemeId, @RequestParam("action") String action) throws Exception {

		logger.debug("Change lexeme levels for id {}, action {}", lexemeId, action);

		cudService.updateLexemeLevels(lexemeId, action);

		return RESPONSE_OK_VER1;
	}

	@ResponseBody
	@PostMapping(CONFIRM_OP_URI)
	public ConfirmationRequest confirmOperation(@RequestBody ConfirmationRequest confirmationRequest) {

		String opName = confirmationRequest.getOpName();
		String opCode = confirmationRequest.getOpCode();
		Long id = confirmationRequest.getId();

		logger.debug("Confirmation request: {} {} {}", opName, opCode, id);

		DatasetPermission userRole = userContext.getUserRole();

		switch (opName) {
		case "delete":
			switch (opCode) {
			case "lexeme":
				return complexOpService.validateLexemeDelete(id);
			case "meaning":
				return complexOpService.validateMeaningDelete(id, userRole);
			case "rus_meaning_lexemes":
				return complexOpService.validateLexemeAndMeaningLexemesDelete(id, LANGUAGE_CODE_RUS, userRole);
			}
		}
		throw new UnsupportedOperationException("Unsupported confirm operation: " + opName + " " + opCode);
	}

	@ResponseBody
	@PostMapping(DELETE_ITEM_URI)
	public String deleteItem(
			@RequestParam("opCode") String opCode,
			@RequestParam("id") Long id,
			@RequestParam(value = "value", required = false) String valueToDelete) throws Exception {

		logger.debug("Delete operation : {} : for id {}, value {}", opCode, id, valueToDelete);

		DatasetPermission userRole = userContext.getUserRole();
		if (userRole == null) {
			return "NOK";
		}
		String datasetCode = userRole.getDatasetCode();

		switch (opCode) {
		case "definition":
			cudService.deleteDefinition(id);
			break;
		case "definition_note":
			cudService.deleteDefinitionNote(id);
			break;
		case "usage":
			cudService.deleteUsage(id);
			break;
		case "usage_translation":
			cudService.deleteUsageTranslation(id);
			break;
		case "usage_definition":
			cudService.deleteUsageDefinition(id);
			break;
		case "usage_author":
		case "usage_source_link":
			sourceLinkService.deleteFreeformSourceLink(id, LifecycleEntity.USAGE);
			break;
		case "lexeme_ff_source_link":
			sourceLinkService.deleteFreeformSourceLink(id, LifecycleEntity.LEXEME);
			break;
		case "meaning_ff_source_link":
			sourceLinkService.deleteFreeformSourceLink(id, LifecycleEntity.MEANING);
			break;
		case "definition_ff_source_link":
			sourceLinkService.deleteFreeformSourceLink(id, LifecycleEntity.DEFINITION);
			break;
		case ContentKey.DEFINITION_SOURCE_LINK:
			sourceLinkService.deleteDefinitionSourceLink(id);
			break;
		case ContentKey.LEXEME_SOURCE_LINK:
			sourceLinkService.deleteLexemeSourceLink(id);
			break;
		case "government":
			cudService.deleteLexemeGovernment(id);
			break;
		case "lexeme_note":
			cudService.deleteLexemeNote(id);
			break;
		case "lexeme_pos":
			cudService.deleteLexemePos(id, valueToDelete);
			break;
		case "lexeme_tag":
			cudService.deleteLexemeTag(id, valueToDelete);
			break;
		case "lexeme_deriv":
			cudService.deleteLexemeDeriv(id, valueToDelete);
			break;
		case "lexeme_register":
			cudService.deleteLexemeRegister(id, valueToDelete);
			break;
		case "lexeme_region":
			cudService.deleteLexemeRegion(id, valueToDelete);
			break;
		case "lexeme_grammar":
			cudService.deleteLexemeGrammar(id);
			break;
		case "lexeme_relation":
			cudService.deleteLexemeRelation(id);
			break;
		case "lexeme_value_state":
			cudService.updateLexemeValueState(id, null);
			break;
		case "lexeme":
			cudService.deleteLexeme(id);
			break;
		case "rus_meaning_lexemes":
			cudService.deleteLexemeAndMeaningLexemes(id, LANGUAGE_CODE_RUS, datasetCode);
			break;
		case "learner_comment":
			cudService.deleteMeaningLearnerComment(id);
			break;
		case "meaning":
			cudService.deleteMeaningAndLexemes(id, datasetCode);
			break;
		case "meaning_domain":
			Classifier meaningDomain = conversionUtil.classifierFromIdString(valueToDelete);
			cudService.deleteMeaningDomain(id, meaningDomain);
			break;
		case "meaning_note":
			cudService.deleteMeaningNote(id);
			break;
		case "meaning_relation":
			cudService.deleteMeaningRelation(id);
			break;
		case "syn_meaning_relation":
			cudService.deleteSynMeaningRelation(id);
			break;
		case "word_gender":
			cudService.updateWordGender(id, null);
			break;
		case "meaning_image":
			cudService.deleteMeaningImage(id);
			break;
		case "meaning_video":
			cudService.deleteMeaningVideo(id);
			break;
		case "word_type":
			cudService.deleteWordType(id, valueToDelete);
			break;
		case "word_aspect":
			cudService.updateWordAspect(id, null);
			break;
		case "word_relation":
			cudService.deleteWordRelation(id);
			break;
		case "word_display_morph":
			cudService.updateWordDisplayMorph(id, null);
			break;
		case "word_vocal_form":
			cudService.updateWordVocalForm(id, null);
			break;
		case "word_note":
			cudService.deleteWordNote(id);
			break;
		case "image_title":
			cudService.deleteImageTitle(id);
			break;
		case "meaning_semantic_type":
			cudService.deleteMeaningSemanticType(id, valueToDelete);
			break;
		case "od_word_recommendation":
			cudService.deleteOdWordRecommendation(id);
			break;
		case "od_lexeme_recommendation":
			cudService.deleteOdLexemeRecommendation(id);
			break;
		case "od_usage_definition":
			cudService.deleteOdUsageDefinition(id);
			break;
		case "od_usage_alternative":
			cudService.deleteOdUsageAlternative(id);
			break;
		}
		return RESPONSE_OK_VER1;
	}

	@PostMapping(UPDATE_WORD_VALUE_URI)
	@ResponseBody
	public String updateWordValue(@RequestParam("wordId") Long wordId, @RequestParam("value") String value) throws Exception {

		value = valueUtil.trimAndCleanAndRemoveHtmlAndLimit(value);

		logger.debug("Updating word value, wordId: \"{}\", valuePrese: \"{}\"", wordId, value);

		cudService.updateWordValue(wordId, value);

		return value;
	}

	@PostMapping(CREATE_RELATIONS_URI)
	@ResponseBody
	public String createRelations(
			@RequestParam("opCode") String opCode,
			@RequestParam(name = "relationType", required = false) String relationType,
			@RequestParam(name = "oppositeRelationType", required = false) String oppositeRelationType,
			@RequestParam(name = "weight", required = false) String weightStr,
			@RequestParam("id") Long id1,
			@RequestParam("ids") List<Long> ids) throws Exception {

		String datasetCode = null;
		for (Long id2 : ids) {
			switch (opCode) {
			case "meaning_relation":
				cudService.createMeaningRelation(id1, id2, relationType, oppositeRelationType);
				break;
			case "lexeme_relation":
				cudService.createLexemeRelation(id1, id2, relationType, oppositeRelationType);
				break;
			case "word_relation":
				cudService.createWordRelation(id1, id2, relationType, oppositeRelationType);
				break;
			case "syn_meaning_relation":
				synSearchService.createSynMeaningRelation(id1, id2, weightStr);
				break;
			case "raw_relation":
				if (datasetCode == null) {
					datasetCode = getDatasetCodeFromRole();
				}
				cudService.createSynWordRelation(id1, id2, weightStr, datasetCode);
				break;
			}
		}
		return RESPONSE_OK_VER1;
	}

	@PostMapping(OPPOSITE_RELATIONS_URI)
	@ResponseBody
	public List<Classifier> getOppositeRelations(@RequestParam("entity") LifecycleEntity entity, @RequestParam("relationType") String relationTypeCode) {
		return lookupService.getOppositeRelations(entity, relationTypeCode);
	}

}
