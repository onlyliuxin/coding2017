package week07.jvm.print;

import java.util.Formatter;

import week07.jvm.constant.ClassInfo;
import week07.jvm.constant.ConstantInfo;
import week07.jvm.constant.ConstantInfo.Visitor;
import week07.jvm.constant.ConstantPool;
import week07.jvm.constant.FieldRefInfo;
import week07.jvm.constant.MethodRefInfo;
import week07.jvm.constant.NameAndTypeInfo;
import week07.jvm.constant.StringInfo;
import week07.jvm.constant.UTF8Info;

public class ConstantPoolPrinter {
	ConstantPool pool;

	ConstantPoolPrinter(ConstantPool pool) {
		this.pool = pool;
	}

	public void print() {

		System.out.println("Constant Pool:");

		Visitor visitor = new Visitor() {

			@Override
			public void visitClassInfo(ClassInfo info) {
				Formatter f = new Formatter(System.out);
				String s1 = "Class";
				String s2 = "#" + info.getUtf8Index();
				String s3 = "//" + info.getClassName();
				f.format("%-20s %-15s %-50s\n", s1, s2, s3);

			}

			@Override
			public void visitFieldRef(FieldRefInfo info) {
				Formatter f = new Formatter(System.out);
				String s1 = "FieldRef";
				String s2 = "#" + info.getClassInfoIndex() + ".#" + info.getNameAndTypeIndex();
				String s3 = "//" + info.getClassName() + "." + info.getFieldName() + ":" + info.getFieldType();
				f.format("%-20s %-15s %-50s\n", s1, s2, s3);

			}

			@Override
			public void visitMethodRef(MethodRefInfo info) {
				Formatter f = new Formatter(System.out);
				String s1 = "MethodRef";
				String s2 = "#" + info.getClassInfoIndex() + ".#" + info.getNameAndTypeIndex();
				String s3;
				if(info.getMethodName().equals("<init>")){
					s3 = "//" + info.getClassName() + ".\"" + info.getMethodName() + "\":"
						+ info.getParamAndReturnType();
				}else{
					s3 = "//" + info.getClassName() + "." + info.getMethodName() + ":"
							+ info.getParamAndReturnType();
				}
				f.format("%-20s %-15s %-50s\n", s1, s2, s3);
			}

			@Override
			public void visitNameAndType(NameAndTypeInfo info) {
				Formatter f = new Formatter(System.out);
				String s1 = "NameAndType";
				String s2 = "#" + info.getIndex1() + ":#" + info.getIndex2();
				String s3;
				if (info.getName().equals("<init>")) {
					s3 = "//" + "\"" + info.getName() + "\"" + ":" + info.getTypeInfo();
				} else {
					s3 = "//" + info.getName() + ":" + info.getTypeInfo();
				}
				f.format("%-20s %-15s %-50s\n", s1, s2, s3);
			}

			@Override
			public void visitString(StringInfo info) {
				Formatter f = new Formatter(System.out);
				String s1 = "String";
				String s2 = "#" + info.getIndex();
				String s3 = "//" + info.toString();
				f.format("%-20s %-15s %-50s\n", s1, s2, s3);

			}

			@Override
			public void visistUTF8(UTF8Info info) {
				Formatter f = new Formatter(System.out);
				String s1 = "Utf8";
				String s2 = info.getValue();
				f.format("%-20s %-15s\n", s1, s2);
			}
		};

		for (int i = 1; i <= pool.getSize(); i++) {
			ConstantInfo constantInfo = pool.getConstantInfo(i);
			System.out.printf("#%-2d = ", i);
			constantInfo.accept(visitor);
		}

	}
}
