package list;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author jiaxun
 */
public class TestSinglyLinkedList {

    SinglyLinkedList singlyLinkedList;

    @Before
    public void setUp() {
        singlyLinkedList = new SinglyLinkedList();
    }

    @After
    public void tearDown() {

    }

    @Test
    public void testReverse() {
        singlyLinkedList.add(3);
        singlyLinkedList.add(7);
        singlyLinkedList.add(10);
        singlyLinkedList.reverse();
        int[] resultList = {10, 7, 3};
        for (int i = 0, len = resultList.length; i < len; i++) {
            Assert.assertEquals(resultList[i], singlyLinkedList.get(i).getData());
        }
    }

    @Test
    public void testRemoveFirstHalf() {
        singlyLinkedList.add(2);
        singlyLinkedList.add(5);
        singlyLinkedList.add(7);
        singlyLinkedList.add(8);
        singlyLinkedList.add(10);
        singlyLinkedList.removeFirstHalf();
        int[] resultList = {7, 8, 10};
        for (int i = 0, len = resultList.length; i < len; i++) {
            Assert.assertEquals(resultList[i], singlyLinkedList.get(i).getData());
        }
    }

    @Test
    public void testRemove() {
        singlyLinkedList.add(1);
        singlyLinkedList.add(2);
        singlyLinkedList.add(3);
        singlyLinkedList.add(4);
        int[] resultList = {1, 3, 4};
        singlyLinkedList.remove(1, 1);
        for (int i = 0, len = resultList.length; i < len; i++) {
            Assert.assertEquals(singlyLinkedList.get(i).getData(), resultList[i]);
        }
    }

    @Test
    public void testGetElements() {
        singlyLinkedList.add(11);
        singlyLinkedList.add(101);
        singlyLinkedList.add(201);
        singlyLinkedList.add(301);
        singlyLinkedList.add(401);
        singlyLinkedList.add(501);
        singlyLinkedList.add(601);
        singlyLinkedList.add(701);
        SinglyLinkedList listB = new SinglyLinkedList();
        listB.add(1);
        listB.add(3);
        listB.add(4);
        listB.add(6);
        int[] expectedArray = {101, 301, 401, 601};
        int[] resultArray = singlyLinkedList.getElements(listB);
        for (int i = 0, len = expectedArray.length; i < len; i++) {
            Assert.assertEquals(expectedArray[i], resultArray[i]);
        }
    }

    @Test
    public void testSubtract() {
        singlyLinkedList.add(11);
        singlyLinkedList.add(101);
        singlyLinkedList.add(201);
        singlyLinkedList.add(301);
        singlyLinkedList.add(401);
        singlyLinkedList.add(501);
        singlyLinkedList.add(601);
        singlyLinkedList.add(701);
        SinglyLinkedList listB = new SinglyLinkedList();
        listB.add(101);
        listB.add(301);
        listB.add(401);
        listB.add(601);
        singlyLinkedList.subtract(listB);
        int[] expectedArray = {11, 201, 501, 701};
        for (int i = 0, len = expectedArray.length; i < len; i++) {
            Assert.assertEquals(expectedArray[i], singlyLinkedList.get(i).getData());
        }
    }

    @Test
    public void testRemoveDuplicateValues() {
        singlyLinkedList.add(11);
        singlyLinkedList.add(101);
        singlyLinkedList.add(201);
        singlyLinkedList.add(201);
        singlyLinkedList.add(301);
        singlyLinkedList.add(301);
        singlyLinkedList.add(301);
        singlyLinkedList.removeDuplicateValues();
        int[] expectedArray = {11, 101, 201, 301};
        for (int i = 0, len = expectedArray.length; i < len; i++) {
            Assert.assertEquals(expectedArray[i], singlyLinkedList.get(i).getData());
        }
    }

    @Test
    public void testRemoveRange() {
        singlyLinkedList.add(11);
        singlyLinkedList.add(101);
        singlyLinkedList.add(201);
        singlyLinkedList.add(301);
        singlyLinkedList.add(401);
        singlyLinkedList.removeRange(10, 400);
        int[] expectedArray = {401};
        for (int i = 0, len = expectedArray.length; i < len; i++) {
            Assert.assertEquals(expectedArray[i], singlyLinkedList.get(i).getData());
        }
    }

    @Test
    public void testIntersection() {
        singlyLinkedList.add(12);
        singlyLinkedList.add(18);
        singlyLinkedList.add(32);
        singlyLinkedList.add(98);
        SinglyLinkedList bList = new SinglyLinkedList();
        bList.add(34);
        bList.add(51);
        bList.add(53);
        bList.add(78);
        SinglyLinkedList resultList = singlyLinkedList.intersection(bList);
        int[] expectedArray = {12, 18, 32, 34, 51, 53, 78, 98};
        for (int i = 0, len = expectedArray.length; i < len; i++) {
            Assert.assertEquals(expectedArray[i], resultList.get(i).getData());
        }
    }

}
