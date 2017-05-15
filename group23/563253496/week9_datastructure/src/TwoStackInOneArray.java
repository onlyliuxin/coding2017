/**
 * Created by bdl19 on 2017/5/6.
 */
public class TwoStackInOneArray {
    Object[] data = new Object[10];

    private int index1 = 0;
    private int index2 = data.length - 1;

    private boolean isFull() {
        if (index1 == index2) {
            return true;
        } else return false;
    }

    private void extendCapacity() {
        Object[] temp = new Object[data.length + data.length / 2];
        for (int i = 0; i <= index1; i++) {
            temp[i] = data[i];
        }
        int indext = data.length - 1;
        for (int i = temp.length - 1; i >= data.length - index2; i--) {
            temp[i] = indext;
            indext--;
        }
        index2 = temp.length - (temp.length - index1);
        this.data = temp;
    }

    /**
     * 向第一个栈中压入元素
     *
     * @param o
     */
    public void push1(Object o) {
        if (this.isFull()) {
            extendCapacity();
        }
        data[index1] = o;
        index1++;

    }

    /**
     * 从第一个栈中弹出元素
     *
     * @return
     */
    public Object pop1() {
        index1--;
        Object o = data[index1];
        data[index1] = null;
        return o;
    }

    /**
     * 获取第一个栈的栈顶元素
     *
     * @return
     */

    public Object peek1() {
        return data[index1 - 1];
    }

    /*
     * 向第二个栈压入元素
     */
    public void push2(Object o) {
        if (this.isFull()) {
            extendCapacity();
        }
        data[index2] = o;
        index2--;
    }

    /**
     * 从第二个栈弹出元素
     *
     * @return
     */
    public Object pop2() {

        Object o = data[++index2];
        data[index2] = null;
        return o;
    }

    /**
     * 获取第二个栈的栈顶元素
     *
     * @return
     */

    public Object peek2() {

        return data[index2 + 1];
    }

}