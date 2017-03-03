package ListService;

//集合接口
public interface KIQueueList<T> {
	public T add(T ele);

	public T remove();

	public Object[] getData();

}
