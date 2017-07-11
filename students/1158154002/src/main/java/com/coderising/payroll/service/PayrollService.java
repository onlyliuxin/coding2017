package com.coderising.payroll.service;

import java.util.Date;
import java.util.List;

import com.coderising.payroll.Employee;
import com.coderising.payroll.Paycheck;

public class PayrollService {
	public List<Employee> getAllEmployees(){
		return null;
	}
	
	public void savePayCheck(Paycheck pc){}
	
	public static void main(String[] args) {
		PayrollService payrollService=new PayrollService();
		Date date=new Date();
		List<Employee> employees=payrollService.getAllEmployees();
		for (Employee e : employees) {
			if (e.isPayDay(date)) {
				Paycheck pc=new Paycheck(e.getPayPeriodStartDate(date), date);
				e.payDay(pc);
				payrollService.savePayCheck(pc);
			}
		}
		
	}
}
