package datastructure.linkedlist;

import java.util.NoSuchElementException;

import datastructure.Iterator;
import datastructure.List;

public class LinkedList implements List {

    private Node head;

    private int size = 0;

    @Override
    public void add(Object o) {
        if (head == null) {
            head = new Node(o);
        } else {
            Node tail = head;
            while (tail.next != null) {
                tail = tail.next;
            }
            Node node = new Node(o);

            tail.next = node;
        }
        size++;
    }

    @Override
    public void add(int index, Object o) {
        rangeCheckForAdd(index);
        if (index == 0) {
            Node node = new Node(o);
            node.next = head;
            head = node;
        } else {
            Node preDest = head;
            for (int i = 0; i < index - 1; i++) {
                preDest = preDest.next;
            }
            Node node = new Node(o);
            node.next = preDest.next;
            preDest.next = node;
        }

        size++;
    }

    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public Object get(int index) {
        rangeCheck(index);

        Node dest = head;
        for (int i = 0; i < index; i++) {
            dest = dest.next;
        }
        return dest.data;
    }

    private void rangeCheck(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public Object remove(int index) {
        rangeCheck(index);

        Node preDest = head;
        for (int i = 0; i < index - 1; i++) {
            preDest = preDest.next;
        }
        Node dest = preDest.next;
        preDest.next = dest.next;

        size--;
        return dest.data;
    }

    @Override
    public int size() {
        return size;
    }

    public void addFirst(Object o) {
        Node node = new Node(o);
        node.next = head;
        head = node;
        size++;
    }

    public void addLast(Object o) {
        Node lastNode = head;
        while (lastNode.next != null) {
            lastNode = lastNode.next;
        }

        Node node = new Node(o);
        lastNode.next = node;
        size++;
    }

    public Object removeFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node target = head;
        head = head.next;
        size--;
        return target.data;
    }

    public Object removeLast() {
        if (head == null) {
            throw new NoSuchElementException();
        }

        Node preDest = head;
        while (preDest.next.next != null) {
            preDest = preDest.next;
        }
        Node dest = preDest.next;
        preDest.next = null;

        size--;
        return dest.data;
    }

    public Iterator iterator() {
        return null;
    }


    private static class Node {
        Object data;
        Node next;

        Node(Object data) {
            this.data = data;
            next = null;
        }
    }

    // =========================第三周作业=========================

    /**
     * 把该链表逆置 例如链表为 3->7->10 , 逆置后变为 10->7->3
     */
    public void reverse() {
        Node reverseNode = null;
        while (head != null) {
            Node temp = head;
            head = head.next;
            temp.next = reverseNode;
            reverseNode = temp;
        }
        head = reverseNode;
    }

    /**
     * 删除一个单链表的前半部分 例如：list = 2->5->7->8 , 删除以后的值为 7->8 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10
     */
    public void removeFirstHalf() {
        int newStartIndex = size / 2;
        for (int i = 0; i < newStartIndex; i++) {
            head = head.next;
        }
        size = size - newStartIndex;
    }

    /**
     * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
     * 
     * @param i
     * @param length
     */
    public void remove(int i, int length) {
        if (i < 0) {
            throw new IllegalArgumentException();
        }
        if (i + length >= size) {
            length = size - i;
        }

        if (i == 0) {
            for (int j = 0; j < length; j++) {
                head = head.next;
            }
        } else {
            Node beforeRemoveStartNode = head;
            for (int j = 0; j < i - 1; j++) {
                beforeRemoveStartNode = beforeRemoveStartNode.next;
            }

            Node removeEndNode = beforeRemoveStartNode;
            for (int j = 0; j < length; j++) {
                removeEndNode = removeEndNode.next;
            }

            beforeRemoveStartNode.next = removeEndNode.next;
        }

        size = size - length;
    }

    /**
     * 假定当前链表和list均包含已升序排列的整数 从当前链表中取出那些list所指定的元素 例如当前链表 = 11->101->201->301->401->501->601->701
     * listB = 1->3->4->6 返回的结果应该是[101,301,401,601]
     * 
     * @param list
     */
    public int[] getElements(LinkedList list) {
        checkList(list);

        int[] dest = new int[list.size];
        int arrayNum = 0;
        Node temp = head;
        int n = (int) list.get(0);
        for (int i = 0; i < n; i++) {
            temp = temp.next;
        }
        dest[arrayNum++] = (int) temp.data;

        for (int i = 1; i < list.size; i++) {
            int num = (int) list.get(i) - (int) list.get(i - 1);
            for (int j = 0; j < num; j++) {
                temp = temp.next;
            }
            dest[arrayNum++] = (int) temp.data;
        }
        return dest;
    }

    private void checkList(LinkedList list) {
        for (int i = 0; i < list.size; i++) {
            if ((int) list.get(i) < 0 || (int) list.get(i) >= size) {
                throw new IllegalArgumentException("list中的元素位置越界");
            }
        }
    }

    /**
     * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。 从当前链表中中删除在list中出现的元素
     * 
     * @param list
     */

    public void subtract(LinkedList list) {
        if (list == null || list.size == 0 || this.size == 0) {
            return;
        }

        int thisIndex = 0;
        int listIndex = 0;
        Node temp = head;
        while (true) { // 后续需要优化替换remove()方法
            if ((int) temp.data < (int) list.get(listIndex)) {
                temp = temp.next;
                thisIndex++;
            } else if ((int) temp.data == (int) list.get(listIndex)) {
                this.remove(thisIndex);
                temp = temp.next;
                thisIndex++;
                listIndex++;
            } else {
                listIndex++;
            }

            if (thisIndex >= this.size || listIndex >= list.size) {
                break;
            }
        }
    }

    /**
     * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
     */
    public void removeDuplicateValues() {
        if (this.size == 0) {
            return;
        }

        Node subHead = head;
        Node subTail = head;

        while (true) {
            if (subTail == null) {
                subHead.next = null; // 清除尾部重复的元素
                break;
            }
            if ((int) subTail.data == (int) subHead.data) {
                if (!(subTail == subHead)) { // 判断两个指针是否指向同一个地方
                    this.size--;
                }
                subTail = subTail.next;
            } else {
                subHead.next = subTail;
                subHead = subHead.next;
            }
        }
    }

    /**
     * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
     * 
     * @param min
     * @param max
     */
    public void removeRange(int min, int max) {
        if (this.size == 0) {
            return;
        }

        if ((int) head.data > max) {
            throw new IllegalArgumentException();
        }

        int length = 0;
        Node subList = new Node(null);
        Node temp = subList;
        while (true) {
            if (head == null) {
                break;
            }
            if ((int) head.data <= min || (int) head.data >= max) {
                temp.next = head;
                temp = temp.next;
                length++;
            }
            head = head.next;
        }
        temp.next = null; // 去掉尾部多余数据
        head = subList.next;
        size = length;
    }

    /**
     * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同） 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
     * 
     * @param list
     */
    public LinkedList intersection(LinkedList list) {
        if (this.size == 0 || list.size == 0) {
            return null;
        }

        Node tempHead = head;
        int listIndex = 0;

        LinkedList newList = new LinkedList();
        while (true) {
            if (tempHead == null || listIndex >= list.size) {
                break;
            }

            if ((int) tempHead.data < (int) list.get(listIndex)) {
                tempHead = tempHead.next;
            } else if ((int) tempHead.data > (int) list.get(listIndex)) {
                listIndex++;
            } else {
                newList.add(tempHead.data);

                tempHead = tempHead.next;
                listIndex++;
            }
        }

        return newList;
    }
}
