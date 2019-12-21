package cn.yumetsuki.lab1

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock



class Room {

    private var product = 0

    private var mutex = Mutex()

    suspend fun increaseProduct() {
        mutex.withLock{
            if (product < 5){
                product++
                println("[producer]: 生产者生产了一个不知道什么玩意，现在一共有${product}个产品")
            }
        }
    }

    suspend fun decreaseProduct() {
        mutex.withLock{
            if (product > 0){
                product--
                println("[customer]: 消费者消费了了一个不知道什么玩意，现在一共有${product}个产品")
            }
        }
    }

}