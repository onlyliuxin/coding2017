package datastructure.stack.expr;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TokenParser {

    private String expr;
    
    private List<Token> tokenList = new ArrayList<>();
    
    public TokenParser(String expr) {
        this.expr = expr;
    }
    
    public List<Token> parse() {
        if (expr == null || "".equals(expr)) {
            return Collections.emptyList();
        }
        
        char[] charArray = expr.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (isNumber(charArray[i])) {
                i = addFullNumber(charArray, i);
                i--;
            } else if (isOperator(charArray[i])) {
                addOperator(charArray[i]);
            }
        }
        return tokenList;
    }
    
    private boolean isNumber(char c) {
        String numbers = "0123456789";
        if (numbers.indexOf(c) != -1) {
            return true;
        }
        return false;
    }
    
    private boolean isOperator(char c) {
        String supplyOperator = "+-*/";
        if (supplyOperator.indexOf(supplyOperator) != -1) {
            return true;
        }
        return false;
    }
    
    private int addFullNumber(char[] charArray, int i) {
        StringBuilder builder = new StringBuilder();
        for (; i < charArray.length; i++) {
            if (isNumber(charArray[i])) {
                builder.append(charArray[i]);
            } else {
                break;
            }
        }
        tokenList.add(new Token(Token.NUMBER, builder.toString()));
        return i;
    }
    
    private void addOperator(char c) {
        tokenList.add(new Token(Token.OPERATOR, String.valueOf(c)));
    }
}
