package com.ace.coding;

/**
 * Created by ace on 2017/3/11.
 */
public class LinkedListTest {
    public static void showLinkedList(LinkedList linkedList){
        for (int i = 0; i < linkedList.size(); i++){
            System.out.println(linkedList.get(i));
        }
    }
    public static void main(String[] args){
        LinkedList linkedList = new LinkedList();
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(3);
        linkedList.add(3);
        linkedList.add(10);

        LinkedList numberLink = new LinkedList();
        numberLink.add(2);
        numberLink.add(3);
        numberLink.add(5);

        /*int[] newArray = linkedList.getElements(numberLink);
        for (int i = 0; i < newArray.length; i++) {
            System.out.println(newArray[i]);
        }*/
        numberLink.reverse();
//        linkedList.removeRange(3,8);
        showLinkedList(numberLink);
    }
}
