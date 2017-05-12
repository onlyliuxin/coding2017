package com.coderising.jvm.attr;

import com.coderising.jvm.clz.ClassFile;
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
		int attrNameIndex = iter.nextU2ToInt();
		int attrLen = iter.nextU4ToInt();
		int maxStack = iter.nextU2ToInt();
		int maxLocals = iter.nextU2ToInt();
		int codeLen = iter.nextU4ToInt();
		String code = iter.nextUxToHexString(codeLen);
		System.out.println(code);
		CodeAttr attr = new CodeAttr(attrNameIndex, attrLen, maxStack, maxLocals, codeLen, code);
		int expTableLen = iter.nextU2ToInt();
		if(expTableLen > 0){
			String expTable = iter.nextUxToHexString(expTableLen);
			System.out.println("expTable: " + expTable);
			// TODO 异常表处理
		}
		int subAttrCount = iter.nextU2ToInt();
		ConstantPool pool = clzFile.getConstantPool();
		for (int i = 1; i <= subAttrCount; i++) {
			int subAttrNameIndex = iter.nextU2ToInt();
			String subAttrName = pool.getUTF8String(subAttrNameIndex);
			iter.back(2);
			if(AttributeInfo.LINE_NUM_TABLE.equalsIgnoreCase(subAttrName)){
				attr.setLineNumberTable(LineNumberTable.parse(iter));
			}else if(AttributeInfo.LOCAL_VAR_TABLE.equalsIgnoreCase(subAttrName)){
				attr.setLocalVariableTable(LocalVariableTable.parse(iter));
			}else if(AttributeInfo.STACK_MAP_TABLE.equalsIgnoreCase(subAttrName)){
				attr.setStackMapTable(StackMapTable.parse(iter));
			}else{
				//TODO 
				throw new RuntimeException("CodeAttr.parse not implement " + subAttrName);
			}
		}
		return attr;
	}
	private void setStackMapTable(StackMapTable t) {
		this.stackMapTable = t;
		
	}

	
	
	
	
}
