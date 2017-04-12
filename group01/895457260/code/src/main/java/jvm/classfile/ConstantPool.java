package jvm.classfile;

import jvm.classfile.constant.item.Constant;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * Created by Haochen on 2017/4/9.
 * TODO:
 */
public class ConstantPool {
    private Map<Integer, Constant> constantMap = new HashMap<>();

    public void forEach(BiConsumer<? super Integer, ? super Constant> action) {
        constantMap.forEach(action);
    }

    public int getSize() {
        return constantMap.size() - 1;
    }

    Constant putConstantInfo(int index, Constant c) {
        return constantMap.put(index, c);
    }
    public Constant getConstantInfo(int index) {
        return constantMap.get(index);
    }
}
