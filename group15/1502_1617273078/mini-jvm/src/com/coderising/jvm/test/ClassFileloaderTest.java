package com.coderising.jvm.test;

import org.junit.After;
import  org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.coderising.jvm.loader.ClassFileLoader;

import java.io.IOException;

import static com.sun.xml.internal.ws.dump.LoggingDumpTube.Position.Before;


public class ClassFileloaderTest {


    static String path1 = "C:\\Users\\liuxin\\git\\coding2017\\liuxin\\mini-jvm\\bin";
    static String path2 = "C:\\temp";



    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testClassPath(){

        ClassFileLoader loader = new ClassFileLoader();
        loader.addClassPath(path1);
        loader.addClassPath(path2);
        //System.out.println(loader.si);
        String clzPath = loader.getClassPath();

        Assert.assertEquals(path1+";"+path2,clzPath);

    }

    @Test
    public void testClassFileLength() {

        ClassFileLoader loader = new ClassFileLoader();
        loader.addClassPath(path1);

        String className = "C:\\javaIntellijIDEA\\coding2017\\group15\\1502_1617273078\\mini-jvm\\out\\production\\mini-jvm\\com\\coderising\\jvm\\test\\EmployeeV1.class";

        byte[] byteCodes = loader.readBinaryCode(className);

        // 注意：这个字节数可能和你的JVM版本有关系， 你可以看看编译好的类到底有多大
        Assert.assertEquals(1056, byteCodes.length);

    }


    @Test
    public void testMagicNumber(){
        ClassFileLoader loader = new ClassFileLoader();
        loader.addClassPath(path1);
        String className = "C:\\javaIntellijIDEA\\coding2017\\group15\\1502_1617273078\\mini-jvm\\out\\production\\mini-jvm\\com\\coderising\\jvm\\test\\EmployeeV1.class";
        byte[] byteCodes = loader.readBinaryCode(className);
        byte[] codes = new byte[]{byteCodes[0],byteCodes[1],byteCodes[2],byteCodes[3]};


        String acctualValue = this.byteToHexString(codes);

        Assert.assertEquals("cafebabe", acctualValue);
    }






    private String byteToHexString(byte[] codes ){
        StringBuffer buffer = new StringBuffer();
        for(int i=0;i<codes.length;i++){
            byte b = codes[i];
            int value = b & 0xFF;
            String strHex = Integer.toHexString(value);
            if(strHex.length()< 2){
                strHex = "0" + strHex;
            }
            buffer.append(strHex);
        }
        return buffer.toString();
    }

}
