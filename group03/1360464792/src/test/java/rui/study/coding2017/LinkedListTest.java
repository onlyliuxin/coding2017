package rui.study.coding2017;

import org.junit.Test;

/**
 * 测试链表
 * Created by 赵睿 on 2017/2/24.
 */
public class LinkedListTest {
    @Test
    public void add() throws Exception {
        LinkedList linkedList=new LinkedList();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        try {
            System.out.println(linkedList.get(3));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(linkedList.get(2));
        System.out.println(linkedList.get(1));
        System.out.println(linkedList.get(0));
        try {
            System.out.println(linkedList.get(-1));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void add1() throws Exception {
        //当前位置是否移动
        LinkedList linkedList=new LinkedList();
        linkedList.add(0,0);
        linkedList.add(1,1);

        linkedList.add(0,-1);
        System.out.println(linkedList.size());
        linkedList.add(1,11);
        System.out.println(linkedList.size());
        linkedList.add(2,22);
        linkedList.add(23);
        System.out.println(linkedList.size());


        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>");
        Iterator iterator=linkedList.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }


    @Test
    public void addFirst() throws Exception {
        LinkedList linkedList=new LinkedList();
        linkedList.add(0,0);
        linkedList.add(1,1);
        linkedList.addFirst(-1);

        System.out.println(linkedList.size());

        Iterator iterator=linkedList.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    @Test
    public void addLast() throws Exception {
        LinkedList linkedList=new LinkedList();
        linkedList.add(0,0);
        linkedList.add(1,1);
        linkedList.addLast(2);
        System.out.println(linkedList.size());

        Iterator iterator=linkedList.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }


    }

    @Test
    public void remove() throws Exception {
        LinkedList linkedList=new LinkedList();
        linkedList.add(0,0);
        linkedList.add(1,1);
        System.out.println(linkedList.size());
        try {
            linkedList.remove(2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        linkedList.remove(1);
        System.out.println(linkedList.size());
        linkedList.remove(0);
        System.out.println(linkedList.size());
        try {
            linkedList.remove(0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void removeFirst() throws Exception {
        LinkedList linkedList=new LinkedList();
        try {
            linkedList.removeFirst();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        linkedList.add(0,0);
        linkedList.add(1,1);
        linkedList.removeFirst();
        System.out.println(linkedList.size());

    }

    @Test
    public void removeLast() throws Exception {
        LinkedList linkedList=new LinkedList();
        try {
            linkedList.removeLast();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        linkedList.add(0,0);
        linkedList.add(1,1);
        linkedList.removeLast();
        System.out.println(linkedList.size());

    }
}