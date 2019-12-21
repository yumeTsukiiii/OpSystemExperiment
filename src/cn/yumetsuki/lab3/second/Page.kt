package cn.yumetsuki.lab3.second

class Page(
    val pageIndex: Int,
    var memoryIndex: Int,
    var location: String,
    var flag: Boolean = false,
    var changeFlag: Boolean = false
)