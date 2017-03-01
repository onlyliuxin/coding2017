##  2017编程提高社群作业：实现基本的数据结构(2017.2.19)

####    [所有基本数据结构实现类及接口](https://github.com/china-kook/coding2017/tree/master/group18/935542673/Coding/20170219/src/com/ikook/basic_data_structure)

1. 实现了ArrayList、LinkedList、Queue(依靠LinkedList实现)、Stack(依靠ArrayList实现)

   1.1  ArrayList实现了以下方法：

   ```
    （1）add(Object): 添加元素到集合；
    （2）add(int, Object): 添加元素到集合的指定位置；
    （3）size(): 返回集合的大小，类型为 int；
    （4）isEmpty(): 判断集合是否为空，类型为 boolean；
    （5）get(int): 获取集合指定位置的元素；
    （6）remove(int): 删除指定位置的对象；
    （7）remove(Object): 删除指定的对象；
    （8）set(int, Object): 更改集合中指定位置的元素，并返回原来的对象；
    （9）iterator(): 返回一个迭代器的实现类。
   ```

   1.2  LinkedList实现以下方法：

   ```
    （1）addFirst(Object): 在链表头部插入新的元素；
    （2）addLast(Object): 在链表尾部插入新的元素；
    （3）add(Object): 在链表中插入新的元素；
    （4）add(int, Object): 在链表指定位置插入新的元素；
    （5）size(): 返回链表的大小，类型为 int；
    （6）isEmpty(): 判断链表是否为空，类型为 boolean；
    （7）getFirst(): 获取链表头部的元素；
    （8）getLast():  获取链表尾部的元素；
    （9）get(int): 获取链表指定位置的元素；
    （10）set(int, Object): 更改链表中指定位置的元素，并返回原来的对象。
    （11）removeFirst(): 删除链表头部的元素；
    （12）removeLast(int):  删除链表尾部的元素；
    （13）remove(Object): 删除指定元素；
    （14）remove(int): 删除指定位置的元素；
    （15）iterator(): 返回一个迭代器的实现类。         
   ```

   1.3  Queue实现了以下方法：

   ```
    （1）enQueue(Object): 入队操作；
    （2）deQueue(): 出队操作；
    （3）size(): 返回队列的长度；
    （4）isEmpty(): 判断队列是否为空。
   ```

   1.4  Stack实现了以下方法：

   ```
    （1）push(Object)：入栈操作；
    （2）pop()：出栈操作；
    （3）getTop()：获取栈顶元素；
    （4）isEmpty()：判断栈是否为空；
    （5）size()：获取栈的深度。
   ```
   ​

2. 实现了BinarySearchTree、Iterator接口

   2.1  BinarySearchTree实现了以下方法：

   ```
     （1）insert(int)：插入操作；
     （2）find(int)：查找操作；
     （3）delete(int)：删除操作；
     （4）inorderTraverse(Node)：遍历操作，采用中序遍历。
   ```

   2.2  Iterator定义了以下方法：

   ```
     （1）hasNext()：判断是否有元素没有被遍历；
     （2）next()：返回游标当前位置的元素并将游标移动到下一个位置；
     （3）remove()：删除游标左边的元素，在执行完 next 之后该操作只能执行一次。
   ```
   ​

   #### [所有基本数据结构测试类](https://github.com/china-kook/coding2017/tree/master/group18/935542673/Coding/20170219/junit/com/ikook/basic_data_structure)

    说明：由于作业以实现基本的数据结构为主，则在实现单元测试时，只对正常情况进行了测试，一些异常情况并进行编写测试用例。
