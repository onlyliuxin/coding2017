package com.coderising.ood.srp;

import java.io.File;
import java.util.List;

/**
 * Created by john on 2017/6/14.
 */
public abstract class Reader {
    File file;

    public Reader(File file) {
        this.file = file;
    }

    public Reader() {

    }

    abstract List read();

}
