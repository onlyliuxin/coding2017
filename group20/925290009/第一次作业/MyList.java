package BasicData;

/**
 * 创建自己的List接口
 * @author Ralf
 *
 */
public interface MyList<T> {

	public abstract boolean add(T t);
	public abstract void add(int index, T t);
	public abstract int size();
	public abstract T remove(int index);
	public abstract boolean set(int index, T t);
	public abstract T get(int index);
}
