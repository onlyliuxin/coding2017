package assignment0326.jvm.print;


import assignment0326.jvm.constant.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ConstantPoolPrinter {
    private int indexLength;
    ConstantPool pool;
    private List<String> comments = new ArrayList<>();

    ConstantPoolPrinter(ConstantPool pool) {
        this.pool = pool;
        indexLength = getMaxIndexLength() + 3;
        generateComments();
    }

    public void print() {

        System.out.println("Constant Pool:");

        for (int i = 1; i < pool.getSize(); i++) {
            ConstantInfo constantInfo = pool.getConstantInfo(i);
            System.out.printf("%" + indexLength + "s = %-20s%-15s",
                    "#" + i, constantInfo.typeDescription(), constantInfo.contentDescription());
            // System.out.printf("%-20s", pool.getConstantInfo(i).typeDescription());
//            System.out.printf("%-15s", pool.getConstantInfo(i).contentDescription());
            if (!(constantInfo instanceof UTF8Info)) {
                System.out.print("// " + getComment(i));
            }
            System.out.println();
        }

    }

    private int getMaxIndexLength() {
        return String.valueOf(pool.getSize()).length();
    }

    private void generateComments() {
        comments.add("nullConstant");
        for (int i = 1; i < pool.getSize(); i++) {
            comments.add(null);
        }

        for (int i = 1; i < pool.getSize(); i++) {
            if (pool.getConstantInfo(i).getType() == ConstantInfo.UTF8_INFO) {
                comments.set(i, pool.getConstantInfo(i).contentDescription());
            }
        }
        while (comments.contains(null)) {
            for (int i = 1; i < pool.getSize(); i++) {
                if (!Objects.equals(getComment(i), null))
                    continue;

                ConstantInfo constantInfo = pool.getConstantInfo(i);
                if (constantInfo.getType() == ConstantInfo.CLASS_INFO) {

                    ClassInfo classInfo = (ClassInfo) constantInfo;
                    comments.set(i, getComment(classInfo.getUtf8Index()));

                } else if (constantInfo.getType() == ConstantInfo.STRING_INFO) {

                    StringInfo stringInfo = (StringInfo) constantInfo;
                    comments.set(i, getComment(stringInfo.getIndex()));

                } else if (constantInfo.getType() == ConstantInfo.FIELD_INFO) {

                    FieldRefInfo fieldRefInfo = (FieldRefInfo) constantInfo;
                    comments.set(i, getComment(fieldRefInfo.getClassInfoIndex(), fieldRefInfo.getNameAndTypeIndex(), '.'));

                } else if (constantInfo.getType() == ConstantInfo.METHOD_INFO) {

                    MethodRefInfo methodRefInfo = (MethodRefInfo) constantInfo;
                    comments.set(i, getComment(methodRefInfo.getClassInfoIndex(), methodRefInfo.getNameAndTypeIndex(), '.'));

                } else if (constantInfo.getType() == ConstantInfo.NAME_AND_TYPE_INFO) {

                    NameAndTypeInfo nameAndTypeInfo = (NameAndTypeInfo) constantInfo;
                    comments.set(i, getComment(nameAndTypeInfo.getIndex1(), nameAndTypeInfo.getIndex2(), ':'));

                }
            }
        }
    }

    private String getComment(int index) {
        return comments.get(index);
    }

    private String getComment(int index1, int index2, char separator) {
        String s1 = getComment(index1);
        String s2 = getComment(index2);
        if (Objects.equals(s1, null) || Objects.equals(s2, null)) {
            return null;
        }
        if (s1.equals("<init>"))
            s1 = "\"<init>\"";
        return s1 + separator + s2;
    }

}
