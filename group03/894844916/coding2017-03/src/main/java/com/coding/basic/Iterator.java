/**
 * 
 */
package com.coding.basic;

/**
 * @author patchouli
 *
 */
public interface Iterator {
	/**
	 * 用来判断下一个元素是否存在。
	 * @return
	 */
	public boolean hasNext();
	
	/**
	 * 用来获取当前元素
	 * @return
	 */
	public Object next();
}
