package com.pan.alg;


import java.util.Stack;

/**
 * Created by QiPan on 2017/4/12.
 */
public class StackUtil {

    public static void bad_reverse(Stack<Integer> s) {
        if(s == null || s.isEmpty()){
            return;
        }
        Stack<Integer> tmpStack = new Stack<>();
        while(!s.isEmpty()){
            tmpStack.push(s.pop());
        }

        s = tmpStack;

    }

    /**
     * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
     * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
     */
    public static void reverse(Stack<Integer> s) {
        if(s == null || s.isEmpty()){
            return;
        }
        Integer top = s.pop();
        reverse(s);
        addToBottom(s,top);


    }
    public static void addToBottom(Stack<Integer> s,  Integer value){
        if(s.isEmpty()){
            s.push(value);
        } else{
            Integer top = s.pop();
            addToBottom(s,value);
            s.push(top);
        }

    }
    /**
     * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
     *
     * @param o
     */
    public static void remove(Stack s,Object o) {
        if(s == null || s.isEmpty()){
            return;
        }
        Stack tmpStack = new Stack();

        while(!s.isEmpty()){
            Object value = s.pop();
            if(!value.equals(o)){
                tmpStack.push(value);
            }
        }

        while(!tmpStack.isEmpty()){
            s.push(tmpStack.pop());
        }
    }

    /**
     * 从栈顶取得len个元素, 原来的栈中元素保持不变
     * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
     * @param len
     * @return
     */
    public static Object[] getTop(Stack s,int len) {

        if(s == null || s.isEmpty() || s.size()<len || len <=0 ){
            return null;
        }

        Stack tmpStack = new Stack();
        int i = 0;
        Object[] result = new Object[len];
        while(!s.isEmpty()){
            Object value = s.pop();
            tmpStack.push(value);
            result[i++] = value;
            if(i == len){
                break;
            }
        }

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

        Stack<Character> stack = new Stack<>();
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);

            if(c == '(' || c =='[' || c == '{'){

                stack.push(c);

            } else if( c == ')'){

                char topChar = stack.pop();
                if(topChar != '('){
                    return false;
                }

            } else if( c == ']'){

                char topChar = stack.pop();
                if(topChar != '['){
                    return false;
                }

            } else if( c == '}'){

                char topChar = stack.pop();
                if(topChar != '{'){
                    return false;
                }

            }
        }
        return stack.size() == 0;
    }

}
