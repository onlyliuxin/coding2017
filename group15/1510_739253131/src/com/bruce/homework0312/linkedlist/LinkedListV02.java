package com.bruce.homework0312.linkedlist;

import com.bruce.homework0226.LinkedListV01;

import java.util.Objects;

public class LinkedListV02<T> {
    private int size = 0;
    private Node<T> head;
    private Node<T> last;

    public LinkedListV02() {
        //保证了初始化一个对象的时候，头节点不为空
        this.head = new Node<T>(null);
    }

    public boolean add (T element) {
        //双向链表，双向都需要维护
        if(last == null){
            last = new Node<>(element);
            head.next = last;
            last.pre = head;
        }else{
            Node<T> oldLast = last;
            last = new Node<>(element);
            last.pre = oldLast;
            oldLast.next = last;
        }
        size++;
        return true;
    }

    public boolean add(int index, T element) {
        Node<T> node = getNode(index);
        Node<T> newNode = new Node<T>(element);
        Node<T> pre = node.pre;
        pre.next = newNode;
        newNode.pre = pre;
        newNode.next = node;
        size++;
        return true;
    }

    public boolean remove(T element){
        Node<T> node = head;
        //下一个节点不为null
        while(node.next != null){
            node = node.next;
            if(Objects.equals(node.element, element)){
                if(node.next != null){
                    node.next.pre = node.pre;
                }
                node.pre.next = node.next;
                size--;
                return true;
            }
        }
        //下一个节点为null，说明是尾节点
        if(node != head){
            last = node;
        }
        //head.next=null,说明是一个空的链表，即仅有一个空head节点
        return false;
    }

    public T remove(int index){
        Node<T> node = getNode(index);
        Node<T> pre = node.pre;
        Node<T> next = node.next;
        pre.next = next;
        next.pre = pre;
        size--;
        return node.element;
    }

    public void clear(){
        for(Node<T> x = head; x != null; ){
            Node<T> next = x.next;
            x.pre = null;
            x.next = null;
            x.element = null;
        }
        head = last = null;
        size = 0;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public boolean contains(Object o){
        for(int i = 0; i < size; i++){
            if(Objects.equals(getNode(i).element, o)){
                return true;
            }
        }
        return false;
    }

    public Node<T> getNode(int index){
        if(index < 0 || index >size){
            return null;
        }
        Node<T> node = head;
        for(int i = 0; i < index; i++){
            node = node.next;
        }
        return node;
    }

    public T get(int index) {
        return getNode(index).element;
    }

    public int indexOf(T element){
        Node<T> node = head;
        int index = 0;
        while(node.next != null){
            node = node.next;
            if(Objects.equals(node.element, element)){
                return index;
            }
            index++;
        }
        return -1;
    }

    private static class Node<T> {
        T element;
        Node<T> pre;
        Node<T> next;

        Node(T element) {
            this.element = element;
        }
    }

    /**
     * 把该链表逆置
     * 例如链表为 3->7->10 , 逆置后变为  10->7->3
     */
    public void reverse(){
        T t;
        for(int i = 0; i < size; i++) {
            t = getNode(i).element;
            getNode(i).element = getNode(size-1-i).element;
            getNode(size-1-i).element = t;
        }
    }

    /**
     * 删除一个单链表的前半部分
     * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
     * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

     */
    public  void removeFirstHalf(){
        if(size < 2) {
            return;
        }
        int half = size >> 1;
        for(int i = 0; i < half; i++) {
            remove(i);
        }
    }

    /**
     * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
     * @param i
     * @param length
     */
    public  void remove(int i, int length){
        if((i+length)<0 || (i+length)>size) {
            return;//抛出异常
        }
        for(int n = i - 1; n <= length; n++) {
            remove(n);
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
    public int[] getElements(LinkedListV02<Integer> list){
        if(list == null) {
            return null;
        }
        int[] result = new int[list.size()];
        for(int i = 0; i < list.size(); i++) {
            if(list.get(i) < size) {
                result[i] = (Integer) this.get(i);
            }
        }
        return result;
    }

    /**
     * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 从当前链表中中删除在list中出现的元素

     * @param list
     */

    public void subtract(LinkedListV02 list){
        if(list == null || list.size() == 0) {
            return;
        }
        for(int i = 0; i < list.size(); i++) {
            if(this.contains(list.get(i))) {
                this.remove((T) list.get(i));
            }
        }
    }

    /**
     * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
     */
    public  void removeDuplicateValues(){
        LinkedListV02<T> newList = new LinkedListV02<>();
        for(int i = 0; i < size; i++) {
            if(!newList.contains(this.get(i))) {
                newList.add(this.get(i));
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
        if(min > max) {
            return;
        }
        if((Integer)head.element > min && (Integer)last.element < max) {
            clear();
        } else if ((Integer)head.element > min && (Integer)last.element > max) {
            Node<T> temp1 = last;
            Node<T> temp2 = head;
            while(temp1.pre != null) {
                temp1 = temp1.pre;
                if(Objects.equals(temp1.element, max)) {
                    last = temp1;
                } else {
                    temp1.pre = null;
                    temp1.element = null;
                }
                temp1.next = null;
            }
            while(temp2.next != null) {
                temp2 = temp2.next;
                if(Objects.equals(temp2.element, min)) {
                    head = temp2;
                } else {
                    temp2.next = null;
                    temp2.element = null;
                }
                temp2.pre = null;
            }
        }

    }

    /**
     * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
     * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
     * @param list
     */
    public LinkedListV02 intersection(LinkedListV02 list){
        if(list == null || list.size() == 0) {
            return null;
        }
        LinkedListV02 newList = new LinkedListV02();
        for(int i = 0; i < list.size(); i ++) {
            if(this.contains(list.get(i))) {
                newList.add(list.get(i));
            }
        }
        return newList;
    }
}
