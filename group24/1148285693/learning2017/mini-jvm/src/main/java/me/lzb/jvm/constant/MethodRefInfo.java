package me.lzb.jvm.constant;

/**
 * Created by LZB on 2017/4/15.
 */
public class MethodRefInfo extends ConstantInfo {
    private int type = ConstantInfo.Methodref_info;

    private int classInfoIndex;
    private int nameAndTypeIndex;

    public MethodRefInfo(ConstantPool pool) {
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
