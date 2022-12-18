package Day17

import java.io.File

object Day17 {
    var instructions = ""
    var opendirs = ""
    var cave = mutableListOf(IntArray(9) { 2 })
    var removedRows :Long = 0
    var previousEnd:Long = 0
    var previousRock:Long = 0
    var part2Continue = true;

    //var highPoint = 0
    var highPoints = IntArray(7) { 0 }

    fun endResult(): Long{
        return removedRows + highestPoint()
    }

    fun highestPoint(): Int {
        //return bottom.maxOrNull()!!
        return highPoints.maxOrNull()!!;
    }

    fun printBottom() {
        cave.reversed().forEach { it.forEach { if (it == 2) print("#") else print(".") };println() }
    }


    fun addEmptyRow() {
        cave.add(intArrayOf(2, 0, 0, 0, 0, 0, 0, 0, 2))
    }

    fun getNextInput(i:Long): Int {
        var result = ""
        if (opendirs.length == 0) {
            opendirs = instructions
            //TODO verschil is 2647. Is aantal rocks ook gelijk.



            if ((1000000000000-i).mod(1730)==0){
                println("Current state: " + endResult() + "Verschil: " + (endResult()- previousEnd))
                println("Current rock: " + i + "Verschil: " + (i - previousRock))
                part2Continue = false
            }

            previousEnd = endResult()
            previousRock = i
        }
        result = opendirs[0].toString()
        opendirs = opendirs.substring(1)
        // println(result)


        return if (result == "<") -1 else 1
    }

    fun readInput(inputType: String) {
        File("src/main/resources/Day17/${inputType}Input.txt").forEachLine {
            instructions = it
        }
        opendirs = instructions
        for (i in 1..8) {
            addEmptyRow()
        }


    }

    fun extendCave(){
        for (i in 1..highestPoint() - cave.size + 9) {
            addEmptyRow()
        }

        //Delete rows
        val toRemove = highPoints.minOrNull()!!-2
        if (toRemove>3) {
            removedRows += toRemove
            for (i in 0..6) {
                highPoints[i] -= toRemove
            }

            for (i in 1..toRemove) {
                cave.removeFirst()
            }
        }


    }

    fun doTurn(i:Long){
        val rock =
            when (i % 5) {
                1.toLong() -> Rock1()
                2.toLong() -> Rock2()
                3.toLong() -> Rock3()
                4.toLong() -> Rock4()
                else -> Rock5()
            }
        var stuck = false
        while (!stuck) {
            rock.moveH(getNextInput(i))
            if (!rock.moveDown()) {
                stuck = true
                rock.addToBottom()
            }
        }
        extendCave()
    }

    fun part1(inputType: String, it:Long =2022): Long {
        readInput(inputType)

        for (i in 1..it) {
          doTurn(i)
        }
        var result = removedRows + highestPoint()
        println("Result: " +  result + ". Current highest: " +  highestPoint()   )
        return result

    }

    fun part2b(inputType: String, it:Long ):Long{
        return part1(inputType,it)

    }

    fun part2(inputType: String): Long {
        readInput(inputType)
        var turns:Long = 1
        while (part2Continue){
            doTurn(turns)
            turns++
        }

        return endResult()
    }


}