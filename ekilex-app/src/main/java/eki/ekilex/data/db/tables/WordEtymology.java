/*
 * This file is generated by jOOQ.
*/
package eki.ekilex.data.db.tables;


import eki.ekilex.data.db.Indexes;
import eki.ekilex.data.db.Keys;
import eki.ekilex.data.db.Public;
import eki.ekilex.data.db.tables.records.WordEtymologyRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
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
        "jOOQ version:3.10.7"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class WordEtymology extends TableImpl<WordEtymologyRecord> {

    private static final long serialVersionUID = 1861554726;

    /**
     * The reference instance of <code>public.word_etymology</code>
     */
    public static final WordEtymology WORD_ETYMOLOGY = new WordEtymology();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<WordEtymologyRecord> getRecordType() {
        return WordEtymologyRecord.class;
    }

    /**
     * The column <code>public.word_etymology.id</code>.
     */
    public final TableField<WordEtymologyRecord, Long> ID = createField("id", org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('word_etymology_id_seq'::regclass)", org.jooq.impl.SQLDataType.BIGINT)), this, "");

    /**
     * The column <code>public.word_etymology.word1_id</code>.
     */
    public final TableField<WordEtymologyRecord, Long> WORD1_ID = createField("word1_id", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.word_etymology.word2_id</code>.
     */
    public final TableField<WordEtymologyRecord, Long> WORD2_ID = createField("word2_id", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.word_etymology.etymology_type_code</code>.
     */
    public final TableField<WordEtymologyRecord, String> ETYMOLOGY_TYPE_CODE = createField("etymology_type_code", org.jooq.impl.SQLDataType.VARCHAR(100), this, "");

    /**
     * The column <code>public.word_etymology.comments</code>.
     */
    public final TableField<WordEtymologyRecord, String[]> COMMENTS = createField("comments", org.jooq.impl.SQLDataType.CLOB.getArrayDataType(), this, "");

    /**
     * The column <code>public.word_etymology.is_questionable</code>.
     */
    public final TableField<WordEtymologyRecord, Boolean> IS_QUESTIONABLE = createField("is_questionable", org.jooq.impl.SQLDataType.BOOLEAN.nullable(false).defaultValue(org.jooq.impl.DSL.field("false", org.jooq.impl.SQLDataType.BOOLEAN)), this, "");

    /**
     * The column <code>public.word_etymology.is_compound</code>.
     */
    public final TableField<WordEtymologyRecord, Boolean> IS_COMPOUND = createField("is_compound", org.jooq.impl.SQLDataType.BOOLEAN.nullable(false).defaultValue(org.jooq.impl.DSL.field("false", org.jooq.impl.SQLDataType.BOOLEAN)), this, "");

    /**
     * Create a <code>public.word_etymology</code> table reference
     */
    public WordEtymology() {
        this(DSL.name("word_etymology"), null);
    }

    /**
     * Create an aliased <code>public.word_etymology</code> table reference
     */
    public WordEtymology(String alias) {
        this(DSL.name(alias), WORD_ETYMOLOGY);
    }

    /**
     * Create an aliased <code>public.word_etymology</code> table reference
     */
    public WordEtymology(Name alias) {
        this(alias, WORD_ETYMOLOGY);
    }

    private WordEtymology(Name alias, Table<WordEtymologyRecord> aliased) {
        this(alias, aliased, null);
    }

    private WordEtymology(Name alias, Table<WordEtymologyRecord> aliased, Field<?>[] parameters) {
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
        return Arrays.<Index>asList(Indexes.WORD_ETYMOLOGY_PKEY, Indexes.WORD_ETYMOLOGY_WORD1_ID_WORD2_ID_KEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<WordEtymologyRecord, Long> getIdentity() {
        return Keys.IDENTITY_WORD_ETYMOLOGY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<WordEtymologyRecord> getPrimaryKey() {
        return Keys.WORD_ETYMOLOGY_PKEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<WordEtymologyRecord>> getKeys() {
        return Arrays.<UniqueKey<WordEtymologyRecord>>asList(Keys.WORD_ETYMOLOGY_PKEY, Keys.WORD_ETYMOLOGY_WORD1_ID_WORD2_ID_KEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<WordEtymologyRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<WordEtymologyRecord, ?>>asList(Keys.WORD_ETYMOLOGY__WORD_ETYMOLOGY_WORD1_ID_FKEY, Keys.WORD_ETYMOLOGY__WORD_ETYMOLOGY_WORD2_ID_FKEY, Keys.WORD_ETYMOLOGY__WORD_ETYMOLOGY_ETYMOLOGY_TYPE_CODE_FKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WordEtymology as(String alias) {
        return new WordEtymology(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WordEtymology as(Name alias) {
        return new WordEtymology(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public WordEtymology rename(String name) {
        return new WordEtymology(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public WordEtymology rename(Name name) {
        return new WordEtymology(name, null);
    }
}
