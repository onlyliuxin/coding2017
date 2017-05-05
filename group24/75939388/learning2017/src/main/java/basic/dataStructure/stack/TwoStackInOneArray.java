package basic.dataStructure.stack;

/**
 * 用一个数组实现两个栈
 * 将数组的起始位置看作是第一个栈的栈底，将数组的尾部看作第二个栈的栈底，
 * 压栈时，栈顶指针分别向中间移动，直到两栈顶指针相遇，则扩容。
 *
 * @author liuxin
 */
public class TwoStackInOneArray {
    Object[] data = new Object[10];

    private int index1 = 0;
    private int index2 = 9;

    private int size1 = 0;
    private int size2 = 0;

    /**
     * 向第一个栈中压入元素
     *
     * @param o
     */
    public void push1(Object o) {
        data[index1] = o;
        this.index1++;
        this.size1 += 1;

        if (size1 + size2 >= data.length) extend();
    }

    /**
     * 从第一个栈中弹出元素
     *
     * @return
     */
    public Object pop1() {
        int index = index1 - 1;
        Object obj = data[index];
        //直接置空
        data[index] = null;
        this.index1 = index;
        this.size1--;
        return obj;
    }

    /**
     * 获取第一个栈的栈顶元素
     *
     * @return
     */

    public Object peek1() {
        int index = index1 - 1;
        Object obj = data[index];

        return obj;
    }

    /*
     * 向第二个栈压入元素
     */
    public void push2(Object o) {
        data[index2] = o;
        index2--;
        this.size2 += 1;

        if (size1 + size2 >= data.length) extend();
    }

    /**
     * 从第二个栈弹出元素
     *
     * @return
     */
    public Object pop2() {
        int index = index2 + 1;
        Object obj = data[index];

        data[index] = null;
        this.index2 = index;
        this.size2--;
        return obj;
    }

    /**
     * 获取第二个栈的栈顶元素
     *
     * @return
     */

    public Object peek2() {
        int index = index2 + 1;
        Object obj = data[index];
        return obj;
    }

    public int size1() {
        return this.size1;
    }

    public int size2() {
        return this.size2;
    }

    /**
     * 扩容
     */
    private void extend() {
        int size = data.length;
        int extendSize = 10;
        Object[] extended = new Object[size + extendSize];

        //stack1
        //因为指针在数据添加后已经加上了，所以要减回去
        for (int i = 0; i <= index1 - 1; i++) {
            extended[i] = data[i];
        }

        //stack2
        //因为指针在添加后已经减掉了1，所以要加回去
        for (int i = index2 + 1; i <= size - 1; i++) {
            int index = i + extendSize;
            extended[index] = data[i];
        }
        this.data = extended;
        this.index2 += extendSize;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("stack1 -> [");
        for (int i = index1 - 1; i >= 0; i--) {
            sb.append(data[i]);
            if (i == 0) {
                sb.append("]");
            } else {
				sb.append(",");
            }
        }

        sb.append("\nstack2 -> [");
        int length = data.length;
        for (int i = index2 + 1; i < length; i++) {
            sb.append(data[i]);
            if (i == length - 1){
                sb.append("]");
            } else {
                sb.append(",");
            }
        }
        return sb.toString();
    }
}