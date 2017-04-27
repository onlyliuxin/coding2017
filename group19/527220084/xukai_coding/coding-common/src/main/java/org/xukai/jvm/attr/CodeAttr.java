package org.xukai.jvm.attr;


import org.xukai.jvm.clz.ClassFile;
import org.xukai.jvm.loader.ByteCodeIterator;

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
		int attributeNameIndex = iter.nextToInt(2);
		int attributeLength = iter.nextToInt(4);
		int maxStack = iter.nextToInt(2);
		int maxLocals = iter.nextToInt(2);
		int codeLength = iter.nextToInt(4);
		String code = iter.nextToString(codeLength);
		System.out.println(code);
		CodeAttr codeAttr = new CodeAttr(attributeNameIndex, attributeLength, maxStack, maxLocals, codeLength, code);
		int exceptionTableLength = iter.nextToInt(2);
		if (exceptionTableLength > 0) {
			iter.nextToInt(exceptionTableLength);
			System.out.println("解析exception");
		}
		int subAttributeCount = iter.nextToInt(2);
		System.out.println("subAttributeCount" + subAttributeCount);
		if (subAttributeCount > 0) {
			for (int i = 0; i < subAttributeCount; i++) {
				AttributeInfo attributeInfo = AttributeInfo.parseAttribute(iter, clzFile);
				if (attributeInfo instanceof LineNumberTable) {
					codeAttr.setLineNumberTable((LineNumberTable)attributeInfo);
				} else if (attributeInfo instanceof LocalVariableTable){
					codeAttr.setLocalVariableTable((LocalVariableTable)attributeInfo);
				} else if (attributeInfo instanceof StackMapTable){
					codeAttr.setStackMapTable((StackMapTable)attributeInfo);
				}
			}
		}
		return codeAttr;
	}
	private void setStackMapTable(StackMapTable t) {
		this.stackMapTable = t;
		
	}

	
	
	
	
}
