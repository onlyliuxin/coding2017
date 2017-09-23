package com.coderising.payroll;

import java.util.ArrayList;
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
import com.coderising.payroll.transaction.AddEmployeeTransaction;
import com.coderising.payroll.transaction.AddHourlyEmployeeTransaction;

public class PayrollService {
	

	public List<Employee> getAllEmployees() {
		List<Employee> list = new ArrayList<>();
		list.add(addHourlyEmployee("????", "???", 0.6));
		list.add(addSalariedEmployee("????", "???", 1000, 0.6));
		list.add(addCommissionedEmployee("????", "???", 1500));

		return list;
	}



	public void savePaycheck(Paycheck pc) {
		//?????????....

	}

	public Employee addHourlyEmployee(String name, String address, double hourlyRate) {
		Employee employee = new Employee(name, address);
		employee.setClassification(new HourlyClassification(hourlyRate));
		employee.setPaymentMethod(new HoldMethod());
		employee.setSchedule(new WeeklySchedule());
		return employee;
	}

	public Employee addSalariedEmployee(String name, String address, double salary, double saleRate) {
		Employee employee = new Employee(name, address);
		employee.setClassification(new CommissionedClassification(salary, saleRate));
		employee.setPaymentMethod(new HoldMethod());
		employee.setSchedule(new BiweeklySchedule());
		return employee;
	}

	public Employee addCommissionedEmployee(String name, String address, double salary) {
		Employee employee = new Employee(name, address);
		employee.setClassification(new SalariedClassification(salary));
		employee.setPaymentMethod(new HoldMethod());
		employee.setSchedule(new MonthlySchedule());
		return employee;
	}
	
	List<AddEmployeeTransaction> list;
	public void addEmployee(AddEmployeeTransaction transaction) {
		list.add(transaction);
	}

	public static void main(String[] args) {
		new PayrollService().addEmployee(new AddHourlyEmployeeTransaction("", "", 0.1));
	}
}
