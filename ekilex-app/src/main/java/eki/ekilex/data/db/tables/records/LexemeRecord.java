/*
 * This file is generated by jOOQ.
 */
package eki.ekilex.data.db.tables.records;


import eki.ekilex.data.db.tables.Lexeme;

import java.math.BigDecimal;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record14;
import org.jooq.Row14;
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
public class LexemeRecord extends UpdatableRecordImpl<LexemeRecord> implements Record14<Long, Long, Long, String, String, String, BigDecimal, Integer, Integer, Integer, String, String, String, Long> {

    private static final long serialVersionUID = 2005527192;

    /**
     * Setter for <code>public.lexeme.id</code>.
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.lexeme.id</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public.lexeme.word_id</code>.
     */
    public void setWordId(Long value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.lexeme.word_id</code>.
     */
    public Long getWordId() {
        return (Long) get(1);
    }

    /**
     * Setter for <code>public.lexeme.meaning_id</code>.
     */
    public void setMeaningId(Long value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.lexeme.meaning_id</code>.
     */
    public Long getMeaningId() {
        return (Long) get(2);
    }

    /**
     * Setter for <code>public.lexeme.dataset_code</code>.
     */
    public void setDatasetCode(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.lexeme.dataset_code</code>.
     */
    public String getDatasetCode() {
        return (String) get(3);
    }

    /**
     * Setter for <code>public.lexeme.type</code>.
     */
    public void setType(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.lexeme.type</code>.
     */
    public String getType() {
        return (String) get(4);
    }

    /**
     * Setter for <code>public.lexeme.frequency_group_code</code>.
     */
    public void setFrequencyGroupCode(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.lexeme.frequency_group_code</code>.
     */
    public String getFrequencyGroupCode() {
        return (String) get(5);
    }

    /**
     * Setter for <code>public.lexeme.corpus_frequency</code>.
     */
    public void setCorpusFrequency(BigDecimal value) {
        set(6, value);
    }

    /**
     * Getter for <code>public.lexeme.corpus_frequency</code>.
     */
    public BigDecimal getCorpusFrequency() {
        return (BigDecimal) get(6);
    }

    /**
     * Setter for <code>public.lexeme.level1</code>.
     */
    public void setLevel1(Integer value) {
        set(7, value);
    }

    /**
     * Getter for <code>public.lexeme.level1</code>.
     */
    public Integer getLevel1() {
        return (Integer) get(7);
    }

    /**
     * Setter for <code>public.lexeme.level2</code>.
     */
    public void setLevel2(Integer value) {
        set(8, value);
    }

    /**
     * Getter for <code>public.lexeme.level2</code>.
     */
    public Integer getLevel2() {
        return (Integer) get(8);
    }

    /**
     * Setter for <code>public.lexeme.level3</code>.
     */
    public void setLevel3(Integer value) {
        set(9, value);
    }

    /**
     * Getter for <code>public.lexeme.level3</code>.
     */
    public Integer getLevel3() {
        return (Integer) get(9);
    }

    /**
     * Setter for <code>public.lexeme.value_state_code</code>.
     */
    public void setValueStateCode(String value) {
        set(10, value);
    }

    /**
     * Getter for <code>public.lexeme.value_state_code</code>.
     */
    public String getValueStateCode() {
        return (String) get(10);
    }

    /**
     * Setter for <code>public.lexeme.process_state_code</code>.
     */
    public void setProcessStateCode(String value) {
        set(11, value);
    }

    /**
     * Getter for <code>public.lexeme.process_state_code</code>.
     */
    public String getProcessStateCode() {
        return (String) get(11);
    }

    /**
     * Setter for <code>public.lexeme.complexity</code>.
     */
    public void setComplexity(String value) {
        set(12, value);
    }

    /**
     * Getter for <code>public.lexeme.complexity</code>.
     */
    public String getComplexity() {
        return (String) get(12);
    }

    /**
     * Setter for <code>public.lexeme.order_by</code>.
     */
    public void setOrderBy(Long value) {
        set(13, value);
    }

    /**
     * Getter for <code>public.lexeme.order_by</code>.
     */
    public Long getOrderBy() {
        return (Long) get(13);
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
    // Record14 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row14<Long, Long, Long, String, String, String, BigDecimal, Integer, Integer, Integer, String, String, String, Long> fieldsRow() {
        return (Row14) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row14<Long, Long, Long, String, String, String, BigDecimal, Integer, Integer, Integer, String, String, String, Long> valuesRow() {
        return (Row14) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field1() {
        return Lexeme.LEXEME.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field2() {
        return Lexeme.LEXEME.WORD_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field3() {
        return Lexeme.LEXEME.MEANING_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return Lexeme.LEXEME.DATASET_CODE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field5() {
        return Lexeme.LEXEME.TYPE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field6() {
        return Lexeme.LEXEME.FREQUENCY_GROUP_CODE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<BigDecimal> field7() {
        return Lexeme.LEXEME.CORPUS_FREQUENCY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field8() {
        return Lexeme.LEXEME.LEVEL1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field9() {
        return Lexeme.LEXEME.LEVEL2;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field10() {
        return Lexeme.LEXEME.LEVEL3;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field11() {
        return Lexeme.LEXEME.VALUE_STATE_CODE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field12() {
        return Lexeme.LEXEME.PROCESS_STATE_CODE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field13() {
        return Lexeme.LEXEME.COMPLEXITY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field14() {
        return Lexeme.LEXEME.ORDER_BY;
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
    public Long component3() {
        return getMeaningId();
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
    public String component5() {
        return getType();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component6() {
        return getFrequencyGroupCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal component7() {
        return getCorpusFrequency();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component8() {
        return getLevel1();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component9() {
        return getLevel2();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component10() {
        return getLevel3();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component11() {
        return getValueStateCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component12() {
        return getProcessStateCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component13() {
        return getComplexity();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component14() {
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
        return getWordId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value3() {
        return getMeaningId();
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
    public String value5() {
        return getType();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value6() {
        return getFrequencyGroupCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal value7() {
        return getCorpusFrequency();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value8() {
        return getLevel1();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value9() {
        return getLevel2();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value10() {
        return getLevel3();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value11() {
        return getValueStateCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value12() {
        return getProcessStateCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value13() {
        return getComplexity();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value14() {
        return getOrderBy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LexemeRecord value1(Long value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LexemeRecord value2(Long value) {
        setWordId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LexemeRecord value3(Long value) {
        setMeaningId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LexemeRecord value4(String value) {
        setDatasetCode(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LexemeRecord value5(String value) {
        setType(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LexemeRecord value6(String value) {
        setFrequencyGroupCode(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LexemeRecord value7(BigDecimal value) {
        setCorpusFrequency(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LexemeRecord value8(Integer value) {
        setLevel1(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LexemeRecord value9(Integer value) {
        setLevel2(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LexemeRecord value10(Integer value) {
        setLevel3(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LexemeRecord value11(String value) {
        setValueStateCode(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LexemeRecord value12(String value) {
        setProcessStateCode(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LexemeRecord value13(String value) {
        setComplexity(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LexemeRecord value14(Long value) {
        setOrderBy(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LexemeRecord values(Long value1, Long value2, Long value3, String value4, String value5, String value6, BigDecimal value7, Integer value8, Integer value9, Integer value10, String value11, String value12, String value13, Long value14) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        value11(value11);
        value12(value12);
        value13(value13);
        value14(value14);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached LexemeRecord
     */
    public LexemeRecord() {
        super(Lexeme.LEXEME);
    }

    /**
     * Create a detached, initialised LexemeRecord
     */
    public LexemeRecord(Long id, Long wordId, Long meaningId, String datasetCode, String type, String frequencyGroupCode, BigDecimal corpusFrequency, Integer level1, Integer level2, Integer level3, String valueStateCode, String processStateCode, String complexity, Long orderBy) {
        super(Lexeme.LEXEME);

        set(0, id);
        set(1, wordId);
        set(2, meaningId);
        set(3, datasetCode);
        set(4, type);
        set(5, frequencyGroupCode);
        set(6, corpusFrequency);
        set(7, level1);
        set(8, level2);
        set(9, level3);
        set(10, valueStateCode);
        set(11, processStateCode);
        set(12, complexity);
        set(13, orderBy);
    }
}
