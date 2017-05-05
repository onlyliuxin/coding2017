package week05.stack;

public class StackUtil {

	/**
	 * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 */
	public static void reverse(Stack s) {
		if (s.isEmpty()) return;

		Stack sRev = new Stack();
		int nums = s.size();
		for (int i = 0; i < nums; i++) {
			sRev.push(s.pop());
		}
		
		Stack sRevTrans = new Stack();
		for (int i = 0; i < nums; i++) {
			sRevTrans.push(sRev.pop());
		}
		
		for (int i = 0; i < nums; i++) {
			s.push(sRevTrans.pop());
		}
	}

	/**
	 * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * 
	 * @param o
	 */
	public static void remove(Stack s, Object o) {
		if (s.isEmpty()) return;

		Stack sRem = new Stack();
		int nums = s.size();
		for (int i = 0; i < nums; i++) {
			if (!s.peek().equals(o)) {
				sRem.push(s.pop());
			} else {
				s.pop();
			}
		}

		int numsOfsRem = sRem.size();

		if (numsOfsRem == nums) return;

		for (int i = 0; i < numsOfsRem; i++) {
			s.push(sRem.pop());
		}
	}

	/**
	 * 从栈顶取得len个元素, 原来的栈中元素保持不变 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty，
	 * 可以使用另外一个栈来辅助
	 * 
	 * @param len
	 * @return
	 */
	public static Object[] getTop(Stack s, int len) {
		if(len > s.size() || len <= 0) return null;
		
		Object[] result = new Object[len];
		for(int i=0;i<len;i++){
			result[i] = s.pop();
		}
		
		for(int i=len-1;i>=0;i--){
			s.push(result[i]);
		}
		
		return result;
	}

	/**
	 * 字符串s 可能包含这些字符： ( ) [ ] { }, a,b,c... x,yz 使用堆栈检查字符串s中的括号是不是成对出现的。 例如s =
	 * "([e{d}f])" , 则该字符串中的括号是成对出现， 该方法返回true 如果 s = "([b{x]y})",
	 * 则该字符串中的括号不是成对出现的， 该方法返回false;
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isValidPairs(String s) {
		char[] arr = s.toCharArray();
		
		Stack s1 = new Stack();
		Stack s2 = new Stack();
		
		for(int i=arr.length-1;i>=0;i--){
			s1.push(arr[i]);
		}
		
		for(int i=0;i<arr.length;i++){
			s2.push(arr[i]);
		}
		
		int nums = arr.length;
		for(int i=0;i<nums/2;i++){
			Object left = s1.pop();
			Object right = s2.pop();
			
			if( (left.equals('(') && !right.equals(')'))
					|| (left.equals('[') && !right.equals(']'))
					|| (left.equals('{') && !right.equals('}')) ){
				return false;
			}
		}
		
		return true;
	}

}
