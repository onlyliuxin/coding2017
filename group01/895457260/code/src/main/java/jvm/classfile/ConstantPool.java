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
    private List<Constant> constants = new ArrayList<>();

    public void forEach(Consumer<? super Constant> action) {
        constants.forEach(action);
    }

    public int getSize() {
        return constants.size() - 1;
    }

    boolean addConstantInfo(Constant c) {
        return constants.add(c);
    }
    public Constant getConstantInfo(int index) {
        return constants.get(index);
    }
}
