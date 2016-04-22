package top.flyfire.reflect;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by shyy_work on 2016/4/22.
 */
public abstract class RawType<T> {

    private static final int INS = 0;

    public final Type getType(){
        Type type = this.getClass().getGenericSuperclass();
        if(type instanceof ParameterizedType){
            return ((ParameterizedType)type).getActualTypeArguments()[INS];
        }
        throw new IllegalArgumentException("type arguments is missing.");
    }

}
