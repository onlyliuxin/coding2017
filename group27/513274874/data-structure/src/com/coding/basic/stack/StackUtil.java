package com.coding.basic.stack;

public class StackUtil {

    /**
     * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
     * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
     */
    public static void reverse(Stack s) {
        Stack temp = new Stack();
        //先清空s
        while (!s.isEmpty()) {
            temp.push(s.pop());
        }

    }

    /**
     * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
     *
     * @param o
     */
    public static void remove(Stack s, Object o) {
        Stack temp = new Stack();
        while (!s.isEmpty()) {
            Object a = s.pop();
            if (a.equals(o)) {
                break;
            }
            temp.push(a);
        }

        while (!temp.isEmpty()) {
            s.push(temp.pop());
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

        Object[] os = null;
        if (len > 0) {
            os = new Object[len];
        } else {
            return null;
        }

        Stack temp = new Stack();
        for (int i = 0; i < len; i++) {
            if (!s.isEmpty()) {
                Object o = s.pop();
                os[i] = o;
                temp.push(o);
            } else {
                break;
            }
        }

        while (!temp.isEmpty()) {
            s.push(temp.pop());
        }
        return os;
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
        if (s == null || s.trim().equals("")) return false;
        Stack stack = new Stack();
        byte[] bytes = s.getBytes();
        byte temp;
        for (byte b : bytes) {
            switch (b) {
                case '(':
                case '[':
                case '{':
                    stack.push(b);
                    break;

                case ')':
                    temp = (byte) stack.peek();
                    if (temp != '(') {
                        return false;
                    } else {
                        stack.pop();
                    }
                    break;
                case ']':
                    temp = (byte) stack.peek();
                    if (temp != ']') {
                        return false;
                    } else {
                        stack.pop();
                    }
                    break;
                case '}':
                    temp = (byte) stack.peek();
                    if (temp != ']') {
                        return false;
                    } else {
                        stack.pop();
                    }
                    break;

            }

            if(stack.isEmpty() ) return true;
        }

        return false;
    }


}
