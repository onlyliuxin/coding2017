package jvm.constant;

/**
 * @author jiaxun
 */
public class ClassInfo extends ConstantInfo {

    private int tag = ConstantInfo.CLASS_INFO;
    private int nameIndex;

    public ClassInfo(ConstantPool pool) {
        super(pool);
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public void setNameIndex(int nameIndex) {
        this.nameIndex = nameIndex;
    }

    public int getTag() {
        return tag;
    }

    public String getClassName() {
        int index = getNameIndex();
        UTF8Info utf8Info = (UTF8Info) constantPool.getConstantInfo(index);
        return utf8Info.getBytes();
    }

}
