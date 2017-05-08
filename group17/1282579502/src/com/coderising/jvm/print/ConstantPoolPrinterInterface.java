package com.coderising.jvm.print;

import com.coderising.jvm.constant.ClassInfo;
import com.coderising.jvm.constant.FieldRefInfo;
import com.coderising.jvm.constant.FloatRefInfo;
import com.coderising.jvm.constant.IntegerInfo;
import com.coderising.jvm.constant.MethodRefInfo;
import com.coderising.jvm.constant.NameAndTypeInfo;
import com.coderising.jvm.constant.NullConstantInfo;
import com.coderising.jvm.constant.StringInfo;
import com.coderising.jvm.constant.UTF8Info;

public interface ConstantPoolPrinterInterface {
//	public static final int UTF8_INFO = 1;
//	public static final int INTEGER_INFO = 3;
//	public static final int FLOAT_INFO = 4;
//	public static final int CLASS_INFO = 7;
//	public static final int STRING_INFO = 8;
//	public static final int FIELD_INFO = 9;
//	public static final int METHOD_INFO = 10;
//	public static final int NAME_AND_TYPE_INFO = 12;
	public void printUtf8Info(UTF8Info info);
	public void printIntegerInfo(IntegerInfo info);
	public void printFloatInfo(FloatRefInfo info);
	public void printClassInfo(ClassInfo info);
	public void printStringInfo(StringInfo info);
	public void printFieldRefInfo(FieldRefInfo info);
	public void printMethodRefInfo(MethodRefInfo info);
	public void printNameAndTypeInfo(NameAndTypeInfo info);
	public void printNullConstInfo(NullConstantInfo info);
	
}
