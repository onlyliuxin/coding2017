package basic.dataStructure.stack.expr;

/**
 * @author : 温友朝
 * @date : 2017/4/27
 */
public class Calculator {
    public static float getFloat(float val1, float val2, String oper) {
        float res = 0l;
        if (oper.equals("*")) {
            res = val1 * val2;
        } else if (oper.equals("+")) {
            res = val1 + val2;
        } else if (oper.equals("-")) {
            res = val1 - val2;
        } else {
            if (val2 == 0) throw new RuntimeException("cannot divide 0, calculation canceled");
            res = val1 / val2;
        }
//        System.out.println("计算结果: " + val1 + oper + val2 + "=" + res);
        return res;
    }
}
