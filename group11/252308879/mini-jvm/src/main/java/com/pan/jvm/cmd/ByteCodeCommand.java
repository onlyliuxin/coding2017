package com.pan.jvm.cmd;

import java.util.HashMap;
import java.util.Map;

import com.pan.jvm.clz.ClassFile;
import com.pan.jvm.constant.ConstantInfo;
import com.pan.jvm.constant.ConstantPool;
import com.pan.jvm.engine.ExecutionResult;
import com.pan.jvm.engine.StackFrame;


public abstract class ByteCodeCommand {	
	
	String opCode;
	ClassFile clzFile;	
	private int offset;
	
	public static final String aconst_null = "01";
	public static final String new_object = "BB";
	public static final String lstore = "37";
	public static final String invokespecial = "B7";
	public static final String invokevirtual = "B6";
	public static final String getfield = "B4";
	public static final String putfield = "B5";
	public static final String getstatic = "B2";
	public static final String ldc = "12";
	public static final String dup = "59";
	public static final String bipush = "10";
	public static final String aload_0 = "2A";
	public static final String aload_1 = "2B";
	public static final String aload_2 = "2C";
	public static final String iload = "15";
	public static final String iload_1 = "1B";
	public static final String iload_2 = "1C";
	public static final String iload_3 = "1D";
	public static final String fload_3 = "25";

	public static final String voidreturn = "B1";
	public static final String ireturn = "AC";
	public static final String freturn = "AE";

	public static final String astore_1 = "4C";
	public static final String if_icmp_ge = "A2";
	public static final String if_icmple = "A4";
	public static final String goto_no_condition = "A7";
	public static final String iconst_0 = "03";
	public static final String iconst_1 = "04";
	public static final String istore_1 = "3C";
	public static final String istore_2 = "3D";
	public static final String iadd = "60";
	public static final String iinc = "84";
	private static Map<String,String> codeMap = new HashMap<String,String>();
	
	static{
		codeMap.put("01", "aconst_null");
		
		codeMap.put("BB", "new");
		codeMap.put("37", "lstore");
		codeMap.put("B7", "invokespecial");
		codeMap.put("B6", "invokevirtual");
		codeMap.put("B4", "getfield");
		codeMap.put("B5", "putfield");
		codeMap.put("B2", "getstatic");
		
		codeMap.put("2A", "aload_0");
		codeMap.put("2B", "aload_1");
		codeMap.put("2C", "aload_2");
		
		codeMap.put("10", "bipush");
		codeMap.put("15", "iload");
		codeMap.put("1A", "iload_0");
		codeMap.put("1B", "iload_1");
		codeMap.put("1C", "iload_2");
		codeMap.put("1D", "iload_3");
		
		codeMap.put("25", "fload_3");
		
		codeMap.put("1E", "lload_0");
		
		codeMap.put("24", "fload_2");
		codeMap.put("4C", "astore_1");
		
		codeMap.put("A2", "if_icmp_ge");
		codeMap.put("A4", "if_icmple");
		
		codeMap.put("A7", "goto");
		
		codeMap.put("B1", "return");
		codeMap.put("AC", "ireturn");
		codeMap.put("AE", "freturn");
		
		codeMap.put("03", "iconst_0");
		codeMap.put("04", "iconst_1");
		
		codeMap.put("3C", "istore_1");
		codeMap.put("3D", "istore_2");
		
		codeMap.put("59", "dup");
		
		codeMap.put("60", "iadd");
		codeMap.put("84", "iinc");
		
		codeMap.put("12", "ldc");
	}
	
	

	

	protected ByteCodeCommand(ClassFile clzFile, String opCode){
		this.clzFile = clzFile;
		this.opCode = opCode;
	}
	
	protected ClassFile getClassFile() {
		return clzFile;
	}
	
	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}
	protected ConstantInfo getConstantInfo(int index){
		return this.getClassFile().getConstantPool().getConstantInfo(index);
	}
	
	protected ConstantPool getConstantPool(){
		return this.getClassFile().getConstantPool();
	}
	
	
	
	public String getOpCode() {
		return opCode;
	}

	public abstract int getLength();
	
	public String getReadableCodeText(){
		String txt = codeMap.get(opCode);
		if(txt == null){
			return opCode;
		}
		return txt;
	}
	
	public abstract void execute(StackFrame frame,ExecutionResult result);
}
