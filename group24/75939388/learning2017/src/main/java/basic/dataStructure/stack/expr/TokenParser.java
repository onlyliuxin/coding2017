package basic.dataStructure.stack.expr;

import java.util.ArrayList;
import java.util.List;

public class TokenParser {


    public static List<Token> parse(String expr) {
        List<Token> tokens = new ArrayList<Token>();

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

            } else if (isLeftBracket(String.valueOf(c))) {
                Token t = new Token(Token.LBRACKET, String.valueOf(c));
                tokens.add(t);
                i++;
            } else if (isRightBracket(String.valueOf(c))) {
                Token t = new Token(Token.RBRACKET, String.valueOf(c));
                tokens.add(t);
                i++;
            } else {
                System.out.println("char :[" + c + "] is not number or operator,ignore");
                i++;
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
        return Token.OPERATORS.contains(sc);
    }

    private static boolean isLeftBracket(String s) {
        return s.equals("(");
    }

    private static boolean isRightBracket(String s) {
        return s.equals(")");
    }

}
