package cn.yumetsuki.lab2.first

class ReadyQueue {

    private var head: PCB? = null

    private var cpuTimeHead: CpuTime? = null

    fun add(pcb: PCB){
        add(pcb, CpuTime(0, null, false))
    }

    fun add(pcb: PCB, cpuTime: CpuTime){
        head?.apply {
            addPCB(null, this, pcb, null, cpuTimeHead,
                cpuTime)
        }?:{ head = pcb; cpuTimeHead = cpuTime }()
    }

    fun shift(): Pair<PCB, CpuTime>?{
        return head?.let {
            val tempPCB = it
            val tempCpuTime = cpuTimeHead!!
            head = it.next
            cpuTimeHead = cpuTimeHead!!.next
            Pair(tempPCB, tempCpuTime)
        }
    }

    fun element(): Pair<PCB, CpuTime>?{
        return head?.let {
            val tempPCB = it
            val tempCpuTime = cpuTimeHead!!
            Pair(tempPCB, tempCpuTime)
        }
    }

    fun isEmpty(): Boolean = head == null

    private fun addPCB(previous: PCB?,
                       current: PCB?,
                       pcb: PCB,
                       prevCpuTime: CpuTime?,
                       curCpuTime: CpuTime?,
                       cpuTime: CpuTime
    ){
        if (current == null) {
            previous!!.next = pcb
            prevCpuTime!!.next = cpuTime
            pcb.next = null
            cpuTime.next = null
            return
        }
        if (pcb.priority > current.priority){
            if (previous == null) {
                pcb.next = head
                cpuTime.next = cpuTimeHead
                head = pcb
                cpuTimeHead = cpuTime
                return
            }
            swap(previous, current, pcb, prevCpuTime, curCpuTime, cpuTime)
        } else if (pcb.priority == current.priority){
            if (cpuTime.time < curCpuTime!!.time) {
                swap(previous, current, pcb, prevCpuTime, curCpuTime, cpuTime)
            } else {
                addPCB(current, current.next, pcb, curCpuTime, curCpuTime.next, cpuTime)
            }
        } else {
            addPCB(current, current.next, pcb, curCpuTime, curCpuTime!!.next, cpuTime)
        }
    }

    fun forEach(block: (item: Pair<PCB, CpuTime>)->Unit){
        var current = head
        var curCpuTime = cpuTimeHead
        while (current != null){
            block(Pair(current, curCpuTime!!))
            current = current.next
            curCpuTime = curCpuTime.next
        }
    }

    private fun swap(previous: PCB?,
                     current: PCB?,
                     pcb: PCB,
                     prevCpuTime: CpuTime?,
                     curCpuTime: CpuTime?,
                     cpuTime: CpuTime
    )
    {
        pcb.next = current
        previous!!.next = pcb
        cpuTime.next = curCpuTime
        prevCpuTime!!.next = cpuTime
    }

    class CpuTime(var time: Int, var next: CpuTime?, var isExecuted: Boolean)

}