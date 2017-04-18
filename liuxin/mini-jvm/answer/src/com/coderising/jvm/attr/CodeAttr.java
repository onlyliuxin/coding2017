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
		int attrLen = iter.nextU4ToInt();
		int maxStack = iter.nextU2ToInt();
		int maxLocals = iter.nextU2ToInt();
		int codeLen = iter.nextU4ToInt();
		
		String code = iter.nextUxToHexString(codeLen);		
		
		
		ByteCodeCommand[] cmds = CommandParser.parse(clzFile,code);
		
		CodeAttr codeAttr = new CodeAttr(attrNameIndex,attrLen, maxStack,maxLocals,codeLen,code,cmds);
		
		int exceptionTableLen = iter.nextU2ToInt();
		//TODO 处理exception
		if(exceptionTableLen>0){
			String exTable = iter.nextUxToHexString(exceptionTableLen);
			System.out.println("Encountered exception table , just ignore it :" + exTable);
			
		}
		
		
		int subAttrCount = iter.nextU2ToInt();
		
		for(int x=1; x<=subAttrCount; x++){
			int subAttrIndex = iter.nextU2ToInt();
			String subAttrName = clzFile.getConstantPool().getUTF8String(subAttrIndex);
		
			//已经向前移动了U2, 现在退回去。
			iter.back(2);
			//line item table
			if(AttributeInfo.LINE_NUM_TABLE.equalsIgnoreCase(subAttrName)){
				
				LineNumberTable t = LineNumberTable.parse(iter);
				codeAttr.setLineNumberTable(t);
			}
			else if(AttributeInfo.LOCAL_VAR_TABLE.equalsIgnoreCase(subAttrName)){
				LocalVariableTable t = LocalVariableTable.parse(iter);
				codeAttr.setLocalVariableTable(t);
			} 
			else if (AttributeInfo.STACK_MAP_TABLE.equalsIgnoreCase(subAttrName)){
				StackMapTable t = StackMapTable.parse(iter);
				codeAttr.setStackMapTable(t);
			}
			else{
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
