/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaclass.download.api;

/**
 *
 * @author CJ
 */
public interface ConnectionManager {

    /**
     * 给定一个url , 打开一个连接
     *
     * @param url
     * @return
     * @throws javaclass.download.api.ConnectionException
     */
    public Connection open(String url) throws ConnectionException;
}
