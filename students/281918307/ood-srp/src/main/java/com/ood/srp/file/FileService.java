package com.ood.srp.file;

import java.io.File;
import java.util.List;

/**
 * 读取文件内容
 * Created by ajaxfeng on 2017/6/20.
 */
public interface FileService {

    /**
     * 读取文件内容，放到List内
     * @param file
     * @return
     */
    List<String> readFile(File file);
}
