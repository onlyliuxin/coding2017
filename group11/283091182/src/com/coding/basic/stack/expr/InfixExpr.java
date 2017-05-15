package com.coding.basic.stack.expr;

import com.coding.basic.stack.Stack;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;

public class InfixExpr {
	String expr = null;
	Stack numStack = new Stack();
	Stack opStack = new Stack();
	private static final String REGEX_OPR = "[\\+\\-\\*\\/]";

	public InfixExpr(String expr) {
		this.expr = expr;
	}

	public float evaluate() {
		
		processNumAndOperatorWithPriority();
		
		while(!opStack.isEmpty()){
			char op = (char)opStack.pop();
			float num2 = (float)numStack.pop();
			float num1 = (float)numStack.pop();
			numStack.push(calculate(num1, op, num2));
		}
		
		return (float)numStack.pop();
	}

	private void processNumAndOperatorWithPriority() {
		
		String[] numStrArr = this.getNumbers();
		char[] operators = this.getOperators();
		
		if(numStrArr.length!=operators.length+1){
			throw new RuntimeException("Invalid Expression:"+this.expr);
		}
		
		this.numStack.push(Float.parseFloat(numStrArr[0]));
		this.numStack.push(Float.parseFloat(numStrArr[1]));
		this.opStack.push(operators[0]);
		for(int i=2;i<numStrArr.length;i++){
			char op = operators[i-1];
			while(!this.opStack.isEmpty() && isLowerPriority(op)){
				float num2 = (float)this.numStack.pop();
				float num1 = (float)this.numStack.pop();
				char prevOp = (char)this.opStack.pop();
				this.numStack.push(this.calculate(num1, prevOp, num2));
			}
			this.opStack.push(op);
			this.numStack.push(Float.parseFloat(numStrArr[i]));
		}
	}
	
	private char[] getOperators(){
		char[] charArr= this.expr.toCharArray();
		char[] result = new char[charArr.length];

		int count = 0;
		for(char tmp:charArr){
			if(tmp=='+'||tmp=='-'||tmp=='*'||tmp=='/'){
				result[count]=tmp;
				count++;
			}else if(!Character.isDigit(tmp)){
				throw new RuntimeException("Unsupported Operator:"+tmp);
			}
		}
		return Arrays.copyOf(result, count);
	}
	private String[] getNumbers(){
		return this.expr.split(REGEX_OPR);
	}
	
	private boolean isLowerPriority(char opr){
		char prevOp = (char)this.opStack.peek();
		boolean result = false;
		if((prevOp=='*'||prevOp=='/')){
			result =  true;
		}else if(opr=='+'||opr=='-'){
			result =  true;
		}
		return result;
	}
	
	private float calculate(float num1,char operator, float num2){
		if(operator=='+'){
			return num1+num2;
		}else if(operator=='-'){
			return num1-num2;
		}else if(operator=='*'){
			return num1*num2;
		}else{
			return num1/num2;
		}
	}
	
}
