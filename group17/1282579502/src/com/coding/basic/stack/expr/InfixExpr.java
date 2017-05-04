package com.coding.basic.stack.expr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import javafx.util.converter.NumberStringConverter;

public class InfixExpr {
	String expr = null;
	Map<Character, Integer> priorityMap = null;
	List<String> exprTokens = null;
	public InfixExpr(String expr) {
		priorityMap = new HashMap<Character, Integer>();
		init();
		this.expr = expr;
		exprTokens = parse();
	}
	
	public void init(){
		priorityMap.put('+', 0);
		priorityMap.put('-', 0);
		priorityMap.put('*', 1);
		priorityMap.put('/', 1);
	}

	public float evaluate() {		
		Stack<Character> operatorStack = new Stack<>();
		Stack<Float> operandStack = new Stack<>();
		
		Float operandOne, operandTwo;
		
		int index = 0;
		
		while(index < exprTokens.size()){
			String cc = exprTokens.get(index);
			try{
				if(isFloat(cc)){
					System.out.println("push operand: " + cc);
					operandStack.push(Float.parseFloat(cc));
					System.out.println("peek: " + operandStack.peek());
					
				}
				else if(priorityMap.get(cc.charAt(0)) != null){
					char c = cc.charAt(0);
					if(operatorStack.isEmpty()){
						System.out.println("push operator: " + cc);
						operatorStack.push(c);
					}
					else{
						int preOperatorPriority = getPriority(operatorStack.peek());
						int curOperatorPriority = getPriority(c);
						if(curOperatorPriority <= preOperatorPriority){
							//do precalculation first
							operandTwo = operandStack.pop();
							operandOne = operandStack.pop();
							char operator = operatorStack.pop();
							float result = doCalculation(operandOne, operandTwo, operator);
							
							operandStack.push(result);
							operatorStack.push(c);
						}
						else{
							System.out.println("push operator: " + cc);
							operatorStack.push(c);
						}
					}
				}
				else{
					throw new Exception("Unsupported character: " + cc);
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}
			index ++;
		}
		System.out.println("dumpping operator stack:");
		dumpStack(operatorStack);
		System.out.println("dumpping operand stack:");
		dumpStack(operandStack);
		try{
			while(!operatorStack.isEmpty()){
				operandTwo = operandStack.pop();
				operandOne = operandStack.pop();
				char operator = operatorStack.pop();
				float result = doCalculation(operandOne, operandTwo, operator);
				
				operandStack.push(result);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(operandStack.size() > 1){
			System.err.println("More than one result reminded in operands stack.");
		}
		return operandStack.pop();
	}
	
	private float doCalculation(float operandOne, float operandTwo, char operator) throws Exception{
		System.out.println("operand 1: " + operandOne + " operand 2: " + operandTwo + " operator: " + operator);
		float result = 0f;
		if(operator == '+'){
			result = operandOne + operandTwo;
		}
		else if(operator == '-'){
			result =  operandOne - operandTwo;
		}
		else if(operator == '*'){
			result =  operandOne * operandTwo;
		}
		else if(operator == '/'){
			result =  operandOne / operandTwo;
		}
		else{
			throw new Exception("Unsupported operator");
		}
		System.out.println("result: " + result);
		return result;
	}
	
	private int getPriority(Character c){
		return priorityMap.get(c);
	}
	
	private boolean isFloat(String v){
		try{
			Float.parseFloat(v);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}

	private List<String> parse(){
		List<String> vals = new ArrayList<>();
		int p1 = 0;
		int p2 = 1;
		while(p2<expr.length()){
			char c1 = expr.charAt(p1);
			char c2 = expr.charAt(p2);
			if(! Character.isDigit(c2)){
				//current is operator
				vals.add(expr.substring(p1,p2));
				vals.add(String.valueOf(c2));
			
				p2++;
				p1 = p2;
				
			}
			else{
				p2++;
			}
		}
		
		if(p1<expr.length()){
			//System.out.println("p1 = " + p1 + " char = " + expr.charAt(p1));
			vals.add(expr.substring(p1));
		}
		dumpArray(vals);
		return vals;
	}
	
	private void dumpArray(List<String> l){
		for(int i =0; i<l.size(); i++){
			System.out.print(l.get(i) + " ");
		}
		System.out.println();
	}
	
	private void dumpStack(Stack s){
		Iterator itr = s.iterator();
		while(itr.hasNext()){
			System.out.print(itr.next()+" ");
		}
		System.out.println();
	}
	
}
