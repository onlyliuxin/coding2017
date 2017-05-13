package stack;

/**
 * Created by Lxx on 2017/4/23.
 */
public class StackUtil {


    /**
     * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
     * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
     */
    public static void reverse(Stack s) {
        if(s == null || s.isEmpty()){
            return;
        }

        Stack tmp = new Stack();
        while(!s.isEmpty()){
            tmp.push(s.pop());
        }
        while(!tmp.isEmpty()){
            Integer top = (Integer) tmp.pop();
            addToBottom(s,top);
        }


    }
    public static void addToBottom(Stack s,  Integer value){
        if(s.isEmpty()){
            s.push(value);
        } else{
            Integer top = (Integer) s.pop();
            addToBottom(s,value);
            s.push(top);
        }

    }

    /**
     * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
     *
     * @param o
     */
    public static void remove(Stack s, Object o) {

        if(s == null || s.isEmpty()){
            return;
        }
        Stack tmpStack = new Stack();
        Stack s1 = new Stack();
        while(!s.isEmpty()){
            Object value = s.pop();
            if(!value.equals(o)){
                tmpStack.push(value);
            }
        }

        while(!tmpStack.isEmpty()){
            s1.push(tmpStack.pop());
        }
        while(!s1.isEmpty()){
            s.push(s1.pop());
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

        if(s == null || s.isEmpty() || s.size() < len || len <= 0){
            return null;
        }
        Stack tmpStack = new Stack();
        int i = 0;
        Object[] topElement = new Object[len];
        while(!s.isEmpty()){
            Object top = s.pop();
            tmpStack.push(top);
            topElement[i++] = top;
            if(i == len){
                break;
            }
        }
        while (!tmpStack.isEmpty()){
            s.push(tmpStack.pop());
        }
        return topElement;
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

        Stack tmpStack = new Stack();
        for (int i = 0; i < s.length(); i++) {
            char tmpChar = s.charAt(i);
            if (tmpChar == '(' || tmpChar == '{' || tmpChar == '[') {
                tmpStack.push(tmpChar);
            } else if (tmpChar == ')') {
                char compareChar = (char) tmpStack.pop();
                if (compareChar != '(') {
                    return false;
                }
            } else if (tmpChar == ']') {
                char compareChar = (char) tmpStack.pop();
                if (compareChar != '[') {
                    return false;
                }
            } else if (tmpChar == '}') {
                char compareChar = (char) tmpStack.pop();
                if (compareChar != '{') {
                    return false;
                }
            }
        }
        return tmpStack.size() == 0;
    }
}
