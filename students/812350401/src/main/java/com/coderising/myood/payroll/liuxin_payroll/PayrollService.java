package com.coderising.myood.payroll.liuxin_payroll;

import com.coderising.myood.payroll.liuxin_payroll.classification.CommissionedClassification;
import com.coderising.myood.payroll.liuxin_payroll.classification.HourlyClassification;
import com.coderising.myood.payroll.liuxin_payroll.classification.SalariedClassification;
import com.coderising.myood.payroll.liuxin_payroll.domain.Employee;
import com.coderising.myood.payroll.liuxin_payroll.domain.HoldMethod;
import com.coderising.myood.payroll.liuxin_payroll.domain.Paycheck;
import com.coderising.myood.payroll.liuxin_payroll.schedule.BiweeklySchedule;
import com.coderising.myood.payroll.liuxin_payroll.schedule.MonthlySchedule;
import com.coderising.myood.payroll.liuxin_payroll.schedule.WeeklySchedule;

import java.util.List;

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
	
	public Employee addCommissionedEmployee(String name, String address, double salary, double saleRate) {
		Employee e = new Employee(name, address);		
		e.setClassification(new CommissionedClassification(salary, saleRate));
		e.setSchedule(new BiweeklySchedule());
		e.setPaymentMethod(new HoldMethod());
		//保存员工到数据库.. 略		
		return e;	
	}

}
