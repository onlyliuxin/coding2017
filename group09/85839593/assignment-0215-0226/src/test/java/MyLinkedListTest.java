import org.junit.*;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: guohairui
 * Date: 17-2-26
 * Time: 下午6:12
 * To change this template use File | Settings | File Templates.
 */
public class MyLinkedListTest {
    @org.junit.Test
    public void testAdd() throws Exception {
              MyLinkedList linkedList = new MyLinkedList();
        linkedList.add("abc1");
        linkedList.add("abc2");
        linkedList.add("abc3");
        linkedList.add("abc4");
        System.out.println(linkedList.get(1));
        System.out.println(linkedList);
        linkedList.add("abc5");
        System.out.println(linkedList.get(3));
        System.out.println(linkedList.get(4));
        System.out.println(linkedList);
        linkedList.add(2,"abcaddtmp");
        System.out.println(linkedList.get(3));
        System.out.println(linkedList.get(4));
        System.out.println(linkedList);
        linkedList.remove(2);
        System.out.println(linkedList.toString());
        linkedList.removeLast();
        System.out.println(linkedList.toString());
        linkedList.removeFirst();
        System.out.println(linkedList.toString());
    }
    @Test
    public void testGet() throws Exception {

    }

    @Test
    public void testRemove() throws Exception {

    }

    @Test
    public void testAddFirst() throws Exception {

    }

    @Test
    public void testRemoveFirst() throws Exception {

    }

    @Test
    public void testRemoveLast() throws Exception {

    }

    @Test
    public void testSize() throws Exception {

    }

    @Test
    public void testGetNode() throws Exception {

    }
}
