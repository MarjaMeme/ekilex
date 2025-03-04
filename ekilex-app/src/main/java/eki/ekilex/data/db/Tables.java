/*
 * This file is generated by jOOQ.
 */
package eki.ekilex.data.db;


import eki.ekilex.data.db.tables.ActivityLog;
import eki.ekilex.data.db.tables.Aspect;
import eki.ekilex.data.db.tables.AspectLabel;
import eki.ekilex.data.db.tables.Collocation;
import eki.ekilex.data.db.tables.CollocationFreeform;
import eki.ekilex.data.db.tables.DataRequest;
import eki.ekilex.data.db.tables.Dataset;
import eki.ekilex.data.db.tables.DatasetPermission;
import eki.ekilex.data.db.tables.Definition;
import eki.ekilex.data.db.tables.DefinitionDataset;
import eki.ekilex.data.db.tables.DefinitionFreeform;
import eki.ekilex.data.db.tables.DefinitionSourceLink;
import eki.ekilex.data.db.tables.DefinitionType;
import eki.ekilex.data.db.tables.DefinitionTypeLabel;
import eki.ekilex.data.db.tables.Deriv;
import eki.ekilex.data.db.tables.DerivLabel;
import eki.ekilex.data.db.tables.DisplayMorph;
import eki.ekilex.data.db.tables.DisplayMorphLabel;
import eki.ekilex.data.db.tables.Domain;
import eki.ekilex.data.db.tables.DomainLabel;
import eki.ekilex.data.db.tables.EkiUser;
import eki.ekilex.data.db.tables.EkiUserApplication;
import eki.ekilex.data.db.tables.EkiUserProfile;
import eki.ekilex.data.db.tables.EtymologyType;
import eki.ekilex.data.db.tables.FeedbackLog;
import eki.ekilex.data.db.tables.FeedbackLogComment;
import eki.ekilex.data.db.tables.Form;
import eki.ekilex.data.db.tables.FormFreq;
import eki.ekilex.data.db.tables.Freeform;
import eki.ekilex.data.db.tables.FreeformSourceLink;
import eki.ekilex.data.db.tables.FreqCorp;
import eki.ekilex.data.db.tables.GameNonword;
import eki.ekilex.data.db.tables.Gender;
import eki.ekilex.data.db.tables.GenderLabel;
import eki.ekilex.data.db.tables.GovernmentType;
import eki.ekilex.data.db.tables.GovernmentTypeLabel;
import eki.ekilex.data.db.tables.LabelType;
import eki.ekilex.data.db.tables.Language;
import eki.ekilex.data.db.tables.LanguageLabel;
import eki.ekilex.data.db.tables.LexColloc;
import eki.ekilex.data.db.tables.LexCollocPosGroup;
import eki.ekilex.data.db.tables.LexCollocRelGroup;
import eki.ekilex.data.db.tables.LexRelMapping;
import eki.ekilex.data.db.tables.LexRelType;
import eki.ekilex.data.db.tables.LexRelTypeLabel;
import eki.ekilex.data.db.tables.LexRelation;
import eki.ekilex.data.db.tables.Lexeme;
import eki.ekilex.data.db.tables.LexemeActivityLog;
import eki.ekilex.data.db.tables.LexemeDeriv;
import eki.ekilex.data.db.tables.LexemeFreeform;
import eki.ekilex.data.db.tables.LexemePos;
import eki.ekilex.data.db.tables.LexemeRegion;
import eki.ekilex.data.db.tables.LexemeRegister;
import eki.ekilex.data.db.tables.LexemeSourceLink;
import eki.ekilex.data.db.tables.LexemeTag;
import eki.ekilex.data.db.tables.Meaning;
import eki.ekilex.data.db.tables.MeaningActivityLog;
import eki.ekilex.data.db.tables.MeaningDomain;
import eki.ekilex.data.db.tables.MeaningFreeform;
import eki.ekilex.data.db.tables.MeaningLastActivityLog;
import eki.ekilex.data.db.tables.MeaningNr;
import eki.ekilex.data.db.tables.MeaningRelMapping;
import eki.ekilex.data.db.tables.MeaningRelType;
import eki.ekilex.data.db.tables.MeaningRelTypeLabel;
import eki.ekilex.data.db.tables.MeaningRelation;
import eki.ekilex.data.db.tables.MeaningSemanticType;
import eki.ekilex.data.db.tables.MeaningTag;
import eki.ekilex.data.db.tables.Morph;
import eki.ekilex.data.db.tables.MorphFreq;
import eki.ekilex.data.db.tables.MorphLabel;
import eki.ekilex.data.db.tables.Paradigm;
import eki.ekilex.data.db.tables.Pos;
import eki.ekilex.data.db.tables.PosGroup;
import eki.ekilex.data.db.tables.PosGroupLabel;
import eki.ekilex.data.db.tables.PosLabel;
import eki.ekilex.data.db.tables.ProficiencyLevel;
import eki.ekilex.data.db.tables.ProficiencyLevelLabel;
import eki.ekilex.data.db.tables.Region;
import eki.ekilex.data.db.tables.Register;
import eki.ekilex.data.db.tables.RegisterLabel;
import eki.ekilex.data.db.tables.SemanticType;
import eki.ekilex.data.db.tables.SemanticTypeLabel;
import eki.ekilex.data.db.tables.Source;
import eki.ekilex.data.db.tables.SourceActivityLog;
import eki.ekilex.data.db.tables.SourceFreeform;
import eki.ekilex.data.db.tables.Tag;
import eki.ekilex.data.db.tables.TempDsImportPkMap;
import eki.ekilex.data.db.tables.TempDsImportQueue;
import eki.ekilex.data.db.tables.TermsOfUse;
import eki.ekilex.data.db.tables.UsageType;
import eki.ekilex.data.db.tables.UsageTypeLabel;
import eki.ekilex.data.db.tables.ValueState;
import eki.ekilex.data.db.tables.ValueStateLabel;
import eki.ekilex.data.db.tables.ViewWwClassifier;
import eki.ekilex.data.db.tables.ViewWwCollocation;
import eki.ekilex.data.db.tables.ViewWwDataset;
import eki.ekilex.data.db.tables.ViewWwDatasetWordMenu;
import eki.ekilex.data.db.tables.ViewWwDefinitionSourceLink;
import eki.ekilex.data.db.tables.ViewWwForm;
import eki.ekilex.data.db.tables.ViewWwLexeme;
import eki.ekilex.data.db.tables.ViewWwLexemeFreeformSourceLink;
import eki.ekilex.data.db.tables.ViewWwLexemeRelation;
import eki.ekilex.data.db.tables.ViewWwLexemeSourceLink;
import eki.ekilex.data.db.tables.ViewWwLexicalDecisionData;
import eki.ekilex.data.db.tables.ViewWwMeaning;
import eki.ekilex.data.db.tables.ViewWwMeaningFreeformSourceLink;
import eki.ekilex.data.db.tables.ViewWwMeaningRelation;
import eki.ekilex.data.db.tables.ViewWwSimilarityJudgementData;
import eki.ekilex.data.db.tables.ViewWwWord;
import eki.ekilex.data.db.tables.ViewWwWordEtymSourceLink;
import eki.ekilex.data.db.tables.ViewWwWordEtymology;
import eki.ekilex.data.db.tables.ViewWwWordRelation;
import eki.ekilex.data.db.tables.ViewWwWordSearch;
import eki.ekilex.data.db.tables.Word;
import eki.ekilex.data.db.tables.WordActivityLog;
import eki.ekilex.data.db.tables.WordEtymology;
import eki.ekilex.data.db.tables.WordEtymologyRelation;
import eki.ekilex.data.db.tables.WordEtymologySourceLink;
import eki.ekilex.data.db.tables.WordFreeform;
import eki.ekilex.data.db.tables.WordFreq;
import eki.ekilex.data.db.tables.WordGroup;
import eki.ekilex.data.db.tables.WordGroupMember;
import eki.ekilex.data.db.tables.WordGuid;
import eki.ekilex.data.db.tables.WordLastActivityLog;
import eki.ekilex.data.db.tables.WordRelMapping;
import eki.ekilex.data.db.tables.WordRelType;
import eki.ekilex.data.db.tables.WordRelTypeLabel;
import eki.ekilex.data.db.tables.WordRelation;
import eki.ekilex.data.db.tables.WordRelationParam;
import eki.ekilex.data.db.tables.WordType;
import eki.ekilex.data.db.tables.WordTypeLabel;
import eki.ekilex.data.db.tables.WordWordType;


/**
 * Convenience access to all tables in public
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

    /**
     * The table <code>public.activity_log</code>.
     */
    public static final ActivityLog ACTIVITY_LOG = ActivityLog.ACTIVITY_LOG;

    /**
     * The table <code>public.aspect</code>.
     */
    public static final Aspect ASPECT = Aspect.ASPECT;

    /**
     * The table <code>public.aspect_label</code>.
     */
    public static final AspectLabel ASPECT_LABEL = AspectLabel.ASPECT_LABEL;

    /**
     * The table <code>public.collocation</code>.
     */
    public static final Collocation COLLOCATION = Collocation.COLLOCATION;

    /**
     * The table <code>public.collocation_freeform</code>.
     */
    public static final CollocationFreeform COLLOCATION_FREEFORM = CollocationFreeform.COLLOCATION_FREEFORM;

    /**
     * The table <code>public.data_request</code>.
     */
    public static final DataRequest DATA_REQUEST = DataRequest.DATA_REQUEST;

    /**
     * The table <code>public.dataset</code>.
     */
    public static final Dataset DATASET = Dataset.DATASET;

    /**
     * The table <code>public.dataset_permission</code>.
     */
    public static final DatasetPermission DATASET_PERMISSION = DatasetPermission.DATASET_PERMISSION;

    /**
     * The table <code>public.definition</code>.
     */
    public static final Definition DEFINITION = Definition.DEFINITION;

    /**
     * The table <code>public.definition_dataset</code>.
     */
    public static final DefinitionDataset DEFINITION_DATASET = DefinitionDataset.DEFINITION_DATASET;

    /**
     * The table <code>public.definition_freeform</code>.
     */
    public static final DefinitionFreeform DEFINITION_FREEFORM = DefinitionFreeform.DEFINITION_FREEFORM;

    /**
     * The table <code>public.definition_source_link</code>.
     */
    public static final DefinitionSourceLink DEFINITION_SOURCE_LINK = DefinitionSourceLink.DEFINITION_SOURCE_LINK;

    /**
     * The table <code>public.definition_type</code>.
     */
    public static final DefinitionType DEFINITION_TYPE = DefinitionType.DEFINITION_TYPE;

    /**
     * The table <code>public.definition_type_label</code>.
     */
    public static final DefinitionTypeLabel DEFINITION_TYPE_LABEL = DefinitionTypeLabel.DEFINITION_TYPE_LABEL;

    /**
     * The table <code>public.deriv</code>.
     */
    public static final Deriv DERIV = Deriv.DERIV;

    /**
     * The table <code>public.deriv_label</code>.
     */
    public static final DerivLabel DERIV_LABEL = DerivLabel.DERIV_LABEL;

    /**
     * The table <code>public.display_morph</code>.
     */
    public static final DisplayMorph DISPLAY_MORPH = DisplayMorph.DISPLAY_MORPH;

    /**
     * The table <code>public.display_morph_label</code>.
     */
    public static final DisplayMorphLabel DISPLAY_MORPH_LABEL = DisplayMorphLabel.DISPLAY_MORPH_LABEL;

    /**
     * The table <code>public.domain</code>.
     */
    public static final Domain DOMAIN = Domain.DOMAIN;

    /**
     * The table <code>public.domain_label</code>.
     */
    public static final DomainLabel DOMAIN_LABEL = DomainLabel.DOMAIN_LABEL;

    /**
     * The table <code>public.eki_user</code>.
     */
    public static final EkiUser EKI_USER = EkiUser.EKI_USER;

    /**
     * The table <code>public.eki_user_application</code>.
     */
    public static final EkiUserApplication EKI_USER_APPLICATION = EkiUserApplication.EKI_USER_APPLICATION;

    /**
     * The table <code>public.eki_user_profile</code>.
     */
    public static final EkiUserProfile EKI_USER_PROFILE = EkiUserProfile.EKI_USER_PROFILE;

    /**
     * The table <code>public.etymology_type</code>.
     */
    public static final EtymologyType ETYMOLOGY_TYPE = EtymologyType.ETYMOLOGY_TYPE;

    /**
     * The table <code>public.feedback_log</code>.
     */
    public static final FeedbackLog FEEDBACK_LOG = FeedbackLog.FEEDBACK_LOG;

    /**
     * The table <code>public.feedback_log_comment</code>.
     */
    public static final FeedbackLogComment FEEDBACK_LOG_COMMENT = FeedbackLogComment.FEEDBACK_LOG_COMMENT;

    /**
     * The table <code>public.form</code>.
     */
    public static final Form FORM = Form.FORM;

    /**
     * The table <code>public.form_freq</code>.
     */
    public static final FormFreq FORM_FREQ = FormFreq.FORM_FREQ;

    /**
     * The table <code>public.freeform</code>.
     */
    public static final Freeform FREEFORM = Freeform.FREEFORM;

    /**
     * The table <code>public.freeform_source_link</code>.
     */
    public static final FreeformSourceLink FREEFORM_SOURCE_LINK = FreeformSourceLink.FREEFORM_SOURCE_LINK;

    /**
     * The table <code>public.freq_corp</code>.
     */
    public static final FreqCorp FREQ_CORP = FreqCorp.FREQ_CORP;

    /**
     * The table <code>public.game_nonword</code>.
     */
    public static final GameNonword GAME_NONWORD = GameNonword.GAME_NONWORD;

    /**
     * The table <code>public.gender</code>.
     */
    public static final Gender GENDER = Gender.GENDER;

    /**
     * The table <code>public.gender_label</code>.
     */
    public static final GenderLabel GENDER_LABEL = GenderLabel.GENDER_LABEL;

    /**
     * The table <code>public.government_type</code>.
     */
    public static final GovernmentType GOVERNMENT_TYPE = GovernmentType.GOVERNMENT_TYPE;

    /**
     * The table <code>public.government_type_label</code>.
     */
    public static final GovernmentTypeLabel GOVERNMENT_TYPE_LABEL = GovernmentTypeLabel.GOVERNMENT_TYPE_LABEL;

    /**
     * The table <code>public.label_type</code>.
     */
    public static final LabelType LABEL_TYPE = LabelType.LABEL_TYPE;

    /**
     * The table <code>public.language</code>.
     */
    public static final Language LANGUAGE = Language.LANGUAGE;

    /**
     * The table <code>public.language_label</code>.
     */
    public static final LanguageLabel LANGUAGE_LABEL = LanguageLabel.LANGUAGE_LABEL;

    /**
     * The table <code>public.lex_colloc</code>.
     */
    public static final LexColloc LEX_COLLOC = LexColloc.LEX_COLLOC;

    /**
     * The table <code>public.lex_colloc_pos_group</code>.
     */
    public static final LexCollocPosGroup LEX_COLLOC_POS_GROUP = LexCollocPosGroup.LEX_COLLOC_POS_GROUP;

    /**
     * The table <code>public.lex_colloc_rel_group</code>.
     */
    public static final LexCollocRelGroup LEX_COLLOC_REL_GROUP = LexCollocRelGroup.LEX_COLLOC_REL_GROUP;

    /**
     * The table <code>public.lex_rel_mapping</code>.
     */
    public static final LexRelMapping LEX_REL_MAPPING = LexRelMapping.LEX_REL_MAPPING;

    /**
     * The table <code>public.lex_rel_type</code>.
     */
    public static final LexRelType LEX_REL_TYPE = LexRelType.LEX_REL_TYPE;

    /**
     * The table <code>public.lex_rel_type_label</code>.
     */
    public static final LexRelTypeLabel LEX_REL_TYPE_LABEL = LexRelTypeLabel.LEX_REL_TYPE_LABEL;

    /**
     * The table <code>public.lex_relation</code>.
     */
    public static final LexRelation LEX_RELATION = LexRelation.LEX_RELATION;

    /**
     * The table <code>public.lexeme</code>.
     */
    public static final Lexeme LEXEME = Lexeme.LEXEME;

    /**
     * The table <code>public.lexeme_activity_log</code>.
     */
    public static final LexemeActivityLog LEXEME_ACTIVITY_LOG = LexemeActivityLog.LEXEME_ACTIVITY_LOG;

    /**
     * The table <code>public.lexeme_deriv</code>.
     */
    public static final LexemeDeriv LEXEME_DERIV = LexemeDeriv.LEXEME_DERIV;

    /**
     * The table <code>public.lexeme_freeform</code>.
     */
    public static final LexemeFreeform LEXEME_FREEFORM = LexemeFreeform.LEXEME_FREEFORM;

    /**
     * The table <code>public.lexeme_pos</code>.
     */
    public static final LexemePos LEXEME_POS = LexemePos.LEXEME_POS;

    /**
     * The table <code>public.lexeme_region</code>.
     */
    public static final LexemeRegion LEXEME_REGION = LexemeRegion.LEXEME_REGION;

    /**
     * The table <code>public.lexeme_register</code>.
     */
    public static final LexemeRegister LEXEME_REGISTER = LexemeRegister.LEXEME_REGISTER;

    /**
     * The table <code>public.lexeme_source_link</code>.
     */
    public static final LexemeSourceLink LEXEME_SOURCE_LINK = LexemeSourceLink.LEXEME_SOURCE_LINK;

    /**
     * The table <code>public.lexeme_tag</code>.
     */
    public static final LexemeTag LEXEME_TAG = LexemeTag.LEXEME_TAG;

    /**
     * The table <code>public.meaning</code>.
     */
    public static final Meaning MEANING = Meaning.MEANING;

    /**
     * The table <code>public.meaning_activity_log</code>.
     */
    public static final MeaningActivityLog MEANING_ACTIVITY_LOG = MeaningActivityLog.MEANING_ACTIVITY_LOG;

    /**
     * The table <code>public.meaning_domain</code>.
     */
    public static final MeaningDomain MEANING_DOMAIN = MeaningDomain.MEANING_DOMAIN;

    /**
     * The table <code>public.meaning_freeform</code>.
     */
    public static final MeaningFreeform MEANING_FREEFORM = MeaningFreeform.MEANING_FREEFORM;

    /**
     * The table <code>public.meaning_last_activity_log</code>.
     */
    public static final MeaningLastActivityLog MEANING_LAST_ACTIVITY_LOG = MeaningLastActivityLog.MEANING_LAST_ACTIVITY_LOG;

    /**
     * The table <code>public.meaning_nr</code>.
     */
    public static final MeaningNr MEANING_NR = MeaningNr.MEANING_NR;

    /**
     * The table <code>public.meaning_rel_mapping</code>.
     */
    public static final MeaningRelMapping MEANING_REL_MAPPING = MeaningRelMapping.MEANING_REL_MAPPING;

    /**
     * The table <code>public.meaning_rel_type</code>.
     */
    public static final MeaningRelType MEANING_REL_TYPE = MeaningRelType.MEANING_REL_TYPE;

    /**
     * The table <code>public.meaning_rel_type_label</code>.
     */
    public static final MeaningRelTypeLabel MEANING_REL_TYPE_LABEL = MeaningRelTypeLabel.MEANING_REL_TYPE_LABEL;

    /**
     * The table <code>public.meaning_relation</code>.
     */
    public static final MeaningRelation MEANING_RELATION = MeaningRelation.MEANING_RELATION;

    /**
     * The table <code>public.meaning_semantic_type</code>.
     */
    public static final MeaningSemanticType MEANING_SEMANTIC_TYPE = MeaningSemanticType.MEANING_SEMANTIC_TYPE;

    /**
     * The table <code>public.meaning_tag</code>.
     */
    public static final MeaningTag MEANING_TAG = MeaningTag.MEANING_TAG;

    /**
     * The table <code>public.morph</code>.
     */
    public static final Morph MORPH = Morph.MORPH;

    /**
     * The table <code>public.morph_freq</code>.
     */
    public static final MorphFreq MORPH_FREQ = MorphFreq.MORPH_FREQ;

    /**
     * The table <code>public.morph_label</code>.
     */
    public static final MorphLabel MORPH_LABEL = MorphLabel.MORPH_LABEL;

    /**
     * The table <code>public.paradigm</code>.
     */
    public static final Paradigm PARADIGM = Paradigm.PARADIGM;

    /**
     * The table <code>public.pos</code>.
     */
    public static final Pos POS = Pos.POS;

    /**
     * The table <code>public.pos_group</code>.
     */
    public static final PosGroup POS_GROUP = PosGroup.POS_GROUP;

    /**
     * The table <code>public.pos_group_label</code>.
     */
    public static final PosGroupLabel POS_GROUP_LABEL = PosGroupLabel.POS_GROUP_LABEL;

    /**
     * The table <code>public.pos_label</code>.
     */
    public static final PosLabel POS_LABEL = PosLabel.POS_LABEL;

    /**
     * The table <code>public.proficiency_level</code>.
     */
    public static final ProficiencyLevel PROFICIENCY_LEVEL = ProficiencyLevel.PROFICIENCY_LEVEL;

    /**
     * The table <code>public.proficiency_level_label</code>.
     */
    public static final ProficiencyLevelLabel PROFICIENCY_LEVEL_LABEL = ProficiencyLevelLabel.PROFICIENCY_LEVEL_LABEL;

    /**
     * The table <code>public.region</code>.
     */
    public static final Region REGION = Region.REGION;

    /**
     * The table <code>public.register</code>.
     */
    public static final Register REGISTER = Register.REGISTER;

    /**
     * The table <code>public.register_label</code>.
     */
    public static final RegisterLabel REGISTER_LABEL = RegisterLabel.REGISTER_LABEL;

    /**
     * The table <code>public.semantic_type</code>.
     */
    public static final SemanticType SEMANTIC_TYPE = SemanticType.SEMANTIC_TYPE;

    /**
     * The table <code>public.semantic_type_label</code>.
     */
    public static final SemanticTypeLabel SEMANTIC_TYPE_LABEL = SemanticTypeLabel.SEMANTIC_TYPE_LABEL;

    /**
     * The table <code>public.source</code>.
     */
    public static final Source SOURCE = Source.SOURCE;

    /**
     * The table <code>public.source_activity_log</code>.
     */
    public static final SourceActivityLog SOURCE_ACTIVITY_LOG = SourceActivityLog.SOURCE_ACTIVITY_LOG;

    /**
     * The table <code>public.source_freeform</code>.
     */
    public static final SourceFreeform SOURCE_FREEFORM = SourceFreeform.SOURCE_FREEFORM;

    /**
     * The table <code>public.tag</code>.
     */
    public static final Tag TAG = Tag.TAG;

    /**
     * The table <code>public.temp_ds_import_pk_map</code>.
     */
    public static final TempDsImportPkMap TEMP_DS_IMPORT_PK_MAP = TempDsImportPkMap.TEMP_DS_IMPORT_PK_MAP;

    /**
     * The table <code>public.temp_ds_import_queue</code>.
     */
    public static final TempDsImportQueue TEMP_DS_IMPORT_QUEUE = TempDsImportQueue.TEMP_DS_IMPORT_QUEUE;

    /**
     * The table <code>public.terms_of_use</code>.
     */
    public static final TermsOfUse TERMS_OF_USE = TermsOfUse.TERMS_OF_USE;

    /**
     * The table <code>public.usage_type</code>.
     */
    public static final UsageType USAGE_TYPE = UsageType.USAGE_TYPE;

    /**
     * The table <code>public.usage_type_label</code>.
     */
    public static final UsageTypeLabel USAGE_TYPE_LABEL = UsageTypeLabel.USAGE_TYPE_LABEL;

    /**
     * The table <code>public.value_state</code>.
     */
    public static final ValueState VALUE_STATE = ValueState.VALUE_STATE;

    /**
     * The table <code>public.value_state_label</code>.
     */
    public static final ValueStateLabel VALUE_STATE_LABEL = ValueStateLabel.VALUE_STATE_LABEL;

    /**
     * The table <code>public.view_ww_classifier</code>.
     */
    public static final ViewWwClassifier VIEW_WW_CLASSIFIER = ViewWwClassifier.VIEW_WW_CLASSIFIER;

    /**
     * The table <code>public.view_ww_collocation</code>.
     */
    public static final ViewWwCollocation VIEW_WW_COLLOCATION = ViewWwCollocation.VIEW_WW_COLLOCATION;

    /**
     * The table <code>public.view_ww_dataset</code>.
     */
    public static final ViewWwDataset VIEW_WW_DATASET = ViewWwDataset.VIEW_WW_DATASET;

    /**
     * The table <code>public.view_ww_dataset_word_menu</code>.
     */
    public static final ViewWwDatasetWordMenu VIEW_WW_DATASET_WORD_MENU = ViewWwDatasetWordMenu.VIEW_WW_DATASET_WORD_MENU;

    /**
     * The table <code>public.view_ww_definition_source_link</code>.
     */
    public static final ViewWwDefinitionSourceLink VIEW_WW_DEFINITION_SOURCE_LINK = ViewWwDefinitionSourceLink.VIEW_WW_DEFINITION_SOURCE_LINK;

    /**
     * The table <code>public.view_ww_form</code>.
     */
    public static final ViewWwForm VIEW_WW_FORM = ViewWwForm.VIEW_WW_FORM;

    /**
     * The table <code>public.view_ww_lexeme</code>.
     */
    public static final ViewWwLexeme VIEW_WW_LEXEME = ViewWwLexeme.VIEW_WW_LEXEME;

    /**
     * The table <code>public.view_ww_lexeme_freeform_source_link</code>.
     */
    public static final ViewWwLexemeFreeformSourceLink VIEW_WW_LEXEME_FREEFORM_SOURCE_LINK = ViewWwLexemeFreeformSourceLink.VIEW_WW_LEXEME_FREEFORM_SOURCE_LINK;

    /**
     * The table <code>public.view_ww_lexeme_relation</code>.
     */
    public static final ViewWwLexemeRelation VIEW_WW_LEXEME_RELATION = ViewWwLexemeRelation.VIEW_WW_LEXEME_RELATION;

    /**
     * The table <code>public.view_ww_lexeme_source_link</code>.
     */
    public static final ViewWwLexemeSourceLink VIEW_WW_LEXEME_SOURCE_LINK = ViewWwLexemeSourceLink.VIEW_WW_LEXEME_SOURCE_LINK;

    /**
     * The table <code>public.view_ww_lexical_decision_data</code>.
     */
    public static final ViewWwLexicalDecisionData VIEW_WW_LEXICAL_DECISION_DATA = ViewWwLexicalDecisionData.VIEW_WW_LEXICAL_DECISION_DATA;

    /**
     * The table <code>public.view_ww_meaning</code>.
     */
    public static final ViewWwMeaning VIEW_WW_MEANING = ViewWwMeaning.VIEW_WW_MEANING;

    /**
     * The table <code>public.view_ww_meaning_freeform_source_link</code>.
     */
    public static final ViewWwMeaningFreeformSourceLink VIEW_WW_MEANING_FREEFORM_SOURCE_LINK = ViewWwMeaningFreeformSourceLink.VIEW_WW_MEANING_FREEFORM_SOURCE_LINK;

    /**
     * The table <code>public.view_ww_meaning_relation</code>.
     */
    public static final ViewWwMeaningRelation VIEW_WW_MEANING_RELATION = ViewWwMeaningRelation.VIEW_WW_MEANING_RELATION;

    /**
     * The table <code>public.view_ww_similarity_judgement_data</code>.
     */
    public static final ViewWwSimilarityJudgementData VIEW_WW_SIMILARITY_JUDGEMENT_DATA = ViewWwSimilarityJudgementData.VIEW_WW_SIMILARITY_JUDGEMENT_DATA;

    /**
     * The table <code>public.view_ww_word</code>.
     */
    public static final ViewWwWord VIEW_WW_WORD = ViewWwWord.VIEW_WW_WORD;

    /**
     * The table <code>public.view_ww_word_etym_source_link</code>.
     */
    public static final ViewWwWordEtymSourceLink VIEW_WW_WORD_ETYM_SOURCE_LINK = ViewWwWordEtymSourceLink.VIEW_WW_WORD_ETYM_SOURCE_LINK;

    /**
     * The table <code>public.view_ww_word_etymology</code>.
     */
    public static final ViewWwWordEtymology VIEW_WW_WORD_ETYMOLOGY = ViewWwWordEtymology.VIEW_WW_WORD_ETYMOLOGY;

    /**
     * The table <code>public.view_ww_word_relation</code>.
     */
    public static final ViewWwWordRelation VIEW_WW_WORD_RELATION = ViewWwWordRelation.VIEW_WW_WORD_RELATION;

    /**
     * The table <code>public.view_ww_word_search</code>.
     */
    public static final ViewWwWordSearch VIEW_WW_WORD_SEARCH = ViewWwWordSearch.VIEW_WW_WORD_SEARCH;

    /**
     * The table <code>public.word</code>.
     */
    public static final Word WORD = Word.WORD;

    /**
     * The table <code>public.word_activity_log</code>.
     */
    public static final WordActivityLog WORD_ACTIVITY_LOG = WordActivityLog.WORD_ACTIVITY_LOG;

    /**
     * The table <code>public.word_etymology</code>.
     */
    public static final WordEtymology WORD_ETYMOLOGY = WordEtymology.WORD_ETYMOLOGY;

    /**
     * The table <code>public.word_etymology_relation</code>.
     */
    public static final WordEtymologyRelation WORD_ETYMOLOGY_RELATION = WordEtymologyRelation.WORD_ETYMOLOGY_RELATION;

    /**
     * The table <code>public.word_etymology_source_link</code>.
     */
    public static final WordEtymologySourceLink WORD_ETYMOLOGY_SOURCE_LINK = WordEtymologySourceLink.WORD_ETYMOLOGY_SOURCE_LINK;

    /**
     * The table <code>public.word_freeform</code>.
     */
    public static final WordFreeform WORD_FREEFORM = WordFreeform.WORD_FREEFORM;

    /**
     * The table <code>public.word_freq</code>.
     */
    public static final WordFreq WORD_FREQ = WordFreq.WORD_FREQ;

    /**
     * The table <code>public.word_group</code>.
     */
    public static final WordGroup WORD_GROUP = WordGroup.WORD_GROUP;

    /**
     * The table <code>public.word_group_member</code>.
     */
    public static final WordGroupMember WORD_GROUP_MEMBER = WordGroupMember.WORD_GROUP_MEMBER;

    /**
     * The table <code>public.word_guid</code>.
     */
    public static final WordGuid WORD_GUID = WordGuid.WORD_GUID;

    /**
     * The table <code>public.word_last_activity_log</code>.
     */
    public static final WordLastActivityLog WORD_LAST_ACTIVITY_LOG = WordLastActivityLog.WORD_LAST_ACTIVITY_LOG;

    /**
     * The table <code>public.word_rel_mapping</code>.
     */
    public static final WordRelMapping WORD_REL_MAPPING = WordRelMapping.WORD_REL_MAPPING;

    /**
     * The table <code>public.word_rel_type</code>.
     */
    public static final WordRelType WORD_REL_TYPE = WordRelType.WORD_REL_TYPE;

    /**
     * The table <code>public.word_rel_type_label</code>.
     */
    public static final WordRelTypeLabel WORD_REL_TYPE_LABEL = WordRelTypeLabel.WORD_REL_TYPE_LABEL;

    /**
     * The table <code>public.word_relation</code>.
     */
    public static final WordRelation WORD_RELATION = WordRelation.WORD_RELATION;

    /**
     * The table <code>public.word_relation_param</code>.
     */
    public static final WordRelationParam WORD_RELATION_PARAM = WordRelationParam.WORD_RELATION_PARAM;

    /**
     * The table <code>public.word_type</code>.
     */
    public static final WordType WORD_TYPE = WordType.WORD_TYPE;

    /**
     * The table <code>public.word_type_label</code>.
     */
    public static final WordTypeLabel WORD_TYPE_LABEL = WordTypeLabel.WORD_TYPE_LABEL;

    /**
     * The table <code>public.word_word_type</code>.
     */
    public static final WordWordType WORD_WORD_TYPE = WordWordType.WORD_WORD_TYPE;
}
