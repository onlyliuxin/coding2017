package com.coderising.payroll;

import java.util.List;

import com.coderising.payroll.classification.CommissionedClassification;
import com.coderising.payroll.classification.HourlyClassification;
import com.coderising.payroll.classification.SalariedClassification;
import com.coderising.payroll.domain.Employee;
import com.coderising.payroll.domain.HoldMethod;
import com.coderising.payroll.domain.Paycheck;
import com.coderising.payroll.schedule.BiweeklySchedule;
import com.coderising.payroll.schedule.MonthlySchedule;
import com.coderising.payroll.schedule.WeeklySchedule;

public class PayrollService {
	public   List<Employee> getAllEmployees(){
		return null;
	}
	public void savePaycheck(Paycheck pc){
		
	}
	
	public Employee addHourlyEmployee(String name, String address, double hourlyRate){
		Employee e = new Employee(name, address);	
		e.setClassification(new HourlyClassification(hourlyRate));		
		e.setSchedule(new WeeklySchedule());	
		e.setPaymentMethod(new HoldMethod());
		//保存员工到数据库.. 略		
		return e;		
	}
	
	public Employee addSalariedEmployee(String name, String address, double salary){
		Employee e = new Employee(name, address);		
		e.setClassification(new SalariedClassification(salary));		
		e.setSchedule(new MonthlySchedule());	
		e.setPaymentMethod(new HoldMethod());
		//保存员工到数据库.. 略		
		return e;	
	}
	
	public Employee addCommissionedEmployee(String name, String address, double salary, double saleRate){
		Employee e = new Employee(name, address);		
		e.setClassification(new CommissionedClassification(salary, saleRate));		
		e.setSchedule(new BiweeklySchedule());		
		e.setPaymentMethod(new HoldMethod());
		//保存员工到数据库.. 略		
		return e;	
	}
}
