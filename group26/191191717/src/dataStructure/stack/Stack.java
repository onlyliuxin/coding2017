package dataStructure.stack;

import week1.com.coding.basic.ArrayList;

/**
 * 栈的特征，先进后出
 * 
 * @author Administrator
 *
 */
public class Stack<T>
{
    private ArrayList elementData = new ArrayList();
    
    private int size = 0;// 计数器
    
    public void push(Object o)
    {
        if (o == null)
            throw new RuntimeException("push elementDate is null!");
        
        elementData.add(o);
        size++;
    }
    
    public Object pop()
    {
        if (size() == 0)
            throw new RuntimeException(" stack is null ,not elementData pop!");
        
        return elementData.get(--size);
    }
    
    public Object peek()
    {
        if (size() == 0)
            throw new RuntimeException(" stack is null ,not elementData!");
        
        return elementData.get(size - 1);
    }
    
    public boolean isEmpty()
    {
        return this.size == 0 ? true : false;
    }
    
    public int size()
    {
        return size;
    }
    
    @Override
    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        Stack<Integer> newStack=new Stack<Integer>();
        while (!this.isEmpty())
        {
            newStack.push(pop());
          
        }
        while(!newStack.isEmpty())
        {
            if (newStack.size !=1)
            {
                sb.append(newStack.pop() + ", ");
            }
            else
            {
                sb.append(newStack.pop() + "]");
            }
        }
        return sb.toString();
    }
    
    public static void main(String[] args)
    {
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        System.out.println(stack.toString());
    }
}
