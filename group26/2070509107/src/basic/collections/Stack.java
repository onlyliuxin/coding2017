package collections;

/**
 * 
 * @author wuhao
 * @date:2017-03-11
 * @description:栈的实现，栈是先进后出；
 * @version:1.0.0
 *
 */
public class Stack {

	private ArrayList elementData = new ArrayList();

	/**
	 * 存放元素
	 * 
	 * @param o
	 */
	public void push(Object o) {
		elementData.add(o);
	}

	/**
	 * 弹出元素，从最末尾弹出
	 * 
	 * @return
	 */
	public Object pop() {
		if (elementData.size() == 0) {
			return null;
		}
		return elementData.remove(elementData.size() - 1);
	}

	/**
	 * 获取栈定元素
	 * 
	 * @return
	 */
	public Object peek() {
		if (elementData.size() == 0) {
			return null;
		}
		return elementData.get(elementData.size());
	}

	/**
	 * 判断是否为空
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		if (0 == elementData.size()) {
			return false;
		}
		return true;
	}

	/**
	 * 栈大小
	 * 
	 * @return
	 */
	public int size() {
		return elementData.size();
	}

}
