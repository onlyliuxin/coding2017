package firstHomeWork.util;

/**
 * @Description: 定义一组操作有序列表的接口
 * @author: leijing
 * @date: 2017年2月21日 下午8:53:52
 * @param <E>
 */
public interface List<E> {
	/**
	 * @Description: 添加元素
	 * @param e
	 * @return: boolean
	 * @author: leijing  
	 * @date: 2017年2月21日 下午8:55:32
	 */
	 boolean add(E e);
	 /**
	  * @Description: 删除指定索引的元素
	  * @param index
	  * @return: E
	  * @author: leijing  
	  * @date: 2017年2月21日 下午8:56:08
	  */
	 E remove(int index);
	 /**
	  * @Description: 删除元素
	  * @param o
	  * @return: boolean
	  * @author: leijing  
	  * @date: 2017年2月21日 下午8:56:28
	  */
	 boolean remove(Object o);
	 /**
	  * @Description: 返回元素个数
	  * @return: int
	  * @author: leijing  
	  * @date: 2017年2月21日 下午8:57:25
	  */
	 int size();
	 /**
	  * @Description: 判断集合是否为空
	  * @return: boolean
	  * @author: leijing  
	  * @date: 2017年2月21日 下午8:57:51
	  */
	 boolean isEmpty();
	 /**
	  * @Description: 获取指定位置的元素
	  * @param index
	  * @return: E
	  * @author: leijing  
	  * @date: 2017年2月21日 下午8:58:27
	  */
	 E get(int index);
	 /**
	  * @Description: 设置指定位置的元素
	  * @param index
	  * @param e
	  * @return: E
	  * @author: leijing  
	  * @date: 2017年2月21日 下午8:58:58
	  */
	 E set(int index , E e);
	 /**
	  * @Description: 判断集合是否包含某个元素
	  * @param o
	  * @return: boolean
	  * @author: leijing  
	  * @date: 2017年2月21日 下午8:59:32
	  */
	 boolean contains(Object o);
	 /**
	  * @Description: 清空集合
	  * @return: void
	  * @author: leijing  
	  * @date: 2017年2月21日 下午9:00:12
	  */
	 void clear();
	 /**
	  * @Description: 获取集合的迭代器
	  * @return: Iterator<E>
	  * @author: leijing  
	  * @date: 2017年2月21日 下午9:00:47
	  */
	 Iterator<E> iterator();
}
