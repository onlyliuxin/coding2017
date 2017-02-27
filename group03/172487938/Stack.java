package 基本数据结构;

import java.util.ArrayList;

public class Stack
{
    private ArrayList elementData = new ArrayList();

    public void push(Object o)
    {
        elementData.add(o);
    }

    public Object pop()
    {
        Object popElement = elementData.remove(elementData.size() - 1);
        return popElement;
    }

    public Object peek()
    {
        Object peekElement = elementData.get(size() - 1);
        return peekElement;
    }

    public boolean isEmpty()
    {
        if(elementData.size() != 0)
            return false;
        return true;
    }

    public int size()
    {
        return elementData.size();
    }
}
