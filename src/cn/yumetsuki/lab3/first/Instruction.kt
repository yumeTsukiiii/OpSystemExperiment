package cn.yumetsuki.lab3.first

typealias PageIndex = Int
typealias UnitIndex = String
typealias LogicalLocation = Pair<PageIndex, UnitIndex>

class Instruction(
    val operation: Operation,
    val logicalLocation: LogicalLocation
){

    fun run(location: String){
        println("[Instruction-$location]: run $operation")
    }

}

enum class Operation{
    Add, Sub, Multi, Div, Shift, Load, Write
}