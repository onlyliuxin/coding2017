package com.coderising.jvm.print;

import com.coderising.jvm.constant.ClassInfo;
import com.coderising.jvm.constant.ConstantInfo;
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
			public void visitString(StringInfo info) {
				StringBuilder buffer = new StringBuilder();
				buffer.append("String    #").append(info.getIndex());
				System.out.println(buffer);
				
			}
			
			@Override
			public void visitNameAndType(NameAndTypeInfo info) {
				StringBuilder buffer = new StringBuilder();
				buffer.append("NameAndType    #").append(info.getIndex1()).append(":#")
				.append(info.getIndex2());
				System.out.println(buffer);
				
			}
			
			@Override
			public void visitMethodRef(MethodRefInfo info) {
				StringBuilder buffer = new StringBuilder();
				buffer.append("MethodRef    #").append(info.getClassInfoIndex()).append(".#")
				.append(info.getNameAndTypeIndex());
				System.out.println(buffer);
				
			}
			
			@Override
			public void visitFieldRef(FieldRefInfo info) {
				StringBuilder buffer = new StringBuilder();
				buffer.append("FieldRef    #").append(info.getClassInfoIndex()).append(".#")
				.append(info.getNameAndTypeIndex());
				System.out.println(buffer);
				
			}
			
			@Override
			public void visitClassInfo(ClassInfo info) {
				StringBuilder buffer = new StringBuilder();
				buffer.append("Class    #").append(info.getUtf8Index())
				.append("  ").append(info.getClassName());
				
				System.out.println(buffer);
				
			}
			
			@Override
			public void visistUTF8(UTF8Info info) {
				StringBuilder buffer = new StringBuilder();
				buffer.append("UTF8    ").append(info.getValue());
				System.out.println(buffer);
				
			}
		};
		
		for(int i=1; i<=pool.getSize(); i++){
			ConstantInfo constantInfo = pool.getConstantInfo(i);
			System.out.print("#"+i+"=");
			constantInfo.accept(visitor);			
		}
	}
}
