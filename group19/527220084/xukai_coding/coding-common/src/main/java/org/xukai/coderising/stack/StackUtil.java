package org.xukai.coderising.stack;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.regex.Pattern;

public class StackUtil {
	
	
	/**
	 * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 */
	public static void reverse(Stack s) {
		Stack stack1 = new Stack();
		while (!s.isEmpty()){
			stack1.push(s.pop());
		}
		Stack stack2 = new Stack();
		while (!stack1.isEmpty()){
			stack2.push(stack1.pop());
		}
		while (!stack2.isEmpty()){
			s.push(stack2.pop());
		}
	}
	@Test
	public void testReverse(){
		Stack stack = new Stack();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		stack.display();
		reverse(stack);
		stack.display();
	}

	/**
	 * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * 
	 * @param o
	 */
	public static void remove(Stack s,Object o) {
		Stack stack = new Stack();
		while (!s.isEmpty()){
			if (!s.peek().equals(o)) {
				stack.push(s.pop());
			} else {
				s.pop();
			}
		}
		while (!stack.isEmpty()){
			s.push(stack.pop());
		}
	}

	@Test
	public void testRemove(){
		Stack stack = new Stack();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		stack.display();
		remove(stack,3);
		stack.display();
	}


	/**
	 * 从栈顶取得len个元素, 原来的栈中元素保持不变
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * @param len
	 * @return
	 */
	public static Object[] getTop(Stack s,int len) {
		Preconditions.checkArgument(len > 0);
		Stack stack = new Stack();
		Object[] objects = new Object[Math.min(len,s.size())];
		for (int i = 0; i < len; i++) {
			if (s.isEmpty()) {
				break;
			}
			objects[i] = s.pop();
			stack.push(objects[i]);
		}
		while (!stack.isEmpty()){
			s.push(stack.pop());
		}
		return objects;
	}

	@Test
	public void testGetTop(){
		Stack stack = new Stack();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		stack.display();
		Object[] objects = getTop(stack, 6);
		for (int i = 0; i < objects.length; i++) {
			System.out.println(objects[i]);
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
		HashMap<String, String> map = Maps.newHashMap();
		map.put("}","{");
		map.put(")","(");
		map.put("]","[");
		Stack stack = new Stack();
		char[] chars = s.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if (Pattern.matches("[\\[({]{1}", chars[i]+"")) {
				stack.push(chars[i]);
			}
			if (Pattern.matches("[\\])}]{1}", chars[i]+"") && !map.get(chars[i]+"").equals(""+stack.pop())) {
				return false;
			}
		}
		return true;
	}

	@Test
	public void testIsValidPairs(){
		Assert.assertTrue(isValidPairs("[d(a)](da){21}"));
		Assert.assertTrue(!isValidPairs("[d(a{)}](da){21}"));
	}
	
	
}
