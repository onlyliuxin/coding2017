package dataStructure_6InfixExpr;

import java.util.Stack;

public class InfixExpr {
	
	String expr = null;
	
	public InfixExpr(String expr){
		this.expr = expr;
	}
	
	public double evaluate(){
		
		Stack operatorStack = new Stack();
		Stack<Double> operandStack = new Stack<Double>();
		
		int tag = -1;
		for(int i = 0; i < expr.length(); i++){
			if(operatorStack.isEmpty()){
				tag = -1;
			}
			char c = expr.charAt(i);
			if( tag == 1  && (c == '+' || c == '-' || c == '*' || c == '/')){
				System.out.println("i= " + i);
				char down = (char) operatorStack.pop();
				System.out.println("down: " + down);
				System.out.println("up: " + c);
				if(judgePriority(down,c)){
					double operand = (double) operandStack.pop();
					double operanded = (double) operandStack.pop();
					operandStack.push(operator(down,operanded,operand));
					operatorStack.push(c);
				}else{
					operatorStack.push(down);
					operatorStack.push(c);
				}
			}else if(tag == -1 && (c == '+' || c == '-' || c == '*' || c == '/')){
				tag = 1;
				operatorStack.push(c);
				
			}else{
				String number = extractNumber(i,expr);
				int length = number.length();
				i += length-1;
				double operand = Double.parseDouble(number);
				operandStack.push(operand);
			}
		}
		
		while(!operatorStack.isEmpty()){
			char operator = (char) operatorStack.pop();
			System.out.println(operator);
			double operand = (double) operandStack.pop();
			System.out.println(operand);
			double operanded = (double) operandStack.pop();
			System.out.println(operanded);
			operandStack.push( operator(operator,operanded,operand));
		}
		
		return (double) operandStack.pop();
	}

	private String extractNumber(int i, String expr2) {
		
		StringBuffer buffer = new StringBuffer();
		while( (expr.charAt(i) != '+') && (expr.charAt(i) != '-') && (expr.charAt(i) != '*') && (expr.charAt(i) != '/') ){
			buffer.append(expr.charAt(i));
			if(i >= expr2.length()-1){
				break;
			}
			i++;
		}
		return buffer.toString();
	}

	private boolean judgePriority(char down, char up) {
		boolean tag = false;
		
		if((up == '+' || up == '-') && (down == '*' || down == '/')){
			tag = true;
		}else if( (up == '*') && (down == '/')){
			tag = true;
		}else if( (up == '/') && (down == '*')){
			tag = true;
		}else if( (up == '+') && (down == '-') ){
			tag = true;
		}else if( (up == '-') && (down == '+') ){
			tag = true;
		}
		return tag;
	}
	
	private double operator(char operator, double operanded, double operand) {
		double result = 0;
		
		switch(operator){
		 	case '+': result = operanded + operand; break;
		 	case '-': result = operanded - operand; break;
		 	case '*': System.out.println("³Ë·¨"); result = operanded * operand; break;
		 	case '/': result = operanded / operand; break;
		}
		
		return result;
	}
}
