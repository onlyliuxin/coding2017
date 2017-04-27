package com.coderising.jvm.attr;


import com.coderising.jvm.loader.ByteCodeIterator;

public class StackMapTable extends AttributeInfo{
	
	private String originalCode;

	public StackMapTable(int attrNameIndex, int attrLen) {
		super(attrNameIndex, attrLen);		
	}

	public static StackMapTable parse(ByteCodeIterator iter){
		int index = iter.nextU2ToInt();
		int len = iter.nextU4ToInt();
		StackMapTable t = new StackMapTable(index,len);
		
		String code = iter.nextUxToHexString(len);
		t.setOriginalCode(code);
		
		return t;
	}

	private void setOriginalCode(String code) {
		this.originalCode = code;
		
	}
}
