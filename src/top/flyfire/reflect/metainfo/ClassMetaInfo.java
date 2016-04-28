package top.flyfire.reflect.metainfo;

import top.flyfire.reflect.*;
import top.flyfire.reflect.type.TypeGenericArray;
import top.flyfire.reflect.type.TypeParameterized;
import top.flyfire.reflect.type.TypeWildcard;

import java.lang.reflect.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by shyy_work on 2016/4/21.
 */
public final class ClassMetaInfo extends $Type {

    public final static ClassMetaInfo $Object = new ClassMetaInfo(Object.class);

    public final static ClassMetaInfo $Map = new ClassMetaInfo(Map.class);

    public final static ClassMetaInfo $Collection = new ClassMetaInfo(Collection.class);

    public final static ClassMetaInfo $List = new ClassMetaInfo(List.class);

    public final static ClassMetaInfo $Set = new ClassMetaInfo(Set.class);

    public final static ClassMetaInfo $String = new ClassMetaInfo(String.class);

    public final static ClassMetaInfo $Number = new ClassMetaInfo(Number.class);

    public final static ClassMetaInfo $Integer = new ClassMetaInfo(Integer.class);

    public final static ClassMetaInfo $Float = new ClassMetaInfo(Float.class);

    public final static ClassMetaInfo $Double = new ClassMetaInfo(Double.class);

    public final static ClassMetaInfo $Boolean = new ClassMetaInfo(Boolean.class);

    public final static ClassMetaInfo $Byte = new ClassMetaInfo(Byte.class);

    public final static ClassMetaInfo $Short = new ClassMetaInfo(Short.class);

    public final static ClassMetaInfo $Long = new ClassMetaInfo(Long.class);

    public final static ClassMetaInfo $Date = new ClassMetaInfo(java.util.Date.class);

    public final static ClassMetaInfo $SqlDate = new ClassMetaInfo(java.sql.Date.class);

    public final static ClassMetaInfo $Timestamp = new ClassMetaInfo(Timestamp.class);

    public final static ClassMetaInfo $BigDecimal = new ClassMetaInfo(BigDecimal.class);

    public final static ClassMetaInfo $BigInteger = new ClassMetaInfo(BigInteger.class);

    private final Class<?> rawType;

    private final ConstructorMetaInfo constructorMetaInfo;

    private final TypeVariable[] typeParameters;

    public final Class<?> getRawType() {
        return rawType;
    }

    private final Map<String,FieldMetaInfo> fieldMetaInfoMap;

    private final boolean isJdkType;

    private final boolean isInterface;

    protected Map<String,FieldMetaInfo> getFieldMetaInfoMap(){
        return this.fieldMetaInfoMap;
    }

    public FieldMetaInfo getFieldMetaInfo(String fieldName){
        return this.fieldMetaInfoMap.get(fieldName);
    }

    public void setFieldMetaInfo(String fieldName,FieldMetaInfo fieldMetaInfo){
        this.fieldMetaInfoMap.put(fieldName,fieldMetaInfo);
    }

    public void extendSuper(ClassMetaInfo classMetaInfo){
        classMetaInfo.fieldMetaInfoMap.forEach((k, v) -> this.fieldMetaInfoMap.put(k, v));
    }

    public void extendSuper(TypeParameterized typeParameterized){
        Type type;
        while(!((type=typeParameterized.getRawType()) instanceof ClassMetaInfo)) {
            if (type instanceof TypeParameterized){
                typeParameterized = (TypeParameterized)type;
            }else{
                throw new ReflectiveSyntaxException("[A ClassMetaInfo or TypeParameterized is expected in the buidlSuper , but superType is of type "+typeParameterized+" .]");
            }
        }
        ClassMetaInfo classMetaInfo = (ClassMetaInfo)type;
        Type[] types = typeParameterized.getActualTypeArguments();
        classMetaInfo.fieldMetaInfoMap.forEach((k, v) -> {
            if (v.getField().getDeclaringClass().equals(classMetaInfo.getRawType())) {
                this.fieldMetaInfoMap
                        .put(k, new FieldMetaInfo(k, v.getField(), ClassMetaInfo.unWrapperField(v.getField().getGenericType(), classMetaInfo.typeParameters, types), v.getGetter(), v.getSetter()));
            } else {
                this.fieldMetaInfoMap.put(k, v);
            }
        });
    }

    public boolean isJdkType(){
        return this.isJdkType;
    }

    public Object $new(){
        try {
            return this.constructorMetaInfo.$new();
        }catch (ReflectiveOperationException e){
            throw new ReflectiveException(e);
        }
    }

    @Override
    public boolean compatible(Type type) {
        return false;
    }

    public ClassMetaInfo(Class<?> rawType) {
        super();
        this.rawType = rawType;
        this.isJdkType = ReflectUtil.isJdkType(rawType);
        this.isInterface = ReflectUtil.isInterface(rawType);
        this.constructorMetaInfo = ConstructorCache.INSTANCE.get(this.rawType);
        this.typeParameters = this.rawType.getTypeParameters();
        this.fieldMetaInfoMap = new HashMap<>();
    }

    @Override
    protected String buildTypeName() {
        return this.rawType.getTypeName();
    }

    private static Type unWrapperField(Type type,TypeVariable[] typeVariables,Type[] types){
        if(type instanceof Class){
            return ReflectiveWrapper.unWrapper(type);
        }else if(type instanceof TypeVariable){
            for(int i = 0;i<typeVariables.length;i++){
                if(type.equals(typeVariables[i]))
                    return ReflectiveWrapper.unWrapper(types.length>i?types[i]:type);
            }
            return $Object;
        }else if(type instanceof ParameterizedType){
            ParameterizedType parameterizedType = (ParameterizedType)type;
            Type[] realTypes = parameterizedType.getActualTypeArguments();
            for(int i = 0;i<realTypes.length;i++){
                realTypes[i] = ClassMetaInfo.unWrapperField(realTypes[i], typeVariables, types);
            }
            return new TypeParameterized(parameterizedType.getRawType(),parameterizedType.getOwnerType(),realTypes);
        }else if(type instanceof WildcardType){
            WildcardType wildcardType = (WildcardType)type;
            return new TypeWildcard(wildcardType.getUpperBounds(),wildcardType.getLowerBounds());
        }else if(type instanceof GenericArrayType){
            GenericArrayType genericArrayType = (GenericArrayType)type;
            return new TypeGenericArray(ClassMetaInfo.unWrapperField(genericArrayType.getGenericComponentType(), typeVariables, types));
        }else{
            throw new ReflectiveSyntaxException("[A ClassMetaInfo or TypeParameterized is expected in the unWrapperField , but fieldType is of type "+type+" .]");
        }
    }

}
