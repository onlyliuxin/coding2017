package task3.basic;

import task1.basic.WIterator;
import task1.basic.WList;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Created by wanc on 2017/2/21.
 * 实现单向链表集合
 */
public class WLinkedList implements WList {
    /**
     * 首节点
     */
    private Node head;
    /**
     * 计数
     */
    private int size = 0;

    /**
     * 检查是否越界 利用jdk源码的检测方法
     */
    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    /**
     * JDK 源码检测方法
     *
     * @param index
     * @return
     */
    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    /**
     * JDK 源码 错误信息
     *
     * @param index
     * @return
     */
    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    /**
     * JDK 源码检测方法
     *
     * @param index
     * @return
     */
    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    /**
     * JDK 源码检测方法
     *
     * @param index
     * @return
     */
    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    /**
     * 获取对应下标的节点
     */
    Node node(int index) {
        Node x = head;
        for (int i = 0; i < index; i++)
            x = x.next;
        return x;
    }

    /**
     * 在末尾添加数据
     *
     * @param o
     */
    public void add(Object o) {

        if (head == null)
            head = new Node(o, null);
        else {
            final Node lastNode = node(size - 1);
            final Node newNode = new Node(o, null);
            lastNode.next = newNode;
        }
        size++;
    }

    /**
     * 指定位置添加数据
     *
     * @param index
     * @param o
     */
    public void add(int index, Object o) {
        checkPositionIndex(index);
        if (size == index)
            add(o);
        else {
            final Node prevNode = node(index - 1);
            final Node nextNode = prevNode.next;
            final Node newNode = new Node(o, nextNode);
            prevNode.next = newNode;
            size++;
        }
    }

    /**
     * 获取指定索引数据
     *
     * @param index
     * @return
     */
    public Object get(int index) {
        return node(index).data;
    }

    /**
     * 移除指定索引数据
     *
     * @param index
     * @return
     */
    public Object remove(int index) {
        checkElementIndex(index);
        final Node prevNode = node(index - 1);
        final Node x = prevNode.next;
        if (index - 1 < 0) {
            prevNode.next = null;
            head = x;
        } else {
            final Node nextNode = x.next;
            prevNode.next = nextNode;
            x.next = null;
        }
        size--;
        return x.data;
    }

    public Object remove(Object element) {
        Node x = head;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(x.data, element))
                x = x.next;
        }
        return null;
    }

    /**
     * 返回数量
     *
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * 在链首添加数据
     *
     * @return
     */
    public void addFirst(Object o) {
        final Node h = head;
        final Node newNode = new Node(o, h);
        head = newNode;
        size++;
    }

    /**
     * 在链尾添加数据
     *
     * @return
     */
    public void addLast(Object o) {
        add(o);
    }

    /**
     * 移除链首数据
     *
     * @return
     */
    public Object removeFirst() {
        final Node h = head;
        if (h == null)
            throw new NoSuchElementException();
        final Node newFirst = h.next;
        h.next = null;
        head = newFirst;
        size--;
        return h.data;
    }

    /**
     * 移除链尾数据
     *
     * @return
     */
    public Object removeLast() {
        final Node prev = node(size - 1 - 1);
        final Node l = prev.next;
        prev.next = null;
        l.next = null;
        size--;
        return l.data;
    }

    /**
     * 获取迭代器
     *
     * @return
     */
    public WIterator iterator() {
        return new LinkedItr();
    }

    public ListWIterator listIterator() {
        return new LinkedItr();
    }

    /**
     * 迭代器实现内部类
     *
     * @return
     */
    private class LinkedItr implements ListWIterator {
        int cursor = 0;//游标
        int delCursor = -1;

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public Object next() {
            int i = cursor;
            if (i > (size - 1)) throw new NoSuchElementException();
            Node current = node(i);
            if (current == null) throw new IndexOutOfBoundsException();
            delCursor = i;
            cursor = i + 1;
//            System.out.println("i="+i+"-"+current.data);
            return current.data;
        }

        @Override
        public void remove() {
            if (delCursor < 0) {
                throw new IllegalStateException();
            }
            try {
                WLinkedList.this.remove(delCursor);
                if (cursor > 0)
                    cursor--;
                delCursor = -1;
            } catch (IndexOutOfBoundsException e) {
                throw new ConcurrentModificationException();
            }

        }
    }

    /**
     * 节点内部类 用于保存数据
     */
    private static class Node {
        Object data;
        Node next;

        Node(Object data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    /**
     * 重写toString 方便打印
     *
     * @return
     */
    @Override
    public String toString() {
        String elementStr = "";
        Node p = head;
        while (p != null) {
            elementStr += p.data + ",";
            p = p.next;
        }

        return "WLinkedList: { size=" + size + ", elementData=" + "["
                + elementStr.substring(0, elementStr.length() - 1) + "]" + " }";
    }


    /**
     * 把该链表逆置
     * 例如链表为 3->7->10 , 逆置后变为  10->7->3
     */
    public void reverse() {
        if (head == null) return;
        Node[] nodes = new Node[size];
        Node x = head;
        for (int i = 0; i < size; i++) {
            nodes[i] = x;
            x = x.next;
        }

        head = nodes[nodes.length - 1];
        Node tmp = head;
        for (int j = nodes.length - 2; j >= 0; j--) {
            Node c = nodes[j];
            tmp.next = c;
            tmp = c;
        }
    }

    /**
     * 删除一个单链表的前半部分
     * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
     * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10
     */
    public void removeFirstHalf() {
        int len = size / 2;
        remove(0, len);

    }

    /**
     * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
     *
     * @param i
     * @param length
     */
    public void remove(int i, int length) {
        checkElementIndex(i);
        checkElementIndex(i + length - 1);
        if (0 == length) return;
        int a = i - 1;
        Node p = node(a);//前一个
        Node f = p.next;//删除第一个
        Node l = node(i + length - 1);//删除最后一个
        Node h = l.next;//后一个
        //去掉引用 等待GC回收
        Node tmp = f;
        while (tmp != l) {
            Node n = tmp.next;
            tmp.next = null;
            tmp = n;
        }
        l.next = null;

        if (0 == i)
            head = h;
        else
            p.next = h;
        size -= length;
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
    public int[] getElements(WLinkedList list) {
        if (list == null) return null;
        int[] arr = new int[list.size];
        WIterator itr = list.iterator();
        int i = 0;
        while (itr.hasNext()) {
            arr[i] = (int) node((int) itr.next()).data;
            i++;
        }
        return arr;
    }

    interface ListWIterator extends WIterator {
        void remove();
    }

    /**
     * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 从当前链表中中删除在list中出现的元素
     *
     * @param list
     */

    public void subtract(WLinkedList list) {
        if (list != null && list.size > 0) {
            WIterator itr = list.iterator();
            while (itr.hasNext()) {
                ListWIterator sourItr = listIterator();
                Object value = itr.next();
                while (sourItr.hasNext()) {
                    Object souValue = sourItr.next();
//                    System.out.println(value+"-"+souValue);
                    if (value.equals(souValue)) {
//                        System.out.println(value+"-"+sourItr.next());
                        sourItr.remove();
//                        System.out.println("remove");
                    }
                }
//                System.out.println("---------------------------------");
            }
        }
    }

    public Object[] toArray() {
        Object[] newObj = new Object[size];
        if (head == null) return new Object[]{};
        Node x = head;
        for (int i = 0; i < size; i++) {
            newObj[i] = x.data;
            x = x.next;
        }
        return newObj;
    }

    /**
     * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
     */
    public void removeDuplicateValues() {
        if (head == null) return;
        Node n1 = head;
        Node n2 = head.next;
        while (n1 != null && n2 != null) {
            if (Objects.equals(n1.data, n2.data)) {
                n2 = n2.next;
                n1.next = n2;
                size--;
            } else {
                n1 = n2;
                n2 = n2.next;
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
        Node x = head;
        boolean mingetflag = false;
        boolean maxgetflag = false;
        Node minEndNode = null;
        Node maxStartNode = null;
        while (x.next != null) {
            if ((int) x.data <= min && !mingetflag) {
                minEndNode = x;
            }else {
                mingetflag = true;
            }
            if ((int) x.data >= max && !maxgetflag) {
                maxStartNode = x;
                maxgetflag = true;
            }
            if (maxgetflag && mingetflag)
                break;
            x = x.next;
        }
        System.out.println(minEndNode.data + "-" + maxStartNode.data+"-"+maxStartNode.next.data);
        if (minEndNode != null && maxStartNode != null) {
            clear(minEndNode, maxStartNode);
        }
        if (minEndNode == null && maxStartNode != null) {
            clear(null, maxStartNode);
        }
        if (minEndNode != null && maxStartNode == null) {
            clear(minEndNode, null);
        }
    }

    private void clear(Node start, Node end) {
        if (start == null)
            start = head;
        Node x = start.next;
        while (x != null) {
            if (end != null && Objects.equals(x.data, end.data)) {
                break;
            }
            Node next = x.next;
            x.data = null;
            x.next = null;
            x = next;
            size--;
        }
        start.next = end;
    }

    /**
     * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
     * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
     *
     * @param list
     */
    public WLinkedList intersection(WLinkedList list) {
        WLinkedList result = new WLinkedList();
        Node n1=this.head,n2=list.head;
        while (n1!=null&&n2!=null){
            if (Objects.equals(n1.data,n2.data)){
                result.add(n1.data);
                n1=n1.next;
                n2=n2.next;
            }else if ((int)n1.data>(int)n2.data){
                n2=n2.next;
            }else {
                n1=n1.next;
            }
        }
        return result;
    }

}
