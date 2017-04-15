package link;

import list.ArrayList;
import list.List;

import java.util.*;

/**
 * Created by gongxun on 2017/3/13.
 */
public class LinkedList<T> {
    private Node<T> head;
    private int size = 0;

    public void add(T o) {
        if (head == null) {
            head = new Node<T>(null, o);
            size++;
        } else
            addLast(o);
    }

    private Node<T> getLast() {
        Node<T> last = head;
        while (last.next != null) {
            last = last.next;
        }
        return last;
    }

    private Node<T> getNodeIndex(int index) {
        if (index > size - 1)
            throw new IndexOutOfBoundsException("size : " + size + ", index : " + index);
        Node target = head;
        for (int i = 0; i < size; i++) {
            if (i == index)
                return target;
            target = target.next;
        }
        return null;
    }

    public void add(int index, T o) {
        Node<T> node = getNodeIndex(index - 1);
        Node<T> nextNode = node.next;
        node.next = new Node<T>(nextNode, o);
        size++;
    }

    public T get(int index) {
        Node<T> node = getNodeIndex(index);
        return node.data;
    }

    public T remove(int index) {
        Node<T> prev = getNodeIndex(index - 1);
        Node<T> now = getNodeIndex(index);
        prev.next = now.next;
        size--;
        return now.data;
    }

    public int size() {
        return size;
    }

    public void addFirst(T o) {
        if (head != null)
            head = new Node<T>(null, o);
        else {
            Node newNode = new Node(head, o);
            head = newNode;
        }
        size++;
    }

    public void addLast(T o) {
        add(size, o);
    }

    public T removeFirst() {
        Node<T> removeNode = head;
        if (head != null)
            head = head.next;
        size--;
        return removeNode == null ? null : removeNode.data;
    }

    public T removeLast() {
        Node<T> last = getNodeIndex(size - 1);
        Node<T> prev = getNodeIndex(size - 2);
        prev.next = null;
        size--;
        return last.data;
    }

    public Iterator iterator() {
        return null;
    }

    private int getIndex(Node<T> node) {
        Node temp = head;
        int index = 0;
        while (temp != null) {
            if (temp == node) {
                return index;
            }
        }
        return -1;
    }

    private int getIndexByData(T data) {
        Node temp = head;
        int index = 0;
        while (temp != null) {
            if ((data == null && temp.data == null) || temp.data.equals(data))
                return index;
            index++;
            temp = temp.next;
        }
        return -1;
    }


    private static class Node<T> {
        T data;
        Node<T> next;

        Node(Node next, T data) {
            this.next = next;
            this.data = data;
        }

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node temp = head;
        while (temp != null) {
            sb.append(temp.data).append("-->");
            temp = temp.next;
        }
        return sb.toString().substring(0, sb.lastIndexOf("-->"));
    }

    /**
     * 把该链表逆置
     * 例如链表为 3->7->10 , 逆置后变为  10->7->3
     */
    public void reverse() {
        Node cur = null;
        Node prev = null;
        while (head != null) {
            cur = head;
            head = head.next;
            cur.next = prev;
            prev = cur;
        }
        head = cur;
    }

    /**
     * 删除一个单链表的前半部分
     * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
     * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10
     */
    public void removeFirstHalf() {
        head = getNodeIndex(size / 2);
    }

    /**
     * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
     *
     * @param i
     * @param length
     */
    public void remove(int i, int length) {
        if (size <= (i + length) || i < 0)
            throw new IndexOutOfBoundsException("size : " + size + ", i + length : " + (i + length));
        Node<T> rightNode = getNodeIndex(i + length);
        if (i == 0)
            head = rightNode;
        else {
            Node<T> leftNode = getNodeIndex(i - 1);
            leftNode.next = rightNode;
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
    public Object[] getElements(LinkedList<Integer> list) {
        Object[] result = new Object[list.size];
        if (list != null) {
            for (int i = 0; i < list.size; i++) {
                result[i] = get(list.get(i));
            }
        }
        return result;
    }

    /**
     * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 从当前链表中中删除在list中出现的元素
     *
     * @param list
     */

    public void subtract(LinkedList<T> list) {
        if (list != null && list.size > 0) {
            for (int i = 0; i < list.size; i++) {
                int index = getIndexByData(list.get(i));
                if (index != -1)
                    remove(index);
                else
                    throw new RuntimeException("wrong element of removed list");
            }
        }
    }

    /**
     * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
     */
    public void removeDuplicateValues() {
        Node<T> temp = head;
        List<T> list = new ArrayList<T>();
        int index = 0;
        while (temp != null) {
            if (list.contains(temp.data))
                remove(index--);
            else
                list.add(temp.data);
            temp = temp.next;
            index++;
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
        Integer first = (Integer) get(0);
        Integer last = (Integer) getLast().data;
        if (first > max || last < min)
            return;
        List<Integer> indexRange = new ArrayList<Integer>();
        Node<Integer> temp = (Node<Integer>) head;
        int index = 0;
        while (temp != null) {
            if (temp.data >= min && temp.data <= max) {
                indexRange.add(index);
            }
            index++;
            temp = temp.next;
        }
        remove(indexRange.get(0), indexRange.size());
    }

    /**
     * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
     * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
     *
     * @param list
     */
    public LinkedList intersection(LinkedList<Integer> list) {
        LinkedList<Integer> newList = new LinkedList<Integer>();
        merge(newList, (Node<Integer>) this.head, list.head);
        return newList;
    }

    private void merge(LinkedList<Integer> newList, Node<Integer> thisHead, Node<Integer> mergeHead) {
        if (thisHead == null && mergeHead == null)
            return;
        if (thisHead == null) {
            //无论是否包含，有元素的链表必须指向next
            if (!newList.contains(mergeHead.data))
                newList.add(mergeHead.data);
            mergeHead = mergeHead.next;
            merge(newList, null, mergeHead);
        }
        if (mergeHead == null) {
            if (!newList.contains(thisHead.data))
                newList.add(thisHead.data);
            thisHead = thisHead.next;
            merge(newList, thisHead, null);
        }
        //要再进行一次判断是因为递归到最底层return之后，返回上一层时某个链表已经为null了，但是上一层还是会将剩下的执行完
        if (thisHead != null && mergeHead != null) {
            if (thisHead.data < mergeHead.data && !newList.contains(thisHead.data)) {
                newList.add(thisHead.data);
                thisHead = thisHead.next;
                merge(newList, thisHead, mergeHead);
            } else if (!newList.contains(mergeHead.data)) {
                newList.add(mergeHead.data);
                mergeHead = mergeHead.next;
                merge(newList, thisHead, mergeHead);
            }
        }
    }

    private boolean contains(Integer data) {
        int index = this.getIndexByData((T) data);
        return index != -1;
    }
}
