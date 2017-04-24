package com.coderising.jvm.attr;

import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.cmd.ByteCodeCommand;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.loader.ByteCodeIterator;
import org.w3c.dom.Attr;


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
	
	public CodeAttr(int attrNameIndex, int attrLen, int maxStack, int maxLocals, int codeLen,String code ,ByteCodeCommand[] cmds) {
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
		String name = clzFile.getConstantPool().getUTF8String(attrNameIndex);

		int attrLength = iter.nextU4ToInt();
		int maxStack = iter.nextU2ToInt();
		int maxLocals = iter.nextU2ToInt();
		int codeLength = iter.nextU4ToInt();

		String code = iter.nextUxToHexString(codeLength);
		System.out.println("code: " + code);

		ByteCodeCommand[] cmds = ByteCodeCommand.parse(clzFile, code);

		CodeAttr codeAttr = new CodeAttr(attrNameIndex, attrLength, maxStack, maxLocals, codeLength, code, cmds);

		int exceptionTableLength = iter.nextU2ToInt();

		if (exceptionTableLength > 0){
			System.out.println(iter.nextUxToHexString(exceptionTableLength));
			//TODO parse exception
			System.out.println("just ignore exception!!");
		}

		int subAttrCount = iter.nextU2ToInt();
		for (int i = 0; i < subAttrCount; i++) {

			int subAttrNameIndex = iter.nextU2ToInt();
			String subAttrName = clzFile.getConstantPool().getUTF8String(subAttrNameIndex);
			iter.back(2);

			if (AttributeInfo.LINE_NUM_TABLE.equals(subAttrName)){
				codeAttr.setLineNumberTable(LineNumberTable.parse(iter));

			} else if (AttributeInfo.LOCAL_VAR_TABLE.equals(subAttrName)){
				codeAttr.setLocalVariableTable(LocalVariableTable.parse(iter));

			} else if (AttributeInfo.STACK_MAP_TABLE.equals(subAttrName)){
				codeAttr.setStackMapTable(StackMapTable.parse(iter));

			} else {
				throw new RuntimeException("Need code to process " + subAttrName);
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
