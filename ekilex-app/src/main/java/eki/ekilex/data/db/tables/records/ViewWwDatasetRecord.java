/*
 * This file is generated by jOOQ.
 */
package eki.ekilex.data.db.tables.records;


import eki.ekilex.data.db.tables.ViewWwDataset;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record6;
import org.jooq.Row6;
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
public class ViewWwDatasetRecord extends TableRecordImpl<ViewWwDatasetRecord> implements Record6<String, String, String, String, Boolean, Long> {

    private static final long serialVersionUID = 663125342;

    /**
     * Setter for <code>public.view_ww_dataset.code</code>.
     */
    public void setCode(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.view_ww_dataset.code</code>.
     */
    public String getCode() {
        return (String) get(0);
    }

    /**
     * Setter for <code>public.view_ww_dataset.type</code>.
     */
    public void setType(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.view_ww_dataset.type</code>.
     */
    public String getType() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.view_ww_dataset.name</code>.
     */
    public void setName(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.view_ww_dataset.name</code>.
     */
    public String getName() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.view_ww_dataset.description</code>.
     */
    public void setDescription(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.view_ww_dataset.description</code>.
     */
    public String getDescription() {
        return (String) get(3);
    }

    /**
     * Setter for <code>public.view_ww_dataset.is_superior</code>.
     */
    public void setIsSuperior(Boolean value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.view_ww_dataset.is_superior</code>.
     */
    public Boolean getIsSuperior() {
        return (Boolean) get(4);
    }

    /**
     * Setter for <code>public.view_ww_dataset.order_by</code>.
     */
    public void setOrderBy(Long value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.view_ww_dataset.order_by</code>.
     */
    public Long getOrderBy() {
        return (Long) get(5);
    }

    // -------------------------------------------------------------------------
    // Record6 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row6<String, String, String, String, Boolean, Long> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row6<String, String, String, String, Boolean, Long> valuesRow() {
        return (Row6) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field1() {
        return ViewWwDataset.VIEW_WW_DATASET.CODE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return ViewWwDataset.VIEW_WW_DATASET.TYPE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return ViewWwDataset.VIEW_WW_DATASET.NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return ViewWwDataset.VIEW_WW_DATASET.DESCRIPTION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Boolean> field5() {
        return ViewWwDataset.VIEW_WW_DATASET.IS_SUPERIOR;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field6() {
        return ViewWwDataset.VIEW_WW_DATASET.ORDER_BY;
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
    public String component2() {
        return getType();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component3() {
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component4() {
        return getDescription();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean component5() {
        return getIsSuperior();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component6() {
        return getOrderBy();
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
    public String value2() {
        return getType();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getDescription();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean value5() {
        return getIsSuperior();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value6() {
        return getOrderBy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ViewWwDatasetRecord value1(String value) {
        setCode(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ViewWwDatasetRecord value2(String value) {
        setType(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ViewWwDatasetRecord value3(String value) {
        setName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ViewWwDatasetRecord value4(String value) {
        setDescription(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ViewWwDatasetRecord value5(Boolean value) {
        setIsSuperior(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ViewWwDatasetRecord value6(Long value) {
        setOrderBy(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ViewWwDatasetRecord values(String value1, String value2, String value3, String value4, Boolean value5, Long value6) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ViewWwDatasetRecord
     */
    public ViewWwDatasetRecord() {
        super(ViewWwDataset.VIEW_WW_DATASET);
    }

    /**
     * Create a detached, initialised ViewWwDatasetRecord
     */
    public ViewWwDatasetRecord(String code, String type, String name, String description, Boolean isSuperior, Long orderBy) {
        super(ViewWwDataset.VIEW_WW_DATASET);

        set(0, code);
        set(1, type);
        set(2, name);
        set(3, description);
        set(4, isSuperior);
        set(5, orderBy);
    }
}
