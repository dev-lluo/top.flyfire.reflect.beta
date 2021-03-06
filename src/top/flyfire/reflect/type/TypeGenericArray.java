package top.flyfire.reflect.type;

import top.flyfire.reflect.$Type;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;

/**
 * Created by shyy_work on 2016/4/24.
 */
public class TypeGenericArray extends $Type implements GenericArrayType {

    private final Type genericComponentType;

    @Override
    public Type getGenericComponentType() {
        return this.genericComponentType;
    }

    @Override
    protected String buildTypeName() {
        return this.genericComponentType.getTypeName()+"[]";
    }

    public TypeGenericArray(Type genericComponentType) {
        this.genericComponentType = genericComponentType;
    }
}
