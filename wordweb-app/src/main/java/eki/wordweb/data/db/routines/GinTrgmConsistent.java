/*
 * This file is generated by jOOQ.
 */
package eki.wordweb.data.db.routines;


import eki.wordweb.data.db.Public;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Parameter;
import org.jooq.impl.AbstractRoutine;
import org.jooq.impl.Internal;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.9"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class GinTrgmConsistent extends AbstractRoutine<Boolean> {

    private static final long serialVersionUID = 945511664;

    /**
     * The parameter <code>public.gin_trgm_consistent.RETURN_VALUE</code>.
     */
    public static final Parameter<Boolean> RETURN_VALUE = Internal.createParameter("RETURN_VALUE", org.jooq.impl.SQLDataType.BOOLEAN, false, false);

    /**
     * @deprecated Unknown data type. Please define an explicit {@link org.jooq.Binding} to specify how this type should be handled. Deprecation can be turned off using {@literal <deprecationOnUnknownTypes/>} in your code generator configuration.
     */
    @java.lang.Deprecated
    public static final Parameter<Object> _1 = Internal.createParameter("_1", org.jooq.impl.DefaultDataType.getDefaultDataType("\"pg_catalog\".\"internal\""), false, true);

    /**
     * The parameter <code>public.gin_trgm_consistent._2</code>.
     */
    public static final Parameter<Short> _2 = Internal.createParameter("_2", org.jooq.impl.SQLDataType.SMALLINT, false, true);

    /**
     * The parameter <code>public.gin_trgm_consistent._3</code>.
     */
    public static final Parameter<String> _3 = Internal.createParameter("_3", org.jooq.impl.SQLDataType.CLOB, false, true);

    /**
     * The parameter <code>public.gin_trgm_consistent._4</code>.
     */
    public static final Parameter<Integer> _4 = Internal.createParameter("_4", org.jooq.impl.SQLDataType.INTEGER, false, true);

    /**
     * @deprecated Unknown data type. Please define an explicit {@link org.jooq.Binding} to specify how this type should be handled. Deprecation can be turned off using {@literal <deprecationOnUnknownTypes/>} in your code generator configuration.
     */
    @java.lang.Deprecated
    public static final Parameter<Object> _5 = Internal.createParameter("_5", org.jooq.impl.DefaultDataType.getDefaultDataType("\"pg_catalog\".\"internal\""), false, true);

    /**
     * @deprecated Unknown data type. Please define an explicit {@link org.jooq.Binding} to specify how this type should be handled. Deprecation can be turned off using {@literal <deprecationOnUnknownTypes/>} in your code generator configuration.
     */
    @java.lang.Deprecated
    public static final Parameter<Object> _6 = Internal.createParameter("_6", org.jooq.impl.DefaultDataType.getDefaultDataType("\"pg_catalog\".\"internal\""), false, true);

    /**
     * @deprecated Unknown data type. Please define an explicit {@link org.jooq.Binding} to specify how this type should be handled. Deprecation can be turned off using {@literal <deprecationOnUnknownTypes/>} in your code generator configuration.
     */
    @java.lang.Deprecated
    public static final Parameter<Object> _7 = Internal.createParameter("_7", org.jooq.impl.DefaultDataType.getDefaultDataType("\"pg_catalog\".\"internal\""), false, true);

    /**
     * @deprecated Unknown data type. Please define an explicit {@link org.jooq.Binding} to specify how this type should be handled. Deprecation can be turned off using {@literal <deprecationOnUnknownTypes/>} in your code generator configuration.
     */
    @java.lang.Deprecated
    public static final Parameter<Object> _8 = Internal.createParameter("_8", org.jooq.impl.DefaultDataType.getDefaultDataType("\"pg_catalog\".\"internal\""), false, true);

    /**
     * Create a new routine call instance
     */
    public GinTrgmConsistent() {
        super("gin_trgm_consistent", Public.PUBLIC, org.jooq.impl.SQLDataType.BOOLEAN);

        setReturnParameter(RETURN_VALUE);
        addInParameter(_1);
        addInParameter(_2);
        addInParameter(_3);
        addInParameter(_4);
        addInParameter(_5);
        addInParameter(_6);
        addInParameter(_7);
        addInParameter(_8);
    }

    /**
     * Set the <code>_1</code> parameter IN value to the routine
     */
    public void set__1(Object value) {
        setValue(_1, value);
    }

    /**
     * Set the <code>_1</code> parameter to the function to be used with a {@link org.jooq.Select} statement
     */
    public void set__1(Field<Object> field) {
        setField(_1, field);
    }

    /**
     * Set the <code>_2</code> parameter IN value to the routine
     */
    public void set__2(Short value) {
        setValue(_2, value);
    }

    /**
     * Set the <code>_2</code> parameter to the function to be used with a {@link org.jooq.Select} statement
     */
    public void set__2(Field<Short> field) {
        setField(_2, field);
    }

    /**
     * Set the <code>_3</code> parameter IN value to the routine
     */
    public void set__3(String value) {
        setValue(_3, value);
    }

    /**
     * Set the <code>_3</code> parameter to the function to be used with a {@link org.jooq.Select} statement
     */
    public void set__3(Field<String> field) {
        setField(_3, field);
    }

    /**
     * Set the <code>_4</code> parameter IN value to the routine
     */
    public void set__4(Integer value) {
        setValue(_4, value);
    }

    /**
     * Set the <code>_4</code> parameter to the function to be used with a {@link org.jooq.Select} statement
     */
    public void set__4(Field<Integer> field) {
        setField(_4, field);
    }

    /**
     * Set the <code>_5</code> parameter IN value to the routine
     */
    public void set__5(Object value) {
        setValue(_5, value);
    }

    /**
     * Set the <code>_5</code> parameter to the function to be used with a {@link org.jooq.Select} statement
     */
    public void set__5(Field<Object> field) {
        setField(_5, field);
    }

    /**
     * Set the <code>_6</code> parameter IN value to the routine
     */
    public void set__6(Object value) {
        setValue(_6, value);
    }

    /**
     * Set the <code>_6</code> parameter to the function to be used with a {@link org.jooq.Select} statement
     */
    public void set__6(Field<Object> field) {
        setField(_6, field);
    }

    /**
     * Set the <code>_7</code> parameter IN value to the routine
     */
    public void set__7(Object value) {
        setValue(_7, value);
    }

    /**
     * Set the <code>_7</code> parameter to the function to be used with a {@link org.jooq.Select} statement
     */
    public void set__7(Field<Object> field) {
        setField(_7, field);
    }

    /**
     * Set the <code>_8</code> parameter IN value to the routine
     */
    public void set__8(Object value) {
        setValue(_8, value);
    }

    /**
     * Set the <code>_8</code> parameter to the function to be used with a {@link org.jooq.Select} statement
     */
    public void set__8(Field<Object> field) {
        setField(_8, field);
    }
}
