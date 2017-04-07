package com.coding.basic;

public class LinkedList implements List {

    private Node head;
    private int size = 0;

    public void add(Object o) {
        Node newNode = new Node(o, null);
        if (head == null) {
            head = newNode;
        } else {
            node(size - 1).next = newNode;
        }
        size++;
    }

    public void add(int index, Object o) {
        checkForAdd(index);
        if (index == size) {
            add(o);
        } else {
            Node newNode = new Node(o, null);
            if (index == 0) {
                addFirst(o);
            } else {
                Node preNode = node(index - 1);
                Node now = preNode.next;
                preNode.next = newNode;
                newNode.next = now;
                size++;
            }
        }

    }

    private Node node(int index) {
        Node x = head;
        for (int i = 0; i < index; i++) {
            x = x.next;
        }
        return x;
    }

    public Object get(int index) {
        checkIndex(index);
        return node(index).data;
    }

    /**
     * 让被删除的引用的持有者指向下一个节点
     *
     * @param index
     * @return
     */
    public Object remove(int index) {
        final Object ret;
        checkIndex(index);
        if (index == 0) {
            Node removeNode = head;
            ret = head.data;
            head = removeNode.next;
        } else {
            Node pre = node(index - 1);
            Node removeNode = pre.next;
            ret = removeNode.data;
            pre.next = removeNode.next;
        }
        size--;
        return ret;
    }

    public int size() {
        return size;
    }

    public void addFirst(Object o) {
        head = new Node(o, head);
        ;
        size++;
    }

    public void addLast(Object o) {
        add(o);
    }

    public Object removeFirst() {
        if (size == 0) {
            return null;
        } else {
            return remove(0);
        }
    }

    public Object removeLast() {
        return remove(size - 1);
    }

    public LinkedListIterator iterator() {
        return new LinkedListIterator(head);
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException(String.format("index=%s, size=%s", index, size));
        }
    }

    private void checkForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException(String.format("index=%s, size=%s", index, size));
        }
    }


    @Override
    public String toString() {
        Iterator iterator = iterator();
        StringBuilder builder = new StringBuilder("[");
        while ((iterator.hasNext())) {
            builder.append(iterator.next()).append(',');
        }
        if (size() > 0) {
            builder.deleteCharAt(builder.length() - 1);
        }
        return builder
                .append(']')
                .toString();
    }

    /**
     * 把该链表逆置
     * 例如链表为 3->7->10 , 逆置后变为  10->7->3
     */
    public void reverse() {
        if (size == 0) {
            return;
        }
        Object[] datas = new Object[size];
        int i = 0;
        // 迭代链表的数据生成数组
        Iterator iterator = iterator();
        while (iterator.hasNext()) {
            datas[i++] = iterator.next();
        }
        // 遍历数组越生成新的 链表
        Node newHead = new Node(datas[--i], null);
        Node next = newHead;
        for (int j = --i; j >= 0; j--) {
            next.next = new Node(datas[j], null);
            next = next.next;

        }
        this.head = newHead;

    }

    /**
     * 删除一个单链表的前半部分
     * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
     * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10
     */
    public void removeFirstHalf() {
        removeFirstSize(size >> 1);
    }

    public void removeFirstSize(int firstSize) {
        firstSize = firstSize > size() ? size() : firstSize;
        LinkedListIterator iterator = iterator();
        int i = 1;
        while (i++ <= firstSize) {
            iterator.nextNode();
        }
        if (size > 0) {
            head = iterator.nextNode();
            size = size() - firstSize;
        }
    }


    /**
     * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
     *
     * @param i
     * @param length
     */
    public void remove(int i, int length) {
        if (i == 0 ) {removeFirstSize(length); return;}
        if (i >= size || length == 0) {return;}

        int lastLenth = size - i;
        length = length <= lastLenth? length : lastLenth;
        Node pre = node(i-1);
        int j = 0;

        Node next = pre;
        while (j++ < length){
            next = next.next;
        }
        pre.next = next.next;
        size = size - length;


    }

    /**
     * 假定当前链表和listB均包含已升序排列的整数
     * 从当前链表中取出那些listB所指定的元素
     * 例如当前链表 = 11->101->201->301->401->501->601->701
     * listB = 1->3->4->6
     * 返回的结果应该是[101,301,401,601]
     *
     * @param list
     */
    public int[] getElements(LinkedList list) {
        return null;
    }

    /**
     * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 从当前链表中中删除在listB中出现的元素
     *
     * @param list
     */

    public void subtract(LinkedList list) {

    }

    /**
     * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
     */
    public void removeDuplicateValues() {

    }

    /**
     * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
     *
     * @param min
     * @param max
     */
    public void removeRange(int min, int max) {

    }

    /**
     * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
     * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
     *
     * @param list
     */
    public LinkedList intersection(LinkedList list) {
        return null;
    }

    private static class Node {
        Object data;
        Node next;

        public Node() {
        }

        public Node(Object data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    private class LinkedListIterator implements Iterator {

        private Node next;

        public LinkedListIterator() {
        }

        private LinkedListIterator(Node next) {
            this.next = next;
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public Object next() {
            if (next == null) {
                throw new IndexOutOfBoundsException("there is no node in list");
            }
            Node ret = next;
            next = next.next;
            return ret.data;
        }


        private Node nextNode() {
            if (next == null) {
                throw new IndexOutOfBoundsException("there is no node in list");
            }
            Node ret = next;
            next = next.next;
            return ret;
        }
    }
}
