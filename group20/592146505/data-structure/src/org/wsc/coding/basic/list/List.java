package org.wsc.coding.basic.list;

/**
 * List接口
 *
 * @author Administrator
 * @date 2017年2月20日下午9:15:32
 * @version v1.0
 *
 * @param <T>
 */
public interface List<E> {

	/**
	 * 获取集合元素个数
	 * 
	 * @return
	 */
	int size();

	/**
	 * 集合是否为空
	 * 
	 * @return
	 */
	boolean isEmpty();

	// /**
	// * 是否包含指定元素
	// * @param o
	// * @return
	// */
	// boolean contains(Object o);

	/**
	 * 获取当前集合迭代器对象
	 * 
	 * @return
	 */
	Iterator<E> iterator();

	/**
	 * 返回集合数组对象
	 *
	 * @return
	 */
	Object[] toArray();

	/**
	 * 将集合元素复制到新数组中
	 * 
	 * @param a
	 * @return
	 */
	<T> T[] toArray(T[] a);

	/**
	 * 在集合末尾追加元素
	 * 
	 * @param e
	 * @return
	 */
	boolean add(E e);

	/**
	 * 将元素添加至指定指定索引处
	 * 
	 * @param index
	 * @param e
	 * @return
	 */
	boolean add(int index, E e);

	/**
	 * 获取指定索引处元素
	 * 
	 * @param index
	 * @return
	 */
	E get(int index);

	/**
	 * 替换指定索引处元素为新元素，并返回被替换元素，
	 * 
	 * @param index
	 * @param e
	 * @return
	 */
	E set(int index, E e);

	/**
	 * 删除并返回指定指定索引处元素
	 * 
	 * @param index
	 * @return
	 */
	E remove(int index);

	// /**
	// * 返回该对象在集合中的下标，不存在返回-1
	// * @param o
	// * @return
	// */
	// int indexOf(Object o);

}