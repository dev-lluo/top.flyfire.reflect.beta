package top.flyfire.reflect;

import top.flyfire.reflect.ReflectiveException;
import top.flyfire.reflect.metainfo.ConstructorMetaInfo;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

/**
 * Created by shyy_work on 2016/4/28.
 */
public enum ConstructorCache {
    INSTANCE;
    private Map<Class<?>,ConstructorMetaInfo> constructorCache;
    private ConstructorCache(){
        ConstructorCache:
        {
            this.constructorCache = new HashMap<>();
            this.constructorCache.put(Map.class, new ConstructorMetaInfo() {
                @Override
                public Constructor buildConstructor() throws NoSuchMethodException {
                    return HashMap.class.getConstructor();
                }

                @Override
                public Object $new() throws ReflectiveOperationException {
                    return new HashMap<>();
                }
            });
            this.constructorCache.put(Collection.class, new ConstructorMetaInfo() {
                @Override
                public Constructor buildConstructor() throws ReflectiveOperationException {
                    return ArrayList.class.getConstructor();
                }

                @Override
                public Object $new() throws ReflectiveOperationException {
                    return new ArrayList<>();
                }
            });
            this.constructorCache.put(List.class, this.constructorCache.get(Collection.class));
            this.constructorCache.put(Set.class, new ConstructorMetaInfo() {
                @Override
                public Constructor buildConstructor() throws ReflectiveOperationException {
                    return HashSet.class.getConstructor();
                }

                @Override
                public Object $new() throws ReflectiveOperationException {
                    return new HashSet<>();
                }
            });
            this.constructorCache.put(Integer.class, new ConstructorMetaInfo() {
                @Override
                public Constructor buildConstructor() throws ReflectiveOperationException {
                    return Integer.class.getConstructor(String.class);
                }

                @Override
                public Object $new() throws ReflectiveOperationException {
                    return 0;
                }
            });
            this.constructorCache.put(int.class, this.constructorCache.get(Integer.class));
            this.constructorCache.put(Double.class, new ConstructorMetaInfo() {
                @Override
                public Constructor buildConstructor() throws ReflectiveOperationException {
                    return Double.class.getConstructor(String.class);
                }

                @Override
                public Object $new() throws ReflectiveOperationException {
                    return 0d;
                }
            });
            this.constructorCache.put(double.class, this.constructorCache.get(Double.class));
            this.constructorCache.put(Float.class, new ConstructorMetaInfo() {
                @Override
                public Constructor buildConstructor() throws ReflectiveOperationException {
                    return Float.class.getConstructor(String.class);
                }

                @Override
                public Object $new() throws ReflectiveOperationException {
                    return 0f;
                }
            });
            this.constructorCache.put(float.class, this.constructorCache.get(Float.class));
            this.constructorCache.put(Short.class, new ConstructorMetaInfo() {
                @Override
                public Constructor buildConstructor() throws ReflectiveOperationException {
                    return Short.class.getConstructor(String.class);
                }

                @Override
                public Object $new() throws ReflectiveOperationException {
                    return (short)0;
                }
            });
            this.constructorCache.put(short.class, this.constructorCache.get(Short.class));
            this.constructorCache.put(Long.class, new ConstructorMetaInfo() {
                @Override
                public Constructor buildConstructor() throws ReflectiveOperationException {
                    return Long.class.getConstructor(String.class);
                }

                @Override
                public Object $new() throws ReflectiveOperationException {
                    return 0l;
                }
            });
            this.constructorCache.put(long.class, this.constructorCache.get(Long.class));
            this.constructorCache.put(Byte.class, new ConstructorMetaInfo() {
                @Override
                public Constructor buildConstructor() throws ReflectiveOperationException {
                    return Byte.class.getConstructor(String.class);
                }

                @Override
                public Object $new() throws ReflectiveOperationException {
                    return (byte)0;
                }
            });
            this.constructorCache.put(byte.class, this.constructorCache.get(Byte.class));
            this.constructorCache.put(Boolean.class, new ConstructorMetaInfo() {
                @Override
                public Constructor buildConstructor() throws ReflectiveOperationException {
                    return Boolean.class.getConstructor(String.class);
                }

                @Override
                public Object $new() throws ReflectiveOperationException {
                    return false;
                }
            });
            this.constructorCache.put(boolean.class, this.constructorCache.get(Boolean.class));
            this.constructorCache.put(BigInteger.class, new ConstructorMetaInfo() {
                @Override
                public Constructor buildConstructor() throws ReflectiveOperationException {
                    return BigInteger.class.getConstructor(String.class);
                }

                @Override
                public Object $new() throws ReflectiveOperationException {
                    return BigInteger.ZERO;
                }
            });
            this.constructorCache.put(BigDecimal.class, new ConstructorMetaInfo() {
                @Override
                public Constructor buildConstructor() throws ReflectiveOperationException {
                    return BigDecimal.class.getConstructor(String.class);
                }

                @Override
                public Object $new() throws ReflectiveOperationException {
                    return BigDecimal.ZERO;
                }
            });
            this.constructorCache.put(Number.class, this.constructorCache.get(BigDecimal.class));
            this.constructorCache.put(java.sql.Date.class, new ConstructorMetaInfo() {
                @Override
                public Constructor buildConstructor() throws ReflectiveOperationException {
                    return java.sql.Date.class.getConstructor(long.class);
                }

                @Override
                public Object $new() throws ReflectiveOperationException {
                    return new java.sql.Date(System.currentTimeMillis());
                }
            });
            this.constructorCache.put(java.sql.Timestamp.class, new ConstructorMetaInfo() {
                @Override
                public Constructor buildConstructor() throws ReflectiveOperationException {
                    return java.sql.Timestamp.class.getConstructor(long.class);
                }

                @Override
                public Object $new() throws ReflectiveOperationException {
                    return new java.sql.Timestamp(System.currentTimeMillis());
                }
            });
            this.constructorCache.put(CharSequence.class, new ConstructorMetaInfo() {
                @Override
                public Constructor buildConstructor() throws ReflectiveOperationException {
                    return String.class.getConstructor();
                }

                @Override
                public Object $new() throws ReflectiveOperationException {
                    return "";
                }
            });
        }
    }

    public void reg(Class clzz,ConstructorMetaInfo constructorMetaInfo){
        ConstructorMetaInfo has;
        if(null==(has = this.constructorCache.get(clzz))) {
            synchronized (this.constructorCache){
                if(null==(has = this.constructorCache.get(clzz))) {
                    this.constructorCache.put(clzz,constructorMetaInfo);
                }
            }
        }
    }

    public ConstructorMetaInfo get(Class clzz){
        ConstructorMetaInfo has;
        if(null==(has = this.constructorCache.get(clzz))) {
            synchronized (this.constructorCache){
                if(null==(has = this.constructorCache.get(clzz))) {
                    has = new ConstructorMetaInfo() {
                        @Override
                        public Constructor buildConstructor() throws ReflectiveOperationException {
                            return clzz.getConstructor();
                        }
                    };
                    this.constructorCache.put(clzz,has);
                }
            }
        }
        return has;
    }
}
