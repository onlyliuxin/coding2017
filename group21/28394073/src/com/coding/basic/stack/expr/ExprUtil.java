package com.coding.basic.stack.expr;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExprUtil {
	public static List<String> parseExpr(String expr){
		List<String> list = new ArrayList<String>();
		Pattern pa = Pattern.compile("[0-9]+|\\+|\\-|\\*|\\/");
		Matcher ma = pa.matcher(expr);
		while(ma.find()){
			String s = ma.group();
			list.add(s);
		}
		return list;
	}
	
	public static boolean isNum(String num){
		Pattern pattern = Pattern.compile("[0-9]+");
		Matcher matcher = pattern.matcher(num);
		if(matcher.matches()){
			return true;
		}
		return false;
	}
	
	public static boolean isOperation(String oper){
		Pattern pattern = Pattern.compile("[\\+|\\-|\\*|\\/]");
		Matcher matcher = pattern.matcher(oper);
		if(matcher.matches()){
			return true;
		}
		return false;
	}
	
	public static float Calculate(Float f1,Float f2,String operation){
		float result=0;
//		float top = numStack.pop();
//		float secondTop = numStack.pop();
//		String operation = operStack.pop();
		switch(operation){
		case "+": 
			result = f1 + f2;
			break;
		case "-":
			result = f1 - f2;
			break;
		case "*":
			result = f1 * f2;
			break;
		case "/":
			result = f1 / f2;
			break;
		}
//		numStack.push(result);
		return result;
	}
}
