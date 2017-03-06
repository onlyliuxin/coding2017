package cc.gzhlaker;

public interface List{
	public void add(int data);
	public void add(int data,int index);
	public void addFirst(int data);
	public void remove(int index);
	public void removeFirst();
	public void removeEnd();
	public void setData(int data,int index);
	public int getData(int index);
}
