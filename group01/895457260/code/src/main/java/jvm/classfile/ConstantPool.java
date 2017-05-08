package jvm.classfile;

import jvm.classfile.constant.item.Constant;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by Haochen on 2017/4/9.
 * TODO:
 */
public class ConstantPool {
    private List<Constant> constantMap = new ArrayList<>();

    public void forEach(Consumer<? super Constant> action) {
        constantMap.forEach(action);
    }

    public int getSize() {
        return constantMap.size() - 1;
    }

    boolean addConstantInfo(Constant c) {
        return constantMap.add(c);
    }
    public Constant getConstantInfo(int index) {
        return constantMap.get(index);
    }
}
