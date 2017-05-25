package task0416.coding.basic.stuck.expr;

public class Token {
	static final String OPERATOR = "operator";
	static final String NUMBER = "number";
	static final int LEVEL_ADD_SUB = 1;
	static final int LEVEL_MUL_DIV = 2;
	
	private String value;
	private int level;
	private String type;
	
	public Token(String type,String value){
		this.type = type;
		this.value = value;
		if(type.equals(OPERATOR)){
			setLevel(value);
		}
	}
		
	public String getValue() {
		return value;
	}
	
	public int getLevel() {
		return level;
	}

	private void setLevel(String value){
		if("+".equals(String.valueOf(value))||"-".equals(String.valueOf(value))){
			this.level = LEVEL_ADD_SUB;
		}else{
			this.level =LEVEL_MUL_DIV;
		}
	}
	
	public boolean isNumber() {
		return type == NUMBER;
	}

	public boolean isOperator() {
		return type == OPERATOR;
	}
}
