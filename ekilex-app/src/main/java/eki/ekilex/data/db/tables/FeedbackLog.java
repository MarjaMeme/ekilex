/*
 * This file is generated by jOOQ.
 */
package eki.ekilex.data.db.tables;


import eki.ekilex.data.db.Keys;
import eki.ekilex.data.db.Public;
import eki.ekilex.data.db.tables.records.FeedbackLogRecord;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row15;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class FeedbackLog extends TableImpl<FeedbackLogRecord> {

    private static final long serialVersionUID = -403831471;

    /**
     * The reference instance of <code>public.feedback_log</code>
     */
    public static final FeedbackLog FEEDBACK_LOG = new FeedbackLog();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<FeedbackLogRecord> getRecordType() {
        return FeedbackLogRecord.class;
    }

    /**
     * The column <code>public.feedback_log.id</code>.
     */
    public final TableField<FeedbackLogRecord, Long> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('feedback_log_id_seq'::regclass)", org.jooq.impl.SQLDataType.BIGINT)), this, "");

    /**
     * The column <code>public.feedback_log.feedback_type</code>.
     */
    public final TableField<FeedbackLogRecord, String> FEEDBACK_TYPE = createField(DSL.name("feedback_type"), org.jooq.impl.SQLDataType.VARCHAR(100).nullable(false), this, "");

    /**
     * The column <code>public.feedback_log.sender_name</code>.
     */
    public final TableField<FeedbackLogRecord, String> SENDER_NAME = createField(DSL.name("sender_name"), org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>public.feedback_log.sender_email</code>.
     */
    public final TableField<FeedbackLogRecord, String> SENDER_EMAIL = createField(DSL.name("sender_email"), org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>public.feedback_log.created_on</code>.
     */
    public final TableField<FeedbackLogRecord, Timestamp> CREATED_ON = createField(DSL.name("created_on"), org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaultValue(org.jooq.impl.DSL.field("statement_timestamp()", org.jooq.impl.SQLDataType.TIMESTAMP)), this, "");

    /**
     * The column <code>public.feedback_log.word</code>.
     */
    public final TableField<FeedbackLogRecord, String> WORD = createField(DSL.name("word"), org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * The column <code>public.feedback_log.definition</code>.
     */
    public final TableField<FeedbackLogRecord, String> DEFINITION = createField(DSL.name("definition"), org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * The column <code>public.feedback_log.comments</code>.
     */
    public final TableField<FeedbackLogRecord, String> COMMENTS = createField(DSL.name("comments"), org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * The column <code>public.feedback_log.usage</code>.
     */
    public final TableField<FeedbackLogRecord, String> USAGE = createField(DSL.name("usage"), org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * The column <code>public.feedback_log.other_info</code>.
     */
    public final TableField<FeedbackLogRecord, String> OTHER_INFO = createField(DSL.name("other_info"), org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * The column <code>public.feedback_log.last_search</code>.
     */
    public final TableField<FeedbackLogRecord, String> LAST_SEARCH = createField(DSL.name("last_search"), org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * The column <code>public.feedback_log.description</code>.
     */
    public final TableField<FeedbackLogRecord, String> DESCRIPTION = createField(DSL.name("description"), org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * The column <code>public.feedback_log.definition_source</code>.
     */
    public final TableField<FeedbackLogRecord, String> DEFINITION_SOURCE = createField(DSL.name("definition_source"), org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * The column <code>public.feedback_log.usage_source</code>.
     */
    public final TableField<FeedbackLogRecord, String> USAGE_SOURCE = createField(DSL.name("usage_source"), org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * The column <code>public.feedback_log.company</code>.
     */
    public final TableField<FeedbackLogRecord, String> COMPANY = createField(DSL.name("company"), org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * Create a <code>public.feedback_log</code> table reference
     */
    public FeedbackLog() {
        this(DSL.name("feedback_log"), null);
    }

    /**
     * Create an aliased <code>public.feedback_log</code> table reference
     */
    public FeedbackLog(String alias) {
        this(DSL.name(alias), FEEDBACK_LOG);
    }

    /**
     * Create an aliased <code>public.feedback_log</code> table reference
     */
    public FeedbackLog(Name alias) {
        this(alias, FEEDBACK_LOG);
    }

    private FeedbackLog(Name alias, Table<FeedbackLogRecord> aliased) {
        this(alias, aliased, null);
    }

    private FeedbackLog(Name alias, Table<FeedbackLogRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public <O extends Record> FeedbackLog(Table<O> child, ForeignKey<O, FeedbackLogRecord> key) {
        super(child, key, FEEDBACK_LOG);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public Identity<FeedbackLogRecord, Long> getIdentity() {
        return Keys.IDENTITY_FEEDBACK_LOG;
    }

    @Override
    public UniqueKey<FeedbackLogRecord> getPrimaryKey() {
        return Keys.FEEDBACK_LOG_PKEY;
    }

    @Override
    public List<UniqueKey<FeedbackLogRecord>> getKeys() {
        return Arrays.<UniqueKey<FeedbackLogRecord>>asList(Keys.FEEDBACK_LOG_PKEY);
    }

    @Override
    public FeedbackLog as(String alias) {
        return new FeedbackLog(DSL.name(alias), this);
    }

    @Override
    public FeedbackLog as(Name alias) {
        return new FeedbackLog(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public FeedbackLog rename(String name) {
        return new FeedbackLog(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public FeedbackLog rename(Name name) {
        return new FeedbackLog(name, null);
    }

    // -------------------------------------------------------------------------
    // Row15 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row15<Long, String, String, String, Timestamp, String, String, String, String, String, String, String, String, String, String> fieldsRow() {
        return (Row15) super.fieldsRow();
    }
}
