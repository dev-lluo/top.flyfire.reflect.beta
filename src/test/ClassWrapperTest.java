package test;

import top.flyfire.reflect.RawType;

import java.lang.reflect.Type;
import java.util.*;

/**
 * Created by shyy_work on 2016/4/21.
 */
public class ClassWrapperTest {

    public static class A<T> {
        private int id;

        private String name;

        private Date birthday;

        private T t;

        private List<T> tList;

        private Map<String,T> tMap;

        public List<T> getTList() {
            return tList;
        }

        public void setTList(List<T> tList) {
            this.tList = tList;
        }

        public Map<String, T> getTMap() {
            return tMap;
        }

        public void setTMap(Map<String, T> tMap) {
            this.tMap = tMap;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Date getBirthday() {
            return birthday;
        }

        public void setBirthday(Date birthday) {
            this.birthday = birthday;
        }

        public T getT() {
            return t;
        }

        public void setT(T t) {
            this.t = t;
        }
    }
//
    public static class AC<T> extends A<String>{

        private String age;


        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        private T obj;

        public T getObj() {
            return obj;
        }

        public void setObj(T obj) {
            this.obj = obj;
        }

        private Map<? extends Number,? extends CharSequence> str;

        public Map<? extends Number, ? extends CharSequence> getStr() {
            return str;
        }

        public void setStr(Map<? extends Number, ? extends CharSequence> str) {
            this.str = str;
        }

        private T[] objArr;

        public T[] getObjArr() {
            return objArr;
        }

        public void setObjArr(T[] objArr) {
            this.objArr = objArr;
        }

    public Map<String, T> getPpmpMap() {
        return ppmpMap;
    }

    public void setPpmpMap(Map<String, T> ppmpMap) {
        this.ppmpMap = ppmpMap;
    }

    private Map<String,T> ppmpMap;
    }
//<Map<String,Date>>
    public static class ACC extends AC<AC<String>> {

    }

    public static void main(String[] args){
//        ReflectiveWrapper.unWrapperClass(A.class);
        long start = System.currentTimeMillis();
        for(int i = 0;i<1000000;i++) {
            Type type = new RawType(ACC.class) {
            }.getType();
//        ReflectiveWrapper.unWrapperClass(new HashMap<String,String>().getClass());
            type = new RawType<A<Date>>() {
            }.getType();
            type = new RawType<String>() {
            }.getType();
        }
        System.out.println("success["+(System.currentTimeMillis()-start)+"s]!!!");
    }






}
