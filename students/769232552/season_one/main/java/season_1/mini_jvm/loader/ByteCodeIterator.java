package mini_jvm.loader;

import mini_jvm.util.Util;

public class ByteCodeIterator {

    private byte[] byteCodes;
    private int currentIndex = -1;
    private int byteSize = 0;

    ByteCodeIterator(byte[] byteCodes){
        this.byteCodes = byteCodes;
        this.byteSize = byteCodes.length;
    }

    public boolean hasNext(){
        return currentIndex < byteSize;
    }


    public int nextU1ToInt(){
        return Util.byteToInt(new byte[]{byteCodes[++currentIndex]});
    }

    public int nextU2ToInt(){
        return Util.byteToInt(new byte[]{byteCodes[++currentIndex],byteCodes[++currentIndex]});
    }

    public int nextU4ToInt(){
        return Util.byteToInt(new byte[]{byteCodes[++currentIndex],byteCodes[++currentIndex],
                byteCodes[++currentIndex],byteCodes[++currentIndex]});
    }

    public String nextU2ToHexString(){
        return Util.byteToHexString(new byte[]{byteCodes[++currentIndex],byteCodes[++currentIndex]});
    }

    public String nextU4ToHexString(){
        return Util.byteToHexString(new byte[]{byteCodes[++currentIndex],byteCodes[++currentIndex],
                byteCodes[++currentIndex],byteCodes[++currentIndex]});
    }

    public String nextBytesLenAsString(int BytesLen) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < BytesLen; i++) {
            char c = (char) byteCodes[++currentIndex];
            sb.append(c);
        }
        return sb.toString();
    }

    public String nextUxToHexString(int len) {
        byte[] tmp = new byte[len];
        for (int i = 0; i < len; i++) {
            tmp[i] = byteCodes[++currentIndex];
        }
        return Util.byteToHexString(tmp).toLowerCase();
    }

    public void back(int n) {
        this.currentIndex -= n;
    }
}
