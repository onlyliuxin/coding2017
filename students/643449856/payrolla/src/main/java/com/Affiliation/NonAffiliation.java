package com.Affiliation;

import com.pojo.Affiliation;
import com.pojo.Paycheck;

/**
 * Created by nengneng
 * Date: 2017/9/15
 * Time: 20:20
 *
 * 没有联系
 */
public class NonAffiliation implements Affiliation {


    @Override
    public double calculateDeductions(Paycheck pc) {
        return 0.0;
    }
}
