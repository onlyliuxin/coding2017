/*范例名称：
 * 原文件名称：
 * 要点：
 * 1. 实现基本的数据结构类：Queue

 */
public class Queue_self<T> {
	private LinkedList_self<T> data;
	private int size;
	
	//初始化队列
	public Queue_self(){
		data=new LinkedList_self<T>();
	}
	
	//入队列
	public void enQueue(Object item){
		data.add((T)item);
		size++;
	}
	
	//出队列
	public T deQueue(){
		size--;
		return data.remove(0);
	}
	
	//是否为空队列
	public boolean isEmpty(){
		return (size==0);
	}
	
	//队列长度
	public int size(){
		return size;
	}
}
