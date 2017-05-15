package com.ecust.jvm4;

import java.util.List;

import java.util.Stack;


public class PrefixExpr {


    private String expr;



    public PrefixExpr(String expr) {

        this.expr = expr;

    }



    public float evaluate(){



        TokenParser tokenParser = new TokenParser();

        List<Token> tokens = tokenParser.parse(expr);



        Stack<Float> numStack = new Stack();

        Stack<Float> oprStack = new Stack();



        for (int i = tokens.size() - 1; i > -1; i--) {

            if (tokens.get(i).isNumber()) {

                numStack.push(new Float(tokens.get(i).getIntValue()));

            } else {

                Float num1 = numStack.pop();

                Float num2 = numStack.pop();

                numStack.push(caculate(tokens.get(i).toString(), num1, num2));

            }

        }



        return numStack.pop();

    }



    private Float caculate(String oper, Float num1, Float num2){

        if (oper.equals("+")) {

            return num1 + num2;

        } else if (oper.equals("-")) {

            return num1 - num2;

        } else if (oper.equals("/")) {

            return num1 / num2;

        } else if (oper.equals("*")) {

            return num1 * num2;

        }

        throw new RuntimeException("illeagal operation token");

    }

}
