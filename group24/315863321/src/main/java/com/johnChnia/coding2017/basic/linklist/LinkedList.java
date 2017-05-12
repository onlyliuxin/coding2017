package com.johnChnia.coding2017.basic.linklist;

import com.johnChnia.coding2017.basic.List;

import java.util.NoSuchElementException;

/**
 * Created by john on 2017/3/9.
 *
 * @// TODO: 2017/4/1 支持Iterator
 */

public class LinkedList<E> implements List<E> {

    private Node<E> first = null;
    private int size = 0;

    /**
     * Constructs an empty list.
     */
    public LinkedList() {

    }


    private static class Node<T> {
        T element;
        Node<T> next;
        Node<T> prev;
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param element element to be appended to this list
     */
    public void add(E element) {
        Node<E> newNode = new Node<>();
        if (first == null) {
            addWhenListIsEmpty(newNode, element);
            return;
        }
        Node<E> last = first;
        while (last.next != null)
            last = last.next;
        last.next = newNode;
        newNode.prev = last;
        newNode.next = null;
        newNode.element = element;
        size++;
    }

    private void addWhenListIsEmpty(Node<E> newNode, E element) {
        first = newNode;
        first.element = element;
        first.next = null;
        first.prev = null;
        size++;
    }

    /**
     * Inserts the specified element at the beginning of this list.
     *
     * @param element the element to add
     */
    public void addFirst(E element) {
        Node<E> newNode = new Node<>();
        if (first == null) {
            addWhenListIsEmpty(newNode, element);
            return;
        }
        newNode.next = first;
        newNode.prev = null;
        newNode.element = element;

        first.prev = newNode;
        first = newNode;
        size++;
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param element element to be appended to this list.
     */
    public void addLast(E element) {
        add(element);
    }


    /**
     * Inserts the specified element at the specified position in this list.
     * Shifts the element currently at that position (if any) and any
     * subsequent elements to the right (adds one to their indices).
     *
     * @param index   index at which the specified element is to be inserted.
     * @param element element to be inserted.
     * @throws RuntimeException if list size less than 2.
     */
    public void add(int index, E element) {
        if (size() < 2)
            throw new RuntimeException("list size should greater than or equal to 2");
        isElementIndex(index);
        if (index == 0) {
            addFirst(element);
            return;
        } else {
            Node<E> temp = new Node<>();
            Node<E> temp2 = first;
            for (int i = 0; i < index; i++) {
                temp2 = temp2.next;
            }
            temp2.prev.next = temp;
            temp.prev = temp2.prev;

            temp.next = temp2;
            temp2.prev = temp;
            temp.element = element;
        }
        size++;

    }


    /**
     * remove last element in the list.
     *
     * @throws RuntimeException if the list is empty.
     */
    public E removeLast() {
        if (size == 0)
            throw new RuntimeException("linkList size should greater than or equal to 1");
        E element;
        Node<E> next = first.next;
        if (next == null) {
            element = first.element;

            first = null;
        } else {
            Node<E> last = first;
            while (last.next != null)
                last = last.next;
            last.prev.next = null;

            element = last.element;

            last = null;  // help GC
        }
        size--;
        return element;
    }


    /**
     * @param index
     * @return
     * @// TODO: 2018/3/14 if i am happy, i will implement it right now!
     */
    public E remove(int index) {
        return null;
    }

    /**
     * Removes and returns the first element from this list.
     *
     * @return the first element from this list
     */
    public E removeFirst() {
        Node<E> f = first;
        if (f == null)
            throw new NoSuchElementException();
        E element = f.element;
        Node<E> next = first.next;
        first.element = null;
        first.next = null; // help GC

        first = next;
        if (next != null) {
            next.prev = null;
        }
        size--;
        return element;
    }


    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     */
    public E get(int index) {
        checkElementIndex(index);
        Node<E> node = first;
        if (index == 0) {
            return first.element;
        }
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.element;
    }

    /**
     * Returns the first element in this list.
     *
     * @return the first element in this list
     * @throws NoSuchElementException if this list is empty
     */
    public E getFirst() {
        final Node<E> f = first;
        if (f == null)
            throw new NoSuchElementException();
        return f.element;
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    public int size() {
        return size;
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index)) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }


    /**
     * Tells if the argument is the index of an existing element.
     */
    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(first.element);
        Node<E> temp = first;
        while (temp.next != null) {
            temp = temp.next;
            sb.append("→");
            sb.append(temp.element);
        }
        return sb.toString();
    }

    /**
     * 把该链表逆置
     * 例如链表为 3->7->10 , 逆置后变为  10->7->3
     */
    public void reverse() {
        Node<E> next;
        Node<E> current = first;
        for (int i = 0; i < size; i++) {
            next = current.next;
            current.next = current.prev;
            current.prev = next;
            if (next != null) {
                current = next;
            }
        }
        first = current;
    }

    /**
     * 删除一个单链表的前半部分
     * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
     * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10
     */
    public void removeFirstHalf() {
        int index = size() / 2;
        Node<E> current = first;
        Node<E> prev;
        for (int i = 0; i < index; i++) {
            prev = current;
            current = current.next;
            delete(prev);
        }
        current.prev = null;
        first = current;
        size = size - index;
    }

    /**
     * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
     *
     * @param i
     * @param length
     */
    public void remove(int i, int length) {
        checkElementIndex(i);
        if (length + i > size()) {
            throw new IllegalArgumentException("Length + i should less than or equal " + size());
        }
        Node<E> head = first;
        Node<E> tail = first;
        if (i == 0 && length == size()) {
            first = null;
        } else if (i == 0 && length < size()) {
            for (int j = 0; j < length; j++) {
                head = head.next;
            }
            head.prev = null;
            first = head;
        } else {
            for (int j = 0; j < i; j++) {
                head = head.next;
            }
            head = head.prev;
            for (int j = 0; j < length + i; j++) {
                tail = tail.next;
            }
            head.next = tail;
            if (tail != null) {
                tail.prev = head;
            }
        }
        size = size - length;
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
        int[] newArray = new int[list.size()];
        Node mapNode = list.first;
        Node valueNode = this.first;
        int indexOfList = 0;
        int indexOfArray = 0;
        while (mapNode != null && valueNode != null) {
            int mapValue = (int) mapNode.element;
            if (mapValue == indexOfList) {
                newArray[indexOfArray] = (int) valueNode.element;
                mapNode = mapNode.next;
                valueNode = valueNode.next;
                indexOfArray++;
            } else {
                valueNode = valueNode.next;
            }
            indexOfList++;
        }
        return newArray;
    }

    /**
     * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 从当前链表中中删除在listB中出现的元素
     *
     * @param list
     */

    public void subtract(LinkedList<E> list) {
        Node pNode = first;
        Node qNode = list.first;
        Node prev = null;
        Node deletedNode;
        while (pNode != null && qNode != null) {
            if ((int) qNode.element < (int) pNode.element) {
                qNode = qNode.next;
            } else if ((int) qNode.element > (int) pNode.element) {
                prev = pNode;
                pNode = pNode.next;
            } else {
                if (prev == null) {  // 头结点
                    first = pNode.next;
                } else {
                    prev.next = pNode.next;
                }
                deletedNode = pNode;
                pNode = pNode.next;
                qNode = qNode.next;
                delete(deletedNode);
                size--;
            }
        }

    }

    private void delete(Node node) {
        node.element = null;
        node.prev = null;
        node.next = null;
    }

    /**
     * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
     */
    public void removeDuplicateValues() {
        Node<E> current = first;
        Node<E> next = current.next;
        while (next != null) {
            if (current.element == next.element) {
                current.next = next.next;
                if (next.next != null) {
                    next.next.prev = current;
                }
                delete(next);
                next = current.next;
                size--;
            } else {
                current = current.next;
                next = next.next;
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
        Node current = first;
        Node prev = null;
        Node deletedNode;
        while (current != null) {
            if ((int) current.element >= max) {
                break;
            }
            if ((int) current.element > min && (int) current.element < max) {
                if (prev == null) {  // 头结点
                    first = current.next;
                } else {
                    prev.next = current.next;
                }
                deletedNode = current;
                current = current.next;
                delete(deletedNode);  // help gc
                size--;
            } else {
                prev = current;
                current = current.next;
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
        LinkedList l = new LinkedList();
        Node pNode = first;
        Node qNode = list.first;
        while (pNode != null && qNode != null) {
            if ((int) pNode.element < (int) qNode.element) {
                pNode = pNode.next;
            } else if ((int) pNode.element > (int) qNode.element) {
                qNode = qNode.next;
            } else {
                l.add(pNode.element);
                pNode = pNode.next;
                qNode = qNode.next;
            }
        }
        return l;
    }


    /**
     * Constructs an IndexOutOfBoundsException detail message.
     * Of the many possible refactorings of the error handling code,
     * this "outlining" performs best with both server and client VMs.
     */
    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }
}
