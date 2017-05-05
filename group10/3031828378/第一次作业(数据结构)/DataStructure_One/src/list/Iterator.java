package list;

public interface Iterator {
	/**
	 * 是否有第一个值
	 * @return
	 */
	boolean hasNext();
	
	/**
	 * 获取下一个值
	 * @return
	 */
	Object next();
	
	/**
	 * 删除
	 */
	void remove();
}
