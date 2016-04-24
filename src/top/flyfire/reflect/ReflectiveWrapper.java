package top.flyfire.reflect;

import top.flyfire.reflect.metainfo.ClassMetaInfo;
import top.flyfire.reflect.metainfo.FieldMetaInfo;
import top.flyfire.reflect.type.TypeGenericArray;
import top.flyfire.reflect.type.TypeParameterized;
import top.flyfire.reflect.type.TypeWildcard;

import java.lang.reflect.*;
import java.util.*;

/**
 * Created by shyy_work on 2016/4/21.
 */
public enum ReflectiveWrapper {
    ;
    public static Type unWrapper(Type type){
        if(type instanceof Class){
            Class clzz = (Class)type;
            if(clzz.isArray()) {
                ReflectiveCache.INSTANCE.get(clzz.getComponentType());
            }else{
                ReflectiveCache.INSTANCE.get(clzz);
            }
            return clzz;
        }
        return null;
    }

    protected static ClassMetaInfo unWrapperClass(Class<?> clzz){
        ClassMetaInfo classMetaInfo = new ClassMetaInfo(clzz);
        Field[] fields;
        Method[] methods,result = new Method[2];
        Map<String,Method> methodMap = new HashMap<>();
        Type[] types = new TypeVariable[0];
        int getter = 0,setter = 1;
        String fieldName;
        while (null!=clzz&&Object.class!=clzz){
            prepared:
            {
                fields = clzz.getDeclaredFields();
                methods = clzz.getDeclaredMethods();
                for (int i = 0; i < methods.length; i++) {
                    methodMap.put(methods[i].getName(), methods[i]);
                }

            }
            buildField:
            {
                for(int i = 0;i<fields.length;i++){
                    fieldName = fields[i].getName();
                    if(ReflectiveWrapper.hasAccess(fieldName,methodMap,result)){
                        FieldMetaInfo fieldMetaInfo = new FieldMetaInfo(fieldName,fields[i],ReflectiveWrapper.unWrapperFieldType(fields[i].getGenericType(), clzz.getTypeParameters(), types),result[getter],result[setter]);
                        System.out.println(fieldMetaInfo);
                        classMetaInfo.setFieldMetaInfo(fieldName,fieldMetaInfo);
                    }
                }
            }
            typed:
            {
                Type type = clzz.getGenericSuperclass();
                clzz = clzz.getSuperclass();
                if(type==null||(type!=null&&type instanceof Class))
                    break typed;
                types = ((ParameterizedType)type).getActualTypeArguments();
            }
        }
        return classMetaInfo;
    }

    private static Type unWrapperFieldType(Type type,TypeVariable[] typeVariables,Type[] types){
        if(type instanceof Class){
            return type;
        }else if(type instanceof TypeVariable){
            for(int i = 0;i<typeVariables.length;i++){
                if(type.equals(typeVariables[i]))
                    return types.length>i?types[i]:Object.class;
            }
            return Object.class;
        }else if(type instanceof ParameterizedType){
            ParameterizedType parameterizedType = (ParameterizedType)type;
            Type[] realTypes = parameterizedType.getActualTypeArguments();
            for(int i = 0;i<realTypes.length;i++){
                realTypes[i] = ReflectiveWrapper.unWrapperFieldType(realTypes[i],typeVariables,types);
            }
            return new TypeParameterized(ReflectiveWrapper.unWrapper(parameterizedType.getRawType()),ReflectiveWrapper.unWrapper(parameterizedType.getOwnerType()),realTypes);
        }else if(type instanceof WildcardType){
            WildcardType wildcardType = (WildcardType)type;
            return new TypeWildcard(wildcardType.getUpperBounds(),wildcardType.getUpperBounds());
        }else if(type instanceof GenericArrayType){
            GenericArrayType genericArrayType = (GenericArrayType)type;
            return new TypeGenericArray(ReflectiveWrapper.unWrapperFieldType(genericArrayType.getGenericComponentType(),typeVariables,types));
        }else{
            return type;
        }
    }


    private static boolean hasAccess(String name,Map<String,Method> methodMap,Method[] result){
        String _gname,_sname;
        int getter = 0,setter = 1;
        boolean has = true;
        build:
        {
            format:
            {
                char[] nameChars = name.toCharArray(), gnameChars = new char[nameChars.length + 3], snameChars = new char[nameChars.length + 3];
                nameChars[0] = toUpper(nameChars[0]);
                System.arraycopy(nameChars, 0, gnameChars, 3, nameChars.length);
                System.arraycopy(nameChars, 0, snameChars, 3, nameChars.length);
                gnameChars[0] = 'g';
                snameChars[0] = 's';
                gnameChars[1] = snameChars[1] = 'e';
                gnameChars[2] = snameChars[2] = 't';
                _gname = new String(gnameChars);
                _sname = new String(snameChars);
            }
            findGetter:
            {
                if (null==(result[getter]=methodMap.get(_gname))){
                    has = false;
                    break build;
                }
                int modifiers = result[getter].getModifiers();
                if(Modifier.isAbstract(modifiers)||!Modifier.isPublic(modifiers)){
                    has = false;
                    break build;
                }
            }
            findSetter:{
                if (null==(result[setter]=methodMap.get(_sname))){
                    has = false;
                    break build;
                }
                int modifiers = result[getter].getModifiers();
                if(Modifier.isAbstract(modifiers)||!Modifier.isPublic(modifiers)){
                    has = false;
                    break build;
                }
            }
        }
        return has;
    }

    private static char toUpper(char sign){
        if(sign>='a'&&sign<='z'){
            return (char)(sign-32);
        }
        return sign;
    }

    private static char toLower(char sign){
        if(sign>='A'&&sign<='Z'){
            return (char)(sign+32);
        }
        return sign;
    }

}
