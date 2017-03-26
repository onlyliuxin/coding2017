package lesson01;

import java.util.NoSuchElementException;

public class LinkedList<E> implements List<E> {
	
	private int size;
	private Node<E> head;
	private Node<E> last;
	private Node<E> temp;
	
	public LinkedList() {
		head = new Node<E>();
	}
	

	@Override
	public boolean add(E e) {
		add(size, e);
		return true;
	}

	@Override
	public void add(int index, E element) {
		if(index < 0 || index > size){
			throw new IndexOutOfBoundsException("Error index:" + index);
		}
		temp = new Node<E>();
		temp.data = element;
		if(last == null){
			//链表中还没有元素
			head.next = temp;
			temp.pre = head;
			last = temp;
		}else if(index == size){
			//链表中已经有元素，在最后一个元素后面添加元素
			last.next = temp;
			temp.pre = last;
			last = temp;
		}else{
			Node<E> pos = head.next;
			for(int i = 0; i < index; i++){
				pos = pos.next;
			}
			
			temp.pre = pos.pre;
			pos.pre.next = temp;
			
			pos.pre = temp;
			temp.next = pos;
		}
		size++;
		temp = null;
	}

	@Override
	public void clear() {
		while(pollLast() != null){
		}
	}

	@Override
	public boolean contains(Object o) {
		return indexOf(o) >= 0;
	}

	@Override
	public int indexOf(Object o) {
		temp = head.next;
		if(o == null){
			for(int i = 0; i < size; i++){
				if(temp.data == null){
					temp = null;
					return i;
				}
				temp = temp.next;
			}
		}else{
			for(int i = 0; i < size; i++){
				if(o.equals(temp.data)){
					temp = null;
					return i;
				}
				temp = temp.next;
			}
		}
		return -1;
	}

	@Override
	public E get(int index) {
		checkIndex(index);
		
		temp = head.next;
		for(int i = 0; i < index; i++){
			temp = temp.next;
		}
		E obj = temp.data;
		temp = null;
		return obj;
	}

	private void checkIndex(int index) {
		if(index < 0 || index >= size){
			throw new IndexOutOfBoundsException("Error index: " + index);
		}
	}

	@Override
	public E remove(int index) {
		checkIndex(index);
		E obj = null;
		if(size == 1){
			//链表中只有一个元素
			obj = last.data;
			head.next = null;
			last.pre  = null;
			last      = null;
		}else if(index == size - 1){
			//链表中有两个或更多的元素，但是移除下标为size()-1的元素
			obj = last.data;
			
			last = last.pre;
			last.next.pre = null;
			last.next = null;
		}else{
			temp = head.next;
			for(int i = 0; i < index; i++){
				temp = temp.next;
			}
			obj = temp.data;
			
			temp.pre.next = temp.next;
			temp.next.pre = temp.pre;
			temp.pre  = null;
			temp.next = null;
			temp      = null;
		}
		size--;
		return obj;
	}

	@Override
	public boolean remove(Object o) {
		int index = indexOf(o);
		if(index >= 0){
			remove(index);
			return true;
		}
		return false;
	}

	@Override
	public E set(int index, E element) {
		checkIndex(index);
		
		temp = head.next;
		for(int i = 0; i < index; i++){
			temp = temp.next;
		}
		E obj = temp.data;
		temp.data  = element;
		temp = null;
		return obj;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Object[] toArray() {
		Object[] arr = new Object[size];
		temp = head.next;
		for(int i = 0; i < size; i++){
			arr[i] = temp.data;
			temp = temp.next;
		}
		return arr;
	}

	@Override
	public Iterator<E> iterator() {
		return new Ite();
	}
	
	private class Ite implements Iterator<E>{
		int pos;
		int lastRet = -1;
		
		@Override
		public boolean hasNext() {
			return pos < size;
		}

		@Override
		public E next() {
			if(pos == size){
				throw new NoSuchElementException();
			}
			E element = get(pos);
			lastRet = pos;
			pos++;
//			E element = get(lastRet = pos++);
			return element;
		}

		@Override
		public void remove() {
			if(lastRet == -1){
				throw new RuntimeException();
			}
			LinkedList.this.remove(lastRet);
			lastRet = -1;
		}
		
	}
	
	private static final class Node<E>{
		E data;
		Node<E> next;
		Node<E> pre;
	}
	
	public void addFirst(E e){
		add(0, e);
	}
	
	public void addLast(E e){
		add(size, e);
	}
	
	public E removeFirst(){
		ensureElementExists();
		return remove(0);
		
	}


	private void ensureElementExists() {
		if(size == 0){
			throw new NoSuchElementException();
		}
	}
	
	public E removeLast(){
		ensureElementExists();
		return remove(size - 1); 
		
	}
	
	public E getFirst(){
		ensureElementExists();
		return get(0);
	}
	
	public E getLast(){
		ensureElementExists();
		return get(size - 1);
		
	}
	
	public E pollFirst(){
		if(size == 0){
			return null;
		}
		return remove(0);
	}
	
	public E pollLast(){
		if(size == 0){
			return null;
		}
		return remove(size - 1);
	}
	
	public E peekFirst(){
		if(size == 0){
			return null;
		}
		return get(0);
	}
	
	public E peekLast(){
		if(size == 0){
			return null;
		}
		return get(size - 1);
	}
	
	

}
