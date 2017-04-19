package com.coderising.jvm.attr;

import java.util.ArrayList;
import java.util.List;

import com.coderising.jvm.cmd.ByteCodeCommand;
import com.coderising.jvm.cmd.CommandParser;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.loader.ByteCodeIterator;

public class CodeAttr extends AttributeInfo {
		
	private int maxStack ;
	private int maxLocals ;
	private int codeLen ;
	private String code;
	private ByteCodeCommand[] cmds ;	
	private List<AttributeInfo> attributeInfos = new ArrayList<>();
	
	public CodeAttr(int attrNameIndex, int attrLen, int maxStack, int maxLocals, int codeLen,String code ,ByteCodeCommand[] cmds) {
		super(attrNameIndex, attrLen);
		this.maxStack = maxStack;
		this.maxLocals = maxLocals;
		this.codeLen = codeLen;
		this.code = code;
		this.cmds = cmds;
	}
	
		
	public static CodeAttr parse(ConstantPool pool,ByteCodeIterator iter){
		
		int attrNameIndex = iter.nextU2ToInt();
		int attrLen = iter.nextU4ToInt();
		int maxStack = iter.nextU2ToInt();
		int maxLocals = iter.nextU2ToInt();
		int codeLen = iter.nextU4ToInt();		
		String code = iter.nextUxToHexString(codeLen);
		ByteCodeCommand[] cmds = CommandParser.parse(pool.getClzFile(),code);
		CodeAttr codeAttr = new CodeAttr(attrNameIndex,attrLen,maxStack,maxLocals,codeLen,code,cmds);
		
		//解析exception_table start TODO
		@SuppressWarnings("unused")
		int exceptionTabLen = iter.nextU2ToInt();
		//System.out.println("exception_table 的个数为"+exceptionTabLen);
		//解析exception_table end  TODO
		
		codeAttr.setAttributeInfos(AttributeInfo.parseAttributes(pool, iter));
		
		return codeAttr;
	}

	public ByteCodeCommand[] getCmds() {		
		return cmds;
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


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}
	
	public List<AttributeInfo> getAttributeInfos() {
		return attributeInfos;
	}


	public void setAttributeInfos(List<AttributeInfo> attributeInfos) {
		this.attributeInfos = attributeInfos;
	}
}
