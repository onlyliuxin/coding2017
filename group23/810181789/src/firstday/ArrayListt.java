package firstday;

import java.util.Arrays;

public class ArrayListt {
    private Object arr[]=new Object[10];
    private int pos=0;
    public boolean add(Object o)
    {
    	if(pos<arr.length)
    	{
    		arr[pos]=o;
    		pos++;
    		return true;
    	}
    	else if(pos>=arr.length)
    	{
    		arr=Arrays.copyOf(arr, arr.length+1);
    		arr[pos] = o;
    		pos++;
    		return true;
    	}
    	return false;
    }
    public boolean add(Object o, int index)
    {
    	if(pos<arr.length)
    	{
    		for(int i=index;i<arr.length;i++)
    		{
    			arr[i+1]=arr[i];
    		}
    		arr[index] = o;
    		return true;
    	}
    	else if(pos>=arr.length)
    	{
    		arr=Arrays.copyOf(arr, arr.length+1);
    		for(int i=arr.length-2;i>=index;i--)
    		{
    			arr[i+1] = arr[i];
    		}
    		arr[index] = o;
    		return true; 
    	}
    	return false;
    }
    public Object get(int i)
    {
    	Object o = arr[i];
    	return o;
    }
    public Object remove(int index)
    {
    	Object var=arr[index];
    	for(int i=index+1;i<arr.length;i++)
    	{
    		arr[i-1]=arr[i];
    	}
    	pos--;
    	return var;
    }
    public int size()
    {
    	return pos;
    }
    public static void main(String[] args)
    {
    	ArrayListt atest = new ArrayListt();
    	for(int i=0;i<10;i++)
    	{
    		atest.add(i);
    	}
    	atest.add(99,7);
    	for(int i=0;i<atest.arr.length;i++)
    	{
    		System.out.println(atest.get(i));
    	}
    	atest.remove(1);
    	for(int i=0;i<atest.arr.length;i++)
    	{
    		System.out.println(atest.get(i));
    	}
    	atest.size();
    }
}
