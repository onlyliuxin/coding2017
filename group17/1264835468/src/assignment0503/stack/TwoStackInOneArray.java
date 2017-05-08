package assignment0503.stack;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * 用一个数组实现两个栈
 * 将数组的起始位置看作是第一个栈的栈底，将数组的尾部看作第二个栈的栈底，压栈时，栈顶指针分别向中间移动，直到两栈顶指针相遇，则扩容。
 *
 * @author liuxin
 */
public class TwoStackInOneArray {
    Object[] data = new Object[10];
    int top1 = -1;
    int top2 = 10;

    /**
     * 向第一个栈中压入元素
     *
     * @param o
     */
    public void push1(Object o) {
        ensureCapacity();
        data[++top1] = o;
    }

    /**
     * 从第一个栈中弹出元素
     *
     * @return
     */
    public Object pop1() {
        if (top1 == 0) {
            throw new EmptyStackException();
        }
        Object o = peek1();
        data[top1] = null;
        top1--;
        return o;
    }

    /**
     * 获取第一个栈的栈顶元素
     *
     * @return
     */

    public Object peek1() {
        if (top1 == 0) {
            throw new EmptyStackException();
        }
        return data[top1];
    }

    /*
     * 向第二个栈压入元素
     */
    public void push2(Object o) {
        ensureCapacity();
        data[--top2] = o;
    }

    /**
     * 从第二个栈弹出元素
     *
     * @return
     */
    public Object pop2() {
        if (top2 == data.length - 1) {
            throw new EmptyStackException();
        }
        Object o = peek2();
        data[top2] = null;
        top2++;
        return o;
    }

    /**
     * 获取第二个栈的栈顶元素
     *
     * @return
     */

    public Object peek2() {
        if (top2 == data.length - 1) {
            throw new EmptyStackException();
        }
        return data[top2];
    }

    private void ensureCapacity() {
        if (top1 == top2 - 1) {
            int size2 = data.length - top2;
            data = Arrays.copyOf(data, data.length * 2);
            System.arraycopy(data, top2, data, data.length - size2, size2);
            top2 = data.length - size2;
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (top1 == 0) {
            stringBuilder.append("[]");
        } else {
            stringBuilder.append("[");
            for (int i = top1; i >= 0; i--) {
                stringBuilder.append(data[i] + ", ");
            }
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
            stringBuilder.append("]");
        }
        if (top2 == data.length - 1) {
            stringBuilder.append("[]");
        } else {
            stringBuilder.append(" [");
            for (int i = top2; i < data.length; i++) {
                stringBuilder.append(data[i] + ", ");
            }
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
            stringBuilder.append("]");
        }

        return stringBuilder.toString();
    }
}
