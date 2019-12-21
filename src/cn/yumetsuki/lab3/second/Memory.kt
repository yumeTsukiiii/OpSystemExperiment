package cn.yumetsuki.lab3.second

import java.util.*
import java.util.concurrent.atomic.AtomicInteger

object Memory {

    const val BLOCK_LENGTH = 128

    private const val DEFAULT_TABLE_SIZE = 5

    val pageTable: PageTable = hashMapOf()

    private val k = AtomicInteger(0)

    private val pageMemory: PageMemory = LinkedList()

    fun isFull(): Boolean = pageMemory.size == DEFAULT_TABLE_SIZE

    fun addPageInstance(page: Page){
        if(pageMemory.size < DEFAULT_TABLE_SIZE){
            pageMemory.add(page)
            page.memoryIndex = pageMemory.size - 1
        } else {
            println("[memory]: 内存满了")
            val first = pageMemory[k.get()]
            if (page.changeFlag) {
                println("[memory]: 调出页${first.pageIndex}")
            } else {
                println("[memory]: 覆盖页${first.pageIndex}")
            }
            first.memoryIndex = -1
            first.flag = false
            pageMemory[k.get()] = page
            page.memoryIndex = k.get()
            k.set((k.get() + 1) % DEFAULT_TABLE_SIZE)
        }
        println("[memory]: 装入页${page.pageIndex}")
        val info = pageMemory.map { "页号--${it.pageIndex}" }
        println("[memory]: 当前内存的情况：$info")
    }

}