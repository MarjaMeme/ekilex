/*
 * This file is generated by jOOQ.
*/
package eki.ekilex.data.db.tables;


import eki.ekilex.data.db.Indexes;
import eki.ekilex.data.db.Keys;
import eki.ekilex.data.db.Public;
import eki.ekilex.data.db.tables.records.WordTypeLabelRecord;

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
public class WordTypeLabel extends TableImpl<WordTypeLabelRecord> {

    private static final long serialVersionUID = -728506397;

    /**
     * The reference instance of <code>public.word_type_label</code>
     */
    public static final WordTypeLabel WORD_TYPE_LABEL = new WordTypeLabel();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<WordTypeLabelRecord> getRecordType() {
        return WordTypeLabelRecord.class;
    }

    /**
     * The column <code>public.word_type_label.code</code>.
     */
    public final TableField<WordTypeLabelRecord, String> CODE = createField("code", org.jooq.impl.SQLDataType.VARCHAR(100).nullable(false), this, "");

    /**
     * The column <code>public.word_type_label.value</code>.
     */
    public final TableField<WordTypeLabelRecord, String> VALUE = createField("value", org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>public.word_type_label.lang</code>.
     */
    public final TableField<WordTypeLabelRecord, String> LANG = createField("lang", org.jooq.impl.SQLDataType.CHAR(3).nullable(false), this, "");

    /**
     * The column <code>public.word_type_label.type</code>.
     */
    public final TableField<WordTypeLabelRecord, String> TYPE = createField("type", org.jooq.impl.SQLDataType.VARCHAR(10).nullable(false), this, "");

    /**
     * Create a <code>public.word_type_label</code> table reference
     */
    public WordTypeLabel() {
        this(DSL.name("word_type_label"), null);
    }

    /**
     * Create an aliased <code>public.word_type_label</code> table reference
     */
    public WordTypeLabel(String alias) {
        this(DSL.name(alias), WORD_TYPE_LABEL);
    }

    /**
     * Create an aliased <code>public.word_type_label</code> table reference
     */
    public WordTypeLabel(Name alias) {
        this(alias, WORD_TYPE_LABEL);
    }

    private WordTypeLabel(Name alias, Table<WordTypeLabelRecord> aliased) {
        this(alias, aliased, null);
    }

    private WordTypeLabel(Name alias, Table<WordTypeLabelRecord> aliased, Field<?>[] parameters) {
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
        return Arrays.<Index>asList(Indexes.WORD_TYPE_LABEL_CODE_LANG_TYPE_KEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<WordTypeLabelRecord>> getKeys() {
        return Arrays.<UniqueKey<WordTypeLabelRecord>>asList(Keys.WORD_TYPE_LABEL_CODE_LANG_TYPE_KEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<WordTypeLabelRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<WordTypeLabelRecord, ?>>asList(Keys.WORD_TYPE_LABEL__WORD_TYPE_LABEL_CODE_FKEY, Keys.WORD_TYPE_LABEL__WORD_TYPE_LABEL_LANG_FKEY, Keys.WORD_TYPE_LABEL__WORD_TYPE_LABEL_TYPE_FKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WordTypeLabel as(String alias) {
        return new WordTypeLabel(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WordTypeLabel as(Name alias) {
        return new WordTypeLabel(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public WordTypeLabel rename(String name) {
        return new WordTypeLabel(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public WordTypeLabel rename(Name name) {
        return new WordTypeLabel(name, null);
    }
}
