package com.coderising.jvm.attr;

import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.constant.UTF8Info;
import com.coderising.jvm.loader.ByteCodeIterator;

public abstract class AttributeInfo {
	public static final String CODE = "Code";
	public static final String CONST_VALUE = "ConstantValue";
	public static final String EXCEPTIONS = "Exceptions";
	public static final String LINE_NUM_TABLE = "LineNumberTable";
	public static final String LOCAL_VAR_TABLE = "LocalVariableTable";
	public static final String STACK_MAP_TABLE = "StackMapTable";
	int attrNameIndex;// u2 attribute_name_index
	int attrLen;// u4 attribute_length

	public abstract String getType();
	public AttributeInfo(int attrNameIndex, int attrLen) {
		this.attrNameIndex = attrNameIndex;
		this.attrLen = attrLen;
	}

	public static AttributeInfo parse(ConstantPool pool, ByteCodeIterator itr) {
		int attrNameIndex = itr.nextU2toInt();
		String attrName = ((UTF8Info) pool.getConstantInfo(attrNameIndex))
				.getValue();
		itr.back(2);
		switch (attrName) {
		case CODE:
			return CodeAttr.parse(pool, itr);
		case LINE_NUM_TABLE:
			return LineNumberTable.parse(pool, itr);
		case LOCAL_VAR_TABLE:
			return LocalVariableTable.parse(pool, itr);
		default:
			throw new RuntimeException(
					"attributeInfo exclude CodeAttr hasn't implemented");
		}
	}
}
