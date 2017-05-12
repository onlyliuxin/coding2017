package basic.dataStructure.stack.expr;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Token {
	public static final List<String> OPERATORS = Arrays.asList("+", "-", "*", "/");
	private static final Map<String,Integer> priorities = new HashMap<String,Integer>();
	static {
		priorities.put("+", 1);
		priorities.put("-", 1);
		priorities.put("*", 2);
		priorities.put("/", 2);
	}
	static final int OPERATOR = 1;
	static final int NUMBER = 2;
	static final int LBRACKET = -1;
	static final int RBRACKET = -2;
	String value;
	int type;
	public Token(int type, String value) {
		this.type = type;
		this.value = value;
	}

	public boolean isNumber() {
		return type == NUMBER;
	}

	public boolean isOperator() {
		return type == OPERATOR;
	}

	public boolean isLBracket(){
		return type == LBRACKET;
	}

	public boolean isRBracket(){
		return type == RBRACKET;
	}

	public boolean isBracket(){
		return type == LBRACKET || type == RBRACKET;
	}

	public int getIntValue() {
		return Integer.valueOf(value).intValue();
	}

	public float getFloatValue(){
		return Float.valueOf(value).floatValue();
	}

	public String toString(){
		return value;
	}
	
	public boolean hasHigherPriority(Token t){
		if(!this.isOperator() && !t.isOperator()){
			throw new RuntimeException("numbers can't compare priority");
		}
		return priorities.get(this.value) - priorities.get(t.value) > 0;
	}
	
	

}