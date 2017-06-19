package main.java.com.coderising.ood.srp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileUtil {

    public static String[] readFile(File file) throws IOException // @02C
    {
        BufferedReader br = null;
        String[] data;
        try {
            br = new BufferedReader(new FileReader(file));
            String temp = br.readLine();
            data = temp.split(" ");
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        } finally {
            br.close();
        }
        return data;
    }
}
