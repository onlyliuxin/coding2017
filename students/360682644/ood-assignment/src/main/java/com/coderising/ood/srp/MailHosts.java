package com.coderising.ood.srp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 360682644 on 2017/6/13.
 */
public class MailHosts {

    private List<String> hosts = new ArrayList();
    {
        hosts.add("smtp.163.com");
        hosts.add("smtp1.163.com");
    }

    public List<String> getHosts() {
        return hosts;
    }
    public void setHosts(List<String> hosts) {
        this.hosts = hosts;
    }
}
