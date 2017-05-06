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
	}
		
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
