package com.coding.basic.stack.expr;

import com.coding.basic.stack.Stack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class InfixToPostfix {
    private static List op = new ArrayList();

    public static List<Token> convert(String expr) {
        op.add('+');
        op.add('-');
        op.add('*');
        op.add('/');
        List<String> numList = new LinkedList<>();
        List<String> opList = new LinkedList<>();

        List<Token> list = new ArrayList<Token>();


        char[] chars = expr.toCharArray();

        int index = 0;
        while (index < chars.length) {
            if (op.contains(chars[index])) {
                if (chars[index] == '*' || chars[index] == '/') {

                    char oper = chars[index];

                    StringBuilder sb = new StringBuilder();
                    index++;
                    while (chars[index] >= '0' && chars[index] <= '9') {
                        sb.append(chars[index]);
                        index++;
                    }
                    String s = numList.get(numList.size() - 1);
                    numList.remove(numList.size() - 1);
                    StringBuilder buffer = new StringBuilder();
                    buffer.append(s + ' ');
                    buffer.append(sb);
                    buffer.append(oper);
                    numList.add(buffer.toString());
                    //numStack.push(buffer.toString());

                } else {
                    opList.add("" + chars[index]);
                    //opStack.push(chars[index]);
                }
                index++;
            } else if (chars[index] == ' ') {
                index++;
            } else {
                StringBuilder sb = new StringBuilder();
                while (chars[index] >= '0' && chars[index] <= '9') {
                    sb.append(chars[index]);
                    index++;
                }
                numList.add(sb.toString());
                //numStack.push(sb.toString());
            }

        }

        int opListIndex = 0;
        while (opListIndex < opList.size()) {
            for (int i = 0; i < opList.size(); i++) {
                String a = numList.get(0);
                String b = numList.get(1);
                numList.set(0, a + " " + b + " " + opList.get(opListIndex));
                numList.remove(1);
            }
        }

        String postfixExpr = numList.get(0);
        char[] chars1 = postfixExpr.toCharArray();
        index = 0;
        while (index < chars1.length) {
            if (chars1[index] == '+') {
                Token token = new Token(1, "+");
                list.add(token);
            } else if (chars1[index] == '-') {
                Token token = new Token(1, "-");
                list.add(token);
            } else if (chars1[index] == '*') {
                Token token = new Token(1, "*");
                list.add(token);
            } else if (chars1[index] == '/') {
                Token token = new Token(1, "/");
                list.add(token);
            }else if (chars1[index] == ' '){
                index++;
            }else {
                StringBuilder sb = new StringBuilder();
                while (chars1[index] >= '0' && chars1[index] <= '9') {
                    sb.append(chars1[index]);
                    index++;
                }
                Token token = new Token(2, sb.toString());
                list.add(token);
            }

        }

        return list;
    }


}
