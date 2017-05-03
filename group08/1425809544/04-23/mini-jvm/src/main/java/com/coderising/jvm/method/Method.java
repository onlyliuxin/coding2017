package com.coderising.jvm.method;

import com.coderising.jvm.attr.AttributeInfo;
import com.coderising.jvm.attr.CodeAttr;
import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.constant.UTF8Info;
import com.coderising.jvm.loader.ByteCodeIterator;

/**
 * 方法
 *
 * @author xyy
 * @create 2017-04-14 17:18
 **/
public class Method {


    private int accessFlag;
    private int nameIndex;
    private int descriptorIndex;

    private CodeAttr codeAtrr;
    private ClassFile classFile;

    public int getAccessFlag() {
        return accessFlag;
    }

    public Method setAccessFlag(int accessFlag) {
        this.accessFlag = accessFlag;
        return this;
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public Method setNameIndex(int nameIndex) {
        this.nameIndex = nameIndex;
        return this;
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }

    public Method setDescriptorIndex(int descriptorIndex) {
        this.descriptorIndex = descriptorIndex;
        return this;
    }

    public CodeAttr getCodeAtrr() {
        return codeAtrr;
    }

    public Method setCodeAtrr(CodeAttr codeAtrr) {
        this.codeAtrr = codeAtrr;
        return this;
    }

    public ClassFile getClassFile() {
        return classFile;
    }

    public Method setClassFile(ClassFile classFile) {
        this.classFile = classFile;
        return this;
    }


    public Method(int accessFlag, int nameIndex, int descriptorIndex, ClassFile classFile) {
        this.accessFlag = accessFlag;
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
        this.classFile = classFile;
    }

    public static Method parse(ClassFile clzFile, ByteCodeIterator iterator){
        int accessFlag = iterator.nextU2ToInt();
        int nameIndex = iterator.nextU2ToInt();
        int descriptorIndex = iterator.nextU2ToInt();
        int arrtibutesCount = iterator.nextU2ToInt();

        Method method = new Method(accessFlag, nameIndex, descriptorIndex, clzFile);
        for (int i = 0; i < arrtibutesCount; i++) {
            int attrNameIndex = iterator.nextU2ToInt();
            String attrName = clzFile.getConstantPool().getUTF8String(attrNameIndex);
            iterator.back(2);

            if (AttributeInfo.CODE.equals(attrName)) {
                CodeAttr codeAttr = CodeAttr.parse(clzFile, iterator);
                method.setCodeAtrr(codeAttr);
            }else {
                throw new RuntimeException("只实现了code属性,还没有实现这个属性: "+ attrName);
            }
        }
        return method;

    }


    public String toString() {

        ConstantPool pool = this.classFile.getConstantPool();
        StringBuilder buffer = new StringBuilder();

        String name = ((UTF8Info)pool.getConstantInfo(this.nameIndex)).getValue();

        String desc = ((UTF8Info)pool.getConstantInfo(this.descriptorIndex)).getValue();

        buffer.append(name).append(":").append(desc).append("\n");

        buffer.append(this.codeAtrr.toString(pool));

        return buffer.toString();
    }
}
