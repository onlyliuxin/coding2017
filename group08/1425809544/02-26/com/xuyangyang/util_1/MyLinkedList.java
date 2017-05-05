package com.util_1;


import java.util.NoSuchElementException;

/**
 * LinkedList集合-链
 * Created by 14258 on 2017/2/27.
 */
public class MyLinkedList implements MyList {
    //链头
    private Node first;
    //链尾
    private Node last;
    //集合大小
    private int size;


    /**
     * 追加元素到链表的末尾
     *
     * @param o
     * @return
     */
    @Override
    public boolean add(Object o) {
        linkedLast(o);
        return true;
    }

    /**
     * 插入元素到链表特定的位置，
     *
     * @param index
     * @param o
     */
    @Override
    public void add(int index, Object o) {
        checkRange(index);//检查index是否越界
        if (index == size) {
            linkedLast(o);
        } else {
            linkedBefore(o, node(index));
        }
    }

    /**
     * add元素时检查index范围
     *
     * @param index
     */
    private void checkRange(int index) {
        if (index < 0 && index > size) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * 移除链表特定位置的元素
     *
     * @param index
     * @return
     */
    @Override
    public Object remove(int index) {
        checkRange(index);
        return unLinked(node(index));
    }

    /**
     * 移除元素
     *
     * @param e
     * @return
     */
    private Object unLinked(Node e) {

        final Object element = e.elementData;
        final Node next = e.next;
        final Node prev = e.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            e.prev = null;
        }
        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            e.next = null;
        }
        e.elementData = null;//把带移除元素置null
        size--;
        return element;
    }

    /**
     * 返回链表中特定位置的元素
     *
     * @param index
     * @return
     */
    @Override
    public Object get(int index) {
        checkRange(index);
        return node(index);
    }

    /**
     * 返回链表中元素的数量
     *
     * @return
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * 元素作为链表第一个元素
     *
     * @param o
     */
    public void addFirst(Object o) {
        linkedFirst(o);
    }

    /**
     * 元素作为链表第一个元素
     *
     * @param o
     */
    private void linkedFirst(Object o) {
        final Node f = first;
        final Node newNode = new Node(null, o, f);
        first = newNode;
        if (f == null) {//当链表为空时，把新元素设置为last
            last = newNode;
        } else {
            f.prev = newNode;
        }
        size++;
    }

    /**
     * 元素作为链表最后一个元素
     *
     * @param
     */
    public void addLast(Object O) {
        linkedLast(O);
    }

    public Object removeFirst() {
        final Node f = first;
        if (f == null) {
            throw new NoSuchElementException();
        }
        final Object element = f.elementData;
        final Node next = f.next;
        f.elementData = null;
        f.next = null;
        first = next;
        if (next == null) {
            last = null;
        } else {
            next.prev = null;
        }
        size--;
        return element;
    }

    public Object removeLast() {
        final Node l = last;
        if (l == null) {
            throw new NoSuchElementException();
        }
        final Object element = l.elementData;
        final Node prev = l.prev;
        l.elementData = null;
        l.prev = null;
        last = prev;
        if (prev == null){
            first = null;
        }else {
            prev.next = null;
        }
        size--;
        return element;
    }


    /**
     * 把元素插入succ节点，
     *
     * @param o
     * @param succ
     */
    private void linkedBefore(Object o, Node succ) {
        final Node pred = succ.prev;//找出succ的前一个Node;
        final Node newNode = new Node(pred, o, succ);//建立一个新节点。
        succ.prev = newNode;//把插入位置的原节点的前一个prev指向新节点
        if (pred == null) {
            first = newNode;//说明原节点时头节点
        } else {
            pred.next = newNode;
        }
        size++;//集合数量+1；
    }

    /**
     * 把元素添加至链表最后；
     *
     * @param o
     */
    private void linkedLast(Object o) {
        final Node l = last;
        final Node newNode = new Node(l, o, null);
        last = newNode;
        if (l == null) {//添加第一个元素时，l为空
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
    }

    /**
     * 根据index返回节点信息
     *
     * @param index
     * @return
     */
    private Node node(int index) {
        if (index < (size >> 1)) {//如果节点在集合的前半部分;
            Node x = first;//把头指针给x;
            //遍历前半部分节点
            for (int i = 0; i < index; i++) {
                //从头指针开始寻找他的下一个
                x = x.next;
            }
            return x;//
        } else {
            Node x = last;
            for (int i = size - 1; i > index; i--) {
                x = x.prev;
            }
            return x;
        }
    }

    /**
     * 链表节点类
     */
    private static class Node {
        private Object elementData;
        private Node prev;
        private Node next;

        public Node(Node prev, Object elementData, Node next) {
            this.prev = prev;
            this.next = next;
            this.elementData = elementData;
        }
    }


    public MyIterator iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements com.util_1.MyIterator {
        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Object next() {
            return null;
        }
    }
}
