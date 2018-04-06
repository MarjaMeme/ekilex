/*
 * This file is generated by jOOQ.
*/
package eki.ekilex.data.db.tables;


import eki.ekilex.data.db.Indexes;
import eki.ekilex.data.db.Keys;
import eki.ekilex.data.db.Public;
import eki.ekilex.data.db.tables.records.WordRelTypeLabelRecord;

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
public class WordRelTypeLabel extends TableImpl<WordRelTypeLabelRecord> {

    private static final long serialVersionUID = -416435749;

    /**
     * The reference instance of <code>public.word_rel_type_label</code>
     */
    public static final WordRelTypeLabel WORD_REL_TYPE_LABEL = new WordRelTypeLabel();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<WordRelTypeLabelRecord> getRecordType() {
        return WordRelTypeLabelRecord.class;
    }

    /**
     * The column <code>public.word_rel_type_label.code</code>.
     */
    public final TableField<WordRelTypeLabelRecord, String> CODE = createField("code", org.jooq.impl.SQLDataType.VARCHAR(100).nullable(false), this, "");

    /**
     * The column <code>public.word_rel_type_label.value</code>.
     */
    public final TableField<WordRelTypeLabelRecord, String> VALUE = createField("value", org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>public.word_rel_type_label.lang</code>.
     */
    public final TableField<WordRelTypeLabelRecord, String> LANG = createField("lang", org.jooq.impl.SQLDataType.CHAR(3).nullable(false), this, "");

    /**
     * The column <code>public.word_rel_type_label.type</code>.
     */
    public final TableField<WordRelTypeLabelRecord, String> TYPE = createField("type", org.jooq.impl.SQLDataType.VARCHAR(10).nullable(false), this, "");

    /**
     * Create a <code>public.word_rel_type_label</code> table reference
     */
    public WordRelTypeLabel() {
        this(DSL.name("word_rel_type_label"), null);
    }

    /**
     * Create an aliased <code>public.word_rel_type_label</code> table reference
     */
    public WordRelTypeLabel(String alias) {
        this(DSL.name(alias), WORD_REL_TYPE_LABEL);
    }

    /**
     * Create an aliased <code>public.word_rel_type_label</code> table reference
     */
    public WordRelTypeLabel(Name alias) {
        this(alias, WORD_REL_TYPE_LABEL);
    }

    private WordRelTypeLabel(Name alias, Table<WordRelTypeLabelRecord> aliased) {
        this(alias, aliased, null);
    }

    private WordRelTypeLabel(Name alias, Table<WordRelTypeLabelRecord> aliased, Field<?>[] parameters) {
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
        return Arrays.<Index>asList(Indexes.WORD_REL_TYPE_LABEL_CODE_LANG_TYPE_KEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<WordRelTypeLabelRecord>> getKeys() {
        return Arrays.<UniqueKey<WordRelTypeLabelRecord>>asList(Keys.WORD_REL_TYPE_LABEL_CODE_LANG_TYPE_KEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<WordRelTypeLabelRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<WordRelTypeLabelRecord, ?>>asList(Keys.WORD_REL_TYPE_LABEL__WORD_REL_TYPE_LABEL_CODE_FKEY, Keys.WORD_REL_TYPE_LABEL__WORD_REL_TYPE_LABEL_LANG_FKEY, Keys.WORD_REL_TYPE_LABEL__WORD_REL_TYPE_LABEL_TYPE_FKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WordRelTypeLabel as(String alias) {
        return new WordRelTypeLabel(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WordRelTypeLabel as(Name alias) {
        return new WordRelTypeLabel(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public WordRelTypeLabel rename(String name) {
        return new WordRelTypeLabel(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public WordRelTypeLabel rename(Name name) {
        return new WordRelTypeLabel(name, null);
    }
}
