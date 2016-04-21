package top.flyfire.reflect.metainfo;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by flyfire[dev.lluo@outlook.com] on 2016/4/20.
 */
public class TypeMetaInfo {

    private Class<?> rawType;

    private Type[] innerType;

    public TypeMetaInfo(Class<?> rawType, Type[] innerType) {
        this.rawType = rawType;
        this.innerType = innerType;
    }

    public Class<?> getRawType() {
        return rawType;
    }

    public Type[] getInnerType() {
        return innerType;
    }
}
