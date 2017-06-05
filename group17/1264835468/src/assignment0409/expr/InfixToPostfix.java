package assignment0409.expr;

import java.util.List;
import java.util.Stack;

public class InfixToPostfix {

    public static PostfixExpr myConvert(String expr) {
        Stack<Character> stack = new Stack<>();
        StringBuilder result = new StringBuilder();
        for (char c : expr.toCharArray()) {
            if (Character.isDigit(c)) {
                result.append(c);
            } else {
                result.append(" ");
                if (stack.empty()) {
                    stack.push(c);
                    continue;
                }
                //while ()
            }
        }
        return null;
    }

    public static List<Token> convert(String expr) {

        return null;
    }


}
