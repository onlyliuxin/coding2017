package assignment0503.stack;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Administrator on 2017/5/7.
 */
public class TwoStackInOneArrayTest {
    TwoStackInOneArray twoStack = new TwoStackInOneArray();

    @Test
    public void twoStackTest() {
        pushAll(1, 1, 2, 3, 4);
        pushAll(2, 5, 6, 7, 8);
        twoStack.pop1();
        twoStack.pop2();
        Assert.assertEquals("[3, 2, 1] [7, 6, 5]", twoStack.toString());
        pushAll(1, 21, 22, 23, 24, 25, 26, 27);
        Assert.assertEquals("[27, 26, 25, 24, 23, 22, 21, 3, 2, 1] [7, 6, 5]", twoStack.toString());
        pushAll(2, 31, 32, 33, 34);
        Assert.assertEquals("[27, 26, 25, 24, 23, 22, 21, 3, 2, 1] [34, 33, 32, 31, 7, 6, 5]", twoStack.toString());

    }

    private void pushAll(int stackIndex, int... data) {
        if (stackIndex == 1) {
            for (int datum : data) {
                twoStack.push1(datum);
            }
        }
        if (stackIndex == 2) {
            for (int datum : data) {
                twoStack.push2(datum);
            }
        }
    }
}