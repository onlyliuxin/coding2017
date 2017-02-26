/**
 * 
 */
package com.coding.basic.container.test;

import java.util.List;

/**
 * @author devin.yin
 *
 */
public class TestContainer {

    /**
     * @param args
     */
    public static void main(String[] args) {
        List<String> list1 = new java.util.ArrayList<String>();
        System.out.println(list1);

        // 4 basic operation for java.util.ArrayList--add remove change query
        list1.add("0");
        list1.add("1");
        list1.add("2");
        list1.add("3");
        list1.add("4");
        list1.add("5");
        list1.add("6");
        list1.add("7");
        list1.add("8");
        list1.add("9");
        System.out.println(list1);

        list1.remove(0);
        System.out.println(list1);

        list1.set(0, "set");
        System.out.println(list1);

        System.out.println(list1.get(0));

        list1.add(9, "10");
        System.out.println(list1);

        System.out.println("------------------------------------------------");

        // 4 basic operation for com.coding.basic.container.ArrayList--add remove change query
        com.coding.basic.container.ArrayList<String> list2 = new com.coding.basic.container.ArrayList<String>();
        System.out.println(list2);
        list2.add("0");
        list2.add("1");
        list2.add("2");
        list2.add("3");
        list2.add("4");
        list2.add("5");
        list2.add("6");
        list2.add("7");
        list2.add("8");
        list2.add("9");
        System.out.println(list2);

        list2.remove(0);
        System.out.println(list2);

        list2.set(0, "set");
        System.out.println(list2);

        System.out.println(list2.get(0));
    }

}
