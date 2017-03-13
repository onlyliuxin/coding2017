package com.circle.algorithm;

/**
 * Created by keweiyang on 2017/3/1.
 * Problem:
   Given an input string, reverse the string word by word.
   For example,
   Given s = “the sky is blue”,
   return “blue is sky the”.
 */
public class Reverse {

    public void reverse(String string) {

        char[] cs = string.toCharArray();
        char[] newChar = new char[cs.length];
        for(int i=0;i<cs.length;i++) {
            if (cs[i] == '0') {

            }
        }
    }

    public static void main(String[] args) {
        Reverse reverse = new Reverse();
        String string = "the sky is blue";

    }

}
