package com.coderising.jvm.attr;

import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.loader.ByteCodeIterator;

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
	
	public static AttributeInfo parse(ClassFile file, ByteCodeIterator iter) throws InvalidAttributeInfoException{
		AttributeInfo attrInfo = null;
		int attributeType = iter.getNextNBytesInteger(2);
		String attributeTag = file.getConstantPool().getUTF8String(attributeType);
		System.out.println("attribute type: " + attributeType + " utf8 val: " + file.getConstantPool().getUTF8String(attributeType));
		
		if(attributeTag.equals(CODE)){
			System.out.println("about to parse CODE attribute");
			//ClassFile clzFile, ByteCodeIterator iter, int attributeNameIndex
			attrInfo = CodeAttr.parse(file, iter, attributeType);
		}
		else if(attributeTag.equals(LINE_NUM_TABLE)){
			System.out.println("about to parse LINE_NUM_TABLE attribute");
			attrInfo = LineNumberTable.parse(iter);
		}
		else if(attributeTag.equals(LOCAL_VAR_TABLE)){
			System.out.println("about to parse LOCAL_VAR_TABLE attribute");
			attrInfo = LocalVariableTable.parse(iter, file, attributeType);
		}
		else{
			System.out.println("about to throw attribute");
			throw new InvalidAttributeInfoException("Unimplemented attribute exception.");
		}
		
		return attrInfo;
	}
}
