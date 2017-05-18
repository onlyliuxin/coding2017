package com.coderising.jvm.print;

import java.util.concurrent.SynchronousQueue;

import com.coderising.jvm.constant.ClassInfo;
import com.coderising.jvm.constant.ConstantInfo;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.constant.FieldRefInfo;
import com.coderising.jvm.constant.MethodRefInfo;
import com.coderising.jvm.constant.NameAndTypeInfo;
import com.coderising.jvm.constant.NullConstantInfo;
import com.coderising.jvm.constant.StringInfo;
import com.coderising.jvm.constant.UTF8Info;

public class ConstantPoolPrinter {
	ConstantPool pool;
	ConstantPoolPrinter(ConstantPool pool){
		this.pool = pool;
	}
	public void print(){
		
		System.out.println("Constant Pool:");
		
		ConstantInfo.Visitor visitor = new ConstantInfo.Visitor() {
			
			@Override
			public void visitUTF8Info(UTF8Info utf8Info) {
				System.out.println("UTF8           " + utf8Info.getValue());
			}
			
			@Override
			public void visitStringInfo(StringInfo stringInfo) {
				System.out.println("String         " + stringInfo.toString());
			}
			
			@Override
			public void visitNullConstantInfo(NullConstantInfo nullConstantInfo) {
				
			}
			
			@Override
			public void visitNameAndTypeInfo(NameAndTypeInfo nameAndTypeInfo) {
				System.out.println("NameAndType    " + nameAndTypeInfo.getIndex1() + ":" + nameAndTypeInfo.getIndex2()
						+ "    // " + nameAndTypeInfo.getName() + ":" + nameAndTypeInfo.getTypeInfo());
			}
			
			@Override
			public void visitMethodRefInfo(MethodRefInfo methodRefInfo) {
				System.out.println("Methodref      " + methodRefInfo.getClassInfoIndex() + "." 
						+ methodRefInfo.getNameAndTypeIndex() + "    // "
						+ methodRefInfo.getClassName() + "." + methodRefInfo.getMethodName()
						+ ":" + methodRefInfo.getParamAndReturnType());
			}
			
			@Override
			public void visitFieldRefInfo(FieldRefInfo fieldRefInfo) {
				System.out.println("Fieldref       " + fieldRefInfo.getClassInfoIndex() + "."
						+ fieldRefInfo.getNameAndTypeIndex() + "    // "
						+ fieldRefInfo.getClassName() + "." + fieldRefInfo.getFieldName() + ":"
						+ fieldRefInfo.getFieldType());
			}
			
			@Override
			public void visitClassInfo(ClassInfo classInfo) {
				System.out.println("Class          " + classInfo.getUtf8Index() + "    // " + classInfo.getClassName());
			}
		};
		
		for(int i=1;i<this.pool.getSize();i++){
			if(i < 10){
				System.out.print(" ");
			}
			System.out.print("  #" + i + " = ");
			this.pool.getConstantInfo(i).accept(visitor);
		}
	}
}
