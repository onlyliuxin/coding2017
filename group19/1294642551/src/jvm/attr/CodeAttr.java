package jvm.attr;

import java.util.Arrays;

import jvm.clz.ClassFile;
import jvm.cmd.ByteCodeCommand;
import jvm.cmd.CommandParser;
import jvm.constant.ConstantPool;
import jvm.loader.ByteCodeIterator;


public class CodeAttr extends AttributeInfo {
	private int maxStack ;
	private int maxLocals ;
	private int codeLen ;
	private String code;
	private LineNumberTable lineNumTable;
	private LocalVariableTable localVarTable;
	private StackMapTable stackMapTable;
	
	private ByteCodeCommand[] cmds ;
	
	public String getCode() {
		return code;
	}
	
	public ByteCodeCommand[] getCmds() {		
		return cmds;
	}
	
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
		iter.back(2);
		int attrNameIndex = iter.nextU2ToInt();
		int attrLen = iter.nextU4ToInt();
		int maxStack = iter.nextU2ToInt();
		int maxLocals = iter.nextU2ToInt();
		int codeLen = iter.nextU4ToInt();
		String code = iter.nextUxToHexString(codeLen);
		
		ByteCodeCommand[] cmds = CommandParser.parse(clzFile, code);
//		System.out.println(Arrays.toString(cmds));
		
		CodeAttr codeAttr = new CodeAttr(attrNameIndex, attrLen, maxStack, maxLocals, codeLen, code, cmds);
		
		int exceptionLen = iter.nextU2ToInt();
		if(exceptionLen>0){
			String exTable = iter.nextUxToHexString(exceptionLen);
			System.out.println("Encountered exception table , just ignore it :" + exTable);
			
		}
		
		int childAttrLen = iter.nextU2ToInt();
		
		
		for(int i = 0; i < childAttrLen; i++){
			int childAttrNameIndex = iter.nextU2ToInt();
			String childAttrName = clzFile.getConstantPool().getUTF8String(childAttrNameIndex);
			if(AttributeInfo.LINE_NUM_TABLE.equalsIgnoreCase(childAttrName)){
				LineNumberTable lineNumberTable = LineNumberTable.parse(iter);
				codeAttr.setLineNumberTable(lineNumberTable);
			}else if(AttributeInfo.LOCAL_VAR_TABLE.equalsIgnoreCase(childAttrName)){
				LocalVariableTable localVariableTable = LocalVariableTable.parse(iter);
				codeAttr.setLocalVariableTable(localVariableTable);
			}else if (AttributeInfo.STACK_MAP_TABLE.equalsIgnoreCase(childAttrName)){
				StackMapTable t = StackMapTable.parse(iter);
				codeAttr.setStackMapTable(t);
			}else{
				throw new RuntimeException("code 的子属性 "+ childAttrName+"没有解析");
			}
		}
		return codeAttr;
	}
	

	public String toString(ConstantPool pool){
		StringBuilder buffer = new StringBuilder();
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
