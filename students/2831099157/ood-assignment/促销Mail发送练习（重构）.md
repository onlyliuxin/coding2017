# 第一次OOD练习 #

## 需求 ##
### 原项目已经实现根据产品列表文件发送促销Mail，需求一直在变化，比如通过数据库获取促销产品或者促销活动改为到货通知，抢购等；为了应变各种变化，需重构代码。 ###
## 需求分析 ##
需求可变因素:</br>

1. 促销产品列表文件可能会变更，或者变更获取方式（如通过数据库获取）
2. 促销活动会根据运营情况变更
## 重构方案 ##
1. 提取GetProductsFunction，SendMailFunction接口
2. 添加Notice抽象类，针对接口添加getProducts,sendMai方法 -------各类通知（降价促销，抢购活动，到货通知等等）
3. 添加Mail,Product,User实体类
4. Mail初始化设置smtpHost,alSmtpHost,fromAddress参数，添加sendMail功能
5. Product添加getSubscribers功能
6. 添加GetProductsFunction，SendMailFunction接口实现类
7. 添加PricePromotion继承Promotion，实现降价促销功能，实例化GetProductsFunction，SendMailFunction接口    
8. 主函数调用sendMail方法

## 重构后 ##
重构后项目，可以根据不同运营方案，添加GetProductsFunction，SendMailFunction接口实现类及Notice子类，向订阅者发送通告


