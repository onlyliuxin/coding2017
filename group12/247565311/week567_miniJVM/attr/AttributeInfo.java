package week567_miniJVM.attr;

import structure.week1.ArrayList;

public abstract class AttributeInfo {
	public static final String CODE = "Code";
	public static final String CONST_VALUE = "ConstantValue";
	public static final String EXCEPTIONS = "Exceptions";
	public static final String LINE_NUM_TABLE = "LineNumberTable";
	public static final String LOCAL_VAR_TABLE = "LocalVariableTable";
	public static final String STACK_MAP_TABLE = "StackMapTable";
	int attrNameIndex;				
	int attrLen ;
	private String attrName;
	ArrayList<AttributeInfo> attrs = new ArrayList<AttributeInfo>();
	public AttributeInfo( int attrNameIndex,int attrLen,String attrname) {
	    this.attrNameIndex = attrNameIndex;
		this.attrLen = attrLen;
		this.attrName = attrname;
	}
	public String getAttrName(){
		return attrName;
	}
	public void addAttr(AttributeInfo attrinfo){
		attrs.add(attrinfo);
	}
}


