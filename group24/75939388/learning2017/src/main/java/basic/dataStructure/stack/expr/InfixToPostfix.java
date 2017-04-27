package basic.dataStructure.stack.expr;

import java.util.List;

/**
 * 中序转后序
 */
public class InfixToPostfix {
	
	public static List<Token> convert(String expr) {
		//括号问题
		if(!(has(expr, "(") && has(expr, ")") && hasPair(expr, "(", ")"))){
			throw new RuntimeException("brackets are not in pair");
		}

		return null;
	}

	private static boolean hasPair(String expr, String sym1, String sym2){
		int leftBracketIndex = expr.indexOf(sym1);
		int rightBracketIndex = expr.indexOf(sym2);
		String subStr = expr.substring(leftBracketIndex, rightBracketIndex + 1);

	}

	private static boolean has(String expr, String sym){
		return expr.indexOf(sym) > 0;
	}

	public static void main(String[] args){
		convert("(2+3)*2");
	}

}
