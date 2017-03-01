package task1;

import java.util.Iterator;

/**
 * LinkedList 实现
 */
public class MyLinkedList<T> implements MyList<T> {

    //存放的元素数量
    private int size;

    private Node<T> head;

    public MyLinkedList() {
        head = new Node<>(null, null);
    }

    @Override
    public void add(T o) {
        add(size, o);
    }

    @Override
    public void add(int index, T o) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("index " + index + " 不合法");
        Node<T> targetNode = new Node<>(null, o);
        Node<T> targetPrevNode = getPrevNode(index);
        targetNode.next = targetPrevNode.next;
        targetPrevNode.next = targetNode;
        size++;
    }

    @Override
    public T get(int index) {
        checkIndexRange(index);
        return getPrevNode(index).next.data;
    }


    @Override
    public T remove(int index) {
        checkIndexRange(index);
        Node<T> prevNode = getPrevNode(index);
        Node<T> nodeToRemove = prevNode.next;
        prevNode.next = nodeToRemove.next;
        size--;
        return nodeToRemove.data;
    }

    @Override
    public int size() {
        return size;
    }

    public void addFirst(T o) {
        add(0, o);

    }

    public void addLast(T o) {
        add(size, o);
    }

    public T removeFirst() {
        return remove(0);
    }

    public T removeLast() {
        return remove(size - 1);
    }


    public Iterator<T> iterator() {
        return new MyLinkedItr();
    }

    /**
     * 找到位置为index的前一个node
     *
     * @param index 索引值
     */

    private Node<T> getPrevNode(int index) {
        Node<T> targetPrevNode = head;
        for (int i = 0; i < index; i++) {
            targetPrevNode = targetPrevNode.next;
        }
        return targetPrevNode;
    }

    /**
     * 检查索引是否越界
     *
     * @param index 索引值
     */
    private void checkIndexRange(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("index " + index + " 越界");
    }

    private static class Node<T> {
        private Node<T> next;
        private T data;

        private Node(Node<T> next, T data) {
            this.next = next;
            this.data = data;
        }
    }

    private class MyLinkedItr implements Iterator<T> {

        private Node<T> currentNode = head;

        @Override
        public boolean hasNext() {
            return currentNode.next != null;
        }

        @Override
        public T next() {
            Node<T> nextNode = currentNode.next;
            T data = nextNode.data;
            currentNode = nextNode;
            return data;
        }
    }
}
