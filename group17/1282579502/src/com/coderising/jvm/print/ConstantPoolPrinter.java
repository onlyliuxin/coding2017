package com.coderising.jvm.print;

import com.coderising.jvm.constant.ClassInfo;
import com.coderising.jvm.constant.ConstantInfo;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.constant.FieldRefInfo;
import com.coderising.jvm.constant.FloatRefInfo;
import com.coderising.jvm.constant.IntegerInfo;
import com.coderising.jvm.constant.MethodRefInfo;
import com.coderising.jvm.constant.NameAndTypeInfo;
import com.coderising.jvm.constant.NullConstantInfo;
import com.coderising.jvm.constant.StringInfo;
import com.coderising.jvm.constant.UTF8Info;

public class ConstantPoolPrinter implements ConstantPoolPrinterInterface{
	ConstantPool pool;
	public ConstantPoolPrinter(ConstantPool pool){
		this.pool = pool;
	}
	int currentIndex = 0;
	String EightSpaces = "        ";
	public void print(){
		StringBuffer sb = new StringBuffer();
		System.out.println("Constant Pool Printer:");
		for(int i = 0; i< pool.getSize(); i++){
			ConstantInfo info = pool.getConstantInfo(i);
			info.print(this);
			currentIndex++;
			
		}
		
		
		
	}
	@Override
	public void printUtf8Info(UTF8Info info) {
//		System.out.println("#" + currentIndex + " = " + "Utf8" + EightSpaces + "#" + info.getValue());
		StringBuffer sb = new StringBuffer();
		sb.append("#");
		sb.append(String.format("%2d", currentIndex));
		sb.append(String.format("%-20s", " = Utf8"));
		sb.append(String.format("%-30s", "#" + info.getValue() ));
		System.out.println(sb.toString());
	}
	@Override
	public void printIntegerInfo(IntegerInfo info) {
		System.out.println("#" + currentIndex + " = " + "Integer" + EightSpaces + info.getInteger() );
		
	}
	@Override
	public void printFloatInfo(FloatRefInfo info) {
		System.out.println("#" + currentIndex + " = " + "FloatRef" + EightSpaces + info.getFloat());
		
		
	}
	@Override
	public void printClassInfo(ClassInfo info) {
		StringBuffer sb = new StringBuffer();
		sb.append("#");
		sb.append(String.format("%2d", currentIndex));
		//sb.append(" = Class");
		sb.append(String.format("%-20s", " = Class"));
		sb.append(String.format("%-30s", "#" + info.getUtf8Index() ));
		sb.append(String.format("%-40s", "//"+info.getClassName()));
		System.out.println(sb.toString());
	}
	@Override
	public void printStringInfo(StringInfo info) {
		System.out.println("#" + currentIndex + " = " + "String" + EightSpaces + "#" + info.getIndex() + EightSpaces +  "//" + info.getConstantInfo(info.getIndex()));

	}
	@Override
	public void printFieldRefInfo(FieldRefInfo info) {
		System.out.println("#" + currentIndex + " = " + "FieldRef" + EightSpaces + "#" + info.getClassInfoIndex() + " " + info.getNameAndTypeIndex() + EightSpaces +  "//" + info.getClassName() + "." + info.getFieldName() );

	}
	@Override
	public void printMethodRefInfo(MethodRefInfo info) {
		System.out.println("#" + currentIndex + " = " + "MethodRef" + EightSpaces + "#" + info.getClassInfoIndex() + " " + info.getNameAndTypeIndex() + EightSpaces +  "//" + info.getClassName() + "." + info.getMethodName() );

		
	}
	@Override
	public void printNameAndTypeInfo(NameAndTypeInfo info) {
		System.out.println("#" + currentIndex + " = " + "NameAndTypeRef" + EightSpaces + "#" + info.getIndex1() + " " + info.getIndex2() + EightSpaces +  "//" + info.getName() + "." + info.getTypeInfo() );

		
	}
	@Override
	public void printNullConstInfo(NullConstantInfo info) {
		// TODO Auto-generated method stub
		
	}

}
