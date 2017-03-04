package Week01;

import java.util.NoSuchElementException;

/*
 * time:2017-2-22 13:00 
 * �ο���http://blog.csdn.net/jianyuerensheng/article/details/51204598
 * 		http://www.jianshu.com/p/681802a00cdf
 * 		jdk1.8Դ��
 * */

//������õ���˫������jdk1.6��linkedList����˫��ѭ������ʵ��
public class LinkedList implements List {
	
	private int size = 0;
	private Node first; //ָ��ͷ���
	private Node last; //ָ��β�ڵ�
	
	//�������end���Ԫ�أ������Լ���addLast()����
	public void add(Object o){
		addLast(o);
	}
	
	//����index����,��δ���ο�ͬ��ͬѧ spike
	public void add(int index, Object o){
		if (index < 0 || index > size) 
			throw new IllegalArgumentException();
		size++;
		if (index == size){
			addLast(o);
		}else{
			Node target = findIndex(index);
			Node newNode = new Node(o, target,target.next);
			if (last == target){
				last = newNode;
			}else{
				//target.next = newNode;����Ҫ��Ҫ��
				target.next.prev = newNode;//�е�����
			}
		}
		size++;
	}
	
	public Object get(int index){
		if ( index < 0 || index > size){
			throw new IllegalArgumentException();
		}
		return findIndex(index).data;
	}
	//ɾ��indexָ����Ԫ��
	public Object remove(int index){
		if (index < 0 || index > size){
			throw new IllegalArgumentException();
		}
		
		Node target = findIndex(index);
		if (target == first){
			first = first.next;
			first.prev = null;
		}else if(target == last){
			last = last.prev;
			last.next = null;
		}else{
			target.prev.next = target.next;
			target.next.prev = target.prev;
		}
		return target.data;
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		
		Node f = first;
		Node newNode = new Node(o,null,f);
		first = newNode;
		if (f == null) 
			last = newNode; //���fΪnull��˵��ֻ��last����ָ��
		else
			f.prev = newNode;
		size++;
	}
	
	public void addLast(Object o){
		Node l = last;
		Node newNode = new Node(o, l, null);
		last = newNode;
		if (l == null)
			first = newNode; 
		else
			l.next = newNode;
		size++;
	}
	
	
	public Object removeFirst() {
		if ( first == null)
			throw new NoSuchElementException();
		Node f = first;
		Object data = f.data;
		Node next = f.next;
 		//ȥ����Ԫ��ָΪnull
		f.data = null;
		f.next = null;
		first = next;
		if (next == null)
			last = null;
		else
			next.prev = null;
		size--;
		return data;
	}
	
	public Object removeLast(){
		if (last == null)
			throw new NoSuchElementException();
		Node l = last;
		Object data = l.data;
		Node previous = l.prev;
		l.data = null;
		l.prev = null;
		last = previous;
		if (previous == null)
			first = null;
		else
			previous.next = null;
		size--;
		return data;
	}
	
	public Iterator iterator(){
		return new LinkedListIterator();
	}
	 
	private class LinkedListIterator implements Iterator {
		Node curNode = first;
		public boolean hashNext() {
			return curNode != null;
		}
		public Object next() {
			if (!hashNext())
				throw new NoSuchElementException();
			Object data = curNode.data;
			curNode = curNode.next;
			return data;
		}
	}
	private Node findIndex(int index) {
		Node target = first;
		int i = 0;
		while(i < index){
			target = target.next;
			i++;
		}
		return target;
	}
	
	//������
	private static class Node{
		private Object data;
		//Ĭ��Ҳ��null
		private Node prev = null; //��һ��Ԫ�ؽڵ�
		private Node next = null;//��һ��Ԫ�ؽڵ�
		
		public Node(Object data, Node pre, Node next){
			this.data = data;
			this.prev = pre;
			this.next = next;
		}
	}
}
