package top.flyfire.reflect;

import top.flyfire.reflect.metainfo.ClassMetaInfo;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by flyfire[dev.lluo@outlook.com] on 2016/4/16.
 */
public enum ReflectiveCache {
    INSTANCE;
    private Map<Class<?>,ClassMetaInfo> classCache;
    private ReflectiveCache(){
        ClassCache:
        {
            this.classCache = new HashMap<>(1<<8);
            this.classCache.put(Object.class, ClassMetaInfo.$Object);
            this.classCache.put(Map.class, ClassMetaInfo.$Map);
            this.classCache.put(Collection.class, ClassMetaInfo.$Collection);
            this.classCache.put(List.class, ClassMetaInfo.$List);
            this.classCache.put(Set.class, ClassMetaInfo.$Set);
            this.classCache.put(String.class, ClassMetaInfo.$String);
            this.classCache.put(Number.class, ClassMetaInfo.$Number);
            this.classCache.put(Integer.class, ClassMetaInfo.$Integer);
            this.classCache.put(Float.class, ClassMetaInfo.$Float);
            this.classCache.put(Double.class, ClassMetaInfo.$Double);
            this.classCache.put(Boolean.class, ClassMetaInfo.$Boolean);
            this.classCache.put(Byte.class, ClassMetaInfo.$Byte);
            this.classCache.put(Short.class, ClassMetaInfo.$Short);
            this.classCache.put(Long.class, ClassMetaInfo.$Long);
            this.classCache.put(java.util.Date.class, ClassMetaInfo.$Date);
            this.classCache.put(java.sql.Date.class, ClassMetaInfo.$SqlDate);
            this.classCache.put(Timestamp.class, ClassMetaInfo.$Timestamp);
            this.classCache.put(BigDecimal.class, ClassMetaInfo.$BigDecimal);
            this.classCache.put(BigInteger.class, ClassMetaInfo.$BigInteger);
        }
    }

    public ClassMetaInfo get(Class<?> clzz){
        ClassMetaInfo classMetaInfo;
        if(null==(classMetaInfo = this.classCache.get(clzz))) {
            synchronized (this.classCache){
                if(null==(classMetaInfo = this.classCache.get(clzz))) {
                    this.classCache.put(clzz,classMetaInfo = ReflectiveWrapper.unWrapperClass(clzz));
                }
            }
        }
        return classMetaInfo;
    }


}
