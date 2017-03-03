import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

public class LinkedListTest {

    @Rule
    public final SystemOutRule log = new SystemOutRule().enableLog();

    @Test
    public void testAdd() {
        log.clearLog();
        LinkedList list = initListWithSize(10);
        System.out.print(list);
        Assert.assertEquals("List: [ 0 1 2 3 4 5 6 7 8 9 ]", log.getLog());
        System.out.println();
    }

    @Test
    public void testAddWithIndex() {
        log.clearLog();
        LinkedList list = initListWithSize(10);
        list.add(0, -1);
        System.out.print(list);
        Assert.assertEquals(11, list.size());
        Assert.assertEquals("List: [ -1 0 1 2 3 4 5 6 7 8 9 ]", log.getLog());
        System.out.println();

        log.clearLog();
        list.add(list.size()-1, 10);
        System.out.print(list);
        Assert.assertEquals("List: [ -1 0 1 2 3 4 5 6 7 8 10 9 ]", log.getLog());
        System.out.println();

        log.clearLog();
        list.add(list.size(), 11);
        System.out.print(list);
        Assert.assertEquals("List: [ -1 0 1 2 3 4 5 6 7 8 10 9 11 ]", log.getLog());
        System.out.println();
    }

    @Test
    public void testRemove() {
        log.clearLog();
        LinkedList list = initListWithSize(10);
        list.remove(0);
        System.out.print(list);
        Assert.assertEquals(9, list.size());
        Assert.assertEquals("List: [ 1 2 3 4 5 6 7 8 9 ]", log.getLog());
        System.out.println();

        log.clearLog();
        list.remove(list.size()-1);
        System.out.print(list);
        Assert.assertEquals(8, list.size());
        Assert.assertEquals("List: [ 1 2 3 4 5 6 7 8 ]", log.getLog());
        System.out.println();

        log.clearLog();
        list.remove(list.size()-2);
        System.out.print(list);
        Assert.assertEquals(7, list.size());
        Assert.assertEquals("List: [ 1 2 3 4 5 6 8 ]", log.getLog());
        System.out.println();
    }

    @Test
    public void testGet() {
        log.clearLog();
        LinkedList list = initListWithSize(10);
        for (int i = 0; i < list.size(); ++i) {
            System.out.print(list.get(i));
        }
        Assert.assertEquals("0123456789", log.getLog());
        System.out.println();
    }

    @Test
    public void testSize() {
        log.clearLog();
        LinkedList list = initListWithSize(10);
        Assert.assertEquals(10, list.size());
        System.out.println();
    }

    @Test
    public void testIterator() {
        log.clearLog();
        LinkedList list = initListWithSize(10);
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            System.out.print(iter.next());
        }
        Assert.assertEquals("0123456789", log.getLog());
        System.out.println();
    }

    private LinkedList initListWithSize(int size) {
        LinkedList list = new LinkedList();
        for (int i = 0; i < size; ++i) {
            list.add(i);
        }
        return list;
    }
}
