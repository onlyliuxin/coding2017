package com.coding2017.jvm.attr;

import com.coding2017.jvm.clz.ClassFile;
import com.coding2017.jvm.loader.ByteCodeIterator;

public class CodeAttr extends AttributeInfo {
    private int maxStack;
    private int maxLocals;
    private int codeLen;
    private String code;

    public String getCode() {
        return code;
    }

    // private ByteCodeCommand[] cmds ;
    // public ByteCodeCommand[] getCmds() {
    // return cmds;
    // }
    private LineNumberTable lineNumTable;
    private LocalVariableTable localVarTable;
    private StackMapTable stackMapTable;

    public CodeAttr(int attrNameIndex, int attrLen, int maxStack, int maxLocals, int codeLen,
            String code /* ByteCodeCommand[] cmds */) {
        super(attrNameIndex, attrLen);
        this.maxStack = maxStack;
        this.maxLocals = maxLocals;
        this.codeLen = codeLen;
        this.code = code;
        // this.cmds = cmds;
    }

    public void setLineNumberTable(LineNumberTable t) {
        this.lineNumTable = t;
    }

    public void setLocalVariableTable(LocalVariableTable t) {
        this.localVarTable = t;
    }

    public static CodeAttr parse(ClassFile clzFile, ByteCodeIterator iter, int nameIndex, int length) {
        int maxStack = iter.nextU2ToInt();
        int maxLocals = iter.nextU2ToInt();
        int codeLength = iter.nextU4ToInt();
        String code = iter.nextUxToHexString(codeLength);
        CodeAttr codeAttr = new CodeAttr(nameIndex, length, maxStack, maxLocals, codeLength, code);
        int exceptionTableLength = iter.nextU2ToInt();
        if(exceptionTableLength > 0){
            String exTable = iter.nextUxToHexString(exceptionTableLength * 8);
            System.out.println("Encountered exception table , just ignore it :" + exTable);

        }
        int codeAttributeCount = iter.nextU2ToInt();
        for (int j = 0; j < codeAttributeCount; j++) {
            AttributeInfo attributeInfo = AttributeInfo.parse(clzFile, iter);
            if (attributeInfo instanceof LineNumberTable) {
                codeAttr.setLineNumberTable((LineNumberTable) attributeInfo);
            } else if (attributeInfo instanceof LocalVariableTable) {
                codeAttr.setLocalVariableTable((LocalVariableTable) attributeInfo);
            } else if (attributeInfo instanceof StackMapTable) {
                codeAttr.setStackMapTable((StackMapTable) attributeInfo);
            }
        }
        return codeAttr;
    }

    private void setStackMapTable(StackMapTable t) {
        this.stackMapTable = t;

    }

}
