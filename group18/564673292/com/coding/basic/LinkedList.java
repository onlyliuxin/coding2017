package com.coding.basic;

public class LinkedList<E> implements List<E>, Iterable<E> {

    private Node head;
    private Node last;
    private int length;

    private class Node{
        public E data;
        public Node next;

        // constructor
        private Node(E o, Node n){
            data = o;
            next = n;
        }
    }   
    
    // constructor
    public LinkedList(){
        head = new Node(null, null);
        last = head;
        length = 0;
    }

    public void add(E o){
        Node newNode = new Node(o, null);
        last.next = newNode;
        last = newNode;
        length++;
    }

    public void insert(int index , E o){
        if(index > length - 1)  throw new IndexOutOfBoundsException();
        Node prevNode = this.getNode(index - 1);
        Node nextNode = this.getNode(index);
        Node nodeToInsert = new Node(o,nextNode);
        prevNode.next = nodeToInsert;
        length++;
    }

    private Node getNode(int index){
        int count = 0;
        Node currentNode = head;
        while(currentNode.next != null && count <= index){
            currentNode = currentNode.next;
            count++;
        }
        return currentNode;
    }

    public E get(int index){
        if(index > length - 1)  throw new IndexOutOfBoundsException();
        Node nodeAtIndex = this.getNode(index);
        return nodeAtIndex.data;
    }

    public E remove(int index){
        if(index > length - 1)  throw new IndexOutOfBoundsException();
        Node nodeToRemove = this.getNode(index);
        Node prevNode = this.getNode(index - 1);
        Node nextNode = this.getNode(index + 1);
        prevNode.next = nextNode;
        E removedData = nodeToRemove.data;
        nodeToRemove = null;
        length--;
        return removedData;
    }
    
    public int size(){
        return length;
    }
    
    public void addFirst(E o){
        this.insert(0, o);
    }
    public void addLast(E o){
        this.add(o);
        
    }
    public E removeFirst(){
        return this.remove(0);
    }
    public E removeLast(){
        return this.remove(length - 1);
    }

    public Iterator<E> iterator(){
        return new Itr(this);
    }

    private class Itr implements Iterator<E>{
        private int itrCurIndex;
        private Node currentNode;
        private LinkedList linkedList;

        public Itr(LinkedList linkedList){
            itrCurIndex = -1;
            currentNode = head;
            this.linkedList = linkedList;
        }

        public boolean hasNext(){
            return (itrCurIndex + 1) > length - 1 ? false: true;
        }

        @SuppressWarnings("unchecked")
        public E next(){
            if(this.hasNext()){
                return (E)this.linkedList.get(++itrCurIndex);
            }else{
                itrCurIndex = -1;
                return null;
            }           
        }

        @SuppressWarnings("unchecked")
        public E remove(){
            return (E)this.linkedList.remove(itrCurIndex);
        }
    }
}