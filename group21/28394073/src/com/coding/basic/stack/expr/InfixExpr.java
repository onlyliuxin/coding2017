package com.coding.basic.stack.expr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InfixExpr {
	String expr = null;
	
	Stack<Float> numStack = new Stack<Float>();
	Stack<String> operStack = new Stack<String>();

	
	public InfixExpr(String expr) {
		this.expr = expr;
	}

	public float evaluate() {	
		
		String[] numbers = expr.split("[+|\\-|*|/]");
		
		ArrayList<String> operations = new ArrayList<String>();
		Pattern pa = Pattern.compile("(?<=\\d)[\\+|\\-|\\*|\\/](?=\\d)");
		Matcher ma = pa.matcher(expr);
		while(ma.find()){
			String s = ma.group();
			System.out.println(s);
			operations.add(s);
		}
		
		ArrayList<String> list = new ArrayList<String>();
		for(int i=0;i<numbers.length;i++){
			list.add(numbers[i]);
			if(i!=numbers.length-1){
				list.add(operations.get(i));
			}
		}

		for(String item:list){
//			char item = expr.charAt(i);//一个字符一个字符的取，如果遇到双位数字就会出错；
			
			if(this.isNum(item)){
				numStack.push(Float.parseFloat(String.valueOf(item)));
			}
			else if(this.isOperation(item)){
				if(this.isHighPriority(item)){//判断栈顶和当前的优先级谁高，当前栈顶高
					popAndCalculate();
					operStack.push(item); //push即将入栈的运算符
				}
				else{//如果即将入栈的高，则入栈
					operStack.push(item);
				}
			}
			else{
				new RuntimeException("符号" + item + "还没有实现！！");
			}
		}
		while(!operStack.isEmpty()){//operStack不为空
			popAndCalculate();
		}
		return numStack.pop();
	}
	
	public boolean isNum(String num){
		Pattern pattern = Pattern.compile("[0-9]+");
		Matcher matcher = pattern.matcher(num);
		if(matcher.matches()){
			return true;
		}
		return false;
	}
	
	public boolean isOperation(String oper){
		Pattern pattern = Pattern.compile("[\\+|\\-|\\*|\\/]");
		Matcher matcher = pattern.matcher(oper);
		if(matcher.matches()){
			return true;
		}
		return false;
	}
	
	public boolean isHighPriority(String c){
		if(operStack.isEmpty()){
			return false;
		}
		else{
			String top = operStack.peek();//InvocationError here,so adding above if logic
			
			Pattern pattern1 = Pattern.compile("[*|/]");
			Matcher matcher1 = pattern1.matcher(top);
			Pattern pattern2 = Pattern.compile("[\\+|\\-|\\*|\\/]");
			Matcher matcher2 = pattern2.matcher(c);
			Pattern pattern3 = Pattern.compile("[\\-]");
			Matcher matcher3 = pattern3.matcher(top);
			Pattern pattern4 = Pattern.compile("[\\-|\\+]");
			Matcher matcher4 = pattern4.matcher(c);
			
			if(matcher1.matches() && matcher2.matches() || matcher3.matches() && matcher4.matches()){
				return true;
			}else
				return false;
		}
	}
	
	public float popAndCalculate(){
		float result=0;
		float top = numStack.pop();
		float secondTop = numStack.pop();
		String operation = operStack.pop();
		switch(operation){
		case "+": 
			result = top + secondTop;
			break;
		case "-":
			result = secondTop - top;
			break;
		case "*":
			result = top * secondTop;
			break;
		case "/":
			result = secondTop / top;
			break;
		}
		numStack.push(result);
		return result;
	}
	
}
