
public interface MyList {
	//增加指定元素
	public void add(Object o);
	//在指定位置上增加元素
	public void add(int index,Object o);
	//获取指定元素
	public Object get(int index);
	//删除指定位置的元素并返回
	public Object remove(int index);
}
