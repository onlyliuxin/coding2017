package com.github.miniyk2012.coding2017.basic.stack;

import com.github.miniyk2012.coding2017.basic.Queue;
import java.util.*;

/**
 * Created by thomas_young on 5/4/2017.
 */
public class StackUtil {


    /**
     * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
     * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
     */
    public static void reverse(Stack s) {
        Queue q = new Queue();
        while (!s.isEmpty()) {
            q.enQueue(s.pop());
        }
        while (!q.isEmpty()) {
            s.push(q.deQueue());
        }
    }

    /**
     * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
     *
     * @param o
     */
    public static void remove(Stack s,Object o) {
        Stack tempS = new Stack();
        boolean found = false;
        while (!s.isEmpty()) {
            if (!found && Objects.equals(s.peek(), o)) {
                s.pop();
                found = true;
            } else {
                tempS.push(s.pop());
            }
        }
        while (!tempS.isEmpty()) {
            s.push(tempS.pop());
        }
    }

    /**
     * 从栈顶取得len个元素, 原来的栈中元素保持不变
     * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
     * @param len
     * @return
     */
    public static Object[] getTop(Stack s, int len) {
        if (len < 0) {
            return null;
        }
        Stack tempS = new Stack();
        while (tempS.size()<len && !s.isEmpty()) {
            tempS.push(s.pop());
        }
        int actualLen = tempS.size();
        Object[] o = new Object[actualLen];
        int i = actualLen;
        while (!tempS.isEmpty()) {
            Object temp = tempS.pop();
            o[--i] = temp;
            s.push(temp);
        }

        return o;
    }

    /**
     * 字符串s 可能包含这些字符：  ( ) [ ] { }, a,b,c... x,yz
     * 使用堆栈检查字符串s中的括号是不是成对出现的。
     * 例如s = "([e{d}f])" , 则该字符串中的括号是成对出现， 该方法返回true
     * 如果 s = "([b{x]y})", 则该字符串中的括号不是成对出现的， 该方法返回false;
     * @param s
     * @return
     */
    public static boolean isValidPairs(String s){
        Map<String, String> matchBrackets = new HashMap<String, String>() {{
            put("{", "}");
            put("[", "]");
            put("(", ")");
        }};

        Stack stack = new Stack();
        for (int i=0; i<s.length(); i++) {
            String c = s.substring(i, i+1);
            if (matchBrackets.keySet().contains(c)) {
                stack.push(c);
            } else if (matchBrackets.values().contains(c)) {
                try {
                    String preC = (String) stack.pop();
                    if (matchBrackets.get(preC).equals(c)) {
                        continue;
                    } else {
                        return false;
                    }
                } catch (Stack.NullStackException e) {
                    return false;
                }
            }
        }
        if (!stack.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public static void main(String[] args) {

        Stack s = new Stack();
        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);
        StackUtil.reverse(s);
        while (!s.isEmpty()) {
            System.out.print(s.pop());
        }
        System.out.println();

        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);
        s.push(2);
        StackUtil.remove(s, 2);
        while (!s.isEmpty()) {
            System.out.print(s.pop());
        }
        System.out.println();

        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);
        s.push(2);
        System.out.println(Arrays.toString(StackUtil.getTop(s, 10)));

        System.out.println((isValidPairs("[")));
        System.out.println((isValidPairs("[]")));
        System.out.println((isValidPairs("ad[(a{f})]db")));
        System.out.println((isValidPairs("ad[(a{f))]db")));
        System.out.println((isValidPairs("ad[(a{f}(saf)((afsdg)))]db")));
        System.out.println((isValidPairs("ad[))]db")));
        System.out.println((isValidPairs("(([[[ad[))]db")));
        System.out.println((isValidPairs("(([[[ad[))]db")));
        System.out.println((isValidPairs("(()")));
        System.out.println((isValidPairs("()")));
        System.out.println((isValidPairs("")));
        System.out.println((isValidPairs("([e{d}f])")));
        System.out.println((isValidPairs("([b{x]y})")));
    }
}
