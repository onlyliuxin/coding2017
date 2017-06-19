package srp.refactor.util;

/**
 * Created by Tee on 2017/6/16.
 */
public class Constants {
    public enum EmailInfo{
        TO_ADDRESS_KEY("toAddress"),
        SUBJECT_KEY("subject"),
        MESSAGE_KEY("message");

        private String key;
        EmailInfo(String key){
            this.key = key;
        }

        public String getKey(){
            return this.key;
        }
    }
}
