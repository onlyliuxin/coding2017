package 基本数据结构;

public interface Iterator<E> {
	public boolean hasNext();
	public E next();
	public void remove();
}
