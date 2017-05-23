package me.lzb.jvm.engine;

/**
 * 没有实现垃圾回收， 所以对于下面新创建的对象， 并没有记录到一个数据结构当中
 *
 * @author LZB
 */
public class Heap {
    //堆，存放new出来的对象
    private static Heap instance = new Heap();

    private Heap() {
    }

    public static Heap getInstance() {
        return instance;
    }

    public JavaObject newObject(String clzName) {

        JavaObject jo = new JavaObject(JavaObject.OBJECT);
        jo.setClassName(clzName);
        return jo;
    }

    public JavaObject newString(String value) {
        JavaObject jo = new JavaObject(JavaObject.STRING);
        jo.setStringValue(value);
        return jo;
    }

    public JavaObject newFloat(float value) {
        JavaObject jo = new JavaObject(JavaObject.FLOAT);
        jo.setFloatValue(value);
        return jo;
    }

    public JavaObject newInt(int value) {
        JavaObject jo = new JavaObject(JavaObject.INT);
        jo.setIntValue(value);
        return jo;
    }

}
