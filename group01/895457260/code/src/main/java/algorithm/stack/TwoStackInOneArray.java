package algorithm.stack;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * 用一个数组实现两个栈
 * 将数组的起始位置看作是第一个栈的栈底，将数组的尾部看作第二个栈的栈底，压栈时，栈顶指针分别向中间移动，直到两栈顶指针相遇，则扩容。
 *
 * @author liuxin
 */
public class TwoStackInOneArray {
    private Object[] data = new Object[10];

    private int top1 = 0;
    private int top2 = data.length - 1;

    /**
     * 向第一个栈中压入元素
     *
     * @param o
     */
    public void push1(Object o) {
        ensureCapacity();
        data[top1++] = o;
    }

    /**
     * 从第一个栈中弹出元素
     *
     * @return
     */
    public Object pop1() {
        if (top1 <= 0) {
            throw new EmptyStackException();
        }
        return data[--top1];
    }

    /**
     * 获取第一个栈的栈顶元素
     *
     * @return
     */
    public Object peek1() {
        if (top1 <= 0) {
            throw new EmptyStackException();
        }
        return data[top1 - 1];
    }

    public int size1() {
        return top1 - 1;
    }

    /*
     * 向第二个栈压入元素
     */
    public void push2(Object o) {
        ensureCapacity();
        data[top2--] = o;
    }

    /**
     * 从第二个栈弹出元素
     *
     * @return
     */
    public Object pop2() {
        if (top2 >= data.length - 1) {
            throw new EmptyStackException();
        }
        return data[++top2];
    }

    /**
     * 获取第二个栈的栈顶元素
     *
     * @return
     */
    public Object peek2() {
        if (top2 >= data.length - 1) {
            throw new EmptyStackException();
        }
        return data[top2 + 1];
    }

    public int size2() {
        return data.length - top2 - 1;
    }

    private void ensureCapacity() {
        if (top1 > top2) {
            int nextCapacity = nextCapacity(data.length);
            Object[] newArray = new Object[nextCapacity];

            System.arraycopy(data, 0, newArray, 0, size1());

            int size2 = size2();
            int srcPos = data.length - size2;
            int destPos = nextCapacity - size2;
            System.arraycopy(data, srcPos, newArray, destPos, size2);
            top2 = destPos - 1;

            Arrays.fill(data, null);
            data = newArray;
        }
    }

    private int nextCapacity(int currentCapacity) {
        return currentCapacity * 2;
    }

}
