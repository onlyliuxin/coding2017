/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaclass.download.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaclass.download.api.Connection;
import javaclass.download.api.ConnectionException;
import javaclass.download.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {

    @Override
    public Connection open(String url) throws ConnectionException {

        try {
            URL curUrl = new URL(url);
            ConnectionImpl ConnectionImpl = new ConnectionImpl(curUrl.openConnection());

            return ConnectionImpl;
        } catch (MalformedURLException ex) {
            Logger.getLogger(ConnectionManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ConnectionManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
