package Day23

import javafx.geometry.Pos
import java.io.File

object Day23 {
    val allElves = mutableListOf<Elve>()
    val elvesMap = mutableMapOf<Position,Elve>()
    val sN = mutableListOf<Direction>(Direction(-1,-1), Direction(0,-1), Direction(1,-1))
    var sS = mutableListOf<Direction>(Direction(-1,1), Direction(0,1), Direction(1,1))
    var sW = mutableListOf<Direction>(Direction(-1,-1), Direction(-1,0), Direction(-1,1))
    var sE = mutableListOf<Direction>(Direction(1,-1), Direction(1,0), Direction(1,1))

    var searchDirs = mutableListOf<MutableList<Direction>>(sN,sS,sW,sE)
    val allProposals = mutableMapOf<Position,Int>()

    fun addProposal(pos:Position){
        if (allProposals[pos]!=null)
            allProposals.put(pos,allProposals[pos]!!+1)
        else allProposals.put(pos,1)
    }


    fun fillElvesMap(){
        elvesMap.clear()
        allElves.forEach {
            //println("fill: " + it.pos)
            elvesMap[it.pos] = it }

    }

    fun readInput(inputType: String) {
        var lineNr=0
        File("src/main/resources/Day23/${inputType}Input.txt").forEachLine { line ->
            var posNr=0
            line.forEach {
                if (it.toString()=="#") {
                    val pos = Position(posNr, lineNr)
                    val elve = Elve(pos)

                    allElves.add(elve)
                }
                posNr++
            }
            lineNr++
        }
        fillElvesMap()

    }

    fun doStep(): Boolean{
        //println(searchDirs.first())
        //println(searchDirs.last())
        allElves.forEach { it.propose()}
        val result = allProposals.size>0
        allElves.forEach { it.move() }
        fillElvesMap()
        allProposals.clear()

        //Change search dir
        val toLast = searchDirs.removeFirst()
        searchDirs.add(toLast)


        return result

    }

    fun part1(inputType: String){
        readInput(inputType)

        for (i in 1..10){
            doStep()

        }
        display()

    }

    fun part2(inputType: String){
        readInput(inputType)
        var step = 0
        var toContinue = true
        while (toContinue){
            step++
            toContinue= doStep()

        }
        display()
        println("Steps: " + step)

    }

    fun display(){
        println()
        var result = 0;
        val minX = allElves.minOf { it.pos.x }
        val minY = allElves.minOf { it.pos.y }
        val maxX = allElves.maxOf { it.pos.x }
        val maxY = allElves.maxOf { it.pos.y }

        for (y in minY..maxY) {
            for (x in minX..maxX) {
                if (elvesMap.get(Position(x,y))!=null) print("#") else {
                    print(".")
                    result++
                }
            }
            println()
        }
        println()
        println("Empty: " + result)



    }
}

class Elve(var pos: Position){
    var proposal: Position? = null;
    fun nextPos():Position?{

        val possibleDirs = mutableListOf<MutableList<Direction>>()

        Day23.searchDirs.forEach { windDir ->
            var possible = true;
            windDir.forEach {
                val checkPos = pos.addDirection(it)
                if (Day23.elvesMap[checkPos]!=null) possible = false
            }
            if(possible) possibleDirs.add(windDir)
            //return pos.addDirection(it[1])

        }
        if (possibleDirs.size==4) return null;
        else if (possibleDirs.size>0) return pos.addDirection(possibleDirs.first()[1])
        else return null




    }

    fun propose(){
         proposal  = nextPos()
        if (proposal != null) Day23.addProposal(proposal!!)

    }

    fun move(){

        if (proposal != null) {
            val nr = Day23.allProposals.get(proposal)!!
            //if (Day23.allElves.indexOf(this)==0) println("NR" + nr)
            if (nr==1) pos = proposal!!
        }
        proposal=null


    }

}

data class Position(val x:Int, val y:Int){
    fun addDirection(dir:Direction):Position{
        return Position(x+dir.x,y+dir.y)

    }
}
data class Direction(val x:Int, val y:Int)