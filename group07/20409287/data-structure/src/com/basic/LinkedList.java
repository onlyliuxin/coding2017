package com.basic;

import java.util.Stack;

/**
 * @Description: 链式列表
 */
public class LinkedList<E> implements List<E> {

    private class Node {

        private E data; // 数据域

        private Node next;  // 指针域

        public Node() {
        }

        private Node(E data) {
            this.data = data;
            this.next = null;
        }

    }

    // 链表大小
    private int size = 0;

    private Node head;

    private Node tail;

    /**
     * 添加元素
     *
     * @param data
     * @return
     */
    @Override
    public boolean add(E data) {

        if (this.head != null) {
            Node newNode = new Node(data);
            this.tail.next = newNode;
            this.tail = newNode;
        } else {
            this.head = new Node(data);
            this.tail = this.head;
        }
        size++;
        return true;
    }

    /**
     * 删除指定索引的元素
     *
     * @param index@return
     */
    @Override
    public E remove(int index) {

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("索引不正确!");
        }
        if (isEmpty()) {
            throw new RuntimeException("链表为空!");
        }
        Node currentNode = this.head;
        Node preNode = currentNode;
        for (int i = 0; i < index; i++) {
            preNode = currentNode;
            currentNode = currentNode.next;
        }
        // 如果是头结点
        if (currentNode == preNode) {
            head = head.next;
        } else if (currentNode.next == null) {
            // 如果是尾结点
            tail = preNode;
            tail.next = null;
        } else {
            preNode.next = currentNode.next;
        }
        size--;
        return currentNode.data;
    }

    /**
     * 删除指定的元素
     *
     * @param e
     * @return
     */
    @Override
    public boolean remove(E e) {

        if (!this.contains(e)) return false;

        if (this.head.data.equals(e)) {
            this.head = this.head.next;
            size--;
            return true;
        }
        Node currentNode = this.head;
        Node preNode = currentNode;
        boolean isFind = false;
        for (int i = 0; i < size; i++) {
            if (currentNode.data.equals(e)) {
                isFind = true;
                break;
            }
            preNode = currentNode;
            currentNode = currentNode.next;
        }
        if (!isFind) return false;
        preNode.next = currentNode.next;
        size--;
        return true;
    }

    /**
     * 返回列表长度
     *
     * @return
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * 判断列表是否为空
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 获取指定索引的元素
     *
     * @param index
     * @return
     */
    @Override
    public E get(int index) {

        if (index < 0 || index > size || isEmpty()) {
            throw new IndexOutOfBoundsException("索引不正确!");
        }
        Node currentNode = this.head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }
        return currentNode.data;
    }

    /**
     * 重置某个索引的元素
     *
     * @param index
     * @param e
     * @return
     */
    @Override
    public boolean set(int index, E e) {

        if (index < 0 || index > size || isEmpty()) {
            throw new IndexOutOfBoundsException("索引不正确!");
        }
        Node currentNode = this.head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }
        currentNode.data = e;
        return false;
    }

    /**
     * 判断是否包含某个元素
     *
     * @param e
     * @return
     */
    @Override
    public boolean contains(E e) {

        if (isEmpty()) return false;
        Node currentNode = this.head;
        boolean isFind = false;
        for (int i = 0; i < size(); i++) {
            if (currentNode.data.equals(e)) {
                isFind = true;
            }
            currentNode = currentNode.next;
        }
        return isFind;
    }

    /**
     * 清空列表
     */
    @Override
    public void clear() {
        this.head = this.tail = null;
        size = 0;
    }

    @Override
    public String toString() {

        if (isEmpty()) return "[]";
        StringBuilder strLinkedList = new StringBuilder("[");
        Node currentNode = this.head;
        while (currentNode.next != null) {
            strLinkedList.append(currentNode.data.toString()).append(",");
            currentNode = currentNode.next;
        }
        strLinkedList.append(currentNode.data.toString()).append("]");
        return strLinkedList.toString();
    }

    /**
     * 把该链表逆置
     * 例如链表为 3->7->10 , 逆置后变为  10->7->3
     */
    public void reverse() {

        if (this.isEmpty()) return;
        Stack<E> stack = new Stack<>();
        for (int i = 0; i < this.size(); i++) {
            stack.push(this.get(i));
        }
        int i = 0;
        while (!stack.isEmpty()) {
            this.set(i++, stack.pop());
        }
    }

    /**
     * 删除一个单链表的前半部分
     * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
     * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10
     */
    public void removeFirstHalf() {

        int begin = size / 2;
        while (begin-- > 0) {
            this.remove(0);
        }
    }

    /**
     * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
     *
     * @param i
     * @param length
     */
    public void remove(int i, int length) {

        if (i < 0 || i > size) {
            throw new IndexOutOfBoundsException("索引不正确: " + i);
        }
        while (length-- > 0 && size() > i) {
            remove(i);
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
    public Object[] getElements(LinkedList<Integer> list) {

        Object[] data = new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            data[i] = this.get(list.get(i)).toString();
        }
        return data;
    }

    /**
     * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 从当前链表中中删除在list中出现的元素
     *
     * @param list
     */
    @SuppressWarnings("unchecked")
    public void subtract(LinkedList list) {

        Object[] removeDatas = getElements(list);
        for (Object removeData : removeDatas) {
            remove((E) removeData);
        }
    }

    /**
     * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
     */
    public void removeDuplicateValues() {

        Node node = head;   // 哨兵
        while (node.next != null) {
            if (node.data.equals(node.next.data)) {
                Node delNode = node.next;
                node.next = node.next.next;
                delNode = null;
            } else {
                node = node.next;
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

        if (max < min) throw new RuntimeException("max大于min！");

        if (Integer.parseInt(head.data.toString()) > min) return;
        if (Integer.parseInt(tail.data.toString()) < max) return;
        Node begin = head;  // 要删除的开始结点
        Node end = tail; // 要删除的结束结点
        Node currentNode = head;
        boolean beginFound = false; // 已找到开始结点标志
        Node preNode = head;
        while (currentNode.next != null) {
            if (!beginFound && min < Integer.parseInt(currentNode.data.toString())) {
                begin = preNode;
                beginFound = true;
            }
            if (max > Integer.parseInt(currentNode.data.toString())) {
                end = currentNode;
            }
            preNode = currentNode;
            currentNode = currentNode.next;
        }
        begin.next = end.next;
    }

    /**
     * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
     * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
     *
     * @param list
     */
    @SuppressWarnings("unchecked")
    public LinkedList intersection(LinkedList list) {

        LinkedList linkedListC = new LinkedList();
        for (int i = 0; i < list.size(); i++) {
            Object element = list.get(i);
            if (this.contains((E) element)) {
                linkedListC.add(list.get(i));
            }
        }
        return linkedListC;
    }

    /**
     * 取得迭代器
     *
     * @return
     */
    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<E> {

        Node curNode = LinkedList.this.head;

        @Override
        public boolean hasNext() {
            return curNode != null;
        }

        @Override
        public E next() {
            Node thisNode = curNode;
            curNode = curNode.next;
            return thisNode.data;
        }

        @Override
        public void remove() {
            LinkedList.this.remove(curNode.data);
            curNode = curNode.next;
        }
    }
}
