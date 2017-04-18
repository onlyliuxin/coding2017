package miniJVM;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * @author : 温友朝
 * @date : 2017/4/18
 */
public class testEmployee {
    String path = "resources/classes/EmployeeV1.class";
    File file;

    @Before
    public void init(){
        try{
            file = new File(path);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void readCafebabe(){
        //JVM第一周作业，读取class文件的头四位魔数cafebabe

        try{
            int length = new Long(file.length()).intValue();
            byte[] buffer = new byte[length];
            DataInputStream dis = new DataInputStream(new FileInputStream(file));
            dis.read(buffer);
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < length; i++){
                int temp = buffer[i] & 0xFF;
                String hexStr = Integer.toHexString(temp);
                if(hexStr.length() < 2){
                    sb.append("0").append(hexStr);
                }else{
                    sb.append(hexStr);
                }

//                if((i + 1) % 2 == 0){
//                    sb.append(" ");
//                }

                if((i + 1) % 16 == 0){
                    sb.append("\n");
                }

            }
            System.out.println(sb.toString());
            Assert.assertEquals("cafebabe", sb.substring(0, 8));

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
