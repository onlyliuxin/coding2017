package ListService;

//集合接口
public interface KIList<T> {

	public void add(T item);

	public void add(int index, T item);

	public void set(int index, T item);

	public void remove(int index);

	public void remove(T item);

	public void clear();

	public boolean contains(T item);

	public boolean isEmpty();

	public T get(int index);

	public int indexOf(T item);

	public int size();

}
