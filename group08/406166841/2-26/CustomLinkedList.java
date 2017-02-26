package net.iyouqu.bruceretrofit.util.java;

/**
 * Created by liq on 2017/2/25.
 */

public class CustomLinkedList<E> implements List {

    //链表长度
    private int size = 0;
    //链表头指针
    private Node<Object> first;
    //链表尾部指针
    private Node<Object> last;
    //操作次数
    private int modCount;

    @Override
    public void add(Object o) {
        linkLast(o);
    }

    @Override
    public void add(int index, Object o) {
        checkPositionIndex(index);
        if (index == size) {
            linkLast(o);
        } else {
            linkBefore(o, node(index));
        }
    }

    @Override
    public Object get(int index) {
        checkPositionIndex(index);
        return node(index).data;
    }

    @Override
    public Object remove(int index) {
        checkPositionIndex(index);
        return unlink(node(index));
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * 添加节点到链表尾部
     */
    public void addLast(Object e) {
        linkLast(e);
    }

    /**
     * 解除传入节点的属性,并且将传入节点的上一个和下一个节点 链接。使传入节点的属性 全部为 null
     */
    private Object unlink(Node<Object> node) {
        //获取当前节点node的属性
        final Object element = node.data;
        final Node<Object> next = node.next;
        final Node<Object> prev = node.prev;
        if (prev == null) {
            //上一个节点为null将首节点设置为下一个节点
            first = next;
        } else {
            //上一个节点有 将上一个节点的下一个节点 设置为当前节点的下一个节点
            prev.next = next;
            //将当前节点的上一个节点设置为null
            node.prev = null;
        }
        if (next == null) {
            //下一个节点为null将末尾节点设置为上一个节点
            last = prev;
        } else {
            //将下一个节点的上一个节点 设置为当前节点的上一个节点
            next.prev = prev;
            node.next = null;
        }
        node.data = null;
        size--;
        modCount++;
        return element;
    }

    /**
     * 获取一个节点
     * 判断index 在前半区间还是后半区间。而不是一直从头到尾搜索
     * 将节点访问复杂度从O(n)变为O(n/2)
     */
    private Node<Object> node(int index) {
        checkPositionIndex(index);
        if (index < (size / 2)) {
            Node<Object> x = first;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            return x;
        } else {
            Node<Object> x = last;
            for (int i = size - 1; i > index; i--) {
                x = x.prev;
            }
            return x;
        }
    }

    /**
     * 在参数节点之前插入一个节点
     */
    private void linkBefore(Object element, Node<Object> node) {
        //获取添加节点的上一个节点
        final Node<Object> pred = node.prev;
        //创建一个新节点
        final Node<Object> newNode = new Node<>(pred, element, node);
        //添加节点的上一个节点为 新节点
        node.prev = newNode;
        //判断上一个节点是否为null
        if (pred == null) {
            //首节点设置为新创建的节点
            first = newNode;
        } else {
            //上个节点不为null。将其下个节点设置为新创建的节点。
            pred.next = newNode;
        }
        size++;
        modCount++;
    }

    /**
     * 链接 节点到 last
     */
    private void linkLast(Object e) {
        final Node<Object> l = last;
        final Node<Object> newNode = new Node<>(l, e, null);
        last = newNode;
        //判断链表last是否为null
        if (l == null) {
            //链表first指向新添加的 节点
            first = newNode;
        } else {
            //链表last不为null将链表last节点的的next设置为新节点
            l.next = newNode;
        }
        size++;
        modCount++;
    }

    /**
     * 检查index是否越界
     */
    private void checkPositionIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index超过界限");
        }
    }

    private static class Node<E> {
        Object data;
        //下一个节点
        Node<Object> next;
        //上一个节点
        Node<Object> prev;
        public Node(Node<Object> prev, Object item, Node<Object> next) {
            this.data = item;
            this.next = next;
            this.prev = prev;
        }
    }
}
