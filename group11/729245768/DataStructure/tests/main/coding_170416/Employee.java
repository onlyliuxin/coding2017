package main.coding_170416;

/**
 * Created by peter on 2017/4/20.
 */
public class Employee {
    private String name;
    private int age;
    public Employee(String name, int age){
        this.name = name;
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public void sayHello(String name){
        System.out.println("Hello, "+name);
    }

    public static void main(String[] args) {

    }
}
