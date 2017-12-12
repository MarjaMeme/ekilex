/*
 * This file is generated by jOOQ.
*/
package eki.eve.data.db.tables.records;


import eki.eve.data.db.tables.WordGuid;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
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
public class WordGuidRecord extends UpdatableRecordImpl<WordGuidRecord> implements Record4<Long, Long, String, String> {

    private static final long serialVersionUID = -1920607136;

    /**
     * Setter for <code>public.word_guid.id</code>.
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.word_guid.id</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public.word_guid.word_id</code>.
     */
    public void setWordId(Long value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.word_guid.word_id</code>.
     */
    public Long getWordId() {
        return (Long) get(1);
    }

    /**
     * Setter for <code>public.word_guid.guid</code>.
     */
    public void setGuid(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.word_guid.guid</code>.
     */
    public String getGuid() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.word_guid.dataset_code</code>.
     */
    public void setDatasetCode(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.word_guid.dataset_code</code>.
     */
    public String getDatasetCode() {
        return (String) get(3);
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
    // Record4 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row4<Long, Long, String, String> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row4<Long, Long, String, String> valuesRow() {
        return (Row4) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field1() {
        return WordGuid.WORD_GUID.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field2() {
        return WordGuid.WORD_GUID.WORD_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return WordGuid.WORD_GUID.GUID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return WordGuid.WORD_GUID.DATASET_CODE;
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
    public Long component2() {
        return getWordId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component3() {
        return getGuid();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component4() {
        return getDatasetCode();
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
    public Long value2() {
        return getWordId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getGuid();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getDatasetCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WordGuidRecord value1(Long value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WordGuidRecord value2(Long value) {
        setWordId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WordGuidRecord value3(String value) {
        setGuid(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WordGuidRecord value4(String value) {
        setDatasetCode(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WordGuidRecord values(Long value1, Long value2, String value3, String value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached WordGuidRecord
     */
    public WordGuidRecord() {
        super(WordGuid.WORD_GUID);
    }

    /**
     * Create a detached, initialised WordGuidRecord
     */
    public WordGuidRecord(Long id, Long wordId, String guid, String datasetCode) {
        super(WordGuid.WORD_GUID);

        set(0, id);
        set(1, wordId);
        set(2, guid);
        set(3, datasetCode);
    }
}
