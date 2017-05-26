package com.coderising.jvm.attr;

import java.io.UnsupportedEncodingException;

import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.cmd.ByteCodeCommand;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.loader.ByteCodeIterator;
import com.coderising.jvm.util.Util;


public class CodeAttr extends AttributeInfo {
	private int maxStack ;
	private int maxLocals ;
	private int codeLen ;
	private String code;
	public String getCode() {
		return code;
	}

	private ByteCodeCommand[] cmds ;
	public ByteCodeCommand[] getCmds() {
		return cmds;
	}
	
	public void setCmds(ByteCodeCommand[] cmds) {
		this.cmds = cmds;
	}

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
		int attrNameIndex=iter.nextU2toInt();
		int attrLen=iter.nextU4toInt();
		int maxStack=iter.nextU2toInt();
		int maxLocals=iter.nextU2toInt();
		int codeLen=iter.nextU4toInt();
		byte[] codes=iter.getByte(codeLen);
		String code = null;
		try {
			code=Util.byteToHexString(codes);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		CodeAttr codeAttr=new CodeAttr(attrNameIndex,attrLen,maxStack,maxLocals,codeLen,code);
		
		int exceNum=iter.nextU2toInt();
		System.out.println("exception num"+exceNum);
		//如果有exception要实现
		int arrtNum=iter.nextU2toInt();
		for(int i=0;i<arrtNum;i++){
			String attrName=clzFile.getConstantPool().getUTF8String(iter.nextU2toInt());
			iter.back(2);
			if(AttributeInfo.LINE_NUM_TABLE.equals(attrName))
				codeAttr.setLineNumberTable(LineNumberTable.parse(iter));
			else if(AttributeInfo.LOCAL_VAR_TABLE.equals(attrName))
				codeAttr.setLocalVariableTable(LocalVariableTable.parse(iter));
			else if(AttributeInfo.STACK_MAP_TABLE.equals(attrName))
				codeAttr.setStackMapTable(StackMapTable.parse(iter));
			else {
				throw new RuntimeException("  please implement the "+ attrName);
			}
		}
		return codeAttr;
	}
	private void setStackMapTable(StackMapTable t) {
		this.stackMapTable = t;
		
	}

	
	
	
	
}
