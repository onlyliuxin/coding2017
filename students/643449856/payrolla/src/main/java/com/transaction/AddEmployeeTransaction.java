package com.transaction;

import com.PaymentMethod.HoldMethod;
import com.pojo.Employee;
import com.pojo.PaymentClassification;
import com.pojo.PaymentMethod;
import com.pojo.PaymentSchedule;

/**
 * Created by nengneng
 * Date: 2017/9/15
 * Time: 21:24
 */
public abstract class AddEmployeeTransaction {

    private String name;
    private String address;
    public AddEmployeeTransaction(String name,String address){
        this.name = name;
        this.address = address;
    }


    public abstract PaymentClassification getClassification();

    public abstract PaymentSchedule getSchedule();


    public void execute(){
        PaymentClassification pc = getClassification();
        PaymentSchedule ps = getSchedule();
        PaymentMethod pm = new HoldMethod();
        Employee e = new Employee(name, address);
        e.setClassification(pc);
        e.setSchedule(ps);
        e.setPaymentMethod(pm);
    }




}
