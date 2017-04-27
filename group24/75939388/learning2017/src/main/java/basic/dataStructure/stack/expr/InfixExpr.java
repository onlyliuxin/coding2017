package basic.dataStructure.stack.expr;

import basic.dataStructure.stack.Stack;

import java.util.List;

public class InfixExpr {
    String expr = null;

    public InfixExpr(String expr) {
        this.expr = expr;
    }

    public float evaluate() {
        List<Token> tokens = TokenParser.parse(expr);
        Stack numbers = new Stack();
        Stack operators = new Stack();

        int temp = 0;

        for (Token token : tokens) {
            if (token.isOperator()) {
                operators.push(token);
            }

            if (token.isNumber()) {
                numbers.push(token);
            }

            //先计算
            int opeSize = operators.size();
            int numSize = numbers.size();

            float val1 = ((Token) numbers.pop()).getFloatValue();
            float val2 = ((Token) numbers.pop()).getFloatValue();
            if (numSize == 3 && opeSize == 2) {
                Token tmp = (Token) operators.pop();
                if (tmp.hasHigherPriority((Token) operators.peek())) {
                    //如果1+2*3，先计算numbers后两位
                    numbers.push(new Token(Token.NUMBER, Calculator.getFloat(val1, val2, tmp.toString()) + ""));
                } else {
                    //如果1*2+3，先计算numbers栈前两位
                    //先保存数字和运算符
                    Token sNum = (Token) numbers.pop();
                    Token sOper = tmp;

                    //需要进行计算的运算符
                    Token oper = (Token) operators.pop();
                    numbers.push(new Token(Token.NUMBER, Calculator.getFloat(val1, val2, oper.toString()) + ""));
                    numbers.push(new Token(Token.NUMBER, sNum + ""));
                    operators.push(new Token(Token.OPERATOR, sOper.toString()));
                }
            }
        }

        if (numbers.size() == 2 && operators.size() == 1) {
            float val1 = ((Token) numbers.pop()).getFloatValue();
            float val2 = ((Token) numbers.pop()).getFloatValue();
            return Calculator.getFloat(val1, val2, (operators.pop()).toString());
        } else {
            throw new RuntimeException("last calculation exception, numbers.size=" + numbers.size() + ", operators.size=" + operators.size());
        }
    }

}
