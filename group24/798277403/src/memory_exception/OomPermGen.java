package memory_exception;


import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * JDK1.6才能模拟出这个异常
 * Created by zhouliang on 2017-05-27.
 */
public class OomPermGen {
/*    public static void main(String[] args) {
        // 使用List保持着常量池引用，避免Full GC 回收常量池行为
        List<String> list = new ArrayList<String>();
        //10MB的PermSize在int范围内足够产生OOM了
        int i = 0;
        while (true) {
            //调用intern方法，将字符串全部放在常量池中
            list.add(String.valueOf(i++).intern());
        }
    }*/
    static String path3 = "C:\\Users\\zhouliang\\Desktop\\mycoding\\coding2017\\group24\\798277403\\out\\production\\zhouliang";
    static String path2 = "C:\\Users\\zhouliang\\Desktop\\mycoding\\";

    public static void main(String[] args) {
        URL url = null;
        List<ClassLoader> classLoaderList = new ArrayList<ClassLoader>();
        int count = 0;
        try {
            url = new File(path2).toURI().toURL();
            System.out.println(url);
            URL[] urls = {url};
            while (true){
                ClassLoader loader = new URLClassLoader(urls);
                classLoaderList.add(loader);
                loader.loadClass("EmployeeV1");
                System.out.println(++count);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
