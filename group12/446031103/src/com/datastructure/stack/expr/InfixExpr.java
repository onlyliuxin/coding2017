package com.datastructure.stack.expr;

import java.util.Objects;
import java.util.Stack;
import java.lang.String;
public class InfixExpr {
	String expr = null;
	
	public InfixExpr(String expr) {
		this.expr = expr;
	}

	public float evaluate() {	
		Stack<Character> sign = new Stack<Character>();
		Stack<Float> number = new Stack<Float>();
		int strCnt = 0;
		int numberLen = 0;
		String[] String = expr.split("[^\\d]");
		if(expr!=null){
			for (int i = 0,strLen = expr.length(); i < strLen; i++) {
				char c=expr.charAt(i);
				if(Objects.equals(c, '+')||Objects.equals(c, '-')){
					sign.push(c);
				}else if(Objects.equals(c, '*')||Objects.equals(c, '/')){
					float number1=number.pop();
					float number2=Float.parseFloat(String[strCnt]);
					float result=calculate(number1,number2,c);
					numberLen = String[strCnt].length();
					number.push(result);
					i += numberLen;
					strCnt++;
				}else{
					number.push(Float.parseFloat(String[strCnt]));
					numberLen = String[strCnt].length();
					strCnt++;
					i += (numberLen-1);
				}
			}
			for (int i = 0,signS = sign.size(); i < signS; i++) {
				float number1 = number.pop();
				float number2 =number.pop();
				char c = sign.pop();
				float result=calculate(number2,number1,c);
				number.push(result);
			}
		}
		return number.pop();
	}
	
	private float calculate(float number1,float number2,char c){
		float result = 0;
		if(Objects.equals(c, '+')){
			result = number1 + number2;
		}else if(Objects.equals(c, '-')){
			result = number1 - number2;
		}else if(Objects.equals(c, '*')){
			result = number1 * number2;
		}else if(Objects.equals(c, '/')){
			result = number1 / number2;
		}
		return result;		
	}
	

	
	
}
