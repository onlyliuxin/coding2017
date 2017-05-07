package com.coding.basic.linklist;

import sun.security.krb5.internal.PAData;

/**
 * 用双向链表实现LRU算法
 * @author liuxin
 *
 */
public class LRUPageFrame {

    private static class Node {

        Node prev;
        Node next;
        int pageNum;

        Node(int pageNum,Node p,Node n) {
            this.pageNum= pageNum;
            this.prev = p;
            this.next = n;

        }
    }

    private int capacity;

    private int size=0;//节点个数
    private Node first;// 链表头
    private Node last;// 链表尾


    public LRUPageFrame(int capacity) {

        this.capacity = capacity;

    }

    /**
     * 获取缓存中对象
     *
     * @param
     * @return
     */
    public void access(int pageNum) {
        if (size<capacity) {
            if (size == 0) {
                first = new Node(pageNum,null,null);
                size++;

            } else if (size == 1) {
                Node newnode=new Node(pageNum,null,first);
                last = first;
                first = newnode;
                last.prev = first;
                size++;
            } else {
                Node newnode = new Node(pageNum, null, first);
                first.prev = newnode;
                first = newnode;
                size++;
            }

        }else {
            if(queryis(pageNum) !=null){
                Node denode = queryis(pageNum);
                Node newfirst = new Node(pageNum,null,first);
                first.prev = newfirst;
                first = newfirst;

                if(denode.next!=null){
                    denode.prev.next = denode.next;
                    denode.next.prev = denode.prev;
                }else {
                    denode.prev.next = null;
                    last = denode.prev;
                }


            }else {
                Node newfirst = new Node(pageNum, null, first);

                first.prev = newfirst;
                first = newfirst;
                last = last.prev;
                last.next = null;
            }

        }
    }


    public Node queryis(int pageNum) {
        Node node = first;
        boolean flag=false;
        while (node != null) {
            if (node.pageNum == pageNum) {
                flag = true;
                break;

            }else {
                node = node.next;
            }

        }
        if (flag) {
            return node;
        } else {
            return null;
        }
    }
    public String toString(){
        StringBuilder buffer = new StringBuilder();
        Node node = first;
        while(node != null){
            buffer.append(node.pageNum);

            node = node.next;
            if(node != null){
                buffer.append(",");
            }
        }
        return buffer.toString();
    }

}