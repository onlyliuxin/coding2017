package com.transaction;

import com.Classification.HourlyClassification;
import com.Schedule.WeeklySchedule;
import com.pojo.PaymentClassification;
import com.pojo.PaymentSchedule;

/**
 * Created by nengneng
 * Date: 2017/9/15
 * Time: 21:25
 */
public class AddHourlyEmployeeTransaction extends AddEmployeeTransaction {


    private double rate;

    AddHourlyEmployeeTransaction(String name, String address, double hourlyRate) {
        super(name, address);
        this.rate = hourlyRate;
    }

    @Override
    public PaymentClassification getClassification() {
        return new HourlyClassification(rate);
    }

    @Override
    public PaymentSchedule getSchedule() {

        return new WeeklySchedule();
    }
}