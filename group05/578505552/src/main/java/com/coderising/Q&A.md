# Struts中去获得getter或者setter方法的时候怎样比较靠谱？目前有两种思路
## 获取Struts中所有以“get”或“set”开头的方法
### 问题1：可能有不是getter或者setter的方法也是以“get”或者“set”开头
## 先获取Struts中所有的成员变量的名字，再与字符串“get”或“set”拼接出getter或者setter方法名，再通过方法名用反射获取方法
### 问题1：因为拼接方法名肯定以驼峰命名法来拼接，这就要求getter和setter方法也是以这个规则命名，虽然ide自动生成的方法是如此，但不能保存总是如此。