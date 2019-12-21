package cn.yumetsuki.lab3.second

import java.util.*
import kotlin.collections.HashMap

typealias PageTable = HashMap<PageIndex, Page>
typealias PageMemory = LinkedList<Page>

fun PageTable.addPage(page: Page){
    //初始化，内存没满的情况下，指令已经在内存中，则把它加进去
    if (page.flag && !Memory.isFull()) Memory.addPageInstance(page)
    this[page.pageIndex] = page
}

fun PageTable.findPage(pageIndex: PageIndex): Page? = this[pageIndex]