package code06;

import code05.Stack;

import java.util.ArrayList;
import java.util.List;

public class InfixExpr {
	String expr = null;
	
	public InfixExpr(String expr) {
		this.expr = expr;
	}

	private float calc(float a, float b, String ops){
		float result = 0.0f;
		if (ops.equals("+")) {
			return b + a;
		}
		if (ops.equals("-")) {
			return b - a;
		}
		if (ops.equals("*")) {
			return b * a;
		}
		if (ops.equals("/")) {
			return b / a;
		}
		return result;
	}

	private boolean isOperator(String ch){
		boolean isOperator = (ch.equals("+") || ch.equals("-") || ch.equals("*") || ch.equals("/"));
		return isOperator;
	}

	//parse string to list
	private List parser(){
		char[] chs = expr.toCharArray();
		List values = new ArrayList<String>();
		int currentCharPos = 0; // 当前字符的位置
		while (currentCharPos < chs.length) {
			String currentStr = String.valueOf(chs[currentCharPos]);
			if(isOperator(currentStr)){
				values.add(currentStr);
				currentCharPos ++;
			}
			else {
				int numberOffset  =  0;
				while (currentCharPos + numberOffset < chs.length && !isOperator(String.valueOf(chs[currentCharPos+numberOffset]))){
					numberOffset ++ ;
				}
				if(numberOffset == 1){
					values.add(currentStr);
					currentCharPos++;
				}else {
					String number = new String(chs,currentCharPos,numberOffset);
					values.add(number);
					currentCharPos = currentCharPos + numberOffset;
				}
			}
		}
		return values;
	}

	private int morePriority(String peek, String e) {
		boolean hasMorePriority = ((e.equals("+") || e.equals("-")) && (peek.equals("*") || peek.equals("/")));
		boolean hasLessPriority = ((e.equals("*") || e.equals("/")) && (peek.equals("+") || peek.equals("-")));
		if(hasMorePriority) {
			return 1;
		}
		if(hasLessPriority){
			return -1;
		}
		return 0;
	}

	public float evaluate() {
		float result = 0.0f;

		Stack numberStack = new Stack();
		Stack opsStack = new Stack();

		List<String> elements = this.parser();
		for(String e : elements){
			if(!isOperator(e)){ //数字直接入栈
				float number = Float.valueOf(e);
				numberStack.push(number);
			}else {//操作符
				if(opsStack.isEmpty()){
					opsStack.push(e);
				}else {
					//栈顶符号有着相等或者更高的优先级
					if(morePriority((String) opsStack.peek(),e) >= 0){
						float a = (Float) numberStack.pop();
						float b = (Float) numberStack.pop();
						String ops = (String) opsStack.pop();

						float value = calc(a,b,ops);

						numberStack.push(value);
						opsStack.push(e);
					}else {
						opsStack.push(e);
					}
				}
			}
		}
		while (!opsStack.isEmpty()) {
			float a = (Float) numberStack.pop();
			float b = (Float) numberStack.pop();
			String ops = (String) opsStack.pop();
			float value = calc(a, b, ops);
			numberStack.push(value);
		}

		result = (Float) numberStack.pop();
		return result;
	}

	public static void main(String[] args) {
		InfixExpr expr = new InfixExpr("20+30.8*400/500");
		List<String> elements = expr.parser();
		System.out.println(expr.evaluate());
	}
}
