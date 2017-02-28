/**   
* @Title: Stack.java 
* @Description: 栈的实现 
* @author glorychou
* @date 2017年2月24日 下午3:05:29 
*/
package per.zyf.bds;

/**
 * @author glorychou
 *
 */
public class Stack<E> {
	// 栈元素
	private ArrayList<E> elementData;
	
	// 栈大小
	private int size;
	
	/**
	 * 初始化栈
	 */
	public Stack() {
		elementData = new ArrayList<>();
	}
	
	/** 
	* @Description: 压栈
	* @param e    数据 
	* @return void    返回类型 
	*/
	public void push(E e) {
		elementData.add(e);
		size = elementData.size();
	}
	
	/** 
	* @Description: 弹栈 
	* @return E    所弹出的数据 
	*/
	public E pop() {
		// 移除最后一项数据
		final E e =  elementData.remove(elementData.size());
		size = elementData.size();
		return e;
	}
	
	/** 
	* @Description: 获取栈顶元素 
	* @return E    返回栈顶元素数据 
	*/
	public E peek() {
		return elementData.get(size - 1);
	}
	
	/** 
	* @Description: 判断栈是否为空 
	* @return boolean    返回类型 
	*/
	public boolean isEmpty() {
		return size > 0 ? false : true;
	}
	
	/** 
	* @Description: 获取栈大小 
	* @return int    栈大小 
	*/
	public int size() {
		return size;
	}
}
