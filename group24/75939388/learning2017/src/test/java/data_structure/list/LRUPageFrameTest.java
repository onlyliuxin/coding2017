package data_structure.list;

import basic.dataStructure.linkedList.LRUPageFrame;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author : 温友朝
 * @date : 2017/4/19
 */
public class LRUPageFrameTest {

    @Test
    public void testAccess() {
        LRUPageFrame frame = new LRUPageFrame(3);
        frame.access(7);
        frame.access(0);
        frame.access(1);
        Assert.assertEquals("1,0,7", frame.toString());
        frame.access(2);
        Assert.assertEquals("2,1,0", frame.toString());
        frame.access(0);
        Assert.assertEquals("0,2,1", frame.toString());
        frame.access(0);
        Assert.assertEquals("0,2,1", frame.toString());
        frame.access(3);
        Assert.assertEquals("3,0,2", frame.toString());
        frame.access(0);
        Assert.assertEquals("0,3,2", frame.toString());
        frame.access(4);
        Assert.assertEquals("4,0,3", frame.toString());
        frame.access(5);
        Assert.assertEquals("5,4,0", frame.toString());

    }

    @Test
    public void testSet(){
        LRUPageFrame frame = new LRUPageFrame(3);
        frame.access(7);
        frame.access(0);
        frame.access(1);
        frame.moveToFirst(1);
        System.out.println(frame.toString());
    }

    @Test
    public void testGet(){
        LRUPageFrame frame = new LRUPageFrame(3);
        frame.access(7);
        frame.access(0);
        frame.access(1);
        System.out.println(frame.get(0));
    }

    @Test
    public void testRemoveFirst(){
        LRUPageFrame frame = new LRUPageFrame(3);
        frame.access(7);
        frame.access(0);
        frame.access(1);
        frame.removeLast();
        System.out.println(frame.toString());
    }
}
