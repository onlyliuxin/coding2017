/*�������ƣ�
 * ԭ�ļ����ƣ�
 * Ҫ�㣺
 * 1. ˳���ӿ�:����������ɾ���ġ��鹦��

 */
public interface KIList<T> {
	public void add(T item);
	public void add(int index, T item);
	
	public T remove(int index);//����ֵ��ɾ����Ԫ��
	
	public void set(int index, T item);
	
	public T get(int index);
	public int size();

}
