package task6.jvm.field;


import task6.jvm.constant.ConstantPool;
import task6.jvm.constant.UTF8Info;
import task6.jvm.loader.ByteCodeIterator;

public class Field {
    private int accessFlag;
    private int nameIndex;
    private int descriptorIndex;

    private ConstantPool pool;

    public Field(int accessFlag, int nameIndex, int descriptorIndex, ConstantPool pool) {

        this.accessFlag = accessFlag;
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
        this.pool = pool;
    }

    public String toString() {
        String name = ((UTF8Info) pool.getConstantInfo(this.nameIndex)).getValue();

        String desc = ((UTF8Info) pool.getConstantInfo(this.descriptorIndex)).getValue();
        return name + ":" + desc;
    }


    public static Field parse(ConstantPool pool, ByteCodeIterator iter) {

        int accessFlag = iter.next2Bytes();
        int nameIndex = iter.next2Bytes();
        int descIndex = iter.next2Bytes();
        int attribCount = iter.next2Bytes();
        //System.out.println("field attribute count:"+ attribCount);

        Field f = new Field(accessFlag, nameIndex, descIndex, pool);

        if (attribCount > 0) {
            throw new RuntimeException("Field Attribute has not been implemented");
        }

        return f;
    }

}