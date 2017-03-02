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
	 * һ���޲ι��캯��������һ����List
	 * 
	 */
	public  MyLinkedList(){

	}



	/**
	 * ���Ԫ�����б�β��
	 * @param Object(��ӵ�Ԫ��)
	 */
	public void add(Object o) {

		addLast(o);
	}


	/**
	 * ���б�ָ��λ�����Ԫ��,���Ǿ�Ԫ��
	 * @param index ����λ�ã�Object ���Ԫ��
	 */
	public void add(int index, Object o) {

		if(!(index >= 0 && index <= size())){
			throw new RuntimeException("The index"+index+"is out of band.");
		}
		
		Node x = node(index);
		x.data = o;
		
	}

    /**
     * ��ȡָ��λ�õ�Ԫ��data
     * @param index ��Ҫ��ȡԪ������λ��
     */
	public Object get(int index) {
		
		if(!(index >= 0 && index <= size())){
			throw new RuntimeException("The index"+index+"is out of band.");
		}
		
		return node(index).data;
	}


	/**
	 * ɾ��ָ��λ��Ԫ��
	 * @param index ɾ���б�Ԫ������λ��
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
	 * ��ȡ�б�ǰsize
	 */
	public int size() {
		return size;
	}

	/**
	 * ���ͷԪ��,���ͷԪ��Ϊ�գ������ø�Ԫ��ͬʱΪβԪ��
	 * @param Object�� ��ӵ�ͷԪ�أ�
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
	 * ���βԪ�أ����βԪ��Ϊ�գ�ͬʱ���ø�Ԫ��ΪͷԪ��
	 * @param Object(��ӵ�βԪ��)
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
	 * �Ƴ���һ��Ԫ�أ�����Ƴ��Ժ��б�Ϊ���� first = next = null
	 * @return �����Ƴ�Ԫ��
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
	 * �Ƴ����һ��Ԫ��
	 * @return �����Ƴ�Ԫ��
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
     * ����Node�ڲ���
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
	 * ����һ����ȡ�±�λ��node�ķ���
	 * �����ǰ�±�С��list���ȵ�һ�䣬���ͷ��ʼ���������� ��β����ʼ����
	 * @param index ����Ԫ�ص�λ��
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
