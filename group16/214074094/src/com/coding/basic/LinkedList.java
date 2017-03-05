package coding.basic;

/**
 * @Author shane
 * @Time 2017/2/25 21:01
 * @Email stevenchenguang@gmail.com
 * @Desc OwnLinkedList
 */
public class LinkedList implements List {

    private int size = 0;

    private Node first;

    private Node last;

    public void add(Object o) {
        if (size == 0) {
            first = new Node(null, o, null);
            last = first;
            size++;
        } else {
            addLast(o);
        }
    }

    public void add(int index, Object o) {
        _checkIndex(index);
        if (index == size - 1) {
            addLast(o);
        } else {
            Node prev = _node(index);
            Node next = _node(index + 1);
            Node newNode = new Node(prev, o, next);
            prev.next = newNode;
            next.prev = newNode;
            size++;
        }
    }

    public Object get(int index) {
        _checkIndex(index);
        return node(index);
    }

    public Object remove(int index) {
        _checkIndex(index);
        if (index == 0) {
            return removeFirst();
        } else if (index == size - 1) {
            return removeLast();
        }
        Node curr = _node(index);
        Object data = curr.data;
        final Node prev = curr.prev;
        final Node next = curr.next;

        prev.next = next;
        next.prev = prev;
        curr = null;
        size--;

        return data;
    }

    private Object removeFirst() {
        Node oldFirst = first;
        Object data = first.data;
        final Node oldSecond = oldFirst.next;
        if (null == oldSecond) {
            first = null;
            last = null;
        } else {
            oldSecond.prev = null;
            first = oldSecond;
            oldFirst = null;
        }
        size--;
        return data;
    }

    private Object removeLast() {
        Node oldLast = last;
        Object data = last.data;
        final Node oldLastButOne = last.prev;
        if (null == oldLastButOne) {
            first = null;
            last = null;
        } else {
            oldLastButOne.next = null;
            last = oldLastButOne;
            oldLast = null;
        }
        size--;
        return data;
    }

    public void addFirst(Object o) {
        final Node oldFirst = first;
        final Node param = new Node(null, o, null);
        if (null == oldFirst) {
            first = param;
        } else {
            oldFirst.prev = param;
            param.next = oldFirst;
            first = param;
        }
        size++;
    }

    public void addLast(Object o) {
        final Node n = last;
        final Node newNode = new Node(n, o, null);
        last = newNode;
        n.next = newNode;
        size++;
    }

    public int size() {
        return size;
    }

    public Iterator iterator() {
        return null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private static class Node {
        Node prev;
        Object data;
        Node next;

        public Node(Node prev, Object data, Node next) {
            this.prev = prev;
            this.data = data;
            this.next = next;
        }
    }

    /**
     * @Author: shane
     * @Time: 2017/2/25 22:44
     * @Email: stevenchenguang@gmail.com
     * @param: int index
     * @Return: Node
     * @Throw:
     * @Desc: 根据下标获取节点元素上的数据
     */
    private Object node(int index) {
        //如果下标在左一半, 从左往右取
        if (index < size >> 1) {
            Node tmp = first;
            for (int i = 0; i < index; i++) {
                tmp = tmp.next;
            }
            return tmp.data;
        } else {
            Node tmp = last;
            for (int i = size - 1; i > index; i--) {
                tmp = tmp.prev;
            }
            return tmp.data;
        }
    }

    /**
     * @Author: shane
     * @Time: 2017/2/25 22:44
     * @Email: stevenchenguang@gmail.com
     * @param: int index
     * @Return: Node
     * @Throw:
     * @Desc: 根据下标获取节点元素
     */
    private Node _node(int index) {
        //如果下标在左一半, 从左往右取
        if (index < size >> 1) {
            Node tmp = first;
            for (int i = 0; i < index; i++) {
                tmp = tmp.next;
            }
            return tmp;
        } else {
            Node tmp = last;
            for (int i = size - 1; i > index; i--) {
                tmp = tmp.prev;
            }
            return tmp;
        }
    }

    /**
     * @Author: shane
     * @Time: 2017/2/25 22:43
     * @Email: stevenchenguang@gmail.com
     * @param: int index
     * @Return:
     * @Throw: IndexOutOfBoundsException
     * @Desc: 校验下标是否合法
     */
    private void _checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index:" + index + ", Size:" + size);
        }
    }

    @Override
    public String toString() {
        if (0 == size) {
            return "[]";
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < this.size; i++) {
            sb.append(get(i)).append(", ");
        }
        String tmp = sb.substring(0, sb.length() - 2);
        return "[" + tmp + "]";
    }
}