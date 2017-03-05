package cn.xl;

import java.util.Iterator;

/**
 * 
 * @author XIAOLONG
 * @param <E>
 *
 */
public class MyLinkedList implements MyList {

	private int size = 0; 

	private Node first;

	private Node last;


	/**
	 * 一个无参构造函数，构造一个空List
	 * 
	 */
	public  MyLinkedList(){

	}



	/**
	 * 添加元素至列表尾部
	 * @param Object(添加的元素)
	 */
	public void add(Object o) {

		addLast(o);
	}


	/**
	 * 向列表指定位置添加元素,覆盖旧元素
	 * @param index 坐标位置，Object 添加元素
	 */
	public void add(int index, Object o) {

		if(!(index >= 0 && index <= size())){
			throw new RuntimeException("The index"+index+"is out of band.");
		}
		
		Node x = node(index);
		x.data = o;
		
	}

    /**
     * 获取指定位置的元素data
     * @param index 将要获取元素所在位置
     */
	public Object get(int index) {
		
		if(!(index >= 0 && index <= size())){
			throw new RuntimeException("The index"+index+"is out of band.");
		}
		
		return node(index).data;
	}


	/**
	 * 删除指定位置元素
	 * @param index 删除列表元素所在位置
	 */
	public Object remove(int index) {
		
		if(!(index >= 0 && index <= size())){
			throw new RuntimeException("The index"+index+"is out of band.");
		}
		
		final Node node = node(index);
		final Object o = node.data;
		if(first.equals(node)){
			removeFirst();
		}else if(last.equals(node)){			
			removeLast();
		}else{
			final Node prev = node.prev;
			final Node next = node.next;
			
			prev.next = next;
			next.prev = prev;
			node.prev = null;
			node.next = null;
		}
		node.data = null;
		size --;
		return o;
	}


	/**
	 * 获取列表当前size
	 */
	public int size() {
		return size;
	}

	/**
	 * 添加头元素,如果头元素为空，则设置该元素同时为尾元素
	 * @param Object（ 添加的头元素）
	 */
	public void addFirst(Object o){

		final Node f = first;
		final Node newNode = new Node(null,o,f);
		if(f == null){
			last = newNode;
		}else{
			f.prev = newNode;
		}
		size ++;
	}

	/**
	 * 添加尾元素，如果尾元素为空，同时设置该元素为头元素
	 * @param Object(添加的尾元素)
	 */
	public void addLast(Object o){

		final Node l = last;
		final Node newNode = new Node(l,o,null);
		if(l == null){
			first = newNode;
		}else{
			l.next = newNode;
		}
		size ++;
	}

	/**
	 * 移除第一个元素，如果移除以后列表为空则 first = next = null
	 * @return 返回移除元素
	 */
	public Object removeFirst(){

		final Node f = first;
		final Object o =  f.data;
		final Node next = f.next ;
		f.next = null;
		f.data = null;
		first = next;
		if(next == null){
			last = next;
		}else{
			next.prev = null;
		}
        size --;
		return o;
	}

	/**
	 * 移除最后一个元素
	 * @return 返回移除元素
	 */
	public Object removeLast(){
		
		final Node l = last;
		final Object o = l.data;
		final Node prev = l.prev;
		l.data = null;
		l.prev = null;
		last = prev;
		if(prev == null){
			last = null;
		}else{
			prev.next = null;
		}
		size --;
		return o;
	}
	public Iterator iterator(){
		return null;
	}
    
    /**
     * 定义Node内部类
     *     
     */
	private static class Node{
		Object data;
		Node next;
		Node prev;

		Node(Node prev,Object o,Node next){
			this.data = o;
			this.next = next;
			this.prev = prev;
		}
	}
	
	/**
	 * 定义一个获取下标位置node的方法
	 * 如果当前下标小于list长度的一变，则从头开始遍历；否则 从尾部开始遍历
	 * @param index 查找元素的位置
	 */
	private Node node(int index){
		if (index < (size >> 1)) {
			Node x = first;
			for (int i = 0; i < index; i++)
				x = x.next;
			return x;
		} else {
			Node x = last;
			for (int i = size - 1; i > index; i--)
				x = x.prev;
			return x;
		}
	}
}
