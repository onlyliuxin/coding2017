package list;


public interface List{
	
	public abstract void add(int paramInt, Object paramE);

	public abstract boolean add(Object paramE);

	public abstract void clear();

	public abstract boolean contains(Object paramObject);

	public abstract boolean equals(Object paramObject);

	public abstract Object get(int paramInt);

	public abstract int indexOf(Object paramObject);

	public abstract boolean isEmpty();

	public abstract Iterator iterator();

	public abstract Object remove(int paramInt);

	public abstract boolean remove(Object paramObject);

	public abstract Object set(int paramInt, Object paramE);

	public abstract int size();

}
