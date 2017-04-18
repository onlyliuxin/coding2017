package io.github.vxzh.datastructure.less5;


public class StackUtil {

    public static void main(String[] args) {
        String s = "(9[O{er}P]0)";
        System.out.println(isValidPairs(s));
    }

    /**
     * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
     * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
     */
    public static void reverse(Stack s) {
        Stack stack = s;
        s = new Stack();
        while (!stack.isEmpty()) {
            s.push(stack.pop());
        }
    }

    /**
     * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
     *
     * @param o
     */
    public static void remove(Stack s, Object o) {
        Stack stack = s;
        s = new Stack();
        while (!stack.isEmpty()) {
            Object obj = stack.pop();
            if (obj != o) {
                s.push(obj);
            }
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
            Object[] arr = new Object[s.size()];
            int i = 0;
            while (!s.isEmpty()) {
                arr[i] = s.pop();
                i++;
            }
            for (int j = 0; j < arr.length; j++) {
                s.push(arr[j]);
            }
            return arr;
        } else {
            Object[] arr = new Object[len];
            int i = 0;
            while (!s.isEmpty()) {
                arr[i] = s.pop();
                i++;
            }
            for (int j = 0; j < arr.length; j++) {
                s.push(arr[j]);
            }
            return arr;
        }
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
        char[] arr = s.toCharArray();
        Stack st1 = new Stack();
        Stack st2 = new Stack();
        for (int i = 0; i < arr.length; i++) {
            st1.push(arr[i]);
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            st2.push(arr[i]);
        }

        while (!st1.isEmpty()) {

            char ch1 = (char) st1.pop();
            char ch2 = (char) st2.pop();
            switch (ch1) {
                case '{':
                    if (ch2 != '}') {
                        return false;
                    }
                    break;
                case '[':
                    if (ch2 != ']') {
                        return false;
                    }
                    break;
                case '(':
                    if (ch2 != ')') {
                        return false;
                    }
                    break;
            }
        }

        return true;
    }


}