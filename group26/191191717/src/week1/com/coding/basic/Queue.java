package week1.com.coding.basic;


/**
 * 队列的特征，先进先出，就像一列火车进入一节随道，先进的车箱肯定先出。<br/>
 * 抽像分析，队列还有如下特征：1、队头允许删除 2、队尾允许插入 3、队列具有一定的容量 4、队列有长度
 * 
 * @author Administrator
 *
 */
public class Queue
{
    private Object[] objects = null;
    
    private int size;// 长度
    
    private int head;// 队头
    
    private int last;// 队尾
    
    private int maxCapacity;// 最大容量
    
    public Queue()
    {
        this(10);
    }
    
    public Queue(int initSize)
    {
        if (initSize > 0)
        {
            maxCapacity = initSize;
            objects = new Object[initSize];
            head = last = 0;
        }
        else
        {
            throw new RuntimeException("初始化容量不能小于0");
        }
    }
    
    // 入队
    public void enQueue(Object o)
    {
        if (size == maxCapacity)// 队列已满
        {
            throw new RuntimeException("队列已满，不允许入队");
        }
        else
        {
            objects[last] = o;
            last++;
            size++;
        }
    }
    
    // 出队
    public Object deQueue()
    {
        if (isEmpty())
        {
            throw new RuntimeException("队列已空，没有值可出");
        }
        Object obj = objects[head];
        objects[head] = null;// 释放队头原有值
        head++;// 队头指针+1
        size--;
        return obj;
    }
    
    public boolean isEmpty()
    {
        return size == 0;
    }
    
    public int size()
    {
        return size;
    }
}
