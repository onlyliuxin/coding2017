package com.coderising.jvm.attr;

import org.junit.Assert;

import com.coderising.jvm.cmd.ByteCodeCommand;
import com.coderising.jvm.cmd.CommandParser;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.loader.ByteCodeIterator;

public class CodeAttr extends AttributeInfo{
	
	private int maxStack ;
	private int maxLocals ;
	private int codeLength ;
	private String code;
	private LineNumberTable lineNumTable;
	private LocalVariableTable localVariableTable;
	private ByteCodeCommand[] cmds;
	public void setCmds(ByteCodeCommand[] cmds) {
		this.cmds = cmds;
	}
	public LocalVariableTable getLocalVariableTable() {
		return localVariableTable;
	}
	public void setLocalVariableTable(LocalVariableTable localVariableTable) {
		this.localVariableTable = localVariableTable;
	}
	public CodeAttr(int attrNameIndex, int attrLength) {
		super(attrNameIndex, attrLength);
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
	public int getCodeLength() {
		return codeLength;
	}
	public void setCodeLength(int codeLength) {
		this.codeLength = codeLength;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public LineNumberTable getLineNumTable() {
		return lineNumTable;
	}
	public void setLineNumTable(LineNumberTable lineNumTable) {
		this.lineNumTable = lineNumTable;
	}
	public static CodeAttr parse(ByteCodeIterator it, ConstantPool constantPool) {
		int attrNameIndex = it.next2ByteToInt();
		int attrLength = it.next4ByteToInt();
		int maxStack = it.next2ByteToInt();
		int maxLocals = it.next2ByteToInt();
		int codeLength = it.next4ByteToInt();
		String code = it.nextXByteToHexStr(codeLength);
		ByteCodeCommand[] cmds = CommandParser.parse(constantPool, code);
		
		int exceptionTableLength = it.next2ByteToInt();
		Assert.assertEquals(0, exceptionTableLength);
		
		CodeAttr codeAttr = new CodeAttr(attrNameIndex, attrLength);
		codeAttr.setMaxStack(maxStack);
		codeAttr.setMaxLocals(maxLocals);
		codeAttr.setCodeLength(codeLength);
		codeAttr.setCode(code);
		codeAttr.setCmds(cmds);
		
		int subAttrCount = it.next2ByteToInt();
		while(subAttrCount > 0) {
			int subAttrNameIndex = it.next2ByteToInt();
			String subAttrName = constantPool.getUTF8String(subAttrNameIndex);
			if(AttributeInfo.ATTR_LINE_NUMBER_TABLE.equals(subAttrName)) { //LineNumberTable
				it.back(2);
				codeAttr.setLineNumTable(LineNumberTable.parse(it, constantPool));
			} else if (AttributeInfo.ATTR_LOCAL_VARIABLE_TABLE.equals(subAttrName)) { //LocalVariableTable
				it.back(2);
				codeAttr.setLocalVariableTable(LocalVariableTable.parse(it, constantPool));
			} else {
				throw new RuntimeException("no implements attrName: " + subAttrName);
			}
			subAttrCount--;
		}
		return codeAttr;
	}
	
	public ByteCodeCommand[] getCmds() {
		return this.cmds;
	}

}
