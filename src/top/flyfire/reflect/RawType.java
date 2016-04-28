package top.flyfire.reflect;

import top.flyfire.reflect.metainfo.ClassMetaInfo;
import top.flyfire.reflect.type.TypeParameterized;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by shyy_work on 2016/4/22.
 */
public abstract class RawType<T> {

    private static final int INS = 0;

    private Type type;

    public RawType() { }

    public RawType(Class<?> type) {
        this.type = type;
    }



    public final Type getType(){
        if(null==this.type) {
            this.type = this.getClass().getGenericSuperclass();
            if (this.type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                ClassMetaInfo classMetaInfo;
                if ((type = ReflectiveWrapper.unWrapper(((ParameterizedType) type).getActualTypeArguments()[INS])) instanceof TypeParameterized) {
                    TypeParameterized pType = (TypeParameterized) type;
                    classMetaInfo = new ClassMetaInfo(((ClassMetaInfo) pType.getRawType()).getRawType());
                    classMetaInfo.extendSuper(pType);
                    this.type = classMetaInfo;
                } else if (type instanceof ClassMetaInfo) {
                    classMetaInfo = (ClassMetaInfo) type;
                    this.type = classMetaInfo;
                } else {
                    throw new ReflectiveSyntaxException("[A ClassMetaInfo or TypeParameterized is expected in the buidlSuper , but superType is of type " + type + " .]");
                }
                return this.type;
            }else{
                throw new IllegalArgumentException("type arguments is missing.");
            }
        }else if(this.type instanceof Class) {
            this.type = ReflectiveWrapper.unWrapper(this.type);
            return this.type;
        }else if(this.type instanceof ClassMetaInfo){
            return this.type;
        }else{
            throw new IllegalArgumentException("type arguments is missing.");
        }
    }

}
