package top.flyfire.reflect;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 * Created by flyfire[dev.lluo@outlook.com] on 2016/4/27.
 */
public interface ReflectUtil {
    static boolean isJdkType(Class clzz){
        return clzz.isPrimitive()||Number.class.isAssignableFrom(clzz)||Date.class.isAssignableFrom(clzz)|| Boolean.class.isAssignableFrom(clzz)|| Collection.class.isAssignableFrom(clzz)|| Map.class.isAssignableFrom(clzz);
    }

    static boolean isInterface(Class clzz){
        return clzz.isInterface();
    }
}
