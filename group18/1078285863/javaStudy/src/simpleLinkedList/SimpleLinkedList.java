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
		Node next;//ָ���һ��Ԫ��
		Node prev; //ָ��ǰһ��Ԫ��
	}
	
	public void add(Object o){
		addLast(o);
	}
	public void add(int index , Object o){
		//���ҵ�indexλ�õ�Ԫ��
		Node tmp = null;
		for (int i = 0; i < index; i++) {
			tmp = tmp.next;
		}
		
		Node pre = tmp.prev;
		Node next = tmp.next;
		
		if (null == pre) {
			addFirst(o);	//����ͷ��
		}
		else if(null == next){
			addLast(o);	//����β��
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
		//����ǰ��Ԫ�غͺ���Ԫ��
		Node prev = delNode.prev;
		Node next = delNode.next;
		
		if(null == head || null == tail){
			return null;
		}
		
		//ɾ�����ǵ�һ��Ԫ��
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
		
		//ɾ���ڵ�Ԫ��
		delNode.prev = null;
		delNode.next = null;
		delNode.data = null;
		
		return element;
	}
	
	public Object remove(int index){
		//��λ��Ԫ��
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
		//��¼ԭͷ���ڵ�
		final Node tmpHead = head;
		
		//�����½ڵ㲢��ֵ
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
			//����ͷ��Ԫ��ָ��
			head = newNode;
		}
	}
	public void addLast(Object o){
		//��¼ԭβ��Ԫ��
		final Node tmpTail = tail;
		
		//�����½ڵ㲢��ֵ
		Node newNode = new Node();
		if (null == newNode) {
			throw new OutOfMemoryError();
		}
		newNode.data = o;
		newNode.next = null;
		
		//���ǵ�һ�β���
		if(null == tail){
			head = newNode;
			tail = newNode;
		}
		else {
			tail.next = newNode;
			newNode.prev = tmpTail;
			tail = newNode;
		}
		
		//ͳ��Ԫ�ظ���
		size++;
	}
	public Object removeFirst(){
		//����ͷ���ڵ�
		Object element = head.data;
		Node nextNode = head.next;
		
		//�ͷ�ͷ���
		head.data = null;
		head.next = null;
		
		//ֻ��һ��Ԫ��
		if (head == tail) {
			head = tail = null;
		}
		else {
			head = nextNode;
			nextNode.prev = null;
		}
		
		//����Ԫ������
		size--;
		
		return element;
	}
	public Object removeLast(){
		//��¼β��Ԫ��
		Object element = tail.data;
		Node prev = tail.prev;
		
		//�ͷ�β���׶�
		tail.next = null;
		tail.data = null;
		
		//������β��
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