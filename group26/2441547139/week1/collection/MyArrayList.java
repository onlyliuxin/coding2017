package week1.collection;

import java.util.Arrays;
/**
 * Created by zndbl on 2017/3/11.
 */
public class MyArrayList {

    private int size;
    private Object[] arr;

    public MyArrayList() {
        this(10);
    }

    public MyArrayList(int oldLength) {
        if(oldLength < 0) {
            throw new RuntimeException("创建集合失败");
        }
        arr = new Object[oldLength];
    }

    public int size() {
        return size;
    }

    /**
     * 数组的长度扩充策略
     */
    public void ensureCapacity(int minCapatity ) {
        int oldCapacity = arr.length;
        if(minCapatity > oldCapacity) {
            int newCapatity = 3 * oldCapacity / 2 + 1;
            if(minCapatity > newCapatity) {
                newCapatity = minCapatity;
            }
            arr = Arrays.copyOf(arr,newCapatity);
        }
    }

    public void add(Object element) {
        ensureCapacity(size+1);
        arr[size++] = element;
    }

    public void add(int index, Object element) {
        if(index < 0 || index > size) {
            throw new RuntimeException("数组越界");
        }
        ensureCapacity(size+1);
        System.arraycopy(arr,index,arr,index+1,size-index);
        arr[index] = element;
        size++;
    }

    public boolean remove(Object o) {
        for(int i=0; i<size; i++) {
            if(arr[i].equals(o)) {
                int num = size - i - 1;
                System.arraycopy(arr, i+1, arr, i, num);
            }
        }
        return true;
    }
}
