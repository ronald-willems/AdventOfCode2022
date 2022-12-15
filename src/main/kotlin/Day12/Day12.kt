package Day12


import java.io.File
import kotlin.math.absoluteValue


object Day12 {

    var map = mutableMapOf<Pair<Int,Int>,Int>()
    var sizeX = 0
    var sizeY =0
    var end = Pair(0,0)
    var start = Pair(0,0)


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
                        start = pos
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






    fun isMax1Higher(curr:Pair<Int,Int>, new: Pair<Int,Int>):Boolean{
        val currHeight = map.get(curr)
        val newHeight = map.get(new)
        //check if on map.
        if (newHeight!=null){
            if ((newHeight -  currHeight!!).absoluteValue<=1) return true
            //if ((newHeight - 1) == currHeight!!) return true
            //if (newHeight  == currHeight!!) return true

            //if ((newHeight - 1) <= currHeight!!) return true
        }
        return false
    }
    fun possibleMoves(currentPos:Pair<Int,Int>): List<Pair<Int,Int>>{
        var result = mutableListOf<Pair<Int,Int>>()

        //Up
        val up = Pair(currentPos.first,currentPos.second-1)
        if (isPossible(currentPos, up)) result.add(up)
        var down = Pair(currentPos.first,currentPos.second+1)
        if (isPossible(currentPos, down)) result.add(down)
        var left = Pair(currentPos.first+1,currentPos.second)
        if (isPossible(currentPos, left)) result.add(left)
        var right = Pair(currentPos.first-1,currentPos.second)
        if (isPossible(currentPos, right)) result.add(right)

        return result

    }

    fun isPossible(curr: Pair<Int,Int>, new: Pair<Int,Int>):Boolean{
        return isMax1Higher(curr,new)
    }



    fun posToInt(pair: Pair<Int,Int>):Int{
        return pair.second*sizeX + pair.first
        //return ((pair.first+1000).toString()+(pair.second+1000).toString()).toInt()
    }

    fun intToPos(pos: Int):Pair<Int,Int>{
        return Pair(pos % sizeX,pos.div(sizeX))
        //return Pair(pos.toString().substring(0,4).toInt()-1000,pos.toString().substring(4,8).toInt()-1000)
    }


    fun part1(inputType:String) {
        readinput(inputType)


//        val nodes = sizeX*sizeY
//        val edges: Array<IntArray> = Array(nodes) { IntArray(nodes) { -1 } }
        var graph = mutableListOf<Edge>()

        for (y in 0..sizeY-1){
            for (x in 0..sizeX-1){
                var pos = Pair(x,y)

                possibleMoves(pos).forEach {
                    //edges[posToInt(pos)][posToInt(it)] = 1
                    val e = Edge(IntNode(posToInt(pos)), IntNode(posToInt(it)), 1)
                    graph.add(e)
                }



            }
        }
        //TODO:

        val result = findShortestPath(graph, IntNode(posToInt(start)), IntNode(posToInt(end)))
        //println(result.shortestPath() )
        println("test")
        println(result.shortestDistance() )





    }


    fun dijkstra(source: Int, edges: Array<IntArray>, nodes: Int) {
        // Initialize single source
        val d = IntArray(nodes) { Integer.MAX_VALUE }
        val pi = IntArray(nodes) { -1 }
        d[source] = 0

        val S: MutableList<Int> = ArrayList()
        val Q: MutableList<Int> = (0 until nodes).toMutableList()

        // Iterations
        while (Q.isNotEmpty()) {
            val u: Int = extractMin(Q, d)
            S.add(u)

            edges[u].forEachIndexed { v, vd ->
                if (vd != -1 && d[v] > d[u] + vd) {
                    d[v] = d[u] + vd
                    pi[v] = u
                }
            }
        }

        println("d: ${d.contentToString()}")
        println("pi: ${pi.contentToString()}")
        println("Naar eind" + d.get(posToInt(end)) )
        println("Naar Eind" + pi.get(posToInt(end)))
        println(d.size)
    }

    fun extractMin(Q: MutableList<Int>, d: IntArray): Int {
        var minNode = Q[0]
        var minDistance: Int = d[0]

        Q.forEach {
            if (d[it] < minDistance) {
                minNode = it
                minDistance = d[it]
            }
        }

        Q.remove(minNode)
        return minNode
    }
}