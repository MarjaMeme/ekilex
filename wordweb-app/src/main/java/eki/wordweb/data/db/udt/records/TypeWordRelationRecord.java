/*
 * This file is generated by jOOQ.
 */
package eki.wordweb.data.db.udt.records;


import eki.wordweb.data.db.udt.TypeWordRelation;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record7;
import org.jooq.Row7;
import org.jooq.impl.UDTRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.9"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TypeWordRelationRecord extends UDTRecordImpl<TypeWordRelationRecord> implements Record7<Long, String, String, Integer, String[], String[], String> {

    private static final long serialVersionUID = -1923856196;

    /**
     * Setter for <code>public.type_word_relation.word_id</code>.
     */
    public void setWordId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.type_word_relation.word_id</code>.
     */
    public Long getWordId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public.type_word_relation.word</code>.
     */
    public void setWord(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.type_word_relation.word</code>.
     */
    public String getWord() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.type_word_relation.word_lang</code>.
     */
    public void setWordLang(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.type_word_relation.word_lang</code>.
     */
    public String getWordLang() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.type_word_relation.homonym_nr</code>.
     */
    public void setHomonymNr(Integer value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.type_word_relation.homonym_nr</code>.
     */
    public Integer getHomonymNr() {
        return (Integer) get(3);
    }

    /**
     * Setter for <code>public.type_word_relation.lex_complexities</code>.
     */
    public void setLexComplexities(String... value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.type_word_relation.lex_complexities</code>.
     */
    public String[] getLexComplexities() {
        return (String[]) get(4);
    }

    /**
     * Setter for <code>public.type_word_relation.word_type_codes</code>.
     */
    public void setWordTypeCodes(String... value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.type_word_relation.word_type_codes</code>.
     */
    public String[] getWordTypeCodes() {
        return (String[]) get(5);
    }

    /**
     * Setter for <code>public.type_word_relation.word_rel_type_code</code>.
     */
    public void setWordRelTypeCode(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>public.type_word_relation.word_rel_type_code</code>.
     */
    public String getWordRelTypeCode() {
        return (String) get(6);
    }

    // -------------------------------------------------------------------------
    // Record7 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row7<Long, String, String, Integer, String[], String[], String> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row7<Long, String, String, Integer, String[], String[], String> valuesRow() {
        return (Row7) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field1() {
        return TypeWordRelation.WORD_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return TypeWordRelation.WORD;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return TypeWordRelation.WORD_LANG;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field4() {
        return TypeWordRelation.HOMONYM_NR;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String[]> field5() {
        return TypeWordRelation.LEX_COMPLEXITIES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String[]> field6() {
        return TypeWordRelation.WORD_TYPE_CODES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field7() {
        return TypeWordRelation.WORD_REL_TYPE_CODE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component1() {
        return getWordId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component2() {
        return getWord();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component3() {
        return getWordLang();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component4() {
        return getHomonymNr();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] component5() {
        return getLexComplexities();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] component6() {
        return getWordTypeCodes();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component7() {
        return getWordRelTypeCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value1() {
        return getWordId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getWord();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getWordLang();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value4() {
        return getHomonymNr();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] value5() {
        return getLexComplexities();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] value6() {
        return getWordTypeCodes();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value7() {
        return getWordRelTypeCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TypeWordRelationRecord value1(Long value) {
        setWordId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TypeWordRelationRecord value2(String value) {
        setWord(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TypeWordRelationRecord value3(String value) {
        setWordLang(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TypeWordRelationRecord value4(Integer value) {
        setHomonymNr(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TypeWordRelationRecord value5(String... value) {
        setLexComplexities(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TypeWordRelationRecord value6(String... value) {
        setWordTypeCodes(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TypeWordRelationRecord value7(String value) {
        setWordRelTypeCode(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TypeWordRelationRecord values(Long value1, String value2, String value3, Integer value4, String[] value5, String[] value6, String value7) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
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
    public TypeWordRelationRecord(Long wordId, String word, String wordLang, Integer homonymNr, String[] lexComplexities, String[] wordTypeCodes, String wordRelTypeCode) {
        super(TypeWordRelation.TYPE_WORD_RELATION);

        set(0, wordId);
        set(1, word);
        set(2, wordLang);
        set(3, homonymNr);
        set(4, lexComplexities);
        set(5, wordTypeCodes);
        set(6, wordRelTypeCode);
    }
}
