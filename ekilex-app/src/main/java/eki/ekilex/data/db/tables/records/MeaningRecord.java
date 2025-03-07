/*
 * This file is generated by jOOQ.
 */
package eki.ekilex.data.db.tables.records;


import eki.ekilex.data.db.tables.Meaning;

import java.sql.Timestamp;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class MeaningRecord extends UpdatableRecordImpl<MeaningRecord> implements Record2<Long, Timestamp> {

    private static final long serialVersionUID = -166341404;

    /**
     * Setter for <code>public.meaning.id</code>.
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.meaning.id</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public.meaning.manual_event_on</code>.
     */
    public void setManualEventOn(Timestamp value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.meaning.manual_event_on</code>.
     */
    public Timestamp getManualEventOn() {
        return (Timestamp) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row2<Long, Timestamp> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    @Override
    public Row2<Long, Timestamp> valuesRow() {
        return (Row2) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Meaning.MEANING.ID;
    }

    @Override
    public Field<Timestamp> field2() {
        return Meaning.MEANING.MANUAL_EVENT_ON;
    }

    @Override
    public Long component1() {
        return getId();
    }

    @Override
    public Timestamp component2() {
        return getManualEventOn();
    }

    @Override
    public Long value1() {
        return getId();
    }

    @Override
    public Timestamp value2() {
        return getManualEventOn();
    }

    @Override
    public MeaningRecord value1(Long value) {
        setId(value);
        return this;
    }

    @Override
    public MeaningRecord value2(Timestamp value) {
        setManualEventOn(value);
        return this;
    }

    @Override
    public MeaningRecord values(Long value1, Timestamp value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached MeaningRecord
     */
    public MeaningRecord() {
        super(Meaning.MEANING);
    }

    /**
     * Create a detached, initialised MeaningRecord
     */
    public MeaningRecord(Long id, Timestamp manualEventOn) {
        super(Meaning.MEANING);

        set(0, id);
        set(1, manualEventOn);
    }
}
