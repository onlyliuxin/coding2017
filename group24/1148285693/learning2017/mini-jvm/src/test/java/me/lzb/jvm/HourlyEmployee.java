package me.lzb.jvm;

/**
 * @author LZB
 */
public class HourlyEmployee extends EmployeeV2 {
    int hourlySalary;

    public HourlyEmployee(String name, int age, int hourlySalary) {
        super(name, age);
        this.hourlySalary = hourlySalary;
    }

    public void sayHello() {
        System.out.println("Hello , this is Hourly Employee");
    }

    public static void main(String[] args) {
        EmployeeV2 e = new HourlyEmployee("Lisa", 20, 40);
        e.sayHello();
    }

    public int getHourlySalary() {
        return this.hourlySalary;
    }
}
