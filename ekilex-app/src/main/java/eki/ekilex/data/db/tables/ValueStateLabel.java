/*
 * This file is generated by jOOQ.
*/
package eki.ekilex.data.db.tables;


import eki.ekilex.data.db.Indexes;
import eki.ekilex.data.db.Keys;
import eki.ekilex.data.db.Public;
import eki.ekilex.data.db.tables.records.ValueStateLabelRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
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
public class ValueStateLabel extends TableImpl<ValueStateLabelRecord> {

    private static final long serialVersionUID = -857123333;

    /**
     * The reference instance of <code>public.value_state_label</code>
     */
    public static final ValueStateLabel VALUE_STATE_LABEL = new ValueStateLabel();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ValueStateLabelRecord> getRecordType() {
        return ValueStateLabelRecord.class;
    }

    /**
     * The column <code>public.value_state_label.code</code>.
     */
    public final TableField<ValueStateLabelRecord, String> CODE = createField("code", org.jooq.impl.SQLDataType.VARCHAR(100).nullable(false), this, "");

    /**
     * The column <code>public.value_state_label.value</code>.
     */
    public final TableField<ValueStateLabelRecord, String> VALUE = createField("value", org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>public.value_state_label.lang</code>.
     */
    public final TableField<ValueStateLabelRecord, String> LANG = createField("lang", org.jooq.impl.SQLDataType.CHAR(3).nullable(false), this, "");

    /**
     * The column <code>public.value_state_label.type</code>.
     */
    public final TableField<ValueStateLabelRecord, String> TYPE = createField("type", org.jooq.impl.SQLDataType.VARCHAR(10).nullable(false), this, "");

    /**
     * Create a <code>public.value_state_label</code> table reference
     */
    public ValueStateLabel() {
        this(DSL.name("value_state_label"), null);
    }

    /**
     * Create an aliased <code>public.value_state_label</code> table reference
     */
    public ValueStateLabel(String alias) {
        this(DSL.name(alias), VALUE_STATE_LABEL);
    }

    /**
     * Create an aliased <code>public.value_state_label</code> table reference
     */
    public ValueStateLabel(Name alias) {
        this(alias, VALUE_STATE_LABEL);
    }

    private ValueStateLabel(Name alias, Table<ValueStateLabelRecord> aliased) {
        this(alias, aliased, null);
    }

    private ValueStateLabel(Name alias, Table<ValueStateLabelRecord> aliased, Field<?>[] parameters) {
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
        return Arrays.<Index>asList(Indexes.VALUE_STATE_LABEL_CODE_LANG_TYPE_KEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<ValueStateLabelRecord>> getKeys() {
        return Arrays.<UniqueKey<ValueStateLabelRecord>>asList(Keys.VALUE_STATE_LABEL_CODE_LANG_TYPE_KEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<ValueStateLabelRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<ValueStateLabelRecord, ?>>asList(Keys.VALUE_STATE_LABEL__VALUE_STATE_LABEL_CODE_FKEY, Keys.VALUE_STATE_LABEL__VALUE_STATE_LABEL_LANG_FKEY, Keys.VALUE_STATE_LABEL__VALUE_STATE_LABEL_TYPE_FKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ValueStateLabel as(String alias) {
        return new ValueStateLabel(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ValueStateLabel as(Name alias) {
        return new ValueStateLabel(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public ValueStateLabel rename(String name) {
        return new ValueStateLabel(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public ValueStateLabel rename(Name name) {
        return new ValueStateLabel(name, null);
    }
}
