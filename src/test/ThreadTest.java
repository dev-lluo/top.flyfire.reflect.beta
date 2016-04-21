package test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shyy_work on 2016/4/21.
 */
public class ThreadTest {

    public static void main(String[] args){

        Map<String,String> res = new HashMap<>();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                synchronized (res){
                    try {
                        Thread.sleep(2000);
                        res.put("123", "123");
                        System.out.println("............");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                System.out.println(res.get("123"));
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(res.get("123"));
            }
        };

        new Thread(runnable).start();
        new Thread(runnable1).start();
    }

}
