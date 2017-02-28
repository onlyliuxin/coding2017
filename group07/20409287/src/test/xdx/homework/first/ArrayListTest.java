package xdx.homework.first;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * ArrayList Tester.
 *
 * @author xdx
 * @version 1.0
 * @since <pre>2月25日, 2017</pre>
 */
public class ArrayListTest {

    private List<String> defaultTestList;

    @Before
    public void before() throws Exception {

        defaultTestList = new ArrayList<>();
        Assert.assertTrue(defaultTestList.add("《三国演义》"));
        Assert.assertTrue(defaultTestList.add("《红楼梦》"));
        Assert.assertTrue(defaultTestList.add("《西游记》"));
        Assert.assertTrue(defaultTestList.add("《水浒传》"));
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: add(E e)
     */
    @Test
    public void testAdd() throws Exception {

        List<Integer> testList = new ArrayList<>();
        Assert.assertTrue(testList.add(1));
        Assert.assertTrue(testList.add(2));
        Assert.assertTrue(testList.add(3));
        System.out.println(testList.toString());
    }

    /**
     * Method: remove(int index)
     */
    @Test
    public void testRemoveIndex() throws Exception {

        List<Integer> testReomoveList = new ArrayList<>();
        Assert.assertTrue(testReomoveList.add(4));
        Assert.assertTrue(testReomoveList.add(5));
        Assert.assertTrue(testReomoveList.add(6));
        System.out.println("删除前: " + testReomoveList);
        Integer delElement = testReomoveList.get(0);
        Assert.assertTrue(testReomoveList.remove(0).equals(delElement));
        System.out.println("删除后: " + testReomoveList);
    }

    /**
     * Method: remove(E e)
     */
    @Test
    public void testRemoveE() throws Exception {

        List<Integer> testReomoveList = new ArrayList<>();
        Assert.assertTrue(testReomoveList.add(7));
        Assert.assertTrue(testReomoveList.add(8));
        Assert.assertTrue(testReomoveList.add(9));
        System.out.println("删除前: " + testReomoveList);
        Assert.assertTrue(testReomoveList.remove(new Integer(8)));
        System.out.println("删除后: " + testReomoveList);
    }

    /**
     * Method: size()
     */
    @Test
    public void testSize() throws Exception {
        int size = defaultTestList.size();
        Assert.assertEquals(4, size);
        System.out.println("defaultTest内容:" + defaultTestList);
        System.out.println("defaultTest长度:" + size);
    }

    /**
     * Method: isEmpty()
     */
    @Test
    public void testIsEmpty() throws Exception {
        Assert.assertFalse(defaultTestList.isEmpty());
        List<Integer> testReomoveList = new ArrayList<>();
        Assert.assertTrue(testReomoveList.isEmpty());
    }

    /**
     * Method: get(int index)
     */
    @Test
    public void testGet() throws Exception {
        Assert.assertTrue("《三国演义》".equals(defaultTestList.get(0)));
        Assert.assertFalse("《西游记》".equals(defaultTestList.get(0)));
    }

    /**
     * Method: set(int index, Object o)
     */
    @Test
    public void testSet() throws Exception {

        List<Integer> testList = new ArrayList<>();
        Assert.assertTrue(testList.add(7));
        Assert.assertTrue(testList.add(8));
        Assert.assertTrue(testList.add(9));
        System.out.println("设置前: " + testList);
        Assert.assertTrue(testList.set(0, 10));
        System.out.println("设置后: " + testList);
    }

    /**
     * Method: contains(Object o)
     */
    @Test
    public void testContains() throws Exception {
        Assert.assertTrue(defaultTestList.contains("《红楼梦》"));
        Assert.assertFalse(defaultTestList.contains("《聊斋》"));
    }

    /**
     * Method: clear()
     */
    @Test
    public void testClear() throws Exception {

        List<Integer> testList = new ArrayList<>();
        Assert.assertTrue(testList.add(7));
        Assert.assertTrue(testList.add(8));
        Assert.assertTrue(testList.add(9));
        System.out.println("清除前: " + testList);
        testList.clear();
        Assert.assertTrue(testList.isEmpty());
        System.out.println("清除后: " + testList);
    }

    /**
     * Method: iterator()
     */
    @Test
    public void testIterator() throws Exception {

        List<Integer> testList = new ArrayList<>();
        Assert.assertTrue(testList.add(7));
        Assert.assertTrue(testList.add(8));
        Assert.assertTrue(testList.add(9));
        Iterator<Integer> iterator = testList.iterator();
        Assert.assertNotNull(iterator);
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        List<String> testListByDel = new ArrayList<>();
        Assert.assertTrue(testListByDel.add("张三"));
        Assert.assertTrue(testListByDel.add("李四"));
        Assert.assertTrue(testListByDel.add("王五"));
        Iterator<String> iteratorByDel = testListByDel.iterator();
        while(iteratorByDel.hasNext()) {
            iteratorByDel.remove();
        }
        Assert.assertTrue(testListByDel.isEmpty());

    }



}
