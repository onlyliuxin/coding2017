package com.ood.srp.util;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 文件操作类。
 * 负责文件句柄的维护。
 * 此类会打开促销文件，促销文件默认按照：
 * "id" 空格 "产品名称"
 * 进行存储，每行一条促销信息。
 *
 * @since 06.19.2017
 */
public class FileUtil {
    private Scanner scanner;


    public FileUtil(String filePath) throws FileNotFoundException {
        scanner = new Scanner(new File(filePath));
    }

    public String readLine() {
        String wholeInfo = scanner.nextLine();
        return wholeInfo;
    }

    public boolean hasNext() {
        return scanner.hasNextLine();
    }

    public void close() {
        scanner.close();
    }
}
