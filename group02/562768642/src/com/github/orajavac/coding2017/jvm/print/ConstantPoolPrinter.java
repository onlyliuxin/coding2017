	package com.github.orajavac.coding2017.jvm.print;

import com.github.orajavac.coding2017.jvm.constant.ClassInfo;
import com.github.orajavac.coding2017.jvm.constant.ConstantInfo;
import com.github.orajavac.coding2017.jvm.constant.ConstantPool;
import com.github.orajavac.coding2017.jvm.constant.FieldRefInfo;
import com.github.orajavac.coding2017.jvm.constant.MethodRefInfo;
import com.github.orajavac.coding2017.jvm.constant.NameAndTypeInfo;
import com.github.orajavac.coding2017.jvm.constant.StringInfo;
import com.github.orajavac.coding2017.jvm.constant.UTF8Info;

public class ConstantPoolPrinter {
	ConstantPool pool;
	ConstantPoolPrinter(ConstantPool pool){
		this.pool = pool;
	}
	public void print(){
		
		System.out.println("Constant Pool:");
		
		ConstantInfo.Visistor visistor = new ConstantInfo.Visistor(){
			public void visitString(StringInfo info){
				StringBuilder buffer = new StringBuilder();
				buffer.append("String #").append(info.getIndex());
			}
			
			public void visitClassInfo(ClassInfo info){
				StringBuilder buffer = new StringBuilder();
				buffer.append("Class #").append(info.getUtf8Index()).append(" ")
				.append(info.getClassName());
			}
			
			public void visitFieldRef(FieldRefInfo info){
				StringBuilder buffer = new StringBuilder();
				buffer.append("FiledRef #").append(info.getClassInfoIndex()).append(" ")
				.append(info.getNameAndTypeIndex());
			}
			
			public void visitMethodRef(MethodRefInfo info){
				StringBuilder buffer = new StringBuilder();
				buffer.append("MethodRef #").append(info.getClassInfoIndex()).append(" ").append(info.getParamAndReturnType());
			}
			
			public void visitNameAndType(NameAndTypeInfo info){
				StringBuilder buffer = new StringBuilder();
				buffer.append("NameAndType #").append(info.getIndex1()).append(" ").append(info.getIndex2());
			}
			
			public void visitUTF8(UTF8Info info){
				StringBuilder buffer = new StringBuilder();
				buffer.append("UTF8 #").append(info.getValue());
			}
		};
		
		for (int i=0;i<pool.getSize();i++){
			ConstantInfo constantInfo = pool.getConstantInfo(i);
			constantInfo.accept(visistor);
		}
	}
}
