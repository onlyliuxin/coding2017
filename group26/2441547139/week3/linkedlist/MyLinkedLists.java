package week3.linkedlist;

import java.util.Iterator;

/**
 * Created by zndbl on 2017/3/29.
 */
public class MyLinkedLists {

    private Node head;
    private int size;

    /**
     * 删除第一个
     *
     * @return
     */
    public Node removeFirst() {
        Node node = head;
        head = node.getNext();
        size--;
        return node;
    }

    /**
     * 删除最后一个
     *
     * @return
     */
    public Node removeLast() {
        Node node = head;
        Node pre = null;
        while (node != null) {
            pre = node;
            node = node.getNext();
        }
        pre.setNext(null);
        size--;
        return pre;
    }

    /**
     * 新增节点在第一个
     *
     * @param data
     */
    public void addFirst(Object data) {
        Node node = new Node(data);
        node.setNext(head);
        head = node;
        size++;
    }

    /**
     * 得到指定索引的节点
     *
     * @param index
     * @return
     */
    public Node getNode(int index) {
        index++;
        Node node = null;
        for (int i = 0; i < index; i++) {
            node = head;
            node = node.getNext();
        }
        return head;
    }

    /**
     * 删除指定索引的节点
     *
     * @param index
     * @return
     */
    public Node removeNode(int index) {
        Node prevNode = getNode(index--);
        Node currNode = getNode(index);
        Node succNode = currNode.getNext();
        prevNode.setNext(succNode);
        currNode = null;
        size--;
        return succNode;
    }

    /**
     * 在最后添加一个节点
     *
     * @return
     */
    public Node addLast(Object data) {
        Node node = new Node(data);
        Node curr = head;
        Node succ = null;
        while (curr != null) {
            succ = curr;
            curr = curr.getNext();
        }
        succ.setNext(node);
        size++;
        return node;
    }

    /**
     * 在指定索引增加
     *
     * @param index
     * @param obj
     */
    public void add(int index, Object obj) {
        Node curr = head;
        Node prev = null;
        while (curr != null) {
            if (index == 0) {
                break;
            }
            prev = curr;
            curr = curr.getNext();
            index--;
        }
        if (prev != null) {
            Node node = new Node(obj);
            node.setNext(curr);
            prev.setNext(node);
        }
    }

    /**
     * 得到大小
     *
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * 得到迭代器
     *
     * @return
     */
    public Iterator iterator() {
        return new MyLinkedListsIterator(this);
    }

    /**
     * 反转节点
     */
    public void reverse() {
        Node prev = null;
        Node next = null;
        Node curr = head;
        while (curr != null) {
            next = curr.getNext();
            curr.setNext(prev);
            prev = curr;
            curr = next;
        }
        head = prev;
    }

    /**
     * 链表头一半删除
     */
    public void removeFirstHalf() {
        Node curr = head;
        int half = size / 2;
        while (half != 0) {
            curr = curr.getNext();
            half--;
        }
        head = curr;
    }

    /**
     * 指定索引，指定长度的删除
     *
     * @param i
     * @param length
     */
    public void remove(int i, int length) {
        if (i + length >= size - 1) {
            length = size - 1 - i;
        }
        int count = i;
        Node pre = null;
        Node curr = head;
        while (curr != null) {
            if (count == 0) {
                break;
            }
            pre = curr;
            curr = curr.getNext();
            count--;
        }
        while (curr != null) {
            if (length == 0) {
                break;
            }
            curr = curr.getNext();
            length--;
        }
        pre.setNext(curr.getNext());

    }

    /**
     * 打印方法
     * @return
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Node current = head;
        while (current == null) {
            builder.append(current.toString());
            current = current.getNext();
        }
        return builder.toString();
    }

    private class MyLinkedListsIterator implements Iterator {

        private MyLinkedLists linkedList;
        private int length = 0;

        public MyLinkedListsIterator(MyLinkedLists linkedList) {
            this.linkedList = linkedList;
        }

        @Override
        public boolean hasNext() {
            return length < size;
        }

        @Override
        public Object next() {
            return linkedList.getNode(length++);
        }

        @Override
        public void remove() {
            linkedList.removeNode(length--);
        }
    }


    public static class Node {

        private Object data;
        private Node next;

        public Node(Object data) {
            this.data = data;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getNext() {
            return next;
        }

        public Object getData() {
            return this.data;
        }

        public String toString() {
            return "data = "+data;
        }
    }
}
