package com.github.miniyk2012.coding2017.coderising.jvm.print;


import com.github.miniyk2012.coding2017.coderising.jvm.clz.ClassFile;
import com.github.miniyk2012.coding2017.coderising.jvm.constant.*;
import com.github.miniyk2012.coding2017.coderising.jvm.loader.ClassFileLoader;

public class ConstantPoolPrinter {
	ConstantPool pool;
	ConstantPoolPrinter(ConstantPool pool){
		this.pool = pool;
	}

	public void print(){
		System.out.println("Constant Pool:");
		Visitor visitor = new Visitor();
        for (int i=1; i<=pool.getSize(); i++) {
            System.out.printf("  #"+(i)+" = ");
            ConstantInfo constantInfo = pool.getConstantInfo(i);
            try {
                constantInfo.accept(visitor);
            } catch (Exception e) {
            }
            System.out.printf("\n");
        }
	}

	public static class Visitor {
        public void visitClassInfo(ClassInfo classInfo) {
            System.out.printf("CLASS        #" + classInfo.getUtf8Index()+"     ");
            System.out.printf("// "+classInfo.getClassName());
        }
        public void visitFieldRefInfo(FieldRefInfo fieldRefInfo) {
            System.out.printf("Fieldref     #" +
                    fieldRefInfo.getClassInfoIndex() + ".#" +
                    fieldRefInfo.getNameAndTypeIndex() +
                    "     ");
            System.out.printf("// "+fieldRefInfo.getClassName()+
                    "."+fieldRefInfo.getFieldName()+
                    ":"+fieldRefInfo.getFieldType()
            );
        }
        public void visitMethodRefInfo(MethodRefInfo methodRefInfo) {
            System.out.printf("Methodref    #" +
                    methodRefInfo.getClassInfoIndex()+".#" +
                    methodRefInfo.getNameAndTypeIndex() +
                    "     ");
            System.out.printf("// "+methodRefInfo.getClassName()+"."+
                    methodRefInfo.getMethodName()+":"+
                    methodRefInfo.getParamAndReturnType()
            );
        }
        public void visitNameAndTypeInfo(NameAndTypeInfo nameAndTypeInfo) {
            System.out.printf("NameAndType  #" +
                    nameAndTypeInfo.getIndex1()+":#" +
                    nameAndTypeInfo.getIndex2() +
                    "     ");
            System.out.printf("// "+nameAndTypeInfo.getName()+":"+nameAndTypeInfo.getTypeInfo());

        }
        public void visitNullConstantInfo(NullConstantInfo nullConstantInfo) {
            // 啥事都不干
        }
        public void visitStringInfo(StringInfo stringInfo) {
            System.out.printf("String       #" +
                    stringInfo.getIndex() +
                    "     ");
            System.out.printf("// "+stringInfo.toString());
        }
        public void visitUTF8Info(UTF8Info utf8Info) {
            System.out.printf("Utf8         "+utf8Info.getValue());
        }
    }

    public static void main(String[] args) {
        String path = ClassFilePrinter.class.getClassLoader().getResource("jvm").getPath();
        ClassFileLoader loader = new ClassFileLoader();
        loader.addClassPath(path);
        String className = "com.github.miniyk2012.coding2017.jvm.test.EmployeeV1";

        ClassFile clzFile = loader.loadClass(className);
        ConstantPoolPrinter poolPrinter = new ConstantPoolPrinter(clzFile.getConstantPool());
        poolPrinter.print();
    }
}
