package com.coderising.jvm.attr;

import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.cmd.ByteCodeCommand;
import com.coderising.jvm.cmd.CommandParser;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.loader.ByteCodeIterator;

/**
 * Created by xudanxia on 2017/4/11.
 */
public class CodeAttr extends AttributeInfo {

    private int maxStack;

    private int maxLocals;

    private int codeLen;

    private String code;

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

    public String getCode() {
        return code;
    }

    public void setLineNumberTable(LineNumberTable t) {
        this.lineNumTable = t;
    }

    public void setLocalVariableTable(LocalVariableTable t) {
        this.localVarTable = t;
    }

    public static CodeAttr parse(ClassFile clzFile, ByteCodeIterator iter) {

        int attrNameIndex = iter.nextU2toInt();
        int attrLength = iter.nextU4ToInt();
        int maxStack = iter.nextU2toInt();
        int maxLocals = iter.nextU2toInt();
        int codeLength = iter.nextU4ToInt();
        String code = iter.nextUxToHexString(codeLength);
        ByteCodeCommand[] cmds = CommandParser.parse(clzFile, code);
        CodeAttr codeAttr = new CodeAttr(attrNameIndex, attrLength, maxStack, maxLocals, codeLength, code, cmds);

        int exceptionLen = iter.nextU2toInt();
        // TODO 此处应有处理异常的代码
        if (exceptionLen > 0) {
            String exTable = iter.nextUxToHexString(exceptionLen);
            System.out.println("异常表内容, 暂不处理: " + exTable);
        }
        int subAttrCount = iter.nextU2toInt();
        for (int i = 0; i < subAttrCount; i++) {
            int subAttrIndex = iter.nextU2toInt();
            String subAttrName = clzFile.getConstantPool().getUTF8String(subAttrIndex);
            iter.back(2);
            if (AttributeInfo.LINE_NUM_TABLE.equalsIgnoreCase(subAttrName)) {
                LineNumberTable lineNumberTable = LineNumberTable.parse(iter);
                codeAttr.setLineNumberTable(lineNumberTable);
            } else if (AttributeInfo.LOCAL_VAR_TABLE.equalsIgnoreCase(subAttrName)) {
                LocalVariableTable localVariableTable = LocalVariableTable.parse(iter);
                codeAttr.setLocalVariableTable(localVariableTable);
            } else if (AttributeInfo.STACK_MAP_TABLE.equalsIgnoreCase(subAttrName)) {
                StackMapTable stackMapTable = StackMapTable.parse(iter);
                codeAttr.setStackMapTable(stackMapTable);
            } else {
                throw new RuntimeException("需要实现的code属性: " + subAttrName);
            }

        }

        return codeAttr;
    }

    private void setStackMapTable(StackMapTable t) {
        this.stackMapTable = t;

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

}