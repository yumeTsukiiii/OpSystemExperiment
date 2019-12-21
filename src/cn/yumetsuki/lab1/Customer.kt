package cn.yumetsuki.lab1

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Customer {

    suspend fun consume(room: Room) {
        GlobalScope.launch { room.decreaseProduct() }
    }

}