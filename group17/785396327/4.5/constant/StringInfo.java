package constant;

/**
 * Created by IBM on 2017/4/10.
 */
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
    public void accept(Visitor visitor) {
        visitor.visitString(this);
    }

    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }


    public String toString(){
        return this.getConstantPool().getUTF8String(index);
    }

}
