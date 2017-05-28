package list.expr;

import coding.coderising.litestruts.StringUtils;

import java.util.List;
import java.util.Stack;

/**
 * @author jiaxun
 */
public class PostfixExpr {

    String expr = null;

    public PostfixExpr(String expr) {
        this.expr = expr;
    }

    public float evaluate() {
        if (StringUtils.isEmpty(expr)) return 0.0f;
        TokenParser parser = new TokenParser();
        List<Token> tokenList = parser.parse(expr);
        Stack<Float> numberStack = new Stack<>();
        for (int i = 0, len = tokenList.size(); i < len; i++) {
            Token token = tokenList.get(i);
            if (token.isNumber()) {
                numberStack.push(Float.valueOf(token.getNumberValue()));
            }
            if (token.isOperator()) {
                float f2 = numberStack.pop();
                float f1 = numberStack.pop();
                numberStack.push(ExprUtils.calculate(token.getOperatorValue(), f1, f2));
            }
        }
        return numberStack.pop();
    }

}
