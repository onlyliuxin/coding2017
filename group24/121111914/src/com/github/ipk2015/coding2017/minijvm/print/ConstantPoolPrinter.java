package com.github.ipk2015.coding2017.minijvm.print;

import com.github.ipk2015.coding2017.minijvm.constant.ClassInfo;
import com.github.ipk2015.coding2017.minijvm.constant.ConstantInfo;
import com.github.ipk2015.coding2017.minijvm.constant.ConstantPool;
import com.github.ipk2015.coding2017.minijvm.constant.FieldRefInfo;
import com.github.ipk2015.coding2017.minijvm.constant.MethodRefInfo;
import com.github.ipk2015.coding2017.minijvm.constant.NameAndTypeInfo;
import com.github.ipk2015.coding2017.minijvm.constant.StringInfo;
import com.github.ipk2015.coding2017.minijvm.constant.UTF8Info;

public class ConstantPoolPrinter {
	ConstantPool pool;
	ConstantPoolPrinter(ConstantPool pool){
		this.pool = pool;
	}
	public void print(){
		
		System.out.println("Constant Pool:");
		
		ConstantInfo.Visitor visitor = new ConstantInfo.Visitor(){

			@Override
			public void visitClassInfo(ClassInfo info) {
				int utf8Index = info.getUtf8Index();
				String className = info.getClassName();
				sop("Class\t\t"+"#"+utf8Index+"\t\t// "+className);
			}

			@Override
			public void visitFieldRef(FieldRefInfo info) {
				int classInfoIndex = info.getClassInfoIndex();
				int nameAndTypeIndex = info.getNameAndTypeIndex();
				String className = info.getClassName();
				String fieldName = info.getFieldName();
				String fieldType = info.getFieldType();
				sop("Fieldref\t\t"+"#"+classInfoIndex+".#"+nameAndTypeIndex+"\t\t// "
				+className+"."+fieldName+":"+fieldType);
			}

			@Override
			public void visitMethodRef(MethodRefInfo info) {
				int classInfoIndex = info.getClassInfoIndex();
				int nameAndTypeIndex = info.getNameAndTypeIndex();
				String className = info.getClassName();
				String methodName = info.getMethodName();
				String paramAndReturnType = info.getParamAndReturnType();
				sop("Methodref\t\t"+"#"+classInfoIndex+".#"+nameAndTypeIndex+"\t\t// "
				+className+"."+methodName+":"+paramAndReturnType);
				
			}

			@Override
			public void visitNameAndType(NameAndTypeInfo info) {
				int index1 = info.getIndex1();
				int index2 = info.getIndex2();
				String name = info.getName();
				String typeInfo = info.getTypeInfo();
				sop("NameAndType\t"+"#"+index1+".#"+index2+"\t\t// "+name+":"+typeInfo);
			}

			@Override
			public void visitString(StringInfo info) {
				int index = info.getIndex();
				String utf8String = info.getConstantPool().getUTF8String(index);
				sop("String\t\t"+"#"+index+"\t\t// "+utf8String);
			}

			@Override
			public void visistUTF8(UTF8Info info) {
				sop("Utf8\t\t"+info.getValue());
			}
			
		};
		
		int size = pool.getSize();
		for(int i = 1;i < size+1;i++){
			ConstantInfo constantInfo = pool.getConstantInfo(i);
			System.out.print("\t"+"#"+i+" = ");
			constantInfo.accept(visitor);
		}
		
	}
	
	public static void sop(String s){
		System.out.println(s);
	}
}
