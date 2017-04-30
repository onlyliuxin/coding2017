package com.github.miniyk2012.coding2017.basic.stack.expr;

import java.util.List;

/**
 * Created by thomas_young on 30/4/2017.
 */
public class ExprUtil {

    /**
     * 求值
     * @param operand1  操作数1
     * @param operator  操作符
     * @param operand2  操作数2
     * @return Token表示的值
     */
    public static Token evalute(Token operand1, Token operator, Token operand2) {
        float resultValue;
        switch (operator.value) {
            case "+": {
                resultValue = operand1.getFloatValue() + operand2.getFloatValue();
                break;
            }
            case "-": {
                resultValue = operand1.getFloatValue() - operand2.getFloatValue();
                break;
            }
            case "*": {
                resultValue = operand1.getFloatValue() * operand2.getFloatValue();
                break;
            }
            case "/": {
                resultValue = operand1.getFloatValue() / operand2.getFloatValue();
                break;
            }
            default: throw new RuntimeException("不支持的运算符:" + operator.value);
        }
        return new Token(Token.NUMBER, String.valueOf(resultValue));
    }

    public static String tokens2String(List<Token> tokens) {
        StringBuffer buffer = new StringBuffer();
        int i = 0;
        for (Token token: tokens) {
            if (i != tokens.size()-1)
                buffer.append(token.toString()+" ");
            else
                buffer.append(token.toString());
            i++;
        }
        return buffer.toString();
    }
}
