package main.coding_170409.jvm.constant;

/**
 * Created by peter on 2017/4/21.
 */
public class StringInfo extends ConstantInfo {
    private int type = ConstantInfo.STRING_INFO;
    private int index;

    public StringInfo(ConstantPool pool) {
        super(pool);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return this.getConstantPool().getUTF8String(index);
    }

    @Override
    public int getType() {
        return type;
    }
}
