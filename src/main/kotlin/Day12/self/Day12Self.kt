package Day12.self



import java.io.File
import java.lang.Integer.min
import kotlin.math.absoluteValue

object Day12Self {
    var map = mutableMapOf<Pair<Int,Int>,Int>()
    var sizeX = 0
    var sizeY =0
    var end = Pair(0,0)
    val openPaths = mutableListOf<Path>()

    var finishedPaths = mutableListOf<Path>()
    var minPathLength = Integer.MAX_VALUE
    var winnerPath:Path? = null


    fun readinput(inputType:String){
        var linenr = 0
        File("src/main/resources/Day12/${inputType}Input.txt").forEachLine { line ->
            sizeX = line.length
            for (i in 0.. line.length-1){
                var pos = Pair(i,linenr)
                var height: Int;
                when (line[i].toString()){
                    "S" -> {
                        height = 1
                        val startPath = Path(mutableListOf(pos))
                        openPaths.add(startPath)
                    }
                    "E" -> {
                        height = 26
                        end = pos
                    }
                    else -> height = line[i].code-96

                }

                map.put(pos,height)

            }
            linenr++
        }
        sizeY = linenr

    }



    fun part1(inputType:String, maxIt:Int = Int.MAX_VALUE){
        readinput(inputType)
        end = Pair(126,34)
        var whilenr = 0
        while (openPaths.size>0 && whilenr<maxIt){

            if (whilenr %(maxIt/100) == 0) {
                println(whilenr)
                println("aantal open" + openPaths.size)
                println("aantal klaar" + finishedPaths.size)
                println("distance to goal" + openPaths[0].distance)
                println("nr steps" + (openPaths[0].points.size-1))
                openPaths[0].display()

            }
            val currPath = openPaths[0]
            if (currPath.points.size+currPath.distance<= minPathLength) {
                val moves = currPath.possibleMoves()
                moves.forEach {
                    val newPath = Path(currPath.points.toMutableList())
                    newPath.points.add(it)
                    if (it == end) {
                        finishedPaths.add(newPath)
                        if (newPath.points.size<minPathLength){
                            minPathLength = newPath.points.size
                            winnerPath = newPath

                        }


                    } else openPaths.add(newPath)
                }
            }
            openPaths.remove(currPath)
            openPaths.sortBy { it.distance }
            //   openPaths.sortByDescending { it.height}
            whilenr++
        }

        println("iterations" + whilenr)
        println("min length:"+( minPathLength-1))
        if (winnerPath!=null)  winnerPath!!.display()

    }
}
