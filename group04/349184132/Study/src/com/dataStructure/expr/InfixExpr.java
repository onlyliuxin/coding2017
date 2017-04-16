package com.dataStructure.expr;


import java.util.List;
import java.util.Stack;

/**
 * Created by wang on 2017/4/14.
 */
public class InfixExpr {
    String expr = null;

    public InfixExpr(String expr) {
        this.expr = expr;
    }



    public float evaluate() {

        ExprParse exprParse = new ExprParse();
        List<Token> tokens = exprParse.prase(this.expr);

        Stack<Float> val = new Stack<>();
        Stack<String> op = new Stack<>();


        for(int i=0; i<tokens.size(); i++){
            Token token = tokens.get(i);
            if(token.getType() == Token.OPERATOR){
                if(op.isEmpty()){

                    op.push(token.getValue());
                }else{

                    int topPrio = getPriority(op.peek());
                    int entryPrio = getPriority(token.getValue());

                    if(entryPrio > topPrio){
                        op.push(token.getValue());
                    }else if(entryPrio <= topPrio){
                        float result = getResult(val,op);
                        val.push(result);
                        op.push(token.getValue());
                    }
                }


            }else if(token.getType() == Token.NUMBER){
                val.push(Float.parseFloat(token.getValue()));

            }
        }

        while(!op.isEmpty()){
            float result = getResult(val,op);
            val.push(result);
        }


        return val.peek();
    }

    private float getResult(Stack<Float> val, Stack<String> op) {
        float v2 = val.pop();
        float v1 = val.pop();
        return calculate(op.pop(),v1,v2);
    }

    public int getPriority(String op){
        if(op.equals("+") || op.equals("-")){
            return 1;
        }else if(op.equals("*") || op.equals("/")){
            return 2;
        }else{
            throw new RuntimeException("Not support this operator!");
        }
    }

    private float calculate(String ops , float v1 , float v2){
        if(ops.equals("+") ){
            return v1 + v2;
        }else if(ops.equals("-")){
            return v1 - v2;
        }else if(ops.equals("*")){
            return v1 * v2;
        }else if(ops.equals("/")){
            return v1 / v2;
        }else{
            throw new RuntimeException("Not support this operator");
        }
    }







}