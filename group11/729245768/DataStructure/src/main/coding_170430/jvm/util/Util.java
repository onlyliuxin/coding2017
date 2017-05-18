package main.coding_170430.jvm.util;

/**
 * Created by peter on 2017/4/21.
 */
public class Util {
    public static int byteToInt(byte[] codes){
        String s1 = byteToHexString(codes);
        return Integer.valueOf(s1, 16).intValue();
    }

    public static String byteToHexString(byte[] codes){
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<codes.length;i++){
            byte b = codes[i];
            int value = b &0xFF;
            String strHex = Integer.toHexString(value);
            if(strHex.length()<2){
                strHex = "0"+strHex;
            }
            sb.append(strHex);
        }
        return sb.toString();
    }
}
