package com.coding.basic;

public class ArrayList<E> implements List<E>, Iterable<E>{
    private E[] array;
    private int lastIndex;
    private int length;
    //Constructor
    @SuppressWarnings("unchecked")
    public ArrayList(){
        length = 10;
        array = (E[])new Object[length];
        lastIndex = 0;
    }

    public void add(E object){
        if(lastIndex == length){
            this.grow();
        }
        array[lastIndex] = object;
        lastIndex++;
    }

    @SuppressWarnings("unchecked")
    private void grow(){
        E[] tempArray = (E[])new Object[length + 10];
        System.arraycopy(array, 0, tempArray, 0, length);
        array = tempArray;
        length = length + 10;
    }

    public void insert(int index, E o) {
        if(index > lastIndex - 1) throw new IndexOutOfBoundsException();
        for (int i = lastIndex; i > index; i--) {
            array[i] = array[i - 1];
        }
        array[index] = o;
        length++;
    }
    
    public E get(int index) {
        if(index > lastIndex - 1) throw new IndexOutOfBoundsException();
        return array[index];
    }
    
    public E remove(int index){
        if(index > lastIndex - 1) throw new IndexOutOfBoundsException();
        E removed = array[index];
        for (int i = index; i < lastIndex - 1; i++) {
            array[i] = array[i + 1];
        }
        lastIndex--;
        array[lastIndex] = null;
        return removed;
    }
    
    public int size(){
        return lastIndex;
    }

    public Iterator<E> iterator(){
        return new Itr(this);
    }

    private class Itr implements Iterator<E>{
        private int itrCurIndex;
        private ArrayList arrayList;
        // constructor
        public Itr(ArrayList arrayList){
            this.arrayList = arrayList;
            itrCurIndex = -1;
        }

        public boolean hasNext(){
            return (itrCurIndex + 1) > lastIndex - 1 ? false: true;
        }

        @SuppressWarnings("unchecked")
        public E next(){
            if(this.hasNext()){
                return (E)this.arrayList.get(++itrCurIndex);
            }else{
                itrCurIndex = -1;
                return null;
            }
        }

        @SuppressWarnings("unchecked")
        public E remove(){
            return (E)this.arrayList.remove(itrCurIndex);
        }
    }
}