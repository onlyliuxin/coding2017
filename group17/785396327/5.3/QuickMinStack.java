import java.util.Arrays;

/**
 * 设计一个栈，支持栈的push和pop操作，以及第三种操作findMin, 它返回改数据结构中的最小元素
 * finMin操作最坏的情形下时间复杂度应该是O(1) ， 简单来讲，操作一次就可以得到最小值
 *
 * @author william
 */
public class QuickMinStack {
    private static final int DEFAULT_SIZE = 10;
    private Integer[] elements = new Integer[DEFAULT_SIZE];
    private int size = 0;
    private Integer min = null;

    public void push(int data) {
        int newCapacity = elements.length;
        if (min == null || data < min)
            min = data;
        if (size == elements.length) {
            if (size > Integer.MAX_VALUE / 2)
                newCapacity = Integer.MAX_VALUE;
            else
                newCapacity = newCapacity << 1;
        }
        Integer[] newElements = new Integer[newCapacity];
        System.arraycopy(elements, 0, newElements, 0, size);

        newElements[size++] = data;
        elements = newElements;
    }

    public int pop() {
        if (size == 0)
            throw new RuntimeException("empty stack");
        Integer removeEle = elements[0];
        elements = Arrays.copyOfRange(elements, 1, size);
        if (min == removeEle) {
            for (int i = 0; i < elements.length; i++) {
                min = min > elements[i] ? elements[i] : min;
            }
        }
        return removeEle;
    }

    public int findMin() {
        return min;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[ ");
        for (int i = 0; i < elements.length; i++) {
            sb.append(elements[i]).append(", ");
        }
        return sb.substring(0, sb.length() - 2) + " ]";
    }
}
