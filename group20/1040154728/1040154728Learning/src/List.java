public interface List<T> {
	public void add(T object);
	public void add(int index, T object);
	public T get(int index);
	public T remove(int index);
	public int size();
	boolean isEmpty();
}
