package com.coderising.jvm.test;

/**
 * Created by wang on 2017/3/29.
 */
public class TestBytetoHex {

    public String byteToHexString(byte[] codes ){
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

    public static void main(String[] args) {
        TestBytetoHex t = new TestBytetoHex();
        byte[] b =  {-54};
//        byte[] c = {0xca,0xcf}
        String s = t.byteToHexString(b);
        System.out.println(s);
    }
}
