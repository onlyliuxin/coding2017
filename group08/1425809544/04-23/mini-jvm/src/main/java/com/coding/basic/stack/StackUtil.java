//package com.coding.basic.stack;
//
//
//import java.util.Arrays;
//import java.util.Stack;
//
//public class StackUtil {
//
//
//    /**
//     * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
//     * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
//     */
//    public static Stack reverse(Stack s) {
//
//        if (s.isEmpty()) {
//            return s;
//        }
//        Stack stack = new Stack();
//
//        while (!s.empty())
//            stack.push(s.pop());
//        return stack;
//
//    }
//
//    /**
//     * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
//     *
//     * @param o
//     */
//    public static Stack remove(Stack s, Object o) {
//
//        if (s.isEmpty()) {
//            return s;
//        }
//        Stack stack = new Stack();
//
//        while (!s.empty()) {
//            Integer ss = (Integer) s.pop();
//            if (!o.equals(ss)) {
//                stack.push(ss);
//            }
//        }
//        return reverse(stack);
//
//
//    }
//
//    /**
//     * 从栈顶取得len个元素, 原来的栈中元素保持不变
//     * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
//     *
//     * @param len
//     * @return
//     */
//    public static Object[] getTop(Stack s, int len) {
//        Stack ss = s;
//
//        Object[] o = new Object[ss.size()];
//        if (s.empty()) {
//            return o;
//        }
//        if (len < 0) {
//            throw new ArrayIndexOutOfBoundsException();
//        }
//
//        int lens;
//        if (ss.size() < len) {
//            lens = ss.size();
//        } else {
//            lens = len;
//        }
//
//        for (int i = 0; i < lens; i++) {
//            o[i] = ss.pop();
//        }
//
//        return Arrays.copyOf(o, lens);
//
//    }
//
//    /**
//     * 字符串s 可能包含这些字符：  ( ) [ ] { }, a,b,c... x,yz
//     * <p>
//     * ++++++ * 使用堆栈检查字符串s中的括号是不是成对出现的。
//     * 例如s = "([e{d}f])" , 则该字符串中的括号是成对出现， 该方法返回true
//     * 如果 s = "([b{x]y})", 则该字符串中的括号不是成对出现的， 该方法返回false;
//     *
//     * @param s
//     * @return
//     */
//    public static boolean isValidPairs(String str) {
//        Stack<String> stack = new Stack<String>();
//
//        while (!str.isEmpty()) {
//            String head = str.substring(0,1);
//            str = str.substring(1);
//            if (head.equals("{") || head.equals("[") || head.equals("(")) {
//                stack.push(head);
//            } else if (head.equals("}") || head.equals("]") || head.equals(")")) {
//                if (stack.empty()) {
//                    return false;
//                }
//                String end = stack.pop();
//                if (head.equals(")")){
//                    if (!end.equals("(")) {
//                        return false;
//                    }
//                } else if (head.equals("{")) {
//                    if (!end.equals("}")) {
//                        return false;
//                    }
//                } else if (head.equals("[")) {
//                    if (!end.equals("]")) {
//                        return false;
//                    }
//
//                }
//            }
//        }
//
//        return stack.isEmpty();
//    }
//
//
//
//
//}
