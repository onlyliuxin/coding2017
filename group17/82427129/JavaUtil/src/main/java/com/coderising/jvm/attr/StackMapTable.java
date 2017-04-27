package com.coderising.jvm.attr;

public class StackMapTable extends AttributeInfo{

	private static String TYPE = STACK_MAP_TABLE;
	@Override
	public String getType() {
		return TYPE;
	}
	public StackMapTable(int attrNameIndex, int attrLen) {
		super(attrNameIndex, attrLen);
	}
}
