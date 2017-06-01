package stack;

import java.util.Arrays;

/**
 * @author jiaxun
 */
public class QuickMinStack {

    private int increment;
    private int[] elementData;
    private int[] minElementData;
    private int elementCount = 0;
    private int minElementCount = 0;

    public QuickMinStack() {
        this(10);
    }

    public QuickMinStack(int capacity) {
        this(capacity, 0);
    }

    public QuickMinStack(int capacity, int increment) {
        if (capacity < 0) throw new IllegalArgumentException("capacity must more zero");
        this.elementData = new int[capacity];
        this.minElementData = new int[capacity];
        this.increment = increment;
    }

    public void push(int data) {
        ensureCapacity(elementCount + 1);
        ensureMinCapacity(minElementCount + 1);
        if (minElementCount == 0 || minElementData[minElementCount - 1] >= data) {
            minElementData[minElementCount++] = data;
        }
        elementData[elementCount++] = data;
    }

    public int pop() {
        if (elementCount > 0) {
            int value = elementData[--elementCount];
            if (value == minElementData[minElementCount - 1]) {
                minElementCount--;
            }
            return value;
        }
        return -1;
    }

    public int findMin() {
        return minElementData[minElementCount - 1];
    }

    private void ensureCapacity(int capacity) {
        if (capacity > elementData.length) {
            int newCapacity = increment > 0 ? elementData.length + increment : elementData.length * 2;
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }

    private void ensureMinCapacity(int capacity) {
        if (capacity > minElementData.length) {
            int newCapacity = increment > 0 ? minElementData.length + increment : minElementData.length * 2;
            minElementData = Arrays.copyOf(minElementData, newCapacity);
        }
    }

}
