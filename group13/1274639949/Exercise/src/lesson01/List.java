package lesson01;

public interface List<E> {
	
	/**
	 * 在List的最后一个元素后添加一个元素
	 * @param e  添加的元素
	 */
	public boolean add(E e);
	
	/**
	 * 在List的index位置加入元素element
	 * @param index
	 * @param element
	 */
	public void add(int index, E element);
	
	/**
	 * 清空该List
	 */
	public void clear();
	
	/**
	 * 判断List中是否含有对象o
	 * @param o
	 * @return 如果o存在于该List中则返回true， 否则返回false
	 */
	public boolean contains(Object o);
	
	/**
	 * 如果o存在于该List中，则返回它第一次出现的位置
	 * 第一个元素的位置为0
	 * 不存在时返回-1
	 * @param o
	 * @return 返回o在List中的位置或-1
	 */
	public int indexOf(Object o);
	
	/**
	 * 获取指定位置上的元素
	 * @param index
	 * @return List中下标为index的元素
	 */
	public E get(int index);
	
	/**
	 * 移除指定位置上的元素
	 * @param index
	 * @return 被移除的元素。
	 */
	public E remove(int index);
	
	/**
	 * 在List中移除指定的元素，如果存在多个，则只移除下标最小的那个
	 * @param o
	 * @return 若指定元素被移除则返回true，否则返回false
	 */
	public boolean remove(Object o);
	
	/**
	 * 使用指定的元素替换指定位置上的元素。
	 * @param index
	 * @param element
	 * @return 被替换掉的元素
	 */
	public E set(int index, E element);
	
	/**
	 * 返回List中元素的个数
	 * @return
	 */
	public int size();
	
	/**
	 * 将List中的全部元素存放于一个数组中并返回该数组
	 * @return
	 */
	public Object[] toArray();
	
	/**
	 * 返回该List上的迭代器
	 * @return
	 */
	public Iterator<E> iterator();
}
