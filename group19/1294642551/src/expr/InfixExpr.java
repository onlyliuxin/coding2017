package expr;

import java.util.ArrayList;
import java.util.Stack;


/**
 * 中序表达式求值：
 * 	1. 只支持加减乘除，不支持括号
 * 	2. 表达式中只支持int，但结果可能是float
 * 	3. 要求用两个栈来实现
 * @author 12946
 *
 */
public class InfixExpr {
	String expr = null;
	
	
	public InfixExpr(String expr) {
		this.expr = expr;
	}

	public float evaluate() {
		Stack<String> operatorStack = new Stack<String>();
		Stack<Float> valueStack = new Stack<Float>();
		String[] arr = toArr(expr);
		inStack(operatorStack, valueStack, arr);
		outStack(operatorStack, valueStack);
		
		if(valueStack.size() == 1){
			return valueStack.peek();
		}else{
			throw new RuntimeException("数值栈中最后的元素个数不对");
		}
		
	}
	
	// 将表达式字符串分解成数值和操作符的数组
	public String[] toArr(String expr){
		ArrayList<String> al = new ArrayList<String>();
		for(int i = 0; i < expr.length(); i++){
			char c = expr.charAt(i);
			if(isOperator(c)){
				al.add(c+"");
			}
		}
		
		for(int i = 0; i < expr.length(); i++){
			char c = expr.charAt(i);
			if(isOperator(c)){
				expr = expr.replace(c, ',');
			}
		}
		String[] arr = expr.split(",");
		String[] totalArr = new String[2 * arr.length - 1];
		for(int i = 0; i < totalArr.length; i++){
			if(i % 2 == 0){
				totalArr[i] = arr[i/2];
			}else{
				totalArr[i] = al.get((i-1)/2);
			}
		}
		
		return totalArr;
	}

	// 表达式入栈操作
	public void inStack(Stack<String> operatorStack, Stack<Float> valueStack,
			String[] arr) {
		for(int i = 0; i < arr.length; i++){
			if(isOperator(arr[i])){//新元素是操作符
				if(operatorStack.size() == 0){//第一个操作符
					operatorStack.push(arr[i]+"");
				}else if(operatorStack.size() > 0){
					while(priority(operatorStack.peek(), arr[i]+"") != -1){//第一个操作符的优先级>=第二个的
						popCompute(operatorStack, valueStack);
						if(operatorStack.isEmpty()){
							break;
						}
					}
					operatorStack.push(arr[i]+"");//操作符入栈
				}
				
			}else{// 新元素是数值
				float value = Float.valueOf(arr[i]+"");
				valueStack.push(value);
			}
			
		}
	}
	
	// 出栈操作，将两个栈中的操作符和数值进行计算，当操作符栈为空时，数值栈的值就是最后的值
	public void outStack(Stack<String> operatorStack, Stack<Float> valueStack){
		while(!operatorStack.isEmpty()){
			popCompute(operatorStack, valueStack);
		}
	}
	
	public boolean isOperator(String c){
		if(c.equals("+") || c.equals("-") || c.equals("*") || c.equals("/")){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isOperator(char c){
		if(c=='+' || c=='-' || c=='*' || c=='/'){
			return true;
		}else{
			return false;
		}
	}
	
	// 第一个操作符的优先级 > 第二个操作符的优先级: 返回 1
	// 第一个操作符的优先级 = 第二个操作符的优先级: 返回 0
	// 第一个操作符的优先级 < 第二个操作符的优先级: 返回 -1
	public int priority(String c1, String c2){
		
		if(c1.equals("+") || c1.equals("-")){
			if(c2.equals("*") || c2.equals("/")){
				return -1;
			}else{
				return 0;
			}
		}else{//(c1=='*' || c1=='/')
			if(c2.equals("+") || c2.equals("-")){
				return 1;
			}else{
				return 0;
			}
		}
		
	}
	

	
	// 计算加减乘除的结果
	public float compute(float num1, float num2, String operator){
		if(operator.equals("+")){
			return num1 + num2;
		}else if(operator.equals("-")){
			return num1 - num2;
		}else if(operator.equals("*")){
			return num1 * num2;
		}else{
			return num1 / num2;
		}
	}
	
	// 弹栈计算，取一个操作符和两个操作数进行计算，并将得到的操作数压入数值栈
	public void popCompute(Stack<String> operatorStack, Stack<Float> valueStack){
		float num2 = valueStack.pop();
		float num1 = valueStack.pop();
		String operator = operatorStack.pop();
		float value = compute(num1, num2, operator);
		valueStack.push(value);

	}
	
	
}
