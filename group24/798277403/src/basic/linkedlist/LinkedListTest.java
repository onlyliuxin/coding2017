package basic.linkedlist;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by zhouliang on 2017-03-10.
 */
public class LinkedListTest {

    private LinkedList<Integer> myLinkedList = new LinkedList<>();

    private java.util.LinkedList<Integer> systemLinkedList = new java.util.LinkedList<>();

    @Before
    public void setUp(){
        for(int i=0; i<10; i++){
            myLinkedList.add(i);
            systemLinkedList.add(i);
        }
    }
    @Test
    public void add() throws Exception {
        for(int i=0; i<10; i++){
            System.out.println(myLinkedList.get(i));
        }
    }

    @Test
    public void reverse(){
        myLinkedList.reverse();
        for(int i=0; i<10; i++){
            System.out.println(myLinkedList.get(i));
        }
    }

    @Test
    public void removeFirstHalf(){
        myLinkedList.removeFirstHalf();
        System.out.println(myLinkedList.size());
        for(int i=0; i<myLinkedList.size(); i++){
            System.out.println(myLinkedList.get(i));
        }
    }

    @Test
    public void remove(){
        myLinkedList.remove(7,1);
        System.out.println("size "+myLinkedList.size());
        for(int i=0; i<myLinkedList.size(); i++){
            System.out.println(myLinkedList.get(i));
        }
    }

    @Test
    public void getElements(){
        LinkedList<Integer> list = new LinkedList<Integer>();
        list.add(0);
        list.add(7);
        list.add(9);
        int[] reuslt = myLinkedList.getElements(list);
        System.out.println(reuslt.length);
        for(int i=0; i<reuslt.length; i++){
            System.out.println(reuslt[i]);
        }
    }

    @Test
    public void subtract(){
        LinkedList<Integer> list = new LinkedList<Integer>();
        list.add(0);
        list.add(7);
        list.add(9);
        myLinkedList.subtract(list);
        for(int i=0; i<myLinkedList.size(); i++){
            System.out.println(myLinkedList.get(i));
        }
    }

    @Test
    public void removeDuplicateValues(){
        myLinkedList.add(9);
        myLinkedList.add(10);
        myLinkedList.add(10);
        myLinkedList.add(11);
        myLinkedList.removeDuplicateValues();
        for(int i=0; i<myLinkedList.size(); i++){
            System.out.println(myLinkedList.get(i));
        }
    }

    @Test
    public void removeRange(){
        myLinkedList.removeRange(5,9);
        for(int i=0; i<myLinkedList.size(); i++){
            System.out.println(myLinkedList.get(i));
        }
    }

    @Test
    public void intersection(){
        LinkedList<Integer> list = new LinkedList<Integer>();
        list.add(0);
        list.add(2);
        list.add(9);
        list.add(9);
        list.add(9);
        list.add(9);
        list.add(9);
        list.add(9);
        list.add(9);



        LinkedList result = myLinkedList.intersection(list);
        for(int i=0; i<result.size(); i++){
            System.out.println(result.get(i));
        }

    }
}