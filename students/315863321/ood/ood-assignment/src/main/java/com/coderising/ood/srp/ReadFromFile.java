package com.coderising.ood.srp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by john on 2017/6/13.
 */
public class ReadFromFile  extends Reader{


    public ReadFromFile(File file) {
        super(file);
    }

    List read() {
        BufferedReader br = null;
        List data = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String temp = br.readLine();
            data = Arrays.asList(temp.split(" "));
        } catch (IOException e) {
            try {
                throw new IOException(e.getMessage());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("产品ID = " + data.get(0) + "\n");
        System.out.println("产品描述 = " + data.get(1) + "\n");
        return data;
    }
}
