package DataStructure_5_Stack;

import java.util.Stack;

public class StackUtil {
	/*
	 * ����ջ�е�Ԫ����Integer����ջ����ջ���ǣ� 5,4,3,2,1 ���ø÷����󣬴�ջ����ջ�׻��� 1,2,3,4,5
	 * ע�⣺ ֻ��ʹ�� Stack�Ļ�����������push,pop,peek,isEmpty
	 */
	public static void reverse(Stack s){
		if(s.isEmpty()){ return; }
		int length = s.size();
		Object []temp = new Object[length];
		for(int i = 0; i < length; i++){
			temp[i] = s.pop();
		}
		for(int i = 0; i < length; i++){
			s.push(temp[i]);
		}
		return;
	}
	
	/*
	 * ɾ��ջ��ָ��Ԫ�أ�ע�⣺ֻ��ʹ��Stack�Ļ�����������push,pop,peek,isEmpty
	 * 
	 */
	public static void remove(Stack s, Object o){
		int length = s.size();
		Object elementTemp;
		System.out.println(length);
		int count = 0;
		Object []temp = new Object[length];
		if(s.isEmpty()){ return; }
		for(int i = 0; i < length;i++){
			elementTemp = s.pop();
			if(!o.equals(elementTemp)){
				temp[count++] = elementTemp;
				System.out.println(temp[i]);
			}
		}

		for(int i = count-1; i >= 0; i--){
			s.push(temp[i]);
		}
		return;
	}
	
	/*
	 * ��ջ��ȡ��len��Ԫ�أ�ԭ��ջ�е�Ԫ�ر��ֲ���
	 * @param len
	 * @return
	 */
	public static Object[]  getTop(Stack s , int len){
		if(s.isEmpty() || len > s.size() || len < 0){
			return null;
		}
		Object []result = new Object[len];
		for(int i = 0; i < len; i++){
			result[i] = s.pop();
		}
		return result;
	}
	
	/*
	 * �ַ��� s ���ܰ�����Щ�ַ��� () [] {}, a, b, c ... x, y, z
	 * ʹ�ö�ջ����ַ��� s �е������ǲ��ǳɶԳ��ֵ�
	 * ���磺 s = ��([e{d}f])��,����ַ����е������ǳɶԳ��ֵģ��÷������� true
	 * ����� s = "([b{x]})",����ַ����е����Ų��ǳɶԳ��ֵģ��÷��� ���� false
	 * @param s 
	 * @return
	 */
	public static boolean isValidPairs(String s){
		Stack stack = new Stack();
		System.out.println(stack.isEmpty());
		char elementTemp;
		boolean tag = false;
		char [] a = s.toCharArray();
		for(int i = 0; i < a.length; i++){
			if((!tag) && (a[i] == '(' || a[i] == ')' || a[i] == '[' || a[i] == ']' || a[i] == '{' || a[i] == '}')){
				stack.push(a[i]);
				tag = true;
			}else{
				if(a[i] == '(' || a[i] == ')' || a[i] == '[' || a[i] == ']' || a[i] == '{' || a[i] == '}'){
					elementTemp = (char) stack.pop();
					switch(elementTemp){
						case '(': if(a[i]==')'){}else{  stack.push(elementTemp);  stack.push(a[i]);  };  break; 
						case '[': if(a[i]==']'){}else{  stack.push(elementTemp);  stack.push(a[i]);  };  break;
						case '{': if(a[i]=='}'){}else{  stack.push(elementTemp);  stack.push(a[i]);  };  break;
						
					}	
				}
			}
			
		}
		if(stack.isEmpty()){
			return true;
		}
		return false;
	}
}






