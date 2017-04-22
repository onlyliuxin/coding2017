package com.coding2017.jvm.method;

import com.coding2017.jvm.attr.AttributeInfo;
import com.coding2017.jvm.attr.CodeAttr;
import com.coding2017.jvm.clz.ClassFile;
import com.coding2017.jvm.loader.ByteCodeIterator;

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
        int accessFlag = iter.nextU2ToInt();
        int nameIndex = iter.nextU2ToInt();
        int descriptorIndex = iter.nextU2ToInt();
        Method method = new Method(clzFile, accessFlag, nameIndex, descriptorIndex);

        method.parseAttributes(iter);
        return method;

    }

    private void parseAttributes(ByteCodeIterator iter) {
        int methodAttributeCount = iter.nextU2ToInt();
        for (int i = 0; i < methodAttributeCount; i++) {
            AttributeInfo attributeInfo = AttributeInfo.parse(this.clzFile, iter);
            if (attributeInfo instanceof CodeAttr) {
                this.setCodeAttr((CodeAttr) attributeInfo);
            } else {
                throw new RuntimeException("unknown method attribute");
            }
        }
    }
}
