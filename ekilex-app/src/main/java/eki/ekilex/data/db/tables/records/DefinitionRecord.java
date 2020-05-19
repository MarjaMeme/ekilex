/*
 * This file is generated by jOOQ.
 */
package eki.ekilex.data.db.tables.records;


import eki.ekilex.data.db.tables.Definition;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record9;
import org.jooq.Row9;
import org.jooq.impl.UpdatableRecordImpl;


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
public class DefinitionRecord extends UpdatableRecordImpl<DefinitionRecord> implements Record9<Long, Long, String, String, String, String, String, Long, Boolean> {

    private static final long serialVersionUID = 1069061059;

    /**
     * Setter for <code>public.definition.id</code>.
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.definition.id</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public.definition.meaning_id</code>.
     */
    public void setMeaningId(Long value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.definition.meaning_id</code>.
     */
    public Long getMeaningId() {
        return (Long) get(1);
    }

    /**
     * Setter for <code>public.definition.definition_type_code</code>.
     */
    public void setDefinitionTypeCode(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.definition.definition_type_code</code>.
     */
    public String getDefinitionTypeCode() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.definition.value</code>.
     */
    public void setValue(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.definition.value</code>.
     */
    public String getValue() {
        return (String) get(3);
    }

    /**
     * Setter for <code>public.definition.value_prese</code>.
     */
    public void setValuePrese(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.definition.value_prese</code>.
     */
    public String getValuePrese() {
        return (String) get(4);
    }

    /**
     * Setter for <code>public.definition.lang</code>.
     */
    public void setLang(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.definition.lang</code>.
     */
    public String getLang() {
        return (String) get(5);
    }

    /**
     * Setter for <code>public.definition.complexity</code>.
     */
    public void setComplexity(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>public.definition.complexity</code>.
     */
    public String getComplexity() {
        return (String) get(6);
    }

    /**
     * Setter for <code>public.definition.order_by</code>.
     */
    public void setOrderBy(Long value) {
        set(7, value);
    }

    /**
     * Getter for <code>public.definition.order_by</code>.
     */
    public Long getOrderBy() {
        return (Long) get(7);
    }

    /**
     * Setter for <code>public.definition.is_public</code>.
     */
    public void setIsPublic(Boolean value) {
        set(8, value);
    }

    /**
     * Getter for <code>public.definition.is_public</code>.
     */
    public Boolean getIsPublic() {
        return (Boolean) get(8);
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
    // Record9 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row9<Long, Long, String, String, String, String, String, Long, Boolean> fieldsRow() {
        return (Row9) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row9<Long, Long, String, String, String, String, String, Long, Boolean> valuesRow() {
        return (Row9) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field1() {
        return Definition.DEFINITION.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field2() {
        return Definition.DEFINITION.MEANING_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return Definition.DEFINITION.DEFINITION_TYPE_CODE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return Definition.DEFINITION.VALUE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field5() {
        return Definition.DEFINITION.VALUE_PRESE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field6() {
        return Definition.DEFINITION.LANG;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field7() {
        return Definition.DEFINITION.COMPLEXITY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field8() {
        return Definition.DEFINITION.ORDER_BY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Boolean> field9() {
        return Definition.DEFINITION.IS_PUBLIC;
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
        return getMeaningId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component3() {
        return getDefinitionTypeCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component4() {
        return getValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component5() {
        return getValuePrese();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component6() {
        return getLang();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component7() {
        return getComplexity();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component8() {
        return getOrderBy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean component9() {
        return getIsPublic();
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
        return getMeaningId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getDefinitionTypeCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value5() {
        return getValuePrese();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value6() {
        return getLang();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value7() {
        return getComplexity();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value8() {
        return getOrderBy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean value9() {
        return getIsPublic();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DefinitionRecord value1(Long value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DefinitionRecord value2(Long value) {
        setMeaningId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DefinitionRecord value3(String value) {
        setDefinitionTypeCode(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DefinitionRecord value4(String value) {
        setValue(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DefinitionRecord value5(String value) {
        setValuePrese(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DefinitionRecord value6(String value) {
        setLang(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DefinitionRecord value7(String value) {
        setComplexity(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DefinitionRecord value8(Long value) {
        setOrderBy(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DefinitionRecord value9(Boolean value) {
        setIsPublic(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DefinitionRecord values(Long value1, Long value2, String value3, String value4, String value5, String value6, String value7, Long value8, Boolean value9) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached DefinitionRecord
     */
    public DefinitionRecord() {
        super(Definition.DEFINITION);
    }

    /**
     * Create a detached, initialised DefinitionRecord
     */
    public DefinitionRecord(Long id, Long meaningId, String definitionTypeCode, String value, String valuePrese, String lang, String complexity, Long orderBy, Boolean isPublic) {
        super(Definition.DEFINITION);

        set(0, id);
        set(1, meaningId);
        set(2, definitionTypeCode);
        set(3, value);
        set(4, valuePrese);
        set(5, lang);
        set(6, complexity);
        set(7, orderBy);
        set(8, isPublic);
    }
}
