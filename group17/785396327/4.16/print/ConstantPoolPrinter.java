package print;

import constant.*;

/**
 * Created by gongxun on 2017/4/21.
 */
public class ConstantPoolPrinter {
    ConstantPool pool;

    ConstantPoolPrinter(ConstantPool pool) {
        this.pool = pool;
    }

    public void print() {
        System.out.println("Constant Pool:");

        ConstantInfo.Visitor visitor = new ConstantInfo.Visitor() {
            @Override
            public void visitClassInfo(ClassInfo info) {
                //   #7 = Class              #44            //  jvm_1/EmployeeV1
                StringBuilder sb = new StringBuilder();
                      sb.append("Class")
                        .append("\t\t\t")
                        .append("#" + info.getUtf8Index())
                        .append("\t\t\t\t")
                        .append("//\t")
                        .append(info.getClassName());
                System.out.println(sb.toString());
            }

            @Override
            public void visitFieldRef(FieldRefInfo info) {
                //  #2 = Fieldref           #7.#37         //  jvm_1/EmployeeV1.name:Ljava/lang/String;
                StringBuilder sb = new StringBuilder();
                      sb.append("Fieldref")
                        .append("\t\t")
                        .append("#" + info.getClassInfoIndex())
                        .append(".")
                        .append("#" + info.getNameAndTypeIndex())
                        .append("\t\t\t")
                        .append("//\t")
                        .append(info.getClassName())
                        .append(".")
                        .append(info.getFieldName())
                        .append(info.getFieldType());
                System.out.println(sb.toString());
            }

            @Override
            public void visitMethodRef(MethodRefInfo info) {
                //  #1 = Methodref          #11.#36        //  java/lang/Object."<init>":()V
                StringBuilder sb = new StringBuilder();
                      sb.append("Methodref")
                        .append("\t\t")
                        .append("#" + info.getClassInfoIndex())
                        .append(".")
                        .append("#" + info.getNameAndTypeIndex())
                        .append("\t\t\t")
                        .append("//\t")
                        .append(info.getClassName())
                        .append(".")
                        .append(info.getMethodName());
                System.out.println(sb.toString());
            }

            @Override
            public void visitNameAndType(NameAndTypeInfo info) {
                //  #36 = NameAndType        #16:#28        //  "<init>":()V
                StringBuilder sb = new StringBuilder();
                      sb.append("NameAndType")
                        .append("\t\t")
                        .append("#" + info.getIndex1())
                        .append(":")
                        .append("#" + info.getIndex2())
                        .append("\t\t\t")
                        .append("//\t")
                        .append(info.getTypeInfo())
                        .append(":")
                        .append(info.getName());
                System.out.println(sb.toString());
            }

            @Override
            public void visitString(StringInfo info) {
                //  #5 = String             #41            //  Hello , this is class Employee
                StringBuilder sb = new StringBuilder();
                      sb.append("String")
                        .append("\t\t\t")
                        .append("#" + info.getIndex())
                        .append("\t\t\t\t")
                        .append("//\t")
                        .append(((UTF8Info) info.getConstantInfo(info.getIndex())).getValue());
                System.out.println(sb.toString());
            }

            @Override
            public void visistUTF8(UTF8Info info) {
                //  #32 = Utf8               [Ljava/lang/String;
                StringBuilder sb = new StringBuilder();
                      sb.append("Utf8")
                        .append("\t\t\t")
                        .append(info.getValue());
                System.out.println(sb.toString());
            }
        };

        for (int i = 1; i < (Integer) pool.getSize(); i++) {
            ConstantInfo constantInfo = pool.getConstantInfo(i);
            System.out.print("#" + i + "\t=\t");
            constantInfo.accept(visitor);
        }
    }
}
