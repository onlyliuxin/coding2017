package com.github.ipk2015.coding2017.minijvm.attr;

import com.github.ipk2015.coding2017.minijvm.clz.ClassFile;
import com.github.ipk2015.coding2017.minijvm.loader.ByteCodeIterator;

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
		int attrNameIndex = iter.nextUNToInt(2);
		int attrLen = iter.nextUNToInt(4);
		int maxStack = iter.nextUNToInt(2); 
		int maxLocals = iter.nextUNToInt(2); 
		int codeLen = iter.nextUNToInt(4);
		String code = iter.nextUNToHexString(codeLen);
		CodeAttr codeAttr = new CodeAttr(attrNameIndex,attrLen,maxStack,maxLocals,codeLen,code);
		int exceptionTableLen = iter.nextUNToInt(2);
		if(exceptionTableLen != 0){
			throw new RuntimeException("code属性里的异常table长度为："+exceptionTableLen);
		}
		int attrCount = iter.nextUNToInt(2);
		for(int i = 0;i<attrCount;i++){
			addSonAttr(clzFile,codeAttr,iter);
		}
		return codeAttr;
	}
	private void setStackMapTable(StackMapTable t) {
		this.stackMapTable = t;
		
	}
	private static void addSonAttr(ClassFile clzFile,CodeAttr codeAttr,ByteCodeIterator iter){
		int attrNameIndex = iter.nextUNToInt(2);
		iter.back(2);
		String name = clzFile.getConstantPool().getUTF8String(attrNameIndex);
		switch(name){
			case AttributeInfo.LINE_NUM_TABLE:
				codeAttr.setLineNumberTable(LineNumberTable.parse(iter));
				break;
			case AttributeInfo.LOCAL_VAR_TABLE:
				codeAttr.setLocalVariableTable(LocalVariableTable.parse(iter));
				break;
			case AttributeInfo.STACK_MAP_TABLE:
				codeAttr.setStackMapTable(StackMapTable.parse(iter));
				break;
			default:
				throw new RuntimeException("此属性不存在："+name);
		}
	}

	
	
	
	
}
