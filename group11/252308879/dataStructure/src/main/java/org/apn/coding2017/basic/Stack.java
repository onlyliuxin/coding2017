package org.apn.coding2017.basic;

/**
 * Created by QiPan on 2017/2/23.
 */
public class Stack<E> {

    private E[] elements;
    // 记录元素当前位置
    private int N;

    public Stack(int cap) {
        elements = (E[]) new Object[cap];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void push(E e) {
        // 如果 N 和数组的长度已经相同，就进行扩容
        if (N == elements.length) {
            resize(2 * elements.length);
        }
        elements[N++] = e;
    }

    public E pop() {
        E element = elements[--N];
        elements[N] = null;// 避免对象游离
        // 如果元素值剩下容量的1/4，那么就把数组容量变成现在的一半
        if (N > 0 && N == elements.length / 4) {
            resize(elements.length / 2);
        }
        return element;
    }

    private void resize(int max) {
        E[] elementTmps = (E[]) new Object[max];
        for (int i = 0; i < N; i++) {
            elementTmps[i] = elements[i];
        }
        // 指向扩容的数组
        elements = elementTmps;
    }

}
