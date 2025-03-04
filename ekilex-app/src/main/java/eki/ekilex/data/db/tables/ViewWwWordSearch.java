/*
 * This file is generated by jOOQ.
 */
package eki.ekilex.data.db.tables;


import eki.ekilex.data.db.Public;
import eki.ekilex.data.db.tables.records.ViewWwWordSearchRecord;
import eki.ekilex.data.db.udt.records.TypeLangComplexityRecord;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row5;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ViewWwWordSearch extends TableImpl<ViewWwWordSearchRecord> {

    private static final long serialVersionUID = 1499775383;

    /**
     * The reference instance of <code>public.view_ww_word_search</code>
     */
    public static final ViewWwWordSearch VIEW_WW_WORD_SEARCH = new ViewWwWordSearch();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ViewWwWordSearchRecord> getRecordType() {
        return ViewWwWordSearchRecord.class;
    }

    /**
     * The column <code>public.view_ww_word_search.sgroup</code>.
     */
    public final TableField<ViewWwWordSearchRecord, String> SGROUP = createField(DSL.name("sgroup"), org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * The column <code>public.view_ww_word_search.word</code>.
     */
    public final TableField<ViewWwWordSearchRecord, String> WORD = createField(DSL.name("word"), org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * The column <code>public.view_ww_word_search.crit</code>.
     */
    public final TableField<ViewWwWordSearchRecord, String> CRIT = createField(DSL.name("crit"), org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * The column <code>public.view_ww_word_search.lang_order_by</code>.
     */
    public final TableField<ViewWwWordSearchRecord, Long> LANG_ORDER_BY = createField(DSL.name("lang_order_by"), org.jooq.impl.SQLDataType.BIGINT, this, "");

    /**
     * The column <code>public.view_ww_word_search.lang_complexities</code>.
     */
    public final TableField<ViewWwWordSearchRecord, TypeLangComplexityRecord[]> LANG_COMPLEXITIES = createField(DSL.name("lang_complexities"), eki.ekilex.data.db.udt.TypeLangComplexity.TYPE_LANG_COMPLEXITY.getDataType().getArrayDataType(), this, "");

    /**
     * Create a <code>public.view_ww_word_search</code> table reference
     */
    public ViewWwWordSearch() {
        this(DSL.name("view_ww_word_search"), null);
    }

    /**
     * Create an aliased <code>public.view_ww_word_search</code> table reference
     */
    public ViewWwWordSearch(String alias) {
        this(DSL.name(alias), VIEW_WW_WORD_SEARCH);
    }

    /**
     * Create an aliased <code>public.view_ww_word_search</code> table reference
     */
    public ViewWwWordSearch(Name alias) {
        this(alias, VIEW_WW_WORD_SEARCH);
    }

    private ViewWwWordSearch(Name alias, Table<ViewWwWordSearchRecord> aliased) {
        this(alias, aliased, null);
    }

    private ViewWwWordSearch(Name alias, Table<ViewWwWordSearchRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.view("create view \"view_ww_word_search\" as  SELECT ws.sgroup,\n    ws.word,\n    ws.crit,\n    ws.lang_order_by,\n    wlc.lang_complexities\n   FROM ( SELECT 'word'::text AS sgroup,\n            w.value AS word,\n            lower(w.value) AS crit,\n            (array_agg(wl.order_by ORDER BY wl.order_by))[1] AS lang_order_by\n           FROM word w,\n            language wl\n          WHERE ((w.lang = wl.code) AND (EXISTS ( SELECT w.id\n                   FROM lexeme l,\n                    dataset ds\n                  WHERE ((l.word_id = w.id) AND (l.is_public = true) AND ((ds.code)::text = (l.dataset_code)::text) AND (ds.is_public = true)))))\n          GROUP BY w.value\n        UNION ALL\n         SELECT 'as_word'::text AS sgroup,\n            w.value AS word,\n            lower(w.value_as_word) AS crit,\n            (array_agg(wl.order_by ORDER BY wl.order_by))[1] AS lang_order_by\n           FROM word w,\n            language wl\n          WHERE ((w.lang = wl.code) AND (w.value_as_word IS NOT NULL) AND (EXISTS ( SELECT w.id\n                   FROM lexeme l,\n                    dataset ds\n                  WHERE ((l.word_id = w.id) AND (l.is_public = true) AND ((ds.code)::text = (l.dataset_code)::text) AND (ds.is_public = true)))))\n          GROUP BY w.value, w.value_as_word\n        UNION ALL\n         SELECT 'form'::text AS sgroup,\n            w.value AS word,\n            lower(f.value) AS crit,\n            (array_agg(wl.order_by ORDER BY wl.order_by))[1] AS lang_order_by\n           FROM form f,\n            paradigm p,\n            word w,\n            language wl\n          WHERE ((f.paradigm_id = p.id) AND (p.word_id = w.id) AND (w.lang = wl.code) AND ((f.morph_code)::text <> '??'::text) AND (f.value <> w.value) AND (f.value <> '-'::text) AND (f.morph_exists = true) AND (f.is_questionable = false) AND (EXISTS ( SELECT w.id\n                   FROM lexeme l,\n                    dataset ds\n                  WHERE ((l.word_id = w.id) AND (l.is_public = true) AND ((ds.code)::text = (l.dataset_code)::text) AND (ds.is_public = true)))))\n          GROUP BY w.value, f.value) ws,\n    ( SELECT lc.word,\n            array_agg(DISTINCT ROW((\n                CASE\n                    WHEN (lc.lang = ANY (ARRAY['est'::bpchar, 'rus'::bpchar, 'eng'::bpchar, 'ukr'::bpchar, 'fra'::bpchar])) THEN lc.lang\n                    ELSE 'other'::bpchar\n                END)::character varying(10), lc.dataset_code, lc.lex_complexity, (rtrim((lc.data_complexity)::text, '12'::text))::character varying(100))::type_lang_complexity) AS lang_complexities\n           FROM ( SELECT w2.value AS word,\n                    w2.lang,\n                    l1.dataset_code,\n                    l1.complexity AS lex_complexity,\n                    l2.complexity AS data_complexity\n                   FROM ((((lexeme l1\n                     JOIN dataset l1ds ON (((l1ds.code)::text = (l1.dataset_code)::text)))\n                     JOIN lexeme l2 ON (((l2.meaning_id = l1.meaning_id) AND ((l2.dataset_code)::text = (l1.dataset_code)::text) AND (l2.word_id <> l1.word_id))))\n                     JOIN dataset l2ds ON (((l2ds.code)::text = (l2.dataset_code)::text)))\n                     JOIN word w2 ON ((w2.id = l2.word_id)))\n                  WHERE ((l1.is_public = true) AND (l1ds.is_public = true) AND (l2.is_public = true) AND (l2ds.is_public = true))\n                UNION ALL\n                 SELECT w.value AS word,\n                    COALESCE(ff.lang, w.lang) AS lang,\n                    l.dataset_code,\n                    l.complexity AS lex_complexity,\n                    ff.complexity AS data_complexity\n                   FROM word w,\n                    lexeme l,\n                    lexeme_freeform lff,\n                    freeform ff,\n                    dataset ds\n                  WHERE ((l.is_public = true) AND ((ds.code)::text = (l.dataset_code)::text) AND (ds.is_public = true) AND (l.word_id = w.id) AND (lff.lexeme_id = l.id) AND (lff.freeform_id = ff.id) AND (ff.is_public = true) AND ((ff.type)::text = ANY ((ARRAY['USAGE'::character varying, 'GRAMMAR'::character varying, 'GOVERNMENT'::character varying, 'NOTE'::character varying])::text[])))\n                UNION ALL\n                 SELECT w.value AS word,\n                    ut.lang,\n                    l.dataset_code,\n                    l.complexity AS lex_complexity,\n                    u.complexity AS data_complexity\n                   FROM word w,\n                    lexeme l,\n                    lexeme_freeform lff,\n                    freeform u,\n                    freeform ut,\n                    dataset ds\n                  WHERE ((l.is_public = true) AND (l.word_id = w.id) AND ((ds.code)::text = (l.dataset_code)::text) AND (ds.is_public = true) AND (lff.lexeme_id = l.id) AND (lff.freeform_id = u.id) AND ((u.type)::text = 'USAGE'::text) AND (u.is_public = true) AND (ut.parent_id = u.id) AND ((ut.type)::text = 'USAGE_TRANSLATION'::text))\n                UNION ALL\n                 SELECT w.value AS word,\n                    d.lang,\n                    l.dataset_code,\n                    l.complexity AS lex_complexity,\n                    d.complexity AS data_complexity\n                   FROM word w,\n                    lexeme l,\n                    definition d,\n                    dataset ds\n                  WHERE ((l.is_public = true) AND (l.word_id = w.id) AND ((ds.code)::text = (l.dataset_code)::text) AND (ds.is_public = true) AND (l.meaning_id = d.meaning_id) AND (d.is_public = true))) lc\n          GROUP BY lc.word) wlc\n  WHERE (ws.word = wlc.word)\n  ORDER BY ws.sgroup, ws.word, ws.crit;"));
    }

    public <O extends Record> ViewWwWordSearch(Table<O> child, ForeignKey<O, ViewWwWordSearchRecord> key) {
        super(child, key, VIEW_WW_WORD_SEARCH);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public ViewWwWordSearch as(String alias) {
        return new ViewWwWordSearch(DSL.name(alias), this);
    }

    @Override
    public ViewWwWordSearch as(Name alias) {
        return new ViewWwWordSearch(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public ViewWwWordSearch rename(String name) {
        return new ViewWwWordSearch(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public ViewWwWordSearch rename(Name name) {
        return new ViewWwWordSearch(name, null);
    }

    // -------------------------------------------------------------------------
    // Row5 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row5<String, String, String, Long, TypeLangComplexityRecord[]> fieldsRow() {
        return (Row5) super.fieldsRow();
    }
}
