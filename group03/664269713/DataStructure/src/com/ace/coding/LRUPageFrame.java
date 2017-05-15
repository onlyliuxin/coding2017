package com.ace.coding;

/**
 * 用双向链表实现LRU算法
 *
 */
public class LRUPageFrame {

	private int capacity;
	private int currentSize;
	
	private Node first;// 链表头
	private Node last;// 链表尾

	
	public LRUPageFrame(int capacity) {
		currentSize = 0;
		this.capacity = capacity;
	}

	/**
	 * 获取缓存中对象
	 */
	public void access(int pageNum) {
        Node newNode = new Node();
        newNode.pageNum = pageNum;

        if(currentSize == 0){
            first = newNode;
            last = first;
            currentSize ++;
        } else {
            // if the node is exist then moveToHead
            //else add to first
                // if currentSize > 3 removeLast
            Node currentNode = getNode(pageNum);
            if(currentNode != null){
                remove(currentNode);
                addFirst(newNode);
            } else {
                addFirst(newNode);
                if(currentSize > 3){
                    removeLast();
                }
            }
        }
	}

	private void remove(Node node){
        Node pNode = first;
        while(pNode != null){
            if(pNode == node){
                if(pNode == last){
                    last = pNode.prev;
                } else {
                    pNode.next.prev = pNode.prev;
                }
                if(pNode == first){
                    first = pNode.next;
                } else {
                    pNode.prev.next = pNode.next;
                }
                currentSize --;
            }
            pNode = pNode.next;
        }
    }

    private Node getNode(int pageNum){
	    Node pNode = first;
        while(pNode != null){
            if(pNode.pageNum == pageNum){
                return pNode;
            }
            pNode = pNode.next;
        }
        return null;
    }

	private void removeLast(){
	    Node pNode = last;
	    if(first.next == null){
	        first = null;
	        last = null;
        } else {
	        last.prev.next  = null;
	        last = last.prev;
        }

//        if(last != null){
//	        if(last.prev != null){
//	            last.prev.next = null;
//            } else {
//	            first = null;
//            }
//            last = last.prev;
//        }

        currentSize --;
    }

    private void addFirst(Node newNode){
	    if(last == null){
            last = newNode;
        } else {
	        newNode.next = first;
	        first.prev = newNode;
        }
        first = newNode;
	    currentSize ++;
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
	private static class Node {

		Node prev;
		Node next;
		int pageNum;

		Node() {
		}
	}
}
