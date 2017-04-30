package jvm;

public class E {
	
	private String name;
    private int age;
    
    public E(String name, int age) {
        this.name = name;
        this.age = age;        
    }    

    public void setName(String name) {
        this.name = name;
    }
    public void setAge(int age){
    	this.age = age;
    }
    public void sayHello() {        
    	System.out.println("Hello , this is class Employee ");    	
    }
    public int test(int a, int b, int c, int d, int e) {
        int f = a + b;
        int g = c + d + e;
    	System.out.println(f + g);
        return 666;
    }
    public static void main(String[] args){
    	E p = new E("Andy",29);
    	p.sayHello();
    	int r = p.test(1, 2, 3, 4, 5);
    	System.out.println(r);
    }
}
