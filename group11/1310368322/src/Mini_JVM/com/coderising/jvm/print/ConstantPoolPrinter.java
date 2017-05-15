package com.coderising.jvm.print;

import com.coderising.jvm.constant.ClassInfo;
import com.coderising.jvm.constant.ConstantInfo;
import com.coderising.jvm.constant.ConstantInfo.Visitor;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.constant.FieldRefInfo;
import com.coderising.jvm.constant.MethodRefInfo;
import com.coderising.jvm.constant.NameAndTypeInfo;
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
			public void visitUTF8(UTF8Info info) {
				StringBuffer buffer = new StringBuffer();
				buffer.append("Utf8                ").append(info.getValue());
				System.out.println(buffer);
				
			}
			
			@Override
			public void visitString(StringInfo info) {
				StringBuffer buffer = new StringBuffer();
				buffer.append("String              #").append(info.getIndex());
				System.out.println(buffer);
				
			}
			
			@Override
			public void visitNameAndType(NameAndTypeInfo info) {
				StringBuffer buffer = new StringBuffer();
				buffer.append("NameAndTypeInfo     #").append(info.getIndex1()).append(
						info.getIndex2());
				System.out.println(buffer);
				
			}
			
			@Override
			public void visitMethodRef(MethodRefInfo info) {
				StringBuffer buffer = new StringBuffer();
				buffer.append("MethodRefInfo       #").append(info.getClassInfoIndex()+"#").append(
						info.getNameAndTypeIndex());
				System.out.println(buffer);
				
			}
			
			@Override
			public void visitFieldRef(FieldRefInfo info) {
				StringBuffer buffer = new StringBuffer();
				buffer.append("FieldRef            #").append(info.getClassInfoIndex()).append(
						info.getNameAndTypeIndex());
				System.out.println(buffer);
			}
			
			@Override
			public void visitClassInfo(ClassInfo info) {
				StringBuffer buffer = new StringBuffer();
				buffer.append("Class               #").append(info.getUtf8Index());
				System.out.println(buffer);
			}
		};
		
		for (int i = 1; i <= pool.getSize(); i++) {
			ConstantInfo constatnInfo = pool.getConstantInfo(i);
			System.out.print("#"+i+" = ");
			constatnInfo.accept(visitor);
		}
		
		
	}
}
