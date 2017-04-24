package com.coding.basic.list;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Korben's LinkedList
 *
 * Created by Korben on 18/02/2017.
 */
public class KLinkedList<T> implements KList<T> {

    private int size;

    private Node<T> head;

    private Node<T> last;

    public KLinkedList() {
        this.head = new Node<>(null);
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean contains(Object o) {
        Node node = this.head;
        while (node.next != null) {
            node = node.next;
            if (Objects.equals(node.data, o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object[] toArray() {
        throw new IllegalStateException("方法未实现");
    }

    @Override
    public boolean add(T o) {
        if (this.last == null) {
            this.last = new Node<>(o);
            this.head.next = this.last;
        } else {
            Node<T> oldLast = this.last;
            this.last = new Node<>(o);
            oldLast.next = this.last;
        }
        this.size++;
        return true;
    }

    @Override
    public boolean remove(T o) {
        Node node = this.head;
        Node preNode;
        while (node.next != null) {
            preNode = node;
            node = node.next;
            if (Objects.equals(node.data, o)) {
                removeNode(preNode, node);
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        this.head.next = null;
        this.last = null;

        this.size = 0;
    }

    @Override
    public T get(int index) {
        return getNode(index).data;
    }

    @Override
    public T set(int index, T element) {
        Node<T> node = getNode(index);
        node.data = element;
        return element;
    }

    @Override
    public void add(int index, T element) {
        ensureIndex(index);

        Node<T> node = this.head;
        Node<T> preNode = node;
        for (int i = 0; i <= index; i++) {
            preNode = node;
            node = node.next;
        }

        Node<T> newNode = new Node<>(element);
        newNode.next = node;
        preNode.next = newNode;

        this.size++;
    }

    @Override
    public T remove(int index) {
        ensureIndex(index);

        Node<T> node = this.head;
        Node<T> preNode = this.head;
        for (int i = 0; i <= index; i++) {
            preNode = node;
            node = node.next;
        }

        preNode.next = node.next;
        if (node == last) {
            last = preNode;
        }
        this.size--;
        return node.data;
    }

    @Override
    public int indexOf(T o) {
        Node node = this.head;
        int index = 0;
        while (node.next != null) {
            node = node.next;
            if (Objects.equals(node.data, o)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    @Override
    public KIterator<T> iterator() {
        return new Iterator();
    }

    private Node<T> getNode(int index) {
        ensureIndex(index);

        Node<T> node = this.head;
        for (int i = 0; i <= index; i++) {
            node = node.next;
        }
        return node;
    }

    /**
     * 把该链表逆置
     * 例如链表为 3->7->10 , 逆置后变为  10->7->3
     */
    public void reverse() {
        // 链表为空, 不用逆置, 返回
        if (this.head.next == null) {
            return;
        }

        // 只有一个元素, 不用逆置, 返回
        if (this.head.next.next == null) {
            return;
        }

        this.last = this.head.next;

        Node<T> preNode = this.head.next;
        Node<T> node = preNode.next;
        Node<T> nextNode = node.next;
        while (nextNode != null) {
            node.next = preNode;

            preNode = node;
            node = nextNode;
            nextNode = nextNode.next;
        }

        node.next = preNode;
        this.head.next = node;
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

        int halfIndex = (this.size) / 2;

        if (halfIndex >= 0) {
            this.head.next = getNode(halfIndex);
        }

        this.size -= halfIndex;
    }

    /**
     * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
     */
    public void remove(int i, int length) {
        ensureIndex(i);
        ensureIndex(i + length - 1);

        int newSize = this.size - length;

        // 得到被删除起始元素的前一个节点
        Node<T> preNode;
        if (i == 0) {
            preNode = this.head;
        } else {
            preNode = getNode(i - 1);
        }

        // 得到最后一个被删除的元素
        Node<T> node = preNode.next;
        while (--length > 0) {
            node = node.next;
        }

        // 删除元素
        preNode.next = node.next;

        // 如果被删除的元素包含最后的节点, 改变最后节点
        if (i + length == this.size - 1) {
            this.last = preNode;
        }

        this.size = newSize;
    }

    /**
     * 假定当前链表和list均包含已升序排列的整数
     * 从当前链表中取出那些list所指定的元素
     * 例如当前链表 = 11->101->201->301->401->501->601->701
     * listB = 1->3->4->6
     * 返回的结果应该是[101,301,401,601]
     */
    public int[] getElements(KLinkedList list) {
        if (list == null || list.size == 0) {
            return new int[0];
        }

        List<Integer> resultList = new ArrayList<>();

        Node node = this.head;

        KIterator listIterator = list.iterator();
        int elementIndex = (int) listIterator.next();
        for (int i = 0; i < this.size; i++) {
            node = node.next;
            if (elementIndex == i) {
                resultList.add((Integer) node.data);
                if (listIterator.hasNext()) {
                    elementIndex = (int) listIterator.next();
                } else {
                    break;
                }
            }
        }

        // list 2 array
        int[] result = new int[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            result[i] = resultList.get(i);
        }
        return result;
    }

    /**
     * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 从当前链表中中删除在list中出现的元素
     */
    public void subtract(KLinkedList<Integer> list) {
        if (list == null || list.size() == 0) {
            return;
        }

        KIterator<Integer> listIterator = list.iterator();

        Node node = this.head;
        Node pre;
        while (listIterator.hasNext()) {

            int listData = listIterator.next();
            while (node.next != null) {
                pre = node;
                node = node.next;

                if (listData == (int) node.data) {
                    removeNode(pre, node);
                } else if (listData < (int) node.data) {
                    break;
                }
            }
        }
    }

    /**
     * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
     */
    public void removeDuplicateValues() {
        if (this.size() <= 1) {
            return;
        }

        Node node = this.head;
        Node pre;

        while (node.next.next != null) {
            pre = node;
            node = node.next;

            if (node.data == node.next.data) {
                removeNode(pre, node);
            }
        }
    }

    /**
     * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
     */
    public void removeRange(int min, int max) {
        Node preMinNode = null;
        Node maxNode = null;
        Node node = this.head;

        // get min and max
        int minIndex = -1;
        int maxIndex = -1;
        while (node.next != null) {
            maxIndex++;
            if (preMinNode == null) {
                minIndex++;
                if ((int) node.next.data == min) {
                    preMinNode = node;
                }
            } else if ((int) node.next.data == max) {
                maxNode = node.next;
            } else if (maxNode != null && (int) node.next.data > (int) maxNode.data) {
                break;
            }

            node = node.next;
        }

        // do remove
        if (preMinNode != null) {
            if (maxNode != null) {
                preMinNode.next = maxNode.next;
                this.size -= maxIndex - minIndex + 1;
                if (preMinNode.next == null) {
                    this.last = preMinNode;
                }
            } else {
                preMinNode.next = null;
                this.size = minIndex;
                this.last = preMinNode;
            }
        }
    }

    /**
     * 123456789876543
     * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
     * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
     */
    public KLinkedList intersection(KLinkedList list) {
        if (list == null || list.size() == 0) {
            return copyList(this);
        }
        if (this.isEmpty()) {
            return copyList(list);
        }

        KLinkedList resultList = new KLinkedList();

        KIterator listIterator = list.iterator();
        KIterator iterator = this.iterator();
        Integer listValue = (Integer) listIterator.next();
        Integer value = (Integer) iterator.next();
        for (int i = 0; i < list.size() + this.size() - 1; i++) {

            if (listValue == null) {
                if (value != null) {
                    resultList.add(value);
                    continue;
                } else {
                    break;
                }
            }

            if (value == null) {
                if (listValue != null) {
                    resultList.add(listValue);
                    listValue = (Integer) listIterator.next();
                    continue;
                } else {
                    break;
                }
            }

            if (listValue <= value) {
                resultList.add(listValue);
                listValue = (Integer) listIterator.next();
                value = (Integer) iterator.next();
            } else {
                resultList.add(value);
                value = (Integer) iterator.next();
            }
        }

        return resultList;
    }

    private KLinkedList copyList(KLinkedList linkedList) {
        if (linkedList == null) {
            return null;
        }

        KLinkedList result = new KLinkedList();
        KIterator iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }

        return result;
    }

    private void ensureIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void removeNode(Node pre, Node node) {
        pre.next = node.next;
        this.size--;
        if (this.last == node) {
            this.last = pre;
        }
    }

    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
        }
    }

    private class Iterator implements KIterator<T> {
        private Node<T> node;

        Iterator() {
            this.node = head;
        }

        @Override
        public boolean hasNext() {
            return node.next != null;
        }

        @Override
        public T next() {
            if (hasNext()) {
                node = node.next;
                return node.data;
            }
            return null;
        }
    }
}
