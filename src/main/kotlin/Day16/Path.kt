package Day16

import kotlin.random.Random

class Path {
    val valves = mutableListOf<Valve>()
    val turnedValves = mutableListOf<Valve>()



    fun initValves(inputlist: String) {
        for (i in 0..inputlist.length - 2 step 2) {
            valves.add(Day16.createOrGetValve(inputlist.substring(i, i + 2)))
        }
    }

    fun addValve(valve:Valve){
     valves.add(valve)
    }

    fun turnValve(valve:Valve){
        println("Turn" + valve.name)
        valves.add(valve)
        turnedValves.add(valve)
    }


    fun calcPressure(): Int {
        var pressurePM = 0
        var totalPressure = 0
        var p = 0 //position
        var m = 1 //minute
        while (m <= 30) {
            totalPressure += pressurePM
            //    println("Minute: " + m + ". Current Pressure: " + pressurePM )
            p++;
            if (p > 0 && p < valves.size && valves[p] == valves[p - 1]) {
                //println("Open valve:" + valves[p].name + " Add pressure: " + valves[p].rate)
                pressurePM += valves[p].rate
            }
            m++


        }
        return totalPressure
    }

    override fun toString():String{
        return valves.map {  it.name }.toString()
    }
}