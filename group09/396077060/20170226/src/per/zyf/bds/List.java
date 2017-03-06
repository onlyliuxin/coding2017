/**   
* @Title: List.java 
* @Description: List接口的实现 
* @author glorychou
* @date 2017年2月24日 下午3:02:34 
*/
package per.zyf.bds;

/**
 * @author glorychou
 *
 */
public interface List<E> {
	/** 
	* @Description: 添加元素 
	* @param e    所需增加元素
	* @return boolean    处理结果 
	*/
	boolean add(E e);
	
	/** 
	* @Description: 在索引指定位置增加元素 
	* @param index    索引
	* @param e    所需增加元素
	* @return boolean    处理结果
	*/
	boolean add(int index, E e);
	
	/** 
	* @Description: 获取指定元素 
	* @param index    索引
	* @return E    返回元素 
	*/
	E get(int index);

	/** 
	* @Description: 删除指定元素 
	* @param index    索引
	* @return E    返回被删除的元素 
	*/
	E remove(int index);
	
	/** 
	* @Description: 获取List容量 
	* @return int   List容量
	*/
	int size();
	
	/** 
	* @Description: 判断是否为空 
	* @return boolean    是否为空 
	*/
	boolean isEmpty();
	
}
