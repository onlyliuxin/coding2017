package assignment0409.expr;

/**
 * Created by Administrator on 2017/4/16.
 */
public class Addition extends Operator {

    public Addition() {
        priority=1;
    }

    @Override
    public float evaluate(float num1, float num2) {
        return num1+num2;
    }
}
