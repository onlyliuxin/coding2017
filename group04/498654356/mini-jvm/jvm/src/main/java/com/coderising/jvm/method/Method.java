package com.coderising.jvm.method;

import com.coderising.jvm.attr.CodeAttr;
import com.coderising.jvm.cmd.ByteCodeCommand;
import com.coderising.jvm.constant.ConstantPool;

public class Method {
	private int accessFlag;
	private int nameIndex;
	private int descIndex;
	private ConstantPool constantPool;
	private CodeAttr codeAttr;
	public int getAccessFlag() {
		return accessFlag;
	}
	public void setAccessFlag(int accessFlag) {
		this.accessFlag = accessFlag;
	}
	public int getNameIndex() {
		return nameIndex;
	}
	public void setNameIndex(int nameIndex) {
		this.nameIndex = nameIndex;
	}
	public int getDescIndex() {
		return descIndex;
	}
	public void setDescIndex(int descIndex) {
		this.descIndex = descIndex;
	}
	public ConstantPool getConstantPool() {
		return constantPool;
	}
	public void setConstantPool(ConstantPool constantPool) {
		this.constantPool = constantPool;
	}
	public CodeAttr getCodeAttr() {
		return codeAttr;
	}
	public void setCodeAttr(CodeAttr codeAttr) {
		this.codeAttr = codeAttr;
	}
	public ByteCodeCommand[] getCmds() {
		return this.getCodeAttr().getCmds();
	}

}
