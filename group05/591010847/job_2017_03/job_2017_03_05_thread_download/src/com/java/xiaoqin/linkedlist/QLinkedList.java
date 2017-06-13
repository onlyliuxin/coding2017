package com.java.xiaoqin.linkedlist;


import java.util.Arrays;

public class QLinkedList implements QList {

    private Node head;
    private int size = 0;

    public void add(Object o) {
        if (null == head) {
            head = new Node();
            head.data = o;
        } else {
            Node node = findNodeByIndex(size - 1);
            node.next = new Node();
            node.next.data = o;
        }
        size++;
    }

    private Node findNodeByIndex(int index) {
        Node node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    public void add(int index, Object o) {
        if (index > size()) {
            throw new ArrayIndexOutOfBoundsException(index);
        }

        Node node = new Node();
        node.data = o;

        Node preNode = findNodeByIndex(index - 1);
        node.next = preNode.next;
        preNode.next = node;
        size++;
    }

    public Object get(int index) {
        if (index > size()) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        return findNodeByIndex(index).data;
    }

    public Object remove(int index) {
        if (index > size()) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        Node preNode = findNodeByIndex(index - 1);
        Node node = preNode.next;
        preNode.next = preNode.next.next;
        size--;
        return node;
    }

    public int size() {
        return size;
    }

    public void addFirst(Object o) {
        Node node = new Node();
        node.data = o;
        node.next = head;
        head = node;
        size++;
    }

    public void addLast(Object o) {
        Node node = new Node();
        node.data = o;
        if (size() > 0) {
            Node preNode = findNodeByIndex(size() - 1);
            preNode.next = node;
        } else {
            head = node;
        }
        size++;
    }

    public Object removeFirst() {
        Node firstNode = null;
        if (null != head) {
            firstNode = head;
            head = head.next;
            size--;
        }
        return firstNode;
    }

    public Object removeLast() {
        Node lastNode = null;
        if (null != head) {
            Node tempNode = head;
            while (null != tempNode.next) {
                if (null == tempNode.next.next) {
                    break;
                }
                tempNode = tempNode.next;
            }
            lastNode = tempNode.next;
            tempNode.next = null;
            size--;
        }
        return lastNode;
    }

    public QIterator iterator() {
        return new QLinkListIterator();
    }

    private class QLinkListIterator implements QIterator {

        private Node iteratorHead;

        public QLinkListIterator() {
            iteratorHead = new Node();
            iteratorHead.next = head;
        }

        @Override
        public boolean hasNext() {
            return null != iteratorHead.next;
        }

        @Override
        public Object next() {
            Node next = iteratorHead.next;
            iteratorHead = iteratorHead.next;
            return next;
        }
    }

    private static class Node {
        Object data;
        Node next;

        @Override
        public String toString() {
            return null == data ? "null" : data.toString() + "\n";
        }
    }

    @Override
    public String toString() {
        Node node = head;
        StringBuilder toStringBuilder = new StringBuilder();
        while (null != node) {
            toStringBuilder.append(node.toString());
            node = node.next;
        }
        return toStringBuilder.toString();
    }

    /**
     * 把该链表逆置
     * 例如链表为 3->7->10 , 逆置后变为  10->7->3
     */
    public void reverse() {
        Node oldNode = head;
        Node tempNode = null;
        while (null != oldNode) {
            Node saveNode = oldNode;
            oldNode = oldNode.next;
            saveNode.next = tempNode;
            tempNode = saveNode;
        }
        head = tempNode;
    }

    /**
     * 删除一个单链表的前半部分
     * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
     * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10
     */
    public void removeFirstHalf() {
        int half = size() / 2;
        for (int i = 0; i < half; i++) {
            head = head.next;
        }
        size -= half;
    }

    /**
     * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
     *
     * @param i
     * @param length
     */
    public void remove(int i, int length) {
        if (i < 0) {
            throw new ArrayIndexOutOfBoundsException(i);
        }
        Node node = head;
        Node preNode = null;
        int index = 0;
        while (null != node) {
            if (index >= i && index < i + length) {
                if (null != preNode) {
                    preNode.next = node.next;
                    size--;
                }
            } else {
                preNode = node;
            }
            index++;
            node = node.next;
        }
        if (i == 0) {
            head = preNode;
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
    public int[] getElements(QLinkedList list) {
        if (null != list && list.size > 0) {
            int[] resultObjs = new int[list.size];
            int objIndex = 0;
            for (int i = 0; i < list.size; i++) {
                Object index = list.get(i);
                if (index instanceof Integer && (int) index < size()) {
                    Object o = get((Integer) index);
                    if (o instanceof Integer) {
                        resultObjs[objIndex++] = (int) o;
                    }
                }
            }
            return Arrays.copyOf(resultObjs, objIndex);
        }
        return null;
    }

    /**
     * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 从当前链表中中删除在list中出现的元素
     *
     * @param list
     */

    public void subtract(QLinkedList list) {
        if (null != list) {
            QIterator iterator = list.iterator();
            while (iterator.hasNext()) {
                Object next = iterator.next();
                for (int i = 0; i < size; i++) {
                    if (next.equals(get(i))) {
                        remove(i);
                        break;
                    }
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
    public QLinkedList intersection(QLinkedList list) {
        QLinkedList resultList = new QLinkedList();
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (get(i) == list.get(j)) {
                    resultList.add(get(i));
                    break;
                }
            }
        }
        return resultList;
    }
}
