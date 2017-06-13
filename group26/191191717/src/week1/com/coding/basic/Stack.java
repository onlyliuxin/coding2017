package week1.com.coding.basic;


/**
 * 栈的特征：先进后出
 * 
 * @author Administrator
 *
 */
public class Stack
{
    private ArrayList elementData = new ArrayList();
    
    int size;
    
    /** 每次推进都是在最后推进 **/
    public void push(Object o)
    {
        if (o == null)
        {
            throw new RuntimeException("推进元素不能为空");
        }
        elementData.add(o);
        size++;
    }
    
    public Object pop()
    {
        // 每次从最后弹出
        if (size ==0)
        {
            throw new RuntimeException("空栈，没有可弹出元素");
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
