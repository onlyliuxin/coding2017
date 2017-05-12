package basic;

import java.util.NoSuchElementException;

/**
 * @author nvarchar
 *         date 2017/3/27
 */
public class LinkedList implements List {

    private Node head;
    private Node tail;
    private int size;

    public void add(Object o) {
        addLast(o);
    }

    public void add(int index, Object o) {
        checkPositionIndex(index);
        if (index == size) {
            addLast(o);
        } else if (index == 0) {
            addFirst(o);
        } else {
            Node node = node(index - 1);
            Node newNode = new Node(o, node.next);
            node.next = newNode;
            size++;
        }
    }

    public Object get(int index) {
        checkPositionIndex(index);
        return node(index).data;
    }

    public Object remove(int index) {
        checkPositionIndex(index);
        if (index == 0) {
            return removeFirst();
        } else if (index == size - 1) {
            return removeLast();
        } else {
            Node newNode = node(index);
            Node prevNode = node(index - 1);
            prevNode.next = newNode.next;
            size--;
            return newNode.data;
        }
    }

    public int size() {
        return size;
    }

    public void addFirst(Object o) {
        Node first = head;
        Node newNode = new Node(o, first);
        head = newNode;
        if (first == null) {
            tail = newNode;
        }
        size++;
    }

    public void addLast(Object o) {
        Node newNode = new Node(o, null);
        Node last = tail;
        tail = newNode;
        if (last == null) {
            head = newNode;
        } else {
            last.next = newNode;
        }
        size++;
    }

    public Object removeFirst() {
        Node first = head;
        if (first == null) {
            throw new NoSuchElementException();
        } else {
            Node next = first.next;
            if (next == null) {
                head = null;
                tail = null;
            } else {
                head = next;
            }
            size--;
            return first.data;
        }
    }

    public Object removeLast() {
        Node last = tail;
        if (last == null) {
            throw new NoSuchElementException();
        } else {
            if (size == 1) {
                head = null;
                tail = null;
            } else {
                tail = node(size - 2);
                tail.next = null;
            }
            size--;
            return last.data;
        }
    }

    public Iterator iterator() {
        return new Iterator() {
            private int nextIndex;
            private Node node;

            @Override
            public boolean hasNext() {
                return nextIndex < size;
            }

            @Override
            public Object next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                } else {
                    nextIndex++;
                    if (node == null) {
                        node = head;
                        return node.data;
                    } else {
                        node = node.next;
                        return node.data;
                    }
                }
            }
        };
    }

    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index)) {
            throw new IndexOutOfBoundsException();
        }
    }

    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    private static class Node {
        Object data;
        Node next;

        public Node(Object data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node node(int index) {
        Node node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    /**
     * 把该链表逆置
     * 例如链表为 3->7->10 , 逆置后变为  10->7->3
     */
    public void reverse() {
        Iterator iterator = iterator();
        LinkedList list = new LinkedList();
        while (iterator.hasNext()) {
            list.addFirst(iterator.next());
        }
        this.head = list.head;
        this.tail = list.tail;
        this.size = list.size;
    }

    /**
     * 删除一个单链表的前半部分
     * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
     * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10
     */
    public void removeFirstHalf() {
        int count = size / 2;
        if (count == 0) {
            return;
        }
        Node newNode = node(count);
        head = newNode;
        size = size - count;
    }

    /**
     * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
     *
     * @param i
     * @param length
     */
    public void remove(int i, int length) {
        checkPositionIndex(i);
        checkPositionIndex(i + length);
        for (int j = i; j < i + length; j++) {
            remove(j);
        }
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
        int[] result = new int[list.size];
        int i = 0;
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            int position = (int) iterator.next();
            if (position >= 0 && position < size) {
                int number = (int) get(position);
                result[i++] = number;
            }
        }
        return result;
    }

    /**
     * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 从当前链表中删除在list中出现的元素
     *
     * @param list
     */

    public void subtract(LinkedList list) {
        LinkedList result = new LinkedList();
        Iterator iterator = iterator();
        Iterator iteratorB = list.iterator();
        while (iterator.hasNext() && iteratorB.hasNext()) {
            int number1 = (int) iterator.next();
            int number2 = (int) iteratorB.next();
            while (number1 < number2) {
                if (!iterator.hasNext()) {
                    break;
                }
                result.add(number1);
                number1 = (int) iterator.next();
            }
            while (number1 > number2) {
                if (!iteratorB.hasNext()) {
                    break;
                }
                number2 = (int) iteratorB.next();
            }
        }
        while (iterator.hasNext()){
            result.add(iterator.next());
        }
        head = result.head;
        tail = result.tail;
        size = result.size;
    }

    /**
     * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
     */
    public void removeDuplicateValues() {
        int prev;
        int after;
        LinkedList result = new LinkedList();
        Iterator iterator = iterator();
        if (iterator.hasNext()) {
            prev = (int) iterator.next();
            result.add(prev);
        } else {
            return;
        }
        if (iterator.hasNext()) {
            after = (int) iterator.next();
        } else {
            return;
        }
        if (prev != after){
            result.add(after);
        }


        while (iterator.hasNext()) {
            prev = after;
            after = (int) iterator.next();
            if (prev != after) {
                result.add(after);
            }
        }

        head = result.head;
        tail = result.tail;
        size = result.size;
    }

    /**
     * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
     *
     * @param min
     * @param max
     */
    public void removeRange(int min, int max) {
        Iterator iterator = iterator();
        LinkedList result = new LinkedList();
        while (iterator.hasNext()) {
            int number = (int) iterator.next();
            if (number <= min || number >= max) {
                result.add(number);
            }
        }
        head = result.head;
        tail = result.tail;
        size = result.size;
    }

    /**
     * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
     * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
     *
     * @param list
     */
    public LinkedList intersection(LinkedList list) {
        LinkedList result = new LinkedList();
        Iterator iterator = iterator();
        Iterator iteratorB = list.iterator();
        while (iterator.hasNext() && iteratorB.hasNext()) {
            int number1 = (int) iterator.next();
            int number2 = (int) iteratorB.next();
            while (number1 < number2) {
                if (!iterator.hasNext()) {
                    break;
                }
                number1 = (int) iterator.next();
            }
            while (number1 > number2) {
                if (!iteratorB.hasNext()) {
                    break;
                }
                number2 = (int) iteratorB.next();
            }
            if (number1 == number2) {
                result.add(number1);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
//        list.addLast(3);
//        list.addLast(7);
//        list.addLast(10);
//        list.reverse();
//        System.out.println();
//        list.addLast(2);
//        list.addLast(5);
//        list.addLast(7);
//        list.addLast(8);
//        list.addLast(10);
//        list.removeFirstHalf();
//        System.out.println();
    }
}
