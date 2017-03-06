package Week01;

import java.util.NoSuchElementException;

/*
 * time:2017-2-22 13:00 
 * 参考：http://blog.csdn.net/jianyuerensheng/article/details/51204598
 * 		http://www.jianshu.com/p/681802a00cdf
 * 		jdk1.8源码
 * */

//这里采用的是双向链表，jdk1.6中linkedList基于双向循环链表实现
public class LinkedList implements List {
	
	private int size = 0;
	private Node first; //指向头结点
	private Node last; //指向尾节点
	
	//在链表的end添加元素，调用自己的addLast()方法
	public void add(Object o){
		addLast(o);
	}
	
	//加在index后面,这段代码参考同组同学 spike
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
				//target.next = newNode;这行要不要加
				target.next.prev = newNode;//有点疑问
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
	//删除index指定的元素
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
			last = newNode; //如果f为null，说明只有last可以指向
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
 		//去除的元素指为null
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
	
	//定义结点
	private static class Node{
		private Object data;
		//默认也是null
		private Node prev = null; //上一个元素节点
		private Node next = null;//下一个元素节点
		
		public Node(Object data, Node pre, Node next){
			this.data = data;
			this.prev = pre;
			this.next = next;
		}
	}
}
