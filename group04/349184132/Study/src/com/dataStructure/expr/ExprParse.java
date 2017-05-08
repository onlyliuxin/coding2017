package com.dataStructure.expr;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by wang on 2017/4/14.
 */
public class ExprParse {

    public List<Token> prase(String expr){

        List<Token> tokens = new LinkedList<>();

        int i = 0;

        while(i < expr.length()){

            char c = expr.charAt(i);

            if(isOperator(c)){

                Token t = new Token(Token.OPERATOR,String.valueOf(c));
                tokens.add(t);
                i++;
            }else if(isNumber(c)){
                int nextIsNumberIndex = nextIsNumber(expr,i);
                String num = expr.substring(i,nextIsNumberIndex);
                Token t = new Token(Token.NUMBER,num);
                tokens.add(t);
                i=nextIsNumberIndex;
            }else{
                throw new RuntimeException( c + " is not operator or number ! " );
            }
        }
        return tokens;

    }

    private int nextIsNumber(String expr,int i) {
        while(i<expr.length()){
            char c = expr.charAt(i);
            if(isNumber(c)){
                i++;
            }else{
                break;
            }

        }
        return i;
    }

    private boolean isNumber(char c) {
        int num = (int)c - 48;
        for(int i = 0; i<10; i++){
            if(num == i){
                return true;
            }
        }
        return false;
    }

    private boolean isOperator(char c) {
        if(c=='+'){
            return true;
        }else if(c=='-'){
            return true;
        }else if(c=='*'){
            return true;
        }else if(c=='/'){
            return true;
        }else{
            return false;
        }

    }


}
