package com.coderising.litestruts.exception;

/**
 * Created by huitailang on 17/3/5.
 * @author  zhangkun
 * @date 2017年03月05日17:46:19
 * 构建XML Document抛出异常
 */
public class BuilderException extends RuntimeException {
    public BuilderException(){
        super();
    }

    public BuilderException(String message){
        super(message);
    }

    public BuilderException(String message, Throwable cause){
        super(message, cause);
    }

    public BuilderException(Throwable cause){
        super(cause);
    }
}
