package com.coding.basic.stack;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Stack;

import com.coding.basic.LinkedList;

public class StackUtil {
	
	
	/**
	 * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * @param <T>
	 */
	public static void reverse(Stack s) {
		LinkedList list = new LinkedList();
		while(!s.isEmpty()){
			list.add(s.pop());
		}
		for(int i=0;i<list.size();i++){
			s.push(list.get(i));
		}	
	}

	/**
	 * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * 
	 * @param o
	 */
	public static void remove(Stack<Integer> testStack,Object o) {
		int index = testStack.search(o);
		if(index == -1){//判断Object o是否在栈中，不在return
			System.out.println("删除元素"+String.valueOf(o)+"不在栈中");
			return;
		}
		else{//Object o在栈中，remove
			System.out.println("删除元素"+String.valueOf(o)+"的索引位置是Index=" + String.valueOf(index));
			//Remove操作
			Stack<Integer> temp = new Stack<Integer>();
			while(testStack.peek() != o){
			temp.push(testStack.pop());
			}
			testStack.pop();
			while(!temp.empty()){
			testStack.push(temp.pop());
			}
		}
	}

	/**
	 * 从栈顶取得len个元素, 原来的栈中元素保持不变
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * @param len
	 * @return
	 */
	public static Object[] getTop(Stack<Integer> s,int len) {
		if(len>s.size()){//如果len>stack.size(),则return null;
			System.out.println("溢出");
			return null;
		}
		else{
			Stack<Integer> temp = new Stack<Integer>();
			int len_temp = len;
			int j = 0;
			Object[] obj = new Object[len];
			while(len_temp--!=0){
				int val = s.pop();
				obj[j] = val;
				j++;
				temp.push(val);
			}	
			while(!temp.empty()){	
				s.push(temp.pop());
			}
			
			return obj;
		}
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
		Stack<Character> tempstack = new Stack<Character>();
		char[] charArr = s.toCharArray();
		for(char item:charArr){		
			if(item=='(' || item=='[' || item=='{' ){
				tempstack.push(item);
				continue;
			}
			switch(item){
			case ')':
				if(tempstack.peek()=='('){
					tempstack.pop();
				}else{
					return false;
				}
				break;
			case '}':
				if(tempstack.peek()=='{'){
					tempstack.pop();
				}else{
					return false;
				}
				break;
			case ']':
				if(tempstack.peek()=='['){
					tempstack.pop();
				}else{
					return false;
				}
				break;
			}
		}
			return true;
	}
	
	public String printStack(java.util.Stack<Integer> s){
		String str = "";
		if (s.empty())
            System.out.println("堆栈是空的，没有元素");
            else {
                System.out.print("堆栈中的元素(从栈底到栈顶)：");
//                Enumeration items = s.elements(); // 得到 stack 中的枚举对象
//                while (items.hasMoreElements()) //显示枚举（stack ） 中的所有元素
//                    System.out.print(items.nextElement()+" ");
                int count = 0;
                for(int i:s){	
            		str = str + String.valueOf(i);
            		if(count++ != s.size()-1){
            			str = str + ",";
            		}
            	}
            }
		System.out.println(str);
        return str;
    }
	
}
