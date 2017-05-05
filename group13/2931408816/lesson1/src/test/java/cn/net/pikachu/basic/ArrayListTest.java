package cn.net.pikachu.basic;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by pikachu on 17-2-20.
 */
public class ArrayListTest {
    ArrayList list;
    @Before
    public void before(){
        list=new ArrayList();
    }
    @Test
    public void testAdd(){
        list.add(1);
        Assert.assertEquals("[1]",list.toString());
        Assert.assertEquals(1,list.size());
        list.add(0,0);
        Assert.assertEquals("[0,1]",list.toString());
        Assert.assertEquals(2,list.size());
        list.add(1,2);
        Assert.assertEquals("[0,2,1]",list.toString());
        Assert.assertEquals(3,list.size());

    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemove(){
        list.add(1);
        list.remove(0);
        Assert.assertEquals("[]",list.toString());
        Assert.assertEquals(0,list.size());
        list.add(2);
        list.add(3);
        list.add(4);
        Object o =list.remove(1);
        Assert.assertEquals(3,o);
        Assert.assertEquals("[2,4]",list.toString());
        Assert.assertEquals(2,list.size());
        list.remove(4);
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGet(){
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        Assert.assertEquals(1,list.get(0));
        Assert.assertEquals(4,list.get(3));
        Assert.assertEquals(4,list.size());
        list.get(4);
    }
    @Test
    public void testSize(){
        Assert.assertEquals(0,list.size());
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        Assert.assertEquals(4,list.size());
    }
    @Test
    public void testIterator(){
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        Iterator iterator = list.iterator();
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        while (iterator.hasNext()){
            builder.append(iterator.next()).append(",");
        }
        builder.deleteCharAt(builder.length()-1);
        builder.append("]");
        Assert.assertEquals(builder.toString(),list.toString());
    }
    /*
    // 抛出异常的测试
    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveWithException(){
        list.remove(2);
    }
    // 超时测试
    @Test(timeout = 2000)
    public void test(){
        while (true);
    }
    */
}
