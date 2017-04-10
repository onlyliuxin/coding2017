package com.github.miniyk2012.coding2017.coderising.jvm.loader;

import com.github.miniyk2012.coding2017.coderising.jvm.util.Util;
import org.apache.commons.lang3.ArrayUtils;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class ByteCodeIterator {
    private byte[] codes;
    private int point = 0;

    public ByteCodeIterator(byte[] codes) {
        this.codes = codes;
    }

    public int nextU1toInt() {
        byte[] u1 = new byte[] {codes[point++]};
        return Util.byteToInt(u1);
    }

    public int nextU2toInt() {
        byte[] u2 = new byte[] {codes[point++], codes[point++]};
        return Util.byteToInt(u2);
    }

    /**
     * 读取n个字节，并编码成UTF-8输出，point自动增加
     * @param n
     * @return
     */
    public String readUtf8(int n)
    {
        byte[] info = Arrays.copyOfRange(codes, point, point+n);
        String utf8;
        try {
            utf8 = new String(info, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("常量池UTF-8编码错误");
        }
        point += n;
        return utf8;
    }

    /**
     * 返回当前位置
     * @return
     */
    public int position() {
        return point;
    }

    /**
     * n可正可负
     * @param n
     */
    public void skip(int n) {
        point = point + n;
    }

    public void seek(int n) {
        if (n >= codes.length || n < 0) throw new IndexOutOfBoundsException();
        point = n;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        byte[] codes = {0x00, 0x34, 0x00};
        ByteCodeIterator byteCodeIterator = new ByteCodeIterator(codes);
        System.out.println(byteCodeIterator.nextU2toInt());
        System.out.println(byteCodeIterator.nextU1toInt());

        byte[] codes2 = "Employee".getBytes("UTF-8");
        byte[] codes3 = ArrayUtils.addAll(codes2, codes);
        byteCodeIterator = new ByteCodeIterator(codes3);
        System.out.println(byteCodeIterator.readUtf8(codes2.length));
        System.out.println(byteCodeIterator.nextU2toInt());
        System.out.println(byteCodeIterator.nextU1toInt());
    }
}
