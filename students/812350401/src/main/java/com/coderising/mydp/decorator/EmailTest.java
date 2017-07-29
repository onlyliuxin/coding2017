package com.coderising.mydp.decorator;

import com.coderising.mydp.utils.Encryptor;
import org.junit.Test;

/**
 * Created by thomas_young on 25/7/2017.
 */
public class EmailTest {
    private Email email = new EmailImpl("Hello World!");
    private String key = "Bar12345Bar12345"; // 128 bit key
    private String initVector = "RandomInitVector"; // 16 bytes IV

    @Test
    public void testSendToOut() {
        Email emailSendOut = new EmailSendOut(email);
        System.out.println(emailSendOut.getContent());
    }

    @Test
    public void testEncrypt() {
        Email emailEncrypt = new EmailEncrypt(key, initVector, email);
        String encryptedContent = emailEncrypt.getContent();
        System.out.println("encrypted content: " + encryptedContent);
        System.out.println(Encryptor.decrypt(key, initVector, encryptedContent));
    }

    @Test
    public void testEncryptSendOut() {
        Email emailsendOutEncrypt = new EmailSendOut(new EmailEncrypt(key, initVector, email));
        System.out.println(emailsendOutEncrypt.getContent());
        System.out.println();

        Email emailEncryptSendOut  = new EmailEncrypt(key, initVector, new EmailSendOut(email));
        String encryptedContent = emailEncryptSendOut.getContent();
        System.out.println("encrypted content: " + encryptedContent);

        System.out.println();
        System.out.println(Encryptor.decrypt(key, initVector, encryptedContent));

    }
}
