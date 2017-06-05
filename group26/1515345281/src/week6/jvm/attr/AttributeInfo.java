package week6.jvm.attr;

public abstract class AttributeInfo {

	public static final String CODE ="Code";
	public static final String CONSTANT_VALUE = "ConstantValue";
	public static final String EXCEPTIONS = "Exceptions";
	public static final String LINE_NUMBER_TABLE = "LineNumberTable";
	public static final String LOCAL_Variable_Table = "LocalVariableTable";
	public static final String STACK_MAP_TABLE = "StackMapValue";
	
	int attrNameIndex;
	int attrLen;
	
	public AttributeInfo(int attrNameIndex, int attrLen) {
		super();
		this.attrNameIndex = attrNameIndex;
		this.attrLen = attrLen;
	}
}
