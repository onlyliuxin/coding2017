package week1.com.coding.Test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import week1.com.coding.basic.Iterator;
import week1.com.coding.basic.LinkedList;

public class LinkedListTest
{
    LinkedList list = null;
    
    @Before
    public void testAdd()
    {
        list = new LinkedList();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
    }
    
    @Test
    public void testPrintNode()
    {
        list.printNode();
    }
    
    @Test
    public void testSize()
    {
        System.out.println(list.size());
    }
    
    @Test
    public void testInsertAdd()
    {
        list.add(0, "f");
        list.printNode();// a b f c d
    }
    @Test
    public void testAddFirst()
    {
        list.addFirst("f");
        list.printNode();
        System.out.println(list.size());
    }
    @Test
    public void testAddLast()
    {
        list.addLast("f");
        list.printNode();
    }
    @Test
    public void testGet()
    {
        Assert.assertEquals("d", list.get(3));
    }
    @Test
    public void testRemoveFirst()
    {
        Assert.assertEquals("a", list.removeFirst());
        list.printNode();
    }
    @Test
    public void testRemoveLast()
    {
        System.out.println(list.removeLast());
        list.printNode();
    }
    @Test 
    public void testRemove()
    {
        Assert.assertEquals("a", list.remove(0));
    }
    
    @Test
    public void testIterator()
    {
        Iterator iter = list.iterator();
        String[] strs = {"a", "b", "c", "d"};
        int i = 0;
        while (iter.hasNext())
        {
            Assert.assertEquals(strs[i], iter.next());
            i++;
        }
    }
}
