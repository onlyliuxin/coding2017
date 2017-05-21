package com.coding.basic.homework_04.jvm.attr;

import com.coding.basic.homework_04.jvm.clz.ClassFile;
import com.coding.basic.homework_04.jvm.cmd.ByteCodeCommand;
import com.coding.basic.homework_04.jvm.cmd.CommandParser;
import com.coding.basic.homework_04.jvm.constant.ConstantPool;
import com.coding.basic.homework_04.jvm.util.ByteCodeIterator;

public class CodeAttr extends AttributeInfo{

	private int attrNameIndex;
	private int attrLength;
	private int maxStack;
	private int maxLocals;
	private int codeLen;
	private String code;
	private ByteCodeCommand[] cmds ;
	
	
	
	private ClassFile clzFile;
	private LineNumberTable lineNumberTable;
	private LocalVariableTable localVariableTable;
	
	public ByteCodeCommand[] getCmds() {		
		return cmds;
	}
	
	public CodeAttr(int attrNameIndex, int attrLen, int maxStack, int maxLocals, int codeLen,String code ,ByteCodeCommand[] cmds) {
		super(attrNameIndex, attrLen);
		this.maxStack = maxStack;
		this.maxLocals = maxLocals;
		this.codeLen = codeLen;
		this.code = code;
		this.cmds = cmds;
	}
	public String toString(ConstantPool pool){
		StringBuilder buffer = new StringBuilder();
//		buffer.append("Code:").append(code).append("\n");
		for(int i=0;i<cmds.length;i++){
			buffer.append(cmds[i].toString(pool)).append("\n");
		}
		buffer.append("\n");
		buffer.append(this.lineNumberTable.toString());
		buffer.append(this.localVariableTable.toString(pool));
		return buffer.toString();
	}
	
	public static CodeAttr parse(ClassFile clzFile, ByteCodeIterator iterator) {
		CodeAttr codeAttr = null;
		int attrNameIndex = iterator.nextU2ToInt();
		int attrLength = iterator.nextU4ToInt();
		int maxStack = iterator.nextU2ToInt();
		int maxLocals = iterator.nextU2ToInt();
		int codeLength = iterator.nextU4ToInt();
		String code = iterator.nextUxToHexString(codeLength);
		ByteCodeCommand[] cmds = CommandParser.parse(clzFile, code);
		
		codeAttr = new CodeAttr(attrNameIndex, attrLength, maxStack, maxLocals, codeLength, code, cmds);
		
		int exceptionTableLength = iterator.nextU2ToInt();
		if(exceptionTableLength > 0){
			throw new RuntimeException("ExceptionTable  has not been implemented");
		}
		parseSubAttr(codeAttr, iterator, clzFile);
		return codeAttr;
	}

	private static void parseSubAttr(CodeAttr codeAttr, ByteCodeIterator iterator, ClassFile clzFile){
		int attributeCount = iterator.nextU2ToInt();
		for(int i=0; i<attributeCount; i++){
			int subAttrNameIndex = iterator.nextU2ToInt();
			String subAttrName = clzFile.getConstantPool().getUTF8String(subAttrNameIndex);
			iterator.back(2);
			if(AttributeInfo.LINE_NUM_TABLE.equals(subAttrName)){
				LineNumberTable lineNumberTable = LineNumberTable.parse(iterator);
				codeAttr.setLineNumberTable(lineNumberTable);
			}else if(AttributeInfo.LOCAL_VAR_TABLE.equals(subAttrName)){
				LocalVariableTable localVariableTable = LocalVariableTable.parse(iterator);
				codeAttr.setLocalVariableTable(localVariableTable);
			}else{
				throw new RuntimeException("this subAttribute has not been implemented");
			}
		}
	}
	
	
	
	public LocalVariableTable getLocalVariableTable() {
		return localVariableTable;
	}

	public void setLocalVariableTable(LocalVariableTable localVariableTable) {
		this.localVariableTable = localVariableTable;
	}

	public LineNumberTable getLineNumberTable() {
		return lineNumberTable;
	}

	public void setLineNumberTable(LineNumberTable lineNumberTable) {
		this.lineNumberTable = lineNumberTable;
	}

	public int getAttrNameIndex() {
		return attrNameIndex;
	}

	public void setAttrNameIndex(int attrNameIndex) {
		this.attrNameIndex = attrNameIndex;
	}

	public int getAttrLength() {
		return attrLength;
	}

	public void setAttrLength(int attrLength) {
		this.attrLength = attrLength;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public ClassFile getClzFile() {
		return clzFile;
	}

	public void setClzFile(ClassFile clzFile) {
		this.clzFile = clzFile;
	}
	
}
