package org.litejunit.v3.sample;


import org.litejunit.v3.Before;
import org.litejunit.v3.Test;

import static org.litejunit.v3.Assert.assertEquals;

/**
 * Created by john on 2017/9/2.
 */
public class PersonTest{

    Person p = null;
    @Before
    public void setUp() {
        p = new Person("andy",30);
    }

    @Test
    public void testAge(){
        assertEquals(30, p.getAge());
    }

    @Test
    public void testName(){
        assertEquals("andy", p.getName());
    }
}

class Person{
    private String name;
    private int age;

    public Person(String name, int age) {

        this.name = name;
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }


}