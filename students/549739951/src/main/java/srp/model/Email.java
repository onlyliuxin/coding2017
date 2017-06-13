package srp.model;

/**
 * @version V1.0
 * @Title： Email
 * @Package： srp.model
 * @Description： Email 对象
 * @author： 南来
 * @date： 2017-06-12 10:32
 */
public class Email {

    /**
     * from address
     */
    private String from;
    /**
     * to address
     */
    private String to;
    /**
     * 主题
     */
    private String subject;
    /**
     * 内容
     */
    private String content;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Email{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
