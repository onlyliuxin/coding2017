package week1.com.coding.basic;


/**
 * ���е��������Ƚ��ȳ�������һ�л𳵽���һ��������Ƚ��ĳ���϶��ȳ���<br/>
 * ������������л�������������1����ͷ����ɾ�� 2����β������� 3�����о���һ�������� 4�������г���
 * 
 * @author Administrator
 *
 */
public class Queue
{
    private Object[] objects = null;
    
    private int size;// ����
    
    private int head;// ��ͷ
    
    private int last;// ��β
    
    private int maxCapacity;// �������
    
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
            throw new RuntimeException("��ʼ����������С��0");
        }
    }
    
    // ���
    public void enQueue(Object o)
    {
        if (size == maxCapacity)// ��������
        {
            throw new RuntimeException("�������������������");
        }
        else
        {
            objects[last] = o;
            last++;
            size++;
        }
    }
    
    // ����
    public Object deQueue()
    {
        if (isEmpty())
        {
            throw new RuntimeException("�����ѿգ�û��ֵ�ɳ�");
        }
        Object obj = objects[head];
        objects[head] = null;// �ͷŶ�ͷԭ��ֵ
        head++;// ��ͷָ��+1
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
