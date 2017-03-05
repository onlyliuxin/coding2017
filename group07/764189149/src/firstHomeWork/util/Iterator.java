package firstHomeWork.util;

/**
 * @Description: 迭代器
 * @author: leijing
 * @date: 2017年2月21日 下午8:49:10
 * @param <E>
 */
public interface Iterator<E> {
	/**
	 * @Description: 返回迭代器中是否有下一个元素
	 * @return: boolean
	 * @author: leijing  
	 * @date: 2017年2月21日 下午8:49:52
	 */
	boolean hasNext();
	/**
	 * @Description: 返回迭代器中的下一个元素
	 * @return: E
	 * @author: leijing  
	 * @date: 2017年2月21日 下午8:50:35
	 */
	E next();
	/**
	 * @Description: 删除迭代器中的当前元素
	 * @author: leijing  
	 * @date: 2017年2月21日 下午8:51:07
	 */
	void remove();
}
