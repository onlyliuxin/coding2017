package org.xukai.jvm.attr;

import org.xukai.jvm.clz.ClassFile;
import org.xukai.jvm.constant.UTF8Info;
import org.xukai.jvm.loader.ByteCodeIterator;

public abstract class AttributeInfo {
	public static final String CODE = "Code";
	public static final String CONST_VALUE = "ConstantValue";
	public static final String EXCEPTIONS = "Exceptions";
	public static final String LINE_NUM_TABLE = "LineNumberTable";
	public static final String LOCAL_VAR_TABLE = "LocalVariableTable";
	public static final String STACK_MAP_TABLE = "StackMapTable";
	int attrNameIndex;				
	int attrLen ;
	public AttributeInfo(int attrNameIndex, int attrLen) {
		
		this.attrNameIndex = attrNameIndex;
		this.attrLen = attrLen;
	}


	public static AttributeInfo parseAttribute(ByteCodeIterator iter, ClassFile clzFile) {
		int attributeNameIndex = iter.nextToInt(2);
		System.out.println(((UTF8Info)clzFile.getConstantPool().getConstantInfo(attributeNameIndex)).getValue());
		String attributeName = ((UTF8Info) clzFile.getConstantPool().getConstantInfo(attributeNameIndex)).getValue();
		switch(attributeName){
			case AttributeInfo.CODE :
				iter.preToInt(2);
				CodeAttr codeAttr = CodeAttr.parse(clzFile, iter);
				return codeAttr;
			case AttributeInfo.EXCEPTIONS :
				iter.preToInt(2);
				System.out.println("解析exception");
				return null;
			case AttributeInfo.CONST_VALUE :
				iter.preToInt(2);
				System.out.println("解析constValue");
				return null;
			case AttributeInfo.LINE_NUM_TABLE :
				iter.preToInt(2);
				LineNumberTable lineNumberTable = LineNumberTable.parse(iter);
				return lineNumberTable;
			case AttributeInfo.LOCAL_VAR_TABLE :
				iter.preToInt(2);
				LocalVariableTable localVariableTable = LocalVariableTable.parse(iter);
				return localVariableTable;
			case AttributeInfo.STACK_MAP_TABLE :
				iter.preToInt(2);
				StackMapTable stackMapTable = StackMapTable.parse(iter);
				return stackMapTable;
			default:
				throw new RuntimeException();
		}


	}
	
}
