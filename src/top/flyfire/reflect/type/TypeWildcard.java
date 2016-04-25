package top.flyfire.reflect.type;

import top.flyfire.reflect.$Type;
import top.flyfire.reflect.ReflectiveWrapper;

import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;

/**
 * Created by shyy_work on 2016/4/24.
 */
public class TypeWildcard extends $Type implements WildcardType {

    public static final int DEFAULT = 0;

    private final Type[] upperBounds;

    private final Type[] lowerBounds;

    @Override
    public Type[] getUpperBounds() {
        return this.upperBounds;
    }

    @Override
    public Type[] getLowerBounds() {
        return this.lowerBounds;
    }

    @Override
    protected String buildTypeName() {
        if(this.lowerBounds.length>0){
            return "? super " + this.lowerBounds[DEFAULT].getTypeName();
        }else if(this.upperBounds.length==0){
            return "?";
        }else{
            return "? extends " + this.upperBounds[DEFAULT].getTypeName();
        }
    }

    public TypeWildcard(Type[] upperBounds, Type[] lowerBounds) {
        this.upperBounds = upperBounds==null?NONE: ReflectiveWrapper.unWrapper(upperBounds);
        this.lowerBounds = lowerBounds==null?NONE: ReflectiveWrapper.unWrapper(lowerBounds);
    }
}
