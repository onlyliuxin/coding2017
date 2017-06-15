package code07;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InfixToPostfix {
	
	public static List<Token> convert(String expr) {

	    List<Token> tokens = new ArrayList<Token>();

        Stack<Token> tokenStack = new Stack<Token>();
        List<Token> infixTokens =TokenParser.parseInfix(expr);

        for(Token t : infixTokens){
            if(t.isNumber()){
                tokens.add(t);
            }else {
                while (!tokenStack.isEmpty() && !t.hasHigherPriority(tokenStack.peek())){
                    tokens .add(tokenStack.pop());
                }
                tokenStack.push(t);
            }
        }

        while(!tokenStack.isEmpty()){
            tokens.add(tokenStack.pop());
        }

		return tokens;
	}

    public static void main(String[] args) {
        String expr = "2+3*4+5";
        List<Token> tokens = InfixToPostfix.convert(expr);
        for(Token t : tokens){
            System.out.println(t.getStringValue());
        }

    }


}
