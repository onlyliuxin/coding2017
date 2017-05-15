package mini_jvm.attr;


import mini_jvm.clz.ClassFile;
import mini_jvm.cmd.ByteCodeCommand;
import mini_jvm.cmd.CommandParser;
import mini_jvm.constant.ConstantPool;
import mini_jvm.loader.ByteCodeIterator;

public class CodeAttr extends AttributeInfo {
	private int maxStack ; //操作数栈的最大深度
	private int maxLocals ; //局部变量表所需要的存储空间
	private int codeLen ; //字节码长度
	private String code; //字节码
	private ByteCodeCommand[] cmds ; //字节码指令
	private LineNumberTable lineNumTable;
	private LocalVariableTable localVarTable;
	private StackMapTable stackMapTable;

	public String getCode() {
		return code;
	}
	public ByteCodeCommand[] getCmds() {
		return cmds;
	}
	public CodeAttr(int attrNameIndex, int attrLen, int maxStack, int maxLocals, int codeLen,String code ,ByteCodeCommand[] cmds) {
		super(attrNameIndex, attrLen);
		this.maxStack = maxStack;
		this.maxLocals = maxLocals;
		this.codeLen = codeLen;
		this.code = code;
		this.cmds = cmds;
	}

	public void setLineNumberTable(LineNumberTable t) {
		this.lineNumTable = t;
	}

	public void setLocalVariableTable(LocalVariableTable t) {
		this.localVarTable = t;
	}

	public static CodeAttr parse(ClassFile clzFile, ByteCodeIterator iter){
		int attrNameIndex = iter.nextU2ToInt();
		int attrLen = iter.nextU4ToInt();
		int maxStack = iter.nextU2ToInt();
		int maxLocals = iter.nextU2ToInt();
		int codeLen = iter.nextU4ToInt();
		String code = iter.nextUxToHexString(codeLen);
		ByteCodeCommand[] cmds = CommandParser.parse(clzFile,code);//解析字节码指令

		CodeAttr codeAttr = new CodeAttr(attrNameIndex,attrLen,maxStack,maxStack,codeLen,code,cmds);
		int exceptionTableLen = iter.nextU2ToInt(); //异常表长度
		if(exceptionTableLen>0){
			String exTable = iter.nextUxToHexString(exceptionTableLen);
			//这里对异常表不做处理
			System.out.println("Encountered exception table , just ignore it :" + exTable);
		}

		int subAttrCount = iter.nextU2ToInt(); //Code属性的子属性总数
		for(int x=1; x<=subAttrCount; x++){
			int subAttrIndex = iter.nextU2ToInt(); //子属性的索引
			String subAttrName = clzFile.getConstantPool().getUTF8String(subAttrIndex); //得到子属性的名称

			//已经向前移动了U2, 现在退回去。
			iter.back(2);
			//根据子属性的名称，分别解析
			if(AttributeInfo.LINE_NUM_TABLE.equalsIgnoreCase(subAttrName)){
				LineNumberTable t = LineNumberTable.parse(iter);
				codeAttr.setLineNumberTable(t);
			} else if(AttributeInfo.LOCAL_VAR_TABLE.equalsIgnoreCase(subAttrName)){
				LocalVariableTable t = LocalVariableTable.parse(iter);
				codeAttr.setLocalVariableTable(t);
			} else if (AttributeInfo.STACK_MAP_TABLE.equalsIgnoreCase(subAttrName)){
				StackMapTable t = StackMapTable.parse(iter);
				codeAttr.setStackMapTable(t);
			} else{
				throw new RuntimeException("Need code to process " + subAttrName);
			}
		}
		return codeAttr;
	}


	public String toString(ConstantPool pool){
		StringBuilder buffer = new StringBuilder();
		//buffer.append("Code:").append(code).append("\n");
		for(int i=0;i<cmds.length;i++){
			buffer.append(cmds[i].toString()).append("\n");
		}
		buffer.append("\n");
		buffer.append(this.lineNumTable.toString());
		buffer.append(this.localVarTable.toString(pool));
		return buffer.toString();
	}

	private void setStackMapTable(StackMapTable t) {
		this.stackMapTable = t;
	}


	
	
	
	
}
