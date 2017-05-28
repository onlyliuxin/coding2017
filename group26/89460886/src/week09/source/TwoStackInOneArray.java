package stack;

import java.util.Arrays;

/**
 * @author jiaxun
 */
public class TwoStackInOneArray {

    private Object[] data;
    private int stackOneTop;
    private int stackTwoTop;
    private int increment;

    public TwoStackInOneArray() {
        this(10);
    }

    public TwoStackInOneArray(int capacity) {
        this(capacity, 0);
    }

    public TwoStackInOneArray(int capacity, int increment) {
        this.data = new Object[capacity];
        this.stackOneTop = 0;
        this.stackTwoTop = capacity - 1;
        this.increment = increment;
    }

    public void push1(Object object){
        ensureCapacity();
        data[stackOneTop++] = object;

    }

    public Object pop1(){
        return data[--stackOneTop];
    }

    public Object peek1(){
        return data[stackOneTop - 1];
    }

    public void push2(Object object){
        ensureCapacity();
        data[stackTwoTop--] = object;
    }

    public Object pop2(){
        return data[++stackTwoTop];
    }

    public Object peek2(){
        return data[stackTwoTop + 1];
    }

    public Object[] stack1ToArray() {
        return Arrays.copyOf(data, stackOneTop);
    }

    public Object[] stack2ToArray() {
        Object[] result = new Object[data.length - stackTwoTop - 1];
        int secondIndex = data.length - 1;
        for (int i = 0, len = result.length; i < len; i++) {
            result[i] = data[secondIndex--];
        }
        return result;
    }

    private void ensureCapacity() {
        if (stackOneTop == stackTwoTop) {
            int newCapacity = increment == 0 ? data.length * 2 : data.length + increment;
            Object[] first = Arrays.copyOfRange(data, 0, stackOneTop);
            Object[] second = Arrays.copyOfRange(data, stackTwoTop + 1, data.length);
            data = new Object[newCapacity];
            for (int i = 0, len = first.length; i < len; i++) {
                data[i] = first[i];
            }
            int secondIndex = newCapacity - 1;
            for (int i = second.length - 1; i >= 0; i--) {
                data[secondIndex--] = second[i];
            }
            this.stackTwoTop = secondIndex;
        }
    }
}
