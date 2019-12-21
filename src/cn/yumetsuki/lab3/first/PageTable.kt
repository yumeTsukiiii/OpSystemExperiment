package cn.yumetsuki.lab3.first

import java.util.*
import kotlin.collections.HashMap

typealias PageTable = HashMap<PageIndex, Page>
typealias PageMemory = LinkedList<Page>

fun PageTable.addPage(page: Page){
    this[page.pageIndex] = page
}

fun PageTable.findPage(pageIndex: PageIndex): Page? = this[pageIndex]