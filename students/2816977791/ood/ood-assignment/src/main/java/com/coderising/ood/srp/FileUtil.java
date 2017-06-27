package com.coderising.ood.srp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author nvarchar
 *         date 2017/6/26
 */
public class FileUtil {

    protected static String[] readFile(File file) throws IOException // @02C
    {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String temp = br.readLine();
            return temp.split(" ");

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        } finally {
            br.close();
        }
    }
}
