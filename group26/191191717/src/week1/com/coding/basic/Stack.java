package week1.com.coding.basic;


/**
 * ջ���������Ƚ����
 * 
 * @author Administrator
 *
 */
public class Stack
{
    private ArrayList elementData = new ArrayList();
    
    int size;
    
    /** ÿ���ƽ�����������ƽ� **/
    public void push(Object o)
    {
        if (o == null)
        {
            throw new RuntimeException("�ƽ�Ԫ�ز���Ϊ��");
        }
        elementData.add(o);
        size++;
    }
    
    public Object pop()
    {
        // ÿ�δ���󵯳�
        if (size ==0)
        {
            throw new RuntimeException("��ջ��û�пɵ���Ԫ��");
        }
        Object object = elementData.get(--size);
        return object;
    }
    
    public Object peek()
    {
        return null;
    }
    
    public boolean isEmpty()
    {
        return elementData.size() == 0;
    }
    
    public int size()
    {
        return elementData.size();
    }
}
