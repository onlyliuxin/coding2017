package dataStruct.com.coding.basic;

/**
 * Created by songbao.yang on 2017/2/21.
 *
 */
public interface List<T> {
	public void add(T o);
	public void add(int index, T o);
	public T get(int index);
	public T remove(int index);
	public int size();
	public Iterator iterator();
}
