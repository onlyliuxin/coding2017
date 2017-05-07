package com.coding.mini_jvm.src.com.coderising.jvm.attr;


import com.coding.mini_jvm.src.com.coderising.jvm.clz.ClassFile;
import com.coding.mini_jvm.src.com.coderising.jvm.cmd.ByteCodeCommand;
import com.coding.mini_jvm.src.com.coderising.jvm.cmd.CommandParser;
import com.coding.mini_jvm.src.com.coderising.jvm.loader.ByteCodeIterator;

public class CodeAttr extends AttributeInfo {
	private int maxStack ;
	private int maxLocals ;
	private int codeLen ;
	private String code;
	public String getCode() {
		return code;
	}

	private ByteCodeCommand[] cmds;

	public ByteCodeCommand[] getCmds() {
		return cmds;
	}

	private LineNumberTable    lineNumTable;
	private LocalVariableTable localVarTable;
	private StackMapTable      stackMapTable;

	public CodeAttr(int attrNameIndex, int attrLen, int maxStack, int maxLocals, int codeLen, String code ,ByteCodeCommand[] cmds) {
		super(attrNameIndex, attrLen);
		this.maxStack = maxStack;
		this.maxLocals = maxLocals;
		this.codeLen = codeLen;
		this.code = code;
		this.cmds = cmds;
	}

	public void setLineNumberTable(LineNumberTable t) {
		this.lineNumTable = t;
	}

	public void setLocalVariableTable(LocalVariableTable t) {
		this.localVarTable = t;		
	}
	
	public static CodeAttr parse(ClassFile clzFile, ByteCodeIterator iter){

		//回退2个字节
		iter.back(ByteCodeIterator.U2);
		int attrNameIndex = iter.readTwoBytesToInt();
		int attrLen = iter.readFourBytesToInt();
		int maxStack = iter.readTwoBytesToInt();
		int maxLocal = iter.readTwoBytesToInt();
		int codeLen = iter.readFourBytesToInt();
		String code = iter.readBytesToHexString(codeLen);
		ByteCodeCommand[] cmds = CommandParser.parse(clzFile, code);
		CodeAttr codeAttr = new CodeAttr(attrNameIndex, attrLen, maxStack, maxLocal, codeLen, code, cmds);
		//异常表长度
		int exceptionTableLen = iter.readTwoBytesToInt();
		if (exceptionTableLen > 0) {
			throw new RuntimeException("not impl yet");
		}

		//子属性个数
		int attrCount = iter.readTwoBytesToInt();
		for (int i = 0; i < attrCount; i++) {
			attrNameIndex = iter.readTwoBytesToInt();
			String attrName = clzFile.getConstantPool().getUTF8String(attrNameIndex);
			if ("LineNumberTable".equals(attrName)) {
				LineNumberTable lineNumberTable = LineNumberTable.parse(iter);
				codeAttr.setLineNumberTable(lineNumberTable);
			} else if ("LocalVariableTable".equals(attrName)) {
				LocalVariableTable localVariableTable = LocalVariableTable.parse(iter);
				codeAttr.setLocalVariableTable(localVariableTable);
			} else if ("StackMapTable".equals(attrName)) {
				StackMapTable stackMapTable = StackMapTable.parse(iter);
				codeAttr.setStackMapTable(stackMapTable);
			} else {
				throw new RuntimeException("not impl yet");
			}
		}

		return codeAttr;
	}

	private void setStackMapTable(StackMapTable t) {
		this.stackMapTable = t;
	}

	
	
	
	
}
