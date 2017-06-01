package com.coding.basic.expr;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;



public class InfixExpr {
	String expr = null;
	/*
	private static List<String[]> signs = new ArrayList<String[]>();
	
	static{
		signs.add(new String[]{"(",")","[","]"});
		signs.add(new String[]{"++","--"});
		signs.add(new String[]{"*","/","%"});
		signs.add(new String[]{"+","-"});
	}
	
	private int getPriority(String sign){
		for (int i = 0; i < signs.size(); i++) {
			String[] strs = signs.get(i);
			for (String str : strs) {
				if(str.equals(sign)){
					return i;
				}
			}
		}
		return -1;
	}*/
	
	public InfixExpr(String expr) {
		this.expr = expr;
	}

	
	//只支持简单的算式   第一位字符不允许为符号   300*20+12*5-20/4
	public float evaluate() {		
		//com.coding.basic.LinkedList result = new LinkedList();
		//com.coding.basic.LinkedList signs = new LinkedList();
		LinkedList<Float> result = new LinkedList<Float>();
		LinkedList<Character> signs = new LinkedList<Character>();
		getNumberAndSign(result, signs);
		evaluateMultiplyDivide(result, signs);
		evaluateAddSubtract(result,signs);
		return result.poll().floatValue();
	}


	private void evaluateAddSubtract(LinkedList<Float> result,
			LinkedList<Character> signs) {
		while(!signs.isEmpty()){
			float num1 = result.pollFirst();
			float num2 = result.pollFirst();
			char sign = signs.pollFirst();
			float temp = 0.0f;
			if(sign=='+'){
				temp = num1+num2;
			}else if(sign=='-'){
				temp = num1-num2;
			}else{
				throw new ArithmeticException();
			}
			result.addFirst(temp);
		}
	}


	private void evaluateMultiplyDivide(LinkedList<Float> result, LinkedList<Character> signs) {
		int index = 0;
		while((index=getMultiplyDivideSign(signs))!=-1){
			float num1 = result.get(index);
			float num2 = result.get(index+1);
			char sign = signs.get(index);
			float temp = 0.0f;
			if(sign=='*'){
				temp = num1*num2;
			}else if(sign=='/'){
				temp = num1/num2;
			}else{
				throw new ArithmeticException();
			}
			result.remove(index);
			result.remove(index);
			signs.remove(index);
			result.add(index, temp);
		}
	}

	private int getMultiplyDivideSign(LinkedList<Character> signs) {
		for (int i = 0; i < signs.size(); i++) {
			char sign = signs.get(i);
			if(sign=='*'||sign=='/'){
				return i;
			}
		}
		return -1;
	}


	//第一位字符不允许为符号   300*20+12*5-20/4
	private void getNumberAndSign(LinkedList<Float> result,
			LinkedList<Character> signs) {
		String number= "";
		for (char c : expr.toCharArray()) {
			if(c==' '){
				continue;
			}
			if(isSign(c)){
				if("".equals(number)){ //符号打头
					if(c=='-'){
						number = "-";
					}else if(c=='+'){
						number = "";
					}else{
						throw new ArithmeticException();
					}
				}else{
					signs.add(c);
					result.add(Float.parseFloat(number));
					number = "";
				}
			}else{
				number = number + c;
			}
		}
		result.add(Float.parseFloat(number));
	}
	
	private boolean isSign(char c){
		if(c=='+'||c=='-'||c=='*'||c=='/'){
			return true;
		}else{
			return false;
		}
	}
	
}
