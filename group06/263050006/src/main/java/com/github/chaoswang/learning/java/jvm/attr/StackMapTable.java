package com.github.chaoswang.learning.java.jvm.attr;

import com.github.chaoswang.learning.java.jvm.loader.ByteCodeIterator;

public class StackMapTable extends AttributeInfo{
	
	private String originalCode;

	public StackMapTable(int attrNameIndex, int attrLen) {
		super(attrNameIndex, attrLen);		
	}

	public static StackMapTable parse(ByteCodeIterator iter){
		int index = iter.nextU2ToInt();
		int len = iter.nextU4ToInt();
		StackMapTable t = new StackMapTable(index,len);
		
		//�����StackMapTable̫�����ӣ� ���ٴ��� ֻ��ԭʼ�Ĵ������������
		String code = iter.nextUxToHexString(len);
		t.setOriginalCode(code);
		
		return t;
	}

	private void setOriginalCode(String code) {
		this.originalCode = code;
		
	}
}
