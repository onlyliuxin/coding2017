package task8.jvm.constant;

import task8.jvm.print.ConstantInfoVisitor;

public class StringInfo extends ConstantInfo {
    private int type = ConstantInfo.STRING_INFO;
    private int index;

    public StringInfo(ConstantPool pool) {
        super(pool);
    }

    public int getType() {
        return type;
    }

    @Override
    public void accept(ConstantInfoVisitor visitor) {
        visitor.visitStringInfo(this);
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
