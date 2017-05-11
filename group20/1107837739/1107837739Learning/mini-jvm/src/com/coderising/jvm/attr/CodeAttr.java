package com.coderising.jvm.attr;

import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.loader.ByteCodeIterator;

public class CodeAttr extends AttributeInfo {
    private int maxStack;
    private int maxLocals;
    private int codeLen;
    private String code;
    //private ByteCodeCommand[] cmds ;
    //public ByteCodeCommand[] getCmds() {
    //	return cmds;
    //}
    private LineNumberTable lineNumTable;
    private LocalVariableTable localVarTable;
    private StackMapTable stackMapTable;

    public CodeAttr(int attrNameIndex, int attrLen, int maxStack, int maxLocals, int codeLen, String code /*ByteCodeCommand[] cmds*/) {
        super(attrNameIndex, attrLen);
        this.maxStack = maxStack;
        this.maxLocals = maxLocals;
        this.codeLen = codeLen;
        this.code = code;
        //this.cmds = cmds;
    }

    public static CodeAttr parse(ClassFile clzFile, ByteCodeIterator iter) {
        int attrNameIndex = iter.nextU2ToInt();
        int attrLen = iter.nextU4ToInt();
        int maxStack = iter.nextU2ToInt();
        int maxLocals = iter.nextU2ToInt();
        int codeLen = iter.nextU4ToInt();

        String code = iter.nextUxToHexString(codeLen);
        System.out.println("Code: " + code);

        CodeAttr codeAttr = new CodeAttr(attrNameIndex, attrLen, maxStack, maxLocals, codeLen, code);

        int exceptionCodeLen = iter.nextU2ToInt();
        if (exceptionCodeLen > 0) {
            String exTable = iter.nextUxToHexString(exceptionCodeLen);
            System.out.println("Encountered exception table, just ignore it");
        }

        int subAttrCount = iter.nextU2ToInt();

        for (int i = 0; i < subAttrCount; i++) {
            int subAttrIndex = iter.nextU2ToInt();
            String subAttrName = clzFile.getConstantPool().getUTF8String(subAttrIndex);

            iter.back(2);

            if (AttributeInfo.LINE_NUM_TABLE.equalsIgnoreCase(subAttrName)) {
                LineNumberTable t = LineNumberTable.parse(iter);
                codeAttr.setLineNumberTable(t);
            } else if (AttributeInfo.LOCAL_VAR_TABLE.equalsIgnoreCase(subAttrName)) {
                LocalVariableTable t = LocalVariableTable.parse(iter);
                codeAttr.setLocalVariableTable(t);
            } else if (AttributeInfo.STACK_MAP_TABLE.equalsIgnoreCase(subAttrName)) {
                StackMapTable t = StackMapTable.parse(iter);
                codeAttr.setStackMapTable(t);
            } else {
                throw new RuntimeException(subAttrName + " hasn't been implemented yet");
            }
        }

        return codeAttr;
    }

    public String getCode() {
        return code;
    }

    public void setLineNumberTable(LineNumberTable t) {
        this.lineNumTable = t;
    }

    public void setLocalVariableTable(LocalVariableTable t) {
        this.localVarTable = t;
    }

    private void setStackMapTable(StackMapTable t) {
        this.stackMapTable = t;
    }
}
