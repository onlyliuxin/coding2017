package com.byhieg.coding2017.homework312;


import com.byhieg.coding2017.homework226.Iterator;
import com.byhieg.coding2017.homework226.List;
import com.byhieg.utils.bprint.FullPrint;

public class LinkedList implements List {

    private Node head;
    int size = 0;

    public void add(Object o) {
        addLast(o);
    }

    public void add(int index, Object o) {
        checkRangeForAdd(index);
        if (index == size) {
            addLast(o);
            return;
        }
        Node nextNode = node(index);
        Node newNode = new Node(o, nextNode);

        Node prevNode;
        if (index == 0) {
            prevNode = null;
        } else {
            prevNode = node(index - 1);
        }
        if (prevNode == null) {
            head = newNode;
        } else {
            prevNode.next = newNode;
        }

        size++;
    }


    private Node node(int index) {
        Node cursor = head;
        for (int i = 0; i < index; i++) {
            cursor = cursor.next;
        }
        return cursor;
    }

    private void checkRangeForAdd(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("指定的index超过界限");
        }
    }

    public Object get(int index) {
        checkRange(index);
        return node(index).data;
    }

    private void checkRange(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("指定index超过界限");
        }
    }

    public Object remove(int index) {
        checkRange(index);
        Node targetNode = node(index);
        Object o = targetNode.data;
        Node prevNode;
        Node nextNode = targetNode.next;

        if (index == 0) {
            prevNode = null;
        } else {
            prevNode = node(index - 1);
        }
        if (prevNode == null) {
            head = nextNode;
            targetNode.next = null;
        } else {
            prevNode.next = nextNode;
            targetNode.next = null;
        }

        targetNode.data = null;
        size--;
        return o;
    }

    public int size() {
        return size;
    }

    public void addFirst(Object o) {
        Node nextNode = head;
        Node newNode = new Node(o, nextNode);
        head = newNode;
        size++;
    }

    public void addLast(Object o) {
        Node newNode = new Node(o, null);
        if (size == 0) {
            head = newNode;
        } else {
            Node lastNode = node(size - 1);
            lastNode.next = newNode;
        }
        size++;
    }

    public Object removeFirst() {
        return remove(0);
    }

    public Object removeLast() {
        return remove(size() - 1);
    }

    public Iterator iterator() {

        return new MyIterator();
    }

    private class MyIterator implements Iterator {

        public Node cursor = head;

        @Override
        public boolean hasNext() {
            return cursor != null;
        }

        @Override
        public Object next() {
            Object o = cursor.data;
            cursor = cursor.next;
            return o;
        }
    }


    private static class Node {
        Object data;
        Node next;

        public Node(Object data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    /**
     * 把该链表逆置
     * 例如链表为 3->7->10 , 逆置后变为  10->7->3
     */
    public void reverse() {
        for (int i = 0; i < size() / 2; i++) {
            if (i == 0) {
                Object o1 = remove(i);
                Object o2 = remove(size - i - 1);
                addFirst(o2);
                addLast(o1);
            } else {
                Object o1 = remove(i);
                Object o2 = remove(size - i - 1);
                add(i, o2);
                add(size - i, o1);
            }
        }
    }


    /**
     * 删除一个单链表的前半部分
     * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
     * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10
     */
    public void removeFirstHalf() {
        for (int i = 0; i < size() / 2; i++) {
            remove(0);
        }
    }

    /**
     * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
     *
     * @param i
     * @param length
     */
    public void remove(int i, int length) {
        for (int j = 0 ; j < length ;j ++) {
            remove(i);
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
        int[] result = new int[list.size()];
        int count = 0;
        for (int i = 0 ; i < list.size();i++) {
            result[count++] = (int)get((Integer) list.get(i));
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
        for (int i = 0 ; i < list.size();i++) {
            for (int j = 0 ;j < size();j++) {
                if (list.get(i) == get(j)) {
                    remove(j);
                }
            }
        }
    }

    /**
     * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
     */
    public void removeDuplicateValues() {
        for (int i = 0; i < size();i++) {
            for (int j = i + 1 ; j < size();j++) {
                if (get(i) == get(j)){
                    remove(j);
                }
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
        for (int i = 0 ; i < size();i++) {
            if ((int)get(i) > min && (int)get(i) < max){
                remove(i);
                i--;
            }

        }
    }

    /**
     * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
     * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
     *
     * @param list
     */
    public LinkedList intersection(LinkedList list) {
        LinkedList result = new LinkedList();
        for (int i = 0 ; i < size();i++) {
            for (int j = 0 ; j < list.size();j++){
                if (get(i) == list.get(j)){
                    result.add(get(i));
                }
            }
        }
        return result;
    }
}
