package com.github.HarryHook.coding2017.jvm.attr;

import com.github.HarryHook.coding2017.jvm.clz.ClassFile;
import com.github.HarryHook.coding2017.jvm.cmd.ByteCodeCommand;
import com.github.HarryHook.coding2017.jvm.cmd.CommandParser;
import com.github.HarryHook.coding2017.jvm.constant.ConstantPool;
import com.github.HarryHook.coding2017.jvm.loader.ByteCodeIterator;
import com.sun.org.apache.bcel.internal.generic.NEW;

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

    public CodeAttr(int attrNameIndex, int attrLen, int maxStack, int maxLocals, int codeLen, String code,
	    ByteCodeCommand[] cmds) {
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

    public static CodeAttr parse(ClassFile clzFile, ByteCodeIterator iter) {

	int attrNameIndex = iter.nextU2ToInt();
	int attrLen = iter.nextU4ToInt();
	int maxStack = iter.nextU2ToInt();
	int maxLocals = iter.nextU2ToInt();
	int codeLen = iter.nextU4ToInt();

	String code = iter.nextUxToHexString(codeLen);
	System.out.println(code);

	ByteCodeCommand[] cmds = CommandParser.parse(clzFile, code);
	CodeAttr codeAttr = new CodeAttr(attrNameIndex, attrLen, maxStack, maxLocals, codeLen, code, cmds);

	int exceptionLength = iter.nextU2ToInt();
	if (exceptionLength > 0) {
	    String exceptionTable = iter.nextUxToHexString(exceptionLength);
	    System.out.println("exception Table has not complemented" + exceptionTable);
	}
	// 解析子属性
	int subAttrCount = iter.nextU2ToInt();

	for (int j = 1; j <= subAttrCount; j++) {

	    int subAttrIndex = iter.nextU2ToInt();
	    String subAttrName = clzFile.getConstantPool().getUTF8String(subAttrIndex);
	    iter.back(2);

	    if (AttributeInfo.LINE_NUM_TABLE.equalsIgnoreCase(subAttrName)) {
		LineNumberTable t = LineNumberTable.parse(iter);
		codeAttr.setLineNumberTable(t);

	    } else if (AttributeInfo.LOCAL_VAR_TABLE.equals(subAttrName)) {
		LocalVariableTable t = LocalVariableTable.parse(iter);
		codeAttr.setLocalVariableTable(t);

	    } else if (AttributeInfo.STACK_MAP_TABLE.equalsIgnoreCase(subAttrName)) {
		StackMapTable t = StackMapTable.parse(iter);
		codeAttr.setStackMapTable(t);
	    } else {
		throw new RuntimeException("Need implement" + subAttrName);
	    }
	}
	return codeAttr;
    }

    private void setStackMapTable(StackMapTable t) {
	this.stackMapTable = t;

    }

    public String toString(ConstantPool pool) {
	StringBuffer buffer = new StringBuffer();
	for(int i=0;i<cmds.length;i++){
		buffer.append(cmds[i].toString(pool)).append("\n");
	}
	//buffer.append("Code:").append(code).append("\n");
	buffer.append("\n");
	buffer.append(this.lineNumTable.toString());
	buffer.append(this.localVarTable.toString(pool));
	return buffer.toString();
    }

}