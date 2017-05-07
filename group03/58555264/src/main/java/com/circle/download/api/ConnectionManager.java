package com.circle.download.api;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.*;

/**
 * Created by keweiyang on 2017/3/10.
 */
public interface ConnectionManager {
    public Connection open(String url) throws ConnectionException, IOException;
}
