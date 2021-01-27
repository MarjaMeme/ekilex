/*
 * This file is generated by jOOQ.
 */
package eki.ekilex.data.db.tables.records;


import eki.ekilex.data.db.tables.ViewWwDataset;

import org.jooq.Field;
import org.jooq.Record7;
import org.jooq.Row7;
import org.jooq.impl.TableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ViewWwDatasetRecord extends TableRecordImpl<ViewWwDatasetRecord> implements Record7<String, String, String, String, String, Boolean, Long> {

    private static final long serialVersionUID = 1107079042;

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
     * Setter for <code>public.view_ww_dataset.contact</code>.
     */
    public void setContact(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.view_ww_dataset.contact</code>.
     */
    public String getContact() {
        return (String) get(4);
    }

    /**
     * Setter for <code>public.view_ww_dataset.is_superior</code>.
     */
    public void setIsSuperior(Boolean value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.view_ww_dataset.is_superior</code>.
     */
    public Boolean getIsSuperior() {
        return (Boolean) get(5);
    }

    /**
     * Setter for <code>public.view_ww_dataset.order_by</code>.
     */
    public void setOrderBy(Long value) {
        set(6, value);
    }

    /**
     * Getter for <code>public.view_ww_dataset.order_by</code>.
     */
    public Long getOrderBy() {
        return (Long) get(6);
    }

    // -------------------------------------------------------------------------
    // Record7 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row7<String, String, String, String, String, Boolean, Long> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    @Override
    public Row7<String, String, String, String, String, Boolean, Long> valuesRow() {
        return (Row7) super.valuesRow();
    }

    @Override
    public Field<String> field1() {
        return ViewWwDataset.VIEW_WW_DATASET.CODE;
    }

    @Override
    public Field<String> field2() {
        return ViewWwDataset.VIEW_WW_DATASET.TYPE;
    }

    @Override
    public Field<String> field3() {
        return ViewWwDataset.VIEW_WW_DATASET.NAME;
    }

    @Override
    public Field<String> field4() {
        return ViewWwDataset.VIEW_WW_DATASET.DESCRIPTION;
    }

    @Override
    public Field<String> field5() {
        return ViewWwDataset.VIEW_WW_DATASET.CONTACT;
    }

    @Override
    public Field<Boolean> field6() {
        return ViewWwDataset.VIEW_WW_DATASET.IS_SUPERIOR;
    }

    @Override
    public Field<Long> field7() {
        return ViewWwDataset.VIEW_WW_DATASET.ORDER_BY;
    }

    @Override
    public String component1() {
        return getCode();
    }

    @Override
    public String component2() {
        return getType();
    }

    @Override
    public String component3() {
        return getName();
    }

    @Override
    public String component4() {
        return getDescription();
    }

    @Override
    public String component5() {
        return getContact();
    }

    @Override
    public Boolean component6() {
        return getIsSuperior();
    }

    @Override
    public Long component7() {
        return getOrderBy();
    }

    @Override
    public String value1() {
        return getCode();
    }

    @Override
    public String value2() {
        return getType();
    }

    @Override
    public String value3() {
        return getName();
    }

    @Override
    public String value4() {
        return getDescription();
    }

    @Override
    public String value5() {
        return getContact();
    }

    @Override
    public Boolean value6() {
        return getIsSuperior();
    }

    @Override
    public Long value7() {
        return getOrderBy();
    }

    @Override
    public ViewWwDatasetRecord value1(String value) {
        setCode(value);
        return this;
    }

    @Override
    public ViewWwDatasetRecord value2(String value) {
        setType(value);
        return this;
    }

    @Override
    public ViewWwDatasetRecord value3(String value) {
        setName(value);
        return this;
    }

    @Override
    public ViewWwDatasetRecord value4(String value) {
        setDescription(value);
        return this;
    }

    @Override
    public ViewWwDatasetRecord value5(String value) {
        setContact(value);
        return this;
    }

    @Override
    public ViewWwDatasetRecord value6(Boolean value) {
        setIsSuperior(value);
        return this;
    }

    @Override
    public ViewWwDatasetRecord value7(Long value) {
        setOrderBy(value);
        return this;
    }

    @Override
    public ViewWwDatasetRecord values(String value1, String value2, String value3, String value4, String value5, Boolean value6, Long value7) {
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
     * Create a detached ViewWwDatasetRecord
     */
    public ViewWwDatasetRecord() {
        super(ViewWwDataset.VIEW_WW_DATASET);
    }

    /**
     * Create a detached, initialised ViewWwDatasetRecord
     */
    public ViewWwDatasetRecord(String code, String type, String name, String description, String contact, Boolean isSuperior, Long orderBy) {
        super(ViewWwDataset.VIEW_WW_DATASET);

        set(0, code);
        set(1, type);
        set(2, name);
        set(3, description);
        set(4, contact);
        set(5, isSuperior);
        set(6, orderBy);
    }
}
