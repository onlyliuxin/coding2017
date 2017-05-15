package com.coderising.jvm.attr;

import java.util.ArrayList;

import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.cmd.ByteCodeCommand;
import com.coderising.jvm.cmd.LdcCmd;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.loader.ByteCodeIterator;


public class CodeAttr extends AttributeInfo {
	private int maxStack ;//u4
	private int maxLocals ;//u2
	private int codeLen ;//
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
		
		
		return null;
	}
	private void setStackMapTable(StackMapTable t) {
		this.stackMapTable = t;
		
	}

//	public ByteCodeCommand[] getCmds() {
//		// TODO Auto-generated method stub
//		int index=0;
//		ArrayList<ByteCodeCommand> list=new ArrayList<>();
//		while (index<=code.length()) {
//			String opCode=code.substring(index,index+1);
//			if (opCode.equals("2a")) {
//				list.add(new LdcCmd(clzFile, opCode));
//			}
//			
//		}
//		return null;
//	}

	
	
	
	
}
