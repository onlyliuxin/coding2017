package com.coding2017.basic.linklist;

import com.coding2017.basic.Iterator;
import com.coding2017.basic.List;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * 功能：实现LinkedList.
 *
 * @author zhanglifeng.
 */
public class LinkedList implements List {
    private Node head, tail;
    private int size;

    private Node getNodeByIndex(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("线性表索引越界");
        }
        Node current = head;
        for (int i = 0; i < size && current != null; i++, current = current.next) {
            if (i == index) {
                return current;
            }
        }
        return null;
    }

    public void add(Object o) {
        addLast(o);
    }

    public void add(int index, Object o) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("线性表索引越界");
        }

        if (0 == index) {
            addFirst(o);
        } else {
            Node node = getNodeByIndex(index - 1);
            node.next = new Node(o, node.next);
            size++;
        }
    }

    public Object get(int index) {
        return getNodeByIndex(index).data;
    }

    public Object remove(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("线性表索引越界");
        }

        if (0 == index) {
            return removeFirst();
        } else if (size - 1 == index) {
            return removeLast();
        } else {
            Node node = getNodeByIndex(index);
            Node preNode = getNodeByIndex(index - 1);
            preNode.next = node.next;
            size--;
            return node.data;
        }
    }

    public int size() {
        return size;
    }

    public void addFirst(Object o) {
        Node currentHead = head;
        Node newNode = new Node(o, currentHead);
        head = newNode;
        if (currentHead == null) {
            tail = newNode;
        }

        size++;
    }

    public void addLast(Object o) {
        Node currentTail = tail;
        Node newNode = new Node(o, null);
        tail = newNode;
        if (currentTail == null) {
            head = newNode;
        } else {
            currentTail.next = newNode;
        }
        size++;
    }

    public Object removeFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node node = new Node(head.data, null);
        head = head.next;
        size--;
        return node.data;
    }

    public Object removeLast() {
        if (tail == null) {
            throw new NoSuchElementException();
        }
        Node node = getNodeByIndex(size - 1);
        node.next = null;
        size--;
        return node.data;
    }

    public Iterator iterator() {
        return new LinkedListIterator(this);
    }

    private static class Node {
        Object data;
        Node next;

        public Node(Object data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    private class LinkedListIterator implements Iterator {
        LinkedList linkedList = null;
        private int current = 0;

        public LinkedListIterator(LinkedList linkedList) {
            this.linkedList = linkedList;
        }

        @Override
        public boolean hasNext() {
            return current < size;
        }

        @Override
        public Object next() {
            return linkedList.get(current++);
        }
    }

    /**
     * 把该链表逆置
     * 例如链表为 3->7->10 , 逆置后变为  10->7->3
     */
    public void reverse() {
        Node first = head;
        Node reverse = null;
        while (first != null) {
            Node second = first.next;
            first.next = reverse;
            reverse = first;
            first = second;
        }
        head = reverse;
    }

    /**
     * 删除一个单链表的前半部分
     * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
     * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10
     */
    public void removeFirstHalf() {
        for (int i = 0; i < size / 2; i++) {
            remove(i);
        }
    }

    /**
     * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
     *
     * @param i
     * @param length
     */
    public void remove(int i, int length) {
        if (size < i + length) {
            throw new IndexOutOfBoundsException("线性表索引越界");
        }
        for (int j = i + length - 1; j >= i; j--) {
            remove(j);
        }
    }

    /**
     * 假定当前链表和list均包含已升序排列的整数
     * 从当前链表中取出那些list所指定的元素
     * 例如当前链表 = 11->101->201->301->401->501->601->701
     * listB = 1->3->4->6
     * 返回的结果应该是[101,301,401,601]
     *
     * @param list
     */
    public int[] getElements(LinkedList list) {
        if (list.size() > size) {
            throw new IndexOutOfBoundsException("线性表索引越界");
        }
        int[] array = new int[list.size];
        for (int i = 0; i < array.length; i++) {
            int element = (int) list.get(i);
            if (element >= size) {
                throw new IndexOutOfBoundsException("线性表索引越界");
            }
            array[i] = ((Integer) get(element));
        }

        System.out.println(Arrays.toString(array));

        return array;
    }

    /**
     * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 从当前链表中中删除在list中出现的元素
     *
     * @param list
     */

    public void subtract(LinkedList list) {
        int length = list.size();
        for (int i = size - 1; i >= 0; i--) {
            for (int j = 0; j < length; j++) {
                if (get(i) == list.get(j)) {
                    remove(i);
                    break;
                }
            }
        }
    }

    /**
     * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
     */
    public void removeDuplicateValues() {
        for (int i = size - 1; i > 0; i--) {
            if (get(i) == get(i - 1)) {
                remove(i);
            }
        }
    }

    /**
     * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
     *
     * @param min
     * @param max
     */
    public void removeRange(int min, int max) {
        for (int i = size - 1; i >= 0; i--) {
            int element = ((int) get(i));
            if ((element > min) && element < max) {
                remove(i);
            }
        }
    }

    /**
     * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
     * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
     *
     * @param list
     */
    public LinkedList intersection(LinkedList list) {
        LinkedList newList = new LinkedList();
        int length = list.size();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < length; j++) {
                if (get(i) == list.get(j)) {
                    newList.add(get(i));
                    break;
                }
            }
        }

        Iterator it = newList.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println();
        return newList;
    }

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.addFirst(0);
        linkedList.addLast(4);

        /*System.out.println("第3个元素：" + linkedList.get(3));

        System.out.println(linkedList.removeFirst());
        System.out.println(linkedList.size());
        System.out.println("Last element:" + linkedList.removeLast());
        System.out.println(linkedList.size());
        System.out.println("第2个元素：" + linkedList.remove(2));*/
        System.out.println(linkedList.size());

        //linkedList.remove(0, 3);
        LinkedList list = new LinkedList();
        list.add(1);
        list.add(3);
        list.add(4);
        //linkedList.getElements(list);
        //linkedList.intersection(list);
        linkedList.reverse();
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println();
    }
}
