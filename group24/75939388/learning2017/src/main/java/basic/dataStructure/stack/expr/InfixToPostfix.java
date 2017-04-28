package basic.dataStructure.stack.expr;

import basic.dataStructure.stack.Stack;

import java.util.ArrayList;
import java.util.List;

/**
 * 中序转后序
 */
public class InfixToPostfix {
    /**
     *
     * 参考文献许卓群版《数据结构与算法》
     *
     *
     * 1）当输入的是操作数时候，直接输出到后序表达式PostfixExp序列中
     * 2）当输入开括号时候，把它压栈
     * 3）当输入的是闭括号时候，先判断栈是否为空，若为空，则发生错误并进行相关处理。
     * 若非空，把栈中元素依次弹出并输出到Postfix中，知道遇到第一个开括号，
     * 若没有遇到开括号，也发生错误，进行相关处理
     * 4）当输入是运算符op（+、- 、×、/）时候
     * a)循环，当（栈非空and栈顶不是开括号and栈顶运算符的优先级不低于输入的
     * 运算符的优先级）时，反复操作：将栈顶元素弹出并添加到Postfix中
     * b)把输入的运算符op压栈
     * 5）当中序表达式InfixExp的符号序列全部读入后，若栈内扔有元素，把他们依次弹
     * 出并放到后序表达式PostfixExp序列尾部。若弹出的元素遇到空括号，则说明不
     * 匹配，发生错误，并进行相关处理
     */
    public static List<Token> convert(String expr) {
        List<Token> original = TokenParser.parse(expr);
        System.out.println(original);
        //结果集合
        List<Token> postfixTokenList = new ArrayList<Token>();
        //缓存，存放优先级低的运算符和括号
        Stack tokens = new Stack();
        for (Token t : original) {
            if (t.isNumber()) {
                postfixTokenList.add(t);
            }

            if (t.isLBracket()) tokens.push(t);

            if (t.isRBracket()) {
                if (tokens.isEmpty()) {
                    throw new RuntimeException("brackets are not in pair");
                } else {
                    Token tmp = (Token) tokens.peek();
                    while (!tmp.isLBracket()) {
                        if (tokens.size() == 1 && !tmp.isLBracket()) {
                            throw new RuntimeException("brackets are not in pair");
                        }
                        postfixTokenList.add((Token) tokens.pop());
                        tmp = (Token)tokens.peek();
                    }
                }
            }

            if(t.isOperator()){
                Token tmp = (Token)tokens.peek();
                while(tmp != null && !tmp.isLBracket() && tmp.isOperator() && tmp.hasHigherPriority(t)){
                    postfixTokenList.add((Token) tokens.pop());
                    tmp = (Token)tokens.peek();
                }

                tokens.push(t);
            }
            System.out.println("t--------->" + t.toString());
            System.out.println("tokens---->" + tokens.toString());
            System.out.println("postfix--->" + postfixTokenList + "\n");

        }

        while(tokens.size() > 0){
            Token t = (Token)tokens.pop();
            if(!t.isBracket()){
                postfixTokenList.add(t);
            }
        }

        return postfixTokenList;
    }


    public static void main(String[] args) {
//		convert("9+(3-1)*3+10/2");
        List<Token> list = convert("1+(2+3*4-5)/2+10-1");
//        System.out.println(list.toString());
//		convert("2+3*4-5");
    }

}
