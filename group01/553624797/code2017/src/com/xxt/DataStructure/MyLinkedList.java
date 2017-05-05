package com.xxt.DataStructure;

import java.util.Iterator;

import java.util.LinkedList;
import java.util.function.Consumer;

/**
 * Created by star on 2017/2/25.
 */
public class MyLinkedList<E> implements List{

    public  static class Node<E>{
        E elementData;
        Node<E> prerious;
        Node<E> next;

        public Node(Node<E> prerious , E elementData, Node<E> next) {
            this.prerious = prerious;
            this.elementData= elementData;
            this.next = next;
        }
    }



    private Node header;
    private Node last;

    private int size = 0;


    //往最后一个节点添加元素
    @Override
    public void add(Object elementData) {
        addBefore((E) elementData, last);
    }

    @Override
    public void add(int index, Object elementData) {
        addBefore((E) elementData, (index == size ? last : node(index)));
    }

    @Override
    public Object get(int index) {
        return node(index).elementData;

    }

    //获取index位置的节点
    private Node<E> node(int index){
        if(index < 0 || index > size){
            throw  new IndexOutOfBoundsException("数组下标越界"+size);
        }

        if(index < (size >> 1)){
            Node<E> e = header;
            for(int i = 0; i < index; i ++){
                e = header.next;
                return e;
            }
        }else {
            Node<E> e = last;
            for(int i = size - 1; i > index; i--){
                e = last.prerious;
                return e;
            }
        }
        return null;
    }


    @Override
    public Object remove(int index) {

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("inde :" + index);
        }

        return remove(node(index));
    }



    //返回被删除的节点
    private Object remove(Node<E> e){
       E movedElementData = e.elementData;

        //被删除节点的上个节点指向该节点的下个节点
       e.prerious.next = e.next;

        //被删除节点的下个节点指向该节点的上个节点
        e.next.prerious = e.prerious;


        //将该节点置为空，让GC能够回收
        e.next = e.prerious = null;
        e.elementData = null;
        //长度-1
        size--;
        return movedElementData;
    }


    @Override
    public int size() {
        return size;
    }

    public Object removeFirst(){
        return remove(header.next);


    }

    public Object removeLast(){
        return remove(last.prerious);
    }

    public Iterator iterator(){
        return null;
    }



    //插入一个新的节点
    private Node<E> addBefore(E e, Node<E> node){

        Node<E> newNode = new Node<E>(node.prerious, e, node.next);

        //将上个节点的next指向自己
        newNode.prerious.next = newNode;

        //将下个节点的previous指向自己
        newNode.next.prerious = newNode;

        size++;
        return newNode;
    }

}
