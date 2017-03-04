##  2017编程提高社群作业：数据结构习题、实现简单的类似于struts的操作(2017.2.19)

- ### [数据结构习题Coding](https://github.com/china-kook/coding2017/blob/master/group18/935542673/Coding/20170226/src/com/ikook/array/ArrayUtil.java) 

  - 实现给定一个整形数组,  对该数组的值进行置换。关键代码如下：

    ```java
    for (int i = 0; i < size; i++) {
    	newArray[i] = origin[size - i - 1];
    }
    for (int i = 0; i < size; i++) {
    	origin[i] = newArray[i];
    }
    ```

  - 实现给定一个整形数组,  删除该数组中指定元素并存入一个新数组。关键代码如下：

    ```java
    for(i = 0; i < oldArray.length; i++) {
    	if(oldArray[i] != 0) {
    		tempArray[j] = oldArray[i];
    		j++;
    	}
    }		
    int[] newArray = new int[j];
    for (int x = 0; x < j; x++) {
    	newArray[x] = tempArray[x];
    }
    ```

  - 实现给定两个已排序的整形数组: a1和a2,  创建一个新的数组a3, 使得新数组包含a1、a2的所有元素，并仍然是有序的，并消除重复元素。关键代码如下：

    ```java
    // 冒泡排序
    int temp = 0;
    for (int i = array3.length - 1; i > 0; --i) {
    	for (int j = 0; j < i; ++j) {
    		if (array3[j] > array3[j + 1]) {
    			temp = array3[j];
    			array3[j] = array3[j + 1];
    			array3[j + 1] = temp;
    		}
    	}
    }
    // 去掉重复元素
    int x = 0;
    for (int i = 1; i < length; i++) {
    	if (array3[i] != array3[x]) {
    		array3[++x] = array3[i];
    	}
    }
    ```

  - 实现对一个已经存满数据的数组进行扩容。关键代码如下：

    ```java
    int oldLength = oldArray.length;
    int newLength = oldLength + size;
    int[] newArray = new int[newLength];
    for (int i = 0; i < oldLength; i++) {
    	newArray[i] = oldArray[i];
    }
    ```

  - 实现给定一个最大值，返回小于该值得斐波那契数列组成的数组。关键代码如下：

    ```java
    int[] fibo = { 1, 1 };
    int last = 2;
    for (int length = 2; last < max;) {
    	fibo = grow(fibo, 1);
    	length++;
    	fibo[length - 1] = fibo[length - 2] + fibo[length - 3];
    	last = fibo[length - 1] + fibo[length - 2];
    }
    ```

  - 实现返回小于给定最大值 max 的所有素数数组。关键代码如下：

    ```java
    int length = 1;
    int[] perimes = { 2 };
    for (int number = 3; number < max; number++) {
    	if (isPrimesNumber(number)) {
    		perimes = grow(perimes, 1);
    		perimes[length++] = number;
    	}
    }
    ```

  - 实现返回一个数组， 数组中是小于给定最大值 max 的所有完数。关键代码如下：

    ```java
    int length = 0;
    int[] perfect = {};
    for(int number = 6; number < max; number++) {
    	if(isPerfectNumber(number)) {
    		perfect = grow(perfect, 1);
    		perfect[length++] = number;
    	}
    }
    ```

  - 实现用 seperator 把数组 array给连接起来。关键代码如下：

    ```java
    String s = array[0] + "";
    for (int i = 1; i < array.length; i++) {
    	s += seperator + array[i];
    }
    ```

- ### [简单的类似于struts的操作](https://github.com/china-kook/coding2017/tree/master/group18/935542673/Coding/20170226/src/com/ikook/litestruts)

  使用 dom4j 的 SAXReader 类读取 struts.xml 文件，并对读取的文件中元素进行逐一处理。关键代码太多，基本整个类都是，点击本二级标题进行查看。

- ### 数据结构习题以及 Struts 测试

  - ##### [数据结构习题 Test Coding](https://github.com/china-kook/coding2017/blob/master/group18/935542673/Coding/20170226/junit/com/ikook/array/ArrayUtilTest.java)

  - ##### [Struts Test Coding](https://github.com/china-kook/coding2017/blob/master/group18/935542673/Coding/20170226/junit/com/ikook/litestruts/StrutsTest.java)





