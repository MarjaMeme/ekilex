/*
 * This file is generated by jOOQ.
 */
package eki.ekilex.data.db;


import eki.ekilex.data.db.tables.AspectType;
import eki.ekilex.data.db.tables.AspectTypeLabel;
import eki.ekilex.data.db.tables.Collocation;
import eki.ekilex.data.db.tables.CollocationFreeform;
import eki.ekilex.data.db.tables.Dataset;
import eki.ekilex.data.db.tables.Definition;
import eki.ekilex.data.db.tables.DefinitionDataset;
import eki.ekilex.data.db.tables.DefinitionFreeform;
import eki.ekilex.data.db.tables.DefinitionSourceLink;
import eki.ekilex.data.db.tables.Deriv;
import eki.ekilex.data.db.tables.DerivLabel;
import eki.ekilex.data.db.tables.DisplayMorph;
import eki.ekilex.data.db.tables.DisplayMorphLabel;
import eki.ekilex.data.db.tables.Domain;
import eki.ekilex.data.db.tables.DomainLabel;
import eki.ekilex.data.db.tables.EkiUser;
import eki.ekilex.data.db.tables.EtymologyType;
import eki.ekilex.data.db.tables.Form;
import eki.ekilex.data.db.tables.FormRelType;
import eki.ekilex.data.db.tables.FormRelTypeLabel;
import eki.ekilex.data.db.tables.FormRelation;
import eki.ekilex.data.db.tables.Freeform;
import eki.ekilex.data.db.tables.FreeformSourceLink;
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
import eki.ekilex.data.db.tables.LexRelType;
import eki.ekilex.data.db.tables.LexRelTypeLabel;
import eki.ekilex.data.db.tables.LexRelation;
import eki.ekilex.data.db.tables.Lexeme;
import eki.ekilex.data.db.tables.LexemeDeriv;
import eki.ekilex.data.db.tables.LexemeFreeform;
import eki.ekilex.data.db.tables.LexemeFrequency;
import eki.ekilex.data.db.tables.LexemeLifecycleLog;
import eki.ekilex.data.db.tables.LexemePos;
import eki.ekilex.data.db.tables.LexemeRegister;
import eki.ekilex.data.db.tables.LexemeSourceLink;
import eki.ekilex.data.db.tables.LifecycleLog;
import eki.ekilex.data.db.tables.Meaning;
import eki.ekilex.data.db.tables.MeaningDomain;
import eki.ekilex.data.db.tables.MeaningFreeform;
import eki.ekilex.data.db.tables.MeaningLifecycleLog;
import eki.ekilex.data.db.tables.MeaningRelType;
import eki.ekilex.data.db.tables.MeaningRelTypeLabel;
import eki.ekilex.data.db.tables.MeaningRelation;
import eki.ekilex.data.db.tables.Morph;
import eki.ekilex.data.db.tables.MorphLabel;
import eki.ekilex.data.db.tables.Paradigm;
import eki.ekilex.data.db.tables.Pos;
import eki.ekilex.data.db.tables.PosGroup;
import eki.ekilex.data.db.tables.PosGroupLabel;
import eki.ekilex.data.db.tables.PosLabel;
import eki.ekilex.data.db.tables.ProcessState;
import eki.ekilex.data.db.tables.Register;
import eki.ekilex.data.db.tables.RegisterLabel;
import eki.ekilex.data.db.tables.Source;
import eki.ekilex.data.db.tables.SourceFreeform;
import eki.ekilex.data.db.tables.UsageType;
import eki.ekilex.data.db.tables.UsageTypeLabel;
import eki.ekilex.data.db.tables.ValueState;
import eki.ekilex.data.db.tables.ValueStateLabel;
import eki.ekilex.data.db.tables.ViewWwAsWord;
import eki.ekilex.data.db.tables.ViewWwClassifier;
import eki.ekilex.data.db.tables.ViewWwCollocation;
import eki.ekilex.data.db.tables.ViewWwDataset;
import eki.ekilex.data.db.tables.ViewWwForm;
import eki.ekilex.data.db.tables.ViewWwLexeme;
import eki.ekilex.data.db.tables.ViewWwLexemeRelation;
import eki.ekilex.data.db.tables.ViewWwLexicalDecisionData;
import eki.ekilex.data.db.tables.ViewWwMeaning;
import eki.ekilex.data.db.tables.ViewWwMeaningRelation;
import eki.ekilex.data.db.tables.ViewWwWord;
import eki.ekilex.data.db.tables.ViewWwWordEtymology;
import eki.ekilex.data.db.tables.ViewWwWordRelation;
import eki.ekilex.data.db.tables.Word;
import eki.ekilex.data.db.tables.WordEtymology;
import eki.ekilex.data.db.tables.WordEtymologySourceLink;
import eki.ekilex.data.db.tables.WordGroup;
import eki.ekilex.data.db.tables.WordGroupMember;
import eki.ekilex.data.db.tables.WordGuid;
import eki.ekilex.data.db.tables.WordLifecycleLog;
import eki.ekilex.data.db.tables.WordRelType;
import eki.ekilex.data.db.tables.WordRelTypeLabel;
import eki.ekilex.data.db.tables.WordRelation;
import eki.ekilex.data.db.tables.WordType;
import eki.ekilex.data.db.tables.WordTypeLabel;
import eki.ekilex.data.db.udt.TypeCollocMember;
import eki.ekilex.data.db.udt.TypeDefinition;
import eki.ekilex.data.db.udt.TypeDomain;
import eki.ekilex.data.db.udt.TypeLexemeRelation;
import eki.ekilex.data.db.udt.TypeMeaningRelation;
import eki.ekilex.data.db.udt.TypeUsage;
import eki.ekilex.data.db.udt.TypeWord;
import eki.ekilex.data.db.udt.TypeWordEtym;
import eki.ekilex.data.db.udt.TypeWordRelation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Catalog;
import org.jooq.Sequence;
import org.jooq.Table;
import org.jooq.UDT;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.5"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Public extends SchemaImpl {

    private static final long serialVersionUID = -843638876;

    /**
     * The reference instance of <code>public</code>
     */
    public static final Public PUBLIC = new Public();

    /**
     * The table <code>public.aspect_type</code>.
     */
    public final AspectType ASPECT_TYPE = eki.ekilex.data.db.tables.AspectType.ASPECT_TYPE;

    /**
     * The table <code>public.aspect_type_label</code>.
     */
    public final AspectTypeLabel ASPECT_TYPE_LABEL = eki.ekilex.data.db.tables.AspectTypeLabel.ASPECT_TYPE_LABEL;

    /**
     * The table <code>public.collocation</code>.
     */
    public final Collocation COLLOCATION = eki.ekilex.data.db.tables.Collocation.COLLOCATION;

    /**
     * The table <code>public.collocation_freeform</code>.
     */
    public final CollocationFreeform COLLOCATION_FREEFORM = eki.ekilex.data.db.tables.CollocationFreeform.COLLOCATION_FREEFORM;

    /**
     * The table <code>public.dataset</code>.
     */
    public final Dataset DATASET = eki.ekilex.data.db.tables.Dataset.DATASET;

    /**
     * The table <code>public.definition</code>.
     */
    public final Definition DEFINITION = eki.ekilex.data.db.tables.Definition.DEFINITION;

    /**
     * The table <code>public.definition_dataset</code>.
     */
    public final DefinitionDataset DEFINITION_DATASET = eki.ekilex.data.db.tables.DefinitionDataset.DEFINITION_DATASET;

    /**
     * The table <code>public.definition_freeform</code>.
     */
    public final DefinitionFreeform DEFINITION_FREEFORM = eki.ekilex.data.db.tables.DefinitionFreeform.DEFINITION_FREEFORM;

    /**
     * The table <code>public.definition_source_link</code>.
     */
    public final DefinitionSourceLink DEFINITION_SOURCE_LINK = eki.ekilex.data.db.tables.DefinitionSourceLink.DEFINITION_SOURCE_LINK;

    /**
     * The table <code>public.deriv</code>.
     */
    public final Deriv DERIV = eki.ekilex.data.db.tables.Deriv.DERIV;

    /**
     * The table <code>public.deriv_label</code>.
     */
    public final DerivLabel DERIV_LABEL = eki.ekilex.data.db.tables.DerivLabel.DERIV_LABEL;

    /**
     * The table <code>public.display_morph</code>.
     */
    public final DisplayMorph DISPLAY_MORPH = eki.ekilex.data.db.tables.DisplayMorph.DISPLAY_MORPH;

    /**
     * The table <code>public.display_morph_label</code>.
     */
    public final DisplayMorphLabel DISPLAY_MORPH_LABEL = eki.ekilex.data.db.tables.DisplayMorphLabel.DISPLAY_MORPH_LABEL;

    /**
     * The table <code>public.domain</code>.
     */
    public final Domain DOMAIN = eki.ekilex.data.db.tables.Domain.DOMAIN;

    /**
     * The table <code>public.domain_label</code>.
     */
    public final DomainLabel DOMAIN_LABEL = eki.ekilex.data.db.tables.DomainLabel.DOMAIN_LABEL;

    /**
     * The table <code>public.eki_user</code>.
     */
    public final EkiUser EKI_USER = eki.ekilex.data.db.tables.EkiUser.EKI_USER;

    /**
     * The table <code>public.etymology_type</code>.
     */
    public final EtymologyType ETYMOLOGY_TYPE = eki.ekilex.data.db.tables.EtymologyType.ETYMOLOGY_TYPE;

    /**
     * The table <code>public.form</code>.
     */
    public final Form FORM = eki.ekilex.data.db.tables.Form.FORM;

    /**
     * The table <code>public.form_rel_type</code>.
     */
    public final FormRelType FORM_REL_TYPE = eki.ekilex.data.db.tables.FormRelType.FORM_REL_TYPE;

    /**
     * The table <code>public.form_rel_type_label</code>.
     */
    public final FormRelTypeLabel FORM_REL_TYPE_LABEL = eki.ekilex.data.db.tables.FormRelTypeLabel.FORM_REL_TYPE_LABEL;

    /**
     * The table <code>public.form_relation</code>.
     */
    public final FormRelation FORM_RELATION = eki.ekilex.data.db.tables.FormRelation.FORM_RELATION;

    /**
     * The table <code>public.freeform</code>.
     */
    public final Freeform FREEFORM = eki.ekilex.data.db.tables.Freeform.FREEFORM;

    /**
     * The table <code>public.freeform_source_link</code>.
     */
    public final FreeformSourceLink FREEFORM_SOURCE_LINK = eki.ekilex.data.db.tables.FreeformSourceLink.FREEFORM_SOURCE_LINK;

    /**
     * The table <code>public.game_nonword</code>.
     */
    public final GameNonword GAME_NONWORD = eki.ekilex.data.db.tables.GameNonword.GAME_NONWORD;

    /**
     * The table <code>public.gender</code>.
     */
    public final Gender GENDER = eki.ekilex.data.db.tables.Gender.GENDER;

    /**
     * The table <code>public.gender_label</code>.
     */
    public final GenderLabel GENDER_LABEL = eki.ekilex.data.db.tables.GenderLabel.GENDER_LABEL;

    /**
     * The table <code>public.government_type</code>.
     */
    public final GovernmentType GOVERNMENT_TYPE = eki.ekilex.data.db.tables.GovernmentType.GOVERNMENT_TYPE;

    /**
     * The table <code>public.government_type_label</code>.
     */
    public final GovernmentTypeLabel GOVERNMENT_TYPE_LABEL = eki.ekilex.data.db.tables.GovernmentTypeLabel.GOVERNMENT_TYPE_LABEL;

    /**
     * The table <code>public.label_type</code>.
     */
    public final LabelType LABEL_TYPE = eki.ekilex.data.db.tables.LabelType.LABEL_TYPE;

    /**
     * The table <code>public.language</code>.
     */
    public final Language LANGUAGE = eki.ekilex.data.db.tables.Language.LANGUAGE;

    /**
     * The table <code>public.language_label</code>.
     */
    public final LanguageLabel LANGUAGE_LABEL = eki.ekilex.data.db.tables.LanguageLabel.LANGUAGE_LABEL;

    /**
     * The table <code>public.lex_colloc</code>.
     */
    public final LexColloc LEX_COLLOC = eki.ekilex.data.db.tables.LexColloc.LEX_COLLOC;

    /**
     * The table <code>public.lex_colloc_pos_group</code>.
     */
    public final LexCollocPosGroup LEX_COLLOC_POS_GROUP = eki.ekilex.data.db.tables.LexCollocPosGroup.LEX_COLLOC_POS_GROUP;

    /**
     * The table <code>public.lex_colloc_rel_group</code>.
     */
    public final LexCollocRelGroup LEX_COLLOC_REL_GROUP = eki.ekilex.data.db.tables.LexCollocRelGroup.LEX_COLLOC_REL_GROUP;

    /**
     * The table <code>public.lex_rel_type</code>.
     */
    public final LexRelType LEX_REL_TYPE = eki.ekilex.data.db.tables.LexRelType.LEX_REL_TYPE;

    /**
     * The table <code>public.lex_rel_type_label</code>.
     */
    public final LexRelTypeLabel LEX_REL_TYPE_LABEL = eki.ekilex.data.db.tables.LexRelTypeLabel.LEX_REL_TYPE_LABEL;

    /**
     * The table <code>public.lex_relation</code>.
     */
    public final LexRelation LEX_RELATION = eki.ekilex.data.db.tables.LexRelation.LEX_RELATION;

    /**
     * The table <code>public.lexeme</code>.
     */
    public final Lexeme LEXEME = eki.ekilex.data.db.tables.Lexeme.LEXEME;

    /**
     * The table <code>public.lexeme_deriv</code>.
     */
    public final LexemeDeriv LEXEME_DERIV = eki.ekilex.data.db.tables.LexemeDeriv.LEXEME_DERIV;

    /**
     * The table <code>public.lexeme_freeform</code>.
     */
    public final LexemeFreeform LEXEME_FREEFORM = eki.ekilex.data.db.tables.LexemeFreeform.LEXEME_FREEFORM;

    /**
     * The table <code>public.lexeme_frequency</code>.
     */
    public final LexemeFrequency LEXEME_FREQUENCY = eki.ekilex.data.db.tables.LexemeFrequency.LEXEME_FREQUENCY;

    /**
     * The table <code>public.lexeme_lifecycle_log</code>.
     */
    public final LexemeLifecycleLog LEXEME_LIFECYCLE_LOG = eki.ekilex.data.db.tables.LexemeLifecycleLog.LEXEME_LIFECYCLE_LOG;

    /**
     * The table <code>public.lexeme_pos</code>.
     */
    public final LexemePos LEXEME_POS = eki.ekilex.data.db.tables.LexemePos.LEXEME_POS;

    /**
     * The table <code>public.lexeme_register</code>.
     */
    public final LexemeRegister LEXEME_REGISTER = eki.ekilex.data.db.tables.LexemeRegister.LEXEME_REGISTER;

    /**
     * The table <code>public.lexeme_source_link</code>.
     */
    public final LexemeSourceLink LEXEME_SOURCE_LINK = eki.ekilex.data.db.tables.LexemeSourceLink.LEXEME_SOURCE_LINK;

    /**
     * The table <code>public.lifecycle_log</code>.
     */
    public final LifecycleLog LIFECYCLE_LOG = eki.ekilex.data.db.tables.LifecycleLog.LIFECYCLE_LOG;

    /**
     * The table <code>public.meaning</code>.
     */
    public final Meaning MEANING = eki.ekilex.data.db.tables.Meaning.MEANING;

    /**
     * The table <code>public.meaning_domain</code>.
     */
    public final MeaningDomain MEANING_DOMAIN = eki.ekilex.data.db.tables.MeaningDomain.MEANING_DOMAIN;

    /**
     * The table <code>public.meaning_freeform</code>.
     */
    public final MeaningFreeform MEANING_FREEFORM = eki.ekilex.data.db.tables.MeaningFreeform.MEANING_FREEFORM;

    /**
     * The table <code>public.meaning_lifecycle_log</code>.
     */
    public final MeaningLifecycleLog MEANING_LIFECYCLE_LOG = eki.ekilex.data.db.tables.MeaningLifecycleLog.MEANING_LIFECYCLE_LOG;

    /**
     * The table <code>public.meaning_rel_type</code>.
     */
    public final MeaningRelType MEANING_REL_TYPE = eki.ekilex.data.db.tables.MeaningRelType.MEANING_REL_TYPE;

    /**
     * The table <code>public.meaning_rel_type_label</code>.
     */
    public final MeaningRelTypeLabel MEANING_REL_TYPE_LABEL = eki.ekilex.data.db.tables.MeaningRelTypeLabel.MEANING_REL_TYPE_LABEL;

    /**
     * The table <code>public.meaning_relation</code>.
     */
    public final MeaningRelation MEANING_RELATION = eki.ekilex.data.db.tables.MeaningRelation.MEANING_RELATION;

    /**
     * The table <code>public.morph</code>.
     */
    public final Morph MORPH = eki.ekilex.data.db.tables.Morph.MORPH;

    /**
     * The table <code>public.morph_label</code>.
     */
    public final MorphLabel MORPH_LABEL = eki.ekilex.data.db.tables.MorphLabel.MORPH_LABEL;

    /**
     * The table <code>public.paradigm</code>.
     */
    public final Paradigm PARADIGM = eki.ekilex.data.db.tables.Paradigm.PARADIGM;

    /**
     * The table <code>public.pos</code>.
     */
    public final Pos POS = eki.ekilex.data.db.tables.Pos.POS;

    /**
     * The table <code>public.pos_group</code>.
     */
    public final PosGroup POS_GROUP = eki.ekilex.data.db.tables.PosGroup.POS_GROUP;

    /**
     * The table <code>public.pos_group_label</code>.
     */
    public final PosGroupLabel POS_GROUP_LABEL = eki.ekilex.data.db.tables.PosGroupLabel.POS_GROUP_LABEL;

    /**
     * The table <code>public.pos_label</code>.
     */
    public final PosLabel POS_LABEL = eki.ekilex.data.db.tables.PosLabel.POS_LABEL;

    /**
     * The table <code>public.process_state</code>.
     */
    public final ProcessState PROCESS_STATE = eki.ekilex.data.db.tables.ProcessState.PROCESS_STATE;

    /**
     * The table <code>public.register</code>.
     */
    public final Register REGISTER = eki.ekilex.data.db.tables.Register.REGISTER;

    /**
     * The table <code>public.register_label</code>.
     */
    public final RegisterLabel REGISTER_LABEL = eki.ekilex.data.db.tables.RegisterLabel.REGISTER_LABEL;

    /**
     * The table <code>public.source</code>.
     */
    public final Source SOURCE = eki.ekilex.data.db.tables.Source.SOURCE;

    /**
     * The table <code>public.source_freeform</code>.
     */
    public final SourceFreeform SOURCE_FREEFORM = eki.ekilex.data.db.tables.SourceFreeform.SOURCE_FREEFORM;

    /**
     * The table <code>public.usage_type</code>.
     */
    public final UsageType USAGE_TYPE = eki.ekilex.data.db.tables.UsageType.USAGE_TYPE;

    /**
     * The table <code>public.usage_type_label</code>.
     */
    public final UsageTypeLabel USAGE_TYPE_LABEL = eki.ekilex.data.db.tables.UsageTypeLabel.USAGE_TYPE_LABEL;

    /**
     * The table <code>public.value_state</code>.
     */
    public final ValueState VALUE_STATE = eki.ekilex.data.db.tables.ValueState.VALUE_STATE;

    /**
     * The table <code>public.value_state_label</code>.
     */
    public final ValueStateLabel VALUE_STATE_LABEL = eki.ekilex.data.db.tables.ValueStateLabel.VALUE_STATE_LABEL;

    /**
     * The table <code>public.view_ww_as_word</code>.
     */
    public final ViewWwAsWord VIEW_WW_AS_WORD = eki.ekilex.data.db.tables.ViewWwAsWord.VIEW_WW_AS_WORD;

    /**
     * The table <code>public.view_ww_classifier</code>.
     */
    public final ViewWwClassifier VIEW_WW_CLASSIFIER = eki.ekilex.data.db.tables.ViewWwClassifier.VIEW_WW_CLASSIFIER;

    /**
     * The table <code>public.view_ww_collocation</code>.
     */
    public final ViewWwCollocation VIEW_WW_COLLOCATION = eki.ekilex.data.db.tables.ViewWwCollocation.VIEW_WW_COLLOCATION;

    /**
     * The table <code>public.view_ww_dataset</code>.
     */
    public final ViewWwDataset VIEW_WW_DATASET = eki.ekilex.data.db.tables.ViewWwDataset.VIEW_WW_DATASET;

    /**
     * The table <code>public.view_ww_form</code>.
     */
    public final ViewWwForm VIEW_WW_FORM = eki.ekilex.data.db.tables.ViewWwForm.VIEW_WW_FORM;

    /**
     * The table <code>public.view_ww_lexeme</code>.
     */
    public final ViewWwLexeme VIEW_WW_LEXEME = eki.ekilex.data.db.tables.ViewWwLexeme.VIEW_WW_LEXEME;

    /**
     * The table <code>public.view_ww_lexeme_relation</code>.
     */
    public final ViewWwLexemeRelation VIEW_WW_LEXEME_RELATION = eki.ekilex.data.db.tables.ViewWwLexemeRelation.VIEW_WW_LEXEME_RELATION;

    /**
     * The table <code>public.view_ww_lexical_decision_data</code>.
     */
    public final ViewWwLexicalDecisionData VIEW_WW_LEXICAL_DECISION_DATA = eki.ekilex.data.db.tables.ViewWwLexicalDecisionData.VIEW_WW_LEXICAL_DECISION_DATA;

    /**
     * The table <code>public.view_ww_meaning</code>.
     */
    public final ViewWwMeaning VIEW_WW_MEANING = eki.ekilex.data.db.tables.ViewWwMeaning.VIEW_WW_MEANING;

    /**
     * The table <code>public.view_ww_meaning_relation</code>.
     */
    public final ViewWwMeaningRelation VIEW_WW_MEANING_RELATION = eki.ekilex.data.db.tables.ViewWwMeaningRelation.VIEW_WW_MEANING_RELATION;

    /**
     * The table <code>public.view_ww_word</code>.
     */
    public final ViewWwWord VIEW_WW_WORD = eki.ekilex.data.db.tables.ViewWwWord.VIEW_WW_WORD;

    /**
     * The table <code>public.view_ww_word_etymology</code>.
     */
    public final ViewWwWordEtymology VIEW_WW_WORD_ETYMOLOGY = eki.ekilex.data.db.tables.ViewWwWordEtymology.VIEW_WW_WORD_ETYMOLOGY;

    /**
     * The table <code>public.view_ww_word_relation</code>.
     */
    public final ViewWwWordRelation VIEW_WW_WORD_RELATION = eki.ekilex.data.db.tables.ViewWwWordRelation.VIEW_WW_WORD_RELATION;

    /**
     * The table <code>public.word</code>.
     */
    public final Word WORD = eki.ekilex.data.db.tables.Word.WORD;

    /**
     * The table <code>public.word_etymology</code>.
     */
    public final WordEtymology WORD_ETYMOLOGY = eki.ekilex.data.db.tables.WordEtymology.WORD_ETYMOLOGY;

    /**
     * The table <code>public.word_etymology_source_link</code>.
     */
    public final WordEtymologySourceLink WORD_ETYMOLOGY_SOURCE_LINK = eki.ekilex.data.db.tables.WordEtymologySourceLink.WORD_ETYMOLOGY_SOURCE_LINK;

    /**
     * The table <code>public.word_group</code>.
     */
    public final WordGroup WORD_GROUP = eki.ekilex.data.db.tables.WordGroup.WORD_GROUP;

    /**
     * The table <code>public.word_group_member</code>.
     */
    public final WordGroupMember WORD_GROUP_MEMBER = eki.ekilex.data.db.tables.WordGroupMember.WORD_GROUP_MEMBER;

    /**
     * The table <code>public.word_guid</code>.
     */
    public final WordGuid WORD_GUID = eki.ekilex.data.db.tables.WordGuid.WORD_GUID;

    /**
     * The table <code>public.word_lifecycle_log</code>.
     */
    public final WordLifecycleLog WORD_LIFECYCLE_LOG = eki.ekilex.data.db.tables.WordLifecycleLog.WORD_LIFECYCLE_LOG;

    /**
     * The table <code>public.word_rel_type</code>.
     */
    public final WordRelType WORD_REL_TYPE = eki.ekilex.data.db.tables.WordRelType.WORD_REL_TYPE;

    /**
     * The table <code>public.word_rel_type_label</code>.
     */
    public final WordRelTypeLabel WORD_REL_TYPE_LABEL = eki.ekilex.data.db.tables.WordRelTypeLabel.WORD_REL_TYPE_LABEL;

    /**
     * The table <code>public.word_relation</code>.
     */
    public final WordRelation WORD_RELATION = eki.ekilex.data.db.tables.WordRelation.WORD_RELATION;

    /**
     * The table <code>public.word_type</code>.
     */
    public final WordType WORD_TYPE = eki.ekilex.data.db.tables.WordType.WORD_TYPE;

    /**
     * The table <code>public.word_type_label</code>.
     */
    public final WordTypeLabel WORD_TYPE_LABEL = eki.ekilex.data.db.tables.WordTypeLabel.WORD_TYPE_LABEL;

    /**
     * No further instances allowed
     */
    private Public() {
        super("public", null);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Sequence<?>> getSequences() {
        List result = new ArrayList();
        result.addAll(getSequences0());
        return result;
    }

    private final List<Sequence<?>> getSequences0() {
        return Arrays.<Sequence<?>>asList(
            Sequences.ASPECT_TYPE_ORDER_BY_SEQ,
            Sequences.COLLOCATION_FREEFORM_ID_SEQ,
            Sequences.COLLOCATION_ID_SEQ,
            Sequences.DATASET_ORDER_BY_SEQ,
            Sequences.DEFINITION_FREEFORM_ID_SEQ,
            Sequences.DEFINITION_ID_SEQ,
            Sequences.DEFINITION_ORDER_BY_SEQ,
            Sequences.DEFINITION_SOURCE_LINK_ID_SEQ,
            Sequences.DEFINITION_SOURCE_LINK_ORDER_BY_SEQ,
            Sequences.DERIV_ORDER_BY_SEQ,
            Sequences.DISPLAY_MORPH_ORDER_BY_SEQ,
            Sequences.DOMAIN_ORDER_BY_SEQ,
            Sequences.EKI_USER_ID_SEQ,
            Sequences.ETYMOLOGY_TYPE_ORDER_BY_SEQ,
            Sequences.FORM_ID_SEQ,
            Sequences.FORM_REL_TYPE_ORDER_BY_SEQ,
            Sequences.FORM_RELATION_ID_SEQ,
            Sequences.FORM_RELATION_ORDER_BY_SEQ,
            Sequences.FREEFORM_ID_SEQ,
            Sequences.FREEFORM_ORDER_BY_SEQ,
            Sequences.FREEFORM_SOURCE_LINK_ID_SEQ,
            Sequences.FREEFORM_SOURCE_LINK_ORDER_BY_SEQ,
            Sequences.GAME_NONWORD_ID_SEQ,
            Sequences.GENDER_ORDER_BY_SEQ,
            Sequences.GOVERNMENT_TYPE_ORDER_BY_SEQ,
            Sequences.LANGUAGE_ORDER_BY_SEQ,
            Sequences.LEX_COLLOC_ID_SEQ,
            Sequences.LEX_COLLOC_POS_GROUP_ID_SEQ,
            Sequences.LEX_COLLOC_POS_GROUP_ORDER_BY_SEQ,
            Sequences.LEX_COLLOC_REL_GROUP_ID_SEQ,
            Sequences.LEX_COLLOC_REL_GROUP_ORDER_BY_SEQ,
            Sequences.LEX_REL_TYPE_ORDER_BY_SEQ,
            Sequences.LEX_RELATION_ID_SEQ,
            Sequences.LEX_RELATION_ORDER_BY_SEQ,
            Sequences.LEXEME_DERIV_ID_SEQ,
            Sequences.LEXEME_FREEFORM_ID_SEQ,
            Sequences.LEXEME_FREQUENCY_ORDER_BY_SEQ,
            Sequences.LEXEME_ID_SEQ,
            Sequences.LEXEME_LIFECYCLE_LOG_ID_SEQ,
            Sequences.LEXEME_ORDER_BY_SEQ,
            Sequences.LEXEME_POS_ID_SEQ,
            Sequences.LEXEME_POS_ORDER_BY_SEQ,
            Sequences.LEXEME_REGISTER_ID_SEQ,
            Sequences.LEXEME_REGISTER_ORDER_BY_SEQ,
            Sequences.LEXEME_SOURCE_LINK_ID_SEQ,
            Sequences.LEXEME_SOURCE_LINK_ORDER_BY_SEQ,
            Sequences.LIFECYCLE_LOG_ID_SEQ,
            Sequences.MEANING_DOMAIN_ID_SEQ,
            Sequences.MEANING_DOMAIN_ORDER_BY_SEQ,
            Sequences.MEANING_FREEFORM_ID_SEQ,
            Sequences.MEANING_ID_SEQ,
            Sequences.MEANING_LIFECYCLE_LOG_ID_SEQ,
            Sequences.MEANING_REL_TYPE_ORDER_BY_SEQ,
            Sequences.MEANING_RELATION_ID_SEQ,
            Sequences.MEANING_RELATION_ORDER_BY_SEQ,
            Sequences.MORPH_ORDER_BY_SEQ,
            Sequences.PARADIGM_ID_SEQ,
            Sequences.POS_GROUP_ORDER_BY_SEQ,
            Sequences.POS_ORDER_BY_SEQ,
            Sequences.PROCESS_STATE_ORDER_BY_SEQ,
            Sequences.REGISTER_ORDER_BY_SEQ,
            Sequences.SOURCE_FREEFORM_ID_SEQ,
            Sequences.SOURCE_ID_SEQ,
            Sequences.USAGE_TYPE_ORDER_BY_SEQ,
            Sequences.VALUE_STATE_ORDER_BY_SEQ,
            Sequences.WORD_ETYMOLOGY_ID_SEQ,
            Sequences.WORD_ETYMOLOGY_ORDER_BY_SEQ,
            Sequences.WORD_ETYMOLOGY_SOURCE_LINK_ID_SEQ,
            Sequences.WORD_ETYMOLOGY_SOURCE_LINK_ORDER_BY_SEQ,
            Sequences.WORD_GROUP_ID_SEQ,
            Sequences.WORD_GROUP_MEMBER_ID_SEQ,
            Sequences.WORD_GROUP_MEMBER_ORDER_BY_SEQ,
            Sequences.WORD_GUID_ID_SEQ,
            Sequences.WORD_ID_SEQ,
            Sequences.WORD_LIFECYCLE_LOG_ID_SEQ,
            Sequences.WORD_REL_TYPE_ORDER_BY_SEQ,
            Sequences.WORD_RELATION_ID_SEQ,
            Sequences.WORD_RELATION_ORDER_BY_SEQ,
            Sequences.WORD_TYPE_ORDER_BY_SEQ);
    }

    @Override
    public final List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(
            AspectType.ASPECT_TYPE,
            AspectTypeLabel.ASPECT_TYPE_LABEL,
            Collocation.COLLOCATION,
            CollocationFreeform.COLLOCATION_FREEFORM,
            Dataset.DATASET,
            Definition.DEFINITION,
            DefinitionDataset.DEFINITION_DATASET,
            DefinitionFreeform.DEFINITION_FREEFORM,
            DefinitionSourceLink.DEFINITION_SOURCE_LINK,
            Deriv.DERIV,
            DerivLabel.DERIV_LABEL,
            DisplayMorph.DISPLAY_MORPH,
            DisplayMorphLabel.DISPLAY_MORPH_LABEL,
            Domain.DOMAIN,
            DomainLabel.DOMAIN_LABEL,
            EkiUser.EKI_USER,
            EtymologyType.ETYMOLOGY_TYPE,
            Form.FORM,
            FormRelType.FORM_REL_TYPE,
            FormRelTypeLabel.FORM_REL_TYPE_LABEL,
            FormRelation.FORM_RELATION,
            Freeform.FREEFORM,
            FreeformSourceLink.FREEFORM_SOURCE_LINK,
            GameNonword.GAME_NONWORD,
            Gender.GENDER,
            GenderLabel.GENDER_LABEL,
            GovernmentType.GOVERNMENT_TYPE,
            GovernmentTypeLabel.GOVERNMENT_TYPE_LABEL,
            LabelType.LABEL_TYPE,
            Language.LANGUAGE,
            LanguageLabel.LANGUAGE_LABEL,
            LexColloc.LEX_COLLOC,
            LexCollocPosGroup.LEX_COLLOC_POS_GROUP,
            LexCollocRelGroup.LEX_COLLOC_REL_GROUP,
            LexRelType.LEX_REL_TYPE,
            LexRelTypeLabel.LEX_REL_TYPE_LABEL,
            LexRelation.LEX_RELATION,
            Lexeme.LEXEME,
            LexemeDeriv.LEXEME_DERIV,
            LexemeFreeform.LEXEME_FREEFORM,
            LexemeFrequency.LEXEME_FREQUENCY,
            LexemeLifecycleLog.LEXEME_LIFECYCLE_LOG,
            LexemePos.LEXEME_POS,
            LexemeRegister.LEXEME_REGISTER,
            LexemeSourceLink.LEXEME_SOURCE_LINK,
            LifecycleLog.LIFECYCLE_LOG,
            Meaning.MEANING,
            MeaningDomain.MEANING_DOMAIN,
            MeaningFreeform.MEANING_FREEFORM,
            MeaningLifecycleLog.MEANING_LIFECYCLE_LOG,
            MeaningRelType.MEANING_REL_TYPE,
            MeaningRelTypeLabel.MEANING_REL_TYPE_LABEL,
            MeaningRelation.MEANING_RELATION,
            Morph.MORPH,
            MorphLabel.MORPH_LABEL,
            Paradigm.PARADIGM,
            Pos.POS,
            PosGroup.POS_GROUP,
            PosGroupLabel.POS_GROUP_LABEL,
            PosLabel.POS_LABEL,
            ProcessState.PROCESS_STATE,
            Register.REGISTER,
            RegisterLabel.REGISTER_LABEL,
            Source.SOURCE,
            SourceFreeform.SOURCE_FREEFORM,
            UsageType.USAGE_TYPE,
            UsageTypeLabel.USAGE_TYPE_LABEL,
            ValueState.VALUE_STATE,
            ValueStateLabel.VALUE_STATE_LABEL,
            ViewWwAsWord.VIEW_WW_AS_WORD,
            ViewWwClassifier.VIEW_WW_CLASSIFIER,
            ViewWwCollocation.VIEW_WW_COLLOCATION,
            ViewWwDataset.VIEW_WW_DATASET,
            ViewWwForm.VIEW_WW_FORM,
            ViewWwLexeme.VIEW_WW_LEXEME,
            ViewWwLexemeRelation.VIEW_WW_LEXEME_RELATION,
            ViewWwLexicalDecisionData.VIEW_WW_LEXICAL_DECISION_DATA,
            ViewWwMeaning.VIEW_WW_MEANING,
            ViewWwMeaningRelation.VIEW_WW_MEANING_RELATION,
            ViewWwWord.VIEW_WW_WORD,
            ViewWwWordEtymology.VIEW_WW_WORD_ETYMOLOGY,
            ViewWwWordRelation.VIEW_WW_WORD_RELATION,
            Word.WORD,
            WordEtymology.WORD_ETYMOLOGY,
            WordEtymologySourceLink.WORD_ETYMOLOGY_SOURCE_LINK,
            WordGroup.WORD_GROUP,
            WordGroupMember.WORD_GROUP_MEMBER,
            WordGuid.WORD_GUID,
            WordLifecycleLog.WORD_LIFECYCLE_LOG,
            WordRelType.WORD_REL_TYPE,
            WordRelTypeLabel.WORD_REL_TYPE_LABEL,
            WordRelation.WORD_RELATION,
            WordType.WORD_TYPE,
            WordTypeLabel.WORD_TYPE_LABEL);
    }

    @Override
    public final List<UDT<?>> getUDTs() {
        List result = new ArrayList();
        result.addAll(getUDTs0());
        return result;
    }

    private final List<UDT<?>> getUDTs0() {
        return Arrays.<UDT<?>>asList(
            TypeCollocMember.TYPE_COLLOC_MEMBER,
            TypeDefinition.TYPE_DEFINITION,
            TypeDomain.TYPE_DOMAIN,
            TypeLexemeRelation.TYPE_LEXEME_RELATION,
            TypeMeaningRelation.TYPE_MEANING_RELATION,
            TypeUsage.TYPE_USAGE,
            TypeWord.TYPE_WORD,
            TypeWordEtym.TYPE_WORD_ETYM,
            TypeWordRelation.TYPE_WORD_RELATION);
    }
}
