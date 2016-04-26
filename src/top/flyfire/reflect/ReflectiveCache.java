package top.flyfire.reflect;

import top.flyfire.reflect.metainfo.ClassMetaInfo;

import java.util.*;

/**
 * Created by flyfire[dev.lluo@outlook.com] on 2016/4/16.
 */
public enum ReflectiveCache {
    INSTANCE;
    private Map<Class<?>,ClassMetaInfo> cache;
    private ReflectiveCache(){
        this.cache = new HashMap<Class<?>,ClassMetaInfo>();
        this.cache.put(Object.class,ClassMetaInfo.$Object);
        this.cache.put(Map.class,ClassMetaInfo.$Map);
        this.cache.put(Collection.class,ClassMetaInfo.$Collection);
        this.cache.put(List.class,ClassMetaInfo.$List);
        this.cache.put(Set.class,ClassMetaInfo.$Set);
    }

    public ClassMetaInfo get(Class<?> clzz){
        ClassMetaInfo classMetaInfo;
        if(null==(classMetaInfo = this.cache.get(clzz))) {
            synchronized (this.cache){
                if(null==(classMetaInfo = this.cache.get(clzz))) {
                    this.cache.put(clzz,classMetaInfo = ReflectiveWrapper.unWrapperClass(clzz));
                }
            }
        }
        return classMetaInfo;
    }


}
