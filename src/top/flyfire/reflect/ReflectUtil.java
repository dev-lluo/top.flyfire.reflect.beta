package top.flyfire.reflect;

import java.util.Date;

/**
 * Created by flyfire[dev.lluo@outlook.com] on 2016/4/27.
 */
public interface ReflectUtil {
    static boolean isJdkType(Class clzz){
        return clzz.isPrimitive()||Number.class.isAssignableFrom(clzz)||Date.class.isAssignableFrom(clzz);
    }
}
