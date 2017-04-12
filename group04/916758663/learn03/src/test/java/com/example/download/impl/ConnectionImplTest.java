package com.example.download.impl;

import static org.assertj.core.api.Assertions.*;

import com.example.download.api.Connection;
import com.example.download.api.ConnectionException;
import com.example.download.api.ConnectionManager;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by qilei on 17/3/24.
 */
public class ConnectionImplTest {

  private Connection connection;

  @Before
  public void setup(){
    ConnectionManager cm = new ConnectionManagerImpl();
    String url ="http://www.hinews.cn/pic/0/13/91/26/13912621_821796.jpg";
    try {
      connection = cm.open(url);
    } catch (ConnectionException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void read() throws Exception {
    byte[] data = null;
    data = connection.read(0, 35469);
    assertThat(data.length).isEqualTo(35470);

    data = connection.read(0, 1023);
    assertThat(data.length).isEqualTo(1024);

    data = connection.read(1024, 2023);
    assertThat(data.length).isEqualTo(1000);

  }

  @Test
  public void getContentLength() throws Exception {
    int contentLength = connection.getContentLength();
    assertThat(contentLength).isEqualTo(35470);
  }

}