package com;

import com.Classification.CommissionedClassification;
import com.Classification.HourlyClassification;
import com.Classification.SalariedClassification;
import com.PaymentMethod.BankMethod;
import com.PaymentMethod.MailMethod;
import com.Schedule.BiweeklySchedule;
import com.Schedule.MonthlySchedule;
import com.Schedule.WeeklySchedule;
import com.pojo.Employee;
import com.PaymentMethod.HoldMethod;
import com.pojo.Paycheck;

import java.util.List;

/**
 * Created by nengneng
 * Date: 2017/9/15
 * Time: 21:11
 */
public class PayrollService {


    public List<Employee> getAllEmployees(){
        return null;
    }

    public void savePaycheck(Paycheck pc){

    }

    /**
     * 添加小时工
     * @param name
     * @param address
     * @param hourlyRate
     * @return
     */
    public Employee addHourlyEmployee(String name, String address, double hourlyRate){
        Employee e = new Employee(name, address);
        e.setClassification(new HourlyClassification(hourlyRate));
        e.setSchedule(new WeeklySchedule());
        e.setPaymentMethod(new HoldMethod());
        return e;
    }


    /**
     * 添加固定工资员工
     * @param name
     * @param address
     * @param salary
     * @return
     */
    public Employee addSalariedEmployee(String name, String address, double salary){
        Employee e = new Employee(name, address);
        e.setClassification(new SalariedClassification(salary));
        e.setSchedule(new MonthlySchedule());
        e.setPaymentMethod(new BankMethod());
        return e;
    }


    /**
     * 添加销售员工
     * @param name
     * @param address
     * @param salary
     * @param saleRate
     * @return
     */
    public Employee addCommissionedEmployee(String name, String address, double salary, double saleRate){
        Employee e = new Employee(name, address);
        e.setClassification(new CommissionedClassification(salary, saleRate));
        e.setSchedule(new BiweeklySchedule());
        e.setPaymentMethod(new MailMethod());
        return e;
    }


}
