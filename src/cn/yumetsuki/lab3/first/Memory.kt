package cn.yumetsuki.lab3.first

import java.util.*
import java.util.concurrent.atomic.AtomicInteger

object Memory {

    const val BLOCK_LENGTH = 128

    private const val DEFAULT_TABLE_SIZE = 5

    val pageTable: PageTable = hashMapOf()

    private val k = AtomicInteger(0)

    private val pageMemory: PageMemory = LinkedList()

    fun addPageInstance(page: Page){
        if(pageMemory.size < DEFAULT_TABLE_SIZE){
            pageMemory.add(page)
        } else {
            pageMemory[k.get()].flag = false
            pageMemory[k.get()] = page
            k.set((k.get() + 1) % DEFAULT_TABLE_SIZE)
        }
    }

}