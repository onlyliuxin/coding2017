package com.aaront.exercise.basic;

public class LinkedList implements List {

    private Node head = new Node(null);
    private int size = 0;

    public void add(Object o) {
        Node newNode = new Node(o);
        Node first = head.next;
        Node second = head;
        while (first != null) {
            second = first;
            first = first.next;
        }
        second.next = newNode;
        size++;
    }

    public void add(int index, Object o) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException("索引超出范围");
        Node first = head;
        int i = 0;
        while (i < index) {
            first = first.next;
            i++;
        }
        Node node = new Node(o);
        node.next = first.next;
        first.next = node;
        size++;
    }

    public Object get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("索引超出范围");
        Node first = head.next;
        int i = 0;
        while (i < index) {
            first = first.next;
            i++;
        }
        return first.data;
    }

    public Object remove(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("索引超出范围");
        Node first = head;
        int i = 0;
        while (i < index) {
            first = first.next;
            i++;
        }
        Node element = first.next;
        first.next = first.next.next;
        size--;
        return element.data;
    }

    public int size() {
        return size;
    }

    public void addFirst(Object o) {
        add(0, o);
    }

    public void addLast(Object o) {
        add(size, o);
    }

    public Object removeFirst() {
        return remove(0);
    }

    public Object removeLast() {
        return remove(size - 1);
    }

    public Iterator iterator() {
        return new LinkedListIterator(this);
    }

    public Object[] toArray() {
        Object[] objects = new Object[size];
        Node first = head.next;
        int pos = 0;
        while (first != null) {
            objects[pos++] = first.data;
            first = first.next;
        }
        return objects;
    }

    private static class LinkedListIterator implements Iterator {

        private int pos = 0;
        private LinkedList linkedList;

        private LinkedListIterator(LinkedList linkList) {
            this.linkedList = linkList;
        }

        @Override
        public boolean hasNext() {
            return pos < linkedList.size();
        }

        @Override
        public Object next() {
            return linkedList.get(pos++);
        }

        @Override
        public void remove() {
            linkedList.remove(pos - 1);
            pos--;
        }
    }


    private static class Node {
        private Object data;
        private Node next;

        private Node(Object data) {
            this.data = data;
        }
    }

    /**
     * 把该链表逆置
     * 例如链表为 3->7->10 , 逆置后变为  10->7->3
     */
    public void reverse() {
        Node newHead = new Node(null);
        Node node = head.next;
        while (node != null) {
            Node temp = node.next;
            node.next = newHead.next;
            newHead.next = node;
            node = temp;
        }
        head = newHead;
    }

    /**
     * 删除一个单链表的前半部分
     * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
     * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10
     */
    public void removeFirstHalf() {
        int removeLen = size / 2;
        if (size <= 1) {
            head.next = null;
            size = 0;
            return;
        }
        for (int i = 0; i < removeLen; i++) {
            Node point = head.next;
            head.next = point.next;
            point.next = null;
            size--;
        }
    }

    /**
     * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
     *
     * @param i
     * @param length
     */
    public void remove(int i, int length) {
        if (i < 0 || i >= size || length < 1) throw new IndexOutOfBoundsException("索引超出范围");
        int endIndex = Math.min(i + length, size);
        Node preNode = head;
        Node endNode = head.next;
        for (int index = 0; index < endIndex; index++) {
            if (index < i) {
                preNode = endNode;
            }
            endNode = endNode.next;
        }

        preNode.next = endNode;
        size = size - (endIndex - i);
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
        if (list == null) return new int[0];
        Iterator iterator = list.iterator();
        int[] result = new int[list.size()];
        int index = 0;
        while (iterator.hasNext()) {
            Integer next = (Integer) iterator.next();
            result[index] = (Integer) this.get(next);
            index++;
        }
        return result;
    }

    /**
     * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 从当前链表中中删除在list中出现的元素
     *
     * @param list
     */

    public void subtract(LinkedList list) {
        if (list == null) return;
        Iterator iterator = this.iterator();
        while (iterator.hasNext()) {
            Object element = iterator.next();
            if (contain(element, list)) {
                iterator.remove();
            }
        }
    }

    private boolean contain(Object element, LinkedList list) {
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            Object next = iterator.next();
            if (next == element) return true;
        }
        return false;
    }

    /**
     * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
     */
    public void removeDuplicateValues() {
        Node pre = head;
        Node cur = head.next;
        while (cur != null) {
            if (pre.data == cur.data) {
                Node node = cur.next;
                while (node != null && node.data == pre.data) {
                    node = node.next;
                    size--;
                }
                pre.next = node;
                cur = node;
                size--;
            } else {
                pre = cur;
                cur = cur.next;
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
        if (min >= max) return;
        Node cur = head;
        Node start = null;
        Node end = null;
        Integer startIndex = null;
        Integer endIndex = size - 1;
        Integer index = -1;
        while (cur.next != null) {
            if (start == null && (Integer) cur.next.data > min) {
                start = cur;
                startIndex = index;
            }
            if ((Integer) cur.next.data >= max) {
                end = cur.next;
                endIndex = index;
                break;
            }
            cur = cur.next;
            index++;
        }
        if (start != null) {
            start.next = end;
            size = size - (endIndex - startIndex);
        }
    }

    /**
     * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
     * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
     *
     * @param list
     */
    public LinkedList intersection(LinkedList list) {
        if (list == null) return this;
        LinkedList intersection = new LinkedList();
        Node node1 = this.head.next;
        Node node2 = list.head.next;
        while (node1 != null && node2 != null) {
            if((Integer)node1.data < (Integer)node2.data) {
                node1 = node1.next;
            } else if((Integer)node1.data > (Integer)node2.data) {
                node2 = node2.next;
            } else {
                intersection.add(node1.data);
                node1 = node1.next;
                node2 = node2.next;
            }
        }

        return intersection;
    }
}
