package algorithm.expression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TokenParser {
    public static final List<String> OPERATORS =
            Arrays.asList(Token.OP_ADD, Token.OP_SUB, Token.OP_MUL,
                    Token.OP_DIV, Token.OP_L_BRACKET, Token.OP_R_BRACKET);

    public static List<Token> parse(String expression) {
        return parse(expression, null);
    }

    public static List<Token> parse(String expression, String splitRegex) {
        List<Token> tokens = new ArrayList<>();
        String[] split = splitRegex == null || "".equals(splitRegex) ?
                new String[]{expression} : expression.split(splitRegex);

        for (String expr : split) {
            int i = 0;
            while (i < expr.length()) {
                char c = expr.charAt(i);
                if (isOperator(c)) {
                    Token t = new Token(Token.OPERATOR, String.valueOf(c));
                    tokens.add(t);
                    i++;
                } else if (Character.isDigit(c)) {
                    int nextOperatorIndex = indexOfNextOperator(i, expr);
                    String value = expr.substring(i, nextOperatorIndex);
                    Token t = new Token(Token.NUMBER, value);
                    tokens.add(t);
                    i = nextOperatorIndex;
                } else {
                    System.out.println("char :[" + c + "] is not number or operator,ignore");
                    i++;
                }
            }
        }
        return tokens;
    }

    private static int indexOfNextOperator(int i, String expr) {
        while (Character.isDigit(expr.charAt(i))) {
            i++;
            if (i == expr.length()) {
                break;
            }
        }
        return i;
    }

    private static boolean isOperator(char c) {
        String sc = String.valueOf(c);
        return OPERATORS.contains(sc);
    }
}
