package com.coderising.payroll;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class EmployeeTest {
	
	@Test
	public void test() throws Exception{
		Date d = DateUtil.parseDate("2017-07-31");
		List<Employee> employeeList = loadEmployees();
		for(Employee e:employeeList){
			if(e.isPayDay(d)){
				Paycheck pc = new Paycheck(e.getPayPeriodStartDate(d), d);
				System.out.println("发工资--> "+e);
				e.payDay(pc);
			}
		}
	}

	private List<Employee> loadEmployees()throws Exception{
		List<Employee> employees = new ArrayList<>();
		Set<String> unioners = loadUnionIds();
		UnionAffiliation ua = new UnionAffiliation(5.0);
		Map<String,List<TimeCard>> tcMap = loadTimeCard();
		Map<String,List<SalesReceipt>> srMap = loadSalyReceipt();
		String employeesFileName = "D:\\coding2017\\students\\282692248\\ood\\ood-assignment\\src\\main\\resources\\employees.txt";
		BufferedReader br = new BufferedReader(new FileReader(employeesFileName));
		String line = null;//id	name	address	type	salary	rate
		while( (line = br.readLine()) != null ){
			String[] fileds = line.split("\t");
			String id = fileds[0];
			Employee employee = new Employee(id,fileds[1], fileds[2]);
			switch (fileds[3]) {
			case "hourly":
				employee.setClassification(new HourlyClassification(Double.parseDouble(fileds[5]),tcMap.get(id)));
				employee.setSchedule(new WeeklySchedule());
				break;
			case "comission":
				employee.setClassification(new ComissionClassification(Double.parseDouble(fileds[4]), 
						Double.parseDouble(fileds[5]), srMap.get(id)));
				employee.setSchedule(new BiWeeklySchedule("2017-05-05"));
				break;
			case "salaried":
				employee.setClassification(new SalariedClassification(Double.parseDouble(fileds[4])));
				employee.setSchedule(new MonthlySchedule());
				break;
			default:
				throw new RuntimeException("unkonwn type ["+fileds[3]+"]");
			}
			employee.setPaymentMethod(new HoldMethod());
			if(unioners.contains(id)){
				employee.setAffiliation(ua);
			}
			employees.add(employee);
		}
		br.close();
		return employees;
	}
	
	private Set<String> loadUnionIds() throws Exception{
		Set<String> unioners = new HashSet<>();
		String unionFileName = "D:\\coding2017\\students\\282692248\\ood\\ood-assignment\\src\\main\\resources\\union.txt";
		BufferedReader br = new BufferedReader(new FileReader(unionFileName));
		String line = null;
		while( (line = br.readLine()) != null ){
			unioners.add(line);
		}
		br.close();
		return unioners;
	}
	
	private Map<String,List<TimeCard>> loadTimeCard() throws Exception{
		Map<String,List<TimeCard>> result = new HashMap<>();
		String timecardFileName = "D:\\coding2017\\students\\282692248\\ood\\ood-assignment\\src\\main\\resources\\timecard.txt";
		BufferedReader br = new BufferedReader(new FileReader(timecardFileName));
		String line = null;
		while( (line = br.readLine()) != null ){//date	id	hours
			String[] fields = line.split("\t");
			TimeCard tc = new TimeCard(DateUtil.parseDate(fields[0]),Integer.parseInt(fields[2]));
			List<TimeCard> tcList = result.get(fields[1]);
			if(tcList == null){
				tcList = new ArrayList<>();
				result.put(fields[1], tcList);
			}
			tcList.add(tc);
		}
		br.close();
		return result;
	}
	
	private Map<String,List<SalesReceipt>> loadSalyReceipt() throws Exception{
		Map<String,List<SalesReceipt>> result = new HashMap<>();
		String receiptFileName = "D:\\coding2017\\students\\282692248\\ood\\ood-assignment\\src\\main\\resources\\salesreceipt.txt";
		BufferedReader br = new BufferedReader(new FileReader(receiptFileName));
		String line = null;
		while( (line = br.readLine()) != null ){//date	id	amount
			String[] fields = line.split("\t");
			SalesReceipt sr = new SalesReceipt(DateUtil.parseDate(fields[0]),Double.parseDouble(fields[2]));
			List<SalesReceipt> srList = result.get(fields[1]);
			if(srList == null){
				srList = new ArrayList<>();
				result.put(fields[1], srList);
			}
			srList.add(sr);
		}
		br.close();
		return result;
	}
}
