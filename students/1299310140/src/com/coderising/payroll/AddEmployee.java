package com.coderising.payroll;

public class AddEmployee {
	public static Employee addHourlyEmployee(String name,String address,double rate,String[] timeCardDates,int[] timeCardhours){
		Employee e = new Employee(name,address);
		HourlyClassification classification = new HourlyClassification(rate);
		for(int i = 0;i < timeCardDates.length;i++){
			TimeCard tc = new TimeCard(DateUtil.parseDate(timeCardDates[i]),timeCardhours[i]);
			classification.addTimeCard(tc);
		}
		Affiliation affiliation = new NonAffiliation();
		PaymentMethod paymentMethod = new HoldMethod();
		PaymentSchedule paymentSchedule = new WeeklySchedule();
		
		e.setClassification(classification);
		e.setAffiliation(affiliation);
		e.setPaymentMethod(paymentMethod);
		e.setSchedule(paymentSchedule);
		return e;
	}
	
	public static Employee addSalariedEmployee(String name,String address,double salary,String[] serviceChargeDates,int[] serviceChargeAmount){
		Employee e = new Employee(name,address);
		SalariedClassification sc = new SalariedClassification(salary);
		UnionAffiliation ua = new UnionAffiliation(name);
		for(int i = 0;i < serviceChargeDates.length;i++){
			ServiceCharge serviceCharge = new ServiceCharge(DateUtil.parseDate(serviceChargeDates[i]),serviceChargeAmount[i]);
			ua.addServiceCharge(serviceCharge);
		}
		PaymentMethod pm = new MailMethod(address);
		PaymentSchedule ps = new MonthlySchedule();
		
		e.setClassification(sc);
		e.setAffiliation(ua);
		e.setPaymentMethod(pm);
		e.setSchedule(ps);
		return e;
	}
	
	public static Employee addCommissionEmployee(String name,String address,String bank, String account,double salary,double rate,String[] salesReceiptDates,int[] salesReceiptAmount){
		Employee e = new Employee(name,address);
		CommissionedClassification classification = new CommissionedClassification(salary,rate);
		for(int i = 0;i < salesReceiptDates.length;i++){
			SalesReceipt sr = new SalesReceipt(DateUtil.parseDate(salesReceiptDates[i]),salesReceiptAmount[i]);
			classification.addSalesReceipt(sr);
		}
		Affiliation affiliation = new NonAffiliation();
		PaymentMethod paymentMethod = new BankMethod(bank,account);//new HoldMethod();
		PaymentSchedule paymentSchedule = new BiWeeklySchedule();
		
		e.setClassification(classification);
		e.setAffiliation(affiliation);
		e.setPaymentMethod(paymentMethod);
		e.setSchedule(paymentSchedule);
		return e;
	}
}
