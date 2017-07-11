package com.ood.srp.file.impl;

import com.ood.srp.file.FileService;
import com.ood.srp.util.FileUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件处理类
 * Created by yuxia on 2017/6/21.
 */
@Service
public class FileServiceImpl implements FileService {


    /**
     * 获取信息
     *
     * @param filePath
     * @return
     * @throws Exception
     */
    public List<String> readFile(String filePath) throws Exception {
        List<String> lineList = new ArrayList<>();
        FileUtil fileUtil = new FileUtil(filePath);
        while (fileUtil.hasNext()) {
            String s = fileUtil.readLine();
            lineList.add(s);
        }
        fileUtil.close();
        return lineList;
    }
}
