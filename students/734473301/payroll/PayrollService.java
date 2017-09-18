package com.jyz.payroll;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jyz.payroll.affiliation.NonAffiliation;
import com.jyz.payroll.classification.CommissionedClassification;
import com.jyz.payroll.classification.HourlyClassification;
import com.jyz.payroll.classification.SalariedClassification;
import com.jyz.payroll.domain.Employee;
import com.jyz.payroll.domain.HoldMethod;
import com.jyz.payroll.domain.Paycheck;
import com.jyz.payroll.domain.TimeCard;
import com.jyz.payroll.schedule.BiweeklySchedule;
import com.jyz.payroll.schedule.MonthlySchedule;
import com.jyz.payroll.schedule.WeeklySchedule;

public class PayrollService {
	private List<Employee> allEmployees = new ArrayList<>();

	public   List<Employee> getAllEmployees(){
		return allEmployees;
	}
	public void savePaycheck(Paycheck pc){
		System.out.println("保存此次支付记录："+pc);
	}
	
	public Employee addHourlyEmployee(String name, String address, double hourlyRate){

		Employee e = new Employee(name, address);
		HourlyClassification hourlyClassification = new HourlyClassification(hourlyRate);

		hourlyClassification.addTimeCard(new TimeCard(new Date(),8));

		e.setClassification(hourlyClassification);
		e.setSchedule(new WeeklySchedule());	
		e.setPaymentMethod(new HoldMethod());
		e.setAffiliation(new NonAffiliation());
		allEmployees.add(e);
		//保存员工到数据库.. 略		
		return e;		
	}
	
	public Employee addSalariedEmployee(String name, String address, double salary){
		Employee e = new Employee(name, address);		
		e.setClassification(new SalariedClassification(salary));		
		e.setSchedule(new MonthlySchedule());	
		e.setPaymentMethod(new HoldMethod());
		e.setAffiliation(new NonAffiliation());
		//保存员工到数据库.. 略		
		allEmployees.add(e);
		return e;
	}
	
	public Employee addCommissionedEmployee(String name, String address, double salary, double saleRate){
		Employee e = new Employee(name, address);		
		e.setClassification(new CommissionedClassification(salary, saleRate));		
		e.setSchedule(new BiweeklySchedule());		
		e.setPaymentMethod(new HoldMethod());
		e.setAffiliation(new NonAffiliation());
		//保存员工到数据库.. 略		
		allEmployees.add(e);
		return e;
	}


}
