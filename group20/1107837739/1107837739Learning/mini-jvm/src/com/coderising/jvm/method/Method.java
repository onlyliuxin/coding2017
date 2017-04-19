package com.coderising.jvm.method;

import com.coderising.jvm.attr.AttributeInfo;
import com.coderising.jvm.attr.CodeAttr;
import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.loader.ByteCodeIterator;

public class Method {

    private int accessFlag;
    private int nameIndex;
    private int descriptorIndex;

    private CodeAttr codeAttr;

    private ClassFile clzFile;

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
        int attributeCount = iter.nextU2ToInt();

        System.out.println("Filed accessFlag: " + accessFlag);
        System.out.println("Filed nameIndex: " + nameIndex);
        System.out.println("Filed descriptorIndex: " + descriptorIndex);
        System.out.println("Filed attributeCount: " + attributeCount);

        Method method = new Method(clzFile, accessFlag, nameIndex, descriptorIndex);

        for (int i = 0; i < attributeCount; i++) {
            int attrNameIndex = iter.nextU2ToInt();
            String attrName = clzFile.getConstantPool().getUTF8String(attrNameIndex);
            iter.back(2);

            if (AttributeInfo.CODE.equalsIgnoreCase(attrName)) {
                CodeAttr codeAttr = CodeAttr.parse(clzFile, iter);
                method.setCodeAttr(codeAttr);
            } else {
                throw new RuntimeException("Only CODE attribute is implemented!");
            }
        }

        return method;
    }

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
}
