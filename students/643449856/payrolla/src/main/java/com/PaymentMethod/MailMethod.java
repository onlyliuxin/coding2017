package com.PaymentMethod;

import com.pojo.Paycheck;
import com.pojo.PaymentMethod;

/**
 * Created by nengneng
 * Date: 2017/9/15
 * Time: 20:18
 *
 * 从银行直接取
 *
 */
public class MailMethod implements PaymentMethod {
    private String address = "";


    public MailMethod(String address) {
        	super();
        	this.address = address;
    }

    @Override
    public void pay(Paycheck pc) {
        System.out.println("已将支票邮寄到"+address);
        System.out.println("应付" + pc.getGrossPay());
        System.out.println("扣除" + pc.getDeductions());
        System.out.println("实付" + pc.getNetPay());
    }
}
