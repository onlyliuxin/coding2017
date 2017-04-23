package com.coderising.jvm.attr;

import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.cmd.ByteCodeCommand;
import com.coderising.jvm.cmd.CommandParser;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.loader.ByteCodeIterator;


public class CodeAttr extends AttributeInfo {
    private int maxStack;
    private int maxLocals;
    private int codeLen;
    private String code;

    public String getCode() {
        return code;
    }

    private ByteCodeCommand[] cmds;

    public ByteCodeCommand[] getCmds() {
        return cmds;
    }

    private LineNumberTable lineNumTable;
    private LocalVariableTable localVarTable;
    private StackMapTable stackMapTable;

    public CodeAttr(int attrNameIndex, int attrLen, int maxStack, int maxLocals, int codeLen, String code, ByteCodeCommand[] cmds) {
        super(attrNameIndex, attrLen);
        this.maxStack = maxStack;
        this.maxLocals = maxLocals;
        this.codeLen = codeLen;
        this.code = code;
        this.cmds = cmds;
    }

    public void setLineNumberTable(LineNumberTable t) {
        this.lineNumTable = t;
    }

    public void setLocalVariableTable(LocalVariableTable t) {
        this.localVarTable = t;
    }

    /*
     * u2 attribute_name_index;
     * u4 attribute_length;
     * u2 max_stack;
     * u2 max_locals;
     * u4 code_length;
     * u1 code[code_length];
     * u2 exception_table_length;
     * {
     *     u2 start_pc;
     *     u2 end_pc;
     *     u2 handler_pc;
     *     u2 catch_type;
     * } exception_table[exception_table_length];
     *
     * u2 attributes_count;
     * attribute_info attributes[attributes_count];
     */
    public static CodeAttr parse(ClassFile clzFile, ByteCodeIterator iterator) {
        ConstantPool constantPool = clzFile.getConstantPool();

        int attributeNameIndex = iterator.nextU2ToInt();
        int attributeLength = iterator.nextU4ToInt();
        int maxStack = iterator.nextU2ToInt();
        int maxLocals = iterator.nextU2ToInt();
        int codeLength = iterator.nextU4ToInt();

        String code = iterator.nextUxToHexString(codeLength);

        CodeAttr codeAttr = new CodeAttr(attributeNameIndex, attributeLength, maxStack, maxLocals, codeLength, code, CommandParser.parse(clzFile, code));

        // TODO 目前为0
        int exceptionTableLength = iterator.nextU2ToInt();
        if (exceptionTableLength > 0) throw new RuntimeException("doesn't implemented parse Exception");

        int subAttributeLength = iterator.nextU2ToInt();
        for (int i = 0; i < subAttributeLength; i++) {
            int attributeIndex = iterator.nextU2ToInt();
            String attribute = constantPool.getUTF8String(attributeIndex);
            iterator.back(2);
            if (AttributeInfo.LINE_NUM_TABLE.equalsIgnoreCase(attribute)) {
                codeAttr.setLineNumberTable(LineNumberTable.parse(iterator));
            } else if (AttributeInfo.STACK_MAP_TABLE.equals(attribute)) {
                codeAttr.setStackMapTable(StackMapTable.parse(iterator));
            } else if (AttributeInfo.LOCAL_VAR_TABLE.equals(attribute)) {
                codeAttr.setLocalVariableTable(LocalVariableTable.parse(iterator));
            }
        }

        return codeAttr;
    }

    public String toString(ConstantPool pool) {
        StringBuilder buffer = new StringBuilder();
        //buffer.append("Code:").append(code).append("\n");
        for (int i = 0; i < cmds.length; i++) {
            buffer.append(cmds[i].toString(pool)).append("\n");
        }
        buffer.append("\n");
        buffer.append(this.lineNumTable.toString());
        buffer.append(this.localVarTable.toString(pool));
        return buffer.toString();
    }

    private void setStackMapTable(StackMapTable t) {
        this.stackMapTable = t;

    }


}
