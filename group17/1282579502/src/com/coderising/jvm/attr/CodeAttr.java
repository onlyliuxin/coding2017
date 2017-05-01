package com.coderising.jvm.attr;

import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.loader.ByteCodeIterator;
import com.coderising.jvm.method.InvalidMethodInfoException;


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
	
	public static CodeAttr parse(ClassFile clzFile, ByteCodeIterator iter, int attributeNameIndex) throws InvalidAttributeInfoException{
		System.out.println("parsing CodeAttr...");
		
		int attributeLength = iter.getNextNBytesInteger(4);
		int maxStack = iter.getNextNBytesInteger(2);
		int maxLocalVar = iter.getNextNBytesInteger(2);
		int codeLength = iter.getNextNBytesInteger(4);
		
		
		System.out.println("attribute length: " + attributeLength);
		System.out.println("max stack: " + maxStack);
		System.out.println("max local variable: " + maxLocalVar);
		System.out.println("code length: " + codeLength);
		
		String realCode = iter.getNextNHexString(codeLength);
		System.out.println("real cdoe: " + realCode);
		
		int exceptionCount = iter.getNextNBytesInteger(2);
		System.out.println("exception count: " + exceptionCount);
		if(exceptionCount>0){
			throw new InvalidAttributeInfoException("Exception parser un-implemented.");
		}
		//int attrNameIndex, int attrLen, int maxStack, int maxLocals, int codeLen,String code
		CodeAttr code = new CodeAttr(attributeNameIndex, attributeLength, maxStack, maxLocalVar, codeLength, realCode);

		int subAttributeCount = iter.getNextNBytesInteger(2);
		System.out.println("sub attribute count : " + subAttributeCount); 
		for(int i = 0; i<subAttributeCount; i++){
			AttributeInfo attInfo = AttributeInfo.parse(clzFile, iter);
			if(attInfo instanceof LineNumberTable){
				code.setLineNumberTable((LineNumberTable)attInfo);
			}
			else if(attInfo instanceof LocalVariableTable){
				code.setLocalVariableTable((LocalVariableTable) attInfo);
			}
			else{
				throw new InvalidAttributeInfoException("Unimplemented attribute type.");
			}
		}
				
		return code;
	}
	private void setStackMapTable(StackMapTable t) {
		this.stackMapTable = t;
		
	}

	
	
	
	
}
