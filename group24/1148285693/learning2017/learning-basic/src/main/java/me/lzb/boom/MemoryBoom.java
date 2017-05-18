package me.lzb.boom;


import java.util.ArrayList;
import java.util.List;

/**
 * @author LZB
 */
public class MemoryBoom {


    public void metaspaceBoom() {
        String path = MemoryBoom.class.getResource("/").getPath();
        String className = "me.lzb.boom.Boom";
        List<Object> list = new ArrayList();
        while (true) {
            BoomClassLoader classLoader = new BoomClassLoader("boom", path);
            Class cls = classLoader.findClass(className);
//            try {
//                list.add(cls.newInstance());
//            } catch (InstantiationException e) {
//                e.printStackTrace();
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
        }

    }


    public void outOfMemoryBoom() {
        List<MemoryBoom> list = new ArrayList();
        while (true) {
            list.add(new MemoryBoom());
        }
    }

    public void stackOverflowBoom() {
        stackOverflowBoom();
    }


}
