package jvm.constant;

/**
 * @author jiaxun
 */
public class UTF8Info extends ConstantInfo {

    private int tag = ConstantInfo.UTF8_INFO;
    private int length;
    private String bytes;

    public UTF8Info(ConstantPool pool) {
        super(pool);
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getTag() {
        return tag;
    }

    public String getBytes() {
        return bytes;
    }

    public void setBytes(String bytes) {
        this.bytes = bytes;
    }

    @Override
    public String toString() {
        return "UTF8Info [type=" + tag + ", length=" + length + ", bytes=" + bytes + ")]";
    }

}
