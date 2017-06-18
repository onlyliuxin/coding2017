/**********************************************************************************************************************
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.                                       *
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.                        *
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.                                                   *
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.                     *
 * Vestibulum commodo. Ut rhoncus gravida arcu.                                                                       *
 **********************************************************************************************************************/

package com.coderising.ood.srp;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class PromotionMail
{
    private static final String NAME_KEY  = "NAME";
    private static final String EMAIL_KEY = "EMAIL";
    private static Configuration config;
    private final FileUtil fileUtil      = new FileUtil();
    protected     String   sendMailQuery = null;
    protected     String   smtpHost      = null;
    protected     String   altSmtpHost   = null;
    protected     String   fromAddress   = null;
    protected     String   toAddress     = null;
    protected     String   subject       = null;
    protected     String   message       = null;
    protected     String   productID     = null;
    protected     String   productDesc   = null;
    private boolean emailDebug;

    public PromotionMail( File file, boolean mailDebug ) throws Exception
    {
        this.emailDebug = mailDebug;
        readProductInfos( file );
        configuringEMAILSetting();
        setLoadQuery();
        List mailingList = loadMailingList();
        sendEMails( mailDebug, mailingList );
    }

    private void readProductInfos( File file ) throws IOException
    {//读取配置文件， 文件中只有一行用空格隔开， 例如 P8756 iPhone8
        String[] productInfos = fileUtil.readFile( file );
        setProductID( productInfos[ 0 ] );
        setProductDesc( productInfos[ 1 ] );
        System.out.println( "产品ID = " + productID + "\n" );
        System.out.println( "产品描述 = " + productDesc + "\n" );
    }

    private void configuringEMAILSetting()
    {
        config = new Configuration();
        setSMTPHost();
        setAltSMTPHost();
        setFromAddress();
    }

    protected void setLoadQuery() throws Exception
    {
        sendMailQuery
                = "Select name from subscriptions " + "where product_id= '" + productID + "' " + "and send_mail=1 ";
        System.out.println( "loadQuery set" );
    }

    protected List loadMailingList() throws Exception
    {
        return DBUtil.query( this.sendMailQuery );
    }

    protected void sendEMails( boolean debug, List mailingList ) throws IOException
    {
        System.out.println( "开始发送邮件" );
        if ( mailingList != null )
        {
            Iterator iter = mailingList.iterator();
            while ( iter.hasNext() )
            {
                configureEMail( ( HashMap ) iter.next() );
                try
                {
                    if ( toAddress.length() > 0 )
                    {
                        MailUtil.sendEmail( toAddress, fromAddress, subject, message, smtpHost, debug );
                    }
                }
                catch ( Exception e )
                {
                    try
                    {
                        MailUtil.sendEmail( toAddress, fromAddress, subject, message, altSmtpHost, debug );
                    }
                    catch ( Exception e2 )
                    {
                        System.out.println( "通过备用 SMTP服务器发送邮件失败: " + e2.getMessage() );
                    }
                }
            }
        }
        else
        {
            System.out.println( "没有邮件发送" );
        }

    }

    private void setProductID( String productID )
    {
        this.productID = productID;
    }

    private void setProductDesc( String productDesc )
    {
        this.productDesc = productDesc;
    }

    protected void setSMTPHost()
    {
        smtpHost = config.getProperty( ConfigurationKeys.SMTP_SERVER );
    }

    protected void setAltSMTPHost()
    {
        altSmtpHost = config.getProperty( ConfigurationKeys.ALT_SMTP_SERVER );
    }

    protected void setFromAddress()
    {
        fromAddress = config.getProperty( ConfigurationKeys.EMAIL_ADMIN );
    }

    protected void configureEMail( HashMap userInfo ) throws IOException
    {
        toAddress = ( String ) userInfo.get( EMAIL_KEY );
        if ( toAddress.length() > 0 )
        {
            setMessage( userInfo );
        }
    }

    protected void setMessage( HashMap userInfo ) throws IOException
    {
        String name = ( String ) userInfo.get( NAME_KEY );
        subject = "您关注的产品降价了";
        message = "尊敬的 " + name + ", 您关注的产品 " + productDesc + " 降价了，欢迎购买!";
    }

    public static void main( String[] args ) throws Exception
    {
        File productPromotionFile = new File(
                "D:\\02_workspace\\myproject\\coding2017\\students\\511134962\\ood-assignment\\src\\main\\java\\com\\coderising\\ood\\srp\\product_promotion.txt" );
        boolean       emailDebug = false;
        PromotionMail pe         = new PromotionMail( productPromotionFile, emailDebug );
    }

    protected String getproductID()
    {
        return productID;
    }
}
