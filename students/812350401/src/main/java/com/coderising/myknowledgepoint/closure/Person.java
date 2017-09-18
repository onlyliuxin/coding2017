package com.coderising.myknowledgepoint.closure;

/**
 * Created by thomas_young on 27/8/2017.
 */
public class Person {

    public static void main(String[] args) {
        //买一箱牛奶
        Milk m = new Milk();

        Active haveMeals = m.HaveMeals();

        //没事喝一瓶
        haveMeals.drink();
        //有事喝一瓶
        haveMeals.drink();

        //看看还剩多少？
        m.currentNum();
        m = null;
        haveMeals.drink();  // 闭包会导致资源不被回收
        haveMeals.drink();
        haveMeals.drink();
        haveMeals.drink();
        haveMeals.drink();
        haveMeals.drink();
        haveMeals.drink();
        haveMeals.drink();
        haveMeals.drink();
        haveMeals.drink();
        haveMeals.drink();
        haveMeals.drink();
        haveMeals.drink();
        haveMeals.drink();
        haveMeals.drink();
        haveMeals.drink();
        haveMeals.drink();
    }

}
