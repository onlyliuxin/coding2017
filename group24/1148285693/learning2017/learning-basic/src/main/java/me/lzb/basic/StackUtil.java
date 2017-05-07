package me.lzb.basic;

import java.util.Stack;

public class StackUtil {

    public static void bad_reverse(Stack<Integer> s) {


    }

    /**
     * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
     * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
     */
    public static void reverse(Stack<Integer> s) {
        Stack<Integer> t = new Stack<>();
        for (Integer i : s) {
            t.push(i);
        }

        s.clear();

        int size = t.size();
        for (int i = 0; i < size; i++) {
            s.push(t.pop());
        }

    }

    public static void addToBottom(Stack<Integer> s, Integer value) {
        Stack<Integer> t = new Stack<>();
        int size = s.size();
        for (int i = 0; i < size; i++) {
            t.push(s.pop());
        }

        s.clear();

        s.push(value);

        for (int i = 0; i < size; i++) {
            s.push(t.pop());
        }

    }

    /**
     * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
     *
     * @param o
     */
    public static void remove(Stack s, Object o) {
        Stack t = new Stack();
        int size = s.size();
        for (int i = 0; i < size; i++) {
            Object ro = s.pop();
            if (!ro.equals(o)) {
                t.push(ro);
            }
        }

        s.clear();

        int sizet = t.size();
        for (int i = 0; i < sizet; i++) {
            s.push(t.pop());
        }

    }

    /**
     * 从栈顶取得len个元素, 原来的栈中元素保持不变
     * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
     *
     * @param len
     * @return
     */
    public static Object[] getTop(Stack s, int len) {
        Object[] array = new Object[len];
        for (int i = 0; i < len; i++) {
            array[i] = s.pop();
        }

        for (int i = len - 1; i >= 0; i--) {
            s.push(array[i]);
        }
        return array;
    }

    /**
     * 字符串s 可能包含这些字符：  ( ) [ ] { }, a,b,c... x,yz
     * 使用堆栈检查字符串s中的括号是不是成对出现的。
     * 例如s = "([e{d}f])" , 则该字符串中的括号是成对出现， 该方法返回true
     * 如果 s = "([b{x]y})", 则该字符串中的括号不是成对出现的， 该方法返回false;
     *
     * @param s
     * @return
     */
    public static boolean isValidPairs(String s) {
        char[] array = s.toCharArray();
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < array.length; i++) {
            stack.push(String.valueOf(array[i]));
        }


        int a = -1;
        int b = -1;
        int c = -1;


        for (int i = 0; i < array.length; i++) {
            String cc = stack.pop();

            if ("{}".indexOf(cc) >= 0) {
                if (a == -1) {
                    a = i;
                } else {
                    if (stack.size() != a) {
                        return false;
                    }
                }
            }

            if ("[]".indexOf(cc) >= 0) {

                if (b == -1) {
                    b = i;
                } else {
                    if (stack.size() != b) {
                        return false;
                    }
                }

            }

            if ("()".indexOf(cc) >= 0) {

                if (c == -1) {
                    c = i;
                } else {
                    if (stack.size() != c) {
                        return false;
                    }
                }

            }

        }
        return true;
    }


}
