import java.util.Arrays;

/**
 * 用一个数组实现两个栈
 * 将数组的起始位置看作是第一个栈的栈底，将数组的尾部看作第二个栈的栈底，压栈时，栈顶指针分别向中间移动，直到两栈顶指针相遇，则扩容。
 *
 * @author william
 */
public class TwoStackInOneArray {
    Object[] data = new Object[10];
    private int size1 = 0;
    private int size2 = 0;

    /**
     * 向第一个栈中压入元素
     *
     * @param o
     */
    public void push1(Object o) {
        grow();
        data[size1++] = o;
    }

    private void grow() {
        if (size1 + size2 == data.length) {
            int newCapacity = (size1 + size2) << 1;
            Object[] newArray = new Object[newCapacity];
            //拷贝第一个栈
            System.arraycopy(data, 0, newArray, 0, size1);
            //拷贝第二个栈
            System.arraycopy(data, data.length - size2, newArray, newArray.length - size2, size2);
            data = newArray;
        }
    }

    /**
     * 从第一个栈中弹出元素
     *
     * @return
     */
    public Object pop1() {
        Object removeEle = data[--size1];
        data[size1] = null;
        return removeEle;
    }

    /**
     * 获取第一个栈的栈顶元素
     *
     * @return
     */

    public Object peek1() {
        return data[size1 - 1];
    }

    /*
     * 向第二个栈压入元素
     */
    public void push2(Object o) {
        grow();
        data[data.length - ++size2] = o;
    }

    /**
     * 从第二个栈弹出元素
     *
     * @return
     */
    public Object pop2() {
        Object removeEle = data[data.length - size2];
        data[data.length - size2--] = null;
        return removeEle;
    }

    /**
     * 获取第二个栈的栈顶元素
     *
     * @return
     */

    public Object peek2() {
        return data[data.length - size2];
    }

    @Override
    public String toString() {
        return Arrays.toString(data);
    }
}
