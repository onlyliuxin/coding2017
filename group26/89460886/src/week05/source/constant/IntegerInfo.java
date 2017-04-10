package jvm.constant;

/**
 * @author jiaxun
 */
public class IntegerInfo extends ConstantInfo {

    private int tag = ConstantInfo.INTEGER_INFO;

    private int bytes;

    @Override
    public int getTag() {
        return tag;
    }

    public int getBytes() {
        return bytes;
    }

    public void setBytes(int bytes) {
        this.bytes = bytes;
    }
}
