/*
 * This file is generated by jOOQ.
*/
package eki.wordweb.data.db.udt;


import eki.wordweb.data.db.Public;
import eki.wordweb.data.db.udt.records.TypeDefinitionRecord;

import javax.annotation.Generated;

import org.jooq.Schema;
import org.jooq.UDTField;
import org.jooq.impl.UDTImpl;


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
public class TypeDefinition extends UDTImpl<TypeDefinitionRecord> {

    private static final long serialVersionUID = 1608011936;

    /**
     * The reference instance of <code>public.type_definition</code>
     */
    public static final TypeDefinition TYPE_DEFINITION = new TypeDefinition();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TypeDefinitionRecord> getRecordType() {
        return TypeDefinitionRecord.class;
    }

    /**
     * The attribute <code>public.type_definition.value</code>.
     */
    public static final UDTField<TypeDefinitionRecord, String> VALUE = createField("value", org.jooq.impl.SQLDataType.CLOB, TYPE_DEFINITION, "");

    /**
     * The attribute <code>public.type_definition.lang</code>.
     */
    public static final UDTField<TypeDefinitionRecord, String> LANG = createField("lang", org.jooq.impl.SQLDataType.CHAR(3), TYPE_DEFINITION, "");

    /**
     * No further instances allowed
     */
    private TypeDefinition() {
        super("type_definition", null, null, false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }
}
