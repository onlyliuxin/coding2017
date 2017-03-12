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
		}else{
			//链表中已经有元素
			last.next = temp;
			temp.pre = last;
		}
		last = temp;
		size++;
		temp = null;
	}

	@Override
	public void clear() {
		while(size > 0){
			temp = last.pre;
			last.pre = null;
			temp.next = null;
			
			if(temp == head){
				last = null;
			}else{
				last = temp;
			}
			size--;
		}
		temp = null;
	}

	@Override
	public boolean contains(Object o) {
		temp = head.next;
		if(o == null){
			while(temp != null){
				if(temp.data == null){
					return true;
				}
				temp = temp.next;
			}
		}
		return false;
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
		}else if(index == 0){
			//链表中有两个或更多的元素，但是移除下标为0的元素
			temp = head.next;
			obj  = temp.data;
			
			head.next = temp.next;
			temp.next.pre = head;
			temp.pre  = null;
			temp.next = null;
			temp      = null;
		}else if(index == size - 1){
			//链表中有两个或更多的元素，但是移除下标为size()-1的元素
			obj = head.data;
			
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

}
