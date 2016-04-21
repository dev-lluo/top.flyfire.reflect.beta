package top.flyfire.reflect.metainfo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by shyy_work on 2016/4/21.
 */
public class FieldMetaInfo {

    private final String fieldName;

    private final Field field;

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

    public FieldMetaInfo(String fieldName,Field field,Method setter, Method getter) {
        this.fieldName = fieldName;
        this.field = field;
        this.setter = setter;
        this.getter = getter;
    }
}
