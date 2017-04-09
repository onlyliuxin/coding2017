/**   
* @Title: Queue.java 
* @Description: 队列的实现 
* @author glorychou
* @date 2017年2月24日 下午3:10:08 
*/
package per.zyf.bds;

/**
 * @author glorychou
 *
 */
public class Queue<E> {
	// 队列元素
	private LinkedList<E> elementData;

	// 队列大小
	private int size;
	
	public Queue() {
		elementData = new LinkedList<>();
	}
	
	/** 
	* @Description: 入队 
	* @param e    入队数据 
	* @return void    返回类型 
	*/
	public void enQueue(E e) {
		elementData.add(e);
		size = elementData.size();
	}
	
	/** 
	* @Description: 出队 
	* @return E    出队的数据 
	*/
	public E deQueue() {
		final E e = elementData.removeFirst();
		size = elementData.size();
		return e;
	}
	
	/** 
	* @Description: 判断队列是否为空 
	* @return boolean    是否为空 
	*/
	public boolean isEmpty() {
		return size > 0 ? false : true;
	}
	
	/** 
	* @Description: 获取队列大小 
	* @return int    队列大小
	*/
	public int size() {
		return size;
	}
}
