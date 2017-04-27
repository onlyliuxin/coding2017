package stack.expr;

import java.util.Stack;
import stack.StackUtil;

public class InfixExprS{
	String expr = null;

	public InfixExprS(String expr) {
		this.expr = expr;
	}

	public float evaluate() {

		TParser tp = new TParser();
		tp.parse(expr);
		Stack<Integer> ints = new Stack<Integer>();
		Stack<String> signs = new Stack<String>();

		int i1 = tp.nextInt();
		String sign1 = tp.nextSign();
		
		ints.push(i1);
		signs.push(sign1);

		while (tp.hasNextInt()) {

			int i2 = tp.nextInt();
			String sign2 = tp.nextSign();

			if (tp.hasNextInt()) {
				
				if (highPrioritySign(sign1)) {
					
					i1 = ints.pop();
					sign1 = signs.pop();
					i2 = calculate(i1, i2, sign1);
			
				}
			
				ints.push(i2);
				signs.push(sign2);
				sign1 = sign2;

			}

		}

		signs.pop();
		StackUtil.reverse(ints);
		StackUtil.reverse(signs);

		while (!ints.isEmpty()) {
			
			int firstInt = ints.pop();

			if (ints.isEmpty()) {
				return (float) firstInt;
			} 
				
			int secInt = ints.pop();
			String sign = signs.pop();
			int result = calculate(firstInt, secInt, sign);
			ints.push(result);
		
		}

		System.out.println("we shall not reach here");
		return (float) ints.peek();
	}

	private int calculate(int firstInt, int secInt, String lowsign) {

		int result;
		if (lowsign.equals("+")) {
			result = firstInt + secInt;
		} else if (lowsign.equals("-")) {
			result = firstInt - secInt;
		} else if (lowsign.equals("*")) {
			result = firstInt * secInt;
		} else if (lowsign.equals("/")) {
			result = firstInt / secInt;
		} else {
			throw new RuntimeException(lowsign + " has not been supported yet!");
		}

		return result;

	}

	private boolean highPrioritySign(String sign) {

		if (sign.equals("*") || sign.equals("/")) {

			return true;

		}

		return false;
	}

}
