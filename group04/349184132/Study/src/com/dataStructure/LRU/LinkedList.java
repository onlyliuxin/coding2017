package com.dataStructure.LRU;

import com.dataStructure.Iterator;
import com.dataStructure.List;

import java.util.Objects;

/**
 * Created by wang on 2017/3/27.
 */
public class LinkedList implements List {

    private Node first;
    private Node last;
    private int size = 0;
    public void add(Object o) {
        if(isEmypty()){
            Node node = new Node(o);
            first = node;
            last = node;
            size++;
        }else{
            addLast(o);
        }
    }

    public void add(int index, Object o) {
        checkRange(index);
        if(isEmypty()){
            Node node = new Node(o);
            first = node;
            last = node;
            size++;
        }else{
            if(index==0){
                addFirst(o);
            }else{
                Node node = getNode(index);
                Node newNode = new Node(o);
                Node pre = node.pre;

                pre.next = newNode;
                newNode.pre = pre;
                newNode.next = node;
                node.pre = newNode;

                size++;
            }
        }
    }

    private Node getNode(int index) {
        Node node = first;
        int count = 0;
        while(count<index){
            node = node.next;
            count++;
        }
        return node;
    }

    private void checkRange(int index) {
        if(index<0 || index >size-1){
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public Object get(int index) {
        checkRange(index);
        return  getNode(index).data;
    }




    public Object set(int index ,Object o){
        checkRange(index);
        Node node = getNode(index);
        Object oldData = node.data;
        node.data = o;
        return oldData;
    }

    public Object remove(int index) {
        checkRange(index);
        if(index==0){
            return removeFirst();
        }else{
            if(index==size-1){
                return removeLast();
            }else{
                Node node = getNode(index);
                Node pre = node.pre;
                Node next = node.next;

                pre.next = next;
                next.pre = pre;
                node.pre = null;
                node.next = null;

                return node.data;
            }
        }

    }



    public int size() {
        return size;
    }

    public boolean isEmypty(){
        return first==null || last == null;
    }

    public void addFirst(Object o) {
        Node newFirst = new Node(o);
        Node oldfirst = first;
        newFirst.next = oldfirst;
        first = newFirst;
        size++;
    }

    public void addLast(Object o) {
        Node oldLast = last;
        Node node = new Node(o);
        oldLast.next = node;
        node.pre = oldLast;
        last = node;
        size++;
    }
    // not help GC
    public Object removeFirst() {
        Node oldFirst = first;
        first = first.next;
        size--;
        return oldFirst.data;
    }
    // not help GC
    public Object removeLast() {
        Node oldLast = last;
        last = last.pre;
        size--;
        return oldLast.data;
    }

    public boolean contain(Object o){
        Node x = first;
        while(x!=null){
            if(Objects.deepEquals(x.data,o)){
                return true;
            }
            x = x.next;
        }
        return false;
    }

    public Iterator iterator() {
        return new LinkIterator();
    }

    private class LinkIterator implements Iterator{
        private int cursor = 0;
        @Override
        public boolean hasNext() {
            return cursor<size;
        }

        @Override
        public Object next() {
            if(cursor >= size){
                throw new ArrayIndexOutOfBoundsException();
            }
            return get(cursor++);
        }
        public boolean remove(){
            LinkedList.this.remove(cursor);
            return true;
        }
    }
    private static class Node {
        Object data;
        Node pre;
        Node next;

        public Node(Object data) {
            this.data = data;
        }
    }
}