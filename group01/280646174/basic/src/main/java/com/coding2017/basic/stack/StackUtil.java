package com.coding2017.basic.stack;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class StackUtil {

    /**
     * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty，
     * 可以使用另外一个栈来辅助 取出栈顶, 把其他元素腾出去, 把栈顶放到栈底, 再把别的元素放回去
     */
    public static void reverse(Stack s) {
        if (s == null || s.isEmpty()) {
            return;
        }

        Stack tempStack = new Stack();
        Integer bottom = null; // 到什么元素为止
        Integer temp;

        while (!reachBottom(s, bottom)) {
            temp = (Integer) s.pop();
            while (!reachBottom(s, bottom)) {
                tempStack.push(s.pop());
            }
            s.push(temp);
            while (!tempStack.isEmpty()) {
                s.push(tempStack.pop());
            }
            bottom = temp;
        }
    }

    private static boolean reachBottom(Stack stack, Integer bottom) {
        return stack.isEmpty() || stack.peek() == bottom;
    }

    /**
     * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
     * 
     * @param o
     */
    public static void remove(Stack s, Object o) {
        Stack tempStack = new Stack();
        while (!s.isEmpty()) {
            if (s.peek().equals(o)) {
                s.pop();
                break;
            }
            tempStack.push(s.pop());
        }
        while (!tempStack.isEmpty()) {
            s.push(tempStack.pop());
        }
    }

    /**
     * 从栈顶取得len个元素, 原来的栈中元素保持不变 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
     * 
     * @param len
     * @return
     */
    public static Object[] getTop(Stack s, int len) {
        Object[] result = new Object[len];
        Stack tempStack = new Stack();
        for (int i = 0; i < len; i++) {
            if (s.isEmpty()) {
                break;
            }
            result[i] = s.peek();
            tempStack.push(s.pop());
        }
        while (!tempStack.isEmpty()) {
            s.push(tempStack.pop());
        }
        return result;
    }

    /**
     * 字符串s 可能包含这些字符： ( ) [ ] { }, a,b,c... x,yz 使用堆栈检查字符串s中的括号是不是成对出现的。 例如s = "([e{d}f])" , 则该字符串中的括号是成对出现， 该方法返回true
     * 如果 s = "([b{x]y})", 则该字符串中的括号不是成对出现的， 该方法返回false;
     * 
     * @param s
     * @return
     */
    public static boolean isValidPairs(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (isRightBracket(c)) {
                if (stack.isEmpty() || !isMatchBracket(stack.pop(), c)) {
                    return false;
                }
            } else if (isLeftBracket(c)) {
                stack.push(c);
            }
        }

        return stack.isEmpty();
    }

    private static boolean isLeftBracket(Character character) {
        final Set<Character> leftBrackets = ImmutableSet.copyOf(new Character[] { '(', '[', '{' });
        return leftBrackets.contains(character);
    }

    private static boolean isRightBracket(Character character) {
        final Set<Character> rightBrackets = ImmutableSet.copyOf(new Character[] { ')', ']', '}' });
        return rightBrackets.contains(character);
    }

    private static boolean isMatchBracket(Character left, Character right) {
        final Map<Character, Character> bracketMap = ImmutableMap.<Character, Character> builder().put(')', '(')
                .put(']', '[').put('}', '{').build();
        return left.equals(bracketMap.get(right));
    }

}
