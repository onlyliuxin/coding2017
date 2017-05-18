package com.ecust.jvm4;

import java.util.List;

import java.util.Stack;



/**

 * 与前缀表达式类似，只是顺序是从左至右：

 从左至右扫描表达式，遇到数字时，将数字压入堆栈，遇到运算符时，弹出栈顶的两个数，用运算符对它们做相应的计算（次顶元素 op 栈顶元素），并将结果入栈；重复上述过程直到表达式最右端，最后运算得出的值即为表达式的结果。

 例如后缀表达式“3 4 + 5 × 6 -”：

 (1) 从左至右扫描，将3和4压入堆栈；

 (2) 遇到+运算符，因此弹出4和3（4为栈顶元素，3为次顶元素，注意与前缀表达式做比较），计算出3+4的值，得7，再将7入栈；

 (3) 将5入栈；

 (4) 接下来是×运算符，因此弹出5和7，计算出7×5=35，将35入栈；

 (5) 将6入栈；

 (6) 最后是-运算符，计算出35-6的值，即29，由此得出最终结果。

 */

public class PostfixExpr {

String expr = null;

	

	public PostfixExpr(String expr) {

		this.expr = expr;

	}



	public float evaluate() {

		TokenParser parser = new TokenParser();

		List<Token> tokens = parser.parse(this.expr);

		

		

		Stack<Float> numStack = new Stack<>();

		for(Token token : tokens){

			if(token.isNumber()){

				numStack.push(new Float(token.getIntValue()));

			} else{

				Float f2 = numStack.pop();

				Float f1 = numStack.pop();

				numStack.push(calculate(token.toString(),f1,f2));

			}

		}

		return numStack.pop().floatValue();

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
