package top.flyfire.reflect;

import top.flyfire.reflect.metainfo.ClassMetaInfo;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
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
        this.cache.put(String.class,ClassMetaInfo.$String);
        this.cache.put(Number.class,ClassMetaInfo.$Number);
        this.cache.put(Integer.class,ClassMetaInfo.$Integer);
        this.cache.put(Float.class,ClassMetaInfo.$Float);
        this.cache.put(Double.class,ClassMetaInfo.$Double);
        this.cache.put(Boolean.class,ClassMetaInfo.$Boolean);
        this.cache.put(Byte.class,ClassMetaInfo.$Byte);
        this.cache.put(Short.class,ClassMetaInfo.$Short);
        this.cache.put(Long.class,ClassMetaInfo.$Long);
        this.cache.put(java.util.Date.class,ClassMetaInfo.$Date);
        this.cache.put(java.sql.Date.class,ClassMetaInfo.$SqlDate);
        this.cache.put(Timestamp.class,ClassMetaInfo.$Timestamp);
        this.cache.put(BigDecimal.class,ClassMetaInfo.$BigDecimal);
        this.cache.put(BigInteger.class,ClassMetaInfo.$BigInteger);
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
