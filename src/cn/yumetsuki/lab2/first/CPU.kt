package cn.yumetsuki.lab2.first


fun main(args: Array<String>) {

    val list = arrayListOf<Pair<PCB, ReadyQueue.CpuTime>>()

    val pcbTime = hashMapOf<PCB, Pair<Int, Int>>()

    val queue = ReadyQueue()

    init(queue, pcbTime)

    var time = 0
    showInfo(time, queue, list)
    time+=1

    while (!queue.isEmpty()){

        schedule(queue, list, pcbTime)

        showInfo(time, queue, list)
        time+=1

    }
    println("name    round_time    waiting_time")
    pcbTime.forEach { pcb, pair ->
        println("${pcb.name}\t\t\t${pair.first}\t\t\t${pair.second}")
    }


}

fun showInfo(time: Int, queue: ReadyQueue, list: ArrayList<Pair<PCB, ReadyQueue.CpuTime>>){
    println("[cpu_time]: $time")
    println("name    cpu_time    need_time   priority   state")
    queue.forEach {
        println("${it.first.name}\t\t${it.second.time}\t\t\t${it.first.needTime}\t\t\t${it.first.priority}\t\t\t${it.first.status}")
    }
    list.forEach {
        println("${it.first.name}\t\t${it.second.time}\t\t\t${it.first.needTime}\t\t\t${it.first.priority}\t\t\t${it.first.status}")
    }
}

fun init(queue: ReadyQueue, pcbTime: HashMap<PCB, Pair<Int, Int>>) {
    val p1 = PCB("P1", null, 2, 1, ProcessStatus.Ready)
    val p5 = PCB("P5", null, 4, 2, ProcessStatus.Ready)
    val p3 = PCB("P3", null, 1, 3, ProcessStatus.Ready)
    val p4 = PCB("P4", null, 2, 4, ProcessStatus.Ready)
    val p2 = PCB("P2", null, 3, 5, ProcessStatus.Ready)
    queue.apply {
        add(p1)
        add(p2)
        add(p3)
        add(p4)
        add(p5)
    }
    pcbTime.apply {
        put(p1, Pair(1, 0))
        put(p2, Pair(1, 0))
        put(p3, Pair(1, 0))
        put(p4, Pair(1, 0))
        put(p5, Pair(1, 0))
    }
}

fun schedule(queue: ReadyQueue, list: ArrayList<Pair<PCB, ReadyQueue.CpuTime>>, pcbTime: HashMap<PCB, Pair<Int, Int>>){
    val pair = queue.shift()!!
    val cpuTime = pair.second
    if (!cpuTime.isExecuted) cpuTime.isExecuted = true
    pair.first.run()

    cpuTime.time+=1
    if (pair.first.needTime == 0) {
        pair.first.finish()
        list.add(pair)

    } else {
        queue.add(pair.first, pair.second)
    }
    queue.forEach {
        val pcbTimeValue = pcbTime[it.first]!!
        val roundTime = pcbTimeValue.first + 1
        var waitingTime = pcbTimeValue.second

        if (it.first != pair.first){
            if (it.second.isExecuted){
                it.second.time+=1
            }
            it.first.ready()
            waitingTime++

        }
        pcbTime[it.first] = pcbTimeValue.copy(first = roundTime, second = waitingTime)
    }
}