package list;

/**
 * Created by william on 2017/2/25.
 */
public class LinkedList<T> implements List<T> {
    private int size;
    private Node<T> first;
    private Node<T> last;

    private static class Node<T> {
        Node next;
        Node prev;
        T data;

        Node(Node<T> prev, Node next, T data) {
            this.prev = prev;
            this.next = next;
            this.data = data;
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(T ele) {
        Node head = first;
        while (head != null) {
            if ((ele == null && head.data == null) || (ele.equals(head.data)))
                return true;
            head = head.next;
        }
        return false;
    }

    @Override
    public boolean add(T ele) {
        if (first == null)
            first = last = new Node<T>(null, null, ele);
        else {
            //新添加节点的上一个节点是原来链表的最后一个节点
            Node addNode = new Node(last, null, ele);
            //原来链表的最后一个节点的下一个节点需要指向新添加的节点
            last.next = addNode;
            //更新最后一个节点为新添加的节点
            last = addNode;
        }
        size++;
        return true;
    }

    @Override
    public boolean add(int index, T ele) {
        checkBounds(index, true);
        if (index == size) add(ele);
        else {
            Node head = first;
            for (int i = 0; i < size; i++) {
                if (i == index - 1)//得到要插入位置的前一个节点
                    head.next = new Node(head, head.next, ele);
                else
                    head = head.next;
            }
        }
        size++;
        return true;
    }

    @Override
    public boolean remove(T ele) {
        if (!contains(ele))
            return false;
        Node head = first;
        Node prev = head.prev;
        while (head != null) {
            if ((ele == null && ele == head.data) || ele.equals(head.data)) {
                prev.next = head.next;
                size--;
                return true;
            }
            prev = head;
            head = head.next;
        }
        return false;
    }

    @Override
    public T remove(int index) {
        checkBounds(index, false);
        T removeEle = get(index);
        remove(removeEle);
        return removeEle;
    }

    @Override
    public T get(int index) {
        checkBounds(index, false);
        if (index > (size >> 1)) {
            //索引位置大于1/2size，从后往前
            Node tail = last;
            for (int i = size - 1; i >= 0; i--) {
                if (i == index)
                    return (T) tail.data;
                else
                    tail = tail.prev;
            }
        } else {
            //从前往后
            Node head = first;
            for (int i = 0; i < size; i++) {
                if (i == index)
                    return (T) head.data;
                else
                    head = head.next;
            }
        }
        return null;
    }

    @Override
    public int indexOf(T ele) {
        if (first == null) return -1;
        Node head = first;
        for (int i = 0; i < size; i++) {
            if ((ele == null && ele == head.data) || ele.equals(head.data))
                return i;
            head = head.next;
        }
        return -1;
    }

    /**
     * 指定位置查找元素和插入元素到指定位置IndexOutofBounds的判断标准不一样
     *
     * @param index
     * @param isInsert
     */
    private void checkBounds(int index, boolean isInsert) {
        if (isInsert && (index < 0 || index > size))//允许插入到最后一个元素之后，不能排除=
            throw new IndexOutOfBoundsException("index : " + index + ", size : [ 0 - " + size + " ]");
        if (index < 0 || index >= size)//查询从0 --- size-1
            throw new IndexOutOfBoundsException("index : " + index + ", size : [ 0 - " + size + " ]");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[ ");
        Node head = first;
        while (head != null) {
            sb.append(head.data + " ");
            head = head.next;
        }
        return sb.append("]").toString();
    }
}
