package rui.study.coding2017.jobs3;

import org.junit.After;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import static org.junit.Assert.*;

/**
 * 创建于 2017-03-12.
 *
 * @author 赵睿
 */
public class LinkedListStudyTest {
    private LinkedListStudy linkedListStudy=new LinkedListStudy();
    private LinkedList<Integer> linkedList=new LinkedList<Integer>();
    @Test
    public void reverse() throws Exception {
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList=linkedListStudy.reverse(linkedList);
    }

    @Test
    public void removeFirstHalf() throws Exception {
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList=linkedListStudy.removeFirstHalf(linkedList);
    }

    @Test
    public void remove() throws Exception {
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
//         linkedList=linkedListStudy.remove(0,1,linkedList);
//        linkedList=linkedListStudy.remove(0,4,linkedList);
        linkedList=linkedListStudy.remove(1,2,linkedList);
    }

    @Test
    public void getElements() throws Exception {
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
//         linkedList=linkedListStudy.remove(0,1,linkedList);
//        linkedList=linkedListStudy.remove(0,4,linkedList);
        ArrayList<Integer> arrayList=new ArrayList<Integer>();
        arrayList.add(1);
        int resule[]=linkedListStudy.getElements(linkedList,arrayList);
        for (int i = 0; i < resule.length; i++) {
            System.out.println(resule[i]);
        }
        
    }

    @Test
    public void subtract() throws Exception {
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
//         linkedList=linkedListStudy.remove(0,1,linkedList);
//        linkedList=linkedListStudy.remove(0,4,linkedList);
        linkedList=linkedListStudy.subtract(linkedList,linkedList);
    }

    @Test
    public void removeDuplicateValues() throws Exception {
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(4);
//         linkedList=linkedListStudy.remove(0,1,linkedList);
//        linkedList=linkedListStudy.remove(0,4,linkedList);
        linkedList=linkedListStudy.removeDuplicateValues(linkedList);
    }

    @Test
    public void removeRange() throws Exception {
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(4);
        linkedList=linkedListStudy.removeRange(1,3,linkedList);
    }

    @Test
    public void intersection() throws Exception {
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);

        LinkedList<Integer> linkedList1=new LinkedList<Integer>();
        linkedList1.add(3);
        linkedList1.add(4);


        linkedList=linkedListStudy.intersection(linkedList,linkedList1);
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("linkedList中存在的值：========");
        Iterator<Integer> iterator =linkedList.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }
}