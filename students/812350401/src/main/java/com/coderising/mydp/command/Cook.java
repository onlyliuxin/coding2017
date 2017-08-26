package com.coderising.mydp.command;

/**
 * Created by thomas_young on 11/8/2017.
 */
public class Cook {

    public void cookSteak() {
        System.out.println("Steak is ok");
    }

    public void cookPork() {
        System.out.println("Pork is ok");
    }

    public static void main(String[] args) {
        StringBuilder x = new StringBuilder("Hello");
        String r1 = x.append(",world").toString();
        String r2 = x.append(",world").toString();
        System.out.println(r1);
        System.out.println(r2);
    }
}
