package com.coderising.ood.srp;

/**
 * Created by 360682644 on 2017/6/13.
 */
public interface IMailable {

    String toEmailText(String... params);
    String toEmailSubject(String... params);
}
