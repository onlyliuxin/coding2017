package thread.download;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * Created by macvi on 2017/4/22.
 */
public class ClassFileReader {
    private static final String PATH = "resources/classes/EmployeeV1.class";

    private static File file;

    private static int length = 0;

    private static byte[] buffer;

    private static DataInputStream dis;



    static {
        try {
            file = new File(PATH);
            length = new Long(file.length()).intValue();
            buffer = new byte[length];
            dis = new DataInputStream(new FileInputStream(file));
            dis.read(buffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String hexStr(byte value){
        return Integer.toHexString(value & 0xFF);
    }

    public static String readNextU4Bytes(int startPos) {
        try {
            StringBuilder sb = new StringBuilder();
            for (int i = startPos; i < 4; i++) {
                String hexStr = hexStr(buffer[i]);
                if (hexStr.length() < 2) {
                    sb.append("0").append(hexStr);
                } else {
                    sb.append(hexStr);
                }
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String readNextU2Bytes(int startPos){
        try {
            StringBuilder sb = new StringBuilder();
            for (int i = startPos; i < 2; i++) {
                String hexStr = hexStr(buffer[i]);
                if (hexStr.length() < 2) {
                    sb.append("0").append(hexStr);
                } else {
                    sb.append(hexStr);
                }
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
