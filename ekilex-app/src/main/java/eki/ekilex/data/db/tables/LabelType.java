/*
 * This file is generated by jOOQ.
*/
package eki.ekilex.data.db.tables;


import eki.ekilex.data.db.Indexes;
import eki.ekilex.data.db.Keys;
import eki.ekilex.data.db.Public;
import eki.ekilex.data.db.tables.records.LabelTypeRecord;

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
public class LabelType extends TableImpl<LabelTypeRecord> {

    private static final long serialVersionUID = 1847074404;

    /**
     * The reference instance of <code>public.label_type</code>
     */
    public static final LabelType LABEL_TYPE = new LabelType();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<LabelTypeRecord> getRecordType() {
        return LabelTypeRecord.class;
    }

    /**
     * The column <code>public.label_type.code</code>.
     */
    public final TableField<LabelTypeRecord, String> CODE = createField("code", org.jooq.impl.SQLDataType.VARCHAR(10).nullable(false), this, "");

    /**
     * The column <code>public.label_type.value</code>.
     */
    public final TableField<LabelTypeRecord, String> VALUE = createField("value", org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

    /**
     * Create a <code>public.label_type</code> table reference
     */
    public LabelType() {
        this(DSL.name("label_type"), null);
    }

    /**
     * Create an aliased <code>public.label_type</code> table reference
     */
    public LabelType(String alias) {
        this(DSL.name(alias), LABEL_TYPE);
    }

    /**
     * Create an aliased <code>public.label_type</code> table reference
     */
    public LabelType(Name alias) {
        this(alias, LABEL_TYPE);
    }

    private LabelType(Name alias, Table<LabelTypeRecord> aliased) {
        this(alias, aliased, null);
    }

    private LabelType(Name alias, Table<LabelTypeRecord> aliased, Field<?>[] parameters) {
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
        return Arrays.<Index>asList(Indexes.LABEL_TYPE_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<LabelTypeRecord> getPrimaryKey() {
        return Keys.LABEL_TYPE_PKEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<LabelTypeRecord>> getKeys() {
        return Arrays.<UniqueKey<LabelTypeRecord>>asList(Keys.LABEL_TYPE_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelType as(String alias) {
        return new LabelType(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelType as(Name alias) {
        return new LabelType(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public LabelType rename(String name) {
        return new LabelType(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public LabelType rename(Name name) {
        return new LabelType(name, null);
    }
}
