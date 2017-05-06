package structure.week1;

public interface List<E> extends Iterator{
	public void add(Object o);
	public E get(int index);
	public E remove(int index);
	public int size();
	void add(int arg0, E arg1);
}
