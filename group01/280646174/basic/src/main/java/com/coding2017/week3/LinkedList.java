package com.coding2017.week3;

import com.coding2017.basic.Iterator;
import com.coding2017.basic.List;

public class LinkedList implements List {

    private Node head;

    private Node tail;

    private int size;

    public LinkedList() {
    }

    public static LinkedList of(Object... objects) {
        LinkedList linkedList = new LinkedList();
        for (int i = 0; i < objects.length; i++) {
            linkedList.add(objects[i]);
        }
        return linkedList;
    }

    public void add(Object o) {
        addLast(o);
    }

    public void add(int index, Object o) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == size) {
            addLast(o);
        } else if (index == 0) {
            addFirst(o);
        } else {
            Node node = new Node(o);
            Node prevNode = getNode(index - 1);
            Node nextNode = prevNode.next;
            prevNode.next = node;
            node.prev = prevNode;
            nextNode.prev = node;
            node.next = nextNode;
            size++;
        }
    }

    private Node getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node node = head;
        for (int j = 0; j < index; j++) {
            node = node.next;
        }
        return node;
    }

    public Object get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return getNode(index).data;
    }

    public Object remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            return removeFirst();
        } else if (index == size - 1) {
            return removeLast();
        } else {
            Node node = getNode(index);
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
            return node.data;
        }
    }

    public int size() {
        return size;
    }

    public void addFirst(Object o) {
        Node node = new Node(o);
        if (size == 0) {
            head = node;
            tail = node;
        } else {
            head.prev = node;
            node.next = head;
            head = node;
        }
        size++;
    }

    public void addLast(Object o) {
        if (size == 0) {
            addFirst(o);
        } else {
            Node node = new Node(o);
            tail.next = node;
            node.prev = tail;
            tail = node;
            size++;
        }
    }

    public Object removeFirst() {
        if (size == 0) {
            throw new IndexOutOfBoundsException();
        }
        Node node = head;
        if (size == 1) {
            head = null;
            tail = null;
            size--;
        } else {
            head.next.prev = null;
            head = head.next;
            size--;
        }
        return node.data;
    }

    public Object removeLast() {
        if (size == 0) {
            throw new IndexOutOfBoundsException();
        }
        if (size == 1) {
            return removeFirst();
        }
        Node node = tail;
        tail.prev.next = null;
        tail = tail.prev;
        size--;
        return node.data;
    }

    public Iterator iterator() {
        return new LinkedListIterator();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        if (size > 0) {
            builder.append(get(0));
        }
        for (Node node = head.next; node != null; node = node.next) {
            builder.append(", ").append(node.data);
        }
        builder.append("]");
        return builder.toString();
    }

    private static class Node {
        private Object data;
        private Node next;
        private Node prev;

        public Node() {
        }

        private Node(Object data) {
            this.data = data;
        }
    }

    private class LinkedListIterator implements Iterator {
        private Node node;

        public LinkedListIterator() {
            this.node = LinkedList.this.head;
        }

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public Object next() {
            Node temp = node;
            node = node.next;
            return temp.data;
        }
    }

    /******** 使用单向链表实现下边功能 *************/
    /**
     * 把该链表逆置 例如链表为 3->7->10 , 逆置后变为 10->7->3
     */
    public void reverse() {
        if (size == 0 || size == 1) {
            return;
        }

        Node preNode = null;
        Node curNode = head;

        while (curNode != null) {
            Node nextNode = curNode.next;
            curNode.next = preNode;
            preNode = curNode;
            curNode = nextNode;
        }
        head = preNode;
    }

    /**
     * 删除一个单链表的前半部分 例如：list = 2->5->7->8 , 删除以后的值为 7->8 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10
     * 
     */
    public void removeFirstHalf() {
        if (size == 0 || size == 1) {
            return;
        }

        remove(0, size / 2);
    }

    /**
     * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
     * 
     * @param index
     * @param length
     */
    public void remove(int index, int length) {
        assert index >= 0 && index < size;
        assert index + length <= size;

        if (index == 0) {
            for (int i = 0; i < length; i++) {
                head = head.next;
            }
        } else {
            Node preNode = getNode(index - 1);
            preNode.next = getNode(index + length);
        }
        size -= length;
    }

    /**
     * 假定当前链表和list均包含已升序排列的整数 从当前链表中取出那些list所指定的元素 例如当前链表 = 11->101->201->301->401->501->601->701 listB = 1->3->4->6
     * 返回的结果应该是[101,301,401,601]
     * 
     * @param list
     */
    public int[] getElements(LinkedList list) {
        int resultSize = list.size;
        int[] result = new int[resultSize];
        Node node = head;
        Node indexNode = list.head;
        int preIndex = 0;
        for (int i = 0; i < resultSize; i++) {
            node = forward(node, (Integer) indexNode.data - preIndex);
            result[i] = (Integer) node.data;
            preIndex = (Integer) indexNode.data;
            indexNode = indexNode.next;
        }

        return result;
    }

    /**
     * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。 从当前链表中中删除在list中出现的元素
     * 
     * @param list
     */
    public void subtract(LinkedList list) {
        Node preNode = null;
        Node node = head;
        Node deleteNode = list.head;
        while (node != null && deleteNode != null) {
            int data = (Integer) node.data;
            int deleteData = (Integer) deleteNode.data;
            if (data == deleteData) {
                if (node == head) {
                    head = head.next;
                    node = node.next;
                } else {
                    preNode.next = node.next;
                    node = node.next;
                }
                size--;
            } else if (data > deleteData) {
                deleteNode = deleteNode.next;
            } else {
                preNode = node;
                node = node.next;
            }
        }
    }

    /**
     * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
     */
    public void removeDuplicateValues() {
        if (size == 0 || size == 1) {
            return;
        }

        Node curNode = head; // 当前节点
        Node nextNode = head.next; // 查找与当前节点不同的节点
        while (curNode != null) {
            if (nextNode == null) {
                curNode.next = null;
                break;
            }
            if (curNode.data.equals(nextNode.data)) {
                nextNode = nextNode.next;
                size--;
            } else {
                curNode.next = nextNode;
                curNode = nextNode;
                nextNode = curNode.next;
            }
        }
    }

    /**
     * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
     * 
     * @param min
     * @param max
     */
    public void removeRange(int min, int max) {
        // 小于等于min的节点, 假定head也大于min
        Node preNode = new Node(null);
        preNode.next = head;

        Node nextNode = null;

        for (Node node = head; node != null; node = node.next) {
            if ((Integer) node.data <= min) {
                preNode = node;
            } else if ((Integer) node.data >= max) {
                nextNode = node;
                break;
            }
        }
        preNode.next = nextNode;
        head = nextNode;
        setSize();
    }

    /**
     * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同） 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
     * 
     * @param list
     */
    public LinkedList intersection(LinkedList list) {

        LinkedList result = new LinkedList();
        Node node1 = head;
        Node node2 = list.head;
        while (node1 != null && node2 != null) {
            int data1 = (Integer) node1.data;
            int data2 = (Integer) node2.data;

            if (data1 == data2) {
                result.add(node1.data);
                node1 = node1.next;
                node2 = node2.next;
            } else if (data1 < data2) {
                node1 = node1.next;
            } else {
                node2 = node2.next;
            }
        }
        return result;
    }

    private Node forward(Node node, int step) {
        for (int i = 0; i < step; i++) {
            node = node.next;
        }
        return node;
    }

    private void setSize() {
        int size = 0;
        for (Node node = head; node != null; node = node.next) {
            size++;
        }
        this.size = size;
    }
}
