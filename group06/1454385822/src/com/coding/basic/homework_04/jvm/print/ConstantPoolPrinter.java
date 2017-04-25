package com.coding.basic.homework_04.jvm.print;

import com.coding.basic.homework_04.jvm.constant.ConstantInfo;
import com.coding.basic.homework_04.jvm.constant.ConstantInfo.Visitor;
import com.coding.basic.homework_04.jvm.constant.ConstantPool;
import com.coding.basic.homework_04.jvm.info.ClassInfo;
import com.coding.basic.homework_04.jvm.info.FieldRefInfo;
import com.coding.basic.homework_04.jvm.info.MethodRefInfo;
import com.coding.basic.homework_04.jvm.info.NameAndTypeInfo;
import com.coding.basic.homework_04.jvm.info.StringInfo;
import com.coding.basic.homework_04.jvm.info.UTF8Info;

public class ConstantPoolPrinter {
	ConstantPool pool;
	ConstantPoolPrinter(ConstantPool pool){
		this.pool = pool;
	}
	public void print(){
		
		System.out.println("Constant Pool:");
		
		ConstantInfo.Visitor consoleVisitor = new Visitor() {
			
			@Override
			public void visitString(StringInfo info) {
				StringBuilder builder = new StringBuilder();
				int stringIndex = info.getString_index();
				
				builder.append("String     #").append(stringIndex)
				.append("      //" + pool.getUTF8String(stringIndex));
				
				System.out.println(builder.toString());
			}
			
			@Override
			public void visitNameAndType(NameAndTypeInfo info) {
				StringBuilder builder = new StringBuilder();
				int index1 = info.getIndex1();
				int index2 = info.getIndex2();
				
				builder.append("NameAndType   #").append(index1 +":#")
				.append(index2)
				.append("    //" + pool.getUTF8String(index1))
				.append(":" + pool.getUTF8String(index2));
				
				System.out.println(builder.toString());
			}
			
			@Override
			public void visitMethodRef(MethodRefInfo info) {
				StringBuilder builder = new StringBuilder();
				int clzIndex = info.getClass_index();
				int nameAndTypeIndex = info.getNameAndType_index();
				ClassInfo clzInfo = (ClassInfo) pool.getConstantInfo(clzIndex);
				NameAndTypeInfo nameAndTypeInfo = (NameAndTypeInfo)pool.getConstantInfo(nameAndTypeIndex);
				
				builder.append("MethodRef      #").append(clzIndex+".#")
				.append(nameAndTypeIndex)
				.append("    //"+ clzInfo.getClassName())
				.append("." + pool.getUTF8String(nameAndTypeInfo.getIndex1()) + ":")
				.append(pool.getUTF8String(nameAndTypeInfo.getIndex2()));
				
				System.out.println(builder.toString());
			}
			
			@Override
			public void visitFieldRef(FieldRefInfo info) {
				StringBuilder builder = new StringBuilder();
				int clzIndex = info.getClz_index();
				int nameAndTypeIndex = info.getNameAndType_index();
				ClassInfo clzInfo = (ClassInfo) pool.getConstantInfo(clzIndex);
				NameAndTypeInfo nameAndTypeInfo = (NameAndTypeInfo)pool.getConstantInfo(nameAndTypeIndex);
				
				builder.append("FieldRef    #").append(clzIndex + ".#")
				.append(nameAndTypeIndex)
				.append("    //" + clzInfo.getClassName())
				.append("." + pool.getUTF8String(nameAndTypeInfo.getIndex1()) + ":")
				.append(pool.getUTF8String(nameAndTypeInfo.getIndex2()));
				
				System.out.println(builder.toString());
			}
			
			@Override
			public void visitClassInfo(ClassInfo info) {
				StringBuilder builder = new StringBuilder();
				builder.append("Class      #").append(info.getUtf8Index()+"   //")
				.append(info.getClassName());
				System.out.println(builder.toString());
			}
			
			@Override
			public void visistUTF8(UTF8Info info) {
				StringBuilder builder = new StringBuilder();
				builder.append("Utf8      ").append(info.getValue());
				System.out.println(builder.toString());
			}
		};
		
		for(int i = 1; i <= pool.getSize(); i++){
			System.out.print("#" + i + " = ");
			pool.getConstantInfo(i).accept(consoleVisitor);
		}
		
	}
}
