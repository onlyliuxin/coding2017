package main.coding_170327.loader;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by peter on 2017/4/20.
 */
public class ClassFileLoaderTest extends TestCase {
    public static final String FULL_QUALIFIED_CLASS_NAME = "main/coding_170327/Employee";
    static String path1 = "E:\\IDEASpace\\coding2017\\group11\\729245768\\DataStructure\\tests";
    static String path2 = "C:\temp";

    @Test
    public void testClassPath(){
        ClassFileLoader loader = new ClassFileLoader();
        loader.addClassPath(path1);
        loader.addClassPath(path2);
        Assert.assertEquals(path1+";"+path2,loader.getClassPath());
    }
    @Test
    public void testClassFileLength(){
        ClassFileLoader loader = new ClassFileLoader();
        loader.addClassPath(path1);
        String className = "main.coding_170327.Employee";
        byte[] codes = loader.readBinaryCodes(className);
        Assert.assertEquals(891,codes.length);
    }
    @Test
    public void testMagicNumber(){
        ClassFileLoader loader = new ClassFileLoader();
        loader.addClassPath(path1);
        String className = "main.coding_170327.Employee";
        byte[] codes = loader.readBinaryCodes(className);
        byte[] magicNumbers = new byte[]{codes[0],codes[1],codes[2],codes[3]};
        String magic = byteToHexString(magicNumbers);
        Assert.assertEquals("cafebabe",magic);
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