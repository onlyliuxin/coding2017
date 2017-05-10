package expr;

/**
 * Created by IBM on 2017/4/15.
 */
public enum OperEnum {
    ADD("+"), SUBTRACT("-"), MULTIPLY("*"), MINUS("/");
    private String operator;

    OperEnum(String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
