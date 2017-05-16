package com.ecust.jvm4;

import java.util.List;

import java.util.Stack;



public class InfixToPostfix2 {





    private static TokenParser tokenParser = new TokenParser();



    public static String toPostFixExpr(String expr){



        List<Token> tokens = tokenParser.parse(expr);



        Stack<Token> s1 = new Stack<Token>();

        Stack<Token> s2 = new Stack<Token>();



        for (Token token : tokens) {

            if (token.isNumber()) {

                s2.push(token);

            } else {

                while (token.isOperator() && !s1.isEmpty()) {

                    if (!token.hasHigherPriority(s1.peek())) {

                        s2.push(s1.pop());

                        continue;

                    }

                    break;

                }

                s1.push(token);

            }

        }

        while (!s1.isEmpty()) {

            s2.push(s1.pop());

        }

        StringBuilder stringBuilder = new StringBuilder();

        while (!s2.isEmpty()) {

            Token token = s2.pop();

            s1.push(token);

        }

        while (!s1.isEmpty()) {

            Token token = s1.pop();

            stringBuilder.append(token.toString()).append(" ");

        }



        return stringBuilder.substring(0,stringBuilder.length() - 1);

    }





}
