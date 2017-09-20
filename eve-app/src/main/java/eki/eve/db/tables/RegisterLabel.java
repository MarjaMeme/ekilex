/*
 * This file is generated by jOOQ.
*/
package eki.eve.db.tables;


import eki.eve.db.Keys;
import eki.eve.db.Public;
import eki.eve.db.tables.records.RegisterLabelRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.5"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class RegisterLabel extends TableImpl<RegisterLabelRecord> {

    private static final long serialVersionUID = 1146574960;

    /**
     * The reference instance of <code>public.register_label</code>
     */
    public static final RegisterLabel REGISTER_LABEL = new RegisterLabel();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<RegisterLabelRecord> getRecordType() {
        return RegisterLabelRecord.class;
    }

    /**
     * The column <code>public.register_label.code</code>.
     */
    public final TableField<RegisterLabelRecord, String> CODE = createField("code", org.jooq.impl.SQLDataType.VARCHAR.length(100).nullable(false), this, "");

    /**
     * The column <code>public.register_label.value</code>.
     */
    public final TableField<RegisterLabelRecord, String> VALUE = createField("value", org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>public.register_label.lang</code>.
     */
    public final TableField<RegisterLabelRecord, String> LANG = createField("lang", org.jooq.impl.SQLDataType.CHAR.length(3).nullable(false), this, "");

    /**
     * The column <code>public.register_label.type</code>.
     */
    public final TableField<RegisterLabelRecord, String> TYPE = createField("type", org.jooq.impl.SQLDataType.VARCHAR.length(10).nullable(false), this, "");

    /**
     * Create a <code>public.register_label</code> table reference
     */
    public RegisterLabel() {
        this("register_label", null);
    }

    /**
     * Create an aliased <code>public.register_label</code> table reference
     */
    public RegisterLabel(String alias) {
        this(alias, REGISTER_LABEL);
    }

    private RegisterLabel(String alias, Table<RegisterLabelRecord> aliased) {
        this(alias, aliased, null);
    }

    private RegisterLabel(String alias, Table<RegisterLabelRecord> aliased, Field<?>[] parameters) {
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
    public List<UniqueKey<RegisterLabelRecord>> getKeys() {
        return Arrays.<UniqueKey<RegisterLabelRecord>>asList(Keys.REGISTER_LABEL_CODE_LANG_TYPE_KEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<RegisterLabelRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<RegisterLabelRecord, ?>>asList(Keys.REGISTER_LABEL__REGISTER_LABEL_CODE_FKEY, Keys.REGISTER_LABEL__REGISTER_LABEL_LANG_FKEY, Keys.REGISTER_LABEL__REGISTER_LABEL_TYPE_FKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegisterLabel as(String alias) {
        return new RegisterLabel(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public RegisterLabel rename(String name) {
        return new RegisterLabel(name, null);
    }
}
