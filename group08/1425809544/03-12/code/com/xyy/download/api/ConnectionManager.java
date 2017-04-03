package xyy.download.api;

import vvv.download.api.ConnectionException;

import java.io.IOException;

/**
 * Created by 14258 on 2017/3/14.
 */
public interface ConnectionManager {


    /**
     * 给定一个url , 打开一个连接
     *
     * @param url
     * @return
     */
    public Connection open(String url) throws ConnectionException, IOException;





}
