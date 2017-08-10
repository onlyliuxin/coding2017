import dp.decorator.Email;
import dp.decorator.EmailImpl;
import dp.decorator.EncryptionEmailImpl;
import dp.decorator.OutsideEmailImpl;
import org.junit.Test;

/**
 * Created by lx on 2017/7/29.
 */
public class EmailTest {
    @Test
    public void testEmail() {
        Email e = new OutsideEmailImpl(new EncryptionEmailImpl(new EmailImpl("内容\n")));
        System.out.println(e.getContent());
    }

}
