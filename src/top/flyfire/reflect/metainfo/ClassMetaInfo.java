package top.flyfire.reflect.metainfo;

import java.lang.reflect.Type;

/**
 * Created by shyy_work on 2016/4/21.
 */
public class ClassMetaInfo {
    private Type rawType;

    public Type getRawType() {
        return rawType;
    }

    public void setRawType(Type rawType) {
        this.rawType = rawType;
    }

    private Type[] innerType;

    public void initInnerType(int size){
        this.innerType = new Type[size];
    }

    public Type[] getInnerType(){
        return this.innerType;
    }

    public void setInnerType(int i,Type type){
        this.innerType[i] = null==type?Object.class:type;
    }
}
