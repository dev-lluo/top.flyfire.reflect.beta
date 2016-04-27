package top.flyfire.reflect;

import java.lang.reflect.Type;

/**
 * Created by shyy_work on 2016/4/24.
 */
public abstract class $Type implements Type {

    public static final Type[] NONE = new Type[0];

    protected String typeName;

    protected abstract String buildTypeName();

    @Override
    public final String getTypeName() {
        return this.toString();
    }

    @Override
    public final String toString() {
        return this.typeName == null?this.typeName=this.buildTypeName():this.typeName;
    }

}
