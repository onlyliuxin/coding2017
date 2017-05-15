package com.coderising.jvm.print;

import java.nio.Buffer;

import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.constant.ClassInfo;
import com.coderising.jvm.constant.ConstantInfo;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.constant.FieldRefInfo;
import com.coderising.jvm.constant.MethodRefInfo;
import com.coderising.jvm.constant.NameAndTypeInfo;
import com.coderising.jvm.constant.StringInfo;
import com.coderising.jvm.constant.UTF8Info;
import com.coderising.jvm.loader.ClassFileLoader;

public class ConstantPoolPrinter {
	ConstantPool pool;
	ConstantPoolPrinter(ConstantPool pool){
		this.pool = pool;
	}
	public void print(){
		
		System.out.println("Constant Pool:");
		
		for (int i = 0; i < pool.getSize(); i++) {
			ConstantInfo info=pool.getConstantInfo(i);
			StringBuffer pringBuffer=new StringBuffer();
			if (i<10) {
				pringBuffer.append(" #"+Integer.toString(i)+" ");
			}
			else {
				pringBuffer.append("#"+Integer.toString(i)+" ");
			}
			if (info instanceof UTF8Info) {
				pringBuffer.append("Utf8");
				String buffer=String.format("%-30s", pringBuffer);
				pringBuffer=new StringBuffer(buffer);
				UTF8Info utf8Info=(UTF8Info)info;
				pringBuffer.append(utf8Info.getValue());
			}
			else if (info instanceof ClassInfo) {
				pringBuffer.append("Class");
				String buffer=String.format("%-30s", pringBuffer);
				pringBuffer=new StringBuffer(buffer);
				ClassInfo classInfo=(ClassInfo)info;
				pringBuffer.append("#"+Integer.toString(classInfo.getUtf8Index()));
				buffer=String.format("%-40s", pringBuffer);
				pringBuffer=new StringBuffer(buffer);
				UTF8Info utf8Info=(UTF8Info)pool.getConstantInfo(classInfo.getUtf8Index());
				pringBuffer.append("// "+utf8Info.getValue());
			}
			else if (info instanceof NameAndTypeInfo) {
				pringBuffer.append("NameAndType");
				String buffer=String.format("%-30s", pringBuffer);
				pringBuffer=new StringBuffer(buffer);
				NameAndTypeInfo nameAndTypeInfo=(NameAndTypeInfo)info;
				int index1=nameAndTypeInfo.getIndex1();
				int index2=nameAndTypeInfo.getIndex2();
				pringBuffer.append("#"+Integer.toString(index1)+";"+"#"+Integer.toString(index2));
				buffer=String.format("%-40s", pringBuffer);
				pringBuffer=new StringBuffer(buffer);
				String string1=((UTF8Info)pool.getConstantInfo(index1)).getValue();
				String string2=((UTF8Info)pool.getConstantInfo(index2)).getValue();
				pringBuffer.append("// "+string1+";"+string2);
				
			}
			else if (info instanceof FieldRefInfo) {
				pringBuffer.append("FieldRef");
				String buffer=String.format("%-30s", pringBuffer);
				pringBuffer=new StringBuffer(buffer);
				FieldRefInfo fieldRefInfo=(FieldRefInfo)info;
				int classInfoIndex=fieldRefInfo.getClassInfoIndex();
				int nameAndTypeIndex=fieldRefInfo.getNameAndTypeIndex();
				pringBuffer.append("#"+Integer.toString(classInfoIndex)+";"+"#"+Integer.toString(nameAndTypeIndex));
				buffer=String.format("%-40s", pringBuffer);
				pringBuffer=new StringBuffer(buffer);
				int index1=((ClassInfo)pool.getConstantInfo(classInfoIndex)).getUtf8Index();
				int index2=((NameAndTypeInfo)pool.getConstantInfo(nameAndTypeIndex)).getIndex1();
				int index3=((NameAndTypeInfo)pool.getConstantInfo(nameAndTypeIndex)).getIndex2();
				String string1=((UTF8Info)pool.getConstantInfo(index1)).getValue();
				String string2=((UTF8Info)pool.getConstantInfo(index2)).getValue();
				String string3=((UTF8Info)pool.getConstantInfo(index3)).getValue();
				pringBuffer.append("// "+string1+";"+string2+";"+string3);
				}
			else if (info instanceof MethodRefInfo) {
				pringBuffer.append("MethodRef");
				String buffer=String.format("%-30s", pringBuffer);
				pringBuffer=new StringBuffer(buffer);
				MethodRefInfo methodRefInfo=(MethodRefInfo)info;
				int classInfoIndex=methodRefInfo.getClassInfoIndex();
				int nameAndTypeIndex=methodRefInfo.getNameAndTypeIndex();
				pringBuffer.append("#"+Integer.toString(classInfoIndex)+";"+"#"+Integer.toString(nameAndTypeIndex));
				buffer=String.format("%-40s", pringBuffer);
				pringBuffer=new StringBuffer(buffer);
				int index1=((ClassInfo)pool.getConstantInfo(classInfoIndex)).getUtf8Index();
				int index2=((NameAndTypeInfo)pool.getConstantInfo(nameAndTypeIndex)).getIndex1();
				int index3=((NameAndTypeInfo)pool.getConstantInfo(nameAndTypeIndex)).getIndex2();
				String string1=((UTF8Info)pool.getConstantInfo(index1)).getValue();
				String string2=((UTF8Info)pool.getConstantInfo(index2)).getValue();
				String string3=((UTF8Info)pool.getConstantInfo(index3)).getValue();
				pringBuffer.append("// "+string1+";"+string2+";"+string3);
			}
			else if (info instanceof StringInfo) {
				pringBuffer.append("String");
				String buffer=String.format("%-30s", pringBuffer);
				pringBuffer=new StringBuffer(buffer);
				StringInfo stringInfo=(StringInfo)info;
				pringBuffer.append("#"+Integer.toString(stringInfo.getIndex()));
				buffer=String.format("%-40s", pringBuffer);
				pringBuffer=new StringBuffer(buffer);
				UTF8Info utf8Info=(UTF8Info)pool.getConstantInfo(stringInfo.getIndex());
				pringBuffer.append("// "+utf8Info.getValue());
			}
			System.out.println(pringBuffer);
		}
		
		
	}
	public static void main(String[] args) {
		String path = "F:\\mycoding2017\\group11\\996108220\\bin";
		ClassFileLoader loader = new ClassFileLoader();
		loader.addClassPath(path);
		String className = "com.coderising.jvm.test.EmployeeV1";
		
		ClassFile clzFile = loader.loadClass(className);
		ConstantPool pool=clzFile.getConstantPool();
		ConstantPoolPrinter printer=new ConstantPoolPrinter(pool);
		printer.print();
	}
}
