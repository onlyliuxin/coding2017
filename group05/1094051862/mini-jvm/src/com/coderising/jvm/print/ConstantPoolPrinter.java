package com.coderising.jvm.print;

import java.util.Formatter;

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
		
		Formatter formatter = new Formatter(System.out);
		ConstantInfo.Visitor visitor = new ConstantInfo.Visitor() {
			
			@Override
			public void visitUTF8(UTF8Info info) {
				StringBuilder buffer = new StringBuilder();
				buffer.append("UTF8");
				formatter.format("%-15s %-10s\n", 
						"UTF8",info.getValue());
			}
			
			@Override
			public void visitString(StringInfo info) {
				StringBuilder buffer = new StringBuilder();
				buffer.append("#").append(info.getIndex());
				formatter.format("%-15s %-10s %-10s\n", 
						"String", buffer, "// "+pool.getUTF8String(info.getIndex()));
			}
			
			@Override
			public void visitNameAndType(NameAndTypeInfo info) {
				StringBuilder buffer = new StringBuilder();
				buffer.append("#").append(info.getIndex1()).append(":#")
				.append(info.getIndex2());
				
				formatter.format("%-15s %-10s %-10s\n",
						 "NameAndType", buffer,
						 "// "+pool.getUTF8String(info.getIndex1())+":"+pool.getUTF8String(info.getIndex2()));
			}
			
			@Override
			public void visitMethodRef(MethodRefInfo info) {
				StringBuilder buffer = new StringBuilder();
				buffer.append("#").append(info.getClassInfoIndex()).append(".#")
				.append(info.getNameAndTypeIndex());
				formatter.format("%-15s %-10s %-10s\n", 
						"MethodRef", buffer, 
						"// "+info.getClassName()+"."+info.getMethodName()+":"+info.getParamAndReturnType());
			}
			
			@Override
			public void visitFieldRef(FieldRefInfo info) {
				StringBuilder buffer = new StringBuilder();
				buffer.append("#").append(info.getClassInfoIndex()).append(".#")
				.append(info.getNameAndTypeIndex());
				formatter.format("%-15s %-10s %-10s\n", 
						"FieldRef", buffer, 
						"// "+info.getClassName()+"."+info.getFieldName()+":"+info.getFieldType());
			}
			
			@Override
			public void visitClassInfo(ClassInfo info) {
				StringBuilder buffer = new StringBuilder();
				buffer.append("#").append(info.getUtf8Index());
				formatter.format("%-15s %-10s %-10s\n", 
						"Class", buffer, "// "+info.getClassName());
			}
		};
		
		for(int i = 1; i <= pool.getSize(); i++) {
			ConstantInfo constantInfo = pool.getConstantInfo(i);
			System.out.printf("%8s", "#" + i + " = ");//左对齐，8个空格
			constantInfo.accept(visitor);
		}
	}
}
