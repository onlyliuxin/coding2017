package baselinked;

/**
 * Created by 14258 on 2017/3/14.
 */
public class LinkedList implements List {

    private Node head;
    private int size;


    @Override
    public void add(Object o) {
        addLast(o);
    }

    @Override
    public void add(int index, Object o) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            addFirst(o);
            return;
        }

        if (index == size) {
            addLast(o);
            return;
        }

        Node newNode = new Node(o);
        Node node = head;
        for (int i = 1; i < index; i++) {
            node = node.next;
        }
        newNode.next = node.next;
        node.next = newNode;
        size++;
    }

    @Override
    public Object remove(int index) {

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            return removeFirst();
        }
        Node node = head;
        Node pre = head;
        for (int i = 1; i < index; i++) {
            if (node.next != null) {
                pre = node;
                node = node.next;
            }
        }

        Object obj = node.data;
        if (head.next == null) {
            head = null;
            pre = null;
        } else if (node.next == null) {
            pre.next = null;
            node = null;
        } else {
            pre.next = node.next;
            node.next = null;
            node = null;
        }
        size--;
        return obj;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Object get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        Object data = node.data;
        return data;
    }


    public void addFirst(Object o) {
        Node newNode = new Node(o);
        newNode.next = head;
        head = newNode;
        size++;
    }

    public void addLast(Object o) {
        Node newNode = new Node(o);
        if (head == null) {
            head = newNode;
        } else {
            if (head.next == null) {
                head.next = newNode;
            } else {
                Node node = head;
                for (int i = 1; i < size; i++) {
                    node = node.next;
                }
                node.next = newNode;
            }
        }
    }

    public Object removeFirst() {
        if (size <= 0) {
            throw new IndexOutOfBoundsException();
        }
        Node node = head;
        head = node.next;
        node.next = null;
        size--;
        return node.data;

    }

    public Object removeLast() {
        if (size <= 0) {
            throw new IndexOutOfBoundsException();
        }
        Node node = head;
        for (int i = 1; i < size; i++) {
            node = node.next;
        }
        Object data = node.next.data;
        node.next = null;
        size--;
        return data;
    }


    private class LinkedListIterator implements Iterator {
        private Node node = head;

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public Object next() {
            Object data = node.data;
            node = node.next;
            return data;
        }
    }

    public Iterator iterator() {
        return new LinkedListIterator();
    }

    private static class Node {
        Object data;
        Node next;

        public Node(Object data) {
            this.data = data;
        }
    }


    /**
     * 把该链表逆置 例如链表为 3->7->10 , 逆置后变为 10->7->3
     */
    public void reverse() {
//        Node node = head.next;
//        Object[] arr = new Object[size];
//        int i = size - 1;
//        while (i >= 0) {
//            arr[i--] = node.data;
//            node = node.next;
//        }
//        node = head.next;
//        for (int j = 0; j < size; j++) {
//            node.data = arr[j];
//            node = node.next;
//        }
        if (size <= 0) {
            throw new IndexOutOfBoundsException("链表下表越界" + size);
        }
        Node node = head;
        Node minNode = node;
        int length = size;
        for (int i = 0; i < length; i++) {
            if (node.next != null) {
                node = node.next;
                addFirst(node.data);
            }
        }
        minNode.next = null;
        node = null;
        size = length;

    }


    /**
     * 删除一个单链表的前半部分 例如：list = 2->5->7->8 , 删除以后的值为 7->8 如果list = 2->5->7->8->10
     * ,删除以后的值为7,8,10
     */


    public void removeFirstHalf() {
        if (size <= 0) {
            throw new IndexOutOfBoundsException("链表下标越界" + size);
        }
        Node node = head;
        Node pre = head;
        int count = 0;
        for (int i = 0; i < size / 2; i++) {
            pre = node;
            node = node.next;
            count++;
        }

        head = node;
        pre.next = null;
        pre = null;
        size = size - count;

    }

    /**
     * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
     *
     * @param i
     * @param length
     */

    public void remove(int i, int length) {

        if (i<0 || i>=size || length<0 ||length>size||(i+length>size)){
            throw new IndexOutOfBoundsException();
        }

        Node node = head;
        Node pre = head;
        Node iNode = head;
        for (int j = 0;j<i+length;j++){
            if (node.next!=null){
                pre = node;
                if (j==(i-1)){
                    iNode = node;
                }
                node = node.next;
            }
        }

        if (i==0){
            head = node;
        }else {
            iNode.next = node;
        }

        pre.next = null;
        pre = null;
        size = size()-length;

    }





}

