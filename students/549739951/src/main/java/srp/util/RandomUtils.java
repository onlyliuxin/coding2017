package srp.util;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Random;

/**
 * @version V1.0
 * @Title： RandomUtils
 * @Package： srp.util
 * @Description： 随机工具类
 * @author： 南来
 * @date： 2017-06-12 13:04
 */
public class RandomUtils {

    public static boolean randomBoolean() {
        return new Random().nextBoolean();
    }

    public static Exception randomException() {
        if (RandomUtils.randomBoolean())
            return new RuntimeException();
        return null;
    }

    public static String randomMail() {
        return randomNumber(9) + "@qq.com";
    }

    public static <T> T randomOne(List<T> list) {
        return list.get(new Random().nextInt(list.size()));
    }

    public static String randomNumber(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++)
            sb.append(new Random().nextInt(10));
        return sb.toString();
    }

    public static String randomName() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            int highCode, lowCode;

            highCode = (176 + Math.abs(new Random().nextInt(39)));
            lowCode = (161 + Math.abs(new Random().nextInt(93)));

            byte[] b = new byte[]{(Integer.valueOf(highCode)).byteValue()
                    , (Integer.valueOf(lowCode)).byteValue()};

            try {
                sb.append(new String(b, "GBK"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
