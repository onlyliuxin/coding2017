package main.coding_170327;

import java.util.ArrayList;

public class LRUPageFrame {

    private static class Node {

        Node prev;
        Node next;
        int pageNum;

        Node() {
        }
    }

    private int capacity;

    private int currentSize;
    private Node first;// 链表头
    private Node last;// 链表尾


    public LRUPageFrame(int capacity) {
       this.capacity = capacity;
       this.currentSize = 0;
       first = new Node();
       last = new Node();
    }

    /**
     * 获取缓存中对象
     *
     * @param pageNum
     * @return
     */
    public void access(int pageNum) {
        if(isEmpty()){
            //显然是最新数据
            Node node = new Node();
            node.pageNum = pageNum;
            addNewNodetoHead(node);
        }else{
            Node node = find(pageNum);
            if(node==null){
                node = new Node();
                node.pageNum = pageNum;
                addNewNodetoHead(node);
            }else{
                moveExistingNodeToHead(node);
            }

        }

    }

    private void addNewNodetoHead(Node node) {
        if(this.currentSize==0){
           // 表示这是第一个节点
            first.next = node;
            node.prev = first;
            node.next = last;
            last.prev = node;
            this.currentSize++;
        } else {
           if(this.currentSize==capacity)
               removeLast();
            node.prev = first;
            node.next = first.next;
            first.next.prev = node;
            first.next = node;
            this.currentSize++;
        }

    }

    private Node find(int data){
        Node compareNode = first.next;
        while (compareNode!=last){
            if(data==compareNode.pageNum)
                break;
            compareNode = compareNode.next;
        }
        if(compareNode!=last){
            Node node = new Node();
            node.pageNum = data;
            return node;
        }
        return null;

    }






    /**
     * 删除链表尾部节点 表示 删除最少使用的缓存对象
     */
    private void removeLast() {
        Node remove = last.prev;
        remove.prev.next = last;
        last.prev = remove.prev;
        this.currentSize--;
    }

    /**
     * 移动到链表头，表示这个节点是最新使用过的
     *
     * @param node
     */
    private void moveExistingNodeToHead(Node node) {
        if(this.currentSize>1){
            //如果当前元素只有一个显然不需要移动
            Node compareNode = first.next;
            //从头比较，找出node节点的位置，将其前移到开头
            while (compareNode!=last){
                if(compareNode.pageNum==node.pageNum)break;
                compareNode = compareNode.next;
            }
            //删除当前node
            compareNode.prev.next = compareNode.next;
            compareNode.next.prev = compareNode.prev;
            //将node插入到开头位置
            node.prev = first;
            node.next = first.next;
            first.next.prev = node;
            first.next = node;
        }
    }
    private boolean isEmpty(){
        return this.currentSize>0?false:true;
    }

    public String toString(){
        ArrayList<Integer> arrays = new ArrayList<>();
       if(this.currentSize>0){
           Node index = first.next;
           while (index!=last){
               arrays.add(index.pageNum);
               index = index.next;
           }
           return arrays.toString();
       }
       return "";
    }



}

