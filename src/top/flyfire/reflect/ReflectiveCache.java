package top.flyfire.reflect;

import top.flyfire.reflect.metainfo.ClassMetaInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by flyfire[dev.lluo@outlook.com] on 2016/4/16.
 */
public enum ReflectiveCache {
    INSTANCE;
    private Map<Class<?>,ClassMetaInfo> cache;
    private ReflectiveCache(){
        this.cache = new HashMap<Class<?>,ClassMetaInfo>();
    }

    public ClassMetaInfo get(Class<?> clzz){
        ClassMetaInfo classMetaInfo;
        if(null==(classMetaInfo = this.cache.get(clzz))) {
            synchronized (this.cache){
                if(null==(classMetaInfo = this.cache.get(clzz))) {
                    this.cache.put(clzz,ReflectiveWrapper.unWrapperClass(clzz));
                }
            }
        }
        return classMetaInfo;
    }


}
