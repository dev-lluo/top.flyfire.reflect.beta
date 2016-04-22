package top.flyfire.reflect.metainfo;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by shyy_work on 2016/4/22.
 */
public class ParameterizedTypeMetaInfo implements ParameterizedType {

    private final Type[] actualTypeArguments;

    @Override
    public Type[] getActualTypeArguments() {
        return this.actualTypeArguments;
    }

    private final Type rawType;

    @Override
    public Type getRawType() {
        return this.rawType;
    }

    private final Type ownerType ;

    @Override
    public Type getOwnerType() {
        return this.ownerType;
    }

    public ParameterizedTypeMetaInfo(Type rawType, Type ownerType, Type[] actualTypeArguments) {
        this.actualTypeArguments = actualTypeArguments;
        this.rawType = rawType;
        this.ownerType = ownerType;
    }

    @Override
    public String toString() {
        if(this.actualTypeArguments==null||this.actualTypeArguments.length==0){
            return this.rawType.getTypeName();
        }else{
            String toString = this.rawType.getTypeName();
            toString+='<';
            toString+=this.actualTypeArguments[0].getTypeName();
            for(int i =1;i<this.actualTypeArguments.length;i++){
                toString+=',';
                toString+=this.actualTypeArguments[i].getTypeName();
            }
            toString+='>';
            return toString;
        }
    }
}
