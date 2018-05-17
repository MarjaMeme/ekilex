/*
 * This file is generated by jOOQ.
*/
package eki.ekilex.data.db.tables.records;


import eki.ekilex.data.db.tables.LexemeRegister;

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
        "jOOQ version:3.10.7"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class LexemeRegisterRecord extends UpdatableRecordImpl<LexemeRegisterRecord> implements Record4<Long, Long, String, Long> {

    private static final long serialVersionUID = -2096599271;

    /**
     * Setter for <code>public.lexeme_register.id</code>.
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.lexeme_register.id</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public.lexeme_register.lexeme_id</code>.
     */
    public void setLexemeId(Long value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.lexeme_register.lexeme_id</code>.
     */
    public Long getLexemeId() {
        return (Long) get(1);
    }

    /**
     * Setter for <code>public.lexeme_register.register_code</code>.
     */
    public void setRegisterCode(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.lexeme_register.register_code</code>.
     */
    public String getRegisterCode() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.lexeme_register.order_by</code>.
     */
    public void setOrderBy(Long value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.lexeme_register.order_by</code>.
     */
    public Long getOrderBy() {
        return (Long) get(3);
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
    public Row4<Long, Long, String, Long> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row4<Long, Long, String, Long> valuesRow() {
        return (Row4) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field1() {
        return LexemeRegister.LEXEME_REGISTER.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field2() {
        return LexemeRegister.LEXEME_REGISTER.LEXEME_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return LexemeRegister.LEXEME_REGISTER.REGISTER_CODE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field4() {
        return LexemeRegister.LEXEME_REGISTER.ORDER_BY;
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
        return getLexemeId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component3() {
        return getRegisterCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component4() {
        return getOrderBy();
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
        return getLexemeId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getRegisterCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value4() {
        return getOrderBy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LexemeRegisterRecord value1(Long value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LexemeRegisterRecord value2(Long value) {
        setLexemeId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LexemeRegisterRecord value3(String value) {
        setRegisterCode(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LexemeRegisterRecord value4(Long value) {
        setOrderBy(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LexemeRegisterRecord values(Long value1, Long value2, String value3, Long value4) {
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
     * Create a detached LexemeRegisterRecord
     */
    public LexemeRegisterRecord() {
        super(LexemeRegister.LEXEME_REGISTER);
    }

    /**
     * Create a detached, initialised LexemeRegisterRecord
     */
    public LexemeRegisterRecord(Long id, Long lexemeId, String registerCode, Long orderBy) {
        super(LexemeRegister.LEXEME_REGISTER);

        set(0, id);
        set(1, lexemeId);
        set(2, registerCode);
        set(3, orderBy);
    }
}
