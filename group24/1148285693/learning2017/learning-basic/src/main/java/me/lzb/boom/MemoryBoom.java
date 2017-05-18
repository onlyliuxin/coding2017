package me.lzb.boom;


import java.util.ArrayList;
import java.util.List;

/**
 * @author LZB
 */
public class MemoryBoom {

    //-XX:MaxMetaspaceSize，最大空间，默认没有限制
    //-XX:MetaspaceSize，初始空间大小，达到该值就会触发垃圾收集进行类型卸载，同时GC会对该值进行调整。如果释放了大量的空间，就适当降低该值；如果释放了很少的空间，那么在不超过MaxMetaspaceSize时，适当提高该值。
    //-XX:MinMetaspaceFreeRatio，在GC之后，最小的Metaspace剩余空间容量的百分比，减少为分配空间所导致的垃圾收集
    //-XX:MaxMetaspaceFreeRatio，在GC之后，最大的Metaspace剩余空间容量的百分比，减少为释放空间所导致的垃圾收集
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
