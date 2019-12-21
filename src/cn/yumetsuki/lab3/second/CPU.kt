package cn.yumetsuki.lab3.second

import java.util.*

fun main(args: Array<String>) {

    initMemory()

    val instructions = init()

    instructions.forEach {
        it.run(MMU.translate(it))
    }

}

fun initMemory() {
    Memory.pageTable.apply {
        addPage(Page( 0, 5, "011", true,  changeFlag = true))
        addPage(Page(1, 8,"012",true))
        addPage(Page( 2, 9, "013", true))
        addPage(Page(3, 1, "021", true))
        addPage(Page( 4, -1, "022"))
        addPage(Page( 5, -1, "023"))
        addPage(Page( 6, -1, "121"))
    }
}

fun init(): LinkedList<Instruction> =

    LinkedList<Instruction>().apply {
        add(Instruction(Operation.Add, LogicalLocation(0, "070")))
        add(Instruction(Operation.Shift, LogicalLocation(4, "053")))
        add(Instruction(Operation.Add, LogicalLocation(1, "050")))
        add(Instruction(Operation.Add, LogicalLocation(5, "023")))
        add(Instruction(Operation.Multi, LogicalLocation(2, "015")))
        add(Instruction(Operation.Write, LogicalLocation(1, "037")))
        add(Instruction(Operation.Write, LogicalLocation(3, "021")))
        add(Instruction(Operation.Load, LogicalLocation(2, "078")))
        add(Instruction(Operation.Load, LogicalLocation(0, "056")))
        add(Instruction(Operation.Add, LogicalLocation(4, "001")))
        add(Instruction(Operation.Sub, LogicalLocation(6, "040")))
        add(Instruction(Operation.Write, LogicalLocation(6, "084")))
    }