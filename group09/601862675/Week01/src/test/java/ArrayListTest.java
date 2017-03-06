import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

public class ArrayListTest {

    @Rule
    public final SystemOutRule log = new SystemOutRule().enableLog();

    @Test
    public void testAddWithIndex() {
        log.clearLog();
        ArrayList list = initListWithSize(10);
        list.add(3, 10);
        System.out.print(list.toString());
        Assert.assertEquals("List: [ 0 1 2 10 3 4 5 6 7 8 9 ]", log.getLog());
        System.out.println();

        log.clearLog();
        list.add(list.size(), 11);
        System.out.print(list.toString());
        Assert.assertEquals("List: [ 0 1 2 10 3 4 5 6 7 8 9 11 ]", log.getLog());
        System.out.println();
    }

    @Test
    public void testAdd() {
        log.clearLog();
        ArrayList list = new ArrayList();
        list.add(10);
        System.out.print(list.toString());
        Assert.assertEquals("List: [ 10 ]", log.getLog());
        System.out.println();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddWithIndexOutOfBoundsException() {
        ArrayList list = initListWithSize(ArrayList.MAX_LIST_SIZE);
        Assert.assertEquals(48, list.size());
        list.add(1);
    }

    @Test
    public void testRemove() {
        ArrayList list = initListWithSize(10);

        log.clearLog();
        Object removed = list.remove(0);
        System.out.print(list);
        Assert.assertEquals(0, removed);
        Assert.assertEquals(9, list.size());
        Assert.assertEquals("List: [ 1 2 3 4 5 6 7 8 9 ]", log.getLog());
        System.out.println();

        log.clearLog();
        removed = list.remove(list.size()-1);
        System.out.print(list);
        Assert.assertEquals(9, removed);
        Assert.assertEquals(8, list.size());
        Assert.assertEquals("List: [ 1 2 3 4 5 6 7 8 ]", log.getLog());
        System.out.println();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetWithIllegalArgumentException() {
        ArrayList list = new ArrayList();
        list.add(1);
        Assert.assertEquals(1, list.size());
        list.get(list.size());
    }

    @Test
    public void testSize() {
        ArrayList list = new ArrayList();
        list.add(10);
        list.add(20);
        list.add(30);
        Assert.assertEquals(3, list.size());
    }

    @Test
    public void testIterator() {
        log.clearLog();
        ArrayList list = new ArrayList();
        for (int i = 0; i < 10; ++i) {
            list.add(i);
        }
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            System.out.print(iter.next());
        }
        Assert.assertEquals("0123456789", log.getLog());
        System.out.println();
    }

    private ArrayList initListWithSize(int size) {
        ArrayList list = new ArrayList();
        for (int i = 0; i < size; ++i) {
            list.add(i);
        }
        return list;
    }
}
