package com.coding.basic.stack.expr;

import java.util.List;
import java.util.Stack;

public class InfixExpr {
	String expr = null;
	
	public InfixExpr(String expr) {
		this.expr = expr;
	}

	public float evaluate() {
//		if(expr == null || "".equals(expr)){
//			return 0;
//		}
//		Stack numStack = new Stack();
//		Stack opStack = new Stack();
//		String[] ss = expr.split("");
//		boolean needOper = false;// 需要运算，遇到* / 后置为true
//		boolean foundOper = true;// 遇到运算符
//		String ops = "+-*/";
//		for (int i=0;i<ss.length;i++) {
//			String s = ss[i];
//			if(ops.indexOf(s) != -1){
//				if(needOper){
//					doMulOrDivOp(numStack, (String)opStack.pop());
//					needOper = false;
//				}
//				opStack.push(s);
//				foundOper = true;
//				if("+-".indexOf(s) != -1){
//					
//				}else{
//					needOper = true;
//				}
//			}else if(!foundOper){// 未找到运算符前，字符串一直追加
//				String tmp = "";
//				Object obj = numStack.pop();
//				if(obj != null){
//					tmp = (String)obj;
//				}
//				numStack.push(tmp + s);
//			}else if("1234567890".indexOf(s) != -1){
//				numStack.push(s);
//				foundOper = false;
//			}else{
//				throw new RuntimeException("InfixExpr not support " + s + " !");
//			}
//			if((i == ss.length - 1) && needOper){
//				doMulOrDivOp(numStack, (String)opStack.pop());
//			}
//		}
//		if(numStack.size() > 1){
//			doAddOrSubOps(numStack, opStack);
//		}
//		return new Float((String)numStack.pop());
		TokenParser parser = new TokenParser();
		List<Token> tokens = parser.parse(this.expr);
		
		
		Stack<Token> opStack = new Stack<>();
		Stack<Float> numStack = new Stack<>();
		
		for(Token token : tokens){
			
			if (token.isOperator()){
				
				if(opStack.isEmpty()){
					
					opStack.push(token);
				} else{
					
					while(!opStack.isEmpty() 
							&& !token.hasHigherPriority(opStack.peek())){
						Token prevOperator = opStack.pop();
						Float f2 = numStack.pop();
						Float f1 = numStack.pop();
						Float result = calculate(prevOperator.toString(), f1,f2);
						numStack.push(result);						
						
					}
					opStack.push(token);
				}
			} 
			if(token.isNumber()){
				numStack.push(new Float(token.getIntValue()));
			}
		}
		
		while(!opStack.isEmpty()){
			Token token = opStack.pop();
			Float f2 = numStack.pop();
			Float f1 = numStack.pop();
			numStack.push(calculate(token.toString(), f1,f2));
		}
		
		
		return numStack.pop().floatValue();
	}
	
	/**
	 * 多个加减
	 * @param numStack
	 * @param opStack
	 */
	private void doAddOrSubOps(Stack numStack, Stack opStack) {
		Stack calStack = new Stack();
		while(numStack.size() > 1){
			calStack.push(numStack.pop());
			calStack.push(opStack.pop());
		}
		float num1 = new Float((String)numStack.pop());
		float num2;
		while(calStack.size() > 0){
			String op = (String)calStack.pop();
			num2 = new Float((String)calStack.pop());
			if("+".equals(op)){
				num1 += num2;
			}else{
				num1 -= num2;
			}
		}
		numStack.push(num1 + "");
	}

	/**
	 * 单个乘除
	 * @param numStack
	 * @param op
	 */
	private void doMulOrDivOp(Stack numStack, String op){
		float num2 = new Float((String)numStack.pop());
		float num1 = new Float((String)numStack.pop());
		String result = null;
		if("*".equals(op)){
			result = (num1 * num2) + "";
		}else{
			result = (num1 / num2) + "";
		}
		numStack.push(result);
	}
	
	private Float calculate(String op, Float f1, Float f2){
		if(op.equals("+")){
			return f1+f2;
		}
		if(op.equals("-")){
			return f1-f2;
		}
		if(op.equals("*")){
			return f1*f2;
		}
		if(op.equals("/")){
			return f1/f2;
		}
		throw new RuntimeException(op + " is not supported");
	}
}