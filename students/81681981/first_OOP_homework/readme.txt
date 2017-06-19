作业说明

类及功能
1.conifgurationKeys.java 邮件发送服务器配置信息
2.新增User.java  对应订阅用户信息（用户名，邮箱） 
3.修改DBUtil.java 数据库操作类，把键值 修改为User对象
4.新增Email.java 维护邮件发送的前置信息和邮件标题，邮件内容，smtphost,altSmtpHost,fromAddress,subject,message
5.新增Product.java 维护产品信息及订阅用户
6.新增Message.java　维护要发送的内容
7.修改PromotionMail.java 功能：读取产品信息，组织要发送的内容封装为Message对象，获取该产品对应的订阅用户，调用MailUtil.java 发送邮件
8.MailUtil.java 负责发送