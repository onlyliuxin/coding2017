package com.coding.basic.datastructure.stack;

import java.util.Stack;

public class StackUtil {

    /**
     * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
     * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty
     */
    public static void reverse(Stack<Integer> s) {
        if (s == null || s.isEmpty()) {
            return;
        }
        Integer top = s.pop();
        reverse(s);
        addToBottom(s, top);
    }

    public static void addToBottom(Stack<Integer> s, Integer value) {
        if (s.isEmpty()) {
            s.push(value);
        } else {
            Integer top = s.pop();
            addToBottom(s, value);
            s.push(top);
        }
    }

    /**
     * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty
     */
    public static void remove(Stack s, Object object) {
        if (s == null || s.isEmpty()) {
            return;
        }
        Stack temp = new Stack();
        while (!s.isEmpty()) {
            Object top = s.pop();
            if (null == object && top != null) {
                temp.push(top);
            }
            if (null != object && !object.equals(top)) {
                temp.push(top);
            }
        }
        while (!temp.isEmpty()) {
            s.push(temp.pop());
        }
    }

    /**
     * 从栈顶取得len个元素, 原来的栈中元素保持不变
     * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty
     */
    public static Object[] getTop(Stack s, int len) {
        if (len < 0) {
            throw new RuntimeException("length should be larger than zero!");
        }
        if (len > s.size()) {
            throw new RuntimeException("length is bigger than stack size!");
        }
        if (s == null || s.isEmpty()) {
            return null;
        }
        Stack temp = new Stack();
        int count = 0;
        while (!s.isEmpty() && count++ <= len) {
            temp.push(s.pop());
        }
        Object[] result = new Object[temp.size()];
        int index = result.length - 1;
        while (!temp.isEmpty()) {
            Object top = temp.pop();
            result[index--] = top;
            s.push(top);
        }
        return result;
    }

    /**
     * 字符串s 可能包含这些字符：  ( ) [ ] { }, a,b,c... x,yz
     * 使用堆栈检查字符串s中的括号是不是成对出现的。
     * 例如s = "([e{d}f])" , 则该字符串中的括号是成对出现， 该方法返回true
     * 如果 s = "([b{x]y})", 则该字符串中的括号不是成对出现的， 该方法返回false;
     */
    public static boolean isValidPairs(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            switch (ch) {
                case '(':
                    stack.push(ch);
                    break;
                case ')':
                    if (!stack.isEmpty() && stack.peek() == '(') {
                        stack.pop();
                    } else {
                        return false;
                    }
                    break;
                case '[':
                    stack.push(ch);
                    break;
                case ']':
                    if (!stack.isEmpty() && stack.peek() == '[') {
                        stack.pop();
                    } else {
                        return false;
                    }
                    break;
                case '{':
                    stack.push(ch);
                    break;
                case '}':
                    if (!stack.isEmpty() && stack.peek() == '{') {
                        stack.pop();
                    } else {
                        return false;
                    }
                    break;
            }
        }
        return stack.isEmpty();
    }

}
