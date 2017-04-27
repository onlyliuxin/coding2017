package com.github.orajavac.coding2017.basic.stack;

public class StackUtil {
	
	/**
	 * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 */
	public static void reverse(Stack s) {
		System.out.println(s.toString());
	}

	/**
	 * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * 
	 * @param o
	 */
	public static void remove(Stack s,Object o) {
		s.remove(o);
	}

	/**
	 * 从栈顶取得len个元素, 原来的栈中元素保持不变
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * @param len
	 * @return
	 */
	public static Object[] getTop(Stack s,int len) {
		return s.getElements(len);
	}
	/**
	 * 字符串s 可能包含这些字符：  ( ) [ ] { }, a,b,c... x,yz
	 * 使用堆栈检查字符串s中的括号是不是成对出现的。
	 * 例如s = "([e{d}f])" , 则该字符串中的括号是成对出现， 该方法返回true
	 * 如果 s = "([b{x]y})", 则该字符串中的括号不是成对出现的， 该方法返回false;
	 * @param s
	 * @return
	 */
	public static boolean isValidPairs(Stack s,String str){
		for (int i=0;i<str.length();i++){
			char c1 = str.charAt(i);
			if(isLeftCase(c1)){
				s.push(c1);	//左符号压栈
			}else if (isRightCase(c1)){
				Object obj = s.getFirstNode();
				char[] c = obj.toString().toCharArray();
				if(isCase(c[0],c1)){	//左、右匹配
					s.remove(obj);
				}else{
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * StackUtil 使用
	 * @param c1
	 * @param c2
	 * @return
	 */
	public static boolean isCase(char c1,char c2){
	    if(c1=='('&&c2==')')
	        return true;
	    if(c1=='{'&&c2=='}')
	        return true;
	    if(c1=='['&&c2==']')
	        return true;
	    return false;//其他的都返回false
	};
	
	/**
	 * StackUtil 使用
	 * @param c1
	 * @return
	 */
	public static boolean isLeftCase(char c1){
		if(c1=='(')
	        return true;
	    if(c1=='{')
	        return true;
	    if(c1=='[')
	        return true;
	    return false;//其他的都返回false
	};
	
	/**
	 * StackUtil 使用
	 * @param c1
	 * @return
	 */
	public static boolean isRightCase(char c1){
		if(c1==')')
	        return true;
	    if(c1=='}')
	        return true;
	    if(c1==']')
	    	return true;
	    return false;//其他的都返回false
	};
}
