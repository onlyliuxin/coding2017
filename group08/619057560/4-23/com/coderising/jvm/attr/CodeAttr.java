package com.coderising.jvm.attr;

import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.cmd.ByteCodeCommand;
import com.coderising.jvm.cmd.CommandParser;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.loader.ByteCodeIterator;


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
	private LineNumberTable lineNumTable;
	private LocalVariableTable localVarTable;
	private StackMapTable stackMapTable;
	
	public CodeAttr(int attrNameIndex, int attrLen, int maxStack, int maxLocals, int codeLen,String code, ByteCodeCommand[] cmds) {
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
		int attrNameIndex = iter.nextU2ToInt();
		int attrLen = iter.nextU4ToInt();
		int maxStack = iter.nextU2ToInt();
		int maxLocals = iter.nextU2ToInt();
		int codeLen = iter.nextU4ToInt();
		String code = iter.nextUxToHexString(codeLen);
		ByteCodeCommand[] cmds = CommandParser.parse(clzFile, code);
		
		CodeAttr codeAttr = new CodeAttr(attrNameIndex, attrLen, maxStack, maxLocals, codeLen, code, cmds);
		
		int exceptionTableLen = iter.nextU2ToInt();
		if (exceptionTableLen > 0) {
			String exceptionTable = iter.nextUxToHexString(exceptionTableLen);
			System.out.println("exceptionTable: " + exceptionTable);
		}
		
		int attrCount = iter.nextU2ToInt();
		for (int i = 1; i <= attrCount; i++) {
			int nameIndex = iter.nextU2ToInt();
			iter.back(2);
			String name = clzFile.getConstantPool().getUTF8String(nameIndex);
			if (name.equalsIgnoreCase(LINE_NUM_TABLE)) {
				codeAttr.setLineNumberTable(LineNumberTable.parse(iter));
			} else if (name.equalsIgnoreCase(LOCAL_VAR_TABLE)) {
				codeAttr.setLocalVariableTable(LocalVariableTable.parse(iter));
			} else if (name.equalsIgnoreCase(STACK_MAP_TABLE)) {
				codeAttr.setStackMapTable(StackMapTable.parse(iter));
			}
		}
		
		return codeAttr;
	}
	public String toString(ConstantPool pool){
		StringBuilder buffer = new StringBuilder();
		//buffer.append("Code:").append(code).append("\n");
		for(int i=0;i<cmds.length;i++){
			buffer.append(cmds[i].toString(pool)).append("\n");
		}
		buffer.append("\n");
		buffer.append(this.lineNumTable.toString());
		buffer.append(this.localVarTable.toString(pool));
		return buffer.toString();
	}
	private void setStackMapTable(StackMapTable t) {
		this.stackMapTable = t;
		
	}

	
	
	
	
}
