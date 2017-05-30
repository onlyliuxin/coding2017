package task9.stack;

import java.util.*;

public class QuickMinStack<T extends Comparable<T>> {

    private List<T> magicList = new ArrayList<>();

    private Stack<T> dataStack = new Stack<>();

    public void push(T data) {
        dataStack.push(data);
        magicList.add(data);
        magicList.sort(Comparator.naturalOrder());
    }

    public T pop() {
        T result = dataStack.pop();
        magicList.remove(result);
        return result;
    }

    public T findMin() {
        T minResult = magicList.get(0);
        dataStack.remove(minResult); // sad
        return minResult;
    }
}
