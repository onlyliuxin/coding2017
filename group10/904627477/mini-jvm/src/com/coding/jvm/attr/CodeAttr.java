package com.coding.jvm.attr;

import com.coding.jvm.clz.ClassFile;
import com.coding.jvm.loader.ByteCodeIterator;



public class CodeAttr extends AttributeInfo {
	private int maxStack ;
	private int maxLocals ;
	private int codeLen ;
	private String code;
	public String getCode() {
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

	public int getCodeLen() {
		return codeLen;
	}

	public void setCodeLen(int codeLen) {
		this.codeLen = codeLen;
	}

	public LineNumberTable getLineNumTable() {
		return lineNumTable;
	}

	public void setLineNumTable(LineNumberTable lineNumTable) {
		this.lineNumTable = lineNumTable;
	}

	public LocalVariableTable getLocalVarTable() {
		return localVarTable;
	}

	public void setLocalVarTable(LocalVariableTable localVarTable) {
		this.localVarTable = localVarTable;
	}

	public StackMapTable getStackMapTable() {
		return stackMapTable;
	}

	public void setCode(String code) {
		this.code = code;
	}

	//private ByteCodeCommand[] cmds ;
	//public ByteCodeCommand[] getCmds() {
	//	return cmds;
	//}
	private LineNumberTable lineNumTable;
	private LocalVariableTable localVarTable;
	private StackMapTable stackMapTable;
	
	public CodeAttr(int attrNameIndex, int attrLen, int maxStack, int maxLocals, int codeLen,String code /*ByteCodeCommand[] cmds*/) {
		super(attrNameIndex, attrLen);
		this.maxStack = maxStack;
		this.maxLocals = maxLocals;
		this.codeLen = codeLen;
		this.code = code;
		//this.cmds = cmds;
	}
	
	public CodeAttr(int attrNameIndex, int attrLen) {
		super(attrNameIndex, attrLen);
	}

	public void setLineNumberTable(LineNumberTable t) {
		this.lineNumTable = t;
	}

	public void setLocalVariableTable(LocalVariableTable t) {
		this.localVarTable = t;		
	}
	
	public static CodeAttr parse(ClassFile clzFile, ByteCodeIterator iter){
		CodeAttr codeAttr = new CodeAttr(iter.nextU2ToInt(), iter.nextU4ToInt());
		codeAttr.setMaxStack(iter.nextU2ToInt());
		codeAttr.setMaxLocals(iter.nextU2ToInt());
		int codeLen = iter.nextU4ToInt();
		codeAttr.setCodeLen(codeLen);
		codeAttr.setCode(iter.nextUxToHexString(codeLen));
		int exceptionCount = iter.nextU2ToInt();
		for(int i=0;i<exceptionCount;i++){
			//TODO  待具体化处理
			AttributeInfo.parse(iter);
		}
		int attrCount = iter.nextU2ToInt();
		for(int i=0;i<attrCount;i++){
			//TODO  待具体化处理
			AttributeInfo.parse(iter);
		}
		return codeAttr;
	}
	private void setStackMapTable(StackMapTable t) {
		this.stackMapTable = t;
		
	}

	
	
	
	
}
