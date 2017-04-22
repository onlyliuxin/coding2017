package assignment0409.expr;

/**
 * Created by Administrator on 2017/4/16.
 */
public abstract class Operator {
    int priority;
    public abstract float evaluate(float num1,float num2);

    public boolean hasHigherPriorityThan(Operator op){
        return priority > op.priority;
    }
    public static Operator of(char ch) {
        switch (ch) {
            case '+':
                return new Addition();
            case '-':
                return new Subtraction();
            case '*':
                return new Multiplication();
            case '/':
                return new Division();
            default:
                throw new IllegalExpressionException();
        }

    }
}
