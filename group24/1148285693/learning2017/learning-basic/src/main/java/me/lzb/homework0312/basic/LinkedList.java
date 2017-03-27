package me.lzb.homework0312.basic;


/**
 * 简易LinkedList
 * Created by LZB on 2017/3/11.
 */
public class LinkedList implements List {

    private int size = 0;


    private Node first;

    private Node last;


    private static class Node {
        Object data;
        Node next;

        public Node(Object data, Node next) {
            this.data = data;
            this.next = next;
        }

    }


    public void add(Object o) {
        if (first == null) {
            first = new Node(o, null);
            last = first;
        } else {
            Node n = new Node(o, null);
            last.next = n;
            last = n;
        }
        size = size + 1;
    }


    public void add(int index, Object o) throws IndexOutOfBoundsException {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index boom");
        }

        if (index == size) {
            add(o);
            return;
        }

        if (index == 0) {
            Node n = new Node(0, first);
            first = n;
            size = size + 1;
            return;
        }

        Node before = first;
        for (int i = 0; i < index - 1; i++) {
            before = before.next;
        }

        Node after = before.next;

        Node n = new Node(o, after);

        before.next = n;

        size = size + 1;

    }


    public Object get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index boom");
        }
        Node result = first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.data;
    }


    public Object remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index boom");
        }

        if (size == 1) {
            Node result = last;
            last = null;
            first = null;
            size = size - 1;
            return result.data;
        }

        if (index == size - 1) {
            Node result = last;
            last = null;
            size = size - 1;
            return result.data;
        }


        if (index == 0) {
            Node result = first;
            Node second = first.next;
            first = second;
            size = size - 1;
            return result.data;
        }


        Node before = first;
        for (int i = 0; i < index - 1; i++) {
            before = before.next;
        }
        Node result = before.next;
        Node after = before.next.next;
        before.next = after;
        size = size - 1;
        return result.data;
    }

    public int size() {
        return size;
    }

    public void addFirst(Object o) {
        add(0, o);
    }


    public void addLast(Object o) {
        add(o);
    }


    public Object removeFirst() {
        return remove(0);
    }


    public Object removeLast() {
        return remove(size);
    }


    public Iterator iterator() {
        return new LinkedListIterator(this);
    }


    private class LinkedListIterator implements Iterator {
        private LinkedList linkedList;

        int pos = 0;

        private LinkedListIterator(LinkedList linkedList) {
            this.linkedList = linkedList;
        }

        @Override
        public boolean hasNext() {

            if (pos >= linkedList.size) {
                return false;
            }
            return true;
        }

        @Override
        public Object next() {
            Object result = linkedList.get(pos);
            pos = pos + 1;
            return result;
        }
    }


    //后面的方法先不用写的说

    /**
     * 把该链表逆置
     * 例如链表为 3->7->10 , 逆置后变为  10->7->3
     */
    public void reverse() {

    }

    /**
     * 删除一个单链表的前半部分
     * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
     * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10
     */
    public void removeFirstHalf() {

    }

    /**
     * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
     *
     * @param i
     * @param length
     */
    public void remove(int i, int length) {

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
    public static int[] getElements(LinkedList list) {
        return null;
    }

    /**
     * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 从当前链表中中删除在list中出现的元素
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
}
