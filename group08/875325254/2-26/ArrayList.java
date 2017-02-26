import java.util.Arrays;

/**
 * Created by Great on 2017/2/25.
 */
public class ArrayList {
    private final int DEFAULT_SIZE = 20;
    int[] array = new int[DEFAULT_SIZE];
    int size = 0;
    public void add(int e) {
        if (size == array.length) {
            array = Arrays.copyOf(array, array.length + DEFAULT_SIZE);
        }
        array[size] = e;
        ++size;
    }

    public Integer get(int i) {
        if(i < 0 || i >= size) return null;
        return array[i];
    }

    public int size() {
        return size;
    }

    public void remove(int i) {
        if (i < 0 || i >= size) return;
        for (int j = i; j < size; j++) {
            array[j] = array[j + 1];
        }
        --size;
    }
    public boolean isEmpty() {
        return size == 0;
    }
}
