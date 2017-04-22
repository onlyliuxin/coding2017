package assignment0326.jvm.loader;

public class ByteCodeIterator {
    int curPos;
    byte[] bytes;

    public ByteCodeIterator(byte[] bytes) {
        curPos = 0;
        this.bytes = bytes;
    }

    public boolean hasNext() {
        return !(curPos >= bytes.length);
    }

    public byte nextByte() {
        return bytes[curPos++];
    }

    public int nextByteToInt() {
        return Byte.toUnsignedInt(nextByte());
    }

    public int nextU2ToInt() {
        int hi = Byte.toUnsignedInt(nextByte());
        int lo = Byte.toUnsignedInt(nextByte());
        int i = hi << 8 | lo;
        return i;
    }

    public int nextU4ToInt(){
        return 0;
    }
    public byte[] nextNBytes(int n) {
        byte[] bytes = new byte[n];
        for (int i = 0; i < n; i++) {
            bytes[i] = nextByte();
        }
        return bytes;
    }

    public void skip(int n) {
        curPos += n;
    }

    public void back(int n) {
        curPos -= n;
    }

    public String nextUxToHexString(int len) {
        return null;
    }
}
