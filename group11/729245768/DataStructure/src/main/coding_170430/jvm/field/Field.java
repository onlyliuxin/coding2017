package main.coding_170430.jvm.field;

import main.coding_170430.jvm.constant.ConstantPool;
import main.coding_170430.jvm.constant.UTF8Info;
import main.coding_170430.jvm.loader.ByteCodeIterator;

/**
 * Created by peter on 2017/4/21.
 */
public class Field {
    private int accessFlag;
    private int nameIndex;
    private int descriptionIndex;

    private ConstantPool pool;

    public Field(int accessFlag,int nameIndex,int descriptionIndex,ConstantPool pool){
        this.accessFlag = accessFlag;
        this.nameIndex = nameIndex;
        this.descriptionIndex = descriptionIndex;
        this.pool = pool;
    }

    @Override
    public String toString() {
        String name = ((UTF8Info)pool.getConstantInfo(this.nameIndex)).getValue();

        String desc = ((UTF8Info)pool.getConstantInfo(this.descriptionIndex)).getValue();
        return name+":"+desc;
    }

    public static Field parse(ConstantPool pool, ByteCodeIterator iterator){
        int accessFlag = iterator.nextU2ToInt();
        int nameIndex = iterator.nextU2ToInt();
        int descIndex = iterator.nextU2ToInt();
        int attributeCount = iterator.nextU2ToInt();
        Field f = new Field(accessFlag,nameIndex,descIndex,pool);

        if(attributeCount>0){
            throw  new RuntimeException("Field attribute has not implemented");
        }
        return f;
    }
}
