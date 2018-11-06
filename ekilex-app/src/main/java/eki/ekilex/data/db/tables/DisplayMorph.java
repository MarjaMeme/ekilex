/*
 * This file is generated by jOOQ.
 */
package eki.ekilex.data.db.tables;


import eki.ekilex.data.db.Indexes;
import eki.ekilex.data.db.Keys;
import eki.ekilex.data.db.Public;
import eki.ekilex.data.db.tables.records.DisplayMorphRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.5"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DisplayMorph extends TableImpl<DisplayMorphRecord> {

    private static final long serialVersionUID = 1685573055;

    /**
     * The reference instance of <code>public.display_morph</code>
     */
    public static final DisplayMorph DISPLAY_MORPH = new DisplayMorph();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<DisplayMorphRecord> getRecordType() {
        return DisplayMorphRecord.class;
    }

    /**
     * The column <code>public.display_morph.code</code>.
     */
    public final TableField<DisplayMorphRecord, String> CODE = createField("code", org.jooq.impl.SQLDataType.VARCHAR(100).nullable(false), this, "");

    /**
     * The column <code>public.display_morph.datasets</code>.
     */
    public final TableField<DisplayMorphRecord, String[]> DATASETS = createField("datasets", org.jooq.impl.SQLDataType.VARCHAR.getArrayDataType(), this, "");

    /**
     * The column <code>public.display_morph.order_by</code>.
     */
    public final TableField<DisplayMorphRecord, Long> ORDER_BY = createField("order_by", org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('display_morph_order_by_seq'::regclass)", org.jooq.impl.SQLDataType.BIGINT)), this, "");

    /**
     * Create a <code>public.display_morph</code> table reference
     */
    public DisplayMorph() {
        this(DSL.name("display_morph"), null);
    }

    /**
     * Create an aliased <code>public.display_morph</code> table reference
     */
    public DisplayMorph(String alias) {
        this(DSL.name(alias), DISPLAY_MORPH);
    }

    /**
     * Create an aliased <code>public.display_morph</code> table reference
     */
    public DisplayMorph(Name alias) {
        this(alias, DISPLAY_MORPH);
    }

    private DisplayMorph(Name alias, Table<DisplayMorphRecord> aliased) {
        this(alias, aliased, null);
    }

    private DisplayMorph(Name alias, Table<DisplayMorphRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> DisplayMorph(Table<O> child, ForeignKey<O, DisplayMorphRecord> key) {
        super(child, key, DISPLAY_MORPH);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.DISPLAY_MORPH_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<DisplayMorphRecord, Long> getIdentity() {
        return Keys.IDENTITY_DISPLAY_MORPH;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<DisplayMorphRecord> getPrimaryKey() {
        return Keys.DISPLAY_MORPH_PKEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<DisplayMorphRecord>> getKeys() {
        return Arrays.<UniqueKey<DisplayMorphRecord>>asList(Keys.DISPLAY_MORPH_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DisplayMorph as(String alias) {
        return new DisplayMorph(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DisplayMorph as(Name alias) {
        return new DisplayMorph(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public DisplayMorph rename(String name) {
        return new DisplayMorph(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public DisplayMorph rename(Name name) {
        return new DisplayMorph(name, null);
    }
}
