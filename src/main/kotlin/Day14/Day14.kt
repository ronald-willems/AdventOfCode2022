package Day14

import java.io.File
import java.util.regex.Pattern
import kotlin.math.min
import kotlin.math.max

object Day14 {

    var grid = mutableMapOf<Pair<Int, Int>, String>()
    var xAxis = intArrayOf(497, 500)
    var yAxis = intArrayOf(0, 5)

    fun displayGrid() {
        for (y in yAxis[0]..yAxis[1]+2) {
            for (x in xAxis[0]..xAxis[1]) {
                if (grid[Pair(x, y)] != null) {
                    print(grid[Pair(x, y)])
                } else print(".")
            }
            println()
        }


    }

    fun updateAxis(pos:Pair<Int,Int>){
        if (pos.first < xAxis[0]) xAxis[0] = pos.first
        if (pos.first > xAxis[1]) xAxis[1] = pos.first
        if (pos.second < yAxis[0]) yAxis[0] = pos.second
        if (pos.second > yAxis[1]) yAxis[1] = pos.second
    }

    fun readInput(inputType: String) {


        File("src/main/resources/Day14/${inputType}Input.txt").forEachLine { line ->
            val elements = line.split(Pattern.compile(" -> ")).map {
                var xy = it.split(",")
                Pair(xy[0].toInt(), xy[1].toInt())
            }
            for (i in 0..elements.size - 2) {


                for (x in min(elements[i].first, elements[i + 1].first)..max(elements[i].first, elements[i + 1].first))
                    for (y in min(elements[i].second, elements[i + 1].second)..max(
                        elements[i].second,
                        elements[i + 1].second
                    )) {

                        grid.put(Pair(x, y), "#")
                        updateAxis(Pair(x,y))

                    }

            }

        }
        xAxis[0] = xAxis[0]-10
        xAxis[1] = xAxis[1]+10
        //grid.put((Pair(500,0)),"+")

    }

    fun checkPos(pos: Pair<Int, Int>): Boolean {
        if (grid[pos] == null) return true
        return false
    }

    fun part1(inputType: String) {
        readInput(inputType)
        var units = 0
        var unitToRest = true
        while (unitToRest) {
            var newSand = Pair(500, 0)
            var newSandFinished = false
            while (!newSandFinished) {
                //Move down
                var newPos = Pair(newSand.first, newSand.second + 1)
                if (checkPos(newPos)) {
                    newSand = newPos
                } else {
                    //Move Left
                    var newPos = Pair(newSand.first - 1, newSand.second + 1)
                    if (checkPos(newPos)) {
                        newSand = newPos
                    } else {
                        //Move right
                        var newPos = Pair(newSand.first + 1, newSand.second + 1)
                        if (checkPos(newPos)) {
                            newSand = newPos
                        } else {
                            //Not possible to move: rest:
                            grid.put(newSand, "o")
                            newSandFinished = true
                        }

                    }
                }

                //println(newSand.second)
                if (newSand.second > yAxis[1]) {
                    println("groter")
                    newSandFinished = true
                    unitToRest = false
                }



            }
            units++

        }

        displayGrid()
        println(units - 1)

    }


    fun part2(inputType: String) {
        readInput(inputType)
        var units = 0
        var unitToRestAtBase = false
        while (!unitToRestAtBase) {
            var newSand = Pair(500, 0)
            var newSandFinished = false
            while (!newSandFinished) {
                //Move down
                var newPos = Pair(newSand.first, newSand.second + 1)
                if (checkPos(newPos)) {
                    newSand = newPos
                } else {
                    //Move Left
                    var newPos = Pair(newSand.first - 1, newSand.second + 1)
                    if (checkPos(newPos)) {
                        newSand = newPos
                    } else {
                        //Move right
                        var newPos = Pair(newSand.first + 1, newSand.second + 1)
                        if (checkPos(newPos)) {
                            newSand = newPos
                        } else {
                            //Not possible to move: rest:
                            grid.put(newSand, "o")
                            newSandFinished = true
                            if (newSand==Pair(500,0)) unitToRestAtBase = true
                        }

                    }
                }
                //println(newSand.second)
                if (newSand.second == yAxis[1]+1) {
                    grid.put(newSand, "o")
                    newSandFinished = true
                }



            }
            units++

        }

        displayGrid()
        println(units )


    }
}

