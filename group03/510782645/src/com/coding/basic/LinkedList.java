package com.coding.basic;

public class LinkedList implements List {
    //链表的长度
    int size = 0;
	private Node first;
	private Node last;

	public void add(Object o){
        linkLast(o);
        size++;
	}

    /**
     * 按照索引添加
     * @param index
     * @param o
     */
	public void add(int index , Object o){
        if (index == size)
            linkLast(o);
        else
            linkBefore(o, node(index));
	}

    /**
     * 向链表的最后添加元素
     * @param o
     */
	private void linkLast(Object o) {
        final Node l = last;
        final Node newNode = new Node(o, l, null);
        last = newNode;
        if (l == null)
            //如果只有一个元素， 那么设置链表的first为newNode
            first = newNode;
        else
            l.next = newNode;
        size++;
    }

    /**
     * 向链表指定位置添加元素
     * @param o
     * @param node
     */
    private void linkBefore(Object o, Node node) {
        final Node pred = node.prev;
        final Node newNode = new Node(o, pred, node);
        node.prev = newNode;
        if (pred == null)
            first = newNode;
        else
            pred.next = newNode;
        size++;
    }

    /**
     * 将元素添加到起始位置。
     * @param o
     */
    private void linkFirst(Object o) {
        final Node f = first;
        final Node newNode = new Node(o, null, f);
        first = newNode;
        if (f == null)
            last = newNode;
        else
            f.prev = newNode;
        size++;
    }

    /**
     * 这里查找index节点时， 通过index与size/2的距离来判断是从前往后找还是从后往前找。
     * @param index
     * @return
     */
    Node node(int index) {
        if (index < (size >> 1)) {
            Node x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node x = last;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }

    /**
     * 直接调用node方法即可。
     * @param index
     * @return
     */
	public Object get(int index){
		return node(index);
	}

    /**
     * 根据下标删除
     * @param index
     * @return
     */
	public Object remove(int index){
        Node node = node(index);
        return remove(node);
	}

    /**
     * 根据节点的data值来remove
     * @param o
     * @return
     */
	public Object remove(Object o) {
        if (o == null) {
            for (Node x = first; x != null; x = x.next) {
                if (x.data == null) {
                    return remove(x);
                }
            }
        } else {
            for (Node x = first; x != null; x = x.next) {
                if (o.equals(x.data)) {
                    return remove(x);
                }
            }
        }
        return null;
    }

	private Object remove(Node node){
        final Object obj = node.data;
        final Node next = node.next;
        final Node prev = node.prev;
        //判断临界的地方，index为第一个元素， index为第二个元素
        if (node == first) {
            first = next;
        } else if (node == last) {
            last = prev;
        } else {
            prev.next = next;
            next.prev = prev;

            node.next = null;
            node.prev = null;
        }

        node.data = null;
        size--;
        return obj;
    }

	public int size(){
		return -size;
	}
	
	public void addFirst(Object o){
		linkFirst(o);
	}
	public void addLast(Object o){
		linkLast(o);
	}
	public Object removeFirst(){
		return remove(first);
	}

    /**
     * 获取但不删除栈顶元素，失败则抛出异常
     * @return
     */
    public Object peekFirst() {
        final Node f = first;
        return (f == null) ? null : f.data;
    }

	public Object removeLast(){
		return remove(last);
	}
	public Iterator iterator(){
		return null;
	}

    /**
     * Node内部实现类
     */
	private static class Node{
        Object data;
        Node prev;
        Node next;

        /**
         * 使用内部类来实现链表的每一个节点，每个节点有一个指向下一个元素的next，指向上一个元素的prev，以及自身的data
         */
        public Node(Object data, Node prev, Node next) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }
}
