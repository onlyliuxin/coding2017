package task1.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import task3.basic.WLinkedList;

import java.util.Arrays;

/**
 * Created by wanc on 2017/3/7.
 * 3月5日 布置的数据结构作业测试
 */
public class WLinkedListTest {
    WLinkedList list;

    @Before
    public void setUp() throws Exception {
        list = new WLinkedList();
        list.add(11);
        list.add(101);
        list.add(201);
        list.add(301);
        list.add(401);
        list.add(501);
        list.add(601);
        list.add(701);
        list.add(701);
        list.add(301);
        System.out.println(list);
    }

    @After
    public void tearDown() throws Exception {


    }


    @Test
    public void testReverse() throws Exception {
        list.reverse();
        System.out.println(list);
    }

    @Test
    public void testRemoveFirstHalf() throws Exception {
        list.removeFirstHalf();
        System.out.println(list);
    }

    @Test
    public void testRemove() throws Exception {
        list.remove(3,4);
        System.out.println(list);
    }

    @Test
    public void testGetElements() throws Exception {
        WLinkedList lst = new WLinkedList();
        lst.add(1);
        lst.add(3);
        lst.add(4);
        lst.add(6);
        int[] elements = list.getElements(lst);
        System.out.println(Arrays.toString(elements));
    }

    @Test
    public void testSubtract() throws Exception {
        WLinkedList lst = new WLinkedList();
        lst.add(101);
        lst.add(301);
        lst.add(401);
        lst.add(601);
        list.subtract(lst);
        System.out.println(list);
    }

    @Test
    public void testRemoveDuplicateValues() throws Exception {
        list.add(301);
        list.add(401);
        System.out.println(list);
        list.removeDuplicateValues();
        System.out.println(list);
    }

    @Test
    public void testRemoveRange() throws Exception {

    }

    @Test
    public void testIntersection() throws Exception {

    }
}