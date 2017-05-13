package com.coding.basic.stack.expr;

import java.util.List;
import java.util.Stack;

public class InfixExpr {
	String expr = null;
	List<Token> tokens;
	public InfixExpr(String expr) {
		this.expr = expr;
	}

	private Token next(int i){
	    Token token;
        try {
           token  = tokens.get(i++);
        }catch (Exception e){
            token = null;
        }
        return token;
    }
	public float evaluate() {
        System.out.println("计算"+expr);
        TokenParser tokenParser = new TokenParser();
        tokens = tokenParser.parse(this.expr);
        Stack<Integer> integers = new Stack<>();
        Stack<Token> ops = new Stack<>();
        int i = 0;
        Token curToken = tokens.get(i++);
        while (curToken!=null){
            System.out.println("当前 "+curToken);
            System.out.println("操作数 "+integers);
            System.out.println("操作符 "+ops);
            if (curToken.isNumber()){
                integers.push(curToken.getIntValue());
                curToken = next(i++);
            }else {
                if (ops.isEmpty() || curToken.hasHigherPriority(ops.peek())){
                    ops.push(curToken);
                    curToken=next(i++);
                }else{
                    Token token = ops.pop();
                    int i1 = integers.pop();
                    int i2 = integers.pop();
                    int r = 0;
                    System.out.println(i1+" "+i2+" "+token);
                    r = getResult(token, i1, i2);
                    integers.push(r);
                }
            }
        }
        // 可能存在多个操作符的情况
        while (!ops.isEmpty()){
            int i1 = integers.pop();
            int i2 = integers.pop();
            int r = 0;
            Token token = ops.pop();
            System.out.println(i1+" "+i2+" "+token);
            r = getResult(token, i1, i2);
            integers.push(r);
        }
        System.out.println("计算"+expr+"完毕");
		return integers.pop();
	}

    private int getResult(Token token, int i1, int i2) {
        int r = 0;
        if (token.value.equals("+")){
            r = i2+i1;
        }else if (token.value.equals("-")){
            r = i2-i1;
        }else if (token.value.equals("*")){
            r = i2*i1;
        }else if (token.value.equals("/")){
            r = i2/i1;
        }
        return r;
    }


}
