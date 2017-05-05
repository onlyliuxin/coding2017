package com.donaldy.download.impl;

import org.junit.Test;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by donal on 2017/3/22.
 */
public class ConnectionManagerImplTest {

    @Test
    public void testOpen() throws Exception{
        URL url = new URL("");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

    }
}
