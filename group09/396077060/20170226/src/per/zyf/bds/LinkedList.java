/**   
* @Title: LinkedList.java 
* @Description: 双向链表的实现
* @author glorychou
* @date 2017年2月24日 上午12:23:00 
*/
package per.zyf.bds;

/**
 * LinkedList的存储结构其实就是链表
 * @author glorychou
 *
 * @param <E>
 * @see per.zyf.bds.List<E>
 */
/**
 * @author glorychou
 *
 * @param <E>
 */
/**
 * @author glorychou
 *
 * @param <E>
 */
public class LinkedList<E> implements List<E> {
	// 链表头
	private Node<E> first;
	
	// 链表尾
	private Node<E> last;
	
	// 链表大小
	private int size;
	
	/* 
	 * @see per.zyf.bds.List#add(java.lang.Object)
	 */
	@Override
	public boolean add(E e) {
		linkLast(e);
		return true;
	}
	
	/* 
	 * @see per.zyf.bds.List#add(int, java.lang.Object)
	 */
	@Override
	public boolean add(int index, E e) {
		// 判断索引是否在合理的位置
		rangeCheck(index);
		
		// 索引值为链表长度，则添加到链表末尾
		if (index == size) {
			linkLast(e);
		} else {
			// 找到索引指定的节点，然后将新节点插入该节点后面
			final Node<E> p = node(index);
			final Node<E> newNode = new Node<>(p, e, p.next);
			p.next = newNode;
			size++;
		}
		
		return true;
	}
	
	/* 
	 * @see per.zyf.bds.List#get(int)
	 */
	@Override
	public E get(int index) {
		return node(index).item;
	}
	
	
	/* (non-Javadoc)
	 * @see per.zyf.bds.List#remove(int)
	 */
	@Override
	public E remove(int index) {
		rangeCheck(index);
		
		Node<E> p = node(index);
		E e = p.item;
		
		// 所需删除节点的前面有节点，则改变前一节点的下一跳
		if(p.prev != null)
			p.prev.next = p.next;
		// 所需删除节点的后面有节点，则改变后一节点的上一跳
		if(p.next != null)
			p.next.prev = p.prev;

		// 清空数据
		p.prev = null;
		p.item = null;
		p.next = null;
		
		size--;
		
		return e;
	}
	
	/** 
	* @Description: 在链表头部增加节点
	* @param e    新节点数据 
	* @return void    返回类型 
	*/
	public void addFirst(E e) {
		final Node<E> newNode = new Node<>(null, e, first);
		first.prev = newNode;
		size++;
	}
	
	/** 
	* @Description: 移除首节点 
	* @return E    所删除节点的数据 
	*/
	public E removeFirst() {
		if(first != null) {
			final E e = first.item;
			final Node<E> n = first.next;
			
			if(n != null) 
				n.prev = null;
				
			// 清空数据，让GC来回收内存
			first.item = null;
			first.next = null;
			// 指定新的头节点
			first = n;
			
			size--;
			return e;
		} else
			return null;
	}
	
	/** 
	* @Description: 删除最后一个节点 
	* @return    设定文件 
	* @return E    所删除数据 
	* @throws 
	*/
	public E removeLast() {
		if(last != null) {
			final E e = last.item;
			final Node<E> p = last.prev;
			
			if(p != null)
				p.next = null;
			
			// 清空数据，让GC来回收内存
			last.item = null;
			last.prev = null;
			// 指定新的尾节点
			last = p;
			
			size--;
			return e;
		} else
			return null;
	}
	
	/* (non-Javadoc)
	 * @see per.zyf.bds.List#size()
	 */
	@Override
	public int size() {
		return size;
	}
	
	/* (non-Javadoc)
	 * @see per.zyf.bds.List#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return true;
	}
	
	/** 
	* @Description: 在链尾增加节点 
	* @param e    新节点数据
	* @return void    返回类型 
	*/
	private void linkLast(E e) {
        // 保存旧链尾节点
		final Node<E> p = last;
        
        // 创建新节点，并将新节点设置为链尾节点
        final Node<E> newNode = new Node<>(p, e, null);
        last = newNode;
        
        // 若链表无节点，则将新节点设置为表头节点
        if (p == null)
            first = newNode;
        else // 若链表已经有节点，则将新节点设置为表尾节点
            p.next = newNode;
		
		// 表长度加一
		size++;
    }
	
	/***
	 * 
	* @Description: 索引范围检查 
	* @param index    索引
	* @return void 
	 */
	private void rangeCheck(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }
	
	/** 
	* @Description: 获取索引位置的节点 
	* @param index    索引
	* @return Node<E>    指定的节点 
	*/
	private Node<E> node(int index) {
		// 判断索引是否在合理的位置
		rangeCheck(index);
		
		Node<E> specifyNode;
		
		// 根据判断索引位置在链表的前半部还是后半部，选择不同的检索方式获取指定节点
		if(index < (size >> 1)) {
			specifyNode = first;
			for (int i = 0; i < index; i++) 
				specifyNode = specifyNode.next;
		} else {
			specifyNode = last;
			for (int i = 0; i > index; i--)
				specifyNode = specifyNode.prev;
		}
		
		return specifyNode;
	} 
	
	// 链表节点
	private static class Node<E> {
		E item;
		Node<E> next;
		Node<E> prev;
		
		Node(Node<E> prev, E e, Node<E> next) {
			this.prev = prev;
			this.item = e;
			this.next = next;
		}
	}
	
}
