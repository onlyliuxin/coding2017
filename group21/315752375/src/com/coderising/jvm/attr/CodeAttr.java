package com.coderising.jvm.attr;

import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.loader.ByteCodeIterator;


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
		int attrNameIndex=iter.nextU2ToInt();
		int attrLen=iter.nextU4ToInt();
		int maxStack=iter.nextU2ToInt();
		int maxLocals=iter.nextU2ToInt();
		int codeLen=iter.nextU4ToInt();

		String code=iter.nextUxToHexString(codeLen);
		System.out.println(code);
		CodeAttr codeAttr=new CodeAttr(attrNameIndex, attrLen, maxStack, maxLocals, codeLen, code);
		
		int exceptionTableLen=iter.nextU2ToInt();
		if(exceptionTableLen>0){
			String exceptionTable=iter.nextUxToHexString(exceptionTableLen);
			System.out.println("exception table has not implemented yet");
		}
		int subAttrCount=iter.nextU2ToInt();
		for(int i=0;i<subAttrCount;i++){
			int subAttrIndex=iter.nextU2ToInt();
			String subAttrNmae=clzFile.getConstantPool().getUTF8String(subAttrIndex);
			iter.back(2);
			if(AttributeInfo.LINE_NUM_TABLE.equalsIgnoreCase(subAttrNmae)){
				LineNumberTable lineNumberTable=LineNumberTable.parse(iter);
				codeAttr.setLineNumberTable(lineNumberTable);
			}
			else if(AttributeInfo.LOCAL_VAR_TABLE.equalsIgnoreCase(subAttrNmae)){
				LocalVariableTable localVariableTable=LocalVariableTable.parse(iter);
				codeAttr.setLocalVariableTable(localVariableTable);
			}
			else if(AttributeInfo.STACK_MAP_TABLE.equalsIgnoreCase(subAttrNmae)){
				StackMapTable stackMapTable=StackMapTable.parse(iter);
				codeAttr.setStackMapTable(stackMapTable);
			}else {
				throw new IllegalArgumentException("unprocessable");
			}
		}
		return codeAttr;
	}
	private void setStackMapTable(StackMapTable t) {
		this.stackMapTable = t;
		
	}

	
	
	
	
}
