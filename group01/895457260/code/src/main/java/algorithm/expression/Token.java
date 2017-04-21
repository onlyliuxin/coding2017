package algorithm.expression;

import java.util.Arrays;
import java.util.List;

class Token {
	public static final String ADD = "+";
	public static final String SUB = "-";
	public static final String MUL = "*";
	public static final String DIV = "/";
	public static final String L_BRACKET = "(";
	public static final String R_BRACKET = ")";

	public static final List<String> OPERATORS = Arrays.asList(ADD, SUB, MUL, DIV, L_BRACKET, R_BRACKET);
	public static final int OPERATOR = 1;
	public static final int NUMBER = 2;

	public static final String SCOPE_STR = "#";
	public static final Token SCOPE = new Token(OPERATOR, SCOPE_STR);

	//优先级表
	private static int[][] priorities = {
			//    +   -   *   /   (   )   #
			{-1, -1, -1, -1,  1, -1,  1}, //  +
			{-1, -1, -1, -1,  1, -1,  1}, //  -
			{ 1,  1, -1, -1,  1, -1,  1}, //  *
			{ 1,  1, -1, -1,  1, -1,  1}, //  /
			{ 1,  1,  1,  1,  1, -1,  1}, //  (
			{-1, -1, -1, -1, -1,  0,  1}, //  )
			{-1, -1, -1, -1, -1, -1,  0}  //  #
	};

	String value;
	int type;

	public Token(int type, String value){
		this.type = type;
		this.value = value;
	}

	public boolean isNumber() {
		return type == NUMBER;
	}

	public boolean isOperator() {
		return type == OPERATOR;
	}

	public float getFloatValue() {
		return Float.valueOf(value);
	}

	public String toString(){
		return value;
	}

	private static int indexOf(String op) {
		switch (op) {
			case ADD:
				return 0;
			case SUB:
				return 1;
			case MUL:
				return 2;
			case DIV:
				return 3;
			case L_BRACKET:
				return 4;
			case R_BRACKET:
				return 5;
			case SCOPE_STR:
				return 6;
		}
		return 0;
	}

	public static int compare(Token op, Token peek) {
		int opIndex = indexOf(op.toString());
		int peekIndex = indexOf(peek.toString());
		return priorities[opIndex][peekIndex];
	}

	public static Token calculate(Token num1, Token op, Token num2) {
		float result = 0.0f;
		float n1 = num1.getFloatValue();
		float n2 = num2.getFloatValue();
		switch (op.toString()) {
			case "+":
				result = n1 + n2;
				break;
			case "-":
				result = n1 - n2;
				break;
			case "*":
				result = n1 * n2;
				break;
			case "/":
				if (n2 == 0) {
					throw new RuntimeException("Divide by 0");
				}
				result = n1 / n2;
				break;
		}
		return new Token(NUMBER, result + "");
	}
}