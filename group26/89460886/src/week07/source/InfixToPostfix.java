package list.expr;

import jdk.nashorn.internal.runtime.regexp.joni.constants.OPSize;
import sun.security.action.OpenFileInputStreamAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author jiaxun
 */
public class InfixToPostfix {

    public static List<Token> convert(String expr) {
        List<Token> resultList = new ArrayList<>();
        Stack<Token> opStack = new Stack<>();
        TokenParser parser = new TokenParser();
        List<Token> tokenList = parser.parse(expr);
        for (int i = 0, len = tokenList.size(); i < len; i++) {
            Token token = tokenList.get(i);
            if (token.isNumber()) {
                resultList.add(token);
            }
            if (token.isOperator()) {
                if (!opStack.isEmpty()) {
                    Token prevToken = opStack.peek();
                    if (!token.hasHigherPriority(prevToken)) {
                        while (!opStack.isEmpty()) {
                            resultList.add(opStack.pop());
                        }
                    }
                }
                opStack.push(token);
            }
        }
        while (!opStack.isEmpty()) {
            resultList.add(opStack.pop());
        }
        return resultList;
    }

}
