package stackUtil;



public class StackUtil {
	
	
	
	/**
	 * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 */
	public static void reverse(Stack<Integer> s) {
		/*
		 * 注意值传递的问题
		 */
        if(s == null || s.isEmpty()) {
        	return;
        }
        
        int size = s.size();
        Stack<Integer> temp1 = new Stack<Integer>();
        Stack<Integer> temp2 = new Stack<Integer>();
        for(int i = 0; i < size; i++){
        	temp1.push(s.pop());
        }
        for(int i = 0; i < size; i++){
        	temp2.push(temp1.pop());
        }
        for(int i = 0; i < size; i++){
        	s.push(temp2.pop());
        }
        
		
		
	}

	
	/**
	 * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * 
	 * @param o
	 */
	public static void remove(Stack<Integer> s, int o) {
        if(s == null || s.isEmpty()) {
        	return;
        }
        
		Stack<Integer> newStack = new Stack<Integer>();
		int value = s.pop();
		while(value != o){
			newStack.push(value);
			value = s.pop();
		}
		while(!newStack.isEmpty()){
			s.push(newStack.pop());
		}
	}

	/**
	 * 从栈顶取得len个元素, 原来的栈中元素保持不变
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * @param len
	 * @return
	 */
	public static Object[] getTop(Stack<Integer> s,int len) {
		Object[] data = new Object[len];
		for(int i = 0; i < len; i++){
			data[i] = s.pop();
		}
		for(int i = len-1; i >= 0; i--){
			s.push((Integer)data[i]);
		}
		return data;
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
		Stack<String> stack = new Stack<String>();
		for(int i = 0; i < s.length(); i++){
			String c = s.charAt(i)+"";
			if(c.equals("{") || c.equals("[") || c.equals("(")){
				stack.push(c);
			}

			if(c.equals("}")){
				if(stack.peek().equals("{")){
					stack.pop();
				}else{
					return false;
				}
			}
			
			if(c.equals("]")){
				if(stack.peek().equals("[")){
					stack.pop();
				}else{
					return false;
				}
			}
			
			if(c.equals(")")){
				if(stack.peek().equals("(")){
					stack.pop();
				}else{
					return false;
				}
			}

		}
		return stack.size() == 0;
	}
	
	
}
