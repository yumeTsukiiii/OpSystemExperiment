package cn.yumetsuki.lab1

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Producer{

    suspend fun produce(room: Room) {
        GlobalScope.launch { room.increaseProduct() }
    }

}