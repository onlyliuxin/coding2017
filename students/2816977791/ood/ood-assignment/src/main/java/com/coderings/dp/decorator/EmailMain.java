package com.coderings.dp.decorator;

/**
 * @author nvarchar
 *         date 2017/7/28
 */
public class EmailMain {
    public static void main(String[] args) {
        Email stateEmail = new StatementEmailImpl(new EmailImpl("发邮件"));
        Email encryptEmail = new EncryptEmailImpl(new EmailImpl("发邮件"));

        System.out.println(stateEmail.getContent());
        System.out.println(encryptEmail.getContent());
    }
}
