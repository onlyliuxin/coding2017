package week6.jvm.attr;

import week6.jvm.clz.ClassFile;
import week6.jvm.loader.ByteCodeIterator;

public class CodeAttr extends AttributeInfo{

	private int maxStack;
	private int maxLocals;
	private int codeLen;
	private String code;
	
	private AttributeInfo lineNumberTable;
	private AttributeInfo localVariableTable;
	private AttributeInfo stackMapTable;
	

	public CodeAttr(int attrNameIndex,int attrLen, int maxStack, int maxLocals, int codeLen, String code) {
		super(attrNameIndex,attrLen);
		this.maxStack = maxStack;
		this.maxLocals = maxLocals;
		this.codeLen = codeLen;
		this.code = code;
	}

	public static AttributeInfo parseCodeAttr(ClassFile clzFile,
			ByteCodeIterator iter) {
		int attrNameIndex=iter.nextU2ToInt();
		int attrLen=iter.nextU4ToInt();
		int maxStack=iter.nextU2ToInt();
		int maxLocal=iter.nextU2ToInt();
		
		int codeLen=iter.nextU4ToInt();
		String code=iter.nextUxToString(codeLen);
	
		CodeAttr codeAttr=new CodeAttr(attrNameIndex,attrLen,maxStack,maxLocal,codeLen,code);
		
		int exceptionTableLen=iter.nextU2ToInt();
		if(exceptionTableLen > 0){
			String exceptionTable=iter.nextUxToString(exceptionTableLen);
			System.out.println("Encountered exception table , just ignore it :"+exceptionTable);
		}
		
		int attrCount=iter.nextU2ToInt();
		for(int i=1;i<=attrCount;i++){
			int subAttrNameIndex=iter.nextU2ToInt();
			String subAttrName=clzFile.getConstantPool().getUTF8String(subAttrNameIndex);
			iter.back(2);
			
			if(AttributeInfo.LINE_NUMBER_TABLE.equalsIgnoreCase(subAttrName)){
				
				AttributeInfo table=LineNumberTable.parse(iter);
				codeAttr.setLineNumberTable(table);
				
			}else if(AttributeInfo.LOCAL_Variable_Table.equalsIgnoreCase(subAttrName)){
				
				AttributeInfo table=LocalVariableTable.parse(iter,clzFile.getConstantPool());
				codeAttr.setLocalVariableTable(table);
			
			}else if(AttributeInfo.STACK_MAP_TABLE.equalsIgnoreCase(subAttrName)){
				
				AttributeInfo table=StackMapTable.parse(iter);
				codeAttr.setStackMapTable(table);
				
			}else{
				
				throw new RuntimeException("Need code to process " + subAttrName);
			}
			
		}
		
		return codeAttr;
	}

	private AttributeInfo getLineNumberTable() {
		return lineNumberTable;
	}

	private void setLineNumberTable(AttributeInfo lineNumberTable) {
		this.lineNumberTable = lineNumberTable;
	}

	private AttributeInfo getLocalVariableTable() {
		return localVariableTable;
	}

	private void setLocalVariableTable(AttributeInfo localVariableTable) {
		this.localVariableTable = localVariableTable;
	}

	private AttributeInfo getStackMapTable() {
		return stackMapTable;
	}

	private void setStackMapTable(AttributeInfo stackMapTable) {
		this.stackMapTable = stackMapTable;
	}

	public String getCode() {
		return code;
	}
	
	

}
