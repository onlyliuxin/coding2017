package com.coderising.myood.atmSimulation.impl;

import com.coderising.myood.atmSimulation.model.BankProxy;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by thomas_young on 30/7/2017.
 */
public class BankProxyImplTest {
    private static BankProxy bankProxy;
    {
        bankProxy = new BankProxyImpl();
        NetworkClient networkClient = new NetworkClient();
        bankProxy.setNetworkClient(networkClient);
    }
    @Test
    public void verify() throws Exception {
        Assert.assertFalse(bankProxy.verify("yangkai", "12345"));
        Assert.assertFalse(bankProxy.verify(null, "12345"));
        Assert.assertTrue(bankProxy.verify("yangkai", "123456"));
    }

    @Test
    public void process() throws Exception {

    }

}