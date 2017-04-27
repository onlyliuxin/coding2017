package com.coding.basic.stack.expr;

import java.util.ArrayList;

import com.coding.basic.stack.Stack;
import com.coding.basic.stack.StackUtil;

public class InfixExpr {
	private String expr = null;
	
	public InfixExpr(String expr) {
		this.expr = expr;
	}
	/**
	 * 对expr进行解析计算
	 * @return
	 */
	public float evaluate() {
		float f = 0.0f;
		//如果不为空继续解析
		if (expr != null || expr.length() > 0) {
			//如果符号不对称,抛出异常
			if (!StackUtil.isValidPairs(expr)) {
				try {
					throw new Exception("格式不正确,解析表达式失败!");
				} catch (Exception e) {
					e.printStackTrace();
				}
			};
			//将字符串转化为集合
			ArrayList<String> list=getStringList(expr);
			//根据获得的集合转化为后序表达式集合
			ArrayList<String> postOrder = getPostOrder(list);
			Stack stack = new Stack();  
	        for (int i = 0; i < postOrder.size(); i++) {
	        	//如果为数字,则压入栈
	            if(Character.isDigit(postOrder.get(i).charAt(0))){  
	                stack.push(Float.parseFloat(postOrder.get(i)));  
	            }else{
	            	//否则,取出栈顶两个元素进行计算.
	                Float back = (Float)stack.pop();  
	                Float front = (Float)stack.pop();  
	                Float res = 0.0f;  
	                switch (postOrder.get(i).charAt(0)) {  
	                case '+':  
	                    res = front + back;  
	                    break;  
	                case '-':  
	                    res = front - back;  
	                    break;  
	                case '*':  
	                    res = front * back;  
	                    break;  
	                case '/':  
	                    res = front / back;  
	                    break;  
	                }  
	                //将结果再压回栈中
	                stack.push(res);  
	            }  
	        }
	        //最终计算结果出栈;
	        f = (Float)stack.pop();

		} else {
			//为空抛出异常
			try {
				throw new Exception("表达式内容为空,解析失败!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return f;
	}
	/**
	 * 将字符串转换为集合方法
	 * @param str
	 * @return
	 */
	private ArrayList<String> getStringList(String str) {
		ArrayList<String> result = new ArrayList<String>();
		String num = "";
		for (int i = 0; i < str.length(); i++) {
			//如果为数字,叠加到num字符串中
			if (Character.isDigit(str.charAt(i))) {
				num = num + str.charAt(i);
			} else {
				//如果num不为空,表示数字字符凑够了,加到集合里
				if (num != "") {
					result.add(num);
				}
				//然后再把非数字字符也加到集合中,清空num字符串
				result.add(str.charAt(i) + "");
				num = "";
			}
		}
		//最后判断下,num中还有值没,有的话加到集合里
		if (num != "") {
			result.add(num);
		}
		//返回结果
		return result;
	}
	
	/**
	 * 中序表达式转后序表达式
	 * @param list
	 * @return
	 */
	private ArrayList<String> getPostOrder(ArrayList<String> list) {

		ArrayList<String> result = new ArrayList<String>();
		Stack stack = new Stack();
		for (int i = 0; i < list.size(); i++) {
			//如果为数字,加到集合里
			if (Character.isDigit(list.get(i).charAt(0))) {
				result.add(list.get(i));
			} else {
				switch (list.get(i).charAt(0)) {
				//如果有左括号,先压入操作符栈中
				case '(':
					stack.push(list.get(i));
					break;
				//ok,等到右括号了
				case ')':
					//先看看操作符栈顶是不是左括号头头
					while (!stack.peek().equals("(")) {
						//不是左括号头头,就把操作符栈中的操作符弹出来一个,加到集合里,一直弹到见到左括号为止
						result.add((String) stack.pop());
					}
					//最后把左括号也弹出来,这样就只有加减乘除没有括号了
					stack.pop();
					break;
				default:
					//这里全是处理加减乘除的操作
					//如果操作符栈不为空,比较下当前操作符和操作符栈顶的操作符优先级大小
					while (!stack.isEmpty() && compare((String) stack.peek(), list.get(i))) {
						//如果栈顶操作符优先级大于当前,则栈中的操作符弹出加到集合里
						result.add((String) stack.pop());
					}
					//否则继续压到栈中,或者之前栈中元素已经弹出,再将优先级小的操作符加到操作符栈中.
					stack.push(list.get(i));
					break;
				}
			}
		}
		while (!stack.isEmpty()) {
			//最后看下操作符栈还有操作符没,有了加到集合末尾
			result.add((String) stack.pop());
		}
		return result;
	}
	/**
	 * 操作符优先级比较算法
	 * @param peek
	 * @param cur
	 * @return
	 */
	public static boolean compare(String peek, String cur) {
		//乘除优先级大于加减
		//如果操作符栈顶操作符的优先级大于当前操作符的优先级,则返回true
		if ("*".equals(peek) && ("/".equals(cur) || "*".equals(cur) || "+".equals(cur) || "-".equals(cur))) {
			return true;
		} else if ("/".equals(peek) && ("/".equals(cur) || "*".equals(cur) || "+".equals(cur) || "-".equals(cur))) {
			return true;
		} else if ("+".equals(peek) && ("+".equals(cur) || "-".equals(cur))) {
			return true;
		} else if ("-".equals(peek) && ("+".equals(cur) || "-".equals(cur))) {
			return true;
		}
		//如果当前操作符的优先级大于栈顶的操作符优先级,返回false
		return false;
	}

	public static void main(String[] args) {
		InfixExpr expr = new InfixExpr("3*20+13*5-40/2");
		float f = expr.evaluate();
		System.out.println(f);
	}
}
