import java.util.*;
import java.util.Stack;

/**
 * Created by Lxx on 2017/4/29.
 */
public class InfixToPostfix {

    String expr = null;
    public InfixToPostfix(String expr){
        this.expr = expr;
    }
    TokenParser parser = new TokenParser();
    List<Token> tokens = parser.parse(this.expr);
    Stack<Token> opStack = new Stack<>();
    Stack<String> allStack = new Stack<>();
    public Stack change(){
        for(Token token : tokens){
            if(token.isOperator()){
                if(opStack.isEmpty()){
                    opStack.push(token);
                }else{
                    while(!opStack.isEmpty() && !token.hasHigherPriority(opStack.peek())){
                        Token preOperator = opStack.pop();
                        allStack.push(preOperator.toString());
                    }
                }
            }
            if (token.isNumber()){
                allStack.push(token.toString());
            }
        }
        return allStack;
    }
}
