package expr;

import stack.MyStack;

import java.util.List;

/**
 * Created by william on 2017/4/13.
 */
public class InfixExpr {
    String expr = null;

    public InfixExpr(String expr) {
        this.expr = expr;
    }

    public float evaluate() {
        List<String> operList = ExprParser.parse(this);
        MyStack<Object> numberStack = new MyStack<Object>();
        MyStack<String> operatorStack = new MyStack<String>();
        if (operList != null) {
            for (String operEle : operList) {
                if (ExprParser.belongsOperator(operEle))
                    operatorStack.push(operEle);
                else {
                    numberStack.push(operEle);
                    if (!operatorStack.isEmpty() && ExprParser.belongsHighPriority(operatorStack.peek())) {
                        String highPriorityValue = calcHighOper(numberStack, operatorStack);
                        numberStack.push(highPriorityValue);
                    }
                }
            }
        }
        if (numberStack.size() != operatorStack.size() + 1)
            throw new RuntimeException(" wrong operation number ");
        return Float.parseFloat(calcSameOper(numberStack, operatorStack));
    }

    private String calcHighOper(MyStack<Object> numberStack, MyStack<String> operatorStack) {
        Float rightNumber = Float.parseFloat((String) numberStack.pop());
        Float leftNumber = Float.parseFloat((String) numberStack.pop());
        String oper = operatorStack.pop();
        return calc(rightNumber, leftNumber, oper);
    }

    private String calcSameOper(MyStack<Object> numberStack, MyStack<String> operatorStack) {
        MyStack<Object> allStack = new MyStack<Object>();
        while (!operatorStack.isEmpty()) {
            allStack.push(numberStack.pop());
            allStack.push(operatorStack.pop());
        }
        allStack.push(numberStack.pop());
        while (allStack.size() != 1) {
            Float leftNumber = Float.parseFloat((String) allStack.pop());
            String oper = (String) allStack.pop();
            Float rightNumber = Float.parseFloat((String) allStack.pop());
            allStack.push(calc(rightNumber, leftNumber, oper));
        }
        return (String) allStack.pop();
    }

    private String calc(Float rightNumber, Float leftNumber, String oper) {
        if (oper.equals(OperEnum.ADD.getOperator()))
            return leftNumber + rightNumber + "";
        else if (oper.equals(OperEnum.SUBTRACT.getOperator()))
            return leftNumber - rightNumber + "";
        else if (oper.equals(OperEnum.MULTIPLY.getOperator()))
            return leftNumber * rightNumber + "";
        else if (oper.equals(OperEnum.MINUS.getOperator()))
            return leftNumber / rightNumber + "";
        else
            throw new RuntimeException("not support " + leftNumber + " " + oper + " " + rightNumber + " operation");
    }

}
