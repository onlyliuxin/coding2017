package cn.net.pikachu.download;

/**
 * Created by pikachu on 2017/3/14.
 */
public class LogUtil {
    public static void log(String log){
        System.out.println(Thread.currentThread().getId()+"-"+Thread.currentThread().getName()+" "+log);
    }
}
