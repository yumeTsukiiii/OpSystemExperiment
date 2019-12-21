package cn.yumetsuki.lab1

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) = runBlocking {

    val room = Room()
    val producer = Producer()
    val customer = Customer()
    GlobalScope.launch {
        while (true) producer.produce(room)
    }
    GlobalScope.launch {
        while (true) customer.consume(room)
    }

    delay(2000)

}