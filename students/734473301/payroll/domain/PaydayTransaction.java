package com.jyz.payroll.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.jyz.payroll.PayrollService;


public class PaydayTransaction {
	private Date date;
	private PayrollService payrollService;
	
	public void execute(){
		List<Employee> employees = payrollService.getAllEmployees();
		for(Employee e : employees){
			if(e.isPayDay(date)){
				Paycheck pc = new Paycheck(e.getPayPeriodStartDate(date),date);
				e.payDay(pc);
				payrollService.savePaycheck(pc);
				System.out.println("");
			}
		}
	}


	public static void main(String args[]) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
		PayrollService ps = new PayrollService();
		PaydayTransaction pt = new PaydayTransaction();
		pt.date = sdf.parse("2017-09-21 05");
		pt.payrollService = ps;
		// 小时工
		ps.addHourlyEmployee("小时工","上海",200);
		//销售人员
		ps.addCommissionedEmployee("销售","广州",500,0.2);
		ps.addSalariedEmployee("月薪族","北京",10000);
		pt.execute();


	}
}

