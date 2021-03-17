/*
 * This file is generated by jOOQ.
 */
package eki.wordweb.data.db.tables.records;


import eki.wordweb.data.db.tables.MviewWwForm;

import java.math.BigDecimal;

import org.jooq.impl.TableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class MviewWwFormRecord extends TableRecordImpl<MviewWwFormRecord> {

    private static final long serialVersionUID = -910553651;

    /**
     * Setter for <code>public.mview_ww_form.word_id</code>.
     */
    public void setWordId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.mview_ww_form.word_id</code>.
     */
    public Long getWordId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public.mview_ww_form.word_class</code>.
     */
    public void setWordClass(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.mview_ww_form.word_class</code>.
     */
    public String getWordClass() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.mview_ww_form.word</code>.
     */
    public void setWord(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.mview_ww_form.word</code>.
     */
    public String getWord() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.mview_ww_form.lang</code>.
     */
    public void setLang(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.mview_ww_form.lang</code>.
     */
    public String getLang() {
        return (String) get(3);
    }

    /**
     * Setter for <code>public.mview_ww_form.paradigm_id</code>.
     */
    public void setParadigmId(Long value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.mview_ww_form.paradigm_id</code>.
     */
    public Long getParadigmId() {
        return (Long) get(4);
    }

    /**
     * Setter for <code>public.mview_ww_form.paradigm_comment</code>.
     */
    public void setParadigmComment(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.mview_ww_form.paradigm_comment</code>.
     */
    public String getParadigmComment() {
        return (String) get(5);
    }

    /**
     * Setter for <code>public.mview_ww_form.inflection_type</code>.
     */
    public void setInflectionType(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>public.mview_ww_form.inflection_type</code>.
     */
    public String getInflectionType() {
        return (String) get(6);
    }

    /**
     * Setter for <code>public.mview_ww_form.form_id</code>.
     */
    public void setFormId(Long value) {
        set(7, value);
    }

    /**
     * Getter for <code>public.mview_ww_form.form_id</code>.
     */
    public Long getFormId() {
        return (Long) get(7);
    }

    /**
     * Setter for <code>public.mview_ww_form.morph_group1</code>.
     */
    public void setMorphGroup1(String value) {
        set(8, value);
    }

    /**
     * Getter for <code>public.mview_ww_form.morph_group1</code>.
     */
    public String getMorphGroup1() {
        return (String) get(8);
    }

    /**
     * Setter for <code>public.mview_ww_form.morph_group2</code>.
     */
    public void setMorphGroup2(String value) {
        set(9, value);
    }

    /**
     * Getter for <code>public.mview_ww_form.morph_group2</code>.
     */
    public String getMorphGroup2() {
        return (String) get(9);
    }

    /**
     * Setter for <code>public.mview_ww_form.morph_group3</code>.
     */
    public void setMorphGroup3(String value) {
        set(10, value);
    }

    /**
     * Getter for <code>public.mview_ww_form.morph_group3</code>.
     */
    public String getMorphGroup3() {
        return (String) get(10);
    }

    /**
     * Setter for <code>public.mview_ww_form.display_level</code>.
     */
    public void setDisplayLevel(Integer value) {
        set(11, value);
    }

    /**
     * Getter for <code>public.mview_ww_form.display_level</code>.
     */
    public Integer getDisplayLevel() {
        return (Integer) get(11);
    }

    /**
     * Setter for <code>public.mview_ww_form.morph_code</code>.
     */
    public void setMorphCode(String value) {
        set(12, value);
    }

    /**
     * Getter for <code>public.mview_ww_form.morph_code</code>.
     */
    public String getMorphCode() {
        return (String) get(12);
    }

    /**
     * Setter for <code>public.mview_ww_form.morph_exists</code>.
     */
    public void setMorphExists(Boolean value) {
        set(13, value);
    }

    /**
     * Getter for <code>public.mview_ww_form.morph_exists</code>.
     */
    public Boolean getMorphExists() {
        return (Boolean) get(13);
    }

    /**
     * Setter for <code>public.mview_ww_form.is_questionable</code>.
     */
    public void setIsQuestionable(Boolean value) {
        set(14, value);
    }

    /**
     * Getter for <code>public.mview_ww_form.is_questionable</code>.
     */
    public Boolean getIsQuestionable() {
        return (Boolean) get(14);
    }

    /**
     * Setter for <code>public.mview_ww_form.value</code>.
     */
    public void setValue(String value) {
        set(15, value);
    }

    /**
     * Getter for <code>public.mview_ww_form.value</code>.
     */
    public String getValue() {
        return (String) get(15);
    }

    /**
     * Setter for <code>public.mview_ww_form.value_prese</code>.
     */
    public void setValuePrese(String value) {
        set(16, value);
    }

    /**
     * Getter for <code>public.mview_ww_form.value_prese</code>.
     */
    public String getValuePrese() {
        return (String) get(16);
    }

    /**
     * Setter for <code>public.mview_ww_form.components</code>.
     */
    public void setComponents(String[] value) {
        set(17, value);
    }

    /**
     * Getter for <code>public.mview_ww_form.components</code>.
     */
    public String[] getComponents() {
        return (String[]) get(17);
    }

    /**
     * Setter for <code>public.mview_ww_form.display_form</code>.
     */
    public void setDisplayForm(String value) {
        set(18, value);
    }

    /**
     * Getter for <code>public.mview_ww_form.display_form</code>.
     */
    public String getDisplayForm() {
        return (String) get(18);
    }

    /**
     * Setter for <code>public.mview_ww_form.audio_file</code>.
     */
    public void setAudioFile(String value) {
        set(19, value);
    }

    /**
     * Getter for <code>public.mview_ww_form.audio_file</code>.
     */
    public String getAudioFile() {
        return (String) get(19);
    }

    /**
     * Setter for <code>public.mview_ww_form.order_by</code>.
     */
    public void setOrderBy(Long value) {
        set(20, value);
    }

    /**
     * Getter for <code>public.mview_ww_form.order_by</code>.
     */
    public Long getOrderBy() {
        return (Long) get(20);
    }

    /**
     * Setter for <code>public.mview_ww_form.form_freq_value</code>.
     */
    public void setFormFreqValue(BigDecimal value) {
        set(21, value);
    }

    /**
     * Getter for <code>public.mview_ww_form.form_freq_value</code>.
     */
    public BigDecimal getFormFreqValue() {
        return (BigDecimal) get(21);
    }

    /**
     * Setter for <code>public.mview_ww_form.form_freq_rank</code>.
     */
    public void setFormFreqRank(Long value) {
        set(22, value);
    }

    /**
     * Getter for <code>public.mview_ww_form.form_freq_rank</code>.
     */
    public Long getFormFreqRank() {
        return (Long) get(22);
    }

    /**
     * Setter for <code>public.mview_ww_form.form_freq_rank_max</code>.
     */
    public void setFormFreqRankMax(Long value) {
        set(23, value);
    }

    /**
     * Getter for <code>public.mview_ww_form.form_freq_rank_max</code>.
     */
    public Long getFormFreqRankMax() {
        return (Long) get(23);
    }

    /**
     * Setter for <code>public.mview_ww_form.morph_freq_value</code>.
     */
    public void setMorphFreqValue(BigDecimal value) {
        set(24, value);
    }

    /**
     * Getter for <code>public.mview_ww_form.morph_freq_value</code>.
     */
    public BigDecimal getMorphFreqValue() {
        return (BigDecimal) get(24);
    }

    /**
     * Setter for <code>public.mview_ww_form.morph_freq_rank</code>.
     */
    public void setMorphFreqRank(Long value) {
        set(25, value);
    }

    /**
     * Getter for <code>public.mview_ww_form.morph_freq_rank</code>.
     */
    public Long getMorphFreqRank() {
        return (Long) get(25);
    }

    /**
     * Setter for <code>public.mview_ww_form.morph_freq_rank_max</code>.
     */
    public void setMorphFreqRankMax(Long value) {
        set(26, value);
    }

    /**
     * Getter for <code>public.mview_ww_form.morph_freq_rank_max</code>.
     */
    public Long getMorphFreqRankMax() {
        return (Long) get(26);
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached MviewWwFormRecord
     */
    public MviewWwFormRecord() {
        super(MviewWwForm.MVIEW_WW_FORM);
    }

    /**
     * Create a detached, initialised MviewWwFormRecord
     */
    public MviewWwFormRecord(Long wordId, String wordClass, String word, String lang, Long paradigmId, String paradigmComment, String inflectionType, Long formId, String morphGroup1, String morphGroup2, String morphGroup3, Integer displayLevel, String morphCode, Boolean morphExists, Boolean isQuestionable, String value, String valuePrese, String[] components, String displayForm, String audioFile, Long orderBy, BigDecimal formFreqValue, Long formFreqRank, Long formFreqRankMax, BigDecimal morphFreqValue, Long morphFreqRank, Long morphFreqRankMax) {
        super(MviewWwForm.MVIEW_WW_FORM);

        set(0, wordId);
        set(1, wordClass);
        set(2, word);
        set(3, lang);
        set(4, paradigmId);
        set(5, paradigmComment);
        set(6, inflectionType);
        set(7, formId);
        set(8, morphGroup1);
        set(9, morphGroup2);
        set(10, morphGroup3);
        set(11, displayLevel);
        set(12, morphCode);
        set(13, morphExists);
        set(14, isQuestionable);
        set(15, value);
        set(16, valuePrese);
        set(17, components);
        set(18, displayForm);
        set(19, audioFile);
        set(20, orderBy);
        set(21, formFreqValue);
        set(22, formFreqRank);
        set(23, formFreqRankMax);
        set(24, morphFreqValue);
        set(25, morphFreqRank);
        set(26, morphFreqRankMax);
    }
}
