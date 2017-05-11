package com.coding.week3.download1.api;

import java.io.IOException;

public class ConnectionException extends IOException {
    public ConnectionException() {
    }

    public ConnectionException(String message) {
        super(message);
    }
}
