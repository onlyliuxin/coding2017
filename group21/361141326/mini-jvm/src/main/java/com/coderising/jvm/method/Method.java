package com.coderising.jvm.method;

import com.coderising.jvm.attr.AttributeInfo;
import com.coderising.jvm.attr.CodeAttr;
import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.cmd.ByteCodeCommand;
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

    public String toString() {

        ConstantPool pool = this.clzFile.getConstantPool();
        StringBuilder buffer = new StringBuilder();

        String name = ((UTF8Info) pool.getConstantInfo(this.nameIndex)).getValue();

        String desc = ((UTF8Info) pool.getConstantInfo(this.descriptorIndex)).getValue();

        buffer.append(name).append(":").append(desc).append("\n");

        buffer.append(this.codeAttr.toString(pool));

        return buffer.toString();
    }

    /**
     * method {
     *     u2 access_flags;
     *     u2 name_index;
     *     u2 descriptor_index;
     *     u2 attributes_count;
     *     attribute_info attributes[attributes_count];
     * }
     *
     */
    public static Method parse(ClassFile clzFile, ByteCodeIterator iterator) {
        int accessFlag = iterator.nextU2ToInt();
        int nameIndex = iterator.nextU2ToInt();
        int descriptorIndex = iterator.nextU2ToInt();

        Method method = new Method(clzFile, accessFlag, nameIndex, descriptorIndex);

        int attributeCount = iterator.nextU2ToInt();
        for (int i = 0; i < attributeCount; i++) {
            int attributeNameIndex = iterator.nextU2ToInt();

            String attrName = clzFile.getConstantPool().getUTF8String(attributeNameIndex);
            iterator.back(2);

            if(AttributeInfo.CODE.equalsIgnoreCase(attrName)){
                CodeAttr codeAttr = CodeAttr.parse(clzFile, iterator);
                method.setCodeAttr(codeAttr);
            } else{
                throw new RuntimeException("only CODE attribute is implemented, this attribute name:"+ attrName);
            }
        }

        return method;
    }

    public ByteCodeCommand[] getCmds() {
        return this.getCodeAttr().getCmds();
    }
}
