package com.coderising.ood.atmSimulation.atm.print;

import com.coderising.ood.atmSimulation.atm.transaction.Trasaction;

import java.util.List;

/**
 * @author nvarchar
 *         date 2017/7/14
 */
public class Printer {
    public void print(List<Trasaction> list) {
        for (Trasaction trasaction : list) {
            System.out.println(trasaction.toPrint());
        }
    }
}
