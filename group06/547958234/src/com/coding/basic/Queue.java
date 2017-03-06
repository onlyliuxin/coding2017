package com.coding.basic;
import java.util.Binary

import com.coding.basic.LinkedList;
public class Queue {
    private LinkedList list = new LinkedList();
    public void enQueue(Object o) {
        list.addLast(o);
    }

    public Object deQueue() {
        return list.removeFirst();
    }

    public boolean isEmpty() {
        if (list.size() == 0) {
            return true;
        }
        return false;
    }

    public int size() {
        return list.size();
    }

}
