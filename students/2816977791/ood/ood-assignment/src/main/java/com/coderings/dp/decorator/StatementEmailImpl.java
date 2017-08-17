package com.coderings.dp.decorator;

/**
 * @author nvarchar
 *         date 2017/7/26
 */
public class StatementEmailImpl extends EmailDecorator {

    public StatementEmailImpl(Email email) {
        super(email);
    }

    public String getContent(){
        String state = "本邮件仅代表个人立场，不代表公司立场;";
        return email.getContent() + state;
    }
}
