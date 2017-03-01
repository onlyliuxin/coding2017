package 基本数据结构;

import java.util.LinkedList;

public class Queue
{

    private LinkedList list = new LinkedList();

    public void enQueue(Object o)
    {
        list.addLast(o);
    }

    public Object deQueue()
    {
        return list.removeFirst();
    }

    public boolean isEmpty()
    {
        if(list.size() != 0)
            return true;
        else
            return false;
    }

    public int size()
    {
        return list.size();
    }
}
