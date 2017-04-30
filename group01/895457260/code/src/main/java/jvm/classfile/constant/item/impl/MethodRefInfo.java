package jvm.classfile.constant.item.impl;

import jvm.classfile.ConstantPool;
import jvm.classfile.constant.item.Constant;
import jvm.classfile.constant.item.IReference;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Haochen on 2017/4/9.
 * TODO:
 */
public class MethodRefInfo implements Constant, IReference {
    private int classIndex;
    private int nameAndTypeIndex;
    private String className;
    private String nameAndType;

    public MethodRefInfo(int classIndex, int nameAndTypeIndex) {
        this.classIndex = classIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }

    @Override
    public int size() {
        return 5;
    }

    @Override
    public Map<Integer, String> printableMap() {
        Map<Integer, String> map = new HashMap<>();
        map.put(PRINT_TYPE, "MethodRef");
        map.put(PRINT_PARAM, "#" + classIndex + ".#" + nameAndTypeIndex);
        map.put(PRINT_COMMENT, "// " + className + '.' + nameAndType);
        return map;
    }

    public int getClassInfoIndex() {
        return classIndex;
    }

    public int getNameAndTypeIndex() {
        return nameAndTypeIndex;
    }

    @Override
    public void linkReference(ConstantPool constantPool) {
        className = ((ClassInfo) constantPool.getConstantInfo(classIndex)).getClassName();
        nameAndType = ((NameAndTypeInfo) constantPool.getConstantInfo(nameAndTypeIndex)).getNameAndType();
    }
}
