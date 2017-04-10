package com.coderising.download.test;

import static org.junit.Assert.*;

import java.io.IOException;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.impl.ConnectionImpl;
import com.coderising.download.impl.ConnectionManagerImpl;

public class ConnectionImplTest extends ConnectionImpl {

	private Connection conn;
	@Before
	public void setUp() throws Exception {
		ConnectionManagerImpl cm = new ConnectionManagerImpl();
		conn = cm.open("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489219389096&di=32a40c21fdc1f75c499f3f6eecaa41a3&imgtype=0&src=http%3A%2F%2Fimgstore.cdn.sogou.com%2Fapp%2Fa%2F100540002%2F759676.jpg");
	}

	@After
	public void tearDown() throws Exception {
	}
	@Test
	public void testRead() throws Exception {
		Assert.assertEquals(108229, conn.getContentLength());
		byte[] bs = conn.read(0, 108229);
	}

}
