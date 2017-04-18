/**
 * Created by pikachu on 2017/4/10.
 */

fun main(args: Array<String>) {
    (1..25).forEach {
        if(it<10)
        println("group0${it}/")
        else{
            println("group${it}/")
        }
    }
}