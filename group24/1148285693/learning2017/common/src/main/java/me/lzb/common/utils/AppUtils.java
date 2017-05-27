package me.lzb.common.utils;

/**
 * @author LZB
 */
public class AppUtils {

    /**
     * 获取一个数的位数
     *
     * @param i
     * @return
     */
    public static int getDigit(int i) {
        int a = i;
        int c = 0;
        while (a > 0) {
            a = a / 10;
            c++;
        }
        return c;
    }
}
