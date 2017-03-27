package io.github.vxzh.download;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class LinkedList<E extends Comparable> implements List<E> {

    private Node<E> head;
    private int size;

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public void clear() {

    }

    /**
     * 从头部添加节点
     *
     * @return
     */
    public boolean add(E o) {
        Node<E> node = new Node<E>();
        node.data = o;
        if (null == head) {
            head = node;
        } else {
            node.next = head.next;
            head.next = node;
        }
        size++;
        return true;
    }

    @Override
    public void set(int index, E e) {
        checkIndex(index);
        Node<E> node = new Node<E>();
        node.data = e;
        Node<E> prev = node(index - 1);
        node.next = prev.next;
        prev.next = node;

    }

    public E get(int index) {
        checkIndex(index);
        return (E) node(index).data;
    }

    public E remove(int index) {
        checkIndex(index);
        if (0 == index) {
            E data = head.data;
            head = head.next;
            return data;
        }
        Node<E> prev = node(index - 1);
        E data = (E) prev.next.data;
        Node<E> current = prev.next;
        prev.next = current.next;
        current.next = null;
        return data;
    }

    public int size() {
        return size;
    }

    public void addLast(E o) {
        Node<E> node = new Node<E>();
        node.data = o;

        if (size == 0) {
            head = node;
        } else {
            Node<E> last = node(size - 1);
            last.next = node;
        }
        size++;
    }

    public E removeFirst() {
        checkIndex(0);
        E data = head.data;
        head = head.next;
        size--;
        return data;
    }

    public E removeLast() {
        if (size == 0) {
            return removeFirst();
        }

        Node<E> node = node(size - 2);
        E data = (E) node.next.data;
        node.next = null;
        size--;
        return data;
    }

    public Iterator<E> iterator() {
        return new It();
    }

    private void checkIndex(int index) {
        if (index < 0 || index > size - 1) {
            throw new NoSuchElementException("index " + index + " not found!");
        }
    }

    private class It implements Iterator<E> {
        Node<E> currentNode = head;
        int currentIndex;

        @Override
        public boolean hasNext() {
            return currentIndex < size - 1;
        }

        @Override
        public E next() {
            currentNode = currentNode.next;
            currentIndex++;
            return currentNode.data;
        }

        @Override
        public void remove() {
            if (currentIndex == 0) {
                removeFirst();
            } else {
                Node<E> prev = node(currentIndex - 1);
                Node<E> cur = prev.next;
                cur.next = null;
                prev.next = cur.next;
                size--;
            }

        }

    }

    private Node<E> node(int index) {
        Node<E> current = head;
        for (int i = 0; i < size; i++) {
            if (index == i) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    private static class Node<E> {
        E data;
        Node<E> next;

    }

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
        if (isEmpty()) {
            return;
        }
        int half = size / 2;

        Node<E> current = head;
        for (int i = 0; i < half; i++) {
            Node<E> next = current.next;
            current.next = null;
            current = next;
        }
    }

    /**
     * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
     *
     * @param i
     * @param length
     */
    public void remove(int i, int length) {
        checkIndex(i);
        if (i + length > size) {
            throw new NoSuchElementException("要删除的元素不存在！");
        }

        Node<E> current = node(i);
        for (int index = i; index <= length; index++) {
            Node<E> next = current.next;
            current.next = null;
            current = next;
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
    public static int[] getElements(LinkedList<Integer> list) {
        List<Integer> listB = new ArrayList<Integer>();

        int[] array = new int[listB.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = (Integer) list.get(i);
        }
        return array;
    }

    /**
     * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 从当前链表中中删除在list中出现的元素
     *
     * @param list
     */

    public void subtract(LinkedList<E> list) {
        Node<E> prev = head;

        while (prev.next != null) {
            if (list.contains(prev.next.data)) {
                Node<E> current = prev.next;
                prev.next = current.next;
                current.next = null;
            }
        }
        prev = head;
        if (prev.data == prev.next.data) {
            head = prev.next;
            prev.next = null;
            prev = head;
        }
    }

    /**
     * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
     */
    public void removeDuplicateValues() {
        Node<E> prev = head;

        while (prev.next != null) {
            Node<E> current = prev.next;
            if (prev.data == prev.next.data) {//如果前一个的data与当前节点的data同等，就删除当前的
                prev.next = current.next;
                current.next = null;
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
        Node<E> prev = head;

        while (prev.next != null) {//先删除头节点之后的
            Node<E> current = prev.next;
            if (min < (Integer) prev.data && (Integer) prev.data < max) {
                prev.next = current.next;
                current.next = null;
            }

        }
        prev = head;
        if (min < (Integer) prev.data && (Integer) prev.data < max) {//如果头节点满足条件，删除头节点
            head = prev.next;
            prev.next = null;
            prev = head;
        }

    }

    /**
     * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
     * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
     *
     * @param list
     */
    public LinkedList<E> intersection(LinkedList<E> list) {
        LinkedList<E> ret = new LinkedList<E>();

        Iterator<E> it1 = list.iterator();
        Iterator<E> it2 = this.iterator();

        E data1 = it1.hasNext() ? it1.next() : null;
        E data2 = it2.hasNext() ? it2.next() : null;
        while (it1.hasNext() || it2.hasNext()) {


            if (data1 == null && data2 != null) {
                ret.add(data2);
                data2 = it2.hasNext() ? it2.next() : null;
            } else if (data2 == null && data1 != null) {
                ret.add(data1);
                data1 = it1.hasNext() ? it1.next() : null;
            } else {// if(data1 != null && data2 != null)
                if (data1.compareTo(data2) < 0) {
                    ret.add(data1);
                    data1 = it1.hasNext() ? it1.next() : null;
                } else if (data1.compareTo(data2) > 0) {
                    ret.add(data2);
                    data2 = it2.hasNext() ? it2.next() : null;
                } else {//equal
                    ret.add(data1);
                    data1 = it1.hasNext() ? it1.next() : null;
                    data2 = it2.hasNext() ? it2.next() : null;
                }
            }
        }

        return ret;
    }
}
