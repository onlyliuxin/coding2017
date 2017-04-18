package org.xukai.jvm.attr;


import org.xukai.jvm.loader.ByteCodeIterator;

public class StackMapTable extends AttributeInfo{
	
	private String originalCode;

	public StackMapTable(int attrNameIndex, int attrLen) {
		super(attrNameIndex, attrLen);		
	}

	public static StackMapTable parse(ByteCodeIterator iter){
		int index = iter.nextToInt(2);
		int len = iter.nextToInt(4);
		StackMapTable t = new StackMapTable(index,len);
		
		//后面的StackMapTable太过复杂， 不再处理， 只把原始的代码读进来保存
		String code = iter.nextToString(len);
		t.setOriginalCode(code);
		
		return t;
	}

	private void setOriginalCode(String code) {
		this.originalCode = code;
		
	}
}
