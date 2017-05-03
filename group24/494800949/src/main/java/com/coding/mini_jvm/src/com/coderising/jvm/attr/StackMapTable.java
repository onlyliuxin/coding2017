package com.coding.mini_jvm.src.com.coderising.jvm.attr;


import com.coding.mini_jvm.src.com.coderising.jvm.loader.ByteCodeIterator;

public class StackMapTable extends AttributeInfo{
	
	private String originalCode;

	public StackMapTable(int attrNameIndex, int attrLen) {
		super(attrNameIndex, attrLen);		
	}

	public static StackMapTable parse(ByteCodeIterator iter){
		iter.back(ByteCodeIterator.U2);
		int index = iter.readTwoBytesToInt();
		int len = iter.readFourBytesToInt();
		StackMapTable t = new StackMapTable(index,len);
		
		//后面的StackMapTable太过复杂， 不再处理， 只把原始的代码读进来保存
		String code = iter.readBytesToString(len);
		t.setOriginalCode(code);
		return t;
	}

	private void setOriginalCode(String code) {
		this.originalCode = code;
		
	}
}
