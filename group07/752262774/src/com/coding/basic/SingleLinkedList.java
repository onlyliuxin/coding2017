package com.coding.basic;

import java.util.HashMap;
import java.util.NoSuchElementException;

/**
 * Created by yrs on 2017/3/6.
 */
public class SingleLinkedList {

    private Node head;

    private int size;

    public void add(Object o){
        if(null == head) {
            head = new Node(o, null);
            size++;
        }else {
            Node node = head;
            while (node.next != null) {
                node = node.next;
            }
            node.next = new Node(o, null);
            size++;
        }

    }
    public void add(int index , Object o){
        if(index<0 || index>size) {
            throw new IndexOutOfBoundsException();
        }else if(index == 0) {
            addFirst(o);
        }else {
            Node before = beforeNode(index);
            Node newNode = new Node(o, before.next);
            before.next = newNode;
            size++;
        }
    }
    public Object get(int index){
        rangeCheck(index);
        if(index == 0) {
            return head.data;
        }else {
            return beforeNode(index).next.data;
        }

    }
    public Object remove(int index){
        rangeCheck(index);
        Object o = null;
        if(index == 0) {
            removeFirst();
        }else {
            Node before = beforeNode(index);
            Node node = before.next;
            o = node.data;
            before.next = before.next.next;
            node.next = null;
            node.data = null;
            size--;
        }
        return o;
    }

    public int size(){
        return this.size;
    }

    public void addFirst(Object o){
        Node node = new Node(o, head);
        head = node;
        size++;
    }
    public void addLast(Object o){
        add(o);
    }
    public Object removeFirst(){
        Node node = head;
        head = head.next;
        Object o = node.data;
        node.data = null;
        node.next = null;
        size--;
        return o;
    }
    public Object removeLast(){
        Node node = beforeNode(size-1);
        Object o = node.next.data;
        node.next.data = null;
        node.next.next = null;
        node.next = null;
        size--;
        return o;
    }
    public Iterator iterator(){
        return new SingleLinkedListIterator(this);
    }

    private class SingleLinkedListIterator implements Iterator {

        SingleLinkedList singleLinkedList;

        int pos;

        private SingleLinkedListIterator(SingleLinkedList singleLinkedList) {
            this.singleLinkedList = singleLinkedList;
        }

        @Override
        public boolean hasNext() {
            return pos < singleLinkedList.size;
        }

        @Override
        public Object next() {
            if(pos < size) {
                return singleLinkedList.get(pos++);
            }else {
                throw new NoSuchElementException();
            }
        }
    }


    private void rangeCheck(int index) {
        if(index<0 || index>=size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private Node beforeNode(int index) {
        Node target = new Node();
        target = this.head;
        //得到目标索引的前一个
        for(int i=0; i<index-1; i++) {
            target = target.next;
        }
        return target;
    }


    private static  class Node{
        Object data;
        Node next;

        private Node() {

        }

        private Node(Object data, Node next) {
            this.data = data;
            this.next = next;
        }

    }

    /**
     * 把该链表逆置
     * 例如链表为 3->7->10 , 逆置后变为  10->7->3
     */
    public void reverse(){
        if (size <= 1) {
            return;
        }
        Node node = head;
        Node cursor;
        head = head.next;
        node.next = null;
        while (head.next != null) {
            cursor = head;
            head = head.next;
            cursor.next = node;
            node = cursor;
        }
        head.next = node;
    }

    /**
     * 删除一个单链表的前半部分
     * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
     * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

     */
    public  void removeFirstHalf(){
        if(size <= 1) {
            return;
        }
        int reLen = size / 2;
        Node node;
        for(int i=0; i<reLen; i++) {
            node = head;
            head = head.next;
            node.data = null;
            node.next = null;
        }
        size = size - 2;
    }

    /**
     * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
     * @param i
     * @param length
     */
    public  void remove(int i, int length) throws Exception {
        rangeCheck(i);
        if (i + length > size) {
            throw new Exception("length is too big");
        }

        Node before = beforeNode(i);
        Node node = before;
        for (int j=0; j<length; j++) {
            node = node.next;
            size--;
        }
        if(i==0) {
            head = node;
        }else {
            before.next = node.next;
        }

    }
    /**
     * 假定当前链表和list均包含已升序排列的整数
     * 从当前链表中取出那些list所指定的元素
     * 例如当前链表 = 11->101->201->301->401->501->601->701
     * listB = 1->3->4->6
     * 返回的结果应该是[101,301,401,601]
     * @param list
     */
    public int[] getElements(LinkedList list){
        if (list.size() > size || (int)list.get(list.size()-1) >= size) {
            throw new IndexOutOfBoundsException();
        }
        int[] result = new int[list.size()];
        for (int i=0; i<list.size(); i++) {
            result[i] = (int)get((int) list.get(i));
        }
        return result;
    }

    /**
     * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 从当前链表中中删除在list中出现的元素

     * @param list
     */

    public  void subtract(SingleLinkedList list){
        if (list == null || list.size() == 0) {
            return;
        }
        HashMap<Object, Integer> map = new HashMap<>();
        for (int i=0; i<size; i++) {
            map.put(get(i), i);
        }
        for (int i=0; i<list.size(); i++) {
            if (map.containsKey(list.get(i))) {
                remove(map.get(list.get(i)));
            }
        }

    }

    /**
     * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
     */
    public  void removeDuplicateValues(){
        Node current = head;
        Node cursor = head.next;
        for (int i=1; i<size; i++) {
            if (current.data.equals(cursor.data)){
                cursor = cursor.next;
                remove(i);
                i--;
            }else {
                current = cursor;
                cursor = cursor.next;
            }
        }
    }

    /**
     * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
     * @param min
     * @param max
     */
    public  void removeRange(int min, int max){
        if (min >= (int)get(size-1) || max <= (int)get(0) || min == max) {
            return;
        }
        Node minN = head;
        Node maxN = head;
        Boolean judge = false;
        int len = 0;
        for (int i=0; i<size-1; i++) {
            if (!judge && (int)get(i) <= min) {
                minN = maxN;
                maxN = maxN.next;
            }else {
                judge = true;
                if((int)get(i) >= max){
                    break;
                }else {
                    maxN = maxN.next;
                    len ++;
                }
            }
        }
        size = size - len;
        minN.next = maxN;
    }

    /**
     * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
     * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
     * @param list
     */
    public  SingleLinkedList intersection( SingleLinkedList list){
        if (size == 0 || list == null || list.size ==0) {
            return null;
        }
        SingleLinkedList singleLinkedList = new SingleLinkedList();

        for (int i=0; i<list.size; i++) {
            for (int j=0; j<size; j++){
                if (list.get(i).equals(get(j))){
                    singleLinkedList.add(list.get(i));
                }
            }
        }

        return singleLinkedList;
    }
}
                