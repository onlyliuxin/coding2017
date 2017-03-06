package xdx.homework.first;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * LinkedList Tester.
 *
 */
public class LinkedListTest {

    private LinkedList<String> defaultLinkList;

    @Before
    public void before() throws Exception {

        defaultLinkList = new LinkedList<>();
        defaultLinkList.add("1.苹果");
        defaultLinkList.add("2.香蕉");
        defaultLinkList.add("3.菠萝");
        defaultLinkList.add("4.橙子");
        defaultLinkList.add("5.葡萄");
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: add(E data)
     */
    @Test
    public void testAdd() throws Exception {

        LinkedList<Integer> testLinkedList = new LinkedList<>();
        for (Integer i = 0; i < 1000; i++) {
            testLinkedList.add(i);
        }
        System.out.println(testLinkedList);
    }

    /**
     * Method: remove(int index)
     */
    @Test
    public void testRemoveIndex() throws Exception {

        LinkedList<String> testLinkedList = new LinkedList<>();
        for (int i = 0; i < 100; i++) {
            testLinkedList.add(i + "号");
        }
        System.out.println("删除前:" + testLinkedList);
        for (int i = 0; i < 50; i++) {
            testLinkedList.remove(i);
        }
        System.out.println("删除后:" + testLinkedList);
    }

    /**
     * Method: remove(E e)
     */
    @Test
    public void testRemoveE() throws Exception {

        LinkedList<String> testLinkedList = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            testLinkedList.add(i + "号");
        }
        System.out.println("删除前:" + testLinkedList);
        testLinkedList.remove("1号");
        testLinkedList.remove("10号");
        System.out.println("删除后:" + testLinkedList);
    }

    /**
     * Method: size()
     */
    @Test
    public void testSize() throws Exception {

        LinkedList<String> testLinkedList = new LinkedList<>();
        int i = 0;
        for (; i < 100; i++) {
            testLinkedList.add(i + "号");
        }
        Assert.assertEquals(i, testLinkedList.size());
        System.out.println("testLinkedList的内容:" + testLinkedList);
        System.out.println("testLinkedList的长度:" + testLinkedList.size());
    }

    /**
     * Method: isEmpty()
     */
    @Test
    public void testIsEmpty() throws Exception {

        LinkedList<String> testLinkedList = new LinkedList<>();
        System.out.println("testLikedList是否为空:" + testLinkedList.isEmpty());
        Assert.assertTrue(testLinkedList.isEmpty());
        String insertElement = "Hello World!";
        System.out.println("插入元素: " + insertElement);
        testLinkedList.add(insertElement);
        System.out.println("testLikedList是否为空:" + testLinkedList.isEmpty());
        Assert.assertFalse(testLinkedList.isEmpty());

    }

    /**
     * Method: get(int index)
     */
    @Test
    public void testGet() throws Exception {

        LinkedList<String> testLinkedList = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            testLinkedList.add(i + "号");
        }
        for (int i = 0; i < 10; i++) {
            System.out.println("索引为" + i + "处的元素为:" + testLinkedList.get(i));
            Assert.assertEquals((i + "号"), testLinkedList.get(i));
        }
    }

    /**
     * Method: set(int index, E e)
     */
    @Test
    public void testSet() throws Exception {

        LinkedList<String> testLinkedList = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            testLinkedList.add(i + "号");
        }
        System.out.println("原链表:" + testLinkedList);
        for (int i = 0; i < 10; i++) {
            String nodeValue = i + "号替补";
            testLinkedList.set(i, nodeValue);
            Assert.assertEquals(nodeValue, testLinkedList.get(i));
        }
        System.out.println("替换后:" + testLinkedList);
    }

    /**
     * Method: contains(E e)
     */
    @Test
    public void testContains() throws Exception {

        LinkedList<String> testLinkedList = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            testLinkedList.add(i + "号");
        }
        System.out.println("原链表:" + testLinkedList);
        for (int i = 0; i < 10; i++) {
            String containTestValue = i + "号";
            System.out.println("是否包含【" + containTestValue + "】:" +
                    testLinkedList.contains(containTestValue));
            Assert.assertTrue(testLinkedList.contains(containTestValue));
        }

    }

    /**
     * Method: clear()
     */
    @Test
    public void testClear() throws Exception {

        LinkedList<String> testLinkedList = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            testLinkedList.add(i + "号");
        }
        System.out.println("原链表:" + testLinkedList);
        testLinkedList.clear();
        System.out.println("清空后的链表:" + testLinkedList);
        Assert.assertTrue(testLinkedList.isEmpty());
    }

    /**
     * Method: iterator()
     */
    @Test
    public void testIterator() throws Exception {

        LinkedList<String> testLinkedList = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            testLinkedList.add(i + "号");
        }
        System.out.println("原链表:" + testLinkedList);
        Iterator iterator = testLinkedList.iterator();
        Assert.assertNotNull(iterator);
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }



    /**
     * Method: remove()
     */
    @Test
    public void testRemove() throws Exception {

        LinkedList<String> testLinkedList = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            testLinkedList.add(i + "号");
        }
        System.out.println("原链表:" + testLinkedList);
        Iterator iterator = testLinkedList.iterator();
        while (iterator.hasNext()) {
            iterator.remove();
        }
        System.out.println("删除所有元素后:" + testLinkedList);
        Assert.assertTrue(testLinkedList.isEmpty());

    }


}
