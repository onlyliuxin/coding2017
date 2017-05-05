package week1.com.coding.Test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import week1.com.coding.basic.ArrayList;
import week1.com.coding.basic.Iterator;

public class ArrayListTest
{
    private ArrayList list = null;
    
    @Before
    public void before()
    {
        list = new ArrayList();
        list.add("a");
        list.add("b");
        list.add("c");
    }
    
    public void testAdd()
    {
        list.add("a");
        list.add("b");
        list.add("c");
    }
    
    public void testRemove()
    {
        Assert.assertEquals("c", list.remove(2));
    }
    
    public void testSize()
    {
        Assert.assertEquals(3, list.size());
    }
    
    public void testGet()
    {
        Assert.assertEquals("c", list.get(2));
    }
    
    public void testInsert()
    {
        list.add(2, "e");
        for (int i = 0; i < list.size(); i++)
        {
            System.out.printf("%s\t", list.get(i));
        }
    }
    
    @Test
    public void testIterator()
    {
        Iterator iterator = list.iterator();
        while (iterator.hasNext())
        {
            System.out.println(iterator.next());
        }
    }
    
}
