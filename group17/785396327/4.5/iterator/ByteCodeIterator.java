package iterator;

import util.Util;

import java.util.Arrays;

/**
 * Created by IBM on 2017/4/11.
 */
public class ByteCodeIterator {

    byte[] codes;

    public ByteCodeIterator(byte[] codes) {
        this.codes = codes;
    }

    public String nextU4ToHexString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            byte code = codes[i];
            int value = code & 0xff;
            sb.append(Integer.toHexString(value));
        }
        codes = Arrays.copyOfRange(codes, 4, codes.length);
        return sb.toString();
    }

    public int nextU2ToInt() {
        byte[] buff = new byte[]{codes[0], codes[1]};
        codes = Arrays.copyOfRange(codes, 2, codes.length);
        return Util.byteToInt(buff);
    }

    public int nextU1ToInt() {
        byte[] buff = new byte[]{codes[0]};
        codes = Arrays.copyOfRange(codes, 1, codes.length);
        return Util.byteToInt(buff);
    }

    public byte[] nextLengthBytes(int length) {
        byte[] result = new byte[length];
        for (int i = 0; i < length; i++) {
            result[i] = codes[i];
        }
        codes = Arrays.copyOfRange(codes, length, codes.length);
        return result;
    }

    public String nextLengthString(int length) {
        byte[] result = new byte[length];
        for (int i = 0; i < length; i++) {
            result[i] = codes[i];
        }
        codes = Arrays.copyOfRange(codes, length, codes.length);
        return Util.byteToHexString(result);
    }

    public int nextU4ToInt() {
        byte[] buff = new byte[]{codes[0], codes[1], codes[2], codes[3]};
        codes = Arrays.copyOfRange(codes, 4, codes.length);
        return Util.byteToInt(buff);
    }
}
