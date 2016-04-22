package test;

import top.flyfire.reflect.RawType;
import top.flyfire.reflect.ReflectiveWrapper;
import top.flyfire.reflect.metainfo.ClassMetaInfo;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
// extends A<String>
    public static class AC<T extends Map>{

        private String age;


        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public T obj;

        public T getObj() {
            return obj;
        }

        public void setObj(T obj) {
            this.obj = obj;
        }
    }
//<Map<String,Date>>
    public static class ACC extends AC {

    }

    public static void main(String[] args){
//        ReflectiveWrapper.unWrapperClass(A.class);
        ReflectiveWrapper.unWrapper(ACC.class);
//        ReflectiveWrapper.unWrapperClass(new HashMap<String,String>().getClass());
//        gg(AC.class);

    }






}
