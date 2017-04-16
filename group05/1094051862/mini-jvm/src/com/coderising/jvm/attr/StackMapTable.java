package com.coderising.jvm.attr;


import com.coderising.jvm.loader.ByteCodeIterator;

public class StackMapTable extends AttributeInfo{
	
	private String originalCode;

	public StackMapTable(int attrNameIndex, int attrLen) {
		super(attrNameIndex, attrLen);		
	}

	public static StackMapTable parse(ByteCodeIterator iter){
		int index = iter.nextU2toInt();
		int len = iter.nextU4toInt();
		StackMapTable t = new StackMapTable(index,len);
		
		//后面的StackMapTable太过复杂， 不再处理， 只把原始的代码读进来保存
		String code = iter.nextUxToHexString(len);
		t.setOriginalCode(code);
		
		return t;
	}

	private void setOriginalCode(String code) {
		this.originalCode = code;
		
	}
}
