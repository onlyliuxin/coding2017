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
public class InvokeDynamicInfo implements Constant, IReference {
    private int bootstrapMethodAttrIndex;
    private int nameAndTypeIndex;
    private String bootstrapMethodAttr;
    private String nameAndType;

    public InvokeDynamicInfo(int bootstrapMethodAttrIndex, int nameAndTypeIndex) {
        this.bootstrapMethodAttrIndex = bootstrapMethodAttrIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }

    @Override
    public int size() {
        return 5;
    }

    @Override
    public Map<Integer, String> printableMap() {
        Map<Integer, String> map = new HashMap<>();
        map.put(PRINT_TYPE, "InvokeDynamic");
        map.put(PRINT_PARAM, "#" + bootstrapMethodAttrIndex + ".#" + nameAndTypeIndex);
        map.put(PRINT_COMMENT, "// " + bootstrapMethodAttr + '.' + nameAndType);
        return map;
    }

    public int getBootstrapMethodAttrIndex() {
        return bootstrapMethodAttrIndex;
    }

    public int getNameAndTypeIndex() {
        return nameAndTypeIndex;
    }

    @Override
    public void linkReference(ConstantPool constantPool) {
        bootstrapMethodAttr = ((ClassInfo) constantPool.getConstantInfo(bootstrapMethodAttrIndex)).getClassName();
        nameAndType = ((NameAndTypeInfo) constantPool.getConstantInfo(nameAndTypeIndex)).getNameAndType();
    }
}
