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
public class NameAndTypeInfo implements Constant, IReference {
    private int nameIndex;
    private int descriptorIndex;
    private String name;
    private String descriptor;

    public NameAndTypeInfo(int nameIndex, int descriptorIndex) {
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
    }

    @Override
    public int size() {
        return 5;
    }

    @Override
    public Map<Integer, String> printableMap() {
        Map<Integer, String> map = new HashMap<>();
        map.put(PRINT_TYPE, "NameAndType");
        map.put(PRINT_PARAM, "#" + nameIndex + ":#" + descriptorIndex);
        map.put(PRINT_COMMENT, "// " + name + ':' + descriptor);
        return map;
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }

    public int getIndex1() {
        return nameIndex;
    }

    public int getIndex2() {
        return descriptorIndex;
    }

    public String getNameAndType() {
        return name + ':' + descriptor;
    }

    @Override
    public void linkReference(ConstantPool constantPool) {
        name = ((UTF8Info) constantPool.getConstantInfo(nameIndex)).getValue();
        descriptor = ((UTF8Info) constantPool.getConstantInfo(descriptorIndex)).getValue();
    }
}
