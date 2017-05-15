package main.coding_170423;

import java.util.Stack;

/**
 * Created by peter on 2017/4/23.
 */
public class PostfixExpr {
    String expr = null;
    public PostfixExpr(String expr){
        this.expr = expr;
    }

    public float evaluate(){
        String[] tokens = expr.split(" ");
        int index = 0;
        Stack<Float> operator = new Stack();//存储操作数
        while (index < tokens.length){
            String s = tokens[index];
            if(Character.isDigit(s.charAt(0))){
                //如果是操作数，直接进栈
                operator.push(Float.parseFloat(s));
            }else{
                float ope2 = operator.pop();
                float ope1 = operator.pop();
                switch (s){
                    case "+":
                        operator.push(ope1+ope2);
                        break;
                    case "-":
                        operator.push(ope1-ope2);
                        break;
                    case "*":
                        operator.push(ope1*ope2);
                        break;
                    case "/":
                        operator.push(ope1/ope2);
                        break;
                    default:
                        System.out.println("invalid operator");
                }
            }
            index++;
        }
        return operator.pop();
    }
}
