package com.coderising.ood.ocp.mine;

/**
 * Created by guodongchow on 2017/6/21.
 */
public class Logger {

    private Fomatter fomatter;
    private Processor processor;

    public Logger(Fomatter fomatter, Processor processor) {
        this.fomatter = fomatter;
        this.processor = processor;
    }

    public void log(String message){
        processor.process(fomatter.format(message));
    }
}
