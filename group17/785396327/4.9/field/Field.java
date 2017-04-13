package field;

import constant.ConstantPool;
import iterator.ByteCodeIterator;

/**
 * Created by IBM on 2017/4/12.
 */
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


    public static Field parse(ConstantPool pool, ByteCodeIterator iter) {
        int accessFlags = iter.nextU2ToInt();
        int nameIndex = iter.nextU2ToInt();
        int descriptor_index = iter.nextU2ToInt();
        int attributesCount = iter.nextU2ToInt();
        if (attributesCount > 0) {
            throw new RuntimeException("Field字段表，暂时不支持存在属性");
        }
        return new Field(accessFlags, nameIndex, descriptor_index, pool);
    }

    @Override
    public String toString() {
        return pool.getUTF8String(nameIndex) + ":" + pool.getUTF8String(descriptorIndex);
    }
}
