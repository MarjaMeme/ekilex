/*
 * This file is generated by jOOQ.
 */
package eki.wordweb.data.db.tables.records;


import eki.wordweb.data.db.tables.MviewWwLexeme;
import eki.wordweb.data.db.udt.records.TypeFreeformRecord;
import eki.wordweb.data.db.udt.records.TypeLangComplexityRecord;
import eki.wordweb.data.db.udt.records.TypeMeaningWordRecord;
import eki.wordweb.data.db.udt.records.TypeUsageRecord;

import java.math.BigDecimal;

import org.jooq.impl.TableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class MviewWwLexemeRecord extends TableRecordImpl<MviewWwLexemeRecord> {

    private static final long serialVersionUID = 86928941;

    /**
     * Setter for <code>public.mview_ww_lexeme.lexeme_id</code>.
     */
    public void setLexemeId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.mview_ww_lexeme.lexeme_id</code>.
     */
    public Long getLexemeId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public.mview_ww_lexeme.word_id</code>.
     */
    public void setWordId(Long value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.mview_ww_lexeme.word_id</code>.
     */
    public Long getWordId() {
        return (Long) get(1);
    }

    /**
     * Setter for <code>public.mview_ww_lexeme.meaning_id</code>.
     */
    public void setMeaningId(Long value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.mview_ww_lexeme.meaning_id</code>.
     */
    public Long getMeaningId() {
        return (Long) get(2);
    }

    /**
     * Setter for <code>public.mview_ww_lexeme.dataset_code</code>.
     */
    public void setDatasetCode(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.mview_ww_lexeme.dataset_code</code>.
     */
    public String getDatasetCode() {
        return (String) get(3);
    }

    /**
     * Setter for <code>public.mview_ww_lexeme.dataset_type</code>.
     */
    public void setDatasetType(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.mview_ww_lexeme.dataset_type</code>.
     */
    public String getDatasetType() {
        return (String) get(4);
    }

    /**
     * Setter for <code>public.mview_ww_lexeme.dataset_name</code>.
     */
    public void setDatasetName(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.mview_ww_lexeme.dataset_name</code>.
     */
    public String getDatasetName() {
        return (String) get(5);
    }

    /**
     * Setter for <code>public.mview_ww_lexeme.value_state_code</code>.
     */
    public void setValueStateCode(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>public.mview_ww_lexeme.value_state_code</code>.
     */
    public String getValueStateCode() {
        return (String) get(6);
    }

    /**
     * Setter for <code>public.mview_ww_lexeme.proficiency_level_code</code>.
     */
    public void setProficiencyLevelCode(String value) {
        set(7, value);
    }

    /**
     * Getter for <code>public.mview_ww_lexeme.proficiency_level_code</code>.
     */
    public String getProficiencyLevelCode() {
        return (String) get(7);
    }

    /**
     * Setter for <code>public.mview_ww_lexeme.reliability</code>.
     */
    public void setReliability(Integer value) {
        set(8, value);
    }

    /**
     * Getter for <code>public.mview_ww_lexeme.reliability</code>.
     */
    public Integer getReliability() {
        return (Integer) get(8);
    }

    /**
     * Setter for <code>public.mview_ww_lexeme.level1</code>.
     */
    public void setLevel1(Integer value) {
        set(9, value);
    }

    /**
     * Getter for <code>public.mview_ww_lexeme.level1</code>.
     */
    public Integer getLevel1() {
        return (Integer) get(9);
    }

    /**
     * Setter for <code>public.mview_ww_lexeme.level2</code>.
     */
    public void setLevel2(Integer value) {
        set(10, value);
    }

    /**
     * Getter for <code>public.mview_ww_lexeme.level2</code>.
     */
    public Integer getLevel2() {
        return (Integer) get(10);
    }

    /**
     * Setter for <code>public.mview_ww_lexeme.weight</code>.
     */
    public void setWeight(BigDecimal value) {
        set(11, value);
    }

    /**
     * Getter for <code>public.mview_ww_lexeme.weight</code>.
     */
    public BigDecimal getWeight() {
        return (BigDecimal) get(11);
    }

    /**
     * Setter for <code>public.mview_ww_lexeme.complexity</code>.
     */
    public void setComplexity(String value) {
        set(12, value);
    }

    /**
     * Getter for <code>public.mview_ww_lexeme.complexity</code>.
     */
    public String getComplexity() {
        return (String) get(12);
    }

    /**
     * Setter for <code>public.mview_ww_lexeme.dataset_order_by</code>.
     */
    public void setDatasetOrderBy(Long value) {
        set(13, value);
    }

    /**
     * Getter for <code>public.mview_ww_lexeme.dataset_order_by</code>.
     */
    public Long getDatasetOrderBy() {
        return (Long) get(13);
    }

    /**
     * Setter for <code>public.mview_ww_lexeme.lexeme_order_by</code>.
     */
    public void setLexemeOrderBy(Long value) {
        set(14, value);
    }

    /**
     * Getter for <code>public.mview_ww_lexeme.lexeme_order_by</code>.
     */
    public Long getLexemeOrderBy() {
        return (Long) get(14);
    }

    /**
     * Setter for <code>public.mview_ww_lexeme.value_state_order_by</code>.
     */
    public void setValueStateOrderBy(Long value) {
        set(15, value);
    }

    /**
     * Getter for <code>public.mview_ww_lexeme.value_state_order_by</code>.
     */
    public Long getValueStateOrderBy() {
        return (Long) get(15);
    }

    /**
     * Setter for <code>public.mview_ww_lexeme.lang_complexities</code>.
     */
    public void setLangComplexities(TypeLangComplexityRecord[] value) {
        set(16, value);
    }

    /**
     * Getter for <code>public.mview_ww_lexeme.lang_complexities</code>.
     */
    public TypeLangComplexityRecord[] getLangComplexities() {
        return (TypeLangComplexityRecord[]) get(16);
    }

    /**
     * Setter for <code>public.mview_ww_lexeme.register_codes</code>.
     */
    public void setRegisterCodes(String[] value) {
        set(17, value);
    }

    /**
     * Getter for <code>public.mview_ww_lexeme.register_codes</code>.
     */
    public String[] getRegisterCodes() {
        return (String[]) get(17);
    }

    /**
     * Setter for <code>public.mview_ww_lexeme.pos_codes</code>.
     */
    public void setPosCodes(String[] value) {
        set(18, value);
    }

    /**
     * Getter for <code>public.mview_ww_lexeme.pos_codes</code>.
     */
    public String[] getPosCodes() {
        return (String[]) get(18);
    }

    /**
     * Setter for <code>public.mview_ww_lexeme.region_codes</code>.
     */
    public void setRegionCodes(String[] value) {
        set(19, value);
    }

    /**
     * Getter for <code>public.mview_ww_lexeme.region_codes</code>.
     */
    public String[] getRegionCodes() {
        return (String[]) get(19);
    }

    /**
     * Setter for <code>public.mview_ww_lexeme.deriv_codes</code>.
     */
    public void setDerivCodes(String[] value) {
        set(20, value);
    }

    /**
     * Getter for <code>public.mview_ww_lexeme.deriv_codes</code>.
     */
    public String[] getDerivCodes() {
        return (String[]) get(20);
    }

    /**
     * Setter for <code>public.mview_ww_lexeme.meaning_words</code>.
     */
    public void setMeaningWords(TypeMeaningWordRecord[] value) {
        set(21, value);
    }

    /**
     * Getter for <code>public.mview_ww_lexeme.meaning_words</code>.
     */
    public TypeMeaningWordRecord[] getMeaningWords() {
        return (TypeMeaningWordRecord[]) get(21);
    }

    /**
     * Setter for <code>public.mview_ww_lexeme.advice_notes</code>.
     */
    public void setAdviceNotes(String[] value) {
        set(22, value);
    }

    /**
     * Getter for <code>public.mview_ww_lexeme.advice_notes</code>.
     */
    public String[] getAdviceNotes() {
        return (String[]) get(22);
    }

    /**
     * Setter for <code>public.mview_ww_lexeme.notes</code>.
     */
    public void setNotes(TypeFreeformRecord[] value) {
        set(23, value);
    }

    /**
     * Getter for <code>public.mview_ww_lexeme.notes</code>.
     */
    public TypeFreeformRecord[] getNotes() {
        return (TypeFreeformRecord[]) get(23);
    }

    /**
     * Setter for <code>public.mview_ww_lexeme.grammars</code>.
     */
    public void setGrammars(TypeFreeformRecord[] value) {
        set(24, value);
    }

    /**
     * Getter for <code>public.mview_ww_lexeme.grammars</code>.
     */
    public TypeFreeformRecord[] getGrammars() {
        return (TypeFreeformRecord[]) get(24);
    }

    /**
     * Setter for <code>public.mview_ww_lexeme.governments</code>.
     */
    public void setGovernments(TypeFreeformRecord[] value) {
        set(25, value);
    }

    /**
     * Getter for <code>public.mview_ww_lexeme.governments</code>.
     */
    public TypeFreeformRecord[] getGovernments() {
        return (TypeFreeformRecord[]) get(25);
    }

    /**
     * Setter for <code>public.mview_ww_lexeme.usages</code>.
     */
    public void setUsages(TypeUsageRecord[] value) {
        set(26, value);
    }

    /**
     * Getter for <code>public.mview_ww_lexeme.usages</code>.
     */
    public TypeUsageRecord[] getUsages() {
        return (TypeUsageRecord[]) get(26);
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached MviewWwLexemeRecord
     */
    public MviewWwLexemeRecord() {
        super(MviewWwLexeme.MVIEW_WW_LEXEME);
    }

    /**
     * Create a detached, initialised MviewWwLexemeRecord
     */
    public MviewWwLexemeRecord(Long lexemeId, Long wordId, Long meaningId, String datasetCode, String datasetType, String datasetName, String valueStateCode, String proficiencyLevelCode, Integer reliability, Integer level1, Integer level2, BigDecimal weight, String complexity, Long datasetOrderBy, Long lexemeOrderBy, Long valueStateOrderBy, TypeLangComplexityRecord[] langComplexities, String[] registerCodes, String[] posCodes, String[] regionCodes, String[] derivCodes, TypeMeaningWordRecord[] meaningWords, String[] adviceNotes, TypeFreeformRecord[] notes, TypeFreeformRecord[] grammars, TypeFreeformRecord[] governments, TypeUsageRecord[] usages) {
        super(MviewWwLexeme.MVIEW_WW_LEXEME);

        set(0, lexemeId);
        set(1, wordId);
        set(2, meaningId);
        set(3, datasetCode);
        set(4, datasetType);
        set(5, datasetName);
        set(6, valueStateCode);
        set(7, proficiencyLevelCode);
        set(8, reliability);
        set(9, level1);
        set(10, level2);
        set(11, weight);
        set(12, complexity);
        set(13, datasetOrderBy);
        set(14, lexemeOrderBy);
        set(15, valueStateOrderBy);
        set(16, langComplexities);
        set(17, registerCodes);
        set(18, posCodes);
        set(19, regionCodes);
        set(20, derivCodes);
        set(21, meaningWords);
        set(22, adviceNotes);
        set(23, notes);
        set(24, grammars);
        set(25, governments);
        set(26, usages);
    }
}
