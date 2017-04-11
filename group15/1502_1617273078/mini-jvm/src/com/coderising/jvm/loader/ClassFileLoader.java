package com.coderising.jvm.loader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class ClassFileLoader {

  private List<String> clzPaths = new ArrayList<String>();

  public byte[] readBinaryCode(String className) {
      File file = new File(className);

      List list = new ArrayList<Byte>();
      FileInputStream s = null;
      try {
          s = new FileInputStream(file);
      } catch (FileNotFoundException e) {
          e.printStackTrace();
      }
      byte[] buffer = new byte[1024];
      int len = 0;
      try {
          while ((len = s.read(buffer)) != 1) {
              if (len < 0) {
                  break;
              }else {
                  for (int i = 0; i <len ; i++) {
                      list.add(buffer[i]);
                  }

              }
          }
      } catch (IOException e) {
          e.printStackTrace();
      }
      byte[] bytes = new byte[list.size()];
      for (int i = 0; i <list.size() ; i++) {
          bytes[i] = (byte) list.get(i);
      }

      return bytes;



  }




  public void addClassPath(String path) {
    clzPaths.add(path);
  }



  public String getClassPath(){
    //StringBuffer stringBuffer = null;
    String temp = null;
    for (int i = 0; i < clzPaths.size(); i++) {
      if (i == 0) {
        temp = clzPaths.get(i);
      }else {
        temp = temp + clzPaths.get(i);
      }
      //stringBuffer.append(temp);
      if (i == clzPaths.size() - 1) {
        break;
      }else {
        //stringBuffer.append(";");
        temp = temp + ";";
      }
    }
    return temp;
  }





}