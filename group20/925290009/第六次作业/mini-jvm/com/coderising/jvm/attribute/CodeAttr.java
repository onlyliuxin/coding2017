package com.coderising.jvm.attribute;

import com.coderising.jvm.clasfile.ClassFile;
import com.coderising.jvm.constant.Utf8Info;
import com.coderising.jvm.loader.ByteCodeIterator;

public class CodeAttr extends AttributeInfo{

	private int attrLen; 
	private int maxStack;
	private int maxLocals; 
	private int codeLen;
	private String code;
	
	private LineNumberTable lineNumTable;
	private LocalVariableTable localVarTable;
	private StackMapTable stackMapTable;
	
	public CodeAttr(int attrNameIndex, int attrLen, int maxStack, int maxLocals, int codeLen,String code) {
		super(attrNameIndex, attrLen);
		this.maxLocals = maxLocals;
		this.maxStack = maxStack;
		this.code = code;
		this.codeLen = codeLen;
	}
	
	public String getCodeString(){
		return this.code;
	}

	public static CodeAttr parse(ClassFile classFile, ByteCodeIterator iterator) {

		iterator.back(2);
		int attrNameIndex = iterator.next2BytesToInt();
		int attrLen = iterator.next4BytesToInt();
		int maxstack = iterator.next2BytesToInt();
		int maxlocals = iterator.next2BytesToInt();
		int codelen = iterator.next4BytesToInt();
		String code = iterator.nextXBytesToString(codelen);
		
		CodeAttr codeAttr = new CodeAttr(attrNameIndex, attrLen, maxstack, maxlocals, codelen, code);
		
		int exceptionCount = iterator.next2BytesToInt();
		if (exceptionCount > 0) {
			throw new RuntimeException("Exception is null." + exceptionCount);
		}
		int childCount = iterator.next2BytesToInt();
		if (childCount > 0) {
			for (int i = 0; i < childCount; i++) {
				
				int childIndex = iterator.next2BytesToInt();
				String attrName = classFile.getPool().getUtf8String(childIndex);
				
				if (AttributeInfo.LINE_NUM_TABLE.equals(attrName)) {
					LineNumberTable lineNumber = LineNumberTable.parse(iterator);
					codeAttr.setLineNumTable(lineNumber);
				}
				else if(AttributeInfo.LOCAL_VAR_TABLE.equals(attrName)){
					LocalVariableTable localVarTable = LocalVariableTable.parse(classFile,iterator);
					codeAttr.setLocalVarTable(localVarTable);
				}
				else if(AttributeInfo.STACK_MAP_TABLE.equals(attrName)){
					StackMapTable stackMapTable = StackMapTable.parse(classFile,iterator);
					codeAttr.setStackMapTable(stackMapTable);
				}
				else {
					throw new RuntimeException("This " + attrName +"is not added.");
				}
			}
		}

		return codeAttr;
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
