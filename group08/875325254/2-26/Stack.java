import java.util.Arrays;

/**
 * Created by Great on 2017/2/23.
 */
public class Stack {
    private final int DEFAULT_SIZE = 20;
    private int base;
    private int top;
    private int[] array = new int[DEFAULT_SIZE];
    public void push(Integer e) {
        if (top == array.length){
            array = Arrays.copyOf(array, array.length + DEFAULT_SIZE);
        }
        array[top] = e;
        ++top;
    }
    public Integer pop(){
        if (top == 0) return null;
        top--;
        return array[top];
    }
    public int size() {
        return top;
    }
    public boolean isEmpty(){
        return top == 0;
    }
}
