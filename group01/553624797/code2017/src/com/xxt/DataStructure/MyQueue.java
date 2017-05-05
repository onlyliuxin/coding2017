package com.xxt.DataStructure;

import java.util.Iterator;

/**
 * Created by star on 2017/2/26.
 */
public class MyQueue{


    public  static class Node<E>{
        Object elementData;
        Node<E> prerious;
        Node<E> next;

        public Node(Object elementData, Node<E> prerious, Node<E> next) {
            this.elementData = elementData;
            this.prerious = prerious;
            this.next = next;
        }

    }


    private MyQueue.Node header;
    private MyQueue.Node last;

    private int size = 0;


    //入队操作.在链表的头节点插入元素
    public void enQueue(Object o){
        addBefore(o, header);
    }


    //出队操作，返回链表的尾节点的元素
    public Object deQueue(){
        return node(size);
    }


    public boolean isEmpty(){
        return header == last;
    }



    public int size(){
        return size;
    }

   private Node addBefore(Object o , Node node){
       Node newNode = new Node(o, node.prerious, node.next);

       newNode.prerious.next = newNode;
       newNode.next.prerious = newNode;

       size++;
       return newNode;

   }


    private Node node(int index){
        if(index < 0 || index > size){
            throw  new IndexOutOfBoundsException("数组下标越界"+size);
        }

        if(index < (size >> 1)){
            Node e = header;
            for(int i = 0; i < index; i ++){
                e = header.next;
                return e;
            }
        }else {
            Node e = last;
            for(int i = size - 1; i > index; i--){
                e = last.prerious;
                return e;
            }
        }
        return null;
    }



}
