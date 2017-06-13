package srp.util;

import srp.model.Email;

/**
 * @version V1.0
 * @Title： MailUtil
 * @Package： srp.util
 * @Description： 邮件工具类
 * @author： 南来
 * @date： 2017-06-12 10:50
 */
public class MailUtil {

    /**
     * 模拟发送 随机失败
     *
     * @param email      email
     * @param serverHost 邮件服务配置
     * @return 成功失败
     */
    public static boolean send(Email email, String serverHost) {
        return RandomUtils.randomBoolean();
    }
}
