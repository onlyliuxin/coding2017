package com.coderising.download.api;

/**
 * Created by mortimer on 2017/3/12.
 *
 */
public class FileAlreadyExistsException extends RuntimeException {

    public FileAlreadyExistsException(String path) {
        super("this file[" + path + "] is already exists, please change another file name");
    }

}
