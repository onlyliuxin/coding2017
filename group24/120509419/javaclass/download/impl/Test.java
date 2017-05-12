/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaclass.download.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author CJ
 */
public class Test {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        File curFile = new File("http://www.baidu.com");
        RandomAccessFile raf = new RandomAccessFile(curFile,"r");
        System.err.println(raf.length());
    }
}
