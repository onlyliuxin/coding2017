package com.bruce.homework0226;

import java.util.Objects;

public class LinkedListV01<T> {
    private int size;
    private Node<T> head;
    private Node<T> last;

    public LinkedListV01() {
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
}
