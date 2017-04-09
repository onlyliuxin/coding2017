# 第二周博客 关于Java的反射机制

## 前言

​	自从毕业以来，逐渐的接触了Java的开发。虽然也接触了两年有余吧，但总是停留在写一些基础业务流程方面。在工作中，长此以往，也是如此了。所以也是给自己一个机会来了解一些进阶的东西来充实自己。

​	对于Java的反射机制，我不会用，也不懂的原理，不敢随意妄言，只是简单记录一下本次对于Java反射作业的认识，同时梳理一下自己的思路，为了日后更加深刻的了解做一些铺垫吧。

## 一、结构梳理

​	本次的作业是一个轻量级的Struts框架的模拟。实际上来说，以前倒是做过一次，只是时间有点久远，或许是没有及时的温习巩固，后来就慢慢的忘记了。此次再次进行模拟，也是参考之前的程序进行编写的。

​	作为一个轻量级的Struts框架，老师给出了一个思路。这个思路是

1.   通过传入actionName与参数来使用Struts框架，生成一个View，这个View可以用来获取Html页面与传递数据。

2.   Struts框架生成View是通过读取xml配置文件来进行生成的。


     根据这个思路来看的话，我们需要：

     * 一个View来展示与保存结果。
     * 一个Struts的框架来利用反射机制，生成结果。
     * 一个xml的配置文件来供Struts进行读取。
     * 还需要一个Action类书写业务流程，为Struts的反射提供实体。
     * 一个Test用于测试，这个不是核心的类了就是了。

     而我们看到的作业的初始框架也就分为这几部分了。这些类的运行过程就是Struts根据参数来读取xml文件，利用反射生成Action类，并运行其中固定的函数，并将结果封装成View返回。



## 二、反射的使用

代码示例：

~~~java
//反射实例化一个对象
Object action = Class.forName(actionClass).newInstance();

//调用exectue
Method exectue = action.getClass().getDeclaredMethod("execute");
String result = (String)exectue.invoke(action);

//生成返回列表
Map<String, String> actionParam = new HashMap<>();
Method[] methods = action.getClass().getDeclaredMethods();
for (Method method : methods) {
  if (method.getName().startsWith("get")) {
    String methodName = Tools.lowerFirst(method.getName().substring(3));
    String methodValue = (String)method.invoke(action);
    actionParam.put(methodName, methodValue);
  }
}
~~~

### 2.1 反射实例化一个对象

​	反射实例化一个对象是使用``` Object obj = Class.forName("className").newInstance``` 来实现的。通过这种方式，我们可以通过类名（完整的，带包名的字符串）来新建一个实例。可以通过字符串来新建实例也就意味着我们可以通过动态的方式来**new** 一个对象，提供了很大的灵活性。

### 2.2 反射调用实例中的方法

​	通过反射调用是使用``` Method method = obj.getClass().getDeclaredMethod("methodName")``` 来获取方法体，并使用``` method.invoke(obj);//obj为一个实例``` 通过这种方式来执行一个函数。

​	这个虽然很灵活，但我用的不是很多，想来如何向函数中传递参数以及如何强制执行私有函数，还有如何防止这种反射机制我还未了解，有时间研究一下，再进行相应的更新。

### 2.3 获取方法列表

​	可以通过``` methods = obj.getClass().getDeclaredMethods()``` 来获取实例中所有的方法。

​	不知道有么有方法可以往实例中注入一些方法呢？

### 2.4 这些可以做什么

​	这些功能这么灵活，那么应该如何使用它们呢？是不是可以开发一个自由度特别特别高的程序呢？感觉上是可以的吧，就像是一个更专业向的框架一样。是不是一个好的想法呢~



## 三、POI的XML文件读取

​	这次作业，感觉比较让人头大的应该是xml文件的读取了，但是只是属于比较繁琐，但是也比较好理解的范畴，随用随查即可，不像反射那样可以有灵活的运用，所以就不做重点的介绍。

