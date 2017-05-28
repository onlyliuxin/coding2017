package list.expr;

import coding.coderising.litestruts.StringUtils;

import java.util.List;
import java.util.Stack;

/**
 * @author jiaxun
 */
public class PrefixExpr {

    String expr = null;

    public PrefixExpr(String expr) {
        this.expr = expr;
    }

    public float evaluate() {
        if (StringUtils.isEmpty(expr))  return 0.0f;
        TokenParser parser = new TokenParser();
        List<Token> tokenList = parser.parse(expr);
        Stack<Token> tokenStack = new Stack<>();
        for (int i = 0, len = tokenList.size(); i < len; i++) {
            Token token = tokenList.get(i);
            tokenStack.push(token);
        }
        Stack<Float> numberStack = new Stack<>();
        while (!tokenStack.isEmpty()) {
            Token token = tokenStack.pop();
            if (token.isNumber()) {
                numberStack.push(new Float(token.getNumberValue()));
            }
            if (token.isOperator()) {
                float f1 = numberStack.pop();
                float f2 = numberStack.pop();
                numberStack.push(ExprUtils.calculate(token.getOperatorValue(), f1, f2));
            }
        }
        return numberStack.pop();
    }

}