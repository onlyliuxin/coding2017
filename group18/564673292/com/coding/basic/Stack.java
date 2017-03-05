package com.coding.basic;

public class Stack<E>{
    private ArrayList<E> arrayList;

    // constructor
    public Stack(){
        arrayList = new ArrayList<E>();
    }

    public void push(E o){
        arrayList.add(o);
    }
    
    public E pop(){
        return arrayList.remove(arrayList.size() - 1);
    }
    
    public E peek(){
        return arrayList.get(arrayList.size() - 1);
    }

    public boolean isEmpty(){
        return arrayList.size() == 0 ? true: false;
    }
    
    public int size(){
        return arrayList.size();
    }

    // public Iterator<E> iterator(){
    //     return new Itr();
    // }

    // private class Itr implements Iterator<E>{
    //     Iterator<E> arrayListItr = arrayList.iterator();
    //     public boolean hasNext(){
    //         return arrayListItr.hasNext();
    //     }

    //     public E next(){
    //         return arrayListItr.next();
    //     }

    //     @Override // Stack iterator can only remove the last element
    //     public E remove(){
    //         return arrayList.pop();
    //     }
    // }
}