package com.coderising.payroll;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		List<Employee> employeeList = getEmployees();
		Date date = DateUtil.parseDate("2017-6-30");
		for(Employee e : employeeList){
			if(e.isPayDay(date)){//可以加上是否已经发过工资的判断
				Paycheck pc = new Paycheck(e.getPayPeriodStartDate(date),date);
				e.payDay(pc);
				//保存pc
			}
		}
	}
	
	public static List<Employee> getEmployees(){
		String[] timeCardDates = {"2017-6-23","2017-6-24","2017-6-28","2017-6-29","2017-6-30","2017-7-1"};
		int[] timeCardhours = {7,8,7,9,6,7};
		Employee e1 = AddEmployee.addHourlyEmployee("Hourly1", "a", 10, timeCardDates, timeCardhours);
		
		String[] serviceChargeDates = {"2017-5-31","2017-6-1","2017-6-15","2017-6-20","2017-6-30","2017-7-1"};
        int[] serviceChargeAmount = {5,3,2,4,5,5};
		Employee e2 = AddEmployee.addSalariedEmployee("Salaried1", "b", 6000, serviceChargeDates, serviceChargeAmount);
		
		String[] salesReceiptDates = {"2017-6-16","2017-6-17","2017-6-20","2017-6-25","2017-6-30","2017-7-1"};
		int[] salesReceiptAmount = {50000,90000,80000,50000,80000,90000};
		Employee e3 = AddEmployee.addCommissionEmployee("Commission1", "c", "中国农业银行", "0010", 2500, 0.01, salesReceiptDates, salesReceiptAmount);
		
		List<Employee> result = new ArrayList<Employee>();
		result.add(e1);
		result.add(e2);
		result.add(e3);
		return result;
	}
}
