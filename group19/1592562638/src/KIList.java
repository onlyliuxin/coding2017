/*范例名称：
 * 原文件名称：
 * 要点：
 * 1. 顺序表接口:基本的增、删、改、查功能

 */
public interface KIList<T> {
	public void add(T item);
	public void add(int index, T item);
	
	public T remove(int index);//返回值是删除的元素
	
	public void set(int index, T item);
	
	public T get(int index);
	public int size();

}
