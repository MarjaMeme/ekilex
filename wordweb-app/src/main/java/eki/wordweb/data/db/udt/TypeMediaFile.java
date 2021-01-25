/*
 * This file is generated by jOOQ.
 */
package eki.wordweb.data.db.udt;


import eki.wordweb.data.db.Public;
import eki.wordweb.data.db.udt.records.TypeMediaFileRecord;

import org.jooq.Schema;
import org.jooq.UDTField;
import org.jooq.impl.DSL;
import org.jooq.impl.SchemaImpl;
import org.jooq.impl.UDTImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TypeMediaFile extends UDTImpl<TypeMediaFileRecord> {

    private static final long serialVersionUID = 3951923;

    /**
     * The reference instance of <code>public.type_media_file</code>
     */
    public static final TypeMediaFile TYPE_MEDIA_FILE = new TypeMediaFile();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TypeMediaFileRecord> getRecordType() {
        return TypeMediaFileRecord.class;
    }

    /**
     * The attribute <code>public.type_media_file.freeform_id</code>.
     */
    public static final UDTField<TypeMediaFileRecord, Long> FREEFORM_ID = createField(DSL.name("freeform_id"), org.jooq.impl.SQLDataType.BIGINT, TYPE_MEDIA_FILE, "");

    /**
     * The attribute <code>public.type_media_file.source_url</code>.
     */
    public static final UDTField<TypeMediaFileRecord, String> SOURCE_URL = createField(DSL.name("source_url"), org.jooq.impl.SQLDataType.CLOB, TYPE_MEDIA_FILE, "");

    /**
     * The attribute <code>public.type_media_file.title</code>.
     */
    public static final UDTField<TypeMediaFileRecord, String> TITLE = createField(DSL.name("title"), org.jooq.impl.SQLDataType.CLOB, TYPE_MEDIA_FILE, "");

    /**
     * The attribute <code>public.type_media_file.complexity</code>.
     */
    public static final UDTField<TypeMediaFileRecord, String> COMPLEXITY = createField(DSL.name("complexity"), org.jooq.impl.SQLDataType.VARCHAR(100), TYPE_MEDIA_FILE, "");

    /**
     * No further instances allowed
     */
    private TypeMediaFile() {
        super("type_media_file", null, null, false);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC != null ? Public.PUBLIC : new SchemaImpl(DSL.name("public"));
    }
}
