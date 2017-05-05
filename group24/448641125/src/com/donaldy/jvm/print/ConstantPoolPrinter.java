package com.donaldy.jvm.print;

import com.donaldy.jvm.clz.ClassFile;
import com.donaldy.jvm.clz.ClassIndex;
import com.donaldy.jvm.constant.ClassInfo;
import com.donaldy.jvm.constant.ConstantInfo;
import com.donaldy.jvm.constant.ConstantPool;
import com.donaldy.jvm.constant.FieldRefInfo;
import com.donaldy.jvm.constant.MethodRefInfo;
import com.donaldy.jvm.constant.NameAndTypeInfo;
import com.donaldy.jvm.constant.StringInfo;
import com.donaldy.jvm.constant.UTF8Info;

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
	/*public void print(){
		
		System.out.println("Constant Pool:");

		for (int i = 0; i <= this.pool.getSize(); ++i) {
			ConstantInfo cnstInfo = this.pool.getConstantInfo(i);

			if (cnstInfo instanceof  ClassInfo) {
				ClassInfo info = (ClassInfo)cnstInfo;

				StringBuilder buffer = new StringBuilder();
				buffer.append("Class    #").append(info.getUtf8Index())
					.append("  ").append(info.getClassName());

				System.out.println(buffer);
			}

			if (cnstInfo instanceof UTF8Info) {

				UTF8Info info = (UTF8Info) cnstInfo;
				StringBuilder buffer = new StringBuilder();
				buffer.append("UTF8     ").append(info.getValue());
				System.out.println(buffer);
			}



			*//*int type = constInfo.getType();
			System.out.println("type : " + type);

			if (type == ClassInfo.UTF8_INFO) {

			} else if (type == ClassInfo.FLOAT_INFO) {

			} else if (type == ClassInfo.CLASS_INFO) {

			} else if (type == ClassInfo.STRING_INFO) {

			} else if (type == ClassInfo.FIELD_INFO) {

			} else if (type == ClassInfo.METHOD_INFO) {

			} else if (type == ClassInfo.NAME_AND_TYPE_INFO) {

			}*//*
		}
		
	}*/
}
