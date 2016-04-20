package top.flyfire.reflect;

import java.io.ObjectStreamException;

/**
 * Created by flyfire[dev.lluo@outlook.com] on 2016/4/16.
 */
public class ReflectiveCache {

    private ReflectiveCache(){
        if(null!=ReflectiveCacheHolder.INSTANCE){
            throw new IllegalStateException("ReflectiveCache instance already created.");
        }
    }

    private static class ReflectiveCacheHolder {
        private static ReflectiveCache INSTANCE = new ReflectiveCache();
    }

    public static ReflectiveCache getInstance(){
        return ReflectiveCacheHolder.INSTANCE;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    private Object readResolve() throws ObjectStreamException {
        return ReflectiveCacheHolder.INSTANCE;
    }
}
