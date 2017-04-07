package com.circle.collection;

import java.util.*;

/**
 * Created by keweiyang on 2017/3/13.
 */
public class LinkedListV2<E> {

    private int size;
    private Node<E> first;

    /**
     * 从尾部插入数据
     *
     * @param e
     */
    public void add(E e) {
        Node<E> node = new Node(e, null);
        if (size == 0) {
            first = node;
        } else {
            Node<E> current = first;
            while (current.next != null) {
                current = current.next;
            }
            current.next = node;
        }
        size++;
    }

    public void add(int index, E e) {
        rangeCheck(index);
        Node<E> node = new Node(e, null);
        Node<E> current = first;
        Node<E> prev = null;
        if (index == 0) {
            node.next = first;
            first = node;
        } else {
            int i = 1;
            prev = current;
            current = current.next;
            while (current != null) {

                if (i == index) {
                    break;
                }
                i++;
                prev = current;
                current = current.next;

            }
            node.next = current;
            prev.next = node;

        }
        size++;
    }

    public E get(int index) {
        rangeCheck(index);
        Node<E> current = first;

        int i = 0;
        if (current == null) {
            throw new NoSuchElementException("链表为空");
        } else {
            while (current.next != null) {

                if (i == index) {
                    break;
                }
                i++;
                current = current.next;
            }
        }
        return (E) current.item;
    }

    private void rangeCheck(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("索引越界");
        }
    }

    public int size() {
        return this.size;
    }


    public E removeFirst() {
        if (first == null) {
            throw new IllegalStateException("链表为空");
        } else {
            Node<E> current = first;
            first = first.next;
            size--;
            return (E) current.item;
        }
    }

    public E removeLast() {
        if (first == null) {
            throw new IllegalStateException("链表为空");
        } else {
            Node<E> current = first;
            while (current.next != null) {
                current = current.next;
            }
            size--;
            return (E) current.item;

        }
    }

    /**
     * 把该链表逆置
     * 例如链表为3->7->10,逆置后变为 10->7->3
     */
    public void reverse() {
        Node<E> current = first;
        LinkedListV2<E> list = new LinkedListV2();
        while (current != null) {

            list.add(0, current.item);
            current = current.next;
        }

        Iterator<E> it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());

        }
    }

    /**
     * 删除一个单链表的前半部分
     * 例如：list=2->5->7->8,删除以后的值为 7->8
     * 如果 list=2->5->7->8 ->10,删除以后的值为7，8，10
     */
    public void removeFirstHalf() {
        Node<E> stepByOne = first;
        Node<E> stepByTwo = first;

        while (stepByTwo.next != null && stepByTwo.next.next != null) {

            stepByTwo = stepByTwo.next.next;
            stepByOne = stepByOne.next;
        }

        if (stepByTwo.next != null) {
            stepByOne = stepByOne.next;
        }

        //打印单链表的前半部分
        while (stepByOne != null) {
            System.out.println(String.valueOf(stepByOne.item));
            stepByOne = stepByOne.next;

        }


    }

    /**
     * 从第i个元素开始，删除length个元素，注意i从0开始
     *
     * @param i
     * @param length
     */
    public void remove(int i, int length) {
        rangeCheck(i);
        if (length <= 0) {
            throw new IllegalStateException("请输入正确的个数");
        }
        Node<E> current = first;
        Node<E> prev = null;
        int a = 0;
        while (current != null) {
            prev = current;
            current = current.next;
            a++;
            if (a == i) {
                break;
            }
        }

        if ((size - i + 1) <= length) {
            prev.next = null;
            size -= (size - i);
        } else {
            Node<E> node = prev;
            int temp = length;
            while (temp > 0) {
                current = current.next;
                temp--;
            }
            prev.next = current;
            size -= length;

        }


    }

    /**
     * 假定当前链表和list均包含已升序排列的整数
     * 从当前链表中取出哪些list所指定的元素
     * 例如当前链表=11->101->201->301->401->501->601->701
     * listB =1->3->4->6
     * 返回的结果应该是[101,301,401,601]
     *
     * @param list
     * @return
     */
    public E[] getElements(LinkedListV2<Integer> list) {

        Iterator it = list.iterator();
        LinkedListV2<E> getElementsList = new LinkedListV2();
        while (it.hasNext()) {
            Integer integer = (Integer) it.next();
            E e = get(integer);
            getElementsList.add(e);
        }


        return (E[]) getElementsList.toArray();
    }

    public Object[] toArray() {
        Object[] result = new Object[size];

        int i = 0;
        for (Node<E> x = first; x != null; x = x.next) {
            result[i] = x.item;
            i++;
        }

        return result;
    }

    /**
     * 已知链表的元素以值递增有序排列，并以单链表做存储结构
     * 从当前链表中删除在list中出现的元素
     *
     * @param list
     */
    public void subtract(LinkedListV2<Integer> list) {
        Iterator it = list.iterator();
        Node<E> current = first;
        Node<E> prev = null;

        while (it.hasNext()) {
            Integer integer = (Integer) it.next();


            while (integer > (Integer) (current.item) && current != null) {
                prev = current;
                current = current.next;
            }

            if (current != null && integer.equals(current.item)) {
                if (current == first) {
                    first = first.next;
                    current = first;
                } else {
                    prev.next = current.next;
                    current = current.next;
                }
                size--;
            } else {
                System.out.println("该链表中没有该元素");
            }
        }
    }

    /**
     * 已知当前链表中的元素以值递增有序排列，并以单链表做存储结构
     * 删除表中所有值相同的多余元素
     */
    public void removeDuplicateValues() {
        Node current = first;
        Node innerCurrent = null;
        while (current != null) {

            innerCurrent = current.next;
            while (innerCurrent != null) {
                if (!(innerCurrent.item).equals(current.item)) {
                    break;
                }
                innerCurrent = innerCurrent.next;
                size--;
            }

            current.next = innerCurrent;
            current = current.next;

        }

    }

    /**
     * 已知链表中的元素以值递增有序排列，并以单链表作为存储结构
     * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
     *
     * @param min
     * @param max
     */
    public void removeRange(int min, int max) {
        Node<E> current = first;
        Node<E> prev = first;
        while (current != null) {
            if ((Integer)(current.item) > min && (Integer)(current.item) < max) {
                if (current == first) {
                    first = first.next;
                    current = first;
                    prev = first;
                }else{
                    prev.next = current.next;

                }
                size--;
            }
            prev = current;
            current = current.next;
        }


    }

    /**
     * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
     * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C的元素有依值递增有序排列
     *
     * @param list
     * @return
     */
    public LinkedListV2<E> intersection(LinkedListV2<E> list) {

        Iterator it = list.iterator();
        Node<E> current = first;

        LinkedListV2<E> newList = new LinkedListV2();

        if (list.size == 0 || this.size == 0) {
            return null;
        }

        if ((Integer) this.first.item > (Integer) list.get(list.size() - 1)) {
            return null;
        }

        if ((Integer) this.removeLast() < (Integer) list.get(0)) {
            return null;
        }

        while (it.hasNext()) {
            Integer integer = (Integer) it.next();

            while (current != null && integer > (Integer) (current.item)) {
//                prev = current;
                current = current.next;
            }

            if (current != null && integer.equals(current.item)) {
                newList.add(current.item);

            }
        }


        return newList;
    }


    private static class Node<E> {
        E item;
        Node next;

        public Node(E item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

    public Iterator iterator() {
        return new Iterator() {
            int nextIndex = 0;

            @Override
            public boolean hasNext() {
                return nextIndex < size;
            }

            @Override
            public E next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                E e = get(nextIndex);
                nextIndex++;

                return e;
            }
        };
    }


    public static void main(String[] args) {
        java.util.LinkedList list;

    }
}
