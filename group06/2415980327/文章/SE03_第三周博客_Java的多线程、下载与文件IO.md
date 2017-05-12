# 第三周博客 Java的多线程、下载与文件IO

# 前言

​	Java里面目前三个最让我头疼的地方在这次的作业中一次性的全都出现了。虽然是一个专科出身的程序员，但是自认为在Java上对于这方面的掌握应该是远远不及经历过培训的同学们的。这是自己的一大弱点，对于一个新的技术，不能系统的去进行学习与研究。对于此，只能是慢慢地区梳理与使用才是了。

​	对于本次的作业，显得异常的吃力，最后也是只能等到老师的讲解视频出来之后才慢慢跟着老师的思路去编码，所以再次的梳理一次程序，让自己有一个更加清晰的概念吧。

## 一、总体结构

​	本次的作业被分为了三个包

* download

* download.api

* download.impl

  在API接口中，有四个接口文件，其中异常接口未被实现，其他三个，分别是Connection，ConnectionManager和DownloadListener分别在impl或者程序运行中进行了实现。这种接口形式的结构可以在没有业务代码的情况下，整理好整个程序运行的框架，剩下就可以再填写.目前来看，这方面的能力需要多加练习

  程序主体流程包含了测试类、多线程下载类与网络连接管理类。

## 二、测试程序

​	感觉实际上测试类可以再封装一次，虽然会更加方便地使用，但是是否会牺牲很多的灵活性呢？还是看实际的应用吧。正所谓适可而止，过犹不及。

​	从测试来看的话，我们将url与文件地址传递到下载类中去，之后再在其中设置网络连接的管理类与下载完成的回调函数。这个回调函数是在所有线程全部完成之后进行回调。接下来开始下载，然后通过回调函数中的标识位来循环判断下载的进度。



## 三、多线程下载类

主流程：

​	1、使用CyclicBarrier来监测多个线程的结束与否，使用方法是在创建线程的run方法最后使用``` barrier.await()``` 来执行监测

​	2、然后通过网络连接管理类获取到connection的相关信息，我们就可以根据*连接的长度* 来使用RandomAccessFile创建一个空白文件来确定硬盘空间足够。在之后各个线程下载完毕就会改写这个文件相关部分，从而完成文件的合并。

​	3、根据下载的线程数来分配每个线程需要下载的部分，并开始创建线程，进行下载。

单个下载线程：

​	通过RandomAccessFile将下载下来的byte[]写入文件中，通过``` file.seek(index)``` 来确定写入文件的位置。



## 四、网络连接类

​	本次作业通过管理类将连接类隐藏了起来。连接类只是对包内可见，这样，程序的封装性显得很好，值得学习。

​	代码示例：

```java
@Override
public byte[] read(int startPos, int endPos) throws IOException {
  	//创建连接
	connect = (HttpURLConnection)url.openConnection();
    //纠结了许久的问题，原来是在setRequestProperty之前不可以使用getContentLength
  	connect.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
	
  	//获取数据
  	InputStream is = connect.getInputStream();
  	byte[] buff = new byte[BUFFER_SIZE];//建立临时缓存区
  	int totalLength = endPos - startPos + 1;
	//byte数组输出流，如此看的话，命名还是很有意思的
 	ByteArrayOutputStream baos = new ByteArrayOutputStream();

  	//将输入流中的数据先读到缓存区中，再将缓存区中的数据写入输出流中
  	while(baos.size() < totalLength){
    	int len = is.read(buff);
    	if (len < 0) {
      		break;
    	}
    	baos.write(buff,0,len);
 	}

  	//防止数组越界。这也就一位置上方的len应该只有-1和1024两种值吧，所以最后一次的读取会造成有少量的留白，这个操作是用来去除留白的。
  	if (baos.size() > totalLength) {
    	byte[] data = baos.toByteArray();
    	return Arrays.copyOf(data, totalLength);//转成byte[]的一种方法
  	}

  return baos.toByteArray();//转成byte[]的另一种方法
}

//这个byte[]如何使用呢？
RandomAccessFile file = new RandomAccessFile("filename", "rw");
file.seek(startPos);
file.write(data);//放在这里写入即可。
file.close();
```





