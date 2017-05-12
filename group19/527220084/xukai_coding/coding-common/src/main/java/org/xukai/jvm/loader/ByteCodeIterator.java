package org.xukai.jvm.loader;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import org.xukai.jvm.constant.ConstantPool;
import org.xukai.jvm.util.Util;

import java.util.Arrays;

public class ByteCodeIterator {

    private byte[] bytes;

    private int offset;

    public ByteCodeIterator(byte[] bytes,int offset) {
        this.bytes = bytes;
        this.offset = offset;
    }


    public int nextToInt(int length){
        Preconditions.checkArgument(length > 0);
        if ((offset + length) < bytes.length) {
            int i = Util.byteToInt(Arrays.copyOfRange(bytes, offset, offset + length));
            offset = offset + length;
            return i;
        }
        return -1;
    }

    public int preToInt(int length){
        Preconditions.checkArgument(length > 0);
        if ((offset - length) > 0) {
            int i = Util.byteToInt(Arrays.copyOfRange(bytes, offset - length, offset));
            offset = offset - length;
            return i;
        }
        return -1;
    }

    public String nextToString(int length){
        Preconditions.checkArgument(length > 0);
        if ((offset + length) < bytes.length) {
            String str = Util.byteToHexString(Arrays.copyOfRange(bytes,offset,offset + length));
            offset = offset + length;
            return str;
        }
        return null;
    }

    public String nextToUTF(int length){
        Preconditions.checkArgument(length > 0);
        if ((offset + length) < bytes.length) {
            String str = new String(Arrays.copyOfRange(bytes,offset,offset + length), Charsets.UTF_8);
            offset = offset + length;
            return str;
        }
        return null;
    }

}
