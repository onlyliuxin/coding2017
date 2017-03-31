package com.coderising.download.api;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

public class ConnectionException extends Exception {

    public ConnectionException (String msg) {
        super(msg);
    }

    public ConnectionException (MalformedURLException e) {
        super(e);
    }

    public ConnectionException (IOException e){
        super(e);
    }

    public ConnectionException (FileNotFoundException e){
        super(e);
    }

    public ConnectionException (InterruptedException e){
        super(e);
    }


}
