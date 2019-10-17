/*
 * This file is generated by jOOQ.
 */
package eki.ekilex.data.db.tables.records;


import eki.ekilex.data.db.tables.WordRelMapping;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.TableRecordImpl;


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
public class WordRelMappingRecord extends TableRecordImpl<WordRelMappingRecord> implements Record2<String, String> {

    private static final long serialVersionUID = -1949799910;

    /**
     * Setter for <code>public.word_rel_mapping.code1</code>.
     */
    public void setCode1(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.word_rel_mapping.code1</code>.
     */
    public String getCode1() {
        return (String) get(0);
    }

    /**
     * Setter for <code>public.word_rel_mapping.code2</code>.
     */
    public void setCode2(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.word_rel_mapping.code2</code>.
     */
    public String getCode2() {
        return (String) get(1);
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<String, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<String, String> valuesRow() {
        return (Row2) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field1() {
        return WordRelMapping.WORD_REL_MAPPING.CODE1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return WordRelMapping.WORD_REL_MAPPING.CODE2;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component1() {
        return getCode1();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component2() {
        return getCode2();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value1() {
        return getCode1();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getCode2();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WordRelMappingRecord value1(String value) {
        setCode1(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WordRelMappingRecord value2(String value) {
        setCode2(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WordRelMappingRecord values(String value1, String value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached WordRelMappingRecord
     */
    public WordRelMappingRecord() {
        super(WordRelMapping.WORD_REL_MAPPING);
    }

    /**
     * Create a detached, initialised WordRelMappingRecord
     */
    public WordRelMappingRecord(String code1, String code2) {
        super(WordRelMapping.WORD_REL_MAPPING);

        set(0, code1);
        set(1, code2);
    }
}
