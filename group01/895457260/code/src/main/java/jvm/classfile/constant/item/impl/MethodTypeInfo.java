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
public class MethodTypeInfo implements Constant, IReference {
    private int descriptorIndex;
    private String descriptor;

    public MethodTypeInfo(int descriptorIndex) {
        this.descriptorIndex = descriptorIndex;
    }

    @Override
    public int size() {
        return 3;
    }

    @Override
    public Map<Integer, String> printableMap() {
        Map<Integer, String> map = new HashMap<>();
        map.put(PRINT_TYPE, "MethodType");
        map.put(PRINT_PARAM, "#" + descriptorIndex);
        map.put(PRINT_COMMENT, "// " + descriptor);
        return map;
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }

    @Override
    public void linkReference(ConstantPool constantPool) {
        descriptor = ((UTF8Info) constantPool.getConstantInfo(descriptorIndex)).getValue();
    }
}
