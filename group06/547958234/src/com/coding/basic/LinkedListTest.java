package com.coding.basic;

import com.coding.basic.LinkedList;
/**
 * Created by mac on 2017/2/21.
 */
public class LinkedListTest {
    public static void main(String[] args) {
        LinkedList l = new LinkedList();
        l.add(0);
        l.add(1);
        l.add(2);
        l.add(3,3);
        Object ret = l.remove(1);

        for(int i=0;i<l.size();i++) {
            System.out.print(l.get(i)+" ");
        }
    }
}
