package top.flyfire.reflect.metainfo;

import top.flyfire.reflect.ReflectiveException;

import java.lang.reflect.Constructor;

/**
 * Created by shyy_work on 2016/4/28.
 */
public abstract class ConstructorMetaInfo {

    protected final Constructor constructor;

    public ConstructorMetaInfo(){
        try{
            this.constructor = this.buildConstructor();
        }catch(ReflectiveOperationException e){
            throw new ReflectiveException(e);
        }
    }

    public abstract Constructor buildConstructor() throws ReflectiveOperationException;

    public Object $new() throws ReflectiveOperationException{
        return this.constructor.newInstance();
    }

}
