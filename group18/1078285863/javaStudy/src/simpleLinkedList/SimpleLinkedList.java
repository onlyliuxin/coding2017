package simpleLinkedList;

import java.util.Iterator;
import java.util.LinkedList;

import javax.sound.sampled.Line;

public class SimpleLinkedList {
	//LinkedList<E>
	private int size = 0;
	private Node head = null;
	private Node tail = null;
	
	private static  class Node{
		Object data;
		Node next;//指向后一个元素
		Node prev; //指向前一个元素
	}
	
	public void add(Object o){
		addLast(o);
	}
	public void add(int index , Object o){
		//先找到index位置的元素
		Node tmp = null;
		for (int i = 0; i < index; i++) {
			tmp = tmp.next;
		}
		
		Node pre = tmp.prev;
		Node next = tmp.next;
		
		if (null == pre) {
			addFirst(o);	//加入头部
		}
		else if(null == next){
			addLast(o);	//加入尾部
		}
		else {
			add(o);
		}
	}
	public Object get(int index){
		if (index > size || index <0) {
			throw new IllegalArgumentException();
		}
		
		Node temp = null;
		for(int i=0;i<index;i++){
			temp = temp.next;
		}
		
		return temp.data;
	}
	
	public Object remove(Node delNode){
		Object element = delNode.data;
		//保存前向元素和后向元素
		Node prev = delNode.prev;
		Node next = delNode.next;
		
		if(null == head || null == tail){
			return null;
		}
		
		//删除的是第一个元素
		if(null == prev){
			head = next;
		}
		else if (null == next) {
			tail = prev;
		}
		else {
			prev.next = next;
			next.prev = prev;
		}
		
		//删除节点元素
		delNode.prev = null;
		delNode.next = null;
		delNode.data = null;
		
		return element;
	}
	
	public Object remove(int index){
		//定位到元素
		Node temp = null;
		for(int i=0;i<index;i++){
			temp = temp.next;
		}
		
		return null;
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		//记录原头部节点
		final Node tmpHead = head;
		
		//申请新节点并赋值
		Node newNode = new Node();
		if (null == newNode) {
			throw new OutOfMemoryError();
		}
		newNode.data = o;
		newNode.next = null;
		newNode.prev = null;
		
		if (null == head) {
			head = tail = newNode;
		}
		else {
			newNode.next = tmpHead;
			tmpHead.prev = newNode;
			//更改头部元素指针
			head = newNode;
		}
	}
	public void addLast(Object o){
		//记录原尾部元素
		final Node tmpTail = tail;
		
		//申请新节点并赋值
		Node newNode = new Node();
		if (null == newNode) {
			throw new OutOfMemoryError();
		}
		newNode.data = o;
		newNode.next = null;
		
		//考虑第一次插入
		if(null == tail){
			head = newNode;
			tail = newNode;
		}
		else {
			tail.next = newNode;
			newNode.prev = tmpTail;
			tail = newNode;
		}
		
		//统计元素个数
		size++;
	}
	public Object removeFirst(){
		//保存头部节点
		Object element = head.data;
		Node nextNode = head.next;
		
		//释放头结点
		head.data = null;
		head.next = null;
		
		//只有一个元素
		if (head == tail) {
			head = tail = null;
		}
		else {
			head = nextNode;
			nextNode.prev = null;
		}
		
		//减少元素数量
		size--;
		
		return element;
	}
	public Object removeLast(){
		//记录尾部元素
		Object element = tail.data;
		Node prev = tail.prev;
		
		//释放尾部阶段
		tail.next = null;
		tail.data = null;
		
		//设置新尾部
		tail = prev;
		
		if (head == tail) {
			head = null;
			tail = null;
		}
		else {
			prev.next = null;
		}
		size--;
		return element;
	}
	public Iterator iterator(){
		return null;
	}
}