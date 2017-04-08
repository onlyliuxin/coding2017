package com.coding.basic;

import java.util.NoSuchElementException;

/**
 * 链表数据结构
 * LinkedList
 * @author greenhills
 * 2017年2月22日 下午11:52:41
 */
public class LinkedList implements List {
	/**
	 * 链表数据量
	 */
	private int size=0;
	/**
	 * 链表头节点
	 */
	private Node head;
	/**
	 * 链表尾节点
	 */
	private Node tail;
	
	
	/**
	 * 在数据链尾部添加数据
	 */
	@Override
	public void add(Object o) {
		addLast(o);
	}
	
	/**
	 * 在数据链指定位置添加数据
	 */
	@Override
	public void add(int index, Object data) {
		checkIndex(index);
		Node old=getNode(index);
        link2Before(old,data);
	}
	
	/**
	 * 获取指定索引处数据
	 */
	@Override
	public Object get(int index) {
		checkIndex(index);
		return getNode(index).data;
	}
	
	/**
	 * 移除指定索引位置的节点
	 */
	@Override
	public Object remove(int index) {
		checkIndex(index);
		return unlink(getNode(index));
	}
	
	/**
	 * 获取数据链的数据量
	 */
	@Override
	public int size() {
		return this.size;
	}
	
	/**
	 * 获取数据链的数据量
	 */
	@Override
	@Deprecated
	public int capacity() {
		return size();
	}
	
	/**
	 * 判断是否为空
	 */
	@Override
	public boolean isEmpty() {
		return this.size==0;
	}
	
	/**
	 * 在数据链头部追加数据
	 * @param data
	 */
	public void addFirst(Object data){
		final Node oldNode=this.head;
		final Node newNode=new Node(null,data,oldNode);//形成新节点，并指向第一个节点
		this.head = newNode; //变更集合保存的首节点
		
		if(oldNode == null){ //没有数据
			this.tail = newNode; //变更集合保存的尾节点
		}else{
			oldNode.prev = newNode; //原首节点指向新的首节点
		}
		this.size++;//数据量加1
	}
	
	/**
	 * 在数据链尾部追加数据
	 * @param data
	 */
	public void addLast(Object data){
		final Node oldNode=this.tail;
		final Node newNode=new Node(oldNode,data,null);//形成新节点，并指向最后一个节点
		this.tail = newNode; //变更集合保存的尾节点
		
		if(oldNode == null){ //没有数据
			this.head = newNode; //变更集合保存的首节点
		}else{
			oldNode.next = newNode;//原尾节点指向新的尾节点
		}
		this.size++;//数据量加1
	}
	
	/**
	 * 把指定数据链接到指定节点前面
	 */
	void link2Before(Node node,Object data){
		//传进来的node就是后节点
		final Node prev=node.prev; //指定节点的上一个节点（前节点）
		//生成新节点，并指向前后的节点
		final Node newNode=new Node(prev,data,node);//生成新节点
		//后节点指向新节点
		node.prev = newNode;
		//前节点指向新节点
		if(prev == null){//没有前节点了（当前节点已是首节点）
			this.head = newNode;//把新的节点作为首节点
		}else{
			prev.next = newNode;
		}
		this.size++;//数据量加1
	}
	
	/**
	 * 把指定数据链接到指定节点后面
	 */
	void link2Last(Node node,Object data){
		//传进来的node就是前节点
		final Node next=node.next; //指定节点的下一个节点(后节点)
		//生成新节点，并指向前后的节点
		final Node newNode=new Node(node,data,next);
		//前节点指向新节点
		node.next = newNode;
		//后节点指向新节点
		if(next == null){//没有后节点了（当前节点已是尾节点）
			this.tail = newNode;//把新的节点作为尾节点
		}else{
			next.prev = newNode;
		}
		this.size++;//数据量加1
	}
	
	/**
	 * 移除首节点
	 * @return
	 */
	public Object removeFirst(){
		return unlink(getNode(0));
	}
	
	/**
	 * 移除尾节点
	 * @return
	 */
	public Object removeLast(){
		return unlink(getNode(this.size-1));
	}
	
	/**
	 * 移除节点
	 * @return
	 */
	Object unlink(Node node){
		final Object element = node.data;
        final Node next = node.next; //下一个节点
        final Node prev = node.prev;//前一个节点

        if (prev == null) {//待删除节点是首节点
            head = next;
        } else {
            prev.next = next;
            node.prev = null;//解除待删除节点的引用关系
        }

        if (next == null) {//待删除节点是尾节点
            tail = prev;
        } else {
            next.prev = prev;
            node.next = null;//解除待删除节点的引用关系
        }

        node.data = null;//清除节点数据
        size--;
        return element;//返回清除节点数据
	}
	
	/**
	 * 在中间位置创建Iterator遍历
	 * @return
	 */
	public Iterator iterator() {
        return new Its(this.size>>1);
    }
	
	/**
	 * 在指定位置创建Iterator遍历
	 * @return
	 */
	public Iterator iterator(int index) {
		checkIndex(index);
        return new Its(index);
    }
	
	/**
	 * 获取指定索引的节点对象
	 * @param @param index
	 * @param @return
	 */
	private Node getNode(int index){
		if (index < (this.size >> 1)) { //在前半部分
            Node node = this.head;
            for (int i = 0; i < index; i++){
            	node = node.next; //向后查找
            }
            return node;
        } else { //在后半部分
            Node node = this.tail;
            for (int i = this.size - 1; i > index; i--){
            	node = node.prev; //向前查找
            }
            return node;
        }
	}
	
	/**
	 * 判断是否为有效索引
	 * @param @param index
	 * @param @return
	 */
	private boolean isEffectiveIndex(int index){
		return (index>=0 && index < size);
	}
	
	/**
	 * 检测索引有效性，无效时抛出异常
	 * @param index
	 */
	private void checkIndex(int index) {
        if (!isEffectiveIndex(index))
            throw new IndexOutOfBoundsException("Index: "+index+" Out Of Bounds, 最大索引: "+(size-1));
    }
	
	
	/**
	 * 实现Iterator的内部实现类
	 */
	private class Its implements Iterator {
		private Node lastReturned = null;
        private Node node;//当前节点
        private int index;//当前节点索引
		
		public Its(int index){
			node = isEffectiveIndex(index) ? getNode(index) : null;
			this.index = index;
		}
		
		@Override
		public boolean hasNext() {
			return index < size;
		}

		@Override
		public Object next() {
			if (!hasNext())
                throw new NoSuchElementException();

            lastReturned = node;
            node = node.next;
            index++;
            return lastReturned.data;
			
		}
		
		public boolean hasPrevious() {
            return index >= 0;
        }

        public Object previous() {
            if (!hasPrevious())
                throw new NoSuchElementException();
            
            lastReturned = node;
            node = node.prev;
            index--;
            return lastReturned.data;
        }
	}
	
	/**
	 * 节点数据
	 * Node
	 */
	private static class Node{
		Object data;
		Node prev;
		Node next;
		
		Node(Node prev,Object data,Node next){
			this.prev=prev;
			this.data=data;
			this.next=next;
		}
	}
}
