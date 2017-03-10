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
	
	public E get(int index);
	
	public E remove(int index);
	
	public boolean remove(Object o);
	
	public E set(int index, E element);
	
	public int size();
	
	public Object[] toArray();
	
	public Iterator<E> iterator();

}
