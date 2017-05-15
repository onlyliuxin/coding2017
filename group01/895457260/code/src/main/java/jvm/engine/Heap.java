package jvm.engine;

import jvm.classfile.constant.item.Constant;
import jvm.classfile.constant.item.impl.ClassInfo;
import jvm.classfile.constant.item.impl.FloatInfo;
import jvm.classfile.constant.item.impl.IntegerInfo;
import jvm.classfile.constant.item.impl.StringInfo;

public class Heap {

    /**
     * 没有实现垃圾回收， 所以对于下面新创建的对象， 并没有记录到一个数据结构当中
     */
    private static Heap instance = new Heap();

    private Heap() {
    }

    public static Heap getInstance() {
        return instance;
    }

    public JavaObject createObject(Constant constant) {
        if (constant instanceof IntegerInfo) {
            int value = ((IntegerInfo) constant).getValue();
            return Heap.getInstance().newInt(value);
        } else if (constant instanceof FloatInfo) {
            float value = ((FloatInfo) constant).getValue();
            return Heap.getInstance().newFloat(value);
        } else if (constant instanceof StringInfo) {
            String value = ((StringInfo) constant).getValue();
            return Heap.getInstance().newString(value);
        } else if (constant instanceof ClassInfo) {
            String className = ((ClassInfo) constant).getClassName();
            return Heap.getInstance().newClassReference(className);
        }
        return null;
    }

    public JavaObject newClassReference(String clzName) {
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
