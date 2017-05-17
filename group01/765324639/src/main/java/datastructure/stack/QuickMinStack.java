package datastructure.stack;

/**
 * 设计一个栈，支持栈的push和pop操作，以及第三种操作findMin, 它返回改数据结构中的最小元素
 * finMin操作最坏的情形下时间复杂度应该是O(1) ， 简单来讲，操作一次就可以得到最小值
 * @author liuxin
 *
 */
public class QuickMinStack {
    
    private int size = 0;
    private int index = 0; // 目前的最早入库索引
    private Node head;
    
    public int findMin(){
        if (head == null) {
            throw new RuntimeException("Stack is empty, don't have min!");
        }
        return head.data;
    }
    
	public void push(int data){
	    if (size == 0) {
	        head = new Node(size++, data);
	    } else {
	        Node temp = head;
	        while (temp.next != null && data > temp.data) {
	            temp = temp.next;
	        }
	        Node newNode = new Node(size++, data);
	        
	        // 未到达最后节点，或者到达最后节点，但是要插入的数据小于最后一个节点，此时插入到temp节点之前
	        if (unReachedLastNodeOrSmallThanLastNode(data, temp)) {
    	        insertBefore(newNode, temp);
	        } else { // 其他情况插入到temp节点之后
	            insertAfter(newNode, temp);
	        }
	    }
	}

    private boolean unReachedLastNodeOrSmallThanLastNode(int data, Node temp) {
        return temp.next != null || (temp.next == null && data < temp.data);
    }
	
	/**
	 * 将src节点插入到dest之前
	 * @param src
	 * @param dest
	 */
	private void insertBefore(Node src, Node dest) {
	    if (dest.prev == null) { // dest为首个元素
            head = src;
	    } else {
    	    src.prev = dest.prev;
    	    dest.prev.next = src;
	    }
	    dest.prev = src;
        src.next = dest;
	}
	
	/**
	 * 将src节点插入到dest之后
	 * @param src
	 * @param dest
	 */
	private void insertAfter(Node src, Node dest) {
	    if (dest.next == null) { // temp为最后一个元素
            dest.next = src;
            src.prev = dest;
        } else {
            dest = dest.next;
            insertBefore(src, dest);
        }
	}
	
	public int pop(){
	    Node temp = head;
	    while (temp.next != null) {
	        if (temp.index == this.index) {
	            Node dest =  remove(temp);
	            return dest.data;
	        }
	        temp = temp.next;
	    }
	    if (temp.index == this.index) {
	        Node dest =  remove(temp);
            return dest.data;
	    }
		throw new RuntimeException("Stack is Empty!");
	}
	
	private Node remove(Node node) {
	    if (node.prev == null && node.next == null) { // 唯一的元素,删除后将初始化size和index
	        this.size = 0;
	        this.index = 0;
	        return node;
	    }
	    if (node.prev == null) { // 元素在开头位置
	        head = head.next;
	        node.next.prev = null;
	        node.next = null;
	    } else if (node.next == null) { // 元素在结尾位置
	        node.prev.next = null;
	        node.prev = null;
	    } else { // 元素在中间位置
    	    node.prev.next = node.next;
    	    node.next.prev = node.prev;
    	    node.prev = null;
    	    node.next = null;
	    }
	    this.size--;
        this.index++;
	    return node;
	}
	
	public int size() {
	    return size;
	}
	
	// 调试输出用
//	@Override
//	public String toString() {
//	    StringBuilder builder = new StringBuilder(size);
//	    builder.append("[");
//	    Node temp = head;
//	    while (temp.next != null) {
//	        builder.append(temp.data + ", ");
//	        temp = temp.next;
//	    }
//	    builder.append(temp.data + "]");
//	    return builder.toString();
//	}
	
	private static class Node {
	    Node prev;
	    int data;
	    int index; // 插入的顺序
	    Node next;
	    
	    Node (int index, int data) {
	        this.index = index;
	        this.data = data;
	    }
	}
}
