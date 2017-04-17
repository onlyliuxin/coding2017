package com.coderising.jvm.method;

import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.attr.AttributeInfo;
import com.coderising.jvm.attr.CodeAttr;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.constant.UTF8Info;
import com.coderising.jvm.loader.ByteCodeIterator;


public class Method {

    private int accessFlag;
    private int nameIndex;
    private int descriptorIndex;

    private CodeAttr codeAttr;

    private ClassFile clzFile;


    public ClassFile getClzFile() {
        return clzFile;
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }

    public CodeAttr getCodeAttr() {
        return codeAttr;
    }

    public void setCodeAttr(CodeAttr code) {
        this.codeAttr = code;
    }

    public Method(ClassFile clzFile, int accessFlag, int nameIndex, int descriptorIndex) {
        this.clzFile = clzFile;
        this.accessFlag = accessFlag;
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
    }


    public static Method parse(ClassFile clzFile, ByteCodeIterator iter) {
        int access_flag = iter.nextU2ToInt();
        int name_index = iter.nextU2ToInt();
        int descriptor_index = iter.nextU2ToInt();
        int attributes_count = iter.nextU2ToInt();
        Method method = new Method(clzFile, access_flag, name_index, descriptor_index);

        System.out.println("method_info" + name_index + "有" + attributes_count + "个属性");

        for (int i = 0; i < attributes_count; i++) {
            method.setCodeAttr(CodeAttr.parse(clzFile, iter));
        }

        return method;

    }
}
