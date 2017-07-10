package com.coderising.payroll;

import com.coderising.payroll.api.Affiliation;
import com.coderising.payroll.api.PaymentClassification;
import com.coderising.payroll.api.PaymentMethod;
import com.coderising.payroll.api.PaymentSchedule;

public abstract class AddEmployeeTransaction {
	private String name;
	private String address;
	private PaymentMethod paymentMethod;
	private Affiliation affiliation;

	
	public AddEmployeeTransaction(String name, String address, PaymentMethod paymentMethod, Affiliation affiliation) {
		super();
		this.name = name;
		this.address = address;
		this.paymentMethod = paymentMethod;
		this.affiliation = affiliation;
	}

	public abstract PaymentClassification getClassification();
	
	public abstract PaymentSchedule getSchedule();
	
	public void execute(){
		PaymentClassification pc=getClassification();
		PaymentSchedule ps=getSchedule();
		Employee e=new Employee(name, address);
		e.setClassification(pc);
		e.setSchedule(ps);
		e.setPaymentMethod(paymentMethod);
		e.setAffiliation(affiliation);
	}
	
}
