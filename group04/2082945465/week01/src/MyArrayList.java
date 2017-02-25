package src;

import java.util.Arrays;

/**
 * Created by Yang on 2/25/2017.
 */
public class MyArrayList implements MyList {

    private int size = 0;

    private Object[] elementData = new Object[100];

    @Override
    public void add(Object obj) {
        this.checkCapacity(size+1);
        elementData[size++] = obj;
    }

    @Override
    public void add(int index, Object obj) {
        this.validIndex(index);
        this.checkCapacity(size+1);
        if(index < size){
            for(int i = size; i > index; i--){
                this.elementData[i] = this.elementData[i-1];
            }
        }else{
            this.elementData[index] = obj;
        }
        this.size++;
    }

    @Override
    public Object get(int index) {
        this.validIndex(index);
        return this.elementData[index];
    }

    @Override
    public Object remove(int index) {
        this.validIndex(index);
        Object o = this.elementData[index];
        for(int i = index; i < this.size-1; i++){
            this.elementData[i] = this.elementData[i+1];
        }
        this.size--;
        return o;
    }

    @Override
    public int size() {
        return this.size;
    }

    private void checkCapacity(int newSize) {
        if(newSize > elementData.length){
            this.extend(elementData);
        }
    }

    private void extend(Object[] oldElementData) {
        int newLength = (int) (oldElementData.length * 1.5);
        elementData = Arrays.copyOf(oldElementData, newLength);
    }

    private void validIndex(int inputIndex) {
        if(inputIndex > size || inputIndex < 0){
            throw new RuntimeException("Index: " + inputIndex + " out of bounds( " + size +" )");
        }
    }

    public boolean isEmpty() {
        if (size() == 0){
            return false;
        }
        return true;
    }
}
