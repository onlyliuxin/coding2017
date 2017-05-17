package com.coding.basic.datastructure;

import com.coding.basic.datastructure.array.ArrayList;
import com.coding.basic.datastructure.linklist.LinkedList;
import com.coding.basic.datastructure.stack.Stack;

import org.junit.Test;

/**
 * Created by zt on 2017/2/19.
 */
public class TestDataStructure {

    @Test
    public void testLinkedList() {
        LinkedList list = new LinkedList();
        for (int i = 0; i < 5; i++) {
            list.add(i);
        }
        list.add(0, -1);
        list.remove(1);
        list.removeLast();
        list.addFirst(999);
        list.removeFirst();
        System.out.println("list size : " + list.size());
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        java.util.LinkedList list1 = new java.util.LinkedList();
        list1.add(0, 2);
        System.out.print(list1.get(0));
    }

    @Test
    public void testStack() {
        Stack stack = new Stack();
        stack.push(1);
        stack.push(2);
        stack.pop();
        System.out.println(stack.size());
        Object obj = stack.peek();
    }

    @Test
    public void testQueue() {
        Queue queue = new Queue();
        queue.enQueue(1);
        queue.enQueue(2);
        queue.enQueue(3);
        Object object = queue.deQueue();
        System.out.println("dqueue object : " + object);
        System.out.println(queue.isEmpty());
        System.out.println(queue.size());
    }

    @Test
    public void testArrayList() {
        List arrayList = new ArrayList();
        for (int i = 0; i < 30; i++) {
            arrayList.add(i);
        }
        arrayList.add(0, -2);
        arrayList.add(1, -1);
        System.out.println(arrayList.remove(1));
        System.out.println("ArrayList size : " + arrayList.size());
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println(arrayList.get(i));
        }
    }

    @Test
    public void testReverseLinkedList() {
        LinkedList linkedList = new LinkedList();
        for (int i = 0; i < 10; i++) {
            linkedList.add(i);
        }
        linkedList.reverse();
        printLinkedList(linkedList);
    }

    @Test
    public void testRemoveLinkedList() {
        LinkedList linkedList = new LinkedList();
        for (int i = 0; i < 10; i++) {
            linkedList.add(i);
        }
        linkedList.remove(0, 8);
        printLinkedList(linkedList);
    }

    @Test
    public void testRemoveFirstHalf() {
        LinkedList linkedList = new LinkedList();
        for (int i = 0; i < 1; i++) {
            linkedList.add(i);
        }
        linkedList.removeFirstHalf();
        printLinkedList(linkedList);
    }

    @Test
    public void testRemoveDuplicateValues() {
        LinkedList linkedList = new LinkedList();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(2);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(4);
        linkedList.removeDuplicateValues();
        printLinkedList(linkedList);
    }

    @Test
    public void testLinkedListIterator() {
        LinkedList linkedList = new LinkedList();
        for (int i = 0; i < 10; i++) {
            linkedList.add(i);
        }
        Iterator iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Test
    public void testGetElements() {
        LinkedList linkedList = new LinkedList();
        linkedList.add(11);
        linkedList.add(101);
        linkedList.add(201);
        linkedList.add(301);
        linkedList.add(401);
        linkedList.add(501);
        linkedList.add(601);
        linkedList.add(701);

        LinkedList indexList = new LinkedList();
        indexList.add(0);
        indexList.add(3);
        indexList.add(4);
        indexList.add(6);

        int[] result = linkedList.getElements(indexList);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }

    @Test
    public void testRemoveRange() {
        LinkedList linkedList = new LinkedList();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(6);
        linkedList.add(6);
        linkedList.removeRange(1, 4);
        printLinkedList(linkedList);
    }

    private void printLinkedList(LinkedList linkedList) {
        for (int i = 0; i < linkedList.size(); i++) {
            System.out.print(linkedList.get(i) + ",");
        }
    }
}
