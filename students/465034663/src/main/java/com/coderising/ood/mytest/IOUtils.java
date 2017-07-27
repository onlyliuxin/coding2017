package com.coderising.ood.mytest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Arthur on 2017/6/17.
 */
public class IOUtils {

    protected static String[] readFile(File file) throws IOException // @02C
    {
        BufferedReader br = null;

        try {

            br = new BufferedReader(new FileReader(file));
            String temp = br.readLine();
            String[] data = temp.split(" ");
            return data;
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        } finally {
            br.close();
        }
    }

}
