package zhuakuang.homework.first.Collection;

public interface List extends Iterable {
	public void add(Object o);
	public void add(int index, Object o);
	public Object get(int index);
	public Object remove(int index);
	public int size();
	Iterator iterator();
}
