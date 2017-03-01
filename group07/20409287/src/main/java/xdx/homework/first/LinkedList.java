package xdx.homework.first;

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

        if(this.head != null) {
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
        preNode.next = currentNode.next;
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
            if(currentNode.data.equals(e)) {
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
            if(currentNode.data.equals(e)) {
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
