package com.coderising.jvm.attr;

import java.util.ArrayList;
import java.util.List;

import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.loader.ByteCodeIterator;

public class CodeAttr extends AttributeInfo {

	private static String TYPE = CODE;
	private int maxStack;//u2 max_stack
	private int maxLocals;//u2 max_locals
	private int codeLenth;//u4 code_length
	private String code;//u1 code[code_length]
	private int exceptionTableLength;//u2 exception_table_length;
	/*
	 * { u2 start_pc;
		u2 end_pc;
		u2 handler_pc;
		u2 catch_type;
		} exception_table[exception_table_length];
	private ExceptionTable exceptionTable;//unimplemented*/
	private int attrCount;//u2 attributes_count
	private List<AttributeInfo> AttrList = new ArrayList<AttributeInfo>();//attribute_info attributes[attributes_count];

	public CodeAttr(int attrNameIndex, int attrLen, int maxStack,
			int maxLocals, int codeLenth, String code,
			int exceptionTableLength, int attrCount) {
		super(attrNameIndex, attrLen);
		this.maxStack = maxStack;
		this.maxLocals = maxLocals;
		this.codeLenth = codeLenth;
		this.code = code;
		this.exceptionTableLength = exceptionTableLength;
		this.attrCount = attrCount;
	}

	/**
	 * 给CodeAttr 增加属性
	 * @param a
	 */
	public void addAttr(AttributeInfo a){
		this.AttrList.add(a);
	}
	
	@Override
	public String getType() {
		return TYPE;
	}
	
	public static CodeAttr parse(ConstantPool pool, ByteCodeIterator itr) {
		int attrNameIndex = itr.nextU2toInt();
		int attrLen = itr.nextU4toInt();
		int maxStack = itr.nextU2toInt();
		int maxLocals = itr.nextU2toInt();
		int codeLenth = itr.nextU4toInt();
		String code = itr.nextUxtoHexString(codeLenth);
		int exceptionTableLength = itr.nextU2toInt();
		int attrCount = itr.nextU2toInt();
		CodeAttr codeAttr = new CodeAttr(attrNameIndex, attrLen, maxStack, maxLocals,
				codeLenth, code, exceptionTableLength, attrCount);
		for (int i = 0; i < attrCount; i++) {
			codeAttr.addAttr(AttributeInfo.parse(pool, itr));
		}
		return codeAttr;
	}
	/*
	 * getter setter
	 */
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

	public int getCodeLenth() {
		return codeLenth;
	}

	public void setCodeLenth(int codeLenth) {
		this.codeLenth = codeLenth;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public int getAttrCount() {
		return attrCount;
	}

	public void setAttrCount(int attrCount) {
		this.attrCount = attrCount;
	}

	public List<AttributeInfo> getAttrList() {
		return AttrList;
	}

	public void setAttrList(List<AttributeInfo> attrList) {
		AttrList = attrList;
	}

	public int getExceptionTableLength() {
		return exceptionTableLength;
	}

	public void setExceptionTableLength(int exceptionTableLength) {
		this.exceptionTableLength = exceptionTableLength;
	}

	

}
