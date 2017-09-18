package org.v0_my.sample.person;

import org.v0_my.TestCase;

public class PersonTest extends TestCase {

	Person p = null;
	protected void setUp() {
		p = new Person("andy",30);
	}
	public PersonTest(String name) {
		super(name);		
	}
	public void testAge(){
		this.assertEquals(31, p.getAge());
	}
	public void testName(){
		this.assertEquals("andy1", p.getName());
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
