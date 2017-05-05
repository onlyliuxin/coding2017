package com.coding.basic.stack;

public class StackUtil {
	
	
	/**
	 * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 */
	public static void reverse(Stack s) {
		if(s==null || s.isEmpty()){ 
			return;
		};
		Stack temp = new Stack();
		int counter = s.size();
		while(counter>1){
			//Get the peek one
			Object o = s.pop();
			for(int i=0;i<counter-1;i++){
				temp.push(s.pop());
			}
			s.push(o);
			for(int j=0;j<counter-1;j++){
				s.push(temp.pop());
			}
			counter--;
		}
	}

	/**
	 * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * 
	 * @param o
	 */
	public static void remove(Stack s,Object o) {
		if(s==null || s.isEmpty()){ 
			return;
		};
		int size = s.size();
		int counter;
		Stack tmpStk = new Stack();
		for(counter = 0; counter<size;counter++){
			Object tmp = s.pop();
			if(!tmp.equals(o)){
				tmpStk.push(tmp);
			}else{
				break;
			}
		}
		for(int i=0;i<counter;i++){
			s.push(tmpStk.pop());
		}
		
	}

	/**
	 * 从栈顶取得len个元素, 原来的栈中元素保持不变
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * @param len
	 * @return
	 */
	public static Object[] getTop(Stack s,int len) {
		if(s==null || s.isEmpty()){
			return new Object[0];
		}
		if(len>s.size()){
			throw new RuntimeException("Index Out of Bound:"+ len);
		}
		Object[] objArr = new Object[len];
		Stack tmpStk  = new Stack();
		for(int i=0;i<len;i++){
			Object obj = s.pop();
			objArr[i] = obj;
			tmpStk.push(obj);
		}
		for(int i=0;i<len;i++){
			s.push(tmpStk.pop());
		}
		return objArr;
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
		if(s==null || s.length()==0){
			return false;
		}
		if(s.indexOf("(")<0 && s.indexOf(")")<0 && 
				s.indexOf("[")<0 && s.indexOf("]")<0 &&
				s.indexOf("{")<0 && s.indexOf("}")<0){
			return false;
		}
		
		char[] charArr = s.toCharArray();
		Stack stk = new Stack();
		
		for(int i=0;i<charArr.length;i++){
			char c = charArr[i];
			if(c=='(' || c=='[' || c=='{'){
				stk.push(c);
			}
			if(c==')' && stk.peek().equals('(')){
				stk.pop();
			}
			if(c==']' && stk.peek().equals('[')){
				stk.pop();
			}
			if(c=='}' && stk.peek().equals('{')){
				stk.pop();
			}
		}
		System.out.println(stk);
		return stk.isEmpty();
	}
	
	public static void main(String args[]){
		Stack s = new Stack();
		s.push("1");
		s.push("2");
		s.push("3");
		s.push("4");
		s.push("5");
		System.out.println(s);
		StackUtil.reverse(s);
		System.out.println(s);
		StackUtil.remove(s, "2");
		System.out.println(s);
		Object[] arr = StackUtil.getTop(s, 3);
		System.out.println(s);
		System.out.println(arr.length+":"+arr[0]+arr[1]+arr[2]);
		System.out.println("isValidPairs:"+StackUtil.isValidPairs("ABCDF"));
		System.out.println("isValidPairs:"+StackUtil.isValidPairs("{A[B(C)D]F}"));
		System.out.println("isValidPairs:"+StackUtil.isValidPairs("{([A[B)}CDF"));
	}
}
