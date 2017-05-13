package basic.dataStructure.stack.expr;

import basic.dataStructure.stack.Stack;

import java.util.ArrayList;
import java.util.List;

/**
 * 中序转后序
 */
public class InfixToPostfix {

    public static List<Token> convert(String expr) {
        List<Token> postfixTokenList = new ArrayList<Token>();

        List<Token> original = TokenParser.parse(expr);
        Stack buffer = new Stack();
        for(Token t : original){
            //数字直接放入结果
            if(t.isNumber()) postfixTokenList.add(t);

            //左括号，放入缓存
            if(t.isLBracket()) buffer.push(t);

            //右括号, 遍历完成后不用放入结果集
            if(t.isRBracket()){
                if(buffer.size() == 0){
                    throw new RuntimeException("brackets are not in pair, check your expression");
                }else{
                    //遍历直至左括号
                    Token tmp = (Token) buffer.peek();
                    while (!tmp.isLBracket()){
                        if(buffer.size() == 1 && !tmp.isLBracket()) throw new RuntimeException("brackets are not in pair, check your expression");
                        //放入结果list
                        postfixTokenList.add((Token) buffer.pop());
                        tmp = (Token)buffer.peek();
                    }
                }
            }

            //处理运算符:
            //  遍历：缓存非空且栈顶是一个运算符且缓存栈顶的运算符的优先级不低于t的优先级，则将栈顶运算符直接入结果list
            //  遍历完成后当前运算符压进缓存栈中
            if(t.isOperator()){
                Token tmp = (Token) buffer.peek();
                while(tmp != null && tmp.isOperator() && tmp.hasHigherPriority(t)){
                    //弹出至结果list
                    postfixTokenList.add((Token) buffer.pop());
                    tmp = (Token) buffer.peek();
                }
                buffer.push(t);
            }
        }

        //遍历完成后，若缓存还有值，则直接放到结果集，并去掉左括号
        while (buffer.size() > 0){
            Token t = (Token) buffer.pop();
            if(!t.isBracket()){
                postfixTokenList.add(t);
            }
        }

        return postfixTokenList;
    }


    public static void main(String[] args) {
//		convert("9+(3-1)*3+10/2");
        List<Token> list = convert("1+(2+3*4-5)/2+10-1");
        System.out.println(list.toString());
//		convert("2+3*4-5");
    }

}
