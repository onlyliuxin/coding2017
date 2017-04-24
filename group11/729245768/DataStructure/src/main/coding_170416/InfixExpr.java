package main.coding_170416;

import java.util.Stack;

/**
 * Created by peter on 2017/4/23.
 */
public class InfixExpr {
    String expr = null;

    public InfixExpr(String expr) {
        this.expr = expr;
    }

    public float evalute() {
        Stack<Float> operator = new Stack<>();//存储操作数
        Stack<Character> opeCharacter = new Stack<>();//存储运算符
        StringBuffer sb = new StringBuffer("");//存储获取到的操作数
        int index = 0;//
        while (index < expr.length()) {

            Character ch = expr.charAt(index);
            if (!Character.isDigit(ch) && opeCharacter.size() == 0) {
                opeCharacter.push(ch);
                operator.push(Float.parseFloat(sb.toString()));
                sb = new StringBuffer("");
                index++;
                continue;
            }
            switch (ch) {
                case '+':
                case '-':
                    operator.push(Float.parseFloat(sb.toString()));
                    sb = new StringBuffer("");
                    Character top = opeCharacter.pop();
                    float op2 = operator.pop();
                    float op1 = operator.pop();
                    switch (top) {
                        case '+':
                            operator.push(op1 + op2);
                            break;
                        case '-':
                            operator.push(op1 - op2);
                            break;
                        case '*':
                            operator.push(op1 * op2);
                            break;
                        case '/':
                            operator.push(op1 / op2);
                            break;
                    }
                    opeCharacter.push(ch);
                    break;
                case '*':
                case '/':
                    operator.push(Float.parseFloat(sb.toString()));
                    sb = new StringBuffer("");
                    Character top1 = opeCharacter.peek();
                    if (top1 == '+' || top1 == '-') {
                        opeCharacter.push(ch);
                    } else {
                        opeCharacter.pop();
                        float ope2 = operator.pop();
                        float ope1 = operator.pop();
                        if (top1 == '*') {
                            operator.push(ope1 * ope2);
                        } else {
                            operator.push(ope1 / ope2);
                        }
                        opeCharacter.push(ch);
                    }
                    break;
                default:
                    sb.append(ch);
            }
            index++;
            if (index == expr.length()) {
                operator.push(Float.parseFloat(sb.toString()));
            }
        }
        //计算运算符栈剩余的操作符
        while (opeCharacter.size()>0){
            float last2 = operator.pop();
            float last1 = operator.pop();
            switch (opeCharacter.pop()){
                case '+': operator.push(last1 + last2) ;break;
                case '-': operator.push(last1 - last2);break;
                case '*': operator.push(last1 * last2);break;
                case '/': operator.push(last1/last2);
            }
        }

        return operator.pop();
    }
}
