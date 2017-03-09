package com.coding.basic;

public class LinkedList<E> implements List<E>, Iterable<E> {

    private Node head;
    private Node last;
    private int length;

    private static class Node{
        Object data;
        Node next;
        private Node(Object data, Node next){
            this.data = data;
            this.next = next;
        }
    }
    
    // constructor
    public LinkedList(){
        head = new Node(null,null);
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
        return (E) nodeAtIndex.data;
    }

    public E remove(int index){
        // warning: if the last node is moved, remember to shift last ahead
        if(index > length - 1)  throw new IndexOutOfBoundsException();
        Node nodeToRemove = this.getNode(index);
        Node prevNode = this.getNode(index - 1);
        Node nextNode = this.getNode(index + 1);
        prevNode.next = nextNode;
        E removedData = (E) nodeToRemove.data;
        nodeToRemove.data = null;
        nodeToRemove.next = null;
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
        private LinkedList linkedList;

        private Itr(LinkedList linkedList){
            itrCurIndex = -1;
            this.linkedList = linkedList;
        }

        public boolean hasNext(){
            return (itrCurIndex + 1) <= length - 1;
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


    /**
     * 把该链表逆置
     * 例如链表为 3->7->10 , 逆置后变为  10->7->3
     */
    public void reverse(){
        head.next = getNode(size() - 1);
        for (int i = size() - 1; i > 0; i--) {
            getNode(i).next = getNode(i - 1);
        }
    }

    /**
     * 删除一个单链表的前半部分
     * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
     * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10
     */
    public void removeFirstHalf(){

    }

    /**
     * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
     * @param i
     * @param length
     */
    public  void remove(int i, int length){

    }
    /**
     * 假定当前链表和listB均包含已升序排列的整数
     * 从当前链表中取出那些listB所指定的元素
     * 例如当前链表 = 11->101->201->301->401->501->601->701
     * listB = 1->3->4->6
     * 返回的结果应该是[101,301,401,601]
     * @param list
     */
    public  int[] getElements(LinkedList list){
        return null;
    }

    /**
     * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 从当前链表中中删除在listB中出现的元素
     * @param list
     */

    public  void subtract(LinkedList list){

    }

    /**
     * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
     */
    public  void removeDuplicateValues(){

    }

    /**
     * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
     * @param min
     * @param max
     */
    public  void removeRange(int min, int max){

    }

    /**
     * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
     * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
     * @param list
     */
    public  LinkedList intersection( LinkedList list){
        return null;
    }
}