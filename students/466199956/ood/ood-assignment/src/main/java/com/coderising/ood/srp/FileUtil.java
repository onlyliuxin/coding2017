package com.coderising.ood.srp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Dell on 2017/6/15.
 */
public class FileUtil {
    public static String readFile (File file)  throws IOException{

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            return br.readLine();

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        } finally {
            br.close();
        }
    }
}
