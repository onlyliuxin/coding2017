package firstHomeWork.util;

import java.util.NoSuchElementException;

/**
 * @Description: 双向链表
 * @author: leijing
 * @date: 2017年2月24日 上午11:37:58
 * @param <E>
 */
public class DoubleLinkedList<E> {

	private int size;//节点个数
	private Node<E> head;//头节点
	private Node<E> tail;//尾节点

	public Node<E> getHead() {
		return head;
	}
	public void setHead(Node<E> head) {
		this.head = head;
	}
	public Node<E> getTail() {
		return tail;
	}
	public void setTail(Node<E> tail) {
		this.tail = tail;
	}
	/**
	 * @Description: 添加元素到头部
	 * @param e
	 * @return: boolean
	 * @author: leijing  
	 * @date: 2017年2月24日 上午11:38:20
	 */
	public boolean addFirst(E e) {
		Node<E> node = new Node<E>(e);
		if(null == head){
			node.prev = null;
			head = node;
			tail = head;
		}else{
			node.next = head;
			head.prev = node;
			head = node;
		}
		size++;
		return true;
	}
	/**
	 * @Description: 添加元素到尾部
	 * @param e
	 * @return: boolean
	 * @author: leijing  
	 * @date: 2017年2月24日 上午11:38:20
	 */
	public boolean addLast(E e) {
		Node<E> node = new Node<E>(e);
		if(null == tail){
			tail.next = null;
			tail = node;
			head = tail;
		}else{
			tail.next = node;
			node.prev = tail;
			tail = node;
		}
		size++;
		return true;
	}

	public boolean remove(E o) throws Exception {
		if(isEmpty()){
			throw new Exception("链表为空，没有元素可以删除");
		}
		Node<E> current = head;//从头节点开始删
		if(o == null){
			while(current != null){
				if(current.data == null){
					current.prev.next = current.next;//将当前节点的前驱节点的后继节点改为当前节点的后继
					current.next.prev = current.prev;//将当前节点后继节点的前驱节点改为当前节点的前驱节点
					current.next = null;//当前节点的前驱改为null
					current.prev = null;//当前节点的后继改为null
					size--;
					return true;
				}
				current = current.next;
			}
		}else{
			while(current != null){
				if(o.equals(current.data)){
					current.prev.next = current.next;//将当前节点的前驱节点的后继节点改为当前节点的后继
					current.next.prev = current.prev;//将当前节点后继节点的前驱节点改为当前节点的前驱节点
					current.next = null;//当前节点的前驱改为null
					current.prev = null;//当前节点的后继改为null
					size--;
					return true;
				}
				current = current.next;
			}
		}

		return false;
	}

	/**
	 * @Description: 返回元素个数
	 * @return: int
	 * @author: leijing  
	 * @date: 2017年2月24日 上午11:38:20
	 */
	public int size() {
		return size;
	}
	/**
	 * @Description: 是否空链表
	 * @return: boolean
	 * @author: leijing  
	 * @date: 2017年2月24日 上午11:38:20
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	/**
	 * @Description: 检查下标有效性
	 * @param index
	 * @return: void
	 * @author: leijing  
	 * @date: 2017年2月24日 上午11:40:15
	 */
	private void rangeCheck(int index){
		if(index < 0 || index > size){
			throw new IndexOutOfBoundsException();
		}
	}
	public E get(int index) {
		rangeCheck(index);
		Node<E> current = head;//从头节点开始
		int i = 0;
		while(current != null){
			if(i == index){
				return current.data;
			}
			current = current.next;
			i++;
		}
		return null;
	}
	public Node<E> node(int index) {
		rangeCheck(index);
		if(index < size >> 1){//小于元素大小的二分之一，从头节点开始遍历
			Node<E> current = head;//从头节点开始
			for(int i = 0 ; i < index ; i++){
				current = current.next;
			}
			return (Node<E>)current;
		}else{//从尾节点开始遍历
			Node<E> current = tail;//从尾节点开始
			for(int i = 0 ; i < index ; i++){
				current = current.prev;
			}
			return (Node<E>)current;
		}
	}
	/**
	 * @Description: 设置某个位置的元素
	 * @param index
	 * @param data
	 * @return: E
	 * @author: leijing  
	 * @date: 2017年2月24日 上午11:58:32
	 */
	public E set(int index, E data) {
		rangeCheck(index);
		Node<E> current = head;//从头节点开始
		int i = 0;
		while(current != null){
			if(i == index){
				Node<E> node = new Node<E>(data);
				Node<E> prev = current.prev;
				prev.next = node;
				node.prev = prev;
				node.next = current;
				current.prev = node;
				size++;
				return data;
			}
			current = current.next;
			i++;
		}
		return null;
	}
	/**
	 * @Description: 判断是否包含某个元素
	 * @param o
	 * @throws Exception
	 * @return: boolean
	 * @author: leijing  
	 * @date: 2017年2月24日 上午11:57:35
	 */
	public boolean contains(Object o) throws Exception {
		if(isEmpty()){
			throw new Exception("链表为空，没有任何元素");
		}
		Node<E> current = head;//从头节点开始找
		if(o == null){
			while(current != null){
				if(current.data == null){
					return true;
				}
				current = current.next;
			}
		}else{
			while(current != null){
				if(o.equals(current.data)){
					return true;
				}
				current = current.next;
			}
		}
		return false;
	}
	/**
	 * @Description: 清空链表，删除所有元素
	 * @return: void
	 * @author: leijing  
	 * @date: 2017年2月24日 下午4:41:56
	 */
	public void clear() {
		Node<E> current = head;//从头节点开始遍历
		while(current != null){
			Node<E> tmp = current;
			current = current.next;
			tmp.prev = null;
			tmp.next = null;
		}
		size = 0;
	}

	public Iterator<E> iterator() {
		return new ListItr(0);
	}
	private  class ListItr implements Iterator<E>{
		private Node<E> lastReturned = null;//当前的节点
		private Node<E> next;//下一个节点
		private int nextIndex;//当前索引的下标
		
		public ListItr(int nextIndex){
			next = (nextIndex == size) ? null : node(nextIndex);
		}

		@Override
		public boolean hasNext() {
			return nextIndex < size;
		}

		@Override
		public E next() {
			if (!hasNext()){
				throw new NoSuchElementException();
			}

			lastReturned = next;
			next = next.next;
			nextIndex++;
			return lastReturned.data;
		}

		@Override
		public void remove() {
			 if (lastReturned == null){
				 throw new IllegalStateException();
			 }
	          
			 if(lastReturned == next){//tail node
				 lastReturned.prev.next = null;
				 lastReturned.prev = null;
			 }else{
				 lastReturned.prev.next = lastReturned.next;
				 lastReturned.next.prev = lastReturned.prev;
				 lastReturned.next = null;
				 lastReturned.prev = null;
			 }
			nextIndex--;
		}
		
		public boolean hasPrev(){
			return nextIndex > 0;
		}
		
		public E prev(){
			if(!hasPrev()){
				throw new NoSuchElementException();
			}
			next = lastReturned = (next == null ) ? tail : next.prev;//如果是头节点，前一个指向尾节点
			nextIndex--;
			return lastReturned.data;
		}

	}

	static class Node<E>{
		private E data;
		private Node<E> prev;//前驱节点
		private Node<E> next;//后继节点

		public Node(E data){
			this.data = data;
		}

		public E getData() {
			return data;
		}

		public void setData(E data) {
			this.data = data;
		}

		public Node<E> getPrev() {
			return prev;
		}

		public void setPrev(Node<E> prev) {
			this.prev = prev;
		}

		public Node<E> getNext() {
			return next;
		}

		public void setNext(Node<E> next) {
			this.next = next;
		}
	}
}
