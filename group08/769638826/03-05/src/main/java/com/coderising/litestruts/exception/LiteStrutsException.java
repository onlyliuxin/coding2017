package com.coderising.litestruts.exception;

/**
 * Created by huitailang on 17/3/5.
 * @author  zhangkun
 * @date 2017年03月05日17:46:28
 * LiteStrutsException
 */
public class LiteStrutsException extends RuntimeException{
    public LiteStrutsException(){
        super();
    }

    public LiteStrutsException(String message){
        super(message);
    }

    public LiteStrutsException(String message, Throwable cause){
        super(message, cause);
    }

    public LiteStrutsException(Throwable cause){
        super(cause);
    }
}
