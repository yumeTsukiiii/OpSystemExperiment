package cn.yumetsuki.lab3.first

class MMU {

    companion object {
        fun translate(instruction: Instruction): String{
            val pageIndex = instruction.logicalLocation.first
            return Memory.pageTable.findPage(pageIndex)?.run {
                if(this.flag){
                    calculateAbsoluteAddress(memoryIndex, instruction.logicalLocation.second)
                } else {
                    "*$pageIndex"
                }
            }?:throw PageIndexException()
        }

        private fun calculateAbsoluteAddress(memoryIndex: Int, unitIndex: UnitIndex): String{
            return (memoryIndex*Memory.BLOCK_LENGTH).toString() + unitIndex
        }
    }

}
class PageIndexException: Exception("Can not find page from this pageIndex")