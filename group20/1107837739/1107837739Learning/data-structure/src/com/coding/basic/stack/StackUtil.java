package com.coding.basic.stack;

import java.util.Objects;

public class StackUtil {

    /**
     * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
     * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
     */
    public static void reverse(Stack s) {
        if (s == null || s.isEmpty()) {
            return;
        }

        Stack tmp = new Stack();

        for (int i = 0; i < s.size(); i++) {
            tmp.push(get(s, i));
        }
        while (!s.isEmpty()) {
            s.pop();
        }
        while (!tmp.isEmpty()) {
            s.push(tmp.pop());
        }
    }

    private static Object get(Stack s, int indexFromBottom) {
        Stack tmp = new Stack();
        int size = s.size();
        for (int i = 0; i < size - indexFromBottom - 1; i++) {
            tmp.push(s.pop());
        }

        Object rtn = s.peek();
        while (!tmp.isEmpty()) {
            s.push(tmp.pop());
        }
        return rtn;
    }

    /**
     * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
     *
     * @param o 被删除的对象
     */
    public static void remove(Stack s, Object o) {
        if (s == null || s.isEmpty()) {
            return;
        }

        Stack tmp = new Stack();

        while (!s.isEmpty()) {
            Object data = s.pop();
            if (Objects.equals(data, o)) {
                break;
            }

            tmp.push(data);
        }

        while (!tmp.isEmpty()) {
            s.push(tmp.pop());
        }
    }

    /**
     * 从栈顶取得len个元素, 原来的栈中元素保持不变
     * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
     *
     * @param len 长度
     */
    public static Object[] getTop(Stack s, int len) {
        if (len < 0 || len >= s.size()) {
            throw new IndexOutOfBoundsException();
        }

        Object[] rtn = new Object[len];
        Stack tmp = new Stack();
        for (int i = 0; i < len; i++) {
            Object data = s.pop();
            rtn[i] = data;
            tmp.push(data);
        }

        for (int i = 0; i < len; i++) {
            s.push(tmp.pop());
        }

        return rtn;
    }

    /**
     * 字符串s 可能包含这些字符：  ( ) [ ] { }, a,b,c... x,yz
     * 使用堆栈检查字符串s中的括号是不是成对出现的。
     * 例如s = "([e{d}f])" , 则该字符串中的括号是成对出现， 该方法返回true
     * 如果 s = "([b{x]y})", 则该字符串中的括号不是成对出现的， 该方法返回false;
     *
     * @param s 输入字符串
     */
    public static boolean isValidPairs(String s) {
        String[] chars = s.split("");

        Stack stack = new Stack();
        for (int i = 0; i < chars.length; i++) {
            if ("(".equals(chars[i])) {
                stack.push(")");
            } else if ("[".equals(chars[i])) {
                stack.push("]");
            } else if ("{".equals(chars[i])) {
                stack.push("}");
            } else if (")".equals(chars[i])) {
                if (!stack.pop().equals(")")) {
                    return false;
                }
            } else if ("]".equals(chars[i])) {
                if (!stack.pop().equals("]")) {
                    return false;
                }
            } else if ("}".equals(chars[i])) {
                if (!stack.pop().equals("}")) {
                    return false;
                }
            }
        }
        return true;
    }
}
