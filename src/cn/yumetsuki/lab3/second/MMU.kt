package cn.yumetsuki.lab3.second


class MMU {

    companion object {
        fun translate(instruction: Instruction): String{
            val pageIndex = instruction.logicalLocation.first
            return Memory.pageTable.findPage(pageIndex)?.run {
                if(this.flag){
                    calculateAbsoluteAddress(memoryIndex, instruction.logicalLocation.second)
                } else {
                    println("[MMU]: 页${pageIndex}不存在")
                    Memory.addPageInstance(this.apply { flag = true })
                    calculateAbsoluteAddress(memoryIndex, instruction.logicalLocation.second)
                }
            }?:throw PageIndexException()
        }

        private fun calculateAbsoluteAddress(memoryIndex: Int, unitIndex: UnitIndex): String{
            return (memoryIndex*Memory.BLOCK_LENGTH).toString() + unitIndex
        }
    }

}

class PageIndexException: Exception("Can not find page from this pageIndex")