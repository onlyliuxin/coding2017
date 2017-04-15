package me.lzb.jvm.constant;

/**
 * Created by LZB on 2017/4/15.
 */
public class FieldRefInfo extends ConstantInfo{
    private int type = ConstantInfo.Fieldref_info;

    private int classInfoIndex;
    private int nameAndTypeIndex;

    public FieldRefInfo(ConstantPool pool) {
        super(pool);
    }
    @Override
    public int getType() {
        return type;
    }

    public int getClassInfoIndex() {
        return classInfoIndex;
    }

    public void setClassInfoIndex(int classInfoIndex) {
        this.classInfoIndex = classInfoIndex;
    }

    public int getNameAndTypeIndex() {
        return nameAndTypeIndex;
    }

    public void setNameAndTypeIndex(int nameAndTypeIndex) {
        this.nameAndTypeIndex = nameAndTypeIndex;
    }
}
