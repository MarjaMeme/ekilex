/*
 * This file is generated by jOOQ.
*/
package eki.wordweb.data.db.tables;


import eki.wordweb.data.db.Indexes;
import eki.wordweb.data.db.Public;
import eki.wordweb.data.db.tables.records.MviewWwLexemeRecord;
import eki.wordweb.data.db.udt.records.TypeUsageRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
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
public class MviewWwLexeme extends TableImpl<MviewWwLexemeRecord> {

    private static final long serialVersionUID = 1937298179;

    /**
     * The reference instance of <code>public.mview_ww_lexeme</code>
     */
    public static final MviewWwLexeme MVIEW_WW_LEXEME = new MviewWwLexeme();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<MviewWwLexemeRecord> getRecordType() {
        return MviewWwLexemeRecord.class;
    }

    /**
     * The column <code>public.mview_ww_lexeme.lexeme_id</code>.
     */
    public final TableField<MviewWwLexemeRecord, Long> LEXEME_ID = createField("lexeme_id", org.jooq.impl.SQLDataType.BIGINT, this, "");

    /**
     * The column <code>public.mview_ww_lexeme.word_id</code>.
     */
    public final TableField<MviewWwLexemeRecord, Long> WORD_ID = createField("word_id", org.jooq.impl.SQLDataType.BIGINT, this, "");

    /**
     * The column <code>public.mview_ww_lexeme.meaning_id</code>.
     */
    public final TableField<MviewWwLexemeRecord, Long> MEANING_ID = createField("meaning_id", org.jooq.impl.SQLDataType.BIGINT, this, "");

    /**
     * The column <code>public.mview_ww_lexeme.advice_notes</code>.
     */
    public final TableField<MviewWwLexemeRecord, String[]> ADVICE_NOTES = createField("advice_notes", org.jooq.impl.SQLDataType.CLOB.getArrayDataType(), this, "");

    /**
     * The column <code>public.mview_ww_lexeme.public_notes</code>.
     */
    public final TableField<MviewWwLexemeRecord, String[]> PUBLIC_NOTES = createField("public_notes", org.jooq.impl.SQLDataType.CLOB.getArrayDataType(), this, "");

    /**
     * The column <code>public.mview_ww_lexeme.grammars</code>.
     */
    public final TableField<MviewWwLexemeRecord, String[]> GRAMMARS = createField("grammars", org.jooq.impl.SQLDataType.CLOB.getArrayDataType(), this, "");

    /**
     * The column <code>public.mview_ww_lexeme.government_id</code>.
     */
    public final TableField<MviewWwLexemeRecord, Long> GOVERNMENT_ID = createField("government_id", org.jooq.impl.SQLDataType.BIGINT, this, "");

    /**
     * The column <code>public.mview_ww_lexeme.government</code>.
     */
    public final TableField<MviewWwLexemeRecord, String> GOVERNMENT = createField("government", org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * The column <code>public.mview_ww_lexeme.usage_meaning_id</code>.
     */
    public final TableField<MviewWwLexemeRecord, Long> USAGE_MEANING_ID = createField("usage_meaning_id", org.jooq.impl.SQLDataType.BIGINT, this, "");

    /**
     * The column <code>public.mview_ww_lexeme.usage_meaning_type_code</code>.
     */
    public final TableField<MviewWwLexemeRecord, String> USAGE_MEANING_TYPE_CODE = createField("usage_meaning_type_code", org.jooq.impl.SQLDataType.VARCHAR(100), this, "");

    /**
     * The column <code>public.mview_ww_lexeme.usages</code>.
     */
    public final TableField<MviewWwLexemeRecord, TypeUsageRecord[]> USAGES = createField("usages", eki.wordweb.data.db.udt.TypeUsage.TYPE_USAGE.getDataType().getArrayDataType(), this, "");

    /**
     * The column <code>public.mview_ww_lexeme.usage_translations</code>.
     */
    public final TableField<MviewWwLexemeRecord, String[]> USAGE_TRANSLATIONS = createField("usage_translations", org.jooq.impl.SQLDataType.CLOB.getArrayDataType(), this, "");

    /**
     * The column <code>public.mview_ww_lexeme.usage_definitions</code>.
     */
    public final TableField<MviewWwLexemeRecord, String[]> USAGE_DEFINITIONS = createField("usage_definitions", org.jooq.impl.SQLDataType.CLOB.getArrayDataType(), this, "");

    /**
     * Create a <code>public.mview_ww_lexeme</code> table reference
     */
    public MviewWwLexeme() {
        this(DSL.name("mview_ww_lexeme"), null);
    }

    /**
     * Create an aliased <code>public.mview_ww_lexeme</code> table reference
     */
    public MviewWwLexeme(String alias) {
        this(DSL.name(alias), MVIEW_WW_LEXEME);
    }

    /**
     * Create an aliased <code>public.mview_ww_lexeme</code> table reference
     */
    public MviewWwLexeme(Name alias) {
        this(alias, MVIEW_WW_LEXEME);
    }

    private MviewWwLexeme(Name alias, Table<MviewWwLexemeRecord> aliased) {
        this(alias, aliased, null);
    }

    private MviewWwLexeme(Name alias, Table<MviewWwLexemeRecord> aliased, Field<?>[] parameters) {
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
        return Arrays.<Index>asList(Indexes.MVIEW_WW_LEXEME_LEXEME_ID_IDX, Indexes.MVIEW_WW_LEXEME_MEANING_ID_IDX, Indexes.MVIEW_WW_LEXEME_WORD_ID_IDX);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MviewWwLexeme as(String alias) {
        return new MviewWwLexeme(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MviewWwLexeme as(Name alias) {
        return new MviewWwLexeme(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public MviewWwLexeme rename(String name) {
        return new MviewWwLexeme(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public MviewWwLexeme rename(Name name) {
        return new MviewWwLexeme(name, null);
    }
}
