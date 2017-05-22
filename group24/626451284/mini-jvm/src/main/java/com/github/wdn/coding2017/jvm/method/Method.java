package com.github.wdn.coding2017.jvm.method;

import com.github.wdn.coding2017.jvm.attr.*;
import com.github.wdn.coding2017.jvm.clz.AccessFlag;
import com.github.wdn.coding2017.jvm.clz.ClassFile;
import com.github.wdn.coding2017.jvm.cmd.ByteCodeCommand;
import com.github.wdn.coding2017.jvm.constant.ConstantPool;
import com.github.wdn.coding2017.jvm.loader.ByteCodeIterator;
import com.github.wdn.coding2017.jvm.util.Util;

/**
 * Created by Administrator on 2017/4/10 0010.
 */
public class Method {
    private AccessFlag accessFlags;
    private int nameIndex;
    private int descriptorIndex;
    private CodeAttr codeAttr;
    //attributes[attributes_count];
    private ConstantPool pool;
    public static Method parse(ClassFile clzFile, ByteCodeIterator iter) {
        Method method = new Method();
        method.setAccessFlags(new AccessFlag(iter.readU2ToInt()));
        method.setNameIndex(iter.readU2ToInt());
        method.setDescriptorIndex(iter.readU2ToInt());
        int methodAttributesCount = iter.readU2ToInt();
        for (int j = 0; j < methodAttributesCount; j++) {
            int methodAttributeNameIndex = iter.readU2ToInt();
            String methodAttributeType = clzFile.getConstantPool().getConstantInfo(methodAttributeNameIndex).getValue();
            methodAttributeType = Util.hexString2String(methodAttributeType);
            iter.back(2);
            if (methodAttributeType.equals(AttributeInfo.CODE)) {
                method.setCodeAttr(CodeAttr.parse(clzFile,iter));
            } else {
                throw new RuntimeException("未知的方法属性类型" + methodAttributeType);
            }
        }
        return method;
    }
    @Override
    public String toString(){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(pool.getConstantInfo(nameIndex).getValue());
        stringBuffer.append(pool.getConstantInfo(descriptorIndex).getValue());
        stringBuffer.append(codeAttr);
        return stringBuffer.toString();
    }
    public AccessFlag getAccessFlags() {
        return accessFlags;
    }

    public void setAccessFlags(AccessFlag accessFlags) {
        this.accessFlags = accessFlags;
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public void setNameIndex(int nameIndex) {
        this.nameIndex = nameIndex;
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }

    public void setDescriptorIndex(int descriptorIndex) {
        this.descriptorIndex = descriptorIndex;
    }

    public CodeAttr getCodeAttr() {
        return codeAttr;
    }

    public void setCodeAttr(CodeAttr codeAttr) {
        this.codeAttr = codeAttr;
    }

    public void setPool(ConstantPool pool) {
        this.pool = pool;
    }

}
