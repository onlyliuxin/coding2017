package cn.xl.basic.stack;


public class StackUtil {


	/**
	 * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
	 * 注意：只能使用MyStack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 */
	public static void reverse(MyStack s) {

		if(s == null || s.isEmpty()){
			return ;
		}

		MyStack ns =  new MyStack();
		Object o = s.pop();
		int count = 0;
		while(!s.isEmpty()){
			ns.push(s.pop()); 
			count ++;
		}
		s.push(o);

		while(!ns.isEmpty()){
			s.push(ns.pop());
		}

		for(int i = 0; i < count - 1 ;i++){
			o = s.pop();
			for(int j = 0; j < count - 1 - i ;j++){
				ns.push(s.pop()); 
			}
			s.push(o);
			while(!ns.isEmpty()){
				s.push(ns.pop());
			}
		}

		//组员写的 ，不错
		/*MyStack a = new MyStack();

		while (!s.isEmpty()){
			Object o = s.pop();
			int count =0;
			while (!a.isEmpty()){
				s.push(a.pop());
				count++;
			}
			a.push(o);
			for (int i = 0; i < count; i++) {
				a.push(s.pop());
			}
		}
		while (!a.isEmpty()){
			s.push(a.pop());
		}

       return s;*/
	}

	/**
	 * 删除栈中的某个元素 注意：只能使用MyStack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * 
	 * @param o
	 */
	public static void remove(MyStack s,Object o) {

		if(s == null || s.isEmpty()){
			return ;
		}

		MyStack ms =  new MyStack();
		Object obj = null;
		while(!s.isEmpty()){
			obj = s.pop();
			if(obj == null){
				if(o == null){
					continue;
				}else{
					ms.push(obj);
				}
			}else{
				if(obj.equals(o)|| obj.toString().equals(o)){
					continue;
				}else{
					ms.push(obj);
				}
			}
		}

		while(!ms.isEmpty()){
			s.push(ms.pop());
		}

	}

	/**
	 * 从栈顶取得len个元素, 原来的栈中元素保持不变
	 * 注意：只能使用MyStack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * @param len
	 * @return
	 */
	public static Object[] getTop(MyStack s,int len) {
		MyStack ns =  new MyStack();
		int count = 0;
		Object[] o = new Object[len];
		while(!s.isEmpty()){
			ns.push(s.pop());
			count++;
		}
		int n = 0;
		for(int i = 0; i < count - len;i++){
			if(i < count - len){
				s.push(ns.pop());
			}else{
				o[n] = ns.pop();
				n++;
			}
		}

		return o;
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

		if(s == null || "".equals(s)){
			return true;
		}
		char[]  b = s.toCharArray();
		MyStack ms = new MyStack();
		String str = null;
		for(int i = 0; i < b.length ; i++){
			str = String.valueOf(b[i]);
			if("(".equals(str)){
				ms.push(b[i]);
			}else if(")".equals(str)){
				if(!popObj(ms,"("))return false;
			}else if("[".equals(str)){
				ms.push(b[i]);
			}else if("]".equals(str)){
				if(!popObj(ms,"["))return false;
			}else if("{".equals(str)){
				ms.push(b[i]);
			}else if("}".equals(str)){
				if(!popObj(ms,"{"))return false;
			}
		}
		if(ms.isEmpty()){
			return true;
		}
		return false;
	}


	private static boolean popObj(MyStack s,Object o) {

		MyStack ms =  new MyStack();
		Object obj = null;
		while(!s.isEmpty()){
			obj = s.pop();
			if(obj.equals(o)||obj.toString().equals(o)){
				while(!ms.isEmpty()){
					s.push(ms.pop());
				}
				return true;
			}else{
				ms.push(obj);
			}
		}
		return false;
	}



	public static void main(String[] args){



	}

}
