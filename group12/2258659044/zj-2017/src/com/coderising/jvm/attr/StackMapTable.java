package com.coderising.jvm.attr;

import com.coderising.jvm.loader.ByteCodeIterator;

public class StackMapTable extends AttributeInfo{
	
	private String originalCode;

	public StackMapTable(int attrNameIndex, int attrLen) {
		super(attrNameIndex, attrLen);		
	}

	public static StackMapTable parse(ByteCodeIterator iter){
		
		int nameIndex = iter.nextU2ToInt();
		int attrLen = iter.nextU4ToInt();
		StackMapTable t = null;
		t = new StackMapTable(nameIndex,attrLen);
		
		//后面的StackMapTable太过复杂， 不再处理， 只把原始的代码读进来保存
		String code = iter.nextUxToHexString(attrLen);
		t.setOriginalCode(code);
		
		return t;
	}
	
	
	private void setOriginalCode(String code) {
		this.originalCode = code;
		
	}
		
	public String getOriginalCode() {
		return originalCode;
	}
}
