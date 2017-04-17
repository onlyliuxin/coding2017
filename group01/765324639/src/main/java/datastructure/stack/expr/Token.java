package datastructure.stack.expr;

public class Token {

    public static final String NUMBER = "number";
    
    public static final String OPERATOR = "operator";
    
    private String type;
    
    private String value;
    
    public Token(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int comparePriority(Token token) {
        String higherLevel = "*/";
        String lowerLevel = "+-";
        if (higherLevel.contains(token.getValue())) {
            if (higherLevel.contains(this.value)) {
                return 0;
            }
            return -1;
        } else if (lowerLevel.contains(token.getValue())){
            if (lowerLevel.contains(this.value)) {
                return 0;
            }
            return 1;
        } else {
            throw new RuntimeException("不支持的运算符");
        }
    }
}
