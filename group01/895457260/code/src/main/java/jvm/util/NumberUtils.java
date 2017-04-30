package jvm.util;

import jvm.util.code.Code;
import jvm.util.code.CompCode;

/**
 * Created by Haochen on 2017/4/30.
 * TODO:
 */
public class NumberUtils {
    public static int toSignedInt(int unsigned, int bitCount) {
        String bin = Integer.toBinaryString(unsigned);
        int leader0Count = bitCount - bin.length();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < leader0Count; ++i) {
            builder.append('0');
        }
        builder.append(bin);
        Code trueCode = new CompCode(Code.PURE_INTEGER, builder.toString()).trueCode();
        return Integer.parseInt(trueCode.getCode(), 2);
    }
}
