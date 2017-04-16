package io.github.vxzh.jvm.field;


import io.github.vxzh.jvm.clz.ClassFile;
import io.github.vxzh.jvm.constant.UTF8Info;
import io.github.vxzh.jvm.loader.ByteCodeIterator;

public class Field {
    private int accessFlag;
    private int nameIndex;
    private int descriptorIndex;
    private ClassFile clzFile;

    public Field(int accessFlag, int nameIndex, int descriptorIndex, ClassFile clzFile) {

        this.accessFlag = accessFlag;
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
        this.clzFile = clzFile;
    }

    public String toString() {
        String name = ((UTF8Info) clzFile.getConstantPool().getConstantInfo(this.nameIndex)).getValue();
        String desc = ((UTF8Info) clzFile.getConstantPool().getConstantInfo(this.descriptorIndex)).getValue();
        return name + ":" + desc;
    }


    public static Field parse(ClassFile clzFile, ByteCodeIterator iter) {

        int accessFlag = iter.nextU2ToInt();
        int nameIndex = iter.nextU2ToInt();
        int descIndex = iter.nextU2ToInt();
        int attribCount = iter.nextU2ToInt();
        //System.out.println("field attribute count:"+ attribCount);

        Field f = new Field(accessFlag, nameIndex, descIndex, clzFile);

        if (attribCount > 0) {
            throw new RuntimeException("Field Attribute has not been implemented");
        }

        return f;
    }

}


