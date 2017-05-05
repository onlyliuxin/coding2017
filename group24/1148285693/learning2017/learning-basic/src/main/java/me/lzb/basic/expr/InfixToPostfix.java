package me.lzb.basic.expr;

import java.util.List;
import java.util.Stack;

/**
 * 中序转后序
 * Created by LZB on 2017/4/20.
 */
public class InfixToPostfix {

    private String infix;

    public InfixToPostfix(String infix) {
        this.infix = infix;
    }


    public String change() {
        StringBuffer sb = new StringBuffer();
        List<Node> list = CalUtil.processInfixExpr(infix);
        Stack<Node> symbolStack = new Stack<>();

        for (int i = 0; i < list.size(); i++) {
            Node n = list.get(i);
            //如果是数字，直接输出
            if (n.isNumber()) {
                append(sb, n);
                continue;
            }

            //操作符部分

            //操作符栈为空，操作符入栈
            if (symbolStack.isEmpty()) {
                symbolStack.push(n);
                continue;
            }

            if (")".equals(n.symbol)) {
                //当前操作符为)，输出操作数栈内的操作符，直到遇到第一个(
                while (!"(".equals(symbolStack.peek().symbol)) {
                    append(sb, symbolStack.pop());
                }
                //遇到的第一个(, 出栈，完成一个括号
                symbolStack.pop();
            } else {

                //计算用，操作符
                //栈顶元素优先级更低，操作符入栈
                if (CalUtil.isLowLevel(symbolStack.peek(), n) || symbolStack.peek().isLevel3()) {
                    symbolStack.push(n);
                } else {
                    //栈顶元素优先级高于等于当前操作符，输出操作符，直到遇到(，或者遇到优先级更低的操作符
                    while (!symbolStack.isEmpty() && !symbolStack.peek().isLevel3() && !CalUtil.isLowLevel(symbolStack.peek(), n)) {
                        append(sb, symbolStack.pop());
                    }
                    //当前操作符入栈
                    symbolStack.push(n);
                }
            }
        }


        while (!symbolStack.isEmpty()) {
            append(sb, symbolStack.pop());
        }

        return sb.toString().trim();
    }


    private void append(StringBuffer sb, Node node) {
        sb.append(" ");
        sb.append(node.isNumber() ? (int) node.number : node.symbol);
    }


}
