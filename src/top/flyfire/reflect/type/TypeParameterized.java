package top.flyfire.reflect.type;

import top.flyfire.reflect.$Type;
import top.flyfire.reflect.ReflectiveWrapper;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by shyy_work on 2016/4/22.
 */
public class TypeParameterized extends $Type implements ParameterizedType {

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

    public TypeParameterized(Type rawType, Type ownerType, Type[] actualTypeArguments) {
        this.actualTypeArguments = ReflectiveWrapper.unWrapper(actualTypeArguments);
        this.rawType = ReflectiveWrapper.unWrapper(rawType);
        this.ownerType = ReflectiveWrapper.unWrapper(ownerType);
    }

    @Override
    protected String buildTypeName() {
        if(this.actualTypeArguments==null||this.actualTypeArguments.length==0){
            return this.rawType.getTypeName();
        }else{
            StringBuilder toString = new StringBuilder(this.rawType.getTypeName());
            toString.append('<').append(this.actualTypeArguments[0].getTypeName());
            for(int i =1;i<this.actualTypeArguments.length;i++){
                toString.append(',').append(this.actualTypeArguments[i].getTypeName());
            }
            toString.append('>');
            return toString.toString();
        }
    }
}
