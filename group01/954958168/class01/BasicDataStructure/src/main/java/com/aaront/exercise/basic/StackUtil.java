package com.aaront.exercise.basic;

import java.util.Arrays;
import java.util.Stack;

public class StackUtil {


    /**
     * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
     * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
     */
    public static void reverse(Stack<Integer> s) {
        if (s == null) return;
        Integer[] temp = new Integer[s.size()];
        int i = 0;
        while (!s.isEmpty()) {
            temp[i++] = s.pop();
        }
        for(i = 0;i < temp.length;i++) {
            s.push(temp[i]);
        }
    }

    /**
     * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
     *
     * @param o
     */
    public static void remove(Stack s, Object o) {
        if (s == null) return;
        Stack temp = new Stack();
        while (!s.isEmpty()) {
            Object element = s.pop();
            if (element.equals(o)) break;
            temp.push(element);
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
        if (s == null) return null;
        int index = 0;
        Object[] elements = new Object[len];
        while (!s.isEmpty() && index < len) {
            elements[index] = s.pop();
            index++;
        }
        for (int i = index - 1; i >= 0; i--) {
            s.push(elements[i]);
        }
        return Arrays.copyOf(elements, index);
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
        if (s.isEmpty()) return true;
        Stack<Character> stack = new Stack<>();
        for (int i = 0, len = s.length(); i < len; i++) {
            char c = s.charAt(i);
            switch (c) {
                case '(':
                case '[':
                case '{':
                    stack.push(c);
                    break;
                case ')':
                    if(stack.peek().equals('(')) stack.pop();
                    else return false;
                    break;
                case ']':
                    if(stack.peek().equals('[')) stack.pop();
                    else return false;
                    break;
                case '}':
                    if (stack.peek().equals('{')) stack.pop();
                    else return false;
                    break;
            }
        }
        return stack.isEmpty();
    }
}

	
