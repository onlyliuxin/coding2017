package third.basic;

import java.util.Objects;

/**
 * ${}
 * Created by spark_lizhy on 2017/3/31.
 */

public class LinkedList<T> {
    private Node<T> mHead;
    private Node<T> mCurrent;
    private int mSize = 0;

    public void add(T o) {
        addLast(o);
        mSize++;

    }

    public void add(int index, T o) {
        checkIndex(index);

        Node<T> next = find(index);
        Node<T> pre = next.previous;
        Node<T> current = new Node<>(o, next, pre);
        next.previous = current;
        pre.next = current;
        mSize++;

    }

    private Node<T> find(int index) {
        Node<T> tra = mHead;
        if (index < (mSize >> 1)) {
            for (int i = 0; i <= index; i++) {
                tra = tra.next;
            }
        } else {
            for (int i = mSize; i > index; i--) {
                tra = tra.previous;
            }
        }
        return tra;
    }

    private void checkIndex(int index) {
        if (index >= mSize || index < 0) {
            throw new IndexOutOfBoundsException("Index:" + index + " Size:" + mSize);
        }
    }

    public Object get(int index) {
        checkIndex(index);

        return find(index).data;
    }

    public T remove(int index) {
        checkIndex(index);
        //重链接
        Node<T> temp = this.find(index);
        Node<T> next = temp.next;
        Node<T> pre = temp.previous;
        pre.next = next;
        next.previous = pre;
        //清除数据
        T removedObject = temp.data;
        temp.data = null;
        temp.next = null;
        temp.previous = null;
        mSize--;
        return removedObject;
    }

    public int size() {
        return mSize;
    }

    public void addFirst(T o) {
        Node<T> next = mHead.next;
        Node<T> first = new Node<>(o, next, mHead);
        next.next = first;
        next.previous = first;
        mSize++;

    }

    public void addLast(T o) {
        Node<T> last = mHead.previous;
        Node<T> temp = new Node<>(o, mHead, last);
        mHead.previous = temp;
        last.next = temp;
        mSize++;
    }

    public T removeFirst() {
        return remove(0);
    }

    public T removeLast() {
        return remove(mSize - 1);
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
     * 如果 list = 2->5->7->8->10 , 删除以后的值为 7,8,10
     */
    public void removeFirstHalf() {

    }

    /**
     * 从第 i 个元素开始， 删除 length 个元素 ， 注意 i 从 0 开始
     *
     * @param i
     * @param length
     */
    public void remove(int i, int length) {

    }

    /**
     * 假定当前链表和 listB 均包含已升序排列的整数
     * 从当前链表中取出那些 listB 所指定的元素
     * 例如当前链表 = 11->101->201->301->401->501->601->701
     * listB = 1->3->4->6
     * 返回的结果应该是 [101,301,401,601]
     *
     * @param list
     */
    public int[] getElements(LinkedList list) {
        return null;
    }

    /**
     * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 从当前链表中中删除在 listB 中出现的元素
     *
     * @param list
     */

    public void subtract(LinkedList list) {

    }

    /**
     * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
     */
    public void removeDuplicateValues() {

    }

    /**
     * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 试写一高效的算法，删除表中所有值大于 min 且小于 max 的元素（若表中存在这样的元素）
     *
     * @param min
     * @param max
     */
    public void removeRange(int min, int max) {

    }

    /**
     * 假设当前链表和参数 list 指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
     * 现要求生成新链表 C，其元素为当前链表和 list 中元素的交集，且表 C 中的元素有依值递增有序排列
     *
     * @param list
     */
    public LinkedList intersection(LinkedList list) {
        return null;
    }


    private static class Node<T> {
        T data;
        Node<T> next;
        Node<T> previous;

        public Node(T data) {
            this.data = data;
            this.next = this;
            this.previous = this;
        }

        public Node(T data, Node<T> next, Node<T> previous) {
            this.data = data;
            this.next = next;
            this.previous = previous;
        }
    }
}
