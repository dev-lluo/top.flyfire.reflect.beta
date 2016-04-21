package top.flyfire.reflect;

import top.flyfire.reflect.metainfo.ClassMetaInfo;

import java.lang.reflect.*;
import java.util.*;

/**
 * Created by shyy_work on 2016/4/21.
 */
public enum ReflectiveWrapper {
    ;
    public static ClassMetaInfo unWrapperClass(Class<?> clzz){
        ClassMetaInfo classMetaInfo = new ClassMetaInfo();
        Field[] fields;
        Method[] methods,result = new Method[2];
        Map<String,Method> methodMap = new HashMap<>();
        Map<String,TypeVariable> typeVariableMap;
        int getter = 0,setter = 1;
        String fieldName;
        while (null!=clzz.getSuperclass()){
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
                        System.out.print(fieldName);
                        System.out.print(" @type:"+fields[i].getGenericType());
                        System.out.print(" @getter:"+result[getter]);
                        System.out.print(" @setter:"+result[setter]);
                        System.out.println();
                    }
                }
            }

            clzz = clzz.getSuperclass();
        }
        return classMetaInfo;
    }

    public static boolean hasAccess(String name,Map<String,Method> methodMap,Method[] result){
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
