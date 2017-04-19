package com.github.miniyk2012.coding2017.basic.stack.expr;

import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class InfixExpr {
	private String expr = null;
    enum Operator {
        PLUS("+", 1), SUB("-", 1), MUL("*", 2), DIV("/", 2);  // 优先级越大，说明应该先算
        private String value;
        private int priority;
        Operator(String value, int prioriy) {
            this.value = value;
            this.priority = prioriy;
        }
        public static Operator parseOperator(String value) {
            for (Operator c : Operator.values()) {
                if (c.value.equals(value)) {
                    return c;
                }
            }
            throw new RuntimeException("运算符不存在");
        }
    }

	public InfixExpr(String expr) {
		this.expr = expr;
	}
	private static Token<Float> evaluate(Token<Operator> operator, Token<Float> number1, Token<Float> number2) {
        float ret;
        switch (operator.value) {
            case PLUS:
                ret = number1.getValue() + number2.getValue();
                break;
            case SUB:
                ret = number1.getValue() - number2.getValue();
                break;
            case MUL:
                ret = number1.getValue() * number2.getValue();
                break;
            case DIV:
                ret = number1.getValue() / number2.getValue();
                break;
            default:
                throw new RuntimeException("运算符不存在");
        }
        return new Token(ret, true);
    }


	public float evaluate() {
        List<Token> tokens= Token.parse(expr);
        Stack<Token<Float>> numberStack = new Stack<>();
        Stack<Token<Operator>> operatorStack = new Stack<>();
        for (Token t : tokens) {
            if (t.isNumber()) {
                numberStack.push(t);
            } else {
                if (operatorStack.isEmpty()) {
                    operatorStack.push(t);
                } else {
                    Token<Operator> preT = operatorStack.peek();
                    while (preT.value.priority >= ((Operator)t.value).priority) {
                        Token number2 = numberStack.pop();
                        Token number1 = numberStack.pop();
                        operatorStack.pop();
                        numberStack.push(evaluate(preT, number1, number2));
                        try {
                            preT = operatorStack.peek();
                        } catch (EmptyStackException e) {
                            break;
                        }
                    }
                    operatorStack.push(t);

                }
            }
        }
        while (!operatorStack.isEmpty()) {
            Token number2 = numberStack.pop();
            Token number1 = numberStack.pop();
            Token operator = operatorStack.pop();
            numberStack.push(evaluate(operator, number1, number2));
        }
		return numberStack.pop().getValue();
	}


    private static class Token<T> {
	    private boolean number;  // 是数字/操作符
	    private T value;
	    public boolean isNumber() {
	        return number;
        }
        public T getValue() {
	        return value;
        }

        public Token(T value, boolean number) {
            this.value = value;
            this.number = number;
        }
        public static List<Token> parse(String expr) {
	        int i = 0;
	        int size = expr.length();
            String current;
            List<Token> tokens = new LinkedList<>();
            String value = "";
            while (i < size) {
                current = expr.substring(i,i+1);
                if (isOperator(current)) {
                    tokens.add(new Token(Float.parseFloat(value), true));
                    value = "";
                    tokens.add(new Token(Operator.parseOperator(current), false));
                } else {
                    value += current;
                }
                i++;
            }
            tokens.add(new Token(Float.parseFloat(value), true));
            return tokens;
        }

        private static boolean isOperator(String value) {
            for (Operator c : Operator.values()) {
                if (c.value.equals(value)) {
                    return true;
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {


    }
}
