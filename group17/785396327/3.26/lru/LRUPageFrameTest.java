package lru;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by william on 2017/3/31.
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
    }

    @Test
    public void testInnerMethod() {
//        lru.LRUPageFrame lruPageFrame = new lru.LRUPageFrame(3);
//        lruPageFrame.access(1);
//        lruPageFrame.access(2);
//        lruPageFrame.access(3);
//        lruPageFrame.access(4);
//        System.out.println(lruPageFrame);
//        System.out.println(lruPageFrame.last);
//        lruPageFrame.removeLast();
//        System.out.println(lruPageFrame);
//        lruPageFrame.addFirst(0);
//        System.out.println(lruPageFrame);
//        lruPageFrame.remove(2);
//        System.out.println(lruPageFrame);
    }
}
