package com.coderising.jvm.attr;

import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.loader.ByteCodeIterator;
import com.sun.org.apache.bcel.internal.classfile.Attribute;


public class CodeAttr extends AttributeInfo {
	private int maxStack ;
	private int maxLocals ;
	private int codeLen ;
	private String code;
	public String getCode() {
		return code;
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

	public void setLineNumberTable(LineNumberTable t) {
		this.lineNumTable = t;
	}

	public void setLocalVariableTable(LocalVariableTable t) {
		this.localVarTable = t;		
	}
	
	public static CodeAttr parse(ClassFile clzFile, ByteCodeIterator iter){
		int attrNameIndex = iter.nextU2toInt();
		int attrLen = iter.nextU4toInt();
		int maxStack = iter.nextU2toInt();
		int maxLocals = iter.nextU2toInt();
		int codeLen = iter.nextU4toInt();
		
		String code = iter.nextUxToHexString(codeLen);
		System.out.println("code:" + code);
		CodeAttr codeAttr = new CodeAttr(attrNameIndex, attrLen, maxStack, maxLocals, codeLen, code);
		
		int exceptionTableLen = iter.nextU2toInt();
		if (exceptionTableLen > 0) {
			String exTable = iter.nextUxToHexString(exceptionTableLen);
			//TODO  parse exception_table
			System.out.println("exception_table has not been parsed !");
			
		}
		
		int subAttrCount = iter.nextU2toInt();
		System.out.println("subAttrCount:"+subAttrCount);
		for (int i = 0; i < subAttrCount; i++) {
			int subAttrIndex = iter.nextU2toInt();
			String subAttrName = clzFile.getConstantPool().getUTF8String(subAttrIndex);
			System.out.println("subAttrName:" + subAttrName);
			
			iter.back(2);
			
			if (AttributeInfo.LINE_NUM_TABLE.equals(subAttrName)) {
				LineNumberTable t = LineNumberTable.parse(iter);
				codeAttr.setLineNumberTable(t);
				
			} else if (AttributeInfo.LOCAL_VAR_TABLE.equals(subAttrName)) {
				LocalVariableTable t = LocalVariableTable.parse(iter);
				codeAttr.setLocalVariableTable(t);
			} else if (AttributeInfo.STACK_MAP_TABLE.equals(subAttrName)) {
				StackMapTable t = StackMapTable.parse(iter);
				codeAttr.setStackMapTable(t);
			} else {
				throw new RuntimeException("Need to parse " + subAttrName);
			}
		}
		return codeAttr;
	}
	private void setStackMapTable(StackMapTable t) {
		this.stackMapTable = t;
		
	}

	
	
	
	
}
