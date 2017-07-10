package cn.net.pikachu.srp.base;

/**
 * 邮件内容模板
 */
public abstract class MailContentTemplate {

    /**
     * Default constructor
     */
    public MailContentTemplate() {
    }


    /**
     * 渲染成字符串
     * @return
     */
    public abstract String render();

}