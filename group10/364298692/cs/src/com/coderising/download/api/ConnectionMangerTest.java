package com.coderising.download.api;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.coderising.download.impl.ConnectionManagerImpl;

public class ConnectionMangerTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testOpen() {
		ConnectionManager cm = new ConnectionManagerImpl();
		try {
			cm.open("http:baidu.com");
		} catch (ConnectionException e) {
			
			e.printStackTrace();
		}
	}

}
