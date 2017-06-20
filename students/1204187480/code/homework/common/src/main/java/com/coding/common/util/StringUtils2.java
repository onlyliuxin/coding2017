package com.coding.common.util;

/**
 * Created by luoziyihao on 3/5/17.
 */
public class StringUtils2 {

    /**
     * 改变指定位置的 char的大小写
     */
    public String toUpperCase(String str, int index) {
        char[] chars = str.toCharArray();
        if (index + 1 > chars.length) {
            throw new RuntimeException("the char at the index don't exist");
        }
        chars[index] = Character.toUpperCase(chars[index]);
        return new String(chars);
    }

    /**
     * 改变指定位置的 char的大小写
     */
    public String toLowwerCase(String str, int index) {
        char[] chars = str.toCharArray();
        if (index + 1 > chars.length ) {throw new RuntimeException("the char at the index don't exist");}
        chars[index] = Character.toLowerCase(chars[index]);
        return new String(chars);
    }

    public boolean isSpaceOrNull(String paraName) {
        return (paraName == null || paraName.trim().isEmpty());
    }

}
