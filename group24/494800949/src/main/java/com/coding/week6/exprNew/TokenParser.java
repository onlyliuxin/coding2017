package com.coding.week6.exprNew;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/22 0022.
 */
public class TokenParser {

    private static List<String> operators = Operator.symbols();
    public List<Token> parse(String expr) {
        List<Token> tokens = new ArrayList<>();
        int i = 0;
        while (i < expr.length()) {
            char c = expr.charAt(i);
            Token token;
            if (Character.isDigit(c)) {
                int nextOperIndex = getNextOperIndex(i, expr);
                String n = expr.substring(i, nextOperIndex);
                token = new Token(Token.NUMBER, n);
                tokens.add(token);
                i = nextOperIndex;
            } else if (isOperator(c)) {
                token = new Token(Token.OPERATOR, c+"");
                tokens.add(token);
                i++;
            } else if (String.valueOf(c).matches("\\s")){
                i++;
            } else if (c == '(') {
                token = new Token(Token.LEFT_BRACKET, String.valueOf(c));
                tokens.add(token);
                i++;
            } else if (c == ')') {
                token = new Token(Token.RIGHT_BRACKET, String.valueOf(c));
                tokens.add(token);
                i++;
            } else {
                throw new RuntimeException(c +" is not number or support operator");
            }
        }
        return tokens;
    }

    private int getNextOperIndex(int i, String expr) {
        while (Character.isDigit(expr.charAt(i))) {
            i++;
            if (i == expr.length()) {
                break;
            }
        }
        return i;
    }

    private boolean isOperator(char c) {
      return  operators.contains(String.valueOf(c));
    }


}
