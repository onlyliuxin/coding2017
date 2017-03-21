package Collection;

public class LinkedList implements List {

    private int theSize = 0;//表示该链表的长度
    private Node beginMarker;//链表的头元素
    private Node endMarker;//链表的尾元素

    //链表的构造方法
    public LinkedList() {
        beginMarker = new Node(null, null, null);
        endMarker = new Node(null, beginMarker, null);
        beginMarker.next = endMarker;
    }

    public void add(Object o) {
        add(size(), o);
    }

    public void add(int index, Object o) {
        addBefore(getNode(index), o);
    }

    public Object get(int index) {
        return getNode(index).data;
    }

    public Object remove(int index) {
        return remove(getNode(index));
    }

    public int size() {
        return theSize;
    }

    public void addFirst(Object o) {
        addBefore(getNode(0), o);
    }

    public void addLast(Object o) {
        addBefore(getNode(theSize), o);
    }

    public Object removeFirst() {
        return remove(getNode(0));
    }

    public Object removeLast() {
        return remove(getNode(theSize - 1));
    }

    public Iterator iterator() {
        return new Itr();
    }

    private class Itr implements Iterator {

        private Node cursor = beginMarker.next;

        @Override
        public boolean hasNext() {

            return cursor != endMarker;
        }

        @Override
        public Object next() {

            Object o = cursor.data;
            cursor = cursor.next;

            return o;
        }

    }

    private Object remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        theSize--;
        return node.data;
    }

    private Node getNode(int index) {
        Node p;
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }
        if (index == -1) {
            return beginMarker;
        } else if (index == theSize) {
            return endMarker;
        }
        if (index < size() / 2) {
            p = beginMarker.next;
            for (int i = 0; i < index; i++) {
                p = p.next;
            }
        } else {
            p = endMarker.prev;
            for (int i = size() - 1; i > index; i--) {
                p = p.prev;
            }
        }
        return p;
    }

    private void addBefore(Node node, Object o) {
        Node newNode = new Node(o, node.prev, node);
        newNode.prev.next = newNode;
        node.prev = newNode;
        theSize++;
        //nodeCount++;
    }

    private static class Node {
        public Object data;
        public Node next;
        public Node prev;

        public Node(Object data, Node prev, Node next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }


    //以下不用实现

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

    }

    /**
     * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
     *
     * @param i
     * @param length
     */
    public void remove(int i, int length) {

    }

    /**
     * 假定当前链表和listB均包含已升序排列的整数
     * 从当前链表中取出那些listB所指定的元素
     * 例如当前链表 = 11->101->201->301->401->501->601->701
     * listB = 1->3->4->6
     * 返回的结果应该是[101,301,401,601]
     *
     * @param list
     */
    public int[] getElements(LinkedList list) {
        return null;
    }

    /**
     * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 从当前链表中中删除在listB中出现的元素
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
     * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
     *
     * @param min
     * @param max
     */
    public void removeRange(int min, int max) {

    }

    /**
     * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
     * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
     *
     * @param list
     */
    public LinkedList intersection(LinkedList list) {
        return null;
    }
}
