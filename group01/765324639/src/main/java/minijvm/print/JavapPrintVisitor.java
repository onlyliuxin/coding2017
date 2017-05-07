package minijvm.print;

import minijvm.constant.ClassInfo;
import minijvm.constant.ConstantInfo.Visitor;
import minijvm.constant.FieldRefInfo;
import minijvm.constant.MethodRefInfo;
import minijvm.constant.NameAndTypeInfo;
import minijvm.constant.StringInfo;
import minijvm.constant.UTF8Info;

public class JavapPrintVisitor implements Visitor{

    private int index = 1;
    
    @Override
    public void visitClassInfo(ClassInfo info) {
        StringBuilder builder = new StringBuilder();
        builder.append(getThisIndexOfPrefix(index++))
                .append(getMid("Class"))
                .append("#" + info.getUtf8Index());
        System.out.println(builder.toString());
    }

    @Override
    public void visitFieldRef(FieldRefInfo info) {
        StringBuilder builder = new StringBuilder();
        builder.append(getThisIndexOfPrefix(index++))
                .append(getMid("Fieldref"))
                .append("#" + info.getClassInfoIndex() + ".#" + info.getNameAndTypeIndex());
        System.out.println(builder.toString());
    }

    @Override
    public void visitMethodRef(MethodRefInfo info) {
        StringBuilder builder = new StringBuilder();
        builder.append(getThisIndexOfPrefix(index++))
                .append(getMid("Methodref"))
                .append("#" + info.getClassInfoIndex() + ".#" + info.getNameAndTypeIndex());
        System.out.println(builder.toString());
    }

    @Override
    public void visitNameAndType(NameAndTypeInfo info) {
        StringBuilder builder = new StringBuilder();
        builder.append(getThisIndexOfPrefix(index++))
                .append(getMid("NameAndType"))
                .append("#" + info.getIndex1() + ":#" + info.getIndex2());
        System.out.println(builder.toString());
    }

    @Override
    public void visitString(StringInfo info) {
        StringBuilder builder = new StringBuilder();
        builder.append(getThisIndexOfPrefix(index++))
                .append(getMid("String"))
                .append("#" + info.getIndex());
        System.out.println(builder.toString());
    }

    @Override
    public void visistUTF8(UTF8Info info) {
        StringBuilder builder = new StringBuilder();
        builder.append(getThisIndexOfPrefix(index++))
                .append(getMid("Utf8"))
                .append(info.getValue());
        System.out.println(builder.toString());
    }

    private String getThisIndexOfPrefix(int index) {
        if (index < 1) {
            throw new IllegalArgumentException("错误的索引");
        }
        
        if (index < 10) {
            return "   #" + index + " = ";
        } else if (index < 100) {
            return "  #" + index + " = ";
        } else if (index < 1000) {
            return " #" + index + " = ";
        } else {
            throw new IllegalArgumentException("还没有实现" + index + "个常量的打印");
        }
    }
    
    private String getMid(String type) {
        if (type.length() >= 19) {
            return type;
        }
        String str = "                   ";
        int length = type.length();
        return type + str.substring(length);
    }
}
