package me.lzb.other.algorithm.consistenthash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author LZB
 * @date 2017/5/11
 */
public class HashFunction {

    /**
     * ketama
     * @param key
     * @return
     */
    public long hash(String key) {
        byte[] bytes = md5(key);
        return ketama(bytes, 0);

    }

    private long ketama(byte[] digest, int nTime) {
        //0xff = 255
        long a = ((long) (digest[3 + nTime * 4] & 0xFF) << 24)
            | ((long) (digest[2 + nTime * 4] & 0xFF) << 16)
            | ((long) (digest[1 + nTime * 4] & 0xFF) << 8)
            | (digest[0 + nTime * 4] & 0xFF);

        return a & 0xffffffffL;
    }

    //输入任意长的信息，输出128位的信息，不可逆，唯一
    private byte[] md5(String key) {
        MessageDigest md5 = null;
        if (md5 == null) {
            try {
                md5 = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }

        md5.reset();
        md5.update(key.getBytes());
        return md5.digest();
    }
}
