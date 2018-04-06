/*
 * This file is generated by jOOQ.
*/
package eki.ekilex.data.db.tables.records;


import eki.ekilex.data.db.tables.ValueState;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Row2;
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
public class ValueStateRecord extends UpdatableRecordImpl<ValueStateRecord> implements Record2<String, String[]> {

    private static final long serialVersionUID = -1786716670;

    /**
     * Setter for <code>public.value_state.code</code>.
     */
    public void setCode(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.value_state.code</code>.
     */
    public String getCode() {
        return (String) get(0);
    }

    /**
     * Setter for <code>public.value_state.datasets</code>.
     */
    public void setDatasets(String... value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.value_state.datasets</code>.
     */
    public String[] getDatasets() {
        return (String[]) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<String> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<String, String[]> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<String, String[]> valuesRow() {
        return (Row2) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field1() {
        return ValueState.VALUE_STATE.CODE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String[]> field2() {
        return ValueState.VALUE_STATE.DATASETS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component1() {
        return getCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] component2() {
        return getDatasets();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value1() {
        return getCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] value2() {
        return getDatasets();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ValueStateRecord value1(String value) {
        setCode(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ValueStateRecord value2(String... value) {
        setDatasets(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ValueStateRecord values(String value1, String[] value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ValueStateRecord
     */
    public ValueStateRecord() {
        super(ValueState.VALUE_STATE);
    }

    /**
     * Create a detached, initialised ValueStateRecord
     */
    public ValueStateRecord(String code, String[] datasets) {
        super(ValueState.VALUE_STATE);

        set(0, code);
        set(1, datasets);
    }
}
