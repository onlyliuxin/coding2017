package task9.stack;

import java.util.Objects;

public class TwoStackInOneArray {

    private static final String INDEX_EXCEPTION_MSG = "数组越界了";
    private static final int DEFAULT_SIZE = 10;

    private Object[] data;

    private int length = DEFAULT_SIZE;

    public TwoStackInOneArray() {
        data = new Object[length];
    }

    public TwoStackInOneArray(int size) {
        this.length = size;
        data = new Object[length];
    }

    private int leftSize = 0;
    private int rightSize = 0;

    /**
     * 向第一个栈中压入元素
     *
     * @param o
     */
    public void push1(Object o) {
        checkIndexRange();
        data[leftSize++] = o;
    }

    private void checkIndexRange() {
        if (leftSize + rightSize > length) {
            throw new RuntimeException(INDEX_EXCEPTION_MSG);
        }
    }

    /**
     * 从第一个栈中弹出元素
     *
     * @return
     */
    public Object pop1() {
        if (leftSize <= 0) {
            throw new RuntimeException(INDEX_EXCEPTION_MSG);
        }
        Object result = data[0];
        Object[] temp = new Object[length];
        System.arraycopy(data, 1, temp, 0, leftSize - 1);
        System.arraycopy(data, length - rightSize, temp, length - rightSize, rightSize);
        data = temp;
        leftSize--;
        return result;
    }

    /**
     * 获取第一个栈的栈顶元素
     *
     * @return
     */

    public Object peek1() {
        return data[0];
    }

    /*
     * 向第二个栈压入元素
     */
    public void push2(Object o) {
        checkIndexRange();
        data[length - rightSize - 1] = o;
        rightSize++;
    }

    /**
     * 从第二个栈弹出元素
     *
     * @return
     */
    public Object pop2() {
        if (rightSize <= 0) {
            throw new RuntimeException(INDEX_EXCEPTION_MSG);
        }
        Object result = data[length - 1];
        Object[] temp = new Object[length];
        System.arraycopy(data, 0, temp, 0, leftSize);
        System.arraycopy(data, length - rightSize, temp, length - rightSize + 1, rightSize - 1);
        data = temp;
        leftSize--;
        return result;
    }

    /**
     * 获取第二个栈的栈顶元素
     *
     * @return
     */

    public Object peek2() {
        return data[length - 1];
    }
}
