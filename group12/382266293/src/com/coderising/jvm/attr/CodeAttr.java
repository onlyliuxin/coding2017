package com.coderising.jvm.attr;

import java.util.Arrays;

import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.cmd.ByteCodeCommand;
import com.coderising.jvm.cmd.CommandParser;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.loader.ByteCodeIterator;


public class CodeAttr extends AttributeInfo {
	public static CodeAttr parse(ClassFile clzFile, ByteCodeIterator iter){
		 int attrNameIndex = iter.nextU2ToInt();
		 
		 int attrLen = iter.nextU4ToInt();

		 int maxStack = iter.nextU2ToInt();
		 
		 int maxLocals = iter.nextU2ToInt();
		 
		 int codeLen = iter.nextU4ToInt();
		 
		 System.out.println("codeLen " + codeLen);
		 
		 String code = iter.nextUxToHexString(codeLen);

		 ByteCodeCommand[] cmds = CommandParser.parse(clzFile, code);
		 CodeAttr codeAttr = new CodeAttr(attrNameIndex, attrLen, maxStack, maxLocals, codeLen, code, cmds);

		System.out.println("Code is " + code);
		System.out.println(Arrays.toString(cmds));
		 
		 int exceptionTableLen = iter.nextU2ToInt();
		 
		 if (exceptionTableLen > 0) {
			 
			 String exTable = iter.nextUxToHexString(exceptionTableLen);
			 
			 System.out.println("exception encounted " + exTable);
		 }
		
		 int sub_attrNum = iter.nextU2ToInt();
		 
		 for (int i = 0; i < sub_attrNum; i++) {
			 
			 int subAttrNameIndex = iter.nextU2ToInt();
			 String subAttrName = clzFile.getConstantPool().getUTF8String(subAttrNameIndex);
			 System.out.println("subAttrNameIndex is " + subAttrName);
			 iter.back(2);
			 
			 if (AttributeInfo.LINE_NUM_TABLE.equalsIgnoreCase(subAttrName)) {
				 
				 LineNumberTable lnt = LineNumberTable.parse(iter);
				 codeAttr.setLineNumberTable(lnt);
						 
			 } else if (AttributeInfo.LOCAL_VAR_TABLE.equalsIgnoreCase(subAttrName)) {
				 
				 LocalVariableTable lvt = LocalVariableTable.parse(iter);
				 codeAttr.setLocalVariableTable(lvt);
				 
			 } else if (AttributeInfo.STACK_MAP_TABLE.equalsIgnoreCase(subAttrName)) {
				 
				 StackMapTable smt =  StackMapTable.parse(iter);
				 codeAttr.setStackMapTable(smt);

			 } else {
				 throw new RuntimeException(subAttrName + "not implemented yet");
			 }
			 
		 }
		 
		 return codeAttr;
	}
	private int maxStack ;
	private int maxLocals ;
	private int codeLen ;
	private String code;

	private LineNumberTable lineNumTable;
	private LocalVariableTable localVarTable;
	private StackMapTable stackMapTable;
	
	public CodeAttr(int attrNameIndex, int attrLen, int maxStack, int maxLocals, int codeLen,String code ,ByteCodeCommand[] cmds) {
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
	
	
	private void setStackMapTable(StackMapTable t) {
		this.stackMapTable = t;
		
	}

	private ByteCodeCommand[] cmds ;
	public ByteCodeCommand[] getCmds() {		
		return cmds;
	}

	public String toString(ConstantPool pool){
		StringBuilder buffer = new StringBuilder();
		//buffer.append("Code:").append(code).append("\n");
		for(int i=0;i<cmds.length;i++){
			buffer.append(cmds[i].toString(pool)).append("\n");
		}
		buffer.append("\n");
		buffer.append(this.lineNumTable.toString());
		buffer.append(this.localVarTable.toString(pool));
		return buffer.toString();
	}
	
	
}
