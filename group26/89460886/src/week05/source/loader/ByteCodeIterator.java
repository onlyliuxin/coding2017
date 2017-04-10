package jvm.loader;

/**
 * @author jiaxun
 */
public class ByteCodeIterator {

    private byte[] codes;
    private int currentPosition = 0;

    public ByteCodeIterator(byte[] codes) {
        this.codes = codes;
    }

    public byte[] next() {
        return new byte[]{codes[currentPosition++]};
    }

    public byte[] nextTwo() {
        return new byte[]{codes[currentPosition++], codes[currentPosition++]};
    }

    public byte[] nextFour() {
        return new byte[]{codes[currentPosition++], codes[currentPosition++],
                codes[currentPosition++], codes[currentPosition++]};
    }

    public byte[] nextLength(int length) {
        byte[] result = new byte[length];
        for (int i = 0; i < length; i++) {
            result[i] = codes[currentPosition++];
        }
        return result;
    }

}
