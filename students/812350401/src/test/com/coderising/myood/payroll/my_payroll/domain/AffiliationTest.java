package com.coderising.myood.payroll.my_payroll.domain;

import com.coderising.myood.payroll.my_payroll.affiliantion.NonAffiliation;
import com.coderising.myood.payroll.my_payroll.affiliantion.ServiseCharge;
import com.coderising.myood.payroll.my_payroll.affiliantion.UnionAffiliation;
import com.coderising.myood.payroll.my_payroll.util.DateUtil;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by thomas_young on 16/9/2017.
 */
public class AffiliationTest {
    private static double TOLERANCE = 1e-5;

    @Test
    public void calculateDeductions() throws Exception {
        Affiliation na = new NonAffiliation();
        Paycheck pc = new Paycheck(1, DateUtil.parseDate("2017-06-01"), DateUtil.parseDate("2017-06-28"));

        double deduction = na.calculateDeductions(pc);
        Assert.assertEquals(0d, deduction, TOLERANCE);

        Affiliation ua = new UnionAffiliation(111);

        ServiseCharge sc1 = new ServiseCharge("2017-06-16", 200);
        ServiseCharge sc2 = new ServiseCharge("2017-06-28", 300);
        ServiseCharge sc3 = new ServiseCharge("2017-07-18", 400);

        deduction = ua.calculateDeductions(pc);
        Assert.assertEquals(444d, deduction, TOLERANCE);

        ((UnionAffiliation) ua).addServiceCharge(sc1);
        ((UnionAffiliation) ua).addServiceCharge(sc2);
        ((UnionAffiliation) ua).addServiceCharge(sc3);
        deduction = ua.calculateDeductions(pc);
        Assert.assertEquals(944d, deduction, TOLERANCE);

    }

}