package jvm.attr;

import jvm.classfile.ConstantPool;
import jvm.util.ByteCodeIterator;

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

	private CodeAttr(int attrNameIndex, int attrLen, int maxStack, int maxLocals,
					int codeLen, String code /*ByteCodeCommand[] cmds*/) {
		super(attrNameIndex, attrLen);
		this.maxStack = maxStack;
		this.maxLocals = maxLocals;
		this.codeLen = codeLen;
		this.code = code;
		//this.cmds = cmds;
	}

	public static CodeAttr parse(int attrNameIndex, int attrLen,
								 ByteCodeIterator iterator, ConstantPool constantPool) {
		int maxStack = iterator.nextU2ToInt();
		int maxLocals = iterator.nextU2ToInt();
		int codeLen = iterator.nextU4ToInt();
		String code = new String(iterator.getBytes(codeLen));

		int exceptionTableLen = iterator.nextU2ToInt();
		iterator.skip(exceptionTableLen * 8);

		CodeAttr result = new CodeAttr(attrNameIndex, attrLen, maxStack, maxLocals, codeLen, code);

		int attrCount = iterator.nextU2ToInt();
		for (int i = 0; i < attrCount; ++i) {
			result.attributes.add(AttributeParser.parse(iterator, constantPool));
		}
		return result;
	}

	public String getCode() {
		return code;
	}

	public List<AttributeInfo> getAttributes() {
		return attributes;
	}
}
