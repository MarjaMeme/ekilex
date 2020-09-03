/*
 * This file is generated by jOOQ.
 */
package eki.ekilex.data.db.udt.records;


import eki.ekilex.data.db.udt.TypeWordRelation;

import org.jooq.Field;
import org.jooq.Record10;
import org.jooq.Row10;
import org.jooq.impl.UDTRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TypeWordRelationRecord extends UDTRecordImpl<TypeWordRelationRecord> implements Record10<Long, String, Long, String, String, Integer, String, String, String[], String[]> {

    private static final long serialVersionUID = -270612495;

    /**
     * Setter for <code>public.type_word_relation.word_group_id</code>.
     */
    public void setWordGroupId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.type_word_relation.word_group_id</code>.
     */
    public Long getWordGroupId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public.type_word_relation.word_rel_type_code</code>.
     */
    public void setWordRelTypeCode(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.type_word_relation.word_rel_type_code</code>.
     */
    public String getWordRelTypeCode() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.type_word_relation.word_id</code>.
     */
    public void setWordId(Long value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.type_word_relation.word_id</code>.
     */
    public Long getWordId() {
        return (Long) get(2);
    }

    /**
     * Setter for <code>public.type_word_relation.word</code>.
     */
    public void setWord(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.type_word_relation.word</code>.
     */
    public String getWord() {
        return (String) get(3);
    }

    /**
     * Setter for <code>public.type_word_relation.word_prese</code>.
     */
    public void setWordPrese(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.type_word_relation.word_prese</code>.
     */
    public String getWordPrese() {
        return (String) get(4);
    }

    /**
     * Setter for <code>public.type_word_relation.homonym_nr</code>.
     */
    public void setHomonymNr(Integer value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.type_word_relation.homonym_nr</code>.
     */
    public Integer getHomonymNr() {
        return (Integer) get(5);
    }

    /**
     * Setter for <code>public.type_word_relation.lang</code>.
     */
    public void setLang(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>public.type_word_relation.lang</code>.
     */
    public String getLang() {
        return (String) get(6);
    }

    /**
     * Setter for <code>public.type_word_relation.aspect_code</code>.
     */
    public void setAspectCode(String value) {
        set(7, value);
    }

    /**
     * Getter for <code>public.type_word_relation.aspect_code</code>.
     */
    public String getAspectCode() {
        return (String) get(7);
    }

    /**
     * Setter for <code>public.type_word_relation.word_type_codes</code>.
     */
    public void setWordTypeCodes(String[] value) {
        set(8, value);
    }

    /**
     * Getter for <code>public.type_word_relation.word_type_codes</code>.
     */
    public String[] getWordTypeCodes() {
        return (String[]) get(8);
    }

    /**
     * Setter for <code>public.type_word_relation.lex_complexities</code>.
     */
    public void setLexComplexities(String[] value) {
        set(9, value);
    }

    /**
     * Getter for <code>public.type_word_relation.lex_complexities</code>.
     */
    public String[] getLexComplexities() {
        return (String[]) get(9);
    }

    // -------------------------------------------------------------------------
    // Record10 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row10<Long, String, Long, String, String, Integer, String, String, String[], String[]> fieldsRow() {
        return (Row10) super.fieldsRow();
    }

    @Override
    public Row10<Long, String, Long, String, String, Integer, String, String, String[], String[]> valuesRow() {
        return (Row10) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return TypeWordRelation.WORD_GROUP_ID;
    }

    @Override
    public Field<String> field2() {
        return TypeWordRelation.WORD_REL_TYPE_CODE;
    }

    @Override
    public Field<Long> field3() {
        return TypeWordRelation.WORD_ID;
    }

    @Override
    public Field<String> field4() {
        return TypeWordRelation.WORD;
    }

    @Override
    public Field<String> field5() {
        return TypeWordRelation.WORD_PRESE;
    }

    @Override
    public Field<Integer> field6() {
        return TypeWordRelation.HOMONYM_NR;
    }

    @Override
    public Field<String> field7() {
        return TypeWordRelation.LANG;
    }

    @Override
    public Field<String> field8() {
        return TypeWordRelation.ASPECT_CODE;
    }

    @Override
    public Field<String[]> field9() {
        return TypeWordRelation.WORD_TYPE_CODES;
    }

    @Override
    public Field<String[]> field10() {
        return TypeWordRelation.LEX_COMPLEXITIES;
    }

    @Override
    public Long component1() {
        return getWordGroupId();
    }

    @Override
    public String component2() {
        return getWordRelTypeCode();
    }

    @Override
    public Long component3() {
        return getWordId();
    }

    @Override
    public String component4() {
        return getWord();
    }

    @Override
    public String component5() {
        return getWordPrese();
    }

    @Override
    public Integer component6() {
        return getHomonymNr();
    }

    @Override
    public String component7() {
        return getLang();
    }

    @Override
    public String component8() {
        return getAspectCode();
    }

    @Override
    public String[] component9() {
        return getWordTypeCodes();
    }

    @Override
    public String[] component10() {
        return getLexComplexities();
    }

    @Override
    public Long value1() {
        return getWordGroupId();
    }

    @Override
    public String value2() {
        return getWordRelTypeCode();
    }

    @Override
    public Long value3() {
        return getWordId();
    }

    @Override
    public String value4() {
        return getWord();
    }

    @Override
    public String value5() {
        return getWordPrese();
    }

    @Override
    public Integer value6() {
        return getHomonymNr();
    }

    @Override
    public String value7() {
        return getLang();
    }

    @Override
    public String value8() {
        return getAspectCode();
    }

    @Override
    public String[] value9() {
        return getWordTypeCodes();
    }

    @Override
    public String[] value10() {
        return getLexComplexities();
    }

    @Override
    public TypeWordRelationRecord value1(Long value) {
        setWordGroupId(value);
        return this;
    }

    @Override
    public TypeWordRelationRecord value2(String value) {
        setWordRelTypeCode(value);
        return this;
    }

    @Override
    public TypeWordRelationRecord value3(Long value) {
        setWordId(value);
        return this;
    }

    @Override
    public TypeWordRelationRecord value4(String value) {
        setWord(value);
        return this;
    }

    @Override
    public TypeWordRelationRecord value5(String value) {
        setWordPrese(value);
        return this;
    }

    @Override
    public TypeWordRelationRecord value6(Integer value) {
        setHomonymNr(value);
        return this;
    }

    @Override
    public TypeWordRelationRecord value7(String value) {
        setLang(value);
        return this;
    }

    @Override
    public TypeWordRelationRecord value8(String value) {
        setAspectCode(value);
        return this;
    }

    @Override
    public TypeWordRelationRecord value9(String[] value) {
        setWordTypeCodes(value);
        return this;
    }

    @Override
    public TypeWordRelationRecord value10(String[] value) {
        setLexComplexities(value);
        return this;
    }

    @Override
    public TypeWordRelationRecord values(Long value1, String value2, Long value3, String value4, String value5, Integer value6, String value7, String value8, String[] value9, String[] value10) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached TypeWordRelationRecord
     */
    public TypeWordRelationRecord() {
        super(TypeWordRelation.TYPE_WORD_RELATION);
    }

    /**
     * Create a detached, initialised TypeWordRelationRecord
     */
    public TypeWordRelationRecord(Long wordGroupId, String wordRelTypeCode, Long wordId, String word, String wordPrese, Integer homonymNr, String lang, String aspectCode, String[] wordTypeCodes, String[] lexComplexities) {
        super(TypeWordRelation.TYPE_WORD_RELATION);

        set(0, wordGroupId);
        set(1, wordRelTypeCode);
        set(2, wordId);
        set(3, word);
        set(4, wordPrese);
        set(5, homonymNr);
        set(6, lang);
        set(7, aspectCode);
        set(8, wordTypeCodes);
        set(9, lexComplexities);
    }
}
