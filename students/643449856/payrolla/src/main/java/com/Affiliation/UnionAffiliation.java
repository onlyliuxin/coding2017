package com.Affiliation;

import com.pojo.Affiliation;
import com.pojo.Paycheck;

/**
 * Created by nengneng
 * Date: 2017/9/15
 * Time: 20:21
 */
public class UnionAffiliation implements Affiliation {


    @Override
    public double calculateDeductions(Paycheck pc) {
        return 0;
    }
}
