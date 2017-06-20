package com.coderising.ood.srp;

/**
 * Created by john on 2017/6/14.
 */
public abstract class Build {
    Reader reader = null;

    abstract void build();

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }
}
