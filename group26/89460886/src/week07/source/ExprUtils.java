package list.expr;

/**
 * @author jiaxun
 */
public class ExprUtils {

    public static Float calculate(String op, Float f1, Float f2){
        if(op.equals("+")){
            return f1+f2;
        }
        if(op.equals("-")){
            return f1-f2;
        }
        if(op.equals("*")){
            return f1*f2;
        }
        if(op.equals("/")){
            return f1/f2;
        }
        throw new RuntimeException(op + " is not supported");
    }

}
