package com.coderising.payroll.transaction;

import com.coderising.payroll.domain.Employee;
import com.coderising.payroll.domain.HoldMethod;
import com.coderising.payroll.domain.PaymentClassification;
import com.coderising.payroll.domain.PaymentMethod;
import com.coderising.payroll.domain.PaymentSchedule;

public abstract class AddEmployeeTransaction {
	private String name;
	private String address;
	public AddEmployeeTransaction(String name,String address){
		this.name = name;
		this.address = address;
	}
	public abstract PaymentSchedule getPaymentSchedule();
	public abstract PaymentClassification getPaymentClassification();
	
	public void execute(){
		Employee employee=new Employee(name, address);
		employee.setClassification(getPaymentClassification());
		employee.setPaymentMethod(new HoldMethod());
		employee.setSchedule(getPaymentSchedule());
		//???›Ô?????, ??
	}
}
