package com.johnChnia.coding2017.basic.stack;

/**
 * Created by john on 2017/4/7.
 */
public class StackUtil {


    /**
     * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
     * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
     */
    public static <E> void reverse(Stack<E> s) {
        if (s.empty()) {
            return;
        }
        E item = s.pop();
        reverse(s);
        insertAtBottom(item, s);
    }

    /**
     * @param item 插入底部的元素
     * @param s    栈对象引用
     */
    private static <E> void insertAtBottom(E item, Stack<E> s) {
        if (s.empty()) {
            s.push(item);
        } else {
            E temp = s.pop();
            insertAtBottom(item, s);
            s.push(temp);
        }
    }


    /**
     * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
     *
     * @param o
     */
    public static <E> void remove(Stack<E> s, Object o) {
        if (s.empty()) {
            return;
        }
        E item = s.pop();
        if (!o.equals(item)) {  //没有考虑o为null的情况
            remove(s, o);
            s.push(item);
        }
    }

    /**
     * 从栈顶取得len个元素, 原来的栈中元素保持不变
     * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
     *
     * @param len
     * @return
     */
    public static <E> Object[] getTop(Stack<E> s, int len) {
        if (len > s.size()) {
            throw new IllegalArgumentException("Len: " + len + ", Size" + s.size());
        }
        Object[] array = new Object[len];
        int index = 0;
        getArray(s, array, index);
        return array;
    }

    /**
     * 采用递归的方式把len个元素加到数组中，且保持原栈中元素不变。
     *
     * @param s     栈
     * @param array Object数组
     * @param index 数组索引
     */
    private static <E> void getArray(Stack<E> s, Object[] array, int index) {
        if (s.empty() || index == array.length) {
            return;
        }
        E item = s.pop();
        array[index++] = item;
        getArray(s, array, index);
        s.push(item);
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
    public static boolean isValidPairs(String s) {  // last unclosed first closed
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            String subStr = s.substring(i, i + 1);
            if ("([{".contains(subStr)) {
                stack.push(subStr);
            } else if (")]}".contains(subStr)) {
                if (stack.empty()) {
                    return false;
                }
                String left = stack.pop();
                if (subStr.equals(")")) {
                    if(!left.equals("("))
                        return false;
                }else if(subStr.equals("]")){
                    if(!left.equals("["))
                        return false;
                }else if(subStr.equals("}")){
                    if(!left.equals("{"))
                        return false;
                }
            }
        }
        return stack.empty();
    }

}