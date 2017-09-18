package com.jyz.payroll.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.jyz.payroll.domain.PaymentSchedule;
import com.jyz.payroll.util.DateUtil;

/**
 * 双周支付一次薪水
 */
public class BiweeklySchedule implements PaymentSchedule {
	Date firstPayableFriday = DateUtil.parseDate("2017-09-07");
	
	@Override
	public boolean isPayDate(Date date) {

		long interval = DateUtil.getDaysBetween(firstPayableFriday, date);
		System.out.println("距离上次支付多少天：" + interval);
		return interval % 14 == 0;
	}

	@Override
	public Date getPayPeriodStartDate(Date payPeriodEndDate) {
		return DateUtil.add(payPeriodEndDate, -13);
		
	}
	
	public static void main(String [] args) throws Exception{
		BiweeklySchedule schedule = new BiweeklySchedule();
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date d = sdf.parse("2017-06-30");
		
		System.out.println(schedule.isPayDate(d));
		
		System.out.println(DateUtil.isFriday(d));		
		
		System.out.println(schedule.getPayPeriodStartDate(d));
	}

}
