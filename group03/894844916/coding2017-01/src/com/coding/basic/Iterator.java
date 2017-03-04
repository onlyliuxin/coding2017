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
	 * 集合中存在下一个元素返回true，不存在下一个元素返回false。
	 * @return
	 */
	public boolean hasNext();
	
	/**
	 * 返回集合中下一个元素
	 * @return
	 */
	public Object next();
}
