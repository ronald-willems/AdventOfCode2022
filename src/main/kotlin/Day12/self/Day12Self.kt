package Day12.self


import java.io.File
import kotlin.math.min

object Day12Self {
    var map = mutableMapOf<Pair<Int, Int>, Int>()
    var sizeX = 0
    var sizeY = 0
    var end = Pair(0, 0)

    val pointsVisited = mutableMapOf<Pair<Int, Int>, Int>()
    var start = Pair(0, 0)

    var finishedPaths = mutableListOf<Path>()
    var minPathLength = Integer.MAX_VALUE
    var winnerPath: Path? = null


    fun readinput(inputType: String) {
        var linenr = 0
        File("src/main/resources/Day12/${inputType}Input.txt").forEachLine { line ->
            sizeX = line.length
            for (i in 0..line.length - 1) {
                var pos = Pair(i, linenr)
                var height: Int;
                when (line[i].toString()) {
                    "S" -> {
                        height = 1
                        start = pos

                    }
                    "E" -> {
                        height = 26
                        end = pos
                    }
                    else -> height = line[i].code - 96

                }

                map.put(pos, height)

            }
            linenr++
        }
        sizeY = linenr

    }

    fun findShortestPath(startPos: Pair<Int,Int>,maxIt:Int= Int.MAX_VALUE){
        var whilenr = 0
        var newMovePossible = true
        var currPath = Path(mutableListOf(startPos))
        pointsVisited.put(startPos, 1)
        while (newMovePossible && whilenr < maxIt) {
            val moves = currPath.possibleMoves()
            val newPaths = mutableListOf<Path>()

            moves.forEach {
                val newPath = currPath.clonePlus(it)
                if (it == end) {
                    finishedPaths.add(newPath)
                    if (newPath.size() < minPathLength) {
                        minPathLength = newPath.size()
                        winnerPath = newPath
                        println("Winner: " + minPathLength)

                    }

                } else {
                    if (newPath.size() + newPath.distance <= minPathLength)
                        newPaths.add(newPath)
                }
            }
            if (newPaths.size > 0) {
                newPaths.sortBy { it.distance }
                newPaths.sortByDescending { it.height }
                currPath = newPaths[0]


                if (pointsVisited[currPath.last()]==null) pointsVisited.put(currPath.last(), currPath.size())
                else if (currPath.size()<pointsVisited[currPath.last()]!!) pointsVisited.put(currPath.last(), currPath.size())



            } else {

                currPath = currPath.cloneMin()

                if (currPath.size() <= 1) newMovePossible = false
            }

            whilenr++
        }
        println("iterations" + whilenr)
        currPath.display()
    }


    fun part1(inputType: String, maxIt: Int = Int.MAX_VALUE): Int {
        readinput(inputType)
        findShortestPath(start,maxIt)


        println("min length:" + (minPathLength - 1))

        //println(pointsVisited)
        if (winnerPath != null) winnerPath!!.display()

        return minPathLength - 1

    }

    fun part2(inputType: String):Int{
        readinput(inputType)
        println(start)

        var resultlist = mutableMapOf<Pair<Int,Int>,Int>()
        var possibleStarts = mutableListOf<Pair<Int,Int>>()
        var maxY = if (inputType=="Sample") 4 else 40
            //possibleStarts = mutableListOf(Pair(0,0), Pair(0,1),Pair(0,2),Pair(0,3),Pair(0,4))



        for (y in 0..40) {
            var tryStart = Pair(0,y)
            findShortestPath(tryStart)


            println("min length:" + (minPathLength - 1))

            //println(pointsVisited)
            if (winnerPath != null) winnerPath!!.display()

            //return minPathLength - 1

            //if (inputType=="Sample")

            resultlist.put(tryStart,minPathLength - 1)

            minPathLength = Int.MAX_VALUE
            pointsVisited.clear()
            finishedPaths.clear()
            winnerPath = null

        }

        resultlist.forEach { (println(it)) }

        return resultlist.minOf { it.value }
    }
}
