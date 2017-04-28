package coderising.jvm.print;

import coderising.jvm.constant.ClassInfo;
import coderising.jvm.constant.ConstantInfo;
import coderising.jvm.constant.ConstantPool;
import coderising.jvm.constant.FieldRefInfo;
import coderising.jvm.constant.MethodRefInfo;
import coderising.jvm.constant.NameAndTypeInfo;
import coderising.jvm.constant.StringInfo;
import coderising.jvm.constant.UTF8Info;

public class ConstantPoolPrinter {
	ConstantPool pool;

	ConstantPoolPrinter(ConstantPool pool) {
		this.pool = pool;
	}

	public void print() {

		System.out.println("Constant Pool:");
		ConstantInfo.Visitor visitor = new ConstantInfo.Visitor() {

			@Override
			public void visitUTF8Info(UTF8Info info) {
				System.out.println("Utf8"+"\t\t"+"" + info.getValue());
			}

			@Override
			public void visitClassInfo(ClassInfo info) {
				System.out.print("Class"+"\t\t"+"#" + info.getUtf8Index());
				System.out.print("    // " + info.getClassName());
				System.out.println();
			}

			@Override
			public void visitFieldRefInfo(FieldRefInfo info) {
				System.out.print("Fieldref"+"\t\t"+"#" + info.getClassInfoIndex() + ".#" + info.getNameAndTypeIndex());
				System.out.println("    // " + info.getClassName());

			}

			@Override
			public void visitMethodRefInfo(MethodRefInfo info) {
				System.out.print("Methodref"+"\t\t"+"#" + info.getClassInfoIndex() + ".#" + info.getNameAndTypeIndex());
				System.out.println("    // " + info.getClassName());

			}

			@Override
			public void visitNameAndTypeInfo(NameAndTypeInfo info) {
				System.out.print("NameAndType"+"\t\t"+"#" + info.getIndex1() + ".#" + info.getIndex2());
				System.out.println("    // " + info.getName());

			}

			@Override
			public void visitStringInfo(StringInfo info) {
				System.out.print(" String"+"\t\t"+"#" + info.getIndex());
				System.out.println("    // " + info.toString());

			}

		};
		for (int i = 1; i <= (Integer) pool.getSize(); i++) {
			ConstantInfo info = pool.getConstantInfo(i);
			System.out.print(" #" + i + " " + "=" + " ");
			info.accept(visitor);
		}

	}
}
