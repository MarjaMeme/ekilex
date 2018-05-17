/*
 * This file is generated by jOOQ.
*/
package eki.ekilex.data.db.tables.records;


import eki.ekilex.data.db.tables.MeaningDomain;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
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
public class MeaningDomainRecord extends UpdatableRecordImpl<MeaningDomainRecord> implements Record5<Long, Long, String, String, Long> {

    private static final long serialVersionUID = -1719060845;

    /**
     * Setter for <code>public.meaning_domain.id</code>.
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.meaning_domain.id</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public.meaning_domain.meaning_id</code>.
     */
    public void setMeaningId(Long value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.meaning_domain.meaning_id</code>.
     */
    public Long getMeaningId() {
        return (Long) get(1);
    }

    /**
     * Setter for <code>public.meaning_domain.domain_code</code>.
     */
    public void setDomainCode(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.meaning_domain.domain_code</code>.
     */
    public String getDomainCode() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.meaning_domain.domain_origin</code>.
     */
    public void setDomainOrigin(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.meaning_domain.domain_origin</code>.
     */
    public String getDomainOrigin() {
        return (String) get(3);
    }

    /**
     * Setter for <code>public.meaning_domain.order_by</code>.
     */
    public void setOrderBy(Long value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.meaning_domain.order_by</code>.
     */
    public Long getOrderBy() {
        return (Long) get(4);
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
    // Record5 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row5<Long, Long, String, String, Long> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row5<Long, Long, String, String, Long> valuesRow() {
        return (Row5) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field1() {
        return MeaningDomain.MEANING_DOMAIN.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field2() {
        return MeaningDomain.MEANING_DOMAIN.MEANING_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return MeaningDomain.MEANING_DOMAIN.DOMAIN_CODE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return MeaningDomain.MEANING_DOMAIN.DOMAIN_ORIGIN;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field5() {
        return MeaningDomain.MEANING_DOMAIN.ORDER_BY;
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
        return getDomainCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component4() {
        return getDomainOrigin();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component5() {
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
        return getMeaningId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getDomainCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getDomainOrigin();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value5() {
        return getOrderBy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MeaningDomainRecord value1(Long value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MeaningDomainRecord value2(Long value) {
        setMeaningId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MeaningDomainRecord value3(String value) {
        setDomainCode(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MeaningDomainRecord value4(String value) {
        setDomainOrigin(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MeaningDomainRecord value5(Long value) {
        setOrderBy(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MeaningDomainRecord values(Long value1, Long value2, String value3, String value4, Long value5) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached MeaningDomainRecord
     */
    public MeaningDomainRecord() {
        super(MeaningDomain.MEANING_DOMAIN);
    }

    /**
     * Create a detached, initialised MeaningDomainRecord
     */
    public MeaningDomainRecord(Long id, Long meaningId, String domainCode, String domainOrigin, Long orderBy) {
        super(MeaningDomain.MEANING_DOMAIN);

        set(0, id);
        set(1, meaningId);
        set(2, domainCode);
        set(3, domainOrigin);
        set(4, orderBy);
    }
}
