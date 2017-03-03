package cn.xl;

/**
 * Queue一个先进先出（first in first out，FIFO）得队列
 * 所谓的队列，也是一个含有至少两个基本操作的抽象数据类型：插入新的元素；删除最久时间插入的元素。
 * 遵循FIFO（First in，first out，先进先出）的原则。
 * MyQueue采用环形数组实现
 * @author XIAOLONG
 *
 */
public class MyQueue<E> {

	private int size,head,tail;

	private Object[] elementData;

	private final int initialCapacity = 4; 

	public MyQueue(){

		head = tail = -1;
		elementData  =  new Object[initialCapacity];  

	}

	/**
	 * 向队列中添加元素
	 * @param o
	 */
	public void enQueue(Object o){

		ensureCapacity();

		if( head == -1) {
			tail = head = 0;
		} 
		size ++;
		elementData[tail] = o;
		tail ++;


	}

	/**
	 * 删除栈顶元素，并返回旧栈顶元素
	 * @return  旧栈顶元素
	 */
	public Object deQueue(){
		Object element = elementData[head];
		if(head == tail){
			head = tail = -1;
		}else if(head == elementData.length-1){
			head = 0;
		}else{
			head ++;
		}
		size --;
		return element;  
	}

	/**
	 * 判断队列是否为空
	 * @return
	 */
	public boolean isEmpty(){
		return head  == -1;
	}

	/**
	 * 返回自身长度
	 * @return
	 */
	public int size(){
		return size;
	}

	/**
	 * 判断队列是否已满
	 * @return
	 */
	public boolean isFull() {  
		return (head == 0 && tail == elementData.length);  
	}

	/**
	 * 扩展容量，如果队列有效数据已经占满空间则增加2，否则覆盖无效数据，重新分配数据空间
	 * @param 当前队列所需最小容量size
	 */
	private void ensureCapacity(){

		if(isFull()){
			Object [] oldData = elementData;  
			elementData = new Object[elementData.length + 2]; 
			System.arraycopy(oldData, head,elementData , 0, oldData.length);
		}else if(head > 0){
			Object [] oldData = elementData;  
			System.arraycopy(oldData, head,elementData , 0, oldData.length-head);
			tail = tail - head ;
			head = 0;
		}
	}


	
	public static void main(String[] args)  
	{  
	

	}  
}
