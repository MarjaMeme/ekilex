/*
 * This file is generated by jOOQ.
 */
package eki.ekilex.data.db.tables.records;


import eki.ekilex.data.db.tables.Collocation;

import java.math.BigDecimal;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record7;
import org.jooq.Row7;
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
public class CollocationRecord extends UpdatableRecordImpl<CollocationRecord> implements Record7<Long, String, String, BigDecimal, BigDecimal, String[], String> {

    private static final long serialVersionUID = 1015512254;

    /**
     * Setter for <code>public.collocation.id</code>.
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.collocation.id</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public.collocation.value</code>.
     */
    public void setValue(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.collocation.value</code>.
     */
    public String getValue() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.collocation.definition</code>.
     */
    public void setDefinition(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.collocation.definition</code>.
     */
    public String getDefinition() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.collocation.frequency</code>.
     */
    public void setFrequency(BigDecimal value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.collocation.frequency</code>.
     */
    public BigDecimal getFrequency() {
        return (BigDecimal) get(3);
    }

    /**
     * Setter for <code>public.collocation.score</code>.
     */
    public void setScore(BigDecimal value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.collocation.score</code>.
     */
    public BigDecimal getScore() {
        return (BigDecimal) get(4);
    }

    /**
     * Setter for <code>public.collocation.usages</code>.
     */
    public void setUsages(String... value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.collocation.usages</code>.
     */
    public String[] getUsages() {
        return (String[]) get(5);
    }

    /**
     * Setter for <code>public.collocation.complexity</code>.
     */
    public void setComplexity(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>public.collocation.complexity</code>.
     */
    public String getComplexity() {
        return (String) get(6);
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
    // Record7 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row7<Long, String, String, BigDecimal, BigDecimal, String[], String> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row7<Long, String, String, BigDecimal, BigDecimal, String[], String> valuesRow() {
        return (Row7) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field1() {
        return Collocation.COLLOCATION.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return Collocation.COLLOCATION.VALUE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return Collocation.COLLOCATION.DEFINITION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<BigDecimal> field4() {
        return Collocation.COLLOCATION.FREQUENCY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<BigDecimal> field5() {
        return Collocation.COLLOCATION.SCORE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String[]> field6() {
        return Collocation.COLLOCATION.USAGES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field7() {
        return Collocation.COLLOCATION.COMPLEXITY;
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
        return getValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component3() {
        return getDefinition();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal component4() {
        return getFrequency();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal component5() {
        return getScore();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] component6() {
        return getUsages();
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
    public Long value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getDefinition();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal value4() {
        return getFrequency();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal value5() {
        return getScore();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] value6() {
        return getUsages();
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
    public CollocationRecord value1(Long value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CollocationRecord value2(String value) {
        setValue(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CollocationRecord value3(String value) {
        setDefinition(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CollocationRecord value4(BigDecimal value) {
        setFrequency(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CollocationRecord value5(BigDecimal value) {
        setScore(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CollocationRecord value6(String... value) {
        setUsages(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CollocationRecord value7(String value) {
        setComplexity(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CollocationRecord values(Long value1, String value2, String value3, BigDecimal value4, BigDecimal value5, String[] value6, String value7) {
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
     * Create a detached CollocationRecord
     */
    public CollocationRecord() {
        super(Collocation.COLLOCATION);
    }

    /**
     * Create a detached, initialised CollocationRecord
     */
    public CollocationRecord(Long id, String value, String definition, BigDecimal frequency, BigDecimal score, String[] usages, String complexity) {
        super(Collocation.COLLOCATION);

        set(0, id);
        set(1, value);
        set(2, definition);
        set(3, frequency);
        set(4, score);
        set(5, usages);
        set(6, complexity);
    }
}
