package jvm.constant;

/**
 * @author jiaxun
 */
public class StringInfo extends ConstantInfo {

    private int tag = ConstantInfo.STRING_INFO;
    private int index;

    public StringInfo(ConstantPool pool) {
        super(pool);
    }

    public int getTag() {
        return tag;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String toString() {
        return this.getConstantPool().getUTF8String(index);
    }

}
