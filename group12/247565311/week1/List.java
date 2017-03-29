package week1;

public interface List<E> extends Iterator{
	public void add(Object o);
	public void add(int index, E o);
	public E get(int index);
	public E remove(int index);
	public int size();
}
