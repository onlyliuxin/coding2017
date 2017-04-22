package basic.dataStructure.stack;

public class StackUtil {
	
	
	
	/**
	 * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 */
	public static Stack reverse(Stack s) {
		Stack st = new Stack();
		int size = s.size();
		for(int i = 0; i < size; i++){
			st.push(s.pop());
		}
		return st;
	}
	
	/**
	 * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * 
	 * @param o
	 */
	public static Stack remove(Stack s,Object o) {
		Stack tmp = new Stack();
		int size = s.size();
		for(int i = 0; i < size; i++){
			Object obj = s.peek();
			if(obj != o && !obj.equals(o)){
				tmp.push(s.pop());
			}else{
				s.pop();
			}
		}
		return tmp;
	}

	/**
	 * 从栈顶取得len个元素, 原来的栈中元素保持不变
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * @param len
	 * @return
	 */
	public static Object[] getTop(Stack s,int len) {
		Stack temp = new Stack();
		int size = s.size();
		Object[] objs = new Object[len];
		for(int i = 0; i < size; i++){
			Object obj = s.pop();
			if(i < len){
				objs[i] = obj;
			}
			temp.push(obj);
		}

		for(int i = 0 ; i < size; i ++){
			s.push(temp.pop());
		}

		return objs;
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
		//括号
		Stack bracket = new Stack(")", "(");

		//方括号
		Stack brackets = new Stack("]", "[");

		//花括号
		Stack braces = new Stack("}", "{");

		char[] symArr = s.toCharArray();
		int length = symArr.length;
		for(int i = 0; i < length/2; i++){
			String sym = String.valueOf(symArr[i]);
			String endSym = String.valueOf(symArr[length - i -1]);
			if(sym.equals(bracket.peek())){
				bracket = remove(bracket, sym);
				bracket = remove(bracket, endSym);
			}

			if(sym.equals(brackets.peek())){
				brackets = remove(brackets, sym);
				brackets = remove(brackets, endSym);
			}

			if(sym.equals(braces.peek())){
				braces = remove(braces, sym);
				braces = remove(braces, endSym);
			}
		}
		return bracket.isEmpty() && brackets.isEmpty() && braces.isEmpty();
	}
	
	
}
