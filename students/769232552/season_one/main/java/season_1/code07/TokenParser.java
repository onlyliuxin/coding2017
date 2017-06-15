package code07;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yyglider on 2017/4/20.
 * 解析表达式成各个Token
 */
public class TokenParser {

    public static List<Token> parseNoInfix(String expr){
        List<Token> tokens = new ArrayList<Token>();

        String[] chs = expr.split(" ");

        for (int i = 0; i < chs.length; i++) {
            if(isOperator(chs[i])){
                tokens.add(new Token(Token.OPERATOR,chs[i]));
            }else {
                String value = chs[i];
                tokens.add(new Token(Token.NUMBER,value));
            }
        }
        return tokens;
    }


    public static List<Token> parseInfix(String expr){
        List<Token> tokens = new ArrayList<Token>();

        char[] chs = expr.toCharArray();

        int i = 0;
        while (i < chs.length){
            if(isOperator(chs[i])){
                tokens.add(new Token(Token.OPERATOR,String.valueOf(chs[i])));
                i++;
            }else if(Character.isDigit(chs[i])){

                int nextOperatorPos = findNextOperatorPos(i,chs);
                String value = expr.substring(i,nextOperatorPos);
                tokens.add(new Token(Token.NUMBER,value));
                i = nextOperatorPos;

            }else {
                System.out.println("char :["+chs[i]+"] is not number or operator,ignore");
                i++;
            }
        }
        return tokens;
    }

    private static int findNextOperatorPos(int i, char[] chs) {
        while (Character.isDigit(chs[i])) {
            i++;
            if (i == chs.length) {
                break;
            }
        }
        return i;
    }

    private static boolean isOperator(char c) {
        String sc = String.valueOf(c);
        return Token.OPERATORS.contains(sc);
    }

    private static boolean isOperator(String c) {
        return Token.OPERATORS.contains(c);
    }

    public static void main(String[] args) {
/*
        String expr = "20+30.8*400/500";
        List<Token> tokens = TokenParser.parseInfix(expr);
        for(Token t: tokens){
            System.out.println(t.getStringValue());
        }
*/


        String expr2 = "- + + 6 / * 2 9 3 * 4 2 8";
        List<Token> tokens2 = TokenParser.parseNoInfix(expr2);
        for(Token t: tokens2){
            System.out.println(t.getStringValue());
        }
    }
}
