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
public class ClassInfo implements Constant, IReference {
    private int nameIndex;
    private String className;

    public ClassInfo(int nameIndex) {
        this.nameIndex = nameIndex;
    }

    @Override
    public int size() {
        return 3;
    }

    @Override
    public Map<Integer, String> printableMap() {
        Map<Integer, String> map = new HashMap<>();
        map.put(PRINT_TYPE, "Class");
        map.put(PRINT_PARAM, "#" + nameIndex);
        map.put(PRINT_COMMENT, "// " + className);
        return map;
    }

    @Override
    public void linkReference(ConstantPool constantPool) {
        Constant constant = constantPool.getConstantInfo(getUtf8Index());
        if (constant instanceof UTF8Info) {
            this.className = ((UTF8Info) constant).getValue();
        }
    }

    public int getUtf8Index() {
        return nameIndex;
    }

    public String getClassName() {
        return className;
    }
}
