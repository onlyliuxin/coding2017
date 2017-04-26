package org.xukai.coderising.stack;

import java.util.List;
import java.util.Stack;

/**
 * 从右至左扫描表达式，遇到数字时，将数字压入堆栈，遇到运算符时，弹出栈顶的两个数，用运算符对它们做相应的计算（栈顶元素 op 次顶元素），并将结果入栈；重复上述过程直到表达式最左端，最后运算得出的值即为表达式的结果。
 例如前缀表达式“- × + 3 4 5 6”：
 (1) 从右至左扫描，将6、5、4、3压入堆栈；
 (2) 遇到+运算符，因此弹出3和4（3为栈顶元素，4为次顶元素，注意与后缀表达式做比较），计算出3+4的值，得7，再将7入栈；
 (3) 接下来是×运算符，因此弹出7和5，计算出7×5=35，将35入栈；
 (4) 最后是-运算符，计算出35-6的值，即29，由此得出最终结果。
 可以看出，用计算机计算前缀表达式的值是很容易的。
 * @author xukai
 * @desc
 * @date 2017-04-22-13:49
 */
public class PrefixExpr {


    private String expr;

    public PrefixExpr(String expr) {
        this.expr = expr;
    }

    public float evaluate(){

        TokenParser tokenParser = new TokenParser();
        List<Token> tokens = tokenParser.parse(expr);

        Stack<Float> numStack = new Stack();
        Stack<Float> oprStack = new Stack();

        for (int i = tokens.size() - 1; i > -1; i--) {
            if (tokens.get(i).isNumber()) {
                numStack.push(new Float(tokens.get(i).getIntValue()));
            } else {
                Float num1 = numStack.pop();
                Float num2 = numStack.pop();
                numStack.push(caculate(tokens.get(i).toString(), num1, num2));
            }
        }

        return numStack.pop();
    }

    private Float caculate(String oper, Float num1, Float num2){
        if (oper.equals("+")) {
            return num1 + num2;
        } else if (oper.equals("-")) {
            return num1 - num2;
        } else if (oper.equals("/")) {
            return num1 / num2;
        } else if (oper.equals("*")) {
            return num1 * num2;
        }
        throw new RuntimeException("illeagal operation token");
    }
}
