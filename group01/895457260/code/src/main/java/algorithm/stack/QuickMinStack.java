package algorithm.stack;

import datastructure.basic.Iterator;
import datastructure.basic.LinkedList;

import java.util.EmptyStackException;

/**
 * 设计一个栈，支持栈的push和pop操作，以及第三种操作findMin, 它返回改数据结构中的最小元素
 * finMin操作最坏的情形下时间复杂度应该是O(1) ， 简单来讲，操作一次就可以得到最小值
 *
 * @author liuxin
 */
public class QuickMinStack {
    private LinkedList elements = new LinkedList();
    private LinkedList sorted = new LinkedList();

    public void push(int data) {
        elements.addFirst(data);
        insert(sorted, data);
    }

    private void insert(LinkedList sorted, int data) {
        Iterator iterator = sorted.iterator();

        int insertPos = 0;
        while (iterator.hasNext()) {
            if ((int) iterator.next() >= data) {
                break;
            }
            insertPos++;
        }
        sorted.add(insertPos, data);
    }

    public int pop() {
        if (elements.isEmpty()) {
            throw new EmptyStackException();
        }
        int peek = (int) elements.removeFirst();
        sorted.remove(sorted.indexOf(peek));
        return peek;
    }

    public int findMin() {
        return (int) sorted.get(0);
    }
}
