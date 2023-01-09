package Day16

import java.io.File

object Day16 {

    val sortedValves = mutableListOf<Valve>()
    var sortedValvesWithGate =  listOf<Valve>()
    val allValves = mutableListOf<Valve>()
    val valvesMap = mutableMapOf<String,Valve>()
    val allTunnelsDirections = mutableListOf<Pair<Valve,Valve>>()
    val allEdges = mutableListOf<Edge>()
    lateinit var firstValve :Valve
    var part2 = false




    fun createOrGetValve(name:String):Valve{
        val found = valvesMap[name]
        if (found != null) return found
        else {
            val newValve = Valve(name)
            allValves.add(newValve)
            valvesMap.put(name,newValve)
            return newValve
        }
    }

    fun addDistancesToValves(){
        allValves.forEach { a->
            allValves.forEach { b->
                if (a!=b && (a.rate>0 || a.name =="AA") && b.rate>0) {
                    val dest = Pair(b, findShortestPath(allEdges, a, b).shortestDistance()!!)
                    a.destinations.add(dest)
                }

            }



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
                allEdges.add(Edge(newValve,connectedValve,1))
            }
        }
        firstValve = valvesMap["AA"]!!
        sortedValves.addAll( allValves.sortedByDescending{it.rate})
        sortedValvesWithGate = sortedValves.filter { it.rate>0 }
        addDistancesToValves()
    }

    fun printResult(steps: MutableList<Step>){
        val pf = PathFinder(26,true)
        steps.removeFirst()
        steps.forEach {
            pf.addStep(it)
            println("Step Added:" + it)
            println(pf.state())
        }

    }

    fun part1(inputType: String):Int{
        readInput(inputType)
       val pf = PathFinder()
        val result = pf.maximize()
        println("Eindresultaat:  " + result)
        return result

    }


    fun part2(inputType: String):Int{
        readInput(inputType)

        val pf = PathFinder(26,true)
        val result = pf.maximize()
        println("Eindresultaat:  " + result)

        printResult(pf.resultSteps.toMutableList())

        return result

    }

}