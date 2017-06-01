package com.coding.jvm.print;

import com.coding.jvm.constant.ClassInfo;
import com.coding.jvm.constant.ConstantInfo;
import com.coding.jvm.constant.ConstantInfo.Visitor;
import com.coding.jvm.constant.ConstantPool;
import com.coding.jvm.constant.DoubleInfo;
import com.coding.jvm.constant.FieldRefInfo;
import com.coding.jvm.constant.FloatInfo;
import com.coding.jvm.constant.IntegerInfo;
import com.coding.jvm.constant.InterfaceMethodRefInfo;
import com.coding.jvm.constant.LongInfo;
import com.coding.jvm.constant.MethodRefInfo;
import com.coding.jvm.constant.NameAndTypeInfo;
import com.coding.jvm.constant.NullConstantInfo;
import com.coding.jvm.constant.StringInfo;
import com.coding.jvm.constant.UTF8Info;

public class ConstantPoolPrinter {
	ConstantPool pool;
	ConstantPoolPrinter(ConstantPool pool){
		this.pool = pool;
	}
	
	public void print(){
		System.out.println("Constant Pool:");
		VisitorImp visitor = new VisitorImp();
		for (int i = 1; i < pool.getSize(); i++) {
			System.out.print("#"+i+" = ");
			pool.getConstantInfo(i).accept(visitor);
		}
	}
	
	public class VisitorImp implements Visitor{

		@Override
		public void visitClassInfo(ClassInfo info) {
			StringBuffer buff = new StringBuffer();
			buff.append("Class\t#"+info.getUtf8Index());
			buff.append("\t//"+pool.getUTF8String(info.getUtf8Index()));
			System.out.println(buff.toString());
		}

		@Override
		public void visitUtf8Info(UTF8Info info) {
			StringBuffer buff = new StringBuffer();
			buff.append("Utf8\t"+info.getValue());
			System.out.println(buff.toString());
		}

		@Override
		public void visitStringInfo(StringInfo info) {
			StringBuffer buff = new StringBuffer();
			buff.append("String\t#"+info.getIndex());
			buff.append("\t//"+pool.getUTF8String(info.getIndex()));
			System.out.println(buff.toString());
		}

		@Override
		public void visitFieldRefInfo(FieldRefInfo info) {
			StringBuffer buff = new StringBuffer();
			buff.append("FieldRef\t#"+info.getClassInfoIndex()+" #"+info.getNameAndTypeIndex());
			ClassInfo classInfo = (ClassInfo) pool.getConstantInfo(info.getClassInfoIndex());
			NameAndTypeInfo nameAndTypeInfo = (NameAndTypeInfo) pool.getConstantInfo(info.getNameAndTypeIndex());
			buff.append("\t//"+pool.getUTF8String(classInfo.getUtf8Index()));
			buff.append(" "+pool.getUTF8String(nameAndTypeInfo.getIndex1())+":"+pool.getUTF8String(nameAndTypeInfo.getIndex2()));
			System.out.println(buff.toString());
		}

		@Override
		public void visitInterfaceMethodRefInfo(InterfaceMethodRefInfo info) {
			StringBuffer buff = new StringBuffer();
			buff.append("InterfaceMethodRef\t#"+info.getClassInfoIndex()+".#"+info.getNameAndTypeIndex());
			ClassInfo classInfo = (ClassInfo) pool.getConstantInfo(info.getClassInfoIndex());
			NameAndTypeInfo nameAndTypeInfo = (NameAndTypeInfo) pool.getConstantInfo(info.getNameAndTypeIndex());
			buff.append("\t//"+pool.getUTF8String(classInfo.getUtf8Index()));
			buff.append("."+pool.getUTF8String(nameAndTypeInfo.getIndex1())+":"+pool.getUTF8String(nameAndTypeInfo.getIndex2()));
			System.out.println(buff.toString());
		}

		@Override
		public void visitMethodRefInfo(MethodRefInfo info) {
			StringBuffer buff = new StringBuffer();
			buff.append("MethodRef\t#"+info.getClassInfoIndex()+".#"+info.getNameAndTypeIndex());
			ClassInfo classInfo = (ClassInfo) pool.getConstantInfo(info.getClassInfoIndex());
			NameAndTypeInfo nameAndTypeInfo = (NameAndTypeInfo) pool.getConstantInfo(info.getNameAndTypeIndex());
			buff.append("\t//"+pool.getUTF8String(classInfo.getUtf8Index()));
			buff.append("."+pool.getUTF8String(nameAndTypeInfo.getIndex1())+":"+pool.getUTF8String(nameAndTypeInfo.getIndex2()));
			System.out.println(buff.toString());
		}

		@Override
		public void visitNameAndTypeInfo(NameAndTypeInfo info) {
			StringBuffer buff = new StringBuffer();
			buff.append("NameAndType\t#"+info.getIndex1()+":#"+info.getIndex2());
			buff.append("\t//"+pool.getUTF8String(info.getIndex1())+":"+pool.getUTF8String(info.getIndex2()));
			System.out.println(buff.toString());
		}

		@Override
		public void visitIntegerInfo(IntegerInfo info) {
			StringBuffer buff = new StringBuffer();
			buff.append("Integer\t"+info.getValue());
			System.out.println(buff.toString());
		}

		@Override
		public void visitDoubleInfo(DoubleInfo info) {
			StringBuffer buff = new StringBuffer();
			buff.append("Double\t"+info.getValue());
			System.out.println(buff.toString());
		}

		@Override
		public void visitFloatInfo(FloatInfo info) {
			StringBuffer buff = new StringBuffer();
			buff.append("Float\t"+info.getValue());
			System.out.println(buff.toString());
		}

		@Override
		public void visitLongInfo(LongInfo info) {
			StringBuffer buff = new StringBuffer();
			buff.append("Long\t"+info.getValue());
			System.out.println(buff.toString());
		}
	}
}
