package cn.net.pikachu

import com.thoughtworks.xstream.XStream

/**
 * Created by pikachu on 17-3-2.
 */
data class PhoneNumber(
        var code:Int,
        var number:String
)
data class Person(
        var firstname:String,
        var lastname:String,
        var phone:PhoneNumber,
        var fax:PhoneNumber
)

fun main(args: Array<String>) {
    val xstream = XStream()
    xstream.alias("person",Person::class.java)
    xstream.alias("phonenumber",PhoneNumber::class.java)

    val joe = Person("Joe","Walnes", PhoneNumber(123,"1234-456"), PhoneNumber(123,"9999-999"))

    val xml = xstream.toXML(joe)

    println(xml)

    val newJoe = xstream.fromXML(xml)
    println(newJoe)
}