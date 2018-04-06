/*
 * This file is generated by jOOQ.
*/
package eki.ekilex.data.db.tables.records;


import eki.ekilex.data.db.tables.DefinitionDataset;

import javax.annotation.Generated;

import org.jooq.Field;
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
public class DefinitionDatasetRecord extends UpdatableRecordImpl<DefinitionDatasetRecord> implements Record2<Long, String> {

    private static final long serialVersionUID = 1416332779;

    /**
     * Setter for <code>public.definition_dataset.definition_id</code>.
     */
    public void setDefinitionId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.definition_dataset.definition_id</code>.
     */
    public Long getDefinitionId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public.definition_dataset.dataset_code</code>.
     */
    public void setDatasetCode(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.definition_dataset.dataset_code</code>.
     */
    public String getDatasetCode() {
        return (String) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record2<Long, String> key() {
        return (Record2) super.key();
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<Long, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<Long, String> valuesRow() {
        return (Row2) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field1() {
        return DefinitionDataset.DEFINITION_DATASET.DEFINITION_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return DefinitionDataset.DEFINITION_DATASET.DATASET_CODE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component1() {
        return getDefinitionId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component2() {
        return getDatasetCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value1() {
        return getDefinitionId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getDatasetCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DefinitionDatasetRecord value1(Long value) {
        setDefinitionId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DefinitionDatasetRecord value2(String value) {
        setDatasetCode(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DefinitionDatasetRecord values(Long value1, String value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached DefinitionDatasetRecord
     */
    public DefinitionDatasetRecord() {
        super(DefinitionDataset.DEFINITION_DATASET);
    }

    /**
     * Create a detached, initialised DefinitionDatasetRecord
     */
    public DefinitionDatasetRecord(Long definitionId, String datasetCode) {
        super(DefinitionDataset.DEFINITION_DATASET);

        set(0, definitionId);
        set(1, datasetCode);
    }
}
