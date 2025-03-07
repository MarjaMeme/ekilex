/*
 * This file is generated by jOOQ.
 */
package eki.ekilex.data.db.udt;


import eki.ekilex.data.db.Public;
import eki.ekilex.data.db.udt.records.TypeValueNameLangRecord;

import org.jooq.Schema;
import org.jooq.UDTField;
import org.jooq.impl.DSL;
import org.jooq.impl.SchemaImpl;
import org.jooq.impl.UDTImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TypeValueNameLang extends UDTImpl<TypeValueNameLangRecord> {

    private static final long serialVersionUID = -1292574063;

    /**
     * The reference instance of <code>public.type_value_name_lang</code>
     */
    public static final TypeValueNameLang TYPE_VALUE_NAME_LANG = new TypeValueNameLang();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TypeValueNameLangRecord> getRecordType() {
        return TypeValueNameLangRecord.class;
    }

    /**
     * The attribute <code>public.type_value_name_lang.value_id</code>.
     */
    public static final UDTField<TypeValueNameLangRecord, Long> VALUE_ID = createField(DSL.name("value_id"), org.jooq.impl.SQLDataType.BIGINT, TYPE_VALUE_NAME_LANG, "");

    /**
     * The attribute <code>public.type_value_name_lang.value</code>.
     */
    public static final UDTField<TypeValueNameLangRecord, String> VALUE = createField(DSL.name("value"), org.jooq.impl.SQLDataType.CLOB, TYPE_VALUE_NAME_LANG, "");

    /**
     * The attribute <code>public.type_value_name_lang.name</code>.
     */
    public static final UDTField<TypeValueNameLangRecord, String> NAME = createField(DSL.name("name"), org.jooq.impl.SQLDataType.CLOB, TYPE_VALUE_NAME_LANG, "");

    /**
     * The attribute <code>public.type_value_name_lang.lang</code>.
     */
    public static final UDTField<TypeValueNameLangRecord, String> LANG = createField(DSL.name("lang"), org.jooq.impl.SQLDataType.CHAR(3), TYPE_VALUE_NAME_LANG, "");

    /**
     * No further instances allowed
     */
    private TypeValueNameLang() {
        super("type_value_name_lang", null, null, false);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC != null ? Public.PUBLIC : new SchemaImpl(DSL.name("public"));
    }
}
