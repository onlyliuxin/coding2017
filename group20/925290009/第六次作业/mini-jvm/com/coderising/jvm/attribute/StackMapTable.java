package com.coderising.jvm.attribute;

import com.coderising.jvm.clasfile.ClassFile;
import com.coderising.jvm.loader.ByteCodeIterator;

public class StackMapTable extends AttributeInfo{

	private String originCode;
	
	public String getOriginCode() {
		return originCode;
	}

	public void setOriginCode(String originCode) {
		this.originCode = originCode;
	}

	public StackMapTable(int attrNameIndex, int attrLen) {
		super(attrNameIndex, attrLen);
	}

	public static StackMapTable parse(ClassFile classFile,
			ByteCodeIterator iterator) {

		iterator.back(2);
		int attName_index = iterator.next2BytesToInt();
		int attr_len = iterator.next4BytesToInt();
		StackMapTable stackMapTable  = new StackMapTable(attName_index, attr_len);
		
		String codeString  = iterator.nextXBytesToString(attr_len);
		stackMapTable.setOriginCode(codeString);
		
		return stackMapTable;
	}

}
