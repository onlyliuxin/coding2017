package com.github.wdn.coding2017.jvm.attr;

import com.github.wdn.coding2017.jvm.clz.ClassFile;
import com.github.wdn.coding2017.jvm.cmd.ByteCodeCommand;
import com.github.wdn.coding2017.jvm.cmd.CommandParser;
import com.github.wdn.coding2017.jvm.constant.ConstantPool;
import com.github.wdn.coding2017.jvm.loader.ByteCodeIterator;
import com.github.wdn.coding2017.jvm.util.Util;

/**
 * Created by Administrator on 2017/4/12 0012.
 */
public class CodeAttr extends AttributeInfo {
    private int maxStack;
    private int maxLocals;
    private String code;
    private LineNumberTable lineNumTable;
    private LocalVariableTable localVarTable;
    private StackMapTable stackMapTable;
    private ByteCodeCommand[] cmds;
    public CodeAttr(int attributeNameIndex, int attributeLength, int maxStack, int maxLocals, String code,ByteCodeCommand[] commands) {
        super(attributeNameIndex, attributeLength);
        this.maxStack = maxStack;
        this.maxLocals = maxLocals;
        this.code = code;
        this.cmds = commands;
    }
    public static CodeAttr parse(ClassFile clzFile, ByteCodeIterator iter){
        int attributeNameIndex = iter.readU2ToInt();
        int attributeLength = iter.readU4ToInt();
        int maxStack = iter.readU2ToInt();
        int maxLocals = iter.readU2ToInt();
        String codeStr = iter.readCustomToString(iter.readU4ToInt());
        ByteCodeCommand[] cmds = CommandParser.parse(clzFile,codeStr);
        CodeAttr codeAttr = new CodeAttr(attributeNameIndex,attributeLength, maxStack, maxLocals, codeStr, cmds);
        int ExceptionCount = iter.readU2ToInt();
        if (ExceptionCount > 0) {
            throw new RuntimeException("方法有异常待解析");
        }
        int codeAttributesCount = iter.readU2ToInt();
        for (int k = 0; k < codeAttributesCount; k++) {
            int codeAttributeNameIndex = iter.readU2ToInt();
            String codeAttributeType = clzFile.getConstantPool().getConstantInfo(codeAttributeNameIndex).getValue();
            codeAttributeType = Util.hexString2String(codeAttributeType);
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
        return codeAttr;
    }
    @Override
    public String toString(){
        return code;
    }
    public int getMaxStack() {
        return maxStack;
    }

    public void setMaxStack(int maxStack) {
        this.maxStack = maxStack;
    }

    public int getMaxLocals() {
        return maxLocals;
    }

    public void setMaxLocals(int maxLocals) {
        this.maxLocals = maxLocals;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setLineNumTable(LineNumberTable lineNumTable) {
        this.lineNumTable = lineNumTable;
    }

    public void setLocalVarTable(LocalVariableTable localVarTable) {
        this.localVarTable = localVarTable;
    }

    public void setStackMapTable(StackMapTable stackMapTable) {
        this.stackMapTable = stackMapTable;
    }

    public ByteCodeCommand[] getCmds() {
        return cmds;
    }
}
