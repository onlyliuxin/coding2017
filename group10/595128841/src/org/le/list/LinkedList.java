/**
 * 
 */
package org.le;

import java.util.NoSuchElementException;

/**
 * @author yue
 * @time 2017年2月19日
 */
public class  LinkedList<E> implements List<E> {
	
	private int size = 0;
	
	private Node<E> first;
	
	private Node<E> last;
	
	private static class Node<E>{
		E item;
		Node<E> prev;
		Node<E> next;
		Node(Node<E> prev,E item,  Node<E> next) {
			super();
			this.item = item;
			this.prev = prev;
			this.next = next;
		}
	}
	
	public LinkedList(){
		
	}
	
	/**
	 * 头部插入
	 */
	private void linkFirst(E e){
		final Node<E> f =  first;
		final Node<E> newNode = new Node<E>(null,e,f);
		first = newNode;
		if(f == null)
			last = newNode;
		else
			f.prev = newNode;
		size ++;
	}
	
	/**
	 * 尾部插入
	 */
	private void linkLast(E e){
		final Node<E> l = last;
		final Node<E> newNode = new Node<>(l,e,null);
		last = newNode;
		if(last == null)
			first = newNode;
		else
			l.next = newNode;
		size ++;
	}
	
	/**
	 * 某个不为null元素之前插入
	 */
	private void linkBefore(E e,Node<E> succ){
		final Node<E> pred = succ.prev;
		final Node<E> newNode = new Node<>(pred,e,succ);
		succ.prev = newNode;
		if(pred == null)
			first = newNode;
		else
			pred.next = newNode;
		size ++;
	}
	
	/**
	 * 删除头部元素
	 */
	private E unlinkFirst(Node<E> f){
		final E element = f.item;
		final Node<E> next = f.next;
		f.item = null;
		f.next = null;
		first = next;
		if(next == null)
			last = null;
		else
			next.prev = null;
		size -- ;
		return element;
	}
	/**
	 * 删除尾部元素
	 * @param l
	 * @return
	 */
	private E unlinkLast(Node<E> l){
		final E element = l.item;
		final Node<E> prev = l.prev;
		l.item = null;
		l.prev = null;
		last = prev;
		if(prev == null)
			first = null;
		else
			prev.next = null;
		size -- ;
		return element;
	}
	
	/**
	 * 删除指定节点
	 * @param e
	 * @return
	 */
	private E unlink(Node<E> e){
		final Node<E> prev = e.prev;
		final E element = e.item;
		final Node<E> next = e.next;
		
		if(prev == null){
			first = next;
		}else{
			prev.next = next;
			e.prev = null;
		}
			
		if(next == null){
			last = prev;
		}else{
			next.prev = prev;
			e.next = null;
		}
		e.item = null;
		size -- ;
		return element;
	}
	
	/**
	 * 该方法默认在尾部添加
	 */
	@Override
	public void add(E e) {
		linkLast(e);
	}

	/**
	 *
	 */
	@Override
	public void add(int index, E e) {
		checkPositionIndex(index);
		if(index == size){
			linkLast(e);
		}else{
			linkBefore(e, node(index));
		}
	}
	
	private Node<E> node(int index) {
		//小于容量一半
		if(index < (size >> 1)){
			Node<E> x = first;
			for(int i = 0; i < index; i++){
				x = x.next;
			}
			return x;
		}else{
			Node<E> x = last;
			for(int i = size - 1; i > index; i --){
				x = x.prev; 
			}
			return x;
		}
	}

	private void checkPositionIndex(int index){
		if(index <0 || index > size){
			throw new IndexOutOfBoundsException("索引越界：index："+index+"，size："+size);
		}
	}
	
	private void checkElementIndex(int index){
		if(index <0 || index >= size){
			throw new IndexOutOfBoundsException("索引越界：index："+index+"，size："+size);
		}
	}

	/**
	 *
	 */
	@Override
	public void remove(E obj) {
		if(obj == null){
			for(Node<E> x = first;x != null; x = x.next){
				if(x.item == null){
					unlink(x);
				}
			}
		}else{
			for(Node<E> x = first;x != null;x = x.next){
				if(obj.equals(x.item)){
					unlink(x);
				}
			}
		}
	}

	/**
	 *
	 */
	@Override
	public E remove(int index) {
		checkElementIndex(index);
		return unlink(node(index));
	}

	/**
	 *
	 */
	@Override
	public E get(int index) {
		checkElementIndex(index);
		return node(index).item;
	}

	/**
	 *
	 */
	@Override
	public E set(int index, E obj) {
		checkElementIndex(index);
		Node<E> x = node(index);
		E oldVal = x.item;
		x.item = obj;
		return oldVal;
	}

	/**
	 *
	 */
	@Override
	public int indexOf(E obj) {
		int index = 0;
		if(obj == null){
			for(Node<E> x = first;x != null;x = x.next){
				if(x.item == null)
					return index;
				index ++;
			}
		}else{
			for(Node<E> x = first; x != null; x = x.next){
				if(obj.equals(x.item))
					return index;
				index ++;
			}
		}
		return -1;
	}
	/**
	 * 弹出栈顶的元素，不删除元素
	 * @param e
	 * @return
	 */
	public E peek(){
		final Node<E> e = first;
		return e == null ? null : e.item;
	}
	
	/**
	 * 弹出栈顶元素，删除元素
	 * @return
	 */
	public E poll(){
		final Node<E> e = first;
		return (e == null) ? null : unlinkFirst(e);
	}
	/**
	 * 入栈，栈顶
	 * @param e
	 */
	public void push(E e){
		linkFirst(e);
	}
	
	/**
	 * 出栈，删除并返回栈顶元素
	 * @return
	 */
	public E pop(){
		final Node<E> f = first;
		if(f == null)
			throw new NoSuchElementException();
		return unlinkFirst(f);
	}

}
