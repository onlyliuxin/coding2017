package expr;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IBM on 2017/4/15.
 */
public class ExprParser {

    public static List<String> parse(InfixExpr infixExpr) {
        if (infixExpr == null || infixExpr.expr == null || infixExpr.expr.trim().isEmpty())
            return null;
        String[] split = infixExpr.expr.split("");
        List<String> results = new ArrayList<String>();
        StringBuilder numberBuffer = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            String ele = split[i];
            if (ele.equals(OperEnum.ADD.getOperator()) || ele.equals(OperEnum.SUBTRACT.getOperator()) || ele.equals(OperEnum.MINUS.getOperator()) || ele.equals(OperEnum.MULTIPLY.getOperator())) {
                results.add(numberBuffer.toString());
                numberBuffer.delete(0, numberBuffer.length());
                results.add(ele);
            } else
                numberBuffer.append(ele);
        }
        results.add(numberBuffer.toString());
        return results;
    }

    public static boolean belongsHighPriority(String operEle) {
        return belongsOperator(operEle) && (operEle.trim().equals(OperEnum.MINUS.getOperator()) || operEle.trim().equals(OperEnum.MULTIPLY.getOperator()));
    }

    public static boolean belongsOperator(String operEle) {
        return (operEle != null) && (operEle.trim().equals(OperEnum.ADD.getOperator()) || operEle.trim().equals(OperEnum.SUBTRACT.getOperator()) || operEle.trim().equals(OperEnum.MULTIPLY.getOperator()) || operEle.trim().equals(OperEnum.MINUS.getOperator()));
    }
}
