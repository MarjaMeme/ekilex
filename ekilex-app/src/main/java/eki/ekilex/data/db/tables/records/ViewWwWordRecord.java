/*
 * This file is generated by jOOQ.
 */
package eki.ekilex.data.db.tables.records;


import eki.ekilex.data.db.tables.ViewWwWord;
import eki.ekilex.data.db.udt.records.TypeDefinitionRecord;
import eki.ekilex.data.db.udt.records.TypeFreeformRecord;
import eki.ekilex.data.db.udt.records.TypeLangComplexityRecord;
import eki.ekilex.data.db.udt.records.TypeMeaningWordRecord;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.jooq.impl.TableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ViewWwWordRecord extends TableRecordImpl<ViewWwWordRecord> {

    private static final long serialVersionUID = -543913284;

    /**
     * Setter for <code>public.view_ww_word.word_id</code>.
     */
    public void setWordId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.view_ww_word.word_id</code>.
     */
    public Long getWordId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public.view_ww_word.word</code>.
     */
    public void setWord(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.view_ww_word.word</code>.
     */
    public String getWord() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.view_ww_word.word_prese</code>.
     */
    public void setWordPrese(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.view_ww_word.word_prese</code>.
     */
    public String getWordPrese() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.view_ww_word.as_word</code>.
     */
    public void setAsWord(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.view_ww_word.as_word</code>.
     */
    public String getAsWord() {
        return (String) get(3);
    }

    /**
     * Setter for <code>public.view_ww_word.lang</code>.
     */
    public void setLang(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.view_ww_word.lang</code>.
     */
    public String getLang() {
        return (String) get(4);
    }

    /**
     * Setter for <code>public.view_ww_word.lang_order_by</code>.
     */
    public void setLangOrderBy(Long value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.view_ww_word.lang_order_by</code>.
     */
    public Long getLangOrderBy() {
        return (Long) get(5);
    }

    /**
     * Setter for <code>public.view_ww_word.homonym_nr</code>.
     */
    public void setHomonymNr(Integer value) {
        set(6, value);
    }

    /**
     * Getter for <code>public.view_ww_word.homonym_nr</code>.
     */
    public Integer getHomonymNr() {
        return (Integer) get(6);
    }

    /**
     * Setter for <code>public.view_ww_word.word_type_codes</code>.
     */
    public void setWordTypeCodes(String[] value) {
        set(7, value);
    }

    /**
     * Getter for <code>public.view_ww_word.word_type_codes</code>.
     */
    public String[] getWordTypeCodes() {
        return (String[]) get(7);
    }

    /**
     * Setter for <code>public.view_ww_word.display_morph_code</code>.
     */
    public void setDisplayMorphCode(String value) {
        set(8, value);
    }

    /**
     * Getter for <code>public.view_ww_word.display_morph_code</code>.
     */
    public String getDisplayMorphCode() {
        return (String) get(8);
    }

    /**
     * Setter for <code>public.view_ww_word.gender_code</code>.
     */
    public void setGenderCode(String value) {
        set(9, value);
    }

    /**
     * Getter for <code>public.view_ww_word.gender_code</code>.
     */
    public String getGenderCode() {
        return (String) get(9);
    }

    /**
     * Setter for <code>public.view_ww_word.aspect_code</code>.
     */
    public void setAspectCode(String value) {
        set(10, value);
    }

    /**
     * Getter for <code>public.view_ww_word.aspect_code</code>.
     */
    public String getAspectCode() {
        return (String) get(10);
    }

    /**
     * Setter for <code>public.view_ww_word.vocal_form</code>.
     */
    public void setVocalForm(String value) {
        set(11, value);
    }

    /**
     * Getter for <code>public.view_ww_word.vocal_form</code>.
     */
    public String getVocalForm() {
        return (String) get(11);
    }

    /**
     * Setter for <code>public.view_ww_word.manual_event_on</code>.
     */
    public void setManualEventOn(Timestamp value) {
        set(12, value);
    }

    /**
     * Getter for <code>public.view_ww_word.manual_event_on</code>.
     */
    public Timestamp getManualEventOn() {
        return (Timestamp) get(12);
    }

    /**
     * Setter for <code>public.view_ww_word.last_activity_event_on</code>.
     */
    public void setLastActivityEventOn(Timestamp value) {
        set(13, value);
    }

    /**
     * Getter for <code>public.view_ww_word.last_activity_event_on</code>.
     */
    public Timestamp getLastActivityEventOn() {
        return (Timestamp) get(13);
    }

    /**
     * Setter for <code>public.view_ww_word.lang_complexities</code>.
     */
    public void setLangComplexities(TypeLangComplexityRecord[] value) {
        set(14, value);
    }

    /**
     * Getter for <code>public.view_ww_word.lang_complexities</code>.
     */
    public TypeLangComplexityRecord[] getLangComplexities() {
        return (TypeLangComplexityRecord[]) get(14);
    }

    /**
     * Setter for <code>public.view_ww_word.meaning_words</code>.
     */
    public void setMeaningWords(TypeMeaningWordRecord[] value) {
        set(15, value);
    }

    /**
     * Getter for <code>public.view_ww_word.meaning_words</code>.
     */
    public TypeMeaningWordRecord[] getMeaningWords() {
        return (TypeMeaningWordRecord[]) get(15);
    }

    /**
     * Setter for <code>public.view_ww_word.definitions</code>.
     */
    public void setDefinitions(TypeDefinitionRecord[] value) {
        set(16, value);
    }

    /**
     * Getter for <code>public.view_ww_word.definitions</code>.
     */
    public TypeDefinitionRecord[] getDefinitions() {
        return (TypeDefinitionRecord[]) get(16);
    }

    /**
     * Setter for <code>public.view_ww_word.od_word_recommendations</code>.
     */
    public void setOdWordRecommendations(TypeFreeformRecord[] value) {
        set(17, value);
    }

    /**
     * Getter for <code>public.view_ww_word.od_word_recommendations</code>.
     */
    public TypeFreeformRecord[] getOdWordRecommendations() {
        return (TypeFreeformRecord[]) get(17);
    }

    /**
     * Setter for <code>public.view_ww_word.freq_value</code>.
     */
    public void setFreqValue(BigDecimal value) {
        set(18, value);
    }

    /**
     * Getter for <code>public.view_ww_word.freq_value</code>.
     */
    public BigDecimal getFreqValue() {
        return (BigDecimal) get(18);
    }

    /**
     * Setter for <code>public.view_ww_word.freq_rank</code>.
     */
    public void setFreqRank(Long value) {
        set(19, value);
    }

    /**
     * Getter for <code>public.view_ww_word.freq_rank</code>.
     */
    public Long getFreqRank() {
        return (Long) get(19);
    }

    /**
     * Setter for <code>public.view_ww_word.forms_exist</code>.
     */
    public void setFormsExist(Boolean value) {
        set(20, value);
    }

    /**
     * Getter for <code>public.view_ww_word.forms_exist</code>.
     */
    public Boolean getFormsExist() {
        return (Boolean) get(20);
    }

    /**
     * Setter for <code>public.view_ww_word.min_ds_order_by</code>.
     */
    public void setMinDsOrderBy(Long value) {
        set(21, value);
    }

    /**
     * Getter for <code>public.view_ww_word.min_ds_order_by</code>.
     */
    public Long getMinDsOrderBy() {
        return (Long) get(21);
    }

    /**
     * Setter for <code>public.view_ww_word.word_type_order_by</code>.
     */
    public void setWordTypeOrderBy(Long value) {
        set(22, value);
    }

    /**
     * Getter for <code>public.view_ww_word.word_type_order_by</code>.
     */
    public Long getWordTypeOrderBy() {
        return (Long) get(22);
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ViewWwWordRecord
     */
    public ViewWwWordRecord() {
        super(ViewWwWord.VIEW_WW_WORD);
    }

    /**
     * Create a detached, initialised ViewWwWordRecord
     */
    public ViewWwWordRecord(Long wordId, String word, String wordPrese, String asWord, String lang, Long langOrderBy, Integer homonymNr, String[] wordTypeCodes, String displayMorphCode, String genderCode, String aspectCode, String vocalForm, Timestamp manualEventOn, Timestamp lastActivityEventOn, TypeLangComplexityRecord[] langComplexities, TypeMeaningWordRecord[] meaningWords, TypeDefinitionRecord[] definitions, TypeFreeformRecord[] odWordRecommendations, BigDecimal freqValue, Long freqRank, Boolean formsExist, Long minDsOrderBy, Long wordTypeOrderBy) {
        super(ViewWwWord.VIEW_WW_WORD);

        set(0, wordId);
        set(1, word);
        set(2, wordPrese);
        set(3, asWord);
        set(4, lang);
        set(5, langOrderBy);
        set(6, homonymNr);
        set(7, wordTypeCodes);
        set(8, displayMorphCode);
        set(9, genderCode);
        set(10, aspectCode);
        set(11, vocalForm);
        set(12, manualEventOn);
        set(13, lastActivityEventOn);
        set(14, langComplexities);
        set(15, meaningWords);
        set(16, definitions);
        set(17, odWordRecommendations);
        set(18, freqValue);
        set(19, freqRank);
        set(20, formsExist);
        set(21, minDsOrderBy);
        set(22, wordTypeOrderBy);
    }
}
