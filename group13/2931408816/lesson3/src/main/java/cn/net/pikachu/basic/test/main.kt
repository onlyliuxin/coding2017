package cn.net.pikachu.basic.test

import cn.net.pikachu.basic.LinkedList

/**
 * Created by pikachu on 2017/3/12.
 */
fun main(args: Array<String>) {
    val list = LinkedList()

    list.add(2)
    list.add(5)
    list.add(7)
    list.add(10)
    val l = LinkedList();
    l.add(2)
    l.add(7)
    l.add(8)
    l.add(10)
    val ll = list.intersection(l)
    println(ll.toString())
}