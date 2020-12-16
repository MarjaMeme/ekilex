/*
 * This file is generated by jOOQ.
 */
package eki.ekilex.data.db.tables;


import eki.ekilex.data.db.Indexes;
import eki.ekilex.data.db.Keys;
import eki.ekilex.data.db.Public;
import eki.ekilex.data.db.tables.records.MeaningRelationRecord;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row6;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class MeaningRelation extends TableImpl<MeaningRelationRecord> {

    private static final long serialVersionUID = -2129047518;

    /**
     * The reference instance of <code>public.meaning_relation</code>
     */
    public static final MeaningRelation MEANING_RELATION = new MeaningRelation();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<MeaningRelationRecord> getRecordType() {
        return MeaningRelationRecord.class;
    }

    /**
     * The column <code>public.meaning_relation.id</code>.
     */
    public final TableField<MeaningRelationRecord, Long> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('meaning_relation_id_seq'::regclass)", org.jooq.impl.SQLDataType.BIGINT)), this, "");

    /**
     * The column <code>public.meaning_relation.meaning1_id</code>.
     */
    public final TableField<MeaningRelationRecord, Long> MEANING1_ID = createField(DSL.name("meaning1_id"), org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.meaning_relation.meaning2_id</code>.
     */
    public final TableField<MeaningRelationRecord, Long> MEANING2_ID = createField(DSL.name("meaning2_id"), org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.meaning_relation.meaning_rel_type_code</code>.
     */
    public final TableField<MeaningRelationRecord, String> MEANING_REL_TYPE_CODE = createField(DSL.name("meaning_rel_type_code"), org.jooq.impl.SQLDataType.VARCHAR(100).nullable(false), this, "");

    /**
     * The column <code>public.meaning_relation.order_by</code>.
     */
    public final TableField<MeaningRelationRecord, Long> ORDER_BY = createField(DSL.name("order_by"), org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('meaning_relation_order_by_seq'::regclass)", org.jooq.impl.SQLDataType.BIGINT)), this, "");

    /**
     * The column <code>public.meaning_relation.weight</code>.
     */
    public final TableField<MeaningRelationRecord, BigDecimal> WEIGHT = createField(DSL.name("weight"), org.jooq.impl.SQLDataType.NUMERIC(5, 4), this, "");

    /**
     * Create a <code>public.meaning_relation</code> table reference
     */
    public MeaningRelation() {
        this(DSL.name("meaning_relation"), null);
    }

    /**
     * Create an aliased <code>public.meaning_relation</code> table reference
     */
    public MeaningRelation(String alias) {
        this(DSL.name(alias), MEANING_RELATION);
    }

    /**
     * Create an aliased <code>public.meaning_relation</code> table reference
     */
    public MeaningRelation(Name alias) {
        this(alias, MEANING_RELATION);
    }

    private MeaningRelation(Name alias, Table<MeaningRelationRecord> aliased) {
        this(alias, aliased, null);
    }

    private MeaningRelation(Name alias, Table<MeaningRelationRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public <O extends Record> MeaningRelation(Table<O> child, ForeignKey<O, MeaningRelationRecord> key) {
        super(child, key, MEANING_RELATION);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.MEANING_RELATION_MEANING1_ID_IDX, Indexes.MEANING_RELATION_MEANING2_ID_IDX);
    }

    @Override
    public Identity<MeaningRelationRecord, Long> getIdentity() {
        return Keys.IDENTITY_MEANING_RELATION;
    }

    @Override
    public UniqueKey<MeaningRelationRecord> getPrimaryKey() {
        return Keys.MEANING_RELATION_PKEY;
    }

    @Override
    public List<UniqueKey<MeaningRelationRecord>> getKeys() {
        return Arrays.<UniqueKey<MeaningRelationRecord>>asList(Keys.MEANING_RELATION_PKEY, Keys.MEANING_RELATION_MEANING1_ID_MEANING2_ID_MEANING_REL_TYPE_C_KEY);
    }

    @Override
    public List<ForeignKey<MeaningRelationRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<MeaningRelationRecord, ?>>asList(Keys.MEANING_RELATION__MEANING_RELATION_MEANING1_ID_FKEY, Keys.MEANING_RELATION__MEANING_RELATION_MEANING2_ID_FKEY, Keys.MEANING_RELATION__MEANING_RELATION_MEANING_REL_TYPE_CODE_FKEY);
    }

    public Meaning meaningRelationMeaning1IdFkey() {
        return new Meaning(this, Keys.MEANING_RELATION__MEANING_RELATION_MEANING1_ID_FKEY);
    }

    public Meaning meaningRelationMeaning2IdFkey() {
        return new Meaning(this, Keys.MEANING_RELATION__MEANING_RELATION_MEANING2_ID_FKEY);
    }

    public MeaningRelType meaningRelType() {
        return new MeaningRelType(this, Keys.MEANING_RELATION__MEANING_RELATION_MEANING_REL_TYPE_CODE_FKEY);
    }

    @Override
    public MeaningRelation as(String alias) {
        return new MeaningRelation(DSL.name(alias), this);
    }

    @Override
    public MeaningRelation as(Name alias) {
        return new MeaningRelation(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public MeaningRelation rename(String name) {
        return new MeaningRelation(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public MeaningRelation rename(Name name) {
        return new MeaningRelation(name, null);
    }

    // -------------------------------------------------------------------------
    // Row6 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row6<Long, Long, Long, String, Long, BigDecimal> fieldsRow() {
        return (Row6) super.fieldsRow();
    }
}
