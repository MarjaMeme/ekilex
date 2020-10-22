/*
 * This file is generated by jOOQ.
 */
package eki.ekilex.data.db.tables;


import eki.ekilex.data.db.Public;
import eki.ekilex.data.db.tables.records.ViewWwFormRecord;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row19;
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
public class ViewWwForm extends TableImpl<ViewWwFormRecord> {

    private static final long serialVersionUID = -529487379;

    /**
     * The reference instance of <code>public.view_ww_form</code>
     */
    public static final ViewWwForm VIEW_WW_FORM = new ViewWwForm();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ViewWwFormRecord> getRecordType() {
        return ViewWwFormRecord.class;
    }

    /**
     * The column <code>public.view_ww_form.word_id</code>.
     */
    public final TableField<ViewWwFormRecord, Long> WORD_ID = createField(DSL.name("word_id"), org.jooq.impl.SQLDataType.BIGINT, this, "");

    /**
     * The column <code>public.view_ww_form.word</code>.
     */
    public final TableField<ViewWwFormRecord, String> WORD = createField(DSL.name("word"), org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * The column <code>public.view_ww_form.lang</code>.
     */
    public final TableField<ViewWwFormRecord, String> LANG = createField(DSL.name("lang"), org.jooq.impl.SQLDataType.CHAR(3), this, "");

    /**
     * The column <code>public.view_ww_form.paradigm_id</code>.
     */
    public final TableField<ViewWwFormRecord, Long> PARADIGM_ID = createField(DSL.name("paradigm_id"), org.jooq.impl.SQLDataType.BIGINT, this, "");

    /**
     * The column <code>public.view_ww_form.inflection_type</code>.
     */
    public final TableField<ViewWwFormRecord, String> INFLECTION_TYPE = createField(DSL.name("inflection_type"), org.jooq.impl.SQLDataType.VARCHAR(100), this, "");

    /**
     * The column <code>public.view_ww_form.form_id</code>.
     */
    public final TableField<ViewWwFormRecord, Long> FORM_ID = createField(DSL.name("form_id"), org.jooq.impl.SQLDataType.BIGINT, this, "");

    /**
     * The column <code>public.view_ww_form.mode</code>.
     */
    public final TableField<ViewWwFormRecord, String> MODE = createField(DSL.name("mode"), org.jooq.impl.SQLDataType.VARCHAR(100), this, "");

    /**
     * The column <code>public.view_ww_form.morph_group1</code>.
     */
    public final TableField<ViewWwFormRecord, String> MORPH_GROUP1 = createField(DSL.name("morph_group1"), org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * The column <code>public.view_ww_form.morph_group2</code>.
     */
    public final TableField<ViewWwFormRecord, String> MORPH_GROUP2 = createField(DSL.name("morph_group2"), org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * The column <code>public.view_ww_form.morph_group3</code>.
     */
    public final TableField<ViewWwFormRecord, String> MORPH_GROUP3 = createField(DSL.name("morph_group3"), org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * The column <code>public.view_ww_form.display_level</code>.
     */
    public final TableField<ViewWwFormRecord, Integer> DISPLAY_LEVEL = createField(DSL.name("display_level"), org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>public.view_ww_form.morph_code</code>.
     */
    public final TableField<ViewWwFormRecord, String> MORPH_CODE = createField(DSL.name("morph_code"), org.jooq.impl.SQLDataType.VARCHAR(100), this, "");

    /**
     * The column <code>public.view_ww_form.morph_exists</code>.
     */
    public final TableField<ViewWwFormRecord, Boolean> MORPH_EXISTS = createField(DSL.name("morph_exists"), org.jooq.impl.SQLDataType.BOOLEAN, this, "");

    /**
     * The column <code>public.view_ww_form.form</code>.
     */
    public final TableField<ViewWwFormRecord, String> FORM = createField(DSL.name("form"), org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * The column <code>public.view_ww_form.components</code>.
     */
    public final TableField<ViewWwFormRecord, String[]> COMPONENTS = createField(DSL.name("components"), org.jooq.impl.SQLDataType.VARCHAR(100).getArrayDataType(), this, "");

    /**
     * The column <code>public.view_ww_form.display_form</code>.
     */
    public final TableField<ViewWwFormRecord, String> DISPLAY_FORM = createField(DSL.name("display_form"), org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>public.view_ww_form.vocal_form</code>.
     */
    public final TableField<ViewWwFormRecord, String> VOCAL_FORM = createField(DSL.name("vocal_form"), org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>public.view_ww_form.audio_file</code>.
     */
    public final TableField<ViewWwFormRecord, String> AUDIO_FILE = createField(DSL.name("audio_file"), org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>public.view_ww_form.order_by</code>.
     */
    public final TableField<ViewWwFormRecord, Long> ORDER_BY = createField(DSL.name("order_by"), org.jooq.impl.SQLDataType.BIGINT, this, "");

    /**
     * Create a <code>public.view_ww_form</code> table reference
     */
    public ViewWwForm() {
        this(DSL.name("view_ww_form"), null);
    }

    /**
     * Create an aliased <code>public.view_ww_form</code> table reference
     */
    public ViewWwForm(String alias) {
        this(DSL.name(alias), VIEW_WW_FORM);
    }

    /**
     * Create an aliased <code>public.view_ww_form</code> table reference
     */
    public ViewWwForm(Name alias) {
        this(alias, VIEW_WW_FORM);
    }

    private ViewWwForm(Name alias, Table<ViewWwFormRecord> aliased) {
        this(alias, aliased, null);
    }

    private ViewWwForm(Name alias, Table<ViewWwFormRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.view("create view \"view_ww_form\" as  SELECT w.id AS word_id,\n    w.value AS word,\n    w.lang,\n    p.id AS paradigm_id,\n    p.inflection_type,\n    f.id AS form_id,\n    f.mode,\n    f.morph_group1,\n    f.morph_group2,\n    f.morph_group3,\n    f.display_level,\n    f.morph_code,\n    f.morph_exists,\n    f.value AS form,\n    f.components,\n    f.display_form,\n    f.vocal_form,\n    f.audio_file,\n    f.order_by\n   FROM word w,\n    paradigm p,\n    form f\n  WHERE ((p.word_id = w.id) AND (f.paradigm_id = p.id) AND (f.display_level > 0) AND (EXISTS ( SELECT l.id\n           FROM lexeme l,\n            dataset ds\n          WHERE ((l.word_id = w.id) AND ((l.type)::text = 'PRIMARY'::text) AND (l.is_public = true) AND ((ds.code)::text = (l.dataset_code)::text) AND (ds.is_public = true)))))\n  ORDER BY p.id, f.order_by, f.id;"));
    }

    public <O extends Record> ViewWwForm(Table<O> child, ForeignKey<O, ViewWwFormRecord> key) {
        super(child, key, VIEW_WW_FORM);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public ViewWwForm as(String alias) {
        return new ViewWwForm(DSL.name(alias), this);
    }

    @Override
    public ViewWwForm as(Name alias) {
        return new ViewWwForm(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public ViewWwForm rename(String name) {
        return new ViewWwForm(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public ViewWwForm rename(Name name) {
        return new ViewWwForm(name, null);
    }

    // -------------------------------------------------------------------------
    // Row19 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row19<Long, String, String, Long, String, Long, String, String, String, String, Integer, String, Boolean, String, String[], String, String, String, Long> fieldsRow() {
        return (Row19) super.fieldsRow();
    }
}
