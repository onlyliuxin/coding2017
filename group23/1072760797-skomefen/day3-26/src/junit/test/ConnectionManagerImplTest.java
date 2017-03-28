package junit.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;
import com.coderising.download.impl.ConnectionImpl;
import com.coderising.download.impl.ConnectionManagerImpl;

public class ConnectionManagerImplTest {

	@Before
	public void setUp() throws Exception {
	}


	@Test
	public void testOpen() throws ConnectionException {
		ConnectionManager cm = new ConnectionManagerImpl();
		String url = "http://localhost:8080/TimeCapsule/readme.txt";
		Connection conn = cm.open(url);
	}

}
