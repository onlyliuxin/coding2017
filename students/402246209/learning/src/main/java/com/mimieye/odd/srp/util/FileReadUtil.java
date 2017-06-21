package com.mimieye.odd.srp.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pierreluo on 2017/6/13.
 */
public class FileReadUtil {

    public static List<String> readFile(String fileName) throws IOException {
        File file = new File(fileName);
        BufferedReader reader = null;
        List<String> results = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                if(results == null){
                    results = new ArrayList<>();
                }
                results.add(tempString);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return results;
    }
}
