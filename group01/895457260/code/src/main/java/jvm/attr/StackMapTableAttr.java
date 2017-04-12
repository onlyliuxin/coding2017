package jvm.attr;

import jvm.util.ByteCodeIterator;

public class StackMapTableAttr extends AttributeInfo{
	
	private String originalCode;

	private StackMapTableAttr(int attrNameIndex, int attrLen) {
		super(attrNameIndex, attrLen);		
	}

	public static StackMapTableAttr parse(ByteCodeIterator iterator) {
		int index = iterator.nextU2ToInt();
		int len = iterator.nextU4ToInt();
		StackMapTableAttr result = new StackMapTableAttr(index, len);
		
		//后面的StackMapTable太过复杂， 不再处理， 只把原始的代码读进来保存
		String code = iterator.nextHexString(len);
		result.setOriginalCode(code);
		
		return result;
	}

	private void setOriginalCode(String code) {
		this.originalCode = code;
	}
}
