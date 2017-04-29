package assignment0405;

import assignment.Stack;

import java.util.Objects;

/**
 * Created by Administrator on 2017/4/6.
 */
public class StackUtil {


    /**
     * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
     * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
     */
    public static void reverse(Stack s) {
        Stack stack1 = new Stack();
        Stack stack2 = new Stack();
        popAllToAnotherStack(s, stack1);
        popAllToAnotherStack(stack1, stack2);
        popAllToAnotherStack(stack2, s);
    }

    private static void popAllToAnotherStack(Stack s, Stack another) {
        while (!s.isEmpty()) {
            another.push(s.pop());
        }
    }

    /**
     * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
     *
     * @param o
     */
    public static void remove(Stack s, Object o) {
        Stack stack2 = new Stack();
        while (!s.isEmpty()) {
            Object top = s.pop();
            //Objects.equals(null, null) == true
            if (!Objects.equals(top, o)) {
                stack2.push(top);
            }
        }
        popAllToAnotherStack(stack2, s);
    }

    /**
     * 从栈顶取得len个元素, 原来的栈中元素保持不变
     * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
     *
     * @param len
     * @return
     */
    public static Object[] getTop(Stack s, int len) {
        if (len > s.size())
            throw new RuntimeException("Stack size:" + s.size() + " < " + len);
        Object[] objects = new Object[len];
        for (int i = 0; i < len; i++) {
            objects[i] = s.pop();
        }
        for (int i = len - 1; i >= 0; i--) {
            s.push(objects[i]);
        }
        return objects;
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
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (isLeft(c)) {
                stack.push(c);
                continue;
            }
            if (isRight(c)) {
                if (stack.isEmpty() || !match(stack.pop(), c)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    private static boolean isLeft(char c) {
        return c == '[' || c == '(' || c == '{';
    }

    private static boolean isRight(char c) {
        return c == ']' || c == ')' || c == '}';
    }

    private static boolean match(char left, char right) {
        return (left == '(' && right == ')') || (left == '[' && right == ']') || (left == '{' && right == '}');
    }


}
