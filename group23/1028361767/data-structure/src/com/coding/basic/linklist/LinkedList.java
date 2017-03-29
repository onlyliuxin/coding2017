package com.coding.basic.linklist;


import java.util.NoSuchElementException;

import com.coding.basic.Iterator;
import com.coding.basic.List;

public class LinkedList implements List {

    private Node head;
    private int size;

    public void add(Object o) {
        Node newNode = new Node(o, null);
        if (head == null) {
            head = newNode;
        } else {
            Node tmp = head;
            while (tmp.next != null) {
                tmp = tmp.next;
            }
            tmp.next = newNode;
        }
        size++;
    }

    public void add(int index, Object o) {
        checkMinBound(index);
        checkMaxBound(index, size);
        Node newNode = new Node(o, null);
        if (index == 0) {
            newNode.next = head;
            head = newNode;
        } else {
            int pos = 1;
            Node tmp = head;
            while (pos != index) {
                tmp = tmp.next;
                pos++;
            }
            newNode.next = tmp.next;
            tmp.next = newNode;
        }
        size++;

    }

    private void checkMinBound(int index) {
        if (index < 0) {
            throw new IllegalArgumentException();
        }
    }

    private void checkMaxBound(int index, int max) {
        if (index > max) {
            throw new IndexOutOfBoundsException();
        }
    }


    public Object get(int index) {
        checkMinBound(index);
        checkMaxBound(index, size - 1);
        Node cur = head;
        if (index != 0) {
            int pos = 0;
            do {
                cur = cur.next;
                pos++;
            } while (pos != index);
        }
        return cur.data;
    }

    public Object remove(int index) {
        checkMinBound(index);
        checkMaxBound(index, size - 1);
        Node cur = head;
        if (index == 0) {
            head = cur.next;
        } else {
            int pos = 1;
            Node prev = cur;
            while (pos != index) {
                prev = prev.next;
                pos++;
            }
            cur = prev.next;
            prev.next = cur.next;
        }
        size--;
        return cur.data;
    }

    public int size() {
        return size;
    }

    public void addFirst(Object o) {
        Node newNode = new Node(o, null);
        newNode.next = head;
        head = newNode;
        size++;
    }

    public void addLast(Object o) {
        Node newNode = new Node(o, null);
        if (head == null) {
            head = newNode;
        } else {
            Node tmp = head;
            while (tmp.next != null) {
                tmp = tmp.next;
            }
            tmp.next = newNode;
        }
        size++;
    }

    public Object removeFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node ret = head;
        head = head.next;
        size--;
        return ret.data;
    }

    public Object removeLast() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node ret;
        if (head.next == null) {
            ret = head;
            head = null;
        } else {
            Node prev = head;
            ret = head.next;
            while (ret.next != null) {
                prev = ret;
                ret = ret.next;
            }
            prev.next = null;
        }
        size--;
        return ret.data;
    }

    public Iterator iterator() {
        return null;
    }


    private static class Node {
        Object data;
        Node next;

        public Node(Object data, Node next) {
            this.data = data;
            this.next = next;
        }

    }

    /**
     * 把该链表逆置
     * 例如链表为 3->7->10 , 逆置后变为  10->7->3
     */
    public void reverse() {
    	if(size == 0 || size == 1){
    		return ;
    	}
    	Node node = head;
    	Node nextNode = head.next;
    	Node tmp;
    	node.next = null;
    	do{
    		tmp = nextNode.next;
    		nextNode.next = node;
    		node = nextNode;
    		nextNode = tmp;
    	}while(nextNode != null);
    	head = node;
    }

    /**
     * 删除一个单链表的前半部分
     * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
     * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10
     */
    public void removeFirstHalf() {
    	if(size == 0 || size == 1){
    		return ;
    	}
    	int num = size / 2;
    	size -= num;
    	Node node = head;
    	while(num > 0){
    		node = node.next;
    		num--;
    	}
    	head = node;
    }
    
    /**
     * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
     *
     * @param i
     * @param length
     */
    public void remove(int i, int length) {
    	checkMinBound(i);
    	checkMaxBound(i + length, size);
    	Node prev = null;
    	Node node = head;
    	int index = 0;
    	while(index < i){
    		prev = node;
    		node = node.next;
    		index++;
    	}
    	Node nextNode = node.next;
    	while(index < (i + length - 1)){
    		nextNode = nextNode.next;
    		index++;
    	}
    	size -= length;
    	if(i == 0){
    		head = nextNode;
    	}else{
    		prev.next = nextNode;
    		head = prev;
    	}
    }

    /**
     * 假定当前链表和list均包含已升序排列的整数
     * 从当前链表中取出那些list所指定的元素
     * 例如当前链表 = 11->101->201->301->401->501->601->701
     * listB = 1->3->4->6
     * 返回的结果应该是[101,301,401,601]
     *
     * @param list
     */
    public int[] getElements(LinkedList list) {
    	int[] ret = new int[list.size()];
    	if(ret.length == 0){
    		return ret;
    	}
    	int index;
    	int j = 0;
    	Node node = head;
    	for(int i=0;i<list.size();i++){
    		index = (int)list.get(i);
    		while(j < index){
    			node = node.next;
    			if(node == null){
    				throw new IndexOutOfBoundsException();
    			}
    			j++;
    		}
    		ret[i] = (int)node.data;
    	}
    	return ret;
    }

    /**
     * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 从当前链表中中删除在list中出现的元素
     *
     * @param list
     */

    public void subtract(LinkedList list) {
    	if(size == 0){
    		return ;
    	}
    	Node prev = null;
    	Node node = head;
    	int data;
    	for(int i=0;i<list.size();i++){
    		data = (int)list.get(i);
    		do{
    			if(data == (int)node.data){
    				if(prev == null){
    					head = node.next;
    				}else{
    					prev.next = node.next;
    				}
    				size--;
    				node = node.next;
    				break;
    			}
    			prev = node;
    		}while((node = node.next) != null);
    	}
    }

    /**
     * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
     */
    public void removeDuplicateValues() {
    	if(size == 0 || size == 1){
    		return ;
    	}
    	Node node = head;
    	Node next = node.next;
    	do{
    		if((int)node.data == (int)next.data){
    			node.next = next.next;
    			size--;
    		}else{
    			node = node.next;
    		}
    		next = next.next;
    	}while(next != null);
    }

    /**
     * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
     *
     * @param min
     * @param max
     */
    public void removeRange(int min, int max) {
    	Node prev = null;
    	Node node = head;
    	boolean foundMin = false;
    	do{
    		if(!foundMin && (int)node.data > min){
    			foundMin = true;
    		}
    		if(foundMin && (int)node.data > max){
    			break;
    		}
    		if(foundMin == false){
    			prev = node;
    		}else{
    			size -= 1;
    		}
    		node = node.next;
    	}while(node != null);
    	if(prev == null){
    		head = node;
    	}else{
    		prev.next = node;
    	}
    }

    /**
     * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
     * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
     *
     * @param list
     */
    public LinkedList intersection(LinkedList list) {
    	LinkedList ret = new LinkedList();
    	Node node = head;
    	int nodeValue;
    	int elementValue;
    	for(int i=0;i<list.size();i++){
    		nodeValue = (int)node.data;
    		elementValue = (int)list.get(i);
    		if(nodeValue == elementValue){
    			ret.add(nodeValue);
    		}else if(nodeValue < elementValue){
    			node = node.next;
    			while(node != null){
    				nodeValue = (int)node.data;
    				if(nodeValue == elementValue){
    					ret.add(nodeValue);
    					node = node.next;
    					break;
    				}else if(nodeValue > elementValue){
    					break;
    				}
    				node = node.next;
    			}
    		}
    	}
        return ret;
    }
    
    public String toString(){
    	Node node = head;
    	StringBuilder sb = new StringBuilder();
    	if(node == null){
    		return "";
    	}else{
    		sb.append(head.data);
    		while((node = node.next) != null){
    			sb.append(",").append(node.data);
    		}
    		return sb.toString();
    	}
    }
}
