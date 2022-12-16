package Day16

import java.io.File

object Day16 {

    val allValves = mutableListOf<Valve>()
    val allTunnelsDirections = mutableListOf<Pair<Valve,Valve>>()
    var firstValve = Valve("XX")


    fun createOrGetValve(name:String):Valve{
        val found = allValves.filter { it.name==name }
        if (found.size>0) return found[0]
        else {
            val newValve = Valve(name)
            allValves.add(newValve)
            return newValve
        }
    }
    fun readInput(inputType: String) {
        File("src/main/resources/Day16/${inputType}Input.txt").forEachLine { line ->
            val elements = line.replace(",", "").replace(";", "").replace("=", " ").split(" ")
            val newValve = createOrGetValve(elements[1])
            newValve.rate=elements[5].toInt()
            for (i in 10..elements.size-1){
                val connectedValve = createOrGetValve(elements[i])
                newValve.connections.add(connectedValve)
                allTunnelsDirections.add(Pair(newValve,connectedValve))
            }
        }
        firstValve = allValves.first()
    }

    fun part1(inputType: String){
        var current = firstValve
        var minutes = 0
        var pressure = 0
        while (minutes<30){


        }


    }


}