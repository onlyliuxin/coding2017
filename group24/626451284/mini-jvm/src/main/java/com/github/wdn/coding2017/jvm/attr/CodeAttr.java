package com.github.wdn.coding2017.jvm.attr;

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
    public CodeAttr(int attributeNameIndex, int attributeLength, int maxStack, int maxLocals, String code) {
        super(attributeNameIndex, attributeLength);
        this.maxStack = maxStack;
        this.maxLocals = maxLocals;
        this.code = code;
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
}
