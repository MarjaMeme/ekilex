/*
 * This file is generated by jOOQ.
*/
package eki.eve.data.db.tables.records;


import eki.eve.data.db.tables.Word;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class WordRecord extends UpdatableRecordImpl<WordRecord> implements Record5<Long, String, String, Integer, String> {

    private static final long serialVersionUID = 1985795773;

    /**
     * Setter for <code>public.word.id</code>.
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.word.id</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public.word.lang</code>.
     */
    public void setLang(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.word.lang</code>.
     */
    public String getLang() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.word.morph_code</code>.
     */
    public void setMorphCode(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.word.morph_code</code>.
     */
    public String getMorphCode() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.word.homonym_nr</code>.
     */
    public void setHomonymNr(Integer value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.word.homonym_nr</code>.
     */
    public Integer getHomonymNr() {
        return (Integer) get(3);
    }

    /**
     * Setter for <code>public.word.display_morph_code</code>.
     */
    public void setDisplayMorphCode(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.word.display_morph_code</code>.
     */
    public String getDisplayMorphCode() {
        return (String) get(4);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record5 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row5<Long, String, String, Integer, String> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row5<Long, String, String, Integer, String> valuesRow() {
        return (Row5) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field1() {
        return Word.WORD.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return Word.WORD.LANG;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return Word.WORD.MORPH_CODE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field4() {
        return Word.WORD.HOMONYM_NR;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field5() {
        return Word.WORD.DISPLAY_MORPH_CODE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component2() {
        return getLang();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component3() {
        return getMorphCode();
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
    public String component5() {
        return getDisplayMorphCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getLang();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getMorphCode();
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
    public String value5() {
        return getDisplayMorphCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WordRecord value1(Long value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WordRecord value2(String value) {
        setLang(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WordRecord value3(String value) {
        setMorphCode(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WordRecord value4(Integer value) {
        setHomonymNr(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WordRecord value5(String value) {
        setDisplayMorphCode(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WordRecord values(Long value1, String value2, String value3, Integer value4, String value5) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached WordRecord
     */
    public WordRecord() {
        super(Word.WORD);
    }

    /**
     * Create a detached, initialised WordRecord
     */
    public WordRecord(Long id, String lang, String morphCode, Integer homonymNr, String displayMorphCode) {
        super(Word.WORD);

        set(0, id);
        set(1, lang);
        set(2, morphCode);
        set(3, homonymNr);
        set(4, displayMorphCode);
    }
}
