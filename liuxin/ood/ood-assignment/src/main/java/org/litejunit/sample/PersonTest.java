package org.litejunit.sample;

import org.litejunit.v2.TestCase;

public class PersonTest extends TestCase {

	Person p = null;
	protected void setUp() {
		p = new Person("andy",30);
	}
	public PersonTest(String name) {
		super(name);		
	}
	public void testAge(){
		this.assertEquals(30, p.getAge());
	}
	public void testName(){
		this.assertEquals("andy", p.getName());
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
