/*
 * This file is generated by jOOQ.
*/
package eki.ekilex.data.db.tables;


import eki.ekilex.data.db.Indexes;
import eki.ekilex.data.db.Keys;
import eki.ekilex.data.db.Public;
import eki.ekilex.data.db.tables.records.UsageTypeRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Index;
import org.jooq.Name;
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
        "jOOQ version:3.10.6"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class UsageType extends TableImpl<UsageTypeRecord> {

    private static final long serialVersionUID = -324290022;

    /**
     * The reference instance of <code>public.usage_type</code>
     */
    public static final UsageType USAGE_TYPE = new UsageType();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<UsageTypeRecord> getRecordType() {
        return UsageTypeRecord.class;
    }

    /**
     * The column <code>public.usage_type.code</code>.
     */
    public final TableField<UsageTypeRecord, String> CODE = createField("code", org.jooq.impl.SQLDataType.VARCHAR(100).nullable(false), this, "");

    /**
     * The column <code>public.usage_type.datasets</code>.
     */
    public final TableField<UsageTypeRecord, String[]> DATASETS = createField("datasets", org.jooq.impl.SQLDataType.VARCHAR.getArrayDataType(), this, "");

    /**
     * Create a <code>public.usage_type</code> table reference
     */
    public UsageType() {
        this(DSL.name("usage_type"), null);
    }

    /**
     * Create an aliased <code>public.usage_type</code> table reference
     */
    public UsageType(String alias) {
        this(DSL.name(alias), USAGE_TYPE);
    }

    /**
     * Create an aliased <code>public.usage_type</code> table reference
     */
    public UsageType(Name alias) {
        this(alias, USAGE_TYPE);
    }

    private UsageType(Name alias, Table<UsageTypeRecord> aliased) {
        this(alias, aliased, null);
    }

    private UsageType(Name alias, Table<UsageTypeRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
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
        return Arrays.<Index>asList(Indexes.USAGE_TYPE_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<UsageTypeRecord> getPrimaryKey() {
        return Keys.USAGE_TYPE_PKEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<UsageTypeRecord>> getKeys() {
        return Arrays.<UniqueKey<UsageTypeRecord>>asList(Keys.USAGE_TYPE_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UsageType as(String alias) {
        return new UsageType(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UsageType as(Name alias) {
        return new UsageType(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public UsageType rename(String name) {
        return new UsageType(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public UsageType rename(Name name) {
        return new UsageType(name, null);
    }
}
