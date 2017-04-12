package jvm.constant;

/**
 * @author jiaxun
 */
public class FloatInfo extends ConstantInfo {

    private int tag = ConstantInfo.FLOAT_INFO;

    private float bytes;

    @Override
    public int getTag() {
        return tag;
    }

    public float getBytes() {
        return bytes;
    }

    public void setBytes(float bytes) {
        this.bytes = bytes;
    }
}
