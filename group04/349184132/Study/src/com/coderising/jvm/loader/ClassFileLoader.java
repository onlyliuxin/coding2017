package com.coderising.jvm.loader;

/**
 * Created by wang on 2017/3/27.
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;



public class ClassFileLoader {

    private List<String> clzPaths = new ArrayList<String>();
    private static final int BUFFER_LENGTH = 1024;
    public byte[] readBinaryCode(String className) {

        File clzFile = new File(getClzPath(className));
        FileInputStream fis = null;
        ByteArrayOutputStream bos = null;
        try {
            fis = new FileInputStream(clzFile);
            bos = new ByteArrayOutputStream();
            int len = 0;
            byte[] buf = new byte[BUFFER_LENGTH];
            while(-1 !=(len = fis.read(buf))){
                bos.write(buf,0,len);
            }
            bos.flush();
            return bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(bos!=null){
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }if(fis!=null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;


    }

    private String getClzPath(String className) {
        String clzPath = this.getClassPath() +
                className.replace(".","\\") +
                ".class";
        return clzPath;
    }




    public void addClassPath(String path) {
        if(path==null){
            throw new IllegalArgumentException();
        }
        clzPaths.add(path);
    }



    public String getClassPath(){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<clzPaths.size(); i++ ){
            sb.append(clzPaths.get(i));
            if(i<clzPaths.size()-1){
                sb.append(";");
            }
        }

        return sb.toString();

    }





}