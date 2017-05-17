package com.coderising.jvm.attr;

import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.cmd.ByteCodeCommand;
import com.coderising.jvm.cmd.CommandParser;
import com.coderising.jvm.loader.ByteCodeIterator;

public class CodeAttr extends AttributeInfo{

	private int maxStack;
	private int maxLocals;
	private int codeLen;
	private String code;
	
	private ByteCodeCommand[] cmds;
	
	public ByteCodeCommand[] getCmds(){
		return cmds;
	}
	
	public String getCode(){
		return code;
	}
	
	
	private LineNumberTable lineNumTable;
	private LocalVariableTable localVarTable;
	private StackMapTable stackMapTable;
	
	
	
	public CodeAttr(int attrNameIndex,  int attrLen , int maxStack, int maxLocals, int codeLen,String code, ByteCodeCommand[] cmds) {
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
	
	private void setStackMapTable(StackMapTable t) {
		this.stackMapTable = t;
		
	}
	
	public static CodeAttr parse(ClassFile clzFile, ByteCodeIterator iter){
		int attrNameIndex = iter.nextU2toInt();
		int attrLen = iter.nextU2toInt();
		int maxStack = iter.nextU2toInt();
		int max_Locals = iter.nextU2toInt();
		int codeLen = iter.nextU4toInt();
		// �������� code
		String code = iter.nextUxToHexString(codeLen);
		System.out.println("code: " + code);
		
		ByteCodeCommand[] cmds = CommandParser.parse(clzFile, code);
		System.out.println("cmds:  ==== " + cmds.length);
		System.out.println("CodeAttr�����е�cmd�ĳ��ȣ� " + cmds.length);
		CodeAttr codeAttr = new CodeAttr(attrNameIndex, attrLen, maxStack, max_Locals, max_Locals, code, cmds);
		
		int exceptionTableLen = iter.nextU2toInt();
		
		if(exceptionTableLen > 0){
			String exTable = iter.nextUxToHexString(exceptionTableLen);
			System.out.println("Encounted exception table, just ignore it");
		}
		
		int subAttributesCount = iter.nextU2toInt();
		
		for(int i = 0; i < subAttributesCount; i++){
			int subAttrNameIndex = iter.nextU2toInt();
			String subAttrName = clzFile.getConstantPool().getUTF8String(subAttrNameIndex);
			if(CodeAttr.LOCAL_VAR_TABLE.equalsIgnoreCase(subAttrName)){
				int subAttrLen = iter.nextU4toInt();// ���� localVariableTable ���Ե� ����
				LocalVariableTable locVarTable = LocalVariableTable.parse(iter, subAttrNameIndex, subAttrLen);
				codeAttr.setLocalVariableTable(locVarTable);
			}else if(CodeAttr.LINE_NUM_TABLE.equalsIgnoreCase(subAttrName)){
				int subAttrLen = iter.nextU4toInt();
				LineNumberTable lineNumTable = LineNumberTable.parse(iter, subAttrNameIndex, subAttrLen);
				codeAttr.setLineNumberTable(lineNumTable);
			}else if(CodeAttr.STACK_MAP_TABLE.equalsIgnoreCase(subAttrName)){
				int subAttrLen = iter.nextU4toInt();
				StackMapTable stackMapTable = StackMapTable.parse(iter);
				codeAttr.setStackMapTable(stackMapTable);
			}else{
				throw new RuntimeException("need code to process" + subAttrName);
			}
		}

		return codeAttr;
	}
}
