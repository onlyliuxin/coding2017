package me.lzb.basic.stack;

/**
 * 用一个数组实现两个栈
 * 将数组的起始位置看作是第一个栈的栈底，将数组的尾部看作第二个栈的栈底，压栈时，栈顶指针分别向中间移动，直到两栈顶指针相遇，则扩容。
 */
public class TwoStackInOneArray {
    private final int growsize = 10;

    private Object[] data = new Object[growsize];

    private int point1 = 0;

    private int point2 = data.length - 1;


    /**
     * 向第一个栈中压入元素
     *
     * @param o
     */
    public void push1(Object o) {
        grow();
        data[point1] = o;
        point1 = point1 + 1;
    }

    /**
     * 从第一个栈中弹出元素
     *
     * @return
     */
    public Object pop1() {
        if (isEmpty1()) {
            throw new RuntimeException("stack1 is empty");
        }

        Object o = data[point1 - 1];
        point1 = point1 - 1;

        return o;
    }

    /**
     * 获取第一个栈的栈顶元素
     *
     * @return
     */

    public Object peek1() {
        if (isEmpty1()) {
            throw new RuntimeException("stack1 is empty");
        }
        return data[point1 - 1];
    }

    /*
     * 向第二个栈压入元素
     */
    public void push2(Object o) {
        grow();
        data[point2] = o;
        point2 = point2 - 1;
    }

    /**
     * 从第二个栈弹出元素
     *
     * @return
     */
    public Object pop2() {
        if (isEmpty2()) {
            throw new RuntimeException("stack2 is empty");
        }
        Object o = data[point2 + 1];
        point2 = point2 + 1;
        return o;
    }

    /**
     * 获取第二个栈的栈顶元素
     *
     * @return
     */

    public Object peek2() {
        if (isEmpty2()) {
            throw new RuntimeException("stack2 is empty");
        }
        return data[point2 + 1];
    }


    public boolean isEmpty1() {
        return size1() <= 0;
    }

    public boolean isEmpty2() {
        return size2() <= 0;
    }

    public int size1() {
        return point1;
    }

    public int size2() {
        return data.length - 1 - point2;
    }


    private void grow() {

        if (point2 - point1 > 0) {
            return;
        }

        Object[] d = new Object[data.length + growsize];

        System.arraycopy(data, 0, d, 0, size1());

        System.arraycopy(data, point2 + 1, d, d.length - size2(), size2());
        point2 = d.length - 1 - size2();
        data = d;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        if (!isEmpty1()) {
            for (int i = 0; i < point1; i++) {
                if (i > 0) {
                    sb.append(",");
                }
                sb.append(data[i].toString());
            }
        }
        sb.append("]");

        sb.append("|");

        sb.append("[");
        if (!isEmpty2()) {
            for (int i = data.length - 1; i > point2; i--) {
                if (i < data.length - 1) {
                    sb.append(",");
                }

                sb.append(data[i].toString());
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
