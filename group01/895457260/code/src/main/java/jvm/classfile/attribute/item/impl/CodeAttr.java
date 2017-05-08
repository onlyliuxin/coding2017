package jvm.classfile.attribute.item.impl;

import jvm.classfile.attribute.item.AttributeInfo;

import java.util.ArrayList;
import java.util.List;

public class CodeAttr extends AttributeInfo {
	private int maxStack;
	private int maxLocals;
	private int codeLen;
	private String code;
	private List<AttributeInfo> attributes = new ArrayList<>();
	//private ByteCodeCommand[] cmds ;

	//public ByteCodeCommand[] getCmds() {
	//	return cmds;
	//}

	public CodeAttr(int attrNameIndex, int attrLen, int maxStack, int maxLocals,
					int codeLen, String code, List<AttributeInfo> attributes /*ByteCodeCommand[] cmds*/) {
		super(attrNameIndex, attrLen);
		this.maxStack = maxStack;
		this.maxLocals = maxLocals;
		this.codeLen = codeLen;
		this.code = code;
		this.attributes = attributes;
		//this.cmds = cmds;
	}

	public String getCode() {
		return code;
	}

	public List<AttributeInfo> getAttributes() {
		return attributes;
	}
}
