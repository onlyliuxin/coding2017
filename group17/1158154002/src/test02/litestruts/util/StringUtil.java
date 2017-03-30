package test02.litestruts.util;

public class StringUtil {

    //首字母大写
    public static String captureName(String name) {
    //     name = name.substring(0, 1).toUpperCase() + name.substring(1);
 //        return  name;
         char[] cs=name.toCharArray();
         cs[0]-=32;
         return String.valueOf(cs);        
     }
}
