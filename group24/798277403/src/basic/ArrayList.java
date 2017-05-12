package basic;

import java.util.Arrays;

/**
 java泛型：
 ？ 表示不确定的java类型。
 T  表示java类型。
 K V 分别代表java键值中的Key Value。
 E 代表Element。

 * 自己实现的ArrayList
 * Created by zhouliang on 2017-03-10.
 */
class ArrayList<E> implements List<E> {

    private Object[] elementData;

    private int size;

    public ArrayList(int size){
        this.elementData = new Object[size];
    }

    //默认大小为10
    public ArrayList(){
        this.elementData = new Object[10];
    }

    //判断是否需要扩展容量  ((旧容量 * 3) / 2) + 1
    private void checkcapacity(int index){
        if(index>elementData.length){
            int length = (elementData.length*3)/2+1;
            /*
            Object[] temp = new Object[length];
            for(int i=0; i<elementData.length; i++){
                temp[i] = elementData[i];
            }
            elementData = temp;
            */
            elementData = Arrays.copyOf(elementData,length);

        }
    }

    @SuppressWarnings("unchecked")
    private E elementData(int index){
        return  (E) elementData[index];
    }

    @Override
    public void add(E e) {
        //确保elementData的长度是否足够大
        checkcapacity(size+1);

        elementData[size]=e;
        size++;
    }

    @Override
    public void add(int index, E e) {

        checkPositionIndex(index);
        //确保elementData的长度是否足够大
        checkcapacity(size+1);

        elementData[index] = e;
    }

    @Override
    public E get(int index) {
        checkElementIndex(index);

        return elementData(index);
    }

    @Override
    public E remove(int index) {
        checkElementIndex(index);

        E e = elementData(index);

        //如果是最后一个元素直接去掉
        if(index == size-1){
            elementData[index] = null;
        }else{
            for(int i=index; i<size; i++){
                elementData[i]=elementData[i+1];
            }
        }
        size--;
        return e;
    }

    @Override
    public int size() {
        return size;
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index)) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: "
                    + size);
        }
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index)) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: "
                    + size);
        }
    }

    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    public Iterator iterator() {
        return new ArrayListIterator(this);
    }

    private class ArrayListIterator implements Iterator {
        private ArrayList<E> list = null;
        private int position = 0;

        private ArrayListIterator(ArrayList<E> list) {
            this.list = list;
        }

        @Override
        public boolean hasNext() {
            if ((position + 1) > size()) {
                return false;
            }
            return true;
        }

        @Override
        public E next() {
            return list.get(position++);
        }
    }
}
