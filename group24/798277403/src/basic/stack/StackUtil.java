package basic.stack;

import java.util.Stack;

/**
 * Created by zhouliang on 2017-04-08.
 */
class StackUtil {
    /**
     * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
     * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
     */
    public static void reverse(Stack<Integer> s) {
        if(s.empty()){
            return;
        }
        int i = getAndRemoveBottom(s); // 依次返回1、2、3
        reverse(s);
        s.push(i);
    }

    //移除并返回当前的栈底元素
    private static int getAndRemoveBottom(Stack<Integer> s){
        int result = s.pop();
        if(s.empty()){
            return result;
        }else{
            int bottom = getAndRemoveBottom(s);
            s.push(result);
            return bottom;
        }
    }



    /**
     * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
     * @param o
     */
    public static void remove(Stack<Object> s,Object o) {
        if(s.empty()){
            return;
        }
        Object temp = s.pop();
        if(temp == o){
            return;
        }
        remove(s,o);
        s.push(temp);
    }

    /**
     * 从栈顶取得len个元素, 原来的栈中元素保持不变
     * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
     * @param len
     * @return
     */
    public static Object[] getTop(Stack<Object> s,int len) {
        Object[] result = new Object[len];
        int index = 0;
        while(index<len){
            result[index] = getIndex(s,index+1);
            index++;
        }
        return result;
    }

    //获取第index个元素（从1开始）
    private static  Object getIndex(Stack<Object> s, int index){
        Object temp = s.pop();
        index--;
        if(0 == index){
            s.push(temp);
            return temp;
        }
        Object result = getIndex(s,index);
        s.push(temp);
        return result;
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
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<Character>();
        for(char c : chars){
            if(c=='(' || c=='[' || c=='{'){
                stack.push(c);
            }else if(c==')'){
                char top = stack.peek();
                if(top == '('){
                    stack.pop();
                }
            }else if(c==']'){
                char top = stack.peek();
                if(top == '['){
                    stack.pop();
                }
            }else if(c=='}'){
                char top = stack.peek();
                if(top == '{'){
                    stack.pop();
                }
            }
        }

        return stack.empty();
    }
}
