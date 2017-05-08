package com.github.ipk2015.coding2017.basic.stack.expr;

import com.github.ipk2015.coding2017.basic.stack.Stack;

public class InfixExpr {
	String expr = null;
	
	public InfixExpr(String expr) {
		this.expr = expr;
	}

	public float evaluate() {		
		String[] elements = getElementArray(expr);
		
		Stack numStack = new Stack();
		Stack operStack = new Stack();
		
		manageMultiAndDivOper(elements,numStack,operStack);

		return manageAddAndMinusOper(numStack,operStack);
	}
	
	private void manageMultiAndDivOper(String[] elements,Stack numStack,Stack operStack){	
		float preElement,nextElement;
		for(int i = 0; i < elements.length;i++){
			if(i%2 == 0){
				numStack.push(Float.valueOf(elements[i]));
			}else{
				
				if(elements[i].equals("+") || elements[i].equals("-")){
					operStack.push(elements[i]);
				}else{
					preElement = (Float)numStack.pop();
					i++;
					nextElement = Float.valueOf(elements[i]);
					numStack.push(doBaseOper(preElement,nextElement,elements[i-1]));
				}
			}
		}
	}
	
	private float manageAddAndMinusOper(Stack numStack,Stack operStack){
		float result = 0f;;
		while(!operStack.isEmpty()){
			result = doBaseOper(result,(Float)numStack.pop(),(String)operStack.pop());
		}
		result += (Float)numStack.pop();
		return result;
	}
	
	private float doBaseOper(float preData,float nextData,String oper){
		switch(oper){
			case "+":
				return preData+nextData;
			case "-":
				return preData-nextData;
			case "*":
				return preData*nextData;
			case "/":
				return preData/nextData;
			default:
				throw new RuntimeException("could not recognise oper:"+oper);
		}
	}
	
	public String[] getElementArray(String expression){
		char[] charArray = expression.toCharArray();
		StringBuffer stringBuffer = new StringBuffer();
		
		for(int i = 0;i<charArray.length;i++){
			if(charArray[i] == '+' || charArray[i] == '-' || charArray[i] == '*' || charArray[i] == '/'){
				stringBuffer.append(","+charArray[i]+",");
			}else{
				stringBuffer.append(charArray[i]);
			}
		}
		
		return stringBuffer.toString().split(",");
	}
	
	
	
}
