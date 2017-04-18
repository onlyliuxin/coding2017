package me.lzb.basic;


/**
 * 简易LinkedList
 * Created by LZB on 2017/3/11.
 */
public class LinkedList implements List {

    private int size = 0;


    private Node first;

    private Node last;


    private static class Node {
        Object data;
        Node next;
//        int intData;

        public Node(Object data, Node next) {
            this.data = data;
            this.next = next;
        }
//        public Node(Object data, Node next, int i) {
//            this.data = data;
//            this.next = next;
//            this.intData = i;
//        }
    }


//    public void add(int i) {
//        if (first == null) {
//            first = new Node(null, null, i);
//            last = first;
//        } else {
//            Node n = new Node(null, null, i);
//            last.next = n;
//            last = n;
//        }
//        size = size + 1;
//    }
    public void add(Object o) {
        if (first == null) {
            first = new Node(o, null);
            last = first;
        } else {
            Node n = new Node(o, null);
            last.next = n;
            last = n;
        }
        size = size + 1;
    }
//
//    public void addInt(int i) {
//        if (first == null) {
//            first = new Node(null, null, i);
//            last = first;
//        } else {
//            Node n = new Node(null, null, i);
//            last.next = n;
//            last = n;
//        }
//        size = size + 1;
//    }


    public void add(int index, Object o) throws IndexOutOfBoundsException {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index boom");
        }

        if (index == size) {
            add(o);
            return;
        }

        if (index == 0) {
            Node n = new Node(0, first);
            first = n;
            size = size + 1;
            return;
        }

        Node before = first;
        for (int i = 0; i < index - 1; i++) {
            before = before.next;
        }

        Node after = before.next;

        Node n = new Node(o, after);

        before.next = n;

        size = size + 1;

    }

    private Node getNode(int index){
        if (size == 0 || index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index boom");
        }
        Node result = first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result;
    }


    public Object get(int index) {
        return getNode(index).data;
    }


    public Object remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index boom");
        }

        if (size == 1) {
            Node result = last;
            last = null;
            first = null;
            size = size - 1;
            return result.data;
        }

        if (index == size - 1) {
            Node result = last;
            last = null;
            size = size - 1;
            return result.data;
        }


        if (index == 0) {
            Node result = first;
            Node second = first.next;
            first = second;
            size = size - 1;
            return result.data;
        }


        Node before = first;
        for (int i = 0; i < index - 1; i++) {
            before = before.next;
        }
        Node result = before.next;
        Node after = before.next.next;
        before.next = after;
        size = size - 1;
        return result.data;
    }








    public int size() {
        return size;
    }

    public void addFirst(Object o) {
        add(0, o);
    }


    public void addLast(Object o) {
        add(o);
    }


    public Object removeFirst() {
        return remove(0);
    }


    public Object removeLast() {
        return remove(size);
    }


    public Iterator iterator() {
        return new LinkedListIterator(this);
    }


    private class LinkedListIterator implements Iterator {
        private LinkedList linkedList;

        int pos = 0;

        private LinkedListIterator(LinkedList linkedList) {
            this.linkedList = linkedList;
        }

        @Override
        public boolean hasNext() {

            if (pos >= linkedList.size) {
                return false;
            }
            return true;
        }

        @Override
        public Object next() {
            Object result = linkedList.get(pos);
            pos = pos + 1;
            return result;
        }
    }


    //后面的方法先不用写的说

    /**
     * 把该链表逆置
     * 例如链表为 3->7->10 , 逆置后变为  10->7->3
     */
    public void reverse() {

        //还可以用堆栈 先进后出

        if(size() <= 1){
            return;
        }
        Object[] array = new Object[size];
        Node tmp = first;
        for (int i = 0; i < size; i++) {
            array[i] = tmp.data;
            tmp = tmp.next;
        }
        this.first = null;
        this.last = null;
        for (int i = array.length - 1; i >= 0 ; i--) {
            add(array[i]);
        }

    }

    /**
     * 删除一个单链表的前半部分
     * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
     * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10
     */
    public void removeFirstHalf() {

        if (size <= 1){
            return;
        }
        int b = size/ 2;
        Node n = getNode(b);
        this.first = n;
        size = (size % 2) + b;
    }

    /**
     * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
     *
     * @param i
     * @param length
     */
    public void remove(int i, int length) {
        if (size == 0 || i < 0 || i >= size){
            return;
        }

        length = size - i >= length ? length : size - i;


        if(i + length == size){
            this.first = null;
            this.last = null;
            size = 0;
            return;
        }

        if(i == 0){
            Node n = getNode(length);
            first = n;
            size = size - length;
            return;
        }


        Node a = getNode(i - 1);
        Node b = getNode(i + length);
        a.next = b;
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

        if(size <= 0 || list.size() <= 0){
            return new int[0];
        }



        int[] result = new int[list.size()];

        Node tmp = list.first;
        int index = 0;
        Node tmp2 = first;
        for (int i = 0; i < list.size(); i++) {
            int newIndex = (int)tmp.data;
            int maxJ = newIndex - index;
            for (int j = 0; j <= maxJ; j++) {

                if(j == maxJ){
                    result[i] = (int)tmp2.data;
                    break;
                }
                tmp2 = tmp2.next;
            }
            index = newIndex;
            tmp = tmp.next;
        }

        size = size - list.size();

        return result;
    }

    /**
     * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 从当前链表中中删除在list中出现的元素
     *
     * @param list
     */

    public void subtract(LinkedList list) {
        for (int i = 0; i < list.size(); i++) {
            this.remove(list.get(i));
        }

    }


    public void remove(Object obj){
        if(size <= 0){
            return;
        }
        if(first.data.equals(obj)){
            first=first.next;
            size = size - 1;
            return;
        }
        Node tmp = first;
        Node tmp2 = first.next;
        for (int i = 1; i < size; i++) {
            if(tmp2.data.equals(obj)){
                tmp.next = tmp2.next;
                size = size - 1;
                return;
            }
            tmp = tmp.next;
            tmp2 = tmp2.next;
        }

    }


    /**
     * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
     */
    public void removeDuplicateValues() {
        if(size <= 1){
            return;
        }

        Node tmp = first;
        for (int i = 1; i < size; i++) {
            if(tmp.next == null){
                break;
            }
            if (tmp.data.equals(tmp.next.data)){
                tmp.next = tmp.next.next;
            }
            tmp = tmp.next;
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
        if(size <= 0){
            return;
        }

        Node tmp = first;
        int a = -1;
        int b = -1;
        for (int i = 0; i < size; i++) {
            if((int)tmp.data > min && a == -1){
                a = i;
            }

            if((int)tmp.data >= max && b == -1){
                b = i;
            }

            tmp = tmp.next;
        }


        if(min < max){
            remove(a, b - a);
            return;

        }


        if(min == max){

        }


        if(min > max){

        }



        return;
    }

    /**
     * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
     * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
     *
     * @param list
     */
    public LinkedList intersection(LinkedList list) {
        LinkedList result = new LinkedList();

        if(list == null || list.size <= 0 || size <= 0){
            return result;
        }

        int i1 = 0;
        int i2 = 0;

        while( i1 < this.size  && i2<list.size() ){

            int value1 = (int)this.get(i1);
            int value2 = (int)list.get(i2);

            if(value1 == value2){
                result.add(value1);
                i1++;
                i2++;

            } else if (value1 < value2){
                i1++;

            } else{
                i2++;

            }
        }


        return result;
    }



    public String toString(){
        StringBuffer buffer = new StringBuffer();
        buffer.append("[");
        Node node = first;
        while(node != null){
            buffer.append(node.data);
            if(node.next != null){
                buffer.append(",");
            }
            node = node.next;
        }
        buffer.append("]");
        return buffer.toString();
    }

}
