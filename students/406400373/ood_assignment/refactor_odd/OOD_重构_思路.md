整个发送的流程主要是以下步骤
1. 从配置文件中读取，生成要发送的产品信息。
2. 设置邮件发送需要的SMTPHOST,ALTSMTPHOST,FROMADDRESS
3. 查询出需要发送的用户列表
4. 设置发送邮件需要的 信息，从用户列表中读取要发送去的地址，用产品信息拼凑出Message
5. 发送邮件。
其实Promotion就是一个job，衔接各个Handler，不自己做逻辑，完成发信的一个任务。

所以我理解首先可以独立出来的一堆职责就是邮件发送的类，它能够被初始化，然后接受参数发送邮件。

所以首先抽取出的职责就是和邮件发送相关的职责。EmailHandler。
smtphost和altSmtpHost是定死的，应该设定成系统初始化时就设定好，至于From address在发送企业推送邮件时，也是统一的，也考虑封装在EmailHandler内部。
在初始化一个EmailHandler时，即完成了邮件系统的相关设置。
然后将发信的逻辑封装在EmailHandler内部。
然后在PromotionMail中移除邮件系统相关的配置，引入EmailHandler。
现在所有和发送相关以及邮件系统初始化的逻辑，全部移动到了EmailHandler内部。
```java
public PromotionMail(File file, boolean mailDebug) throws Exception {
		// 读文件，获得要推送的商品信息
		readFile(file);
		// 查询用户
		setLoadQuery();
		// 初始化邮件发送Handler
		EmailHandler emailHandler = new EmailHandler();
		// 对私有函数增加一个参数。
		sendEMails(mailDebug, loadMailingList(), emailHandler);
	}
```

ok，接下来，邮件发送需要的主体信息是来自外部的，但它可以是一个纯数据类，存放着发送者，主题，message，一个pojo类，sendEMails应该只需要接受pojo的list，然后直接调emailHandler的发送函数即可，而不需要在里面还要进行取值的操作。
```java
		configureEMail((HashMap) iter.next());
	    boolean result = emailHandler.sendMail(toAddress, subject, message, debug);
```
一个简单的pojo类，emailHandler应该接受这个作为参数，修改代码如下
```java
  private String fromAddress;
    private String toAddress;
    private String subject;
    private String message;
```
新的发送邮件的函数
```java
private void sendEMails(boolean debug, List<EmailEntity> emailEntities, EmailHandler emailHandler)
			throws IOException {

		System.out.println("开始发送邮件");
		if (CollectionUtils.isNotEmpty(emailEntities)) {
			for (EmailEntity emailEntity : emailEntities) {
				boolean result = emailHandler.sendMail(emailEntity, debug);
				System.out.println("发送邮件结果: " + result);
			}
		} else {
			System.out.println("没有邮件发送");
		}
	}
```
emailHandler直接和emailEntity交互。
接下来就是说我们怎么去构造emailEntities这个list了。
然后这个商品信息的文件读取出来是一个list，首先对原有的读文件函数进行改动，用一个pojo类去代表商品的信息。同样的，用户的返回我们也用一个pojo类存储，因为这些东西以后的加字段可能是比较高的，通过一个统一的实体来管理会比较好。
然后在job类里少用this，通过传参的方式，首先改变查询用户的函数，传入参数查询语句，返回的是user的list。
分别构造出用来查询商品和用来处理用户的私有方法，准备下一步的重构。
将查询商品相关的处理，封装到ProductHandler内部，只对外暴露获得商品列表的接口。
```java
// 查询商品
List<Product> products = new ProductHandler().fetchProducts();
```
用户是根据订阅了商品的来查的，所以处理用户的UserHandler接受一个sql语句查询，和商品之间解耦。

最终总结就是通过三个处理器负责商品，用户，邮件的处理，原有的PromotionMail则相当于是衔接各个处理器的job，具体的业务逻辑放在各个handler内部完成处理。

重构完毕。
