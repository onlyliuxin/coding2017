package org.xukai.coderising.stack;

import java.util.List;
import java.util.Stack;

/**
 * 将中缀表达式转换为后缀表达式：
 与转换为前缀表达式相似，遵循以下步骤：
 (1) 初始化两个栈：运算符栈S1和储存中间结果的栈S2；
 (2) 从左至右扫描中缀表达式；
 (3) 遇到操作数时，将其压入S2；
 (4) 遇到运算符时，比较其与S1栈顶运算符的优先级：
 (4-1) 如果S1为空，或栈顶运算符为左括号“(”，则直接将此运算符入栈；
 (4-2) 否则，若优先级比栈顶运算符的高，也将运算符压入S1（注意转换为前缀表达式时是优先级较高或相同，而这里则不包括相同的情况）；
 (4-3) 否则，将S1栈顶的运算符弹出并压入到S2中，再次转到(4-1)与S1中新的栈顶运算符相比较；
 (5) 遇到括号时：
 (5-1) 如果是左括号“(”，则直接压入S1；
 (5-2) 如果是右括号“)”，则依次弹出S1栈顶的运算符，并压入S2，直到遇到左括号为止，此时将这一对括号丢弃；
 (6) 重复步骤(2)至(5)，直到表达式的最右边；
 (7) 将S1中剩余的运算符依次弹出并压入S2；
 (8) 依次弹出S2中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式（转换为前缀表达式时不用逆序）。

 例如，将中缀表达式“1+((2+3)×4)-5”转换为后缀表达式的过程如下：
 扫描到的元素	S2(栈底->栈顶)	S1 (栈底->栈顶)	说明
 1	1	空	数字，直接入栈
 +	1	+	S1为空，运算符直接入栈
 (	1	+ (	左括号，直接入栈
 (	1	+ ( (	同上
 2	1 2	+ ( (	数字
 +	1 2	+ ( ( +	S1栈顶为左括号，运算符直接入栈
 3	1 2 3	+ ( ( +	数字
 )	1 2 3 +	+ (	右括号，弹出运算符直至遇到左括号
 ×	1 2 3 +	+ ( ×	S1栈顶为左括号，运算符直接入栈
 4	1 2 3 + 4	+ ( ×	数字
 )	1 2 3 + 4 ×	+	右括号，弹出运算符直至遇到左括号
 -	1 2 3 + 4 × +	-	-与+优先级相同，因此弹出+，再压入-
 5	1 2 3 + 4 × + 5	-	数字
 到达最右端	1 2 3 + 4 × + 5 -	空	S1中剩余的运算符
 因此结果为“1 2 3 + 4 × + 5 -”（注意需要逆序输出）。
 * @author xukai
 * @desc
 * @date 2017-04-22-14:10
 */
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
