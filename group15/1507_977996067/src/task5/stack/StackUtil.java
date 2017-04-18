package task5.stack;

@SuppressWarnings("unchecked")
public class StackUtil {


    /**
     * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
     * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
     */
    public static void reverse(Stack s) {
        Stack temp1 = new Stack();
        Stack temp2 = new Stack();
        int size = s.size();
        while (size > 0) {
            temp1.push(s.pop());
            size--;
        }
        while (size > 0) {
            temp2.push(temp1.pop());
            size--;
        }
        while (size > 0) {
            s.push(temp2.pop());
            size--;
        }
    }

    /**
     * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
     */
    public static void remove(Stack s, Object o) {
        Stack temp = new Stack();
        while (!s.isEmpty()) {
            Object val = s.pop();
            if (val != o)
                temp.push(val);
            else break;
        }
        while (!temp.isEmpty()) {
            s.push(temp.pop());
        }
    }

    /**
     * 从栈顶取得len个元素, 原来的栈中元素保持不变
     * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
     */
    public static Object[] getTop(Stack s, int len) {
        if (len > 0 && len <= s.size()) {
            Object[] result = new Object[len];
            while (len > 0) {
                result[len--] = s.pop();
            }
            return result;
        }
        return null;
    }

    /**
     * 字符串s 可能包含这些字符：  ( ) [ ] { }, a,b,c... x,yz
     * 使用堆栈检查字符串s中的括号是不是成对出现的。
     * 例如s = "([e{d}f])" , 则该字符串中的括号是成对出现， 该方法返回true
     * 如果 s = "([b{x]y})", 则该字符串中的括号不是成对出现的， 该方法返回false;
     */
    public static boolean isValidPairs(String s) {
        int length = s.length();
        int size = length / 2;
        Stack temp1 = new Stack();
        Stack temp2 = new Stack();
        int position = 0;
        while (position <= size) {
            temp1.push(s.charAt(position));
            temp2.push(s.charAt(length - position));
        }

        int tempPosition = 0;
        while (tempPosition <= size) {
            if (temp1.pop() != temp2.pop()) {
                return false;
            }
        }
        return true;
    }


}