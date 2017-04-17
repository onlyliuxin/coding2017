package com.bruce.homework0409.stack;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.Stack;

public class StackUtil {
	
	/**
	 * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 */
	public static Stack reverse(Stack s) {
		if (s == null || s.isEmpty()) {
			return s;
		}
		Stack temp = new Stack();
        int size = s.size();
		for (int i = 0; i < size; i++) {
			temp.push(s.pop());
		}
		return temp;
	}

	/**
	 * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * 
	 * @param o
	 */
	public static void remove(Stack s,Object o) {
		if (s == null || s.isEmpty()) {
			return;
		}
		Stack temp = new Stack();
        int size = s.size();
		for (int i = 0; i < size; i++) {
            Object pop = s.pop();
            if (Objects.equals(o, pop)) {
				continue;
			}
			temp.push(pop);
		}
		for (int j = 0; j < size; j++) {
		    if(!temp.isEmpty()) {
                s.push(temp.pop());
            }
        }
	}

	/**
	 * 从栈顶取得len个元素, 原来的栈中元素保持不变
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * @param len
	 * @return
	 */
	public static Object[] getTop(Stack s, int len) {
	    if (s == null || s.isEmpty() || len < 0) {
	        return null;
        }
        Object[] array = new Object[s.size()];
        Object[] result = new Object[len];
        Stack copy = new  Stack();
        int size = s.size();
        for (int i = 0; i < size; i++) {
            array[i] = s.pop();
        }
        for (int j = array.length - 1; j >= 0; j--) {
            s.push(array[j]);
            if (array.length-1-j < len) {
                result[array.length-1-j] = array[j];
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
	    if (s == null || s.trim().length() < 2) {
	        return false;
        }
	    char[] array = s.toCharArray();
        StringBuffer sb = new StringBuffer("#");
        //从原字符串中提取出“()[]{}”这几种字符，顺序与在原字符串中出现的顺序相同
        for (int i = 0; i <  array.length; i++) {
            if (array[i] == '(' || array[i] == ')' || array[i] == '[' || array[i] == ']'
                    || array[i] == '{' || array[i] == '}') {
                sb.append(array[i]);
            }
        }
        if (sb.length() % 2 == 0) {
            return false;
        }
        //将得到的新的字符串拆分为字符数组，根据数组下标判断应该成对出现的括号
        String str = sb.toString();
        char[] chars = str.toCharArray();
        LinkedHashMap<Character, Integer> map = new LinkedHashMap();
        for (int i = 1; i < chars.length; i++) {
            if (map.containsKey(chars[i])) {
                map.put(chars[i], map.get(chars[i]) + i);
            } else {
                map.put(chars[i], i);
            }
        }
        if (map.containsKey('(') && !map.containsKey(')') || map.containsKey('[') && !map.containsKey(']')
                || map.containsKey('{') && !map.containsKey('}')) {
            return false;
        }
        int parentheses = map.get('(') + map.get(')');
        int bracket = map.get('[') + map.get(']');
        int brace = map.get('{') + map.get('}');
        return parentheses == bracket && bracket == brace;
	}
}
