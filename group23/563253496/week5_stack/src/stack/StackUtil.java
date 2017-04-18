package stack;

import stack.Stack;

/**
 * Created by bdl19 on 2017/4/8.
 */

public class StackUtil {


    public static void main(String[] args) {
        Stack s = new Stack();
        for (int i = 1; i <=5 ; i++) {
            s.push(i);
        }
        System.out.println(s);
        reverse(s);
        System.out.println(s);
    }
    /**
     * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
     * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
     */
    public static void reverse(Stack s) {
        Stack temp = new Stack();
        while (!s.isEmpty()) {
            temp.push(s.pop());
        }
        //System.out.println(temp);
        s = temp;

    }

    /**
     * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
     *
     * @param o
     */
    public static void remove(Stack s, Object o) {
        Stack temp = new Stack();
        Object obj;
        while (!s.isEmpty()) {
            obj = s.pop();
            if (obj.equals(o)) {
                break;
            }
            temp.push(obj);
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
        if (len > s.size()) {
            throw new IndexOutOfBoundsException();
        }
        Object[] o = new Object[len];
        Stack temp = new Stack();

        for (int i = 0; i < len; i++) {
            o[i] = s.peek();
            temp.push(s.pop());

        }
        while (!temp.isEmpty()) {
            s.push(temp.pop());
        }
        return o;

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
        char[] chars = s.toCharArray();
        Stack stack = new Stack();

        for (int i = 0; i < chars.length; i++) {
            switch (chars[i]) {
                case '(':
                    stack.push('(');
                    break;
                case '[':
                    stack.push('[');
                    break;
                case '{':
                    stack.push('{');
                    break;
                case ')':
                    if (!stack.pop().equals('(')) {
                        return false;
                    }
                    break;
                case ']':
                    if (!stack.pop().equals('[')) {
                        return false;
                    }
                    break;
                case '}':
                    if (!stack.pop().equals('{')) {
                        return false;
                    }
                    break;
            }
        }

        return true;
    }


}
