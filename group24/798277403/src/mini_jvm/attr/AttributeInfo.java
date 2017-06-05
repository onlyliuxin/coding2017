package mini_jvm.attr;

/**
 * 属性表
 */
public abstract class AttributeInfo {
	public static final String CODE = "Code"; //Java代码编译成的字节码指令
	public static final String CONST_VALUE = "ConstantValue"; //final关键字定义的常量值
	public static final String EXCEPTIONS = "Exceptions"; //方法抛出的异常
	public static final String LINE_NUM_TABLE = "LineNumberTable"; //Java源码的行号和字节码指令的对应关系
	public static final String LOCAL_VAR_TABLE = "LocalVariableTable"; //方法的局部变量描述
	public static final String STACK_MAP_TABLE = "StackMapTable"; //给类型检查验证器使用
	int attrNameIndex; //属性值名称索引
	int attrLen ; //属性值长度
	public AttributeInfo(int attrNameIndex, int attrLen) {
		
		this.attrNameIndex = attrNameIndex;
		this.attrLen = attrLen;
	}
	
	
}
