package stack;

/**
 * Created by gongxun on 2017/4/12.
 */
public class StackUtil {
    public static void bad_reverse(MyStack<Integer> s) {

    }


    public static void reverse_247565311(MyStack<Integer> s) {

    }


    /**
     * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
     * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
     */
    public static void reverse(MyStack<Integer> s) {
        MyStack<Integer> tempStack = new MyStack<Integer>();
        while (!s.isEmpty())
            addToBottom(tempStack, s.pop());
        while (!tempStack.isEmpty())
            s.push(tempStack.pop());
    }

    public static void addToBottom(MyStack<Integer> s, Integer value) {
        MyStack<Integer> reserveStack = new MyStack<Integer>();
        while (!s.isEmpty())
            reserveStack.push(s.pop());
        s.push(value);
        while (!reserveStack.isEmpty())
            s.push(reserveStack.pop());
    }

    /**
     * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
     *
     * @param o
     */
    public static void remove(MyStack s, Object o) {
        if (null == o)
            return;
        MyStack tempStack = new MyStack();
        while (!s.isEmpty()) {
            if (!s.peek().equals(o)) {
                tempStack.push(s.pop());
            } else {
                s.pop();
                break;
            }
        }
        while (!tempStack.isEmpty())
            s.push(tempStack.pop());
    }


    /**
     * 从栈顶取得len个元素, 原来的栈中元素保持不变
     * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
     *
     * @param len
     * @return
     */
    public static Object[] getTop(MyStack s, int len) {
        if (len > s.size())
            return null;
        Object[] datas = new Object[len];
        for (int i = 0; i < len; i++) {
            datas[i] = s.pop();
        }
        return datas;
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
        if (s == null || s.trim().isEmpty())
            return false;
        String[] arr = s.split("");
        MyStack<String> stack = new MyStack<String>();
        for (int i = 0; i < arr.length; i++) {
            if ("(".equals(arr[i]) || "[".equals(arr[i]) || "{".equals(arr[i]))
                stack.push(arr[i]);
            if (")".equals(arr[i])) {
                if (stack.peek().equals("("))
                    stack.pop();
                else
                    return false;
            }
            if ("]".equals(arr[i])) {
                if (stack.peek().equals("["))
                    stack.pop();
                else
                    return false;
            }
            if ("}".equals(arr[i])) {
                if (stack.peek().equals("{"))
                    stack.pop();
                else
                    return false;
            }
        }
        return stack.size() == 0;//最后判断stack中没有元素了，可能有单个括号
    }
}
