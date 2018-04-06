/*
 * This file is generated by jOOQ.
*/
package eki.ekilex.data.db.tables.records;


import eki.ekilex.data.db.tables.Source;

import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record8;
import org.jooq.Row8;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.6"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SourceRecord extends UpdatableRecordImpl<SourceRecord> implements Record8<Long, String, Timestamp, String, Timestamp, String, String, String> {

    private static final long serialVersionUID = 64459053;

    /**
     * Setter for <code>public.source.id</code>.
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.source.id</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public.source.concept</code>.
     */
    public void setConcept(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.source.concept</code>.
     */
    public String getConcept() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.source.created_on</code>.
     */
    public void setCreatedOn(Timestamp value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.source.created_on</code>.
     */
    public Timestamp getCreatedOn() {
        return (Timestamp) get(2);
    }

    /**
     * Setter for <code>public.source.created_by</code>.
     */
    public void setCreatedBy(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.source.created_by</code>.
     */
    public String getCreatedBy() {
        return (String) get(3);
    }

    /**
     * Setter for <code>public.source.modified_on</code>.
     */
    public void setModifiedOn(Timestamp value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.source.modified_on</code>.
     */
    public Timestamp getModifiedOn() {
        return (Timestamp) get(4);
    }

    /**
     * Setter for <code>public.source.modified_by</code>.
     */
    public void setModifiedBy(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.source.modified_by</code>.
     */
    public String getModifiedBy() {
        return (String) get(5);
    }

    /**
     * Setter for <code>public.source.process_state_code</code>.
     */
    public void setProcessStateCode(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>public.source.process_state_code</code>.
     */
    public String getProcessStateCode() {
        return (String) get(6);
    }

    /**
     * Setter for <code>public.source.type</code>.
     */
    public void setType(String value) {
        set(7, value);
    }

    /**
     * Getter for <code>public.source.type</code>.
     */
    public String getType() {
        return (String) get(7);
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
    // Record8 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row8<Long, String, Timestamp, String, Timestamp, String, String, String> fieldsRow() {
        return (Row8) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row8<Long, String, Timestamp, String, Timestamp, String, String, String> valuesRow() {
        return (Row8) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field1() {
        return Source.SOURCE.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return Source.SOURCE.CONCEPT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field3() {
        return Source.SOURCE.CREATED_ON;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return Source.SOURCE.CREATED_BY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field5() {
        return Source.SOURCE.MODIFIED_ON;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field6() {
        return Source.SOURCE.MODIFIED_BY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field7() {
        return Source.SOURCE.PROCESS_STATE_CODE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field8() {
        return Source.SOURCE.TYPE;
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
        return getConcept();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp component3() {
        return getCreatedOn();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component4() {
        return getCreatedBy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp component5() {
        return getModifiedOn();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component6() {
        return getModifiedBy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component7() {
        return getProcessStateCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component8() {
        return getType();
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
        return getConcept();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value3() {
        return getCreatedOn();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getCreatedBy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value5() {
        return getModifiedOn();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value6() {
        return getModifiedBy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value7() {
        return getProcessStateCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value8() {
        return getType();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SourceRecord value1(Long value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SourceRecord value2(String value) {
        setConcept(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SourceRecord value3(Timestamp value) {
        setCreatedOn(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SourceRecord value4(String value) {
        setCreatedBy(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SourceRecord value5(Timestamp value) {
        setModifiedOn(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SourceRecord value6(String value) {
        setModifiedBy(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SourceRecord value7(String value) {
        setProcessStateCode(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SourceRecord value8(String value) {
        setType(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SourceRecord values(Long value1, String value2, Timestamp value3, String value4, Timestamp value5, String value6, String value7, String value8) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached SourceRecord
     */
    public SourceRecord() {
        super(Source.SOURCE);
    }

    /**
     * Create a detached, initialised SourceRecord
     */
    public SourceRecord(Long id, String concept, Timestamp createdOn, String createdBy, Timestamp modifiedOn, String modifiedBy, String processStateCode, String type) {
        super(Source.SOURCE);

        set(0, id);
        set(1, concept);
        set(2, createdOn);
        set(3, createdBy);
        set(4, modifiedOn);
        set(5, modifiedBy);
        set(6, processStateCode);
        set(7, type);
    }
}
