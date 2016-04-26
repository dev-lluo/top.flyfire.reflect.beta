package top.flyfire.reflect.metainfo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * Created by shyy_work on 2016/4/21.
 */
public class FieldMetaInfo {

    private final String fieldName;

    private final Field field;

    private final Type type;

    private final Method setter;

    private final Method getter;


    public Method getGetter() {
        return getter;
    }

    public Method getSetter() {
        return setter;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Field getField(){
        return field;
    }

    public Type getType(){
        return type;
    }

    public FieldMetaInfo(String fieldName,Field field,Type type, Method getter ,Method setter) {
        this.fieldName = fieldName;
        this.field = field;
        this.type = type;
        this.getter = getter;
        this.setter = setter;

    }

    @Override
    public String toString() {
        return this.type.getTypeName()+" "+this.field.getDeclaringClass().getTypeName()+"."+this.fieldName;
    }
}
