package algorithm.expression;

class Token {
	public static final String OP_ADD = "+";
	public static final String OP_SUB = "-";
	public static final String OP_MUL = "*";
	public static final String OP_DIV = "/";
	public static final String OP_L_BRACKET = "(";
	public static final String OP_R_BRACKET = ")";
	public static final String OP_SCOPE = "#";

	public static final int OPERATOR = 1;
	public static final int NUMBER = 2;

	public static final Token ADD = new Token(OPERATOR, OP_ADD);
	public static final Token SUB = new Token(OPERATOR, OP_SUB);
	public static final Token MUL = new Token(OPERATOR, OP_MUL);
	public static final Token DIV = new Token(OPERATOR, OP_DIV);
	public static final Token L_BRACKET = new Token(OPERATOR, OP_L_BRACKET);
	public static final Token R_BRACKET = new Token(OPERATOR, OP_R_BRACKET);
	public static final Token SCOPE = new Token(OPERATOR, OP_SCOPE);

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
			case OP_ADD:
				return 0;
			case OP_SUB:
				return 1;
			case OP_MUL:
				return 2;
			case OP_DIV:
				return 3;
			case OP_L_BRACKET:
				return 4;
			case OP_R_BRACKET:
				return 5;
			case OP_SCOPE:
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Token token = (Token) o;

		return type == token.type && (value != null ? value.equals(token.value) : token.value == null);
	}

	@Override
	public int hashCode() {
		int result = value != null ? value.hashCode() : 0;
		result = 31 * result + type;
		return result;
	}
}