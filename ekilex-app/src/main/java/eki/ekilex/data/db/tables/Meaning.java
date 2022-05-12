/*
 * This file is generated by jOOQ.
 */
package eki.ekilex.data.db.tables;


import eki.ekilex.data.db.Indexes;
import eki.ekilex.data.db.Keys;
import eki.ekilex.data.db.Public;
import eki.ekilex.data.db.tables.records.MeaningRecord;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row2;
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
public class Meaning extends TableImpl<MeaningRecord> {

    private static final long serialVersionUID = 1691912138;

    /**
     * The reference instance of <code>public.meaning</code>
     */
    public static final Meaning MEANING = new Meaning();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<MeaningRecord> getRecordType() {
        return MeaningRecord.class;
    }

    /**
     * The column <code>public.meaning.id</code>.
     */
    public final TableField<MeaningRecord, Long> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('meaning_id_seq'::regclass)", org.jooq.impl.SQLDataType.BIGINT)), this, "");

    /**
     * The column <code>public.meaning.manual_event_on</code>.
     */
    public final TableField<MeaningRecord, Timestamp> MANUAL_EVENT_ON = createField(DSL.name("manual_event_on"), org.jooq.impl.SQLDataType.TIMESTAMP, this, "");

    /**
     * Create a <code>public.meaning</code> table reference
     */
    public Meaning() {
        this(DSL.name("meaning"), null);
    }

    /**
     * Create an aliased <code>public.meaning</code> table reference
     */
    public Meaning(String alias) {
        this(DSL.name(alias), MEANING);
    }

    /**
     * Create an aliased <code>public.meaning</code> table reference
     */
    public Meaning(Name alias) {
        this(alias, MEANING);
    }

    private Meaning(Name alias, Table<MeaningRecord> aliased) {
        this(alias, aliased, null);
    }

    private Meaning(Name alias, Table<MeaningRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public <O extends Record> Meaning(Table<O> child, ForeignKey<O, MeaningRecord> key) {
        super(child, key, MEANING);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.MEANING_MANUAL_EVENT_ON_IDX);
    }

    @Override
    public Identity<MeaningRecord, Long> getIdentity() {
        return Keys.IDENTITY_MEANING;
    }

    @Override
    public UniqueKey<MeaningRecord> getPrimaryKey() {
        return Keys.MEANING_PKEY;
    }

    @Override
    public List<UniqueKey<MeaningRecord>> getKeys() {
        return Arrays.<UniqueKey<MeaningRecord>>asList(Keys.MEANING_PKEY);
    }

    @Override
    public Meaning as(String alias) {
        return new Meaning(DSL.name(alias), this);
    }

    @Override
    public Meaning as(Name alias) {
        return new Meaning(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Meaning rename(String name) {
        return new Meaning(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Meaning rename(Name name) {
        return new Meaning(name, null);
    }

    // -------------------------------------------------------------------------
    // Row2 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row2<Long, Timestamp> fieldsRow() {
        return (Row2) super.fieldsRow();
    }
}
