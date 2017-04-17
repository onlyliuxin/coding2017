package com.github.wdn.coding2017.jvm.clz;

import com.github.wdn.coding2017.jvm.attr.*;
import com.github.wdn.coding2017.jvm.constant.ConstantPool;
import com.github.wdn.coding2017.jvm.loader.ByteCodeIterator;

/**
 * Created by Administrator on 2017/4/10 0010.
 */
public class Method {
    private AccessFlag accessFlags;
    private int nameIndex;
    private int descriptorIndex;
    private CodeAttr code;
    //attributes[attributes_count];
    private ConstantPool pool;
    public static Method parse(ConstantPool pool, ByteCodeIterator iter) {
        Method method = new Method();
        method.setAccessFlags(new AccessFlag(iter.readU2ToInt()));
        method.setNameIndex(iter.readU2ToInt());
        method.setDescriptorIndex(iter.readU2ToInt());
        int methodAttributesCount = iter.readU2ToInt();
        for (int j = 0; j < methodAttributesCount; j++) {
            int methodAttributeNameIndex = iter.readU2ToInt();
            String methodAttributeType = pool.getConstantInfo(methodAttributeNameIndex).getValue();
            if (methodAttributeType.equals(AttributeInfo.CODE)) {
                CodeAttr codeAttr = new CodeAttr(methodAttributeNameIndex, iter.readU4ToInt(), iter.readU2ToInt(), iter.readU2ToInt(), iter.readCustomToString(iter.readU4ToInt()));
                int ExceptionCount = iter.readU2ToInt();
                if (ExceptionCount > 0) {
                    throw new RuntimeException("方法有异常待解析");
                }
                int codeAttributesCount = iter.readU2ToInt();
                for (int k = 0; k < codeAttributesCount; k++) {
                    int codeAttributeNameIndex = iter.readU2ToInt();
                    String codeAttributeType = pool.getConstantInfo(codeAttributeNameIndex).getValue();
                    if ("LineNumberTable".equals(codeAttributeType)) {
                        LineNumberTable lineNumberTable = LineNumberTable.parse(iter);
                        codeAttr.setLineNumTable(lineNumberTable);
                    } else if ("LocalVariableTable".equals(codeAttributeType)) {
                        LocalVariableTable localVariableTable = LocalVariableTable.parse(iter);
                        codeAttr.setLocalVarTable(localVariableTable);
                    }else if ("StackMapTable".equals(codeAttributeType)) {
                        StackMapTable stackMapTable = StackMapTable.parse(iter);
                        codeAttr.setStackMapTable(stackMapTable);
                    } else {
                        throw new RuntimeException("未知的Code附加属性类型" + codeAttributeType);
                    }
                }
                method.setCode(codeAttr);
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
        stringBuffer.append(code);
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

    public CodeAttr getCode() {
        return code;
    }

    public void setCode(CodeAttr code) {
        this.code = code;
    }

    public void setPool(ConstantPool pool) {
        this.pool = pool;
    }
}
