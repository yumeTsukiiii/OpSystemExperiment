package cn.yumetsuki.lab2.first

class PCB(
    val name: String,  //进程名
    var next: PCB?,  //下一个进程
    var needTime: Int,
    var priority: Int,
    var status: ProcessStatus
){

    fun run(){
        status = ProcessStatus.Working
        needTime-=1
        priority-=1
        //println("[PCB-$name]: I'm working----priority: $priority")
    }

    fun ready(){
        status = ProcessStatus.Ready
        //println("[PCB-$name]: I'm ready----priority: $priority")
    }

    fun finish(){
        status = ProcessStatus.Finish
    }

}

enum class ProcessStatus{
    Ready(), Working(), Finish()
}