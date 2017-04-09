package minijvm.loader;

import java.io.UnsupportedEncodingException;

import minijvm.util.Util;

public class ByteCodeIterator {

    private byte[] codes;
    
    private int pos = 0;
    
    public ByteCodeIterator (byte[] codes) {
        this.codes = codes;
    }
    
    public int nextU1ToInt() {
        return Util.byteToInt(new byte[]{codes[pos++]});
    }
    
    public int nextU2ToInt() {
        return Util.byteToInt(new byte[]{codes[pos++], codes[pos++]});
    }

    public String nextU4ToHexString() {
        return Util.byteToHexString(new byte[]{codes[pos++], codes[pos++], codes[pos++], codes[pos++]});
    }

    public String nextLengthToString(int length) {
        String dest = null;
        try {
            dest = new String(getBytes(length), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return dest;
    }
    
    private byte[] getBytes(int length) {
        byte[] b = new byte[length];
        for (int i = 0; i < length; i++) {
            b[i] = codes[pos++];
        }
        return b;
    }
    
}
